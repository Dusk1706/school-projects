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

def comprobarEntry(*datos):
    for dato in datos:
        if dato == "" or not dato.isdecimal() or int(dato)<1:
            messagebox.showwarning("Advertencia", "Datos Invalidos")
            break
    else:
        generar(datos[0], datos[1], datos[2], datos[3], datos[4])

def generar(num_Sim, licen, cost_l, ing_l, ing_d):
    limpiar_frame(window)
    frame = Frame(window, width=1200, height=700)
    frame.config(background="cyan")
    frame.pack(fill="both", expand=True)

    botonVolverMenu = Button(frame, text="Volver Al Menu", command=menu)
    botonVolverMenu.config(font=10, width=20, height=2)
    botonVolverMenu.pack(padx=20,pady=10,side=RIGHT)

    datosTabla = []
    porcentajes = [0] * 5

    sumUtilidad = 0

    for i in range(1, int(num_Sim) + 1):
        numero = round(random.random(),4)
        licencia = ""
        if numero <= 0.3:
            licencia = "100"
            porcentajes[0]+=1
        elif numero <= 0.5:
            licencia = "150"
            porcentajes[1]+=1
        elif numero <= 0.8:
            licencia = "200"
            porcentajes[2]+=1
        elif numero <= 0.95:
            licencia = "250"
            porcentajes[3]+=1
        elif numero <= 1:
            licencia = "300"
            porcentajes[4]+=1
        if((int(licencia)) > int(licen)):
            licencia = licen
        devuelta = int(licen) - int(licencia)
        costo = int(licen) * int(cost_l)
        ingreso = int(licencia) * int(ing_l)
        ingreso_d = devuelta * int(ing_d)
        utilidad = ingreso + ingreso_d - costo
        sumUtilidad += utilidad
        datosTabla.append([i, numero, licencia, devuelta, costo
                           , ingreso, ingreso_d, utilidad])
    
    for i in range(len(porcentajes)):
        porcentajes[i] = (porcentajes[i]/(int)(num_Sim))*100

    ingre_dev = Label(frame, text=f"Utilidad media: {sumUtilidad/int(num_Sim)}",
                       font=("Ubuntu", 15))
    ingre_dev.config(bg="pink")
    ingre_dev.pack(padx=50, pady=10, side=TOP)

    #Calculo de la varianza de la utilidad
    varianza = 0
    for dato in datosTabla:
        varianza += (dato[7] - (sumUtilidad/int(num_Sim)))**2
    varianza = math.sqrt(varianza/int(num_Sim))

    Varianza_L = Label(frame, text=f"Varianza: {varianza}",
                       font=("Ubuntu", 15))
    Varianza_L.config(bg="pink")
    Varianza_L.pack(padx=50, pady=10, side=TOP)

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
    tabla.heading(1, text="N")
    tabla.heading(2, text="#aleagen")
    tabla.heading(3, text="L_Vendidas")
    tabla.heading(4, text="L_Devueltas")
    tabla.heading(5, text="Costo")
    tabla.heading(6, text="I x ventas")
    tabla.heading(7, text="I x devolucion")
    tabla.heading(8, text="Utilidad")
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

    frameInput=Frame(frameMenu)
    frameInput.config(background="pink")
    frameInput.pack(fill="both", expand=True)
    labelSim = Label(frameInput, text="¿Cuantas veces deseas realizar la simulacion?",
                       font=("Ubuntu", 15))
    labelSim.config(bg="pink")
    labelSim.pack(padx=50, pady=10)

    simulaciones = Entry(frameInput, font=15, width=20)
    simulaciones.pack()

    labelCom = Label(frameInput, text="¿Cuantas licencias deseas comprar?", 
                     font = ("Ubuntu", 15))
    labelCom.config(bg="pink")
    labelCom.pack(padx=50, pady=10)

    opciones = ["100", "150", "200", "250", "300"]
    opcion = ttk.Combobox(frameInput, values=opciones)
    opcion.set("100")
    opcion.state(["readonly"])
    opcion.pack()

    costo_lic = Label(frameInput, text="¿Costo por licencia?",
                       font=("Ubuntu", 15))
    costo_lic.config(bg="pink")
    costo_lic.pack(padx=50, pady=10)

    costL = Entry(frameInput, font=15, width=20)
    costL.pack()

    ingre_lic = Label(frameInput, text="¿Ingreso por licencia vendida?",
                       font=("Ubuntu", 15))
    ingre_lic.config(bg="pink")
    ingre_lic.pack(padx=50, pady=10)

    ingreL = Entry(frameInput, font=15, width=20)
    ingreL.pack()

    ingre_dev = Label(frameInput, text="¿Ingreso por devolucion?",
                       font=("Ubuntu", 15))
    ingre_dev.config(bg="pink")
    ingre_dev.pack(padx=50, pady=10)

    ingreD = Entry(frameInput, font=15, width=20)
    ingreD.pack()

    botonGenerar = Button(frameMenu, text="Generar", command=lambda: 
            [comprobarEntry(simulaciones.get(), opcion.get(),
                             costL.get(), ingreL.get(),
                               ingreD.get()),])
    botonGenerar.config(font=10, width=25, height=1)
    botonGenerar.pack(pady=10)

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