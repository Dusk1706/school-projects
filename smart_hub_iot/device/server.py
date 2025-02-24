import socket
import sys
from time import sleep


def start_server():
    # Create a TCP/IP socket
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    # Bind the socket to the port
    server_address = ('localhost', 8080)
    print('starting up on {} port {}'.format(*server_address))
    sock.bind(server_address)

    # Listen for incoming connections
    sock.listen(1)
    
    return sock


def wait_for_connection():
    print('Esperando una conexion')
    connection, client_address = sock.accept()
    
    print('Conexion establecida al cliente: ', client_address)
    mensaje = b'light,LuzUno'
    try:
        connection.sendall(mensaje)
    except:
        print('Error al enviar el reconocimiento')
        connection.close()
    
    return connection
        

sock = start_server()
connection = wait_for_connection()

while True:
    sleep(5)
    mensaje = b'active=false'
    try:
        connection.sendall(mensaje)
    except:
        print('Error al enviar el mensaje')
        connection.close()
        break 
    