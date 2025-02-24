#![allow(unused)]

use log::{error, info};
use serde::Deserialize;
use std::borrow::BorrowMut;
use std::collections::hash_map::Iter;
use std::collections::HashMap;
use std::env;
use std::io::{Read, Write};
use std::mem::ManuallyDrop;
use std::net::{Shutdown, TcpListener, TcpStream};
use std::sync::{Arc, Mutex, MutexGuard};
use std::thread;
use std::time::Duration;

#[derive(Debug)]
enum Message {
    Register(RegisterMessage),
    Command(CommandMessage),
}

#[derive(Debug)]
struct CommandMessage {
    port: u16,
    command: String,
}

#[derive(Debug)]
struct RegisterMessage {
    device_type: DeviceType,
    name: String,
}

#[derive(Eq, PartialEq, Debug)]
enum MessageType {
    Register,
    Command,
}

#[derive(Eq, PartialEq, Debug)]
enum DeviceType {
    Hub,
    Light,
    Fan,
    Plug,
    AirConditioner,
}

impl DeviceType {
    fn from_str(device_type: &str) -> Option<Self> {
        match device_type {
            "hub" => Some(DeviceType::Hub),
            "light" => Some(DeviceType::Light),
            "fan" => Some(DeviceType::Fan),
            "airConditioner" => Some(DeviceType::AirConditioner),
            "plug" => Some(DeviceType::Plug), 
            _ => None,
        }
    }

    fn to_str(&self) -> &str {
        match self {
            DeviceType::Hub => "hub",
            DeviceType::Light => "light",
            DeviceType::Fan => "fan",
            DeviceType::AirConditioner => "airConditioner",
            DeviceType::Plug => "plug",
        }
    }
}

#[derive(Debug)]
struct Client {
    stream: TcpStream,
    port: u16,
    info: Option<ClientInfo>,
}

#[derive(Debug)]
struct ClientInfo {
    name: String,
    device_type: DeviceType,
}

impl Client {
    fn new(stream: TcpStream, port: u16) -> Self {
        Self {
            stream,
            info: None,
            port,
        }
    }
}

type ConnectionMap = HashMap<u16, Client>;
type ConnectionMapArc = Arc<Mutex<ConnectionMap>>;

#[derive(Debug)]
struct Server {
    connections: ConnectionMapArc,
}

impl Server {
    fn new() -> Self {
        let connections: ConnectionMapArc = Arc::new(Mutex::new(HashMap::new()));

        Self { connections }
    }

    fn add_connection(&self, port: u16, client: Client) {
        self.connections.lock().unwrap().insert(port, client);
    }
}

// This is the function that sends a message to all hub connections.
fn send_message_to_hubs(server: Arc<Server>) {
    loop {
        thread::sleep(Duration::from_secs(2));

        let mutex = server.connections.lock().unwrap();

        let device_connections = mutex.iter().filter(|(_, client)| {
            let info = client.info.as_ref();
            info.is_some() && info.unwrap().device_type != DeviceType::Hub
        });

        let hub_connections_with_info = mutex.iter().filter(|(_, client)| {
            let info = client.info.as_ref();
            info.is_some() && info.unwrap().device_type == DeviceType::Hub
        });

        if hub_connections_with_info.clone().count() == 0 {
            info!("No hubs connected");
            continue;
        }

        let connections_message = device_connections
            .clone()
            .map(|(port, client)| {
                let info = client.info.as_ref().unwrap();

                let name = info.name.clone();
                let device_type_str = info.device_type.to_str();

                format!("[{},{},{}]", port, name, device_type_str)
            })
            .collect::<Vec<String>>()
            .join(",");
        let message = format!("[{}]\n", connections_message);

        info!("Sending message to hubs: {:?}", message);

        let message_bytes = message.as_bytes();

        // Iterate over the connections and send the message to each hub.
        for (port, client) in hub_connections_with_info.clone() {
            let info = client.info.as_ref().unwrap();
            if info.device_type == DeviceType::Hub {
                let mut stream_clone = client.stream.try_clone().unwrap();
                stream_clone.write(message_bytes).unwrap();
            }
        }
    }
}

fn main() {
    env::set_var("RUST_LOG", "info");
    env_logger::init();

    let args: Vec<String> = env::args().collect();
    if args.len() < 2 {
        panic!("Address not provided");
    }
    let address = &args[1];

    info!("Starting server on: {}", address);

    let server: Arc<Server> = Arc::new(Server::new());

    let server_clone = Arc::clone(&server);
    thread::spawn(move || {
        send_message_to_hubs(server_clone);
    });

    // TODO: get port from command line arguments or environment variables

    let listener_result = TcpListener::bind(address);
    let listener = match listener_result {
        Ok(listener) => listener,
        Err(e) => {
            error!("Failed to bind to address: {}", e);
            return;
        }
    };

    info!("Listening on: {}", address);
    for stream in listener.incoming() {
        match stream {
            Ok(stream) => {
                let address = stream.peer_addr().unwrap();
                let address_port = address.port();

                let server_clone = Arc::clone(&server);
                let client = Client::new(stream.try_clone().unwrap(), address_port);
                server.add_connection(address_port, client);

                info!("Connection established: {}", address_port);

                thread::spawn(move || {
                    handle_client(server_clone, address_port, stream);
                });
            }
            Err(e) => error!("Failed to establish connection: {}", e),
        }
    }
}

fn handle_client(server: Arc<Server>, port: u16, stream: TcpStream) {
    let mut data = [0 as u8; 50];

    while match stream.try_clone().unwrap().read(&mut data) {
        Ok(size) if size == 0 => {
            info!("Received empty data, terminating connection");
            false
        }
        Ok(size) => {
            info!("Received data: {:?}", &data[0..size]);
            let received_data = &data[0..size];

            let result = handle_message(&server, port, received_data);
            match result {
                Ok(_) => {
                    info!("Message handled successfully");
                    true
                }
                Err(_) => {
                    let error_message = result.err().unwrap();
                    error!("{}", error_message);
                    true
                }
            }
        }
        Err(_) => {
            let connections = server.connections.lock().unwrap();
            let client = connections
                .get(&port)
                .expect("Failed to get client connection");

            let address = client
                .stream
                .peer_addr()
                .expect("Failed to get peer address");
            error!("An error occurred, terminating connection with {}", address);

            client
                .stream
                .shutdown(Shutdown::Both)
                .expect("Failed to shutdown connection");

            false
        }
    } {}

    let mut connections = server.connections.lock().unwrap();
    let client = connections
        .get(&port)
        .expect("Failed to get client connection");
    let port = client
        .stream
        .peer_addr()
        .expect("Failed to get peer address")
        .port();
    connections.remove(&port);

    info!("Connection terminated: {}", port);
}

fn decode_message(server: &Arc<Server>, port: u16, data: &[u8]) -> Result<Message, &'static str> {
    let string_data = std::str::from_utf8(data).expect("Failed to parse data");

    let commas_separated: Vec<&str> = string_data.split(',').collect();
    if commas_separated.is_empty() {
        let error_message = "Invalid message format, missing comma separator";
        return Err(error_message);
    }

    if commas_separated.len() != 2 {
        let error_message = "Invalid message format, expected two comma separated values";
        return Err(error_message);
    }

    let binding = server.connections.lock().unwrap();
    let client = binding.get(&port).expect("Failed to get client connection");
    let client_info = client.info.as_ref();
    let has_registered = client_info.is_some();
    info!("Client info: {:?}", client_info);

    if !has_registered {
        let register_message = decode_register_message(commas_separated).unwrap();
        return Ok(Message::Register(register_message));
    }

    let port_str = commas_separated[0].to_string();
    let port_result = port_str.parse::<u16>();
    // TODO: use chaining
    if port_result.is_err() {
        let error_message = "Failed to parse port";
        return Err(error_message);
    }
    let client_port = port_result.unwrap();
    let command = commas_separated[1].to_string();

    Ok(Message::Command(CommandMessage {
        port: client_port,
        command,
    }))
}

fn decode_register_message(commas_separated: Vec<&str>) -> Result<RegisterMessage, &'static str> {
    let device_type_str = commas_separated[0].to_string();
    let device_type_opt = DeviceType::from_str(device_type_str.as_str());
    if device_type_opt.is_none() {
        let error_message = "Invalid device type";
        error!("{}", error_message);
        return Err(error_message);
    }

    let device_type = device_type_opt.unwrap();

    let name = commas_separated[1].to_string();
    let name = name.replace("\n", "");

    Ok(RegisterMessage { device_type, name })
}

fn handle_message(server: &Arc<Server>, port: u16, data: &[u8]) -> Result<(), &'static str> {
    let opt_message = decode_message(server, port, data);
    if opt_message.is_err() {
        let error_message = opt_message.err().unwrap();
        return Err(error_message);
    }

    let message = opt_message.unwrap();
    info!("Decoded message: {:?}", message);
    match message {
        Message::Register(register_message) => {
            return handle_register_message(server, port, register_message);
        }
        Message::Command(command_message) => {
            return handle_command_message(server, port, command_message);
        }
    }
}

fn handle_register_message(
    server: &Arc<Server>,
    port: u16,
    register_message: RegisterMessage,
) -> Result<(), &'static str> {
    info!("Registering device: {:?}", register_message);

    let mut binding = server.connections.lock().unwrap();
    let client = binding
        .get_mut(&port)
        .expect("Failed to get client connection");

    client.info = Some(ClientInfo {
        name: register_message.name.clone(),
        device_type: register_message.device_type,
    });

    let message = format!("Registered successfully");
    info!("{}", message);

    Ok(())
}

fn handle_command_message(
    server: &Arc<Server>,
    port: u16,
    command_message: CommandMessage,
) -> Result<(), &'static str> {
    info!("Handling command message: {:?}", command_message);

    let connections = server.connections.lock().unwrap();
    let client = connections
        .get(&port)
        .expect("Failed to get client connection");

    let client_info = client.info.as_ref().expect("Client info not found");
    if client_info.device_type != DeviceType::Hub {
        let error_message = "Only hubs can send commands";
        error!("{}", error_message);
        return Err(error_message);
    }

    let device_port = command_message.port;

    match connections.get(&device_port) {
        Some(Client {
            stream: cloned_stream,
            info,
            port,
        }) => {
            let command = command_message.command;
            let mut stream = cloned_stream.try_clone().expect("Failed to clone stream");
            let message = format!("Sending command {} to: {}", command, port);
            info!("{}", message);

            let command_with_new_line = format!("{}\n", command);
            stream
                .write(command_with_new_line.as_bytes())
                .expect("Failed to write to stream");
            return Ok(());
        }
        None => {
            let error_message = format!("Device not found: {}", port);
            return Err("Device not found");
        }
    }
}
