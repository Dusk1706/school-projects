import tensorflow as tf
import numpy as np
from pathlib import Path

class Modelo:
    def __init__(self, model_path="modelo.h5"):
        self.model_path = Path(model_path)
        if self.model_path.exists():
            print("Cargando modelo guardado...")
            self.modelo = tf.keras.models.load_model(self.model_path)
            self.mostrar_precision()
        else:
            print("Creando y entrenando un nuevo modelo...")
            self.modelo = self.crear_red_neuronal()
            self.entrenar_red_neuronal()
            self.guardar_modelo()

    def crear_red_neuronal(self):
        modelo = tf.keras.models.Sequential([
            tf.keras.layers.Conv2D(
                32, (3, 3), activation='relu', input_shape=(28, 28, 1)
            ),
            tf.keras.layers.MaxPooling2D((2, 2)),
            tf.keras.layers.Conv2D(64, (3, 3), activation='relu'),
            tf.keras.layers.MaxPooling2D((2, 2)),
            tf.keras.layers.Flatten(),
            tf.keras.layers.Dense(128, activation='relu'),
            tf.keras.layers.Dense(10, activation='softmax')
        ])
        
        modelo.compile(
            optimizer='adam', 
            loss='sparse_categorical_crossentropy', 
            metrics=['accuracy']
        )
        
        return modelo

    def entrenar_red_neuronal(self):
        mnist = tf.keras.datasets.mnist
        (x_train, y_train), _ = mnist.load_data()
        x_train = x_train / 255.0
        x_train = x_train.reshape(-1, 28, 28, 1)
        self.historial_entrenamiento = self.modelo.fit(
            x_train, y_train, validation_split=0.2, epochs=5, batch_size=32
        )

    def guardar_modelo(self):
        print(f"Guardando modelo en {self.model_path}...")
        self.modelo.save(self.model_path)

    def mostrar_precision(self):
        mnist = tf.keras.datasets.mnist
        (_, _), (x_test, y_test) = mnist.load_data()
        x_test = x_test / 255.0
        x_test = x_test.reshape(-1, 28, 28, 1)
        loss, accuracy = self.modelo.evaluate(x_test, y_test, verbose=0)
        print(f"Precisión del modelo cargado: {accuracy:.2%}")

    def predecir(self, input):
        prediction = self.modelo.predict(input)
        return np.argmax(prediction)
