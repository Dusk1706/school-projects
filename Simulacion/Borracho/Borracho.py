import random
from tkinter import *
from tkinter import ttk
from tkinter import messagebox
import matplotlib.pyplot as plt

def limpiar_frame(frame):
    for widgets in frame.winfo_children():
        widgets.pack_forget()

def comprobarEntry(entry):
    if entry == "" or not entry.isdecimal():
        messagebox.showwarning("Advertencia", "Datos nulos o invalidos.")
    elif int(entry) > 1000:
        messagebox.showwarning(
            "Advertencia", "Ingrese un numero menor o igual a 1000 (mil).")
    else:
        montecarlo(int(entry))

def coords(x,y):
    return "("+str(x)+","+str(y)+")"

def validar(x,y):
    if(abs(x)+abs(y)==2):
       return "Si"
    return "No" 

def montecarlo(n):
    limpiar_frame(window)
    window.withdraw()
    frameMontecarlo=Frame(window)
    frameMontecarlo.config(background='light sea green')
    frameMontecarlo.pack(fill='both', expand=True)
    
    labelTitulo=Label(frameMontecarlo,text="Resultados Simulacion",font=("Ubuntu",15))
    labelTitulo.config(bg='light sea green',font=('Arial', 20, ))
    labelTitulo.pack(padx=5, pady=10)
    
    campos=[]
    frameTabla = Frame(frameMontecarlo)
    frameTabla.pack(fill=BOTH, expand=True)

    tabla = ttk.Treeview(frameTabla, columns=(1,2,3,4,5), show='headings')
    tabla.pack(side=LEFT,fill=BOTH, expand=True)
    
    tabla.column("#1",anchor="center")
    tabla.column("#2",anchor="center")
    tabla.column("#3",anchor="center")
    tabla.column("#4",anchor="center")
    tabla.column("#5",anchor="center")
    tabla.heading(1, text="N")
    tabla.heading(2, text="Cuadras Recorridas")
    tabla.heading(3, text="#Aleatorio Generado")
    tabla.heading(4, text="Localizacion")
    tabla.heading(5, text="Exito")
    # Creamos un scrollbar para la tabla
    scrollbar_tabla = ttk.Scrollbar(frameTabla, orient=VERTICAL, command=tabla.yview)
    scrollbar_tabla.pack(side=RIGHT, fill=Y)
    tabla.configure(yscrollcommand=scrollbar_tabla.set)   
     
    for i in range(1,n+1):
        xPoints = [0]
        yPoints = [0]
        plt.plot([-10, 10], [0, 0], color='black')
        plt.plot([0, 0], [-10, 10], color='black')
        for j in range(1, 11):
            plt.pause(.2)
            numero = round(random.random(), 4)
            if numero <= 0.25:
                xPoints.append(xPoints[j-1])
                yPoints.append(yPoints[j-1]+1)
            elif numero <= 0.5:
                xPoints.append(xPoints[j-1])
                yPoints.append(yPoints[j-1]-1)
            elif numero <= 0.75:
                xPoints.append(xPoints[j-1]+1)
                yPoints.append(yPoints[j-1])
            elif numero <= 1:
                xPoints.append(xPoints[j-1]-1)
                yPoints.append(yPoints[j-1])
            plt.plot([xPoints[j-1], xPoints[j]],
                     [yPoints[j-1], yPoints[j]], color='cyan')
            x=xPoints[j]
            y=yPoints[j]
            if j==1:
                campos.append([i,j,numero,coords(x,y),""])
            elif j==10:
                campos.append(["",j,numero,coords(x,y),validar(x,y)])
            else:
                campos.append(["",j,numero,coords(x,y),""])
        plt.plot(xPoints[10], yPoints[10], marker="o")
        plt.pause(1)
        plt.close()
        plt.show()
    for dato in campos:
        tabla.insert("", "end", values=dato)
    window.deiconify()
    botonVolverMenu = Button(frameMontecarlo, text="Volver Al Menu", command=menu)
    botonVolverMenu.config(font=10, width=20, height=1)
    botonVolverMenu.pack(padx=10,pady=10)



def menu():
    limpiar_frame(window)
    frameActual = Frame(window, width=1000, height=1000)
    frameActual.config(background='pink')
    frameActual.pack(fill='both', expand=True)

    labelTitle = Label(frameActual, text='Borracho', font=('Arial', 25))
    labelTitle.config(bg='Pink')
    labelTitle.pack(padx=5, pady=5)

    labelEntry = Label(frameActual, text='Ingrese la cantidad de pruebas que quiere realizar:')
    labelEntry.config(bg='Pink', font=('Arial', 15))
    labelEntry.pack(padx=5, pady=5)

    entry = Entry(frameActual, font=15, width=15)
    entry.pack(padx=5, pady=5)

    botonEnviar = Button(frameActual, text='Iniciar Programa')
    botonEnviar.config(font=10, width=20, height=2, command=lambda: [
                       comprobarEntry(entry.get())])
    botonEnviar.pack(pady=10)

    botonSalir = Button(frameActual, text='Salir', command=window.quit)
    botonSalir.config(font=10, width=20, height=2)
    botonSalir.pack(pady=10)

window = Tk()
window.geometry('1100x500')
window.resizable(True, True)
window.title('Borracho')
menu()
window.mainloop()