from tkinter import ttk
from tkinter import *
from tkinter import messagebox

# Calcular media formula suma total entre N
def calcMedia(numeros):
    sum = 0
    for i in numeros:
        sum += i
    return sum / len(numeros)  

# Calcular varianza formula suma de las diferencias respecto a la media al cuadrado entre N
def calcVarianza(numeros,media):
    varianza=0.0
    for i in numeros:
        varianza += pow(i-media,2)
    return varianza/len(numeros)

def limpiar_frame(frame):
    for widgets in frame.winfo_children():
        widgets.pack_forget()

def comprobarEntry(dato):
    if not dato.isdecimal() or (int(dato) < 100 or int(dato) > 999):
        messagebox.showwarning("Advertencia", "Datos Invalidos")
    else:
        generarNumeros(int(dato))

# Método para generar Numeros Aleatorios
def generarNumeros(semilla:int):
    limpiar_frame(window)
    frameTabla = Frame(window, width=640, height=360)
    frameTabla.pack(fill="both", expand=True)
     #treeview
    style = ttk.Style()
    style.configure("Treeview", font=("Arial", 12))
    tabla= ttk.Treeview(frameTabla, columns=('n', 'x','y','w','z'), show='headings')
    vsb = ttk.Scrollbar(frameTabla, orient="vertical", command=tabla.yview)
    tabla.configure(yscrollcommand=vsb.set)
    vsb.pack(side='right', fill='y')
    tabla.heading('n',text="n")
    tabla.column('n',anchor=CENTER)
    tabla.heading('x',text="Semilla")
    tabla.column('x',anchor=CENTER)
    tabla.column('y',anchor=CENTER)
    tabla.heading('y',text="Semilla al cuadrado")
    tabla.heading('w',text="Numero Interno")
    tabla.column('w',anchor=CENTER)
    tabla.column('z',anchor=CENTER)
    tabla.heading('z',text="Siguiente numero")
    tabla.pack(side="top",fill=BOTH,expand=True)
    
    numeros = [semilla]
    indice=0
    num=semilla
    #tabla.insert(parent='',index=END,values=[0,semilla])
    while True:
        indice+=1
        sCuadrado = num ** 2
        nInterno = str(sCuadrado)
        nInterno = nInterno[1:len(nInterno)-1]
        nextNumero = nInterno[0:min(3, len(nInterno))]
        tabla.insert(parent='',index=END,values=[indice,num,sCuadrado,nInterno,nextNumero])
        num = int(nextNumero)
        if num < 100 or num == 0 or num in numeros:
            break
        numeros.append(num)
    
    media = round(calcMedia(numeros), 4)
    varianza = round(calcVarianza(numeros,media),4)
    labelNumeros = Label(frameTabla, text = "Numeros Generados: " + str(len(numeros)), font = ("Arial", 16))
    labelNumeros.pack()
    labelMedia = Label(frameTabla, text= "La media de los numeros es: "+ str(media), font=("Arial", 16))
    labelMedia.pack()
    labelVarianza = Label(frameTabla, text= "La varianza de los numeros es: "+ str(varianza), font=("Arial", 16))
    labelVarianza.pack()
           
    botonVolverMenu = Button(frameTabla, text="Volver Al Menu", command=lambda: [
                         menu()])
    botonVolverMenu.config(font=10, width=20, height=2)
    botonVolverMenu.pack(padx=20,pady=10,side=RIGHT)

def menu():
    limpiar_frame(window)
    frameMedios = Frame(window, width=640, height=360)
    frameMedios.config(background="cyan")
    frameMedios.pack(fill="both", expand=True)

    labelNumeros = Label(frameMedios, text="Numeros Aleatorios	")
    labelNumeros.config(bg="cyan", font=("Arial", 25))
    labelNumeros.pack()

    labelManualMed = Label(frameMedios, text="Ingrese Semilla de 3 Digitos maximo:")
    labelManualMed.config(bg="cyan", font=("Arial", 22))
    labelManualMed.pack(pady=10)

    entryManual = Entry(frameMedios, font=15, width=20)
    entryManual.pack()

    botonEnviar = Button(frameMedios, text="Enviar", command=lambda: 
            [comprobarEntry(entryManual.get()),])
    botonEnviar.config(font=10, width=20, height=2)
    botonEnviar.pack(pady=10)

# Ventana principal
window = Tk()
window.geometry("640x360")
# window.resizable(False, False)
window.config(bg="pink")
window.title("Semillas")

menu()
window.mainloop()