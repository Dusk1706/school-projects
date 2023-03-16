from tkinter import ttk
from tkinter import *
from tkinter import messagebox
import random

def limpiar_frame(frame):
    for widgets in frame.winfo_children():
        widgets.pack_forget()

def comprobarEntry(dato,metodo):
    if (dato == "" or not dato.isdecimal() or (metodo=="N" and len(dato)!=10) 
        or (metodo=="M" and len(dato)!=4)):
        messagebox.showwarning("Advertencia", "Datos Invalidos")
    elif metodo=="N":
        generarMedios(int(dato))
    else:
        generarNewman(int(dato))

# Método medios cuadrados
def generarMedios(semilla:int):
    limpiar_frame(window)
    frameTabla = Frame(window, width=640, height=360)
    frameTabla.pack(fill="both", expand=True)
     #treeview
    style = ttk.Style()
    style.configure("Treeview", font=("Arial", 12))
    tabla= ttk.Treeview(frameTabla, columns=('x','y'), show='headings')
    vsb = ttk.Scrollbar(frameTabla, orient="vertical", command=tabla.yview)
    tabla.configure(yscrollcommand=vsb.set)
    vsb.pack(side='right', fill='y')
    tabla.heading('x',text="Iteraciones")
    tabla.column('x',anchor=CENTER)
    tabla.column('y',anchor=CENTER)
    tabla.heading('y',text="Numeros")
    tabla.pack(side="top",fill=BOTH,expand=True)
    
    numeros = [semilla]
    indice=0
    num=semilla
    tabla.insert(parent='',index=END,values=[0,semilla])
    while True:
        indice+=1
        n = str(num ** 2)
        n = ("0" * (8 - len(n))) + n
        num = int(n[2: 6])
        if num == 0 or num in numeros:
            break
        tabla.insert(parent='',index=END,values=[indice,num])
        numeros.append(num)
        
    botonVolverMetodo = Button(frameTabla, text="Volver Al Metodo", command=lambda: [
                         limpiar_frame(window), medios()])
    botonVolverMetodo.config(font=10, width=20, height=2)
    botonVolverMetodo.pack(padx=20,pady=10,side=LEFT)    
           
    botonVolverMenu = Button(frameTabla, text="Volver Al Menu", command=lambda: [
                         limpiar_frame(window), frameMenu.pack()])
    botonVolverMenu.config(font=10, width=20, height=2)
    botonVolverMenu.pack(padx=20,pady=10,side=RIGHT)
       
#Menu metodos medios cuadrados
def medios():
    limpiar_frame(window)
    frameMedios = Frame(window, width=640, height=360)
    frameMedios.config(background="cyan")
    frameMedios.pack(fill="both", expand=True)

    labelNumeros = Label(frameMedios, text="Metodo Medios Cuadrados")
    labelNumeros.config(bg="cyan", font=("Arial", 25))
    labelNumeros.pack()

    labelManualMed = Label(frameMedios, text="Ingrese Semilla de 4 Digitos:")
    labelManualMed.config(bg="cyan", font=("Arial", 22))
    labelManualMed.pack(pady=10)

    entryManual = Entry(frameMedios, font=15, width=20)
    entryManual.pack()

    botonEnviar = Button(frameMedios, text="Enviar entrada manual", command=lambda: 
            [comprobarEntry(entryManual.get(),"M"),])
    botonEnviar.config(font=10, width=20, height=2)
    botonEnviar.pack(pady=10)

    botonRandom = Button(frameMedios, text="Números Random",
                         command=lambda:[generarMedios(random.randint(1000,10000)),])
    botonRandom.config(font=10, width=20, height=2)
    botonRandom.pack(pady=10)
    
    botonVolver = Button(frameMedios, text="Volver", command=lambda: [
                         limpiar_frame(window), frameMenu.pack()])
    botonVolver.config(font=10, width=20, height=2)
    botonVolver.pack(pady=10)    
    
def generarNewman(semilla:int):
    limpiar_frame(window)
    frameTabla = Frame(window, width=640, height=360)
    frameTabla.pack(fill="both", expand=True)
     #treeview
    style = ttk.Style()
    style.configure("Treeview", font=("Arial", 12))
    tabla= ttk.Treeview(frameTabla, columns=('x','y'), show='headings')
    vsb = ttk.Scrollbar(frameTabla, orient="vertical", command=tabla.yview)
    tabla.configure(yscrollcommand=vsb.set)
    vsb.pack(side='right', fill='y')
    tabla.heading('x',text="Iteraciones")
    tabla.column('x',anchor=CENTER)
    tabla.column('y',anchor=CENTER)
    tabla.heading('y',text="Numeros")
    tabla.pack(side="top",fill=BOTH,expand=True)
    
    numeros = [semilla]
    indice=0
    num=semilla
    tabla.insert(parent='',index=END,values=[0,semilla])
    while True:
        indice+=1
        n = str(num ** 2)
        if len(n)%2==0:
            n="0"+n
        l=len(n)
        num=int(n[l//2-2 : l//2+3])
        if num == 0 or num in numeros:
            break
        tabla.insert(parent='',index=END,values=[indice,num])
        numeros.append(num)
    
    botonVolverMetodo = Button(frameTabla, text="Volver Al Metodo", command=lambda: [
                         limpiar_frame(window), newman()])
    botonVolverMetodo.config(font=10, width=20, height=2)
    botonVolverMetodo.pack(padx=20,pady=10,side=LEFT)    
           
    botonVolverMenu = Button(frameTabla, text="Volver Al Menu", command=lambda: [
                         limpiar_frame(window), frameMenu.pack()])
    botonVolverMenu.config(font=10, width=20, height=2)
    botonVolverMenu.pack(padx=20,pady=10,side=RIGHT)    
        
def newman():
    limpiar_frame(window)
    frameNewman = Frame(window, width=640, height=360)
    frameNewman.config(background="OliveDrab1")
    frameNewman.pack(fill="both", expand=True)

    labelNumeros = Label(frameNewman, text="Metodo Newman")
    labelNumeros.config(bg="OliveDrab1", font=("Arial", 25))
    labelNumeros.pack()
    
    labelManualMed = Label(frameNewman, text="Ingrese Semilla de 10 Digitos:", font=("Arial", 22))
    labelManualMed.config(bg="OliveDrab1")
    labelManualMed.pack(padx=50, pady=10)

    entryManual = Entry(frameNewman, font=15, width=20)
    entryManual.pack()

    botonEnviar = Button(
        frameNewman, text="Enviar entrada manual", command=lambda: 
            [comprobarEntry(entryManual.get(),"N"),])
    botonEnviar.config(font=10, width=20, height=2)
    botonEnviar.pack(pady=10)
    botonRandom = Button(frameNewman, text="Números Random",
                         command=lambda:[generarNewman(random.randint(1000000000,10000000000)),])
    botonRandom.config(font=10, width=20, height=2)
    botonRandom.pack(pady=10)
    botonVolver = Button(frameNewman, text="Volver", command=lambda: [
                         limpiar_frame(window), frameMenu.pack()])
    botonVolver.config(font=10, width=20, height=2)
    botonVolver.pack(pady=10)


# Ventana principal
window = Tk()
window.geometry("640x360")
# window.resizable(False, False)
window.config(bg="pink")
window.title("Semillas")

# Crear frame actual
frameMenu = Frame(window, width=640, height=360)
frameMenu.config(background="pink")
frameMenu.pack(fill="both", expand=True)

labelSel = Label(frameMenu, text="Seleccione el método", font=("Arial", 25))
labelSel.config(bg="pink")
labelSel.pack(padx=50, pady=10)

botonMedios = Button(frameMenu, text="Método de Medios Cuadrados", command=medios)
botonMedios.config(font=10, width=25, height=2)
botonMedios.pack(pady=10)

botonNewman = Button(frameMenu, text="Método de Newman", command=newman)
botonNewman.config(font=10, width=25, height=2)
botonNewman.pack(pady=10)

botonSalir = Button(frameMenu, text="Salir",command=window.quit)
botonSalir.config(font=10, width=25, height=2)
botonSalir.pack(pady=10)

window.mainloop()