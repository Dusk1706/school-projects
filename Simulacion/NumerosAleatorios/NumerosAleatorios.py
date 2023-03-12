import random
from tkinter import ttk
from tkinter import *
from tkinter import messagebox
 
# Calcular media formula suma total entre N
def calcMedia(numerosRandom):
    sum = 0
    for i in numerosRandom:
        sum += i
    return sum / len(numerosRandom)  

# Calcular varianza formula suma de las diferencias respecto a la media al cuadrado entre N
def calcVarianza(numerosRandom,media):
    varianza=0.0
    for i in numerosRandom:
        varianza += pow(i-media,2)
    return varianza/len(numerosRandom)

# Mostrar la tabla en el nuevo Frame
def mostrarTabla(numerosRandom):
    frameActual =Frame(window, width=640,height=360)
    frameActual.pack()
    
    labelTexto= Label(frameActual,text="Numeros Generados",font=("Arial",25))
    labelTexto.pack()
    
    tabla = Frame(frameActual, width=640,height=360)
    tabla.pack(fill=BOTH, expand=True, padx=10, pady=10)
    
    # Insercion de los numeros generados en la tabla
    listaNumeros = Listbox(tabla,width=70,justify=CENTER)
    for i in numerosRandom:
        listaNumeros.insert(END, i)

    # Crear la barra de desplazamiento
    scrollbar = ttk.Scrollbar(tabla, orient=VERTICAL, command=listaNumeros.yview)
    listaNumeros.config(yscrollcommand=scrollbar.set)
    scrollbar.pack(side=RIGHT, fill=Y)
    listaNumeros.pack(fill=BOTH, expand=True)
    
    # Mostrar el resultado de la media y la varianza
    media = round(calcMedia(numerosRandom), 4)
    varianza = round(calcVarianza(numerosRandom,media),4)
    labelMedia = Label(frameActual, text= "La media de los numeros es: "+ str(media), font=("Arial", 16))
    labelMedia.pack()
    labelVarianza = Label(frameActual, text= "La varianza de los numeros es: "+ str(varianza), font=("Arial", 16))
    labelVarianza.pack()

# Función para cambiar de frame
def cambiarFrame(numerosRandom):
    frameActual.pack_forget()
    mostrarTabla(numerosRandom)
    
# Genera una lista de N cantidad numeros random entre 0 y 10000
def generarNumeros(cantidad):
    numerosRandom=[]
    for i in range(cantidad):
        numerosRandom.append(random.randint(0, 10))
    return cambiarFrame(numerosRandom)

#Comprueba que si se presiona el boton manual haya datos validos en la entry
def comprobarEntry():
    dato = entryManual.get()
    if dato == "" or not dato.isdecimal():
        messagebox.showwarning("Advertencia", "Datos Nulos o Invalidos")
    else:
        generarNumeros(int(dato))

#Genera una cantidad aleatoria de numeros a generar y cambia de frame
def generarCantidad():
    generarNumeros(random.randint(10,200))

#Ventana principal
window=Tk()
window.geometry("640x360")
window.resizable(False,False)
window.title("Numeros Pseudoaleatorios")

# Crear frame actual
frameActual=Frame(window,width=640,height=360)
frameActual.config(background = "cyan")
frameActual.pack(fill="both",expand=True)

labelManual= Label(frameActual,text="Ingreso Manual:",font=("Arial",25))
labelManual.config(bg="cyan")
labelManual.pack(padx=50,pady=10)

entryManual = Entry(frameActual, font=15,width=20)
entryManual.pack()

botonEnviar = Button(frameActual, text = "Enviar entrada manual", command=comprobarEntry,)
botonEnviar.config(font=10,width=20,height=2)
botonEnviar.pack(pady=10)

botonRandom = Button(frameActual, text = "Números Random", command=generarCantidad)
botonRandom.config(font=10,width=20,height=2)
botonRandom.pack(pady=10)


window.mainloop()