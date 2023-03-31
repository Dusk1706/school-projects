from tkinter import ttk
from tkinter import *
from tkinter import messagebox
import numpy as np
import matplotlib.pyplot as plt
from numpy import random as r
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg

# Método para limpiar el frame
def limpiar_frame(frame):
    for widgets in frame.winfo_children():
        widgets.pack_forget()

# Generamos los números y las líneas

def generarNumeros(n):
    limpiar_frame(window)
    plt.clf()

    
    fig, graf = plt.subplots(2, figsize=(1, 1))
    fig.subplots_adjust(hspace=0.5)

    graf[0].set_title("Agujas de Buffon")

    frameTabla = Frame(window, width=640, height=360)
    frameTabla.pack(fill='both', expand=True)

    puntosIniciales = r.uniform(1, 4, (int(n), 2))
    angulos = r.uniform(0, 2*np.pi, int(n))
    lineas = []
    c = 0

    for line in puntosIniciales:
        x2 = line[0] + np.cos(angulos[c])
        y2 = line[1] + np.sin(angulos[c])
        lineas.append([line[0], x2, line[1], y2])
        c += 1

    inters = 0
    t = 0
    puntos = []  

    for line in lineas:
        x1 = line[0]
        x2 = line[1]
        
        #Verificamos si x1 es mayor que x2
        if x1 > x2:
            x1, x2 = x2, x1

        #Verificamos si la aguja corta la linea 1, 2, 3 y 4
        for i in range(1, 5):
            if x1 <= i and x2 >= i:
                inters += 1
                graf[0].plot([line[0], line[1]], [line[2], line[3]], color='blue')
                break
        else:
            graf[0].plot([line[0], line[1]], [line[2], line[3]], color='red')

        #Tabla
        t += 1
        pi = 0
        if inters != 0:
            pi = (t * 2) / inters
        
        puntos.append([t, pi])


    #Graficamos las lineas paralelas
    for i in range(1, 5):
        graf[0].plot([i, i], [0, 5], color='black')

    pi = (int(n) * 2) / inters
    graf[1].set_title("Pi = " + str(pi))
    graf[1].set_xlabel("Número de agujas")
    graf[1].set_ylabel("Valor de Pi")
    
    #Graficamos los puntos
    for i in puntos:
        graf[1].scatter(i[0], i[1], color='blue')

    graf[1].plot([0, int(n)], [np.pi, np.pi], color='red')
    
    fig2 = plt.gcf()
    canvas = FigureCanvasTkAgg(fig2, master=frameTabla)
    canvas.draw()
    canvas.get_tk_widget().pack(fill=BOTH, expand=True)

    
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
    frameActual = Frame(window, width=640, height=500)
    frameActual.config(background='Turquoise')
    frameActual.pack(fill='both', expand=True)

    label = Label(
        frameActual, text='Introduzca la cantidad de agujas lanzadas:', font=('Arial', 20))
    label.config(bg='Turquoise')
    label.pack(padx=5, pady=5)

    entryManual = Entry(frameActual, font=15, width=15)
    entryManual.pack(padx=5, pady=5)

    botonEnviar = Button(
        frameActual, text='Enviar entrada manual', command=lambda: [
            comprobarEntry(entryManual.get()),])
    botonEnviar.config(font=10, width=20, height=2)
    botonEnviar.pack(pady=10)


# Ventana principal
window = Tk()
window.geometry('640x500')
window.resizable(False, False)
window.title('Agujas de Buffon')
menu()
window.mainloop()