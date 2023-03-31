from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg
from matplotlib.figure import Figure
from tkinter import ttk
from tkinter import *
from tkinter import messagebox
import random

def limpiar_frame(frame):
    for widgets in frame.winfo_children():
        widgets.pack_forget()

def comprobarEntry(dato,metodo):
    if dato == "" or not dato.isdecimal() or int(dato)<1:
        messagebox.showwarning("Advertencia", "Datos Invalidos")
    elif metodo=="M":
        generarMoneda(int(dato))
    else:
        generarDado(int(dato))

def generarMoneda(lanzamientos):
    limpiar_frame(window)
    frameMoneda = Frame(window, width=1200, height=400)
    frameMoneda.config(background="cyan")
    frameMoneda.pack(fill="both", expand=True)
    datosTabla = []
    porcentajes = [0,0]
    etiquetas = ['Sello', 'Aguila']
    
    for i in range(1, lanzamientos + 1):
        numero = round(random.random(),4)
        cara = ""
        if numero <= 0.5:
            cara = "Sello"
            porcentajes[0]+=1
        elif numero <= 1:
            cara = "Aguila"
            porcentajes[1]+=1
        datosTabla.append([i, numero, cara])
        
    porcentajes[0]=(porcentajes[0]/lanzamientos)*100
    porcentajes[1]=(porcentajes[1]/lanzamientos)*100
    
    frameGrafica = Frame(frameMoneda)
    frameGrafica.pack(side=LEFT)

    # Creamos la gráfica circular
    fig = Figure(figsize=(5, 5), dpi=100)
    ax = fig.add_subplot(111)
    ax.pie(porcentajes, labels=etiquetas, autopct='%1.1f%%')
    ax.set_title('Porcentajes de eventos')
    
    canvas = FigureCanvasTkAgg(fig, master=frameGrafica)
    canvas.draw()
    canvas.get_tk_widget().pack()

    # Tabla de datos
    frameTabla = Frame(frameMoneda)
    frameTabla.pack(fill=BOTH, expand=True)

    tabla = ttk.Treeview(frameTabla, columns=(1,2,3), show='headings', height=3)
    tabla.pack(side=LEFT, fill=BOTH, expand=True)
    
    tabla.column("#1",anchor="center")
    tabla.column("#2",anchor="center")
    tabla.column("#3",anchor="center")
    tabla.heading(1, text="Iteraciones")
    tabla.heading(2, text="Número aleatorio")
    tabla.heading(3, text="Evento")
    for dato in datosTabla:
        tabla.insert("", "end", values=dato)

    # Creamos un scrollbar para la tabla
    scrollbar_tabla = ttk.Scrollbar(frameTabla, orient=VERTICAL, command=tabla.yview)
    scrollbar_tabla.pack(side=RIGHT, fill=Y)
    tabla.configure(yscrollcommand=scrollbar_tabla.set)
    
        
    botonVolverMetodo = Button(frameMoneda, text="Volver Al Metodo", command=moneda)
    botonVolverMetodo.config(font=10, width=20, height=2)
    botonVolverMetodo.pack(padx=20,pady=10,side=LEFT)    
           
    botonVolverMenu = Button(frameMoneda, text="Volver Al Menu", command=menu)
    botonVolverMenu.config(font=10, width=20, height=2)
    botonVolverMenu.pack(padx=20,pady=10,side=RIGHT)
       
def moneda():
    limpiar_frame(window)
    frameMoneda = Frame(window, width=640, height=360)
    frameMoneda.config(background="cyan")
    frameMoneda.pack(fill="both", expand=True)

    labelMoneda = Label(frameMoneda, text="Moneda")
    labelMoneda.config(bg="cyan", font=("Arial", 25))
    labelMoneda.pack()

    labelEventos = Label(frameMoneda, text="Ingrese la cantidad de eventos:")
    labelEventos.config(bg="cyan", font=("Arial", 22))
    labelEventos.pack(pady=10)

    entryManual = Entry(frameMoneda, font=15, width=20)
    entryManual.pack()

    botonEnviar = Button(frameMoneda, text="Generar", command=lambda: 
            [comprobarEntry(entryManual.get(),"M"),])
    botonEnviar.config(font=10, width=20, height=2)
    botonEnviar.pack(pady=10)

    botonVolver = Button(frameMoneda, text="Volver", command=menu)
    botonVolver.config(font=10, width=20, height=2)
    botonVolver.pack(pady=10)    
    
def generarDado(lanzamientos):
    limpiar_frame(window)
    frameDado = Frame(window, width=640, height=360)
    frameDado.pack(fill="both", expand=True)
    frameDado.config(background="OliveDrab1")
    datosTabla = []
    porcentajes = [0,0,0,0,0,0]
    caras = ['Uno', 'Dos', 'Tres', 'Cuatro', 'Cinco', 'Seis']
    
    for i in range(1, lanzamientos + 1):
        numero = round(random.random(),4)
        cara = ""
        if numero <= 1 / 6:
            porcentajes[0] += 1
            cara=caras[0]
        elif numero <= 2 / 6:
            porcentajes[1] += 1
            cara=caras[1]
        elif numero <= 3 / 6:
            porcentajes[2] += 1
            cara=caras[2]
        elif numero <= 4 / 6:
            porcentajes[3] += 1
            cara=caras[3]
        elif numero <= 5 / 6:
            porcentajes[4] += 1
            cara=caras[4]
        elif numero <= 6 / 6:
            porcentajes[5] += 1
            cara=caras[5]
        datosTabla.append([i, numero, cara])
    for i in porcentajes:
        i=(i/lanzamientos)*100
    
    frameGrafica = Frame(frameDado)
    frameGrafica.pack(side=LEFT)

    # Creamos la gráfica circular
    fig = Figure(figsize=(5, 5), dpi=100)
    ax = fig.add_subplot(111)
    ax.pie(porcentajes, labels=caras, autopct='%1.1f%%')
    ax.set_title('Porcentajes de eventos')
    # Creamos el lienzo de la figura y lo colocamos en el frame
    canvas = FigureCanvasTkAgg(fig, master=frameGrafica)
    canvas.draw()
    canvas.get_tk_widget().pack()

    # Creamos un frame para la tabla
    frameTabla = Frame(frameDado)
    frameTabla.pack(fill=BOTH, expand=True)

    # Creamos la tabla con los datos
    tabla = ttk.Treeview(frameTabla, columns=(1,2,3), show='headings', height=3)
    tabla.pack(side=LEFT, fill=BOTH, expand=True)
    
    tabla.column("#1",anchor="center")
    tabla.column("#2",anchor="center")
    tabla.column("#3",anchor="center")
    tabla.heading(1, text="Iteraciones")
    tabla.heading(2, text="Número aleatorio")
    tabla.heading(3, text="Evento")
    for dato in datosTabla:
        tabla.insert("", "end", values=dato)

    # Creamos un scrollbar para la tabla
    scrollbar_tabla = ttk.Scrollbar(frameTabla, orient=VERTICAL, command=tabla.yview)
    scrollbar_tabla.pack(side=RIGHT, fill=Y)
    tabla.configure(yscrollcommand=scrollbar_tabla.set)
    
    botonVolverMetodo = Button(frameDado, text="Volver Al Metodo", command=dado)
    botonVolverMetodo.config(font=10, width=20, height=2)
    botonVolverMetodo.pack(padx=20,pady=10,side=LEFT)    
           
    botonVolverMenu = Button(frameDado, text="Volver Al Menu", command=menu)
    botonVolverMenu.config(font=10, width=20, height=2)
    botonVolverMenu.pack(padx=20,pady=10,side=RIGHT)    
        
def dado():
    limpiar_frame(window)
    frameDado = Frame(window, width=1000, height=500)
    frameDado.config(background="OliveDrab1")
    frameDado.pack(fill="both", expand=True)

    labelNumeros = Label(frameDado, text="Dado")
    labelNumeros.config(bg="OliveDrab1", font=("Arial", 25))
    labelNumeros.pack()
    
    labelEntry = Label(frameDado, text="Ingrese la cantidad de eventos:", font=("Arial", 22))
    labelEntry.config(bg="OliveDrab1")
    labelEntry.pack(padx=50, pady=10)

    entryManual = Entry(frameDado, font=15, width=20)
    entryManual.pack()

    botonEnviar = Button(frameDado, text="Generar", command=lambda: 
            [comprobarEntry(entryManual.get(),"D"),])
    botonEnviar.config(font=10, width=20, height=2)
    botonEnviar.pack(pady=10)
   
    botonVolver = Button(frameDado, text="Volver", command=menu)
    botonVolver.config(font=10, width=20, height=2)
    botonVolver.pack(pady=10)

def menu():
    limpiar_frame(window)
    frameMenu = Frame(window, width=640, height=360)
    frameMenu.config(background="pink")
    frameMenu.pack(fill="both", expand=False)

    labelSel = Label(frameMenu, text="¿Que quieres lanzar?", font=("Arial", 25))
    labelSel.config(bg="pink")
    labelSel.pack(padx=50, pady=10)

    botonMoneda = Button(frameMenu, text="Moneda", command=moneda)
    botonMoneda.config(font=10, width=25, height=2)
    botonMoneda.pack(pady=10)

    botonDado = Button(frameMenu, text="Dado", command=dado)
    botonDado.config(font=10, width=25, height=2)
    botonDado.pack(pady=10)

    botonSalir = Button(frameMenu, text="Salir",command=window.quit)
    botonSalir.config(font=10, width=25, height=2)
    botonSalir.pack(pady=10)

# Ventana principal
window = Tk()
window.geometry("1000x400")
window.resizable(True, True)
window.config(bg="pink")
window.title("Semillas")
menu()
window.mainloop()