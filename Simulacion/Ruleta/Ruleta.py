from tkinter import ttk
from tkinter import *
from tkinter import messagebox
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg
import random

def limpiar_frame(frame):
    for widgets in frame.winfo_children():
        widgets.pack_forget()


def comprobarEntry(entry):
    if entry == "" or not entry.isdecimal():
        messagebox.showwarning("Advertencia", "Datos nulos o invalidos.")
    else:
        generarJuegos(entry)


def colorAleatorio(numRandom):

    if numRandom < 10/22:
        return 'Rojo'
    elif numRandom < 20/22:
        return 'Negro'
    else:
        return 'Verde'


def generarJuegos(n):
    limpiar_frame(window)
    inicializarTablas()
    for i in range(int(n)):
        generarApuestas(i+1)
    generarMenu()
    
def generarTablaJ1():
    limpiar_frame(window)
    frameJ1.pack(fill=BOTH,expand=True)
    frameVolver.pack()

def generarTablaJ2():
    limpiar_frame(window)
    frameJ2.pack(fill=BOTH,expand=True)
    frameVolver.pack()

def generarMenu():
    limpiar_frame(window)

    frameMenu = Frame(window, width=640, height=360)
    frameMenu.config(background="pink")
    frameMenu.pack(fill="both", expand=True)

    labelSel = Label(frameMenu, text="Seleccione Opcion", font=("Arial", 25))
    labelSel.config(bg="pink")
    labelSel.pack(padx=50, pady=10)

    botonJ1 = Button(frameMenu, text="Tabla Jugador 1", command=generarTablaJ1)
    botonJ1.config(font=10, width=25, height=2)
    botonJ1.pack(pady=10)

    botonJ2 = Button(frameMenu, text="Tabla Jugador 2", command=generarTablaJ2)
    botonJ2.config(font=10, width=25, height=2)
    botonJ2.pack(pady=10)

    botonSalir = Button(frameMenu, text="Salir",command=window.quit)
    botonSalir.config(font=10, width=25, height=2)
    botonSalir.pack(pady=10)

def inicializarTablas():
    global frameJ1
    frameJ1 = Frame(window, width=700, height=360)
    frameJ1.config(background='pink')
    global tablaJ1
    tablaJ1 = ttk.Treeview(frameJ1, columns=(
        1, 2, 3, 4, 5, 6, 7, 8), show='headings', height=20)
    tablaJ1.pack(side=LEFT, fill=BOTH, expand=True)

    global frameVolver
    frameVolver = Frame(window, width=640, height=360)
    botonVolver = Button(frameVolver, text="Volver",command=generarMenu)
    botonVolver.config(font=10, width=25, height=2)
    botonVolver.pack(pady=10)
    
    tablaJ1.column("#1", anchor="center", width=1)
    tablaJ1.column("#2", anchor="center", width=1)
    tablaJ1.column("#3", anchor="center", width=1)
    tablaJ1.column("#4", anchor="center", width=1)
    tablaJ1.column("#5", anchor="center", width=1)
    tablaJ1.column("#6", anchor="center", width=1)
    tablaJ1.column("#7", anchor="center", width=1)
    tablaJ1.column("#8", anchor="center", width=2)

    tablaJ1.heading(1, text="#It")
    tablaJ1.heading(2, text="$Antes de girar")
    tablaJ1.heading(3, text="Apuesta")
    tablaJ1.heading(4, text="#Aleatorio")
    tablaJ1.heading(5, text="Color")
    tablaJ1.heading(6, text="Gano?")
    tablaJ1.heading(7, text="$Despues de girar")
    tablaJ1.heading(8, text="Se llego a $1000?")

    scrollbar_tabla = ttk.Scrollbar(
        frameJ1, orient=VERTICAL, command=tablaJ1.yview)
    scrollbar_tabla.pack(side=RIGHT, fill=Y)
    tablaJ1.configure(yscrollcommand=scrollbar_tabla.set)

    global frameJ2
    frameJ2 = Frame(window, width=700, height=360)
    frameJ2.config(background='pink')
    global tablaJ2
    tablaJ2 = ttk.Treeview(frameJ2, columns=(
        1, 2, 3, 4, 5, 6, 7, 8), show='headings', height=20)
    tablaJ2.pack(side=LEFT, fill=BOTH, expand=True)

    tablaJ2.column("#1", anchor="center", width=1)
    tablaJ2.column("#2", anchor="center", width=1)
    tablaJ2.column("#3", anchor="center", width=1)
    tablaJ2.column("#4", anchor="center", width=1)
    tablaJ2.column("#5", anchor="center", width=1)
    tablaJ2.column("#6", anchor="center", width=1)
    tablaJ2.column("#7", anchor="center", width=1)
    tablaJ2.column("#8", anchor="center", width=2)

    tablaJ2.heading(1, text="#It")
    tablaJ2.heading(2, text="$Antes de girar")
    tablaJ2.heading(3, text="Apuesta")
    tablaJ2.heading(4, text="#Aleatorio")
    tablaJ2.heading(5, text="Color")
    tablaJ2.heading(6, text="Gano?")
    tablaJ2.heading(7, text="$Despues de girar")
    tablaJ2.heading(8, text="Se llego a $1000?")

    scrollbar_tabla = ttk.Scrollbar(
        frameJ2, orient=VERTICAL, command=tablaJ2.yview)
    scrollbar_tabla.pack(side=RIGHT, fill=Y)
    tablaJ2.configure(yscrollcommand=scrollbar_tabla.set)

def meterDatos(datosTablaJ1, datosTablaJ2):
    for dato in datosTablaJ1:
        tablaJ1.insert("", "end", values=dato)

    for dato in datosTablaJ2:
        tablaJ2.insert("", "end", values=dato)


def generarApuestas(i):
    limpiar_frame(window)
    frameApuesta = Frame(window, width=640, height=500)
    frameApuesta.pack(fill='both', expand=True)
    dineroj1 = 200
    dineroj2 = 200
    apuestaj2 = 1
    ganoj1 = '-'
    ganoj2 = '-'
    datosTablaJ1 = []
    datosTablaJ2 = []

    canvas = Canvas(frameApuesta, width=640, height=500)
    canvas.pack()
    simulacion = canvas.create_text(
        (320, 20), text="Simulacion "+str(i), font=('Arial', 20))
    cuadrado = canvas.create_rectangle(280, 50, 360, 130, fill="red")
    txtJ1 = canvas.create_text((90, 170), text="Jugador 1", font=('Arial', 20))
    txtJ2 = canvas.create_text(
        (420, 170), text="Jugador 2", font=('Arial', 20))
    txtDineroJ1 = canvas.create_text(
        (100, 220), text='Dinero: '+str(dineroj1), font=('Arial', 20))
    txtDineroJ2 = canvas.create_text(
        (420, 220), text='Dinero: '+str(dineroj2), font=('Arial', 20))
    txtApuestaJ1 = canvas.create_text(
        (100, 270), text='Apuesta: '+str(1), font=('Arial', 20))
    txtApuestaJ2 = canvas.create_text(
        (420, 270), text='Apuesta: '+str(apuestaj2), font=('Arial', 20))
    txtGanoJ1 = canvas.create_text(
        (80, 320), text='Gano: '+str(ganoj1), font=('Arial', 20))
    txtGanoJ2 = canvas.create_text(
        (420, 320), text='Gano: '+str(ganoj2), font=('Arial', 20))

    sigueJugandoJ1 = True
    sigueJugandoJ2 = True
    colorVerde = False
    it = 0
    while sigueJugandoJ1 or sigueJugandoJ2:
        it += 1
        numRandom = random.uniform(0, 1)
        color = colorAleatorio(numRandom)
        canvas.after(0)
        canvas.update()
        dineroA1 = dineroj1
        dineroA2 = dineroj2
        apuestaA = apuestaj2
        if color == 'Rojo':
            canvas.itemconfig(cuadrado, fill="red")
            if colorVerde:
                ganoj1 = '-'
                ganoj2 = '-'
                colorVerde = False
            else:
                ganoj1 = 'Si'
                ganoj2 = 'Si'
                if sigueJugandoJ1:
                    dineroj1 += 1
                    canvas.itemconfig(txtGanoJ1, text="Gano: Si")
                    canvas.itemconfig(
                        txtDineroJ1, text='Dinero: '+str(dineroj1))

                if sigueJugandoJ2:
                    dineroj2 += apuestaj2
                    apuestaj2 = 1
                    canvas.itemconfig(txtGanoJ2, text="Gano: Si")
                    canvas.itemconfig(
                        txtApuestaJ2, text='Apuesta: '+str(apuestaj2))
                    canvas.itemconfig(
                        txtDineroJ2, text='Dinero: '+str(dineroj2))

        elif color == 'Negro':
            canvas.itemconfig(cuadrado, fill="black")
            ganoj1 = 'No'
            ganoj2 = 'No'

            colorVerde = False

            if sigueJugandoJ1:
                dineroj1 -= 1
                canvas.itemconfig(txtGanoJ1, text="Gano: No")
                canvas.itemconfig(txtDineroJ1, text='Dinero: '+str(dineroj1))
            if sigueJugandoJ2:
                dineroj2 -= apuestaj2
                canvas.itemconfig(txtDineroJ2, text='Dinero: '+str(dineroj2))
                canvas.itemconfig(txtGanoJ2, text="Gano: No")
                if apuestaj2*2 > 1000:
                    apuestaj2 = 1
                else:
                    if apuestaj2*2 > dineroj2:
                        apuestaj2 = dineroj2
                    else:
                        apuestaj2 *= 2
                canvas.itemconfig(
                    txtApuestaJ2, text='Apuesta: '+str(apuestaj2))
        else:
            canvas.itemconfig(cuadrado, fill="green")
            colorVerde = True
            ganoj1 = '-'
            ganoj2 = '-'
            canvas.itemconfig(txtGanoJ1, text="Gano: -")
            canvas.itemconfig(txtGanoJ2, text="Gano: -")
        
        if sigueJugandoJ1:
            if dineroj1 >= 1000:
                datosTablaJ1.append(
                    [it, dineroA1, 1, numRandom, color, ganoj1, dineroj1, "Si"])
                sigueJugandoJ1 = False
                canvas.itemconfig(txtApuestaJ1, text='Apuesta: 0')
            else:
                datosTablaJ1.append(
                    [it, dineroA1, 1, numRandom, color, ganoj1, dineroj1, "No"])
                if dineroj1 <= 0:
                    sigueJugandoJ1 = False
                    canvas.itemconfig(txtApuestaJ1, text='Apuesta: 0')

        if sigueJugandoJ2:
            if dineroj2 >= 1000:
                datosTablaJ2.append(
                    [it, dineroA2, apuestaA, numRandom, color, ganoj2, dineroj2, "Si"])
                sigueJugandoJ2 = False
                canvas.itemconfig(txtApuestaJ2, text='Apuesta: 0')
            else:
                datosTablaJ2.append(
                    [it, dineroA2, apuestaA, numRandom, color, ganoj2, dineroj2, "No"])
                if dineroj2 <= 0:
                    sigueJugandoJ2 = False
                    canvas.itemconfig(txtApuestaJ2, text='Apuesta: 0')
    meterDatos(datosTablaJ1, datosTablaJ2)


def menu():
    frameActual = Frame(window, width=640, height=500)
    frameActual.config(background='Turquoise')
    frameActual.pack(fill='both', expand=True)

    label = Label(frameActual, text='Introduzca la cantidad de simulaciones:')
    label.config(bg='Turquoise', font=('Arial', 20))
    label.pack(padx=5, pady=5)

    entryManual = Entry(frameActual, font=15, width=15)
    entryManual.pack(padx=5, pady=5)

    botonEnviar = Button(
        frameActual, text='Enviar', command=lambda: [
            comprobarEntry(entryManual.get())])
    botonEnviar.config(font=10, width=20, height=2)
    botonEnviar.pack(pady=10)


window = Tk()
window.geometry('700x500')
window.resizable(True, True)
window.title('Juego de la Ruleta')
menu()
window.mainloop()
