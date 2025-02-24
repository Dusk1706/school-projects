import 'dart:io';
import 'dart:async';
import 'package:flutter/foundation.dart';

class SingletonTcpConnection {
  static final SingletonTcpConnection _singleton =
      SingletonTcpConnection._internal();

  late TcpConnection _tcpConnection;

  factory SingletonTcpConnection() {
    return _singleton;
  }

  SingletonTcpConnection._internal() {
    _tcpConnection = TcpConnection(host: '192.168.100.17', port: 8090);
    print("New instance of SingletonTcpConnection created!");
  }

  TcpConnection get tcpConnection => _tcpConnection;
}

enum DeviceType { light, fan, plug, airConditioner }

class Device {
  final String name;
  final DeviceType type;
  final int port;

  Device({required this.name, required this.type, required this.port});
}

enum TcpConnectionState { connecting, connected, disconnected }

class TcpConnection {
  final String host;
  final int port;

  List<Device> devices = [];
  TcpConnectionState state = TcpConnectionState.disconnected;

  late Socket _socket;
  late Function() _onData;

  TcpConnection({required this.host, required this.port});

  Future<void> connect(
    Function() onData,
  ) async {
    _onData = onData;
    _socket = await Socket.connect(
      host,
      port,
    );
    _socket.listen(
      dataHandler,
      onError: errorHandler,
      onDone: doneHandler,
      cancelOnError: false,
    );
    state = TcpConnectionState.connected;
    print("Connected to $host:$port!");
  }

  DeviceType getDeviceType(String type) {
    switch (type) {
      case 'light':
        return DeviceType.light;
      case 'plug':
        return DeviceType.plug;
      case 'airConditioner':
        return DeviceType.airConditioner;
      case 'fan':
        return DeviceType.fan;
      default:
        throw Exception('Unknown device type');
    }
  }

  void dataHandler(Uint8List data) {
    if (kDebugMode) {
      print('Data: ${data}');
    }
    // Convert data to string
    final stringData = String.fromCharCodes(data);
    print('String Data: $stringData'); // Added for debugging

    // Remove the enclosing brackets and split by commas
    final cleanedData = stringData.replaceAll(RegExp(r'[\[\]\n]'), '').trim();
    print("Cleaned Data: ${cleanedData}"); // Added for debugging
    if (cleanedData.isEmpty) {
      devices = [];
      return;
    }

    List<String> splitted = cleanedData.split(',');

    List<Device> newDevices = [];
    for (int i = 0; i < splitted.length; i += 3) {
      final port = int.parse(splitted[i]);
      final name = splitted[i + 1];
      final type = getDeviceType(splitted[i + 2]);

      final device = Device(
        port: port,
        name: name,
        type: type,
      );

      newDevices.add(device);
    }
    for (var device in devices) {
      print(
          'Device: ${device.name}, Type: ${device.type}, Port: ${device.port}');
    }
    devices = newDevices;

    _onData();
  }

  void errorHandler(error, StackTrace trace) {
    print('Error: $error');
    state = TcpConnectionState.disconnected;
  }

  void doneHandler() {
    print('Connection has been closed!');
    state = TcpConnectionState.disconnected;
  }

  void sendCommand(String command) {
    _socket.write(command);
  }

  void disconnect() {
    _socket.close();
  }

  int get localPort => _socket.port; // Este es el puerto del servidor
  int get remotePort => _socket.remotePort; // Este es el puerto del cliente
}
