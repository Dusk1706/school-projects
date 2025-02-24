#!/bin/bash

# Verificar si se proporcionaron suficientes argumentos
if [ "$#" -ne 2 ]; then
    echo "Uso: $0 <dirección IP> <número de ejecuciones>"
    exit 1
fi

# Dirección IP proporcionada como primer argumento
IP=$1

# Número de ejecuciones proporcionado como segundo argumento
NUM_EXECUTIONS=$2

# Lista de dispositivos
devices=("light" "airConditioner" "plug" "fan")

# Verificar si el segundo argumento es un número entero positivo
if ! [[ "$NUM_EXECUTIONS" =~ ^[0-9]+$ ]] ; then
    echo "El número de ejecuciones debe ser un entero positivo"
    exit 1
fi

# Obtener la ruta completa del script de Python
SCRIPT_DIR=$(dirname "$(readlink -f "$0")")
PYTHON_SCRIPT="$SCRIPT_DIR/connection.py"

# Bucle para ejecutar el script de Python n veces
for ((i = 0; i < NUM_EXECUTIONS; i++))
do
    # Selección aleatoria de un dispositivo
    RANDOM_DEVICE=${devices[ $RANDOM % ${#devices[@]} ]}
    echo "Ejecución $((i+1)) de $NUM_EXECUTIONS: $RANDOM_DEVICE"
    # Ejecutar el script de Python con la IP y el dispositivo seleccionado en segundo plano
    python "$PYTHON_SCRIPT" "$IP" "$RANDOM_DEVICE" &> "log_$((i+1)).txt" &
done

echo "Todas las instancias del script de Python se han iniciado en segundo plano."
