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
        generar(int(dato))

def generar(cantidadFlores):
    limpiar_frame(window)
    frame = Frame(window, width=1200, height=700)
    frame.config(background="cyan")
    frame.pack(fill="both", expand=True)

    botonVolverMenu = Button(frame, text="Volver Al Menu", command=menu)
    botonVolverMenu.config(font=10, width=20, height=2)
    botonVolverMenu.pack(padx=20,pady=10,side=RIGHT)

    datosTabla = []

    sumUtilidad = 0

    for i in range(1, 366):
        numero = round(random.random(),4)
        demanda = ""
        if numero <= 0.07:
            demanda = 6
        elif numero <= 0.22:
            demanda = 12
        elif numero <= 0.32:
            demanda = 24
        elif numero <= 0.57:
            demanda = 36
        elif numero <= 0.77:
            demanda = 48
        elif numero <= 0.82:
            demanda = 60
        elif numero <= 0.9:
            demanda = 100
        elif numero <= 0.96:
            demanda = 200
        elif numero <= 1:
            demanda = 500
        vendidas=0
        noVendiadas=0
        if demanda>=cantidadFlores:
            vendidas=demanda
            noVendiadas=0
        else:
            vendidas=demanda
            noVendiadas=cantidadFlores-demanda
                        
        costo=100*cantidadFlores
        ventas=160*vendidas
        ventaSiguiente=50*noVendiadas
        utilidad=ventas+ventaSiguiente-costo
        sumUtilidad+=utilidad
        datosTabla.append([i, numero, demanda, cantidadFlores, vendidas
                           , noVendiadas, costo, ventas,ventaSiguiente,utilidad])
    
    ingre_dev = Label(frame, text=f"Utilidad media: {sumUtilidad/365}",
                       font=("Ubuntu", 15))
    ingre_dev.config(bg="pink")
    ingre_dev.pack(padx=50, pady=10, side=TOP)

    # Tabla de datos
    frameTabla = Frame(frame)
    frameTabla.pack(fill=BOTH, expand=True)

    tabla = ttk.Treeview(frameTabla, columns=(1,2,3,4,5,6,7,8,9,10), show='headings', height=20)
    tabla.pack(side=LEFT, fill=BOTH, expand=True)
    
    tabla.column("#1",anchor="center", width=50)
    tabla.column("#2",anchor="center", width=100)
    tabla.column("#3",anchor="center", width=100)
    tabla.column("#4",anchor="center", width=100)
    tabla.column("#5",anchor="center", width=100)
    tabla.column("#6",anchor="center", width=100)
    tabla.column("#7",anchor="center", width=150)
    tabla.column("#8",anchor="center", width=100)
    tabla.column("#9",anchor="center", width=100)
    tabla.column("#10",anchor="center", width=100)
    
    tabla.heading(1, text="Dia")
    tabla.heading(2, text="#aleagen")
    tabla.heading(3, text="Demanda por dia")
    tabla.heading(4, text="X Flores(compra)")
    tabla.heading(5, text="Vendidas")
    tabla.heading(6, text="No Vendidas")
    tabla.heading(7, text="Costo")
    tabla.heading(8, text="Ing.Venta")
    tabla.heading(9, text="Ing.Venta Dia Siguiente")
    tabla.heading(10, text="Utilidad")
    for dato in datosTabla:
        tabla.insert("", "end", values=dato)

    # Creamos un scrollbar para la tabla
    scrollbar_tabla = ttk.Scrollbar(frameTabla, orient=VERTICAL, command=tabla.yview)
    scrollbar_tabla.pack(side=RIGHT, fill=Y)
    tabla.configure(yscrollcommand=scrollbar_tabla.set)    

def menu():
    limpiar_frame(window)
    frameMenu = Frame(window, width=640, height=360)
    frameMenu.config(background="pink")
    frameMenu.pack(fill="both", expand=True)
    
    labelSim = Label(frameMenu, text="¿Cuantas flores desea comprar?",
                       font=("Ubuntu", 15))
    labelSim.config(bg="pink")
    labelSim.pack(padx=10, pady=10)

    entry = Entry(frameMenu, font=15, width=20)
    entry.pack(pady=10)

    botonGenerar = Button(frameMenu, text="Generar", command=lambda: 
            [comprobarEntry(entry.get())])
    botonGenerar.config(font=10, width=25, height=1)
    botonGenerar.pack()

    botonSalir = Button(frameMenu, text="Salir",command=window.quit)
    botonSalir.config(font=10, width=25, height=2)
    botonSalir.pack(pady=10)

# Ventana principal
window = Tk()
window.geometry("1200x500")
window.resizable(False, False)
window.config(bg="pink")
window.title("Semillas")
menu()
window.mainloop()