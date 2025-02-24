import socket
import time
import sys
import random
import logging

from Light import Light
from fan import Fan
from plug import Plug
from air_conditioner import AirConditioner

logging.basicConfig(format="%(asctime)s %(message)s")
name = "DEBUG"
logger = logging.getLogger(name)
logger.setLevel(logging.INFO)

def connect_to_server():
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    
    ip = sys.argv[1]
    server_address = (ip, 8080)
    logger.info('Conectando a {} puerto {}'.format(*server_address))
    tiempo = 1
    while True:
        try:
            sock.connect(server_address)
            logger.info('Conexion establecida')
            break
        except (socket.error, ConnectionResetError) as e:
            logger.warning('Error al conectar al servidor: {}'.format(e))
            if tiempo > 256:
                sys.exit(1)
            logger.info('Reintentando en {} segundos'.format(tiempo))
            time.sleep(tiempo)
            tiempo = tiempo << 1
    
    return sock


def get_device_object(tipo):
    if tipo == "light":
        return Light()
    elif tipo == "plug":
        return Plug()
    elif tipo == "fan":
        return Fan()
    elif tipo == "airConditioner":
        return AirConditioner()
    else:
        print("Tipo de dispositivo no válido")
        sys.exit(1)


def valid_arguments():
    if len(sys.argv) != 3:
        print("Uso: python connection.py <ip> <tipo>")
        sys.exit(1)


def reconocimiento(sock):
    recon = tipo + ',' + tipo + str(random.randint(1, 100))
    sock.sendall(recon.encode('utf-8'))


tipo = sys.argv[2]
dispositivo = get_device_object(tipo)
try:
    sock = connect_to_server()
    reconocimiento(sock)
    while True:
        data = sock.recv(50)
        if data:
            txtData = data.decode('utf-8')
            logger.info('Recibido: {}'.format(txtData))
            command = txtData.split('=')[0]
            value = txtData.split('=')[1]
            dispositivo.command(command, value)
        else:
            logger.info('Sin datos del servidor')
            
            time.sleep(1)
finally:
    logger.info('Cerrando conexion')
    sock.close()
    