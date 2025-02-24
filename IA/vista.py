import cv2
import tkinter as tk
from PIL import Image, ImageTk
from modelo import Modelo
from procesador_frame import ProcesadorFrame


class Vista:
    def __init__(self):
        self.model = Modelo()
        self.processor = ProcesadorFrame()

        self.root = tk.Tk()
        self.root.title("Reconocimiento de Digitos")
        self.root.geometry("800x600")

        frame_main = tk.Frame(self.root)
        frame_main.pack(expand=True, fill=tk.BOTH, padx=20, pady=20)

        self.frame_camara = tk.Frame(frame_main, width=500, height=500)
        self.frame_camara.pack(side=tk.LEFT, expand=True, fill=tk.BOTH, padx=10)

        self.frame_prediccion = tk.Frame(frame_main, width=250, height=500)
        self.frame_prediccion.pack(side=tk.RIGHT, padx=10)

        self.video_label = tk.Label(self.frame_camara, width=500, height=500)
        self.video_label.pack(expand=True)

        self.prediction_label = tk.Label(self.frame_prediccion, text="Numero detectado:", font=("Arial", 16))
        self.prediction_label.pack(pady=10)

        self.label_digito = tk.Label(self.frame_prediccion, text="-", font=("Arial", 64))
        self.label_digito.pack(pady=10)

        self.label_procesado = tk.Label(self.frame_prediccion, width=200, height=200)
        self.label_procesado.pack(pady=10)

        self.cap = cv2.VideoCapture(0)
        self.cap.set(cv2.CAP_PROP_FRAME_WIDTH, 640)
        self.cap.set(cv2.CAP_PROP_FRAME_HEIGHT, 480)

        self.actualizar_frame()
        self.root.mainloop()

    def actualizar_frame(self):
        ret, frame = self.cap.read()
        if ret:
            cv2_image = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
            pil_image = Image.fromarray(cv2_image).resize((500, 500), Image.LANCZOS)
            photo = ImageTk.PhotoImage(image=pil_image)

            self.video_label.photo = photo
            self.video_label.configure(image=photo)

            frame_procesado, imagen_procesada = self.processor.preprocesar_frame(frame)
            if imagen_procesada:
                self.label_procesado.photo = imagen_procesada
                self.label_procesado.configure(image=imagen_procesada)

            if frame_procesado is not None:
                digit = self.model.predecir(frame_procesado)
                self.label_digito.config(text=str(digit))
            else:
                self.label_digito.config(text="-")

        self.root.after(30, self.actualizar_frame)
