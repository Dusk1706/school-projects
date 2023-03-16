from tkinter import ttk
from tkinter import *
from tkinter import messagebox
import random

def limpiar_frame(frame):
    for widgets in frame.winfo_children():
        widgets.pack_forget()
        
def esPrimo(numero):
    if numero<2:
        return False
    for i in range(2, int(numero**0.5)+1):
        if numero % i == 0:
            return False
    return True

# X Semilla
def metodo(a,x,c,m):
    
    limpiar_frame(window)
    frameMetodo = Frame(window, width=1000, height=400,bg="pink")
    frameMetodo.pack(fill="both", expand=True)

    labelTitulo = Label(frameMetodo, text="Resultados")
    labelTitulo.config(bg="pink", font=("Arial", 25))
    labelTitulo.pack(pady=10)
    
    datoA = Label(frameMetodo, text="A ="+str(a))
    datoA.config(bg="pink", font=("Arial", 20))
    datoA.place(x=150,y=100)
    
    datoX = Label(frameMetodo, text="X ="+str(x))
    datoX.config(bg="pink", font=("Arial", 20))
    datoX.place(x=300,y=100)
    
    datoC = Label(frameMetodo, text="C ="+str(c))
    datoC.config(bg="pink", font=("Arial", 20))
    datoC.place(x=450,y=100)
    
    datoM = Label(frameMetodo, text="M ="+str(m))
    datoM.config(bg="pink", font=("Arial", 20))
    datoM.place(x=600,y=100)
    
    #treeview
    style = ttk.Style()
    style.configure("Treeview", font=("Arial", 12))
    tabla= ttk.Treeview(frameMetodo, columns=('A','B','C','D','E'), show='headings')
    vsb = ttk.Scrollbar(frameMetodo, orient="vertical", command=tabla.yview)
    tabla.configure(yscrollcommand=vsb.set)
    vsb.pack(side='right', fill='y')
    
    tabla.heading('A',text="Iteraciones")
    tabla.column('A',anchor=CENTER)
    
    tabla.heading('B',text="X")
    tabla.column('B',anchor=CENTER)
    
    tabla.heading('C',text="aXn + c")
    tabla.column('C',anchor=CENTER)
    
    tabla.heading('D',text="(aXn + c)/m")
    tabla.column('D',anchor=CENTER)
    
    tabla.heading('E',text="Xn + 1")
    tabla.column('E',anchor=CENTER)
    
    tabla.pack(side="bottom",fill=BOTH)
    
    numeros = []
    indice=0
    num=x
    axn_c=0
    axn_c_m=0.0
    while True:
        if num in numeros:
            break
        axn_c=a*num+c
        axn_c_m=round(axn_c/m,4)
        residuo=axn_c%m
        tabla.insert(parent='',index=END,values=[indice,num,axn_c,axn_c_m,residuo])
        numeros.append(num)
        indice+=1
        num=residuo
    botonVolverMenu = Button(frameMetodo, text="Volver Al Menu", command=lambda: [
                         limpiar_frame(window),menu()])
    botonVolverMenu.config(font=10, width=20, height=2)
    botonVolverMenu.pack(padx=20,pady=10,side=RIGHT)   
    
def calcularM(d):
    for i in range((10**d)-1,1,-1):
        if esPrimo(i):
            return i
    
def comprobarInput(a,x,c,d):
    txt=""
    if not a.isdecimal() or not x.isdecimal() or not c.isdecimal or not d.isdecimal():
        messagebox.showwarning("Advertencia", "Alguno de los datos no son enteros positivos")
        return
    a=int(a)
    x=int(x)
    c=int(c)
    d=int(d)
    if (a % 2 == 0 or a % 3 == 0 or a % 5 == 0) or a<=0:
        txt+="El multiplicador debe ser impar y no debe ser multiplo de 3 y 5\n"
    if x<=0:
        txt+="La semilla debe ser un entero positivo mayor que 0\n"
    if not c%200==21 or c<=0:
        txt+="El incremento debe cumplir la regla c MOD 200 = 21\n"
    if d<=0 or d>5:
        txt+="D debe ser un entero positivo y menor a 6\n"
    if(txt!=""):
        messagebox.showwarning("Advertencia", txt)
    else:
        m=calcularM(d)
        metodo(a,x,c,calcularM(d))
        
def generarRandom(entry,limite):
    entry.delete(0, END)
    entry.insert(0,random.randint(1,limite))

def menu():
    limpiar_frame(window)
    a=0
    x=0
    c=0
    m=0
    frameMenu = Frame(window, bg="pink")
    frameMenu.pack(fill="both", expand=True)

    labelTitulo = Label(frameMenu, text="Metodo de las Congruencias")
    labelTitulo.config(bg="pink", font=("Arial", 25))
    labelTitulo.pack(pady=10)

    labelImagen = Label(frameMenu, text="SIXOLOGY",font=("Arial", 40),bg="pink")
    labelImagen.place(x=650,y=130)

    labelTonno = Label(frameMenu, text="Toños Team",font=("Arial", 40),bg="pink")
    labelTonno.place(x=640,y=200)

    labelA = Label(frameMenu, text="Valor de (A)")
    labelA.config(bg="pink", font=("Arial", 14))
    labelA.place(x=100,y=100)
    entryA = Entry(frameMenu, font=15, width=10)
    entryA.place(x=300,y=100)
    
    labelX = Label(frameMenu, text="Valor de (X)")
    labelX.config(bg="pink", font=("Arial", 14))
    labelX.place(x=100,y=150)
    
    entryX = Entry(frameMenu, font=15, width=10)
    entryX.place(x=300,y=150)
    
    botonRandomX = Button(frameMenu, text="Random",command=lambda:[generarRandom(entryX,10000)])
    botonRandomX.place(x=420,y=148)
    botonRandomX.config(font=10, width=7, height=1)
    
    labelC = Label(frameMenu, text="Valor de (C)")
    labelC.config(bg="pink", font=("Arial", 14))
    labelC.place(x=100,y=200)
    entryC = Entry(frameMenu, font=15, width=10)
    entryC.place(x=300,y=200)
    
    labelM = Label(frameMenu, text="Valor de (M)")
    labelM.config(bg="pink", font=("Arial", 14))
    labelM.place(x=100,y=250)
    labelD = Label(frameMenu, text="Regla (D)")
    labelD.config(bg="pink", font=("Arial", 10))
    labelD.place(x=230,y=255)
    entryD = Entry(frameMenu, font=15, width=10)
    entryD.place(x=300,y=255)
    
    botonRandomM = Button(frameMenu, text="Random",command=lambda:[generarRandom(entryD,5)])
    botonRandomM.place(x=420,y=250)
    botonRandomM.config(font=10, width=7, height=1)
    
    botonEnviar = Button(frameMenu, text="Enviar",command=lambda: 
        [comprobarInput(entryA.get(),entryX.get(),entryC.get(),entryD.get()),])
    botonEnviar.place(x=150,y=300)
    botonEnviar.config(font=10, width=20, height=2)

    botonSalir = Button(frameMenu, text="Salir",command=window.quit)
    botonSalir.place(x=390,y=300)
    botonSalir.config(font=10, width=10, height=2)
    

# Ventana principal
window = Tk()
window.geometry("1000x400")
# window.resizable(False, False)
window.config(bg="pink")
window.title("Semillas")

menu()

window.mainloop()