import random
from tkinter import ttk
from tkinter import *
from tkinter import messagebox
import numpy as np
from numpy import random as r
import matplotlib.pyplot as plt
import math as m
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg


# Método para limpiar el frame
def limpiar_frame(frame):
    for widgets in frame.winfo_children():
        widgets.pack_forget()


def calcularAciertos(data,color_filt,colors):
    ac = 0
    for line in data:
        dis = m.sqrt((m.pow((line[0]) - 1, 2)) + m.pow((line[1]) - 1, 2))
        if dis <= 1:
            ac += 1
            color_filt.append(colors[0])
        else:
            color_filt.append(colors[1])
    return ac

# Generar las coordenadas aleatorias
def generarNumeros(data):
    limpiar_frame(window)
    plt.clf()
    # Calcular aciertos
    colors = ["blue", "red"]
    color_filt = []
    frameTabla = Frame(window, width=640, height=360)
    frameTabla.pack(fill='both', expand=True)

    n = int(data)
    data = r.uniform(0, 2, (n, 2))
    a = calcularAciertos(data,color_filt,colors)
    plt.scatter(data[:, 0], data[:, 1], color=color_filt)

    #Graficar Circulo
    t = np.linspace(0, 2 * np.pi, 100)
    plt.plot(1 + np.cos(t), 1 + np.sin(t), color='black')

    plt.title("Lanzando Dardos")
    plt.xlabel("Eje X")
    plt.ylabel("Eje Y")
    
    fig = plt.gcf()
    canvas = FigureCanvasTkAgg(fig, master=frameTabla)
    canvas.draw()
    canvas.get_tk_widget().pack(fill=BOTH, expand=True)

    p = (a/n)*4
    label = Label(frameTabla, text=str(p), font=('Arial', 20, ))
    label.pack(padx=5, pady=5)

    botonVolver = Button(frameTabla, text="Volver", command=menu)
    botonVolver.config(font=10, width=20, height=2)
    botonVolver.pack(padx=5, pady=5)


# Verificación de la entrada manual
def comprobarEntry(entry):
    if entry == "" or not entry.isdecimal():
        messagebox.showwarning("Advertencia", "Datos nulos o invalidos.")
    elif int(entry) > 100000:
        messagebox.showwarning(
            "Advertencia", "Ingrese un número menor o igual a 100000 (Cien mil).")
    else:
        generarNumeros(entry)

def menu():
    limpiar_frame(window)
    frameActual = Frame(window, width=640, height=360)
    frameActual.config(background='light sea green')
    frameActual.pack(fill='both', expand=True)

    labelEntry = Label(frameActual, text='¿Cuántos dardos quiere lanzar?')
    labelEntry.config(bg='light sea green',font=('Arial', 20, ))
    labelEntry.pack(padx=5, pady=10)

    entryManual = Entry(frameActual, font='Arial 15', width=15)
    entryManual.pack(padx=5, pady=5)

    botonEnviar = Button(frameActual, text='Enviar entrada manual', command=lambda:[comprobarEntry(entryManual.get()),])
    botonEnviar.config(font=10, width=20, height=2)
    botonEnviar.pack(pady=10)


# Ventana principal
window = Tk()
window.geometry('640x640')
window.resizable(True, True)
window.title('Dardos')
menu()
window.mainloop()