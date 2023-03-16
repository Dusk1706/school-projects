import math
import matplotlib.pyplot as plt
import numpy as np
from tkinter import ttk
from tkinter import *
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg

# Función para cambiar de frame
def cambiar_frame():
    frame_actual.pack_forget()
    nuevoFrame.pack()
    tabulacion()
    
def calcularVelocidad(m, c, t):
    g = 9.81
    return g * m / c * (1 - math.e ** (-(c / m) * t))

def generarValores(tabla):
    x, y = [], []
    t = 0
    velocidadActual = math.inf
    m=float(mEntry.get())
    c=float(cEntry.get())
    while True:
        velocidadAnterior = velocidadActual
        velocidadActual = calcularVelocidad(m, c, t)
        if velocidadActual == velocidadAnterior and velocidadActual != 0:
            break
        x.append(t)
        y.append(velocidadActual)
        tabla.insert(parent='',index=t,values=[t,velocidadActual])
        t+=1

    x1 = np.arange(0, t, 0.1)
    y1 = calcularVelocidad(m, c, x1)
    plt.plot(x1, y1)
    plt.title("Caida libre")
    plt.xlabel("Tiempo")
    plt.ylabel("Velocidad")
    fig=plt.gcf()
    canvas = FigureCanvasTkAgg(fig, master=window)
    canvas.draw()
    canvas.get_tk_widget().pack(fill=BOTH,expand=True)
    
def tabulacion():
    #treeview
    style = ttk.Style()
    style.configure("Treeview", font=("Arial", 12))
    tabla= ttk.Treeview(nuevoFrame, columns=('x','y'), show='headings')

    vsb = ttk.Scrollbar(nuevoFrame, orient="vertical", command=tabla.yview)
    #vsb.place(x=30+200+2, y=95, height=200+20)
    tabla.configure(yscrollcommand=vsb.set)
    vsb.pack(side='right', fill='y')
    tabla.heading('x',text="Tiempo")
    tabla.column('x',anchor=CENTER)

    tabla.column('y',anchor=CENTER)
    tabla.heading('y',text="Velocidad")
    tabla.pack(side="top",fill=BOTH,expand=True)
    generarValores(tabla)
    
# Crear ventana principal
window = Tk()
window.title("Simulacion de paracaidismo")

# Crear frame actual
frame_actual=Frame(window)
tituloSimular= Label(frame_actual,text="Ingresa los datos a simular",font=("Arial",25))
tituloSimular.pack()

labelM = Label(frame_actual,text="Peso:",font=40,width=30).pack(padx=10, pady=10)
mEntry = Entry(frame_actual)
mEntry.pack()

labelC = Label(frame_actual,text="Coeficiente de resistencia:",font=40,width=30).pack(padx=10, pady=10)
cEntry = Entry(frame_actual)
cEntry.pack()

botonCambiar = Button(frame_actual, text="Enviar", command=cambiar_frame)
botonCambiar.pack(padx=10, pady=10)
frame_actual.pack(side=TOP, padx=10, pady=10)

# Crear nuevo frame
nuevoFrame = Frame(window, width=650, height=650)

window.mainloop()