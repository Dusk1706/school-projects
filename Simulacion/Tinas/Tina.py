import math
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg
from matplotlib.figure import Figure
from tkinter import ttk
from tkinter import *
from tkinter import messagebox
import random

def limpiar_frame(frame):
    for widgets in frame.winfo_children():
        widgets.pack_forget()

def comprobarEntry(dato):
    if dato == "" or not dato.isdecimal() or int(dato)<1:
        messagebox.showwarning("Advertencia", "Datos Invalidos")
    else:
        simular(int(dato))

def simular(years):

    datosTabla = []
    AquilerAnual = 0

    for i in range(1, years + 1):
        sumAlquiler = 0
        pesoSimulado = 0
        for j in range(1, 261):
            pesoAcumulado = 0
            for k in range(1, 6):
                numR = random.random()
                if numR < .5:
                    pesoSimulado = (190 + math.sqrt(800*numR))
                else:
                    pesoSimulado = (230 - math.sqrt(800*(1-numR)))

                pesoAcumulado += pesoSimulado
                
                me = "NO"

                if pesoAcumulado > 1000:
                    me = "SI"
                    sumAlquiler += 200

                datosTabla.append([i, j, k, numR, pesoSimulado, pesoAcumulado, me, sumAlquiler])
        AquilerAnual += sumAlquiler

    limpiar_frame(window)
    frame = Frame(window, width=1200, height=700)
    frame.config(background="cyan")
    frame.pack(fill="both", expand=True)

    ingre_dev = Label(frame, text=f"Alquiler Promedio Anual: {AquilerAnual/years}",
                       font=("Ubuntu", 15))
    ingre_dev.config(bg="cyan")
    ingre_dev.pack(padx=50, pady=10, side=TOP)

    # Tabla de datos
    frameTabla = Frame(frame)
    frameTabla.pack(fill=BOTH, expand=True)

    tabla = ttk.Treeview(frameTabla, columns=(1,2,3,4,5,6,7,8), show='headings', height=20)
    tabla.pack(side=LEFT, fill=BOTH, expand=True)
    
    tabla.column("#1",anchor="center", width=50)
    tabla.column("#2",anchor="center", width=100)
    tabla.column("#3",anchor="center", width=100)
    tabla.column("#4",anchor="center", width=100)
    tabla.column("#5",anchor="center", width=100)
    tabla.column("#6",anchor="center", width=100)
    tabla.column("#7",anchor="center", width=150)
    tabla.column("#8",anchor="center", width=100)

    tabla.heading(1, text="Año")
    tabla.heading(2, text="Dia")
    tabla.heading(3, text="Tina")
    tabla.heading(4, text="Rand")
    tabla.heading(5, text="Peso Simulado")
    tabla.heading(6, text="Peso Sim. Acumulado")
    tabla.heading(7, text="Excede?")
    tabla.heading(8, text="Alquiler Acumulado")

    for dato in datosTabla:
        tabla.insert("", "end", values=dato)

    # Creamos un scrollbar para la tabla
    scrollbar_tabla = ttk.Scrollbar(frameTabla, orient=VERTICAL, command=tabla.yview)
    scrollbar_tabla.pack(side=RIGHT, fill=Y)
    tabla.configure(yscrollcommand=scrollbar_tabla.set)  



def menu():
    limpiar_frame(window)
    frameMenu = Frame(window, width=640, height=360)
    frameMenu.config(background="gray")
    frameMenu.pack(fill="both", expand=True)

    labelSim = Label(frameMenu, text="¿Cuantos años deseas simular?",
                       font=("Ubuntu", 15))
    labelSim.config(bg="gray")
    labelSim.pack(padx=50, pady=10)

    simulaciones = Entry(frameMenu, font=15, width=20)
    simulaciones.pack()

    botonGenerar = Button(frameMenu, text="Simular", command=lambda: 
            [comprobarEntry(simulaciones.get()),])
    botonGenerar.config(font=10, width=25, height=2)
    botonGenerar.pack(pady=10)

    botonSalir = Button(frameMenu, text="Salir",command=window.quit)
    botonSalir.config(font=10, width=25, height=2)
    botonSalir.pack(pady=10)

# Ventana principal
window = Tk()
window.geometry("1200x400")
window.resizable(False, False)
window.config(bg="cyan")
window.title("Semillas")
menu()
window.mainloop()
