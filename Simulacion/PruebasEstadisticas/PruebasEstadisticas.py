import random as r
from tkinter import *
from tkinter import messagebox, ttk
import math
import matplotlib.pyplot as plt
import numpy as np
from scipy.stats import chi2
import matplotlib.pyplot as plt
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg
from collections import OrderedDict
from scipy.stats import ksone
# aleatorios = []
aleatorios=[0.31207, 0.46019, 0.36324, 0.41145, 0.03358, 0.41943, 0.84863, 0.88017, 0.49005, 0.89294, 0.70785, 0.53752, 0.50422, 0.28328, 0.8955, 0.11573, 0.11205, 0.04953, 0.10583, 0.15737, 0.68543, 0.09019, 0.64459, 0.52415, 0.24608, 0.97212, 0.28743, 0.87061, 0.67355, 0.37459, 0.16314, 0.05638, 0.32141, 0.76657, 0.42797, 0.51675, 0.60022, 0.70464, 0.20674, 0.9302, 0.99252, 0.79536, 0.59397, 0.83455, 0.65608, 0.53558, 0.08617, 0.38113, 0.69448, 0.78549]
def limpiar_frame(frame):
    for widgets in frame.winfo_children():
        widgets.pack_forget()

def comprobarEntry(entry):
    if entry == "" or not entry.isdecimal():
        messagebox.showwarning("Advertencia", "Datos nulos o invalidos.")
    elif int(entry) < 34 or int(entry) > 100:
        messagebox.showwarning("Advertencia",
                               "Entrada incorrecta, el valor debe estar entre 34 y 100")
    else:
        generarNumerosAleatorios(int(entry))
        
def generarNumerosAleatorios(entry):
    aleatorios.clear()
    creacionFrameAleatorios()
    
    for x in range(entry):
        num=round(r.uniform(0,1),5)
        aleatorios.append(num)
        num=aleatorios[x]
        tablaAleatorios.insert("", index=END, values=[x+1,num])
    menuMetodos()
    
        
def creacionFrameAleatorios():
    global frameAleatorios
    frameAleatorios = Frame(window, width=700, height=600)
    frameAleatorios.config(background='pink')
    global frameTabla
    frameTabla = Frame(frameAleatorios, width=700, height=600)
    
    global tablaAleatorios 
    tablaAleatorios =ttk.Treeview(frameTabla, columns=(1, 2), show='headings')
    tablaAleatorios.column("#1", anchor="center")
    tablaAleatorios.column("#2", anchor="center")

    tablaAleatorios.heading(1, text="#")
    tablaAleatorios.heading(2, text="Número Aleatorio")

    global scrollTabla 
    scrollTabla= ttk.Scrollbar(frameTabla, orient="vertical", command=tablaAleatorios.yview)
    tablaAleatorios.configure(yscrollcommand=scrollTabla.set)
    
    global botonVolver 
    botonVolver= Button(frameAleatorios, text='Volver al Menú', command=menuMetodos)
    botonVolver.config(font=10, width=20, height=2)

def mostrarAleatorios():
    limpiar_frame(window)
    frameAleatorios.pack(fill='both', expand=True)
    frameTabla.pack(side=TOP,fill='both', expand=True)
    tablaAleatorios.pack(side=LEFT,fill=BOTH,expand=True)
    scrollTabla.pack(side=RIGHT, fill=Y)
    botonVolver.pack(side="bottom", pady=10)

#Inicio Prueba chi2
def Mchi2(error):

    global Error_Chi2
    Error_Chi2 = error

    N = len(aleatorios)
    print(N)
    L = math.ceil(math.sqrt(N))
    K = L + 1
    E = N / K
    frecuencias = [0] * K

    for i in range(N):
        for j in range(1, K + 1):
            if aleatorios[i] <= j * (1 / K):
                frecuencias[j - 1] += 1
                break

    intervalos = [0] * K
    for i in range(1, K + 1):
        intervalos[i - 1] = f"{((i) / K):.5f}"

    creacionFrameChi2(frecuencias, intervalos, E)

    global DatosChi2
    DatosChi2 = []

    global sum_chi2
    sum_chi2 = 0

    for i in range(K):
        O = frecuencias[i]
        O_E = O - E
        O_E2 = O_E ** 2
        O_E2_E = O_E2 / E
        sum_chi2 += O_E2_E
        DatosChi2.append([f"{((i + 1) / K):.5f}", O, f"{E:.5f}", f"{O_E:.5f}", f"{O_E2_E:.5f}"])

    DatosChi2.append(["Totales", N, N, "", f"{sum_chi2:.5f}"])

    for x in range(K + 1):
        tablaChi2.insert("", index=END, values=DatosChi2[x])

    menuChi2()

def creacionFrameChi2(frecuencias, intervalos, Esperado):
    global frameChi2
    frameChi2 = Frame(window, width=700, height=500)
    frameChi2.config(background='pink')
    
    global frameTablaChi2
    frameTablaChi2 = Frame(frameChi2, width=700, height=500)
    frameTablaChi2.config(background='pink')
    
    global tablaChi2
    tablaChi2 =ttk.Treeview(frameTablaChi2, columns=(1, 2, 3, 4, 5), show='headings')
    tablaChi2.column("#1", anchor="center", minwidth=0, width=100)
    tablaChi2.column("#2", anchor="center", minwidth=0, width=100)
    tablaChi2.column("#3", anchor="center", minwidth=0, width=100)
    tablaChi2.column("#4", anchor="center", minwidth=0, width=100)
    tablaChi2.column("#5", anchor="center", minwidth=0, width=100)       
    tablaChi2.heading(1, text="i")
    tablaChi2.heading(2, text="O")
    tablaChi2.heading(3, text="E")
    tablaChi2.heading(4, text="O - E")
    tablaChi2.heading(5, text="((O - E)^2)/E")

    global scrollTablaChi2
    scrollTablaChi2= ttk.Scrollbar(frameTablaChi2, orient="vertical", command=tablaChi2.yview)
    tablaAleatorios.configure(yscrollcommand=scrollTablaChi2.set)

    fig, ax = plt.subplots()
    ax.bar(intervalos, frecuencias)
    ax.set_ylabel('Frecuencia')
    ax.set_xlabel('Intervalos')
    ax.set_title('Grafica de Barras Chi-Cuadrado')
    ax.axhline(y=Esperado, color='red', linestyle='--')
    ax.tick_params(axis='x', labelsize=8)
    global canvas
    canvas = FigureCanvasTkAgg(fig, master=frameChi2)
    canvas.draw()

    global botonVolverChi2
    botonVolverChi2= Button(frameChi2, text='Volver al Menú', command=menuChi2)
    botonVolverChi2.config(font=10, width=20, height=2)

def menuChi2():
    limpiar_frame(window)
    frameChi2 = Frame(window, width=700, height=600)
    frameChi2.config(background='pink')
    frameChi2.pack(fill='both', expand=True)
    
    labelOpcionChi2 = Label(frameChi2, text='Seleccione una opcion:')
    labelOpcionChi2.config(bg='Pink',font=('Arial', 15))
    labelOpcionChi2.pack(padx=5, pady=5)

    botonOpc1 = Button(frameChi2, text='Tabla Chi-Cuadrado', command=botonOpc1Chi2)
    botonOpc1.config(font=10, width=18, height=1)
    botonOpc1.pack(pady=10)
    
    botonOpc2 = Button(frameChi2, text='Grafica Chi-Cuadrado', command=botonOpc2Chi2)
    botonOpc2.config(font=10, width=18, height=1)
    botonOpc2.pack(pady=10)
    
    botonVolver= Button(frameChi2, text='Volver al Menú', command=menuMetodos)
    botonVolver.config(font=10, width=20, height=2)
    botonVolver.pack(side="bottom", pady=10)

def botonOpc1Chi2():
    limpiar_frame(window)
    limpiar_frame(frameChi2)
    frameChi2.pack(fill='both', expand=True)
    frameTablaChi2.pack(fill='both', expand=True, side=TOP)
    tablaChi2.pack(side=LEFT,fill='both',expand=True)
    scrollTablaChi2.pack(side=RIGHT, fill=Y)
    botonVolverChi2.pack(side="bottom", pady=10)

    N = len(aleatorios)
    Err5=chi2.ppf(0.95, math.ceil(math.sqrt(N)))
    Err10=chi2.ppf(0.90, math.ceil(math.sqrt(N)))

    txxt=f"{Err5:.5f}"
    if Error_Chi2==.1:
        txxt=f"{Err10:.5f}"

    label=Label(frameChi2, text='Sumatoria chi: '+ f"{sum_chi2:.5f}"+" <= "+txxt)
    label.config(font=('Arial', 15),background='pink')
    label.pack(fill='both', expand=True)

    txt="no estan uniformemente distribuidos" 

    if (Error_Chi2==.05 and sum_chi2<=Err5) or (Error_Chi2==0.1 and sum_chi2<=Err10):
        txt="estan uniformemente distribuidos"
    label2=Label(frameChi2, text='Los numeros: '+txt)
    label2.config(font=('Arial', 15),background='pink')
    label2.pack(fill='both', expand=True)

def botonOpc2Chi2():
    limpiar_frame(window)
    limpiar_frame(frameChi2)
    frameChi2.pack(fill='both', expand=True)
    botonVolverChi2.pack(side="bottom", pady=10)
    canvas.get_tk_widget().pack(fill='x', expand=True)

def comprobarChi2Error(error):
    if error == "":
        messagebox.showwarning("Advertencia", "Datos nulos.")
    else:
        if error == "5%":
            error=0.05
        else:
            error=0.1
        Mchi2(error)

def frameChiError():
    limpiar_frame(window)
    framErrorSeries = Frame(window, width=700, height=500)
    framErrorSeries.config(background='pink')
    framErrorSeries.pack(fill='both', expand=True)
    
    labelError = Label(framErrorSeries, text='Seleccione el porcentaje de fallo')
    labelError.config(bg='Pink',font=('Arial', 15))
    labelError.pack(padx=5, pady=5)
    combo = ttk.Combobox(framErrorSeries, values=["5%", "10%"],state="readonly")
    combo.pack()

    botonEnviar = Button(framErrorSeries, text='Enviar', command=lambda: comprobarChi2Error(combo.get()))
    botonEnviar.config(font=10, width=20, height=2)
    botonEnviar.pack(pady=10)
    
    botonVolver= Button(framErrorSeries, text='Volver al Menú', command=menuMetodos)
    botonVolver.config(font=10, width=20, height=2)
    botonVolver.pack(side="bottom", pady=10)    
#Fin Prueba chi2

#Inicio Prueba Kolmogorov
def kolmogorov(error):

    global ErrorKg
    ErrorKg = error

    limpiar_frame(window)
    aleatoriosKg = aleatorios.copy()
    aleatoriosKg.sort()

    datos = []
    D = 0
    global Xi
    global Yi
    Xi = []
    Yi = []
    for i in range(len(aleatoriosKg)):
        D1 = ((1 + i) / len(aleatoriosKg))
        D2 = abs(D1 - aleatoriosKg[i])
        D = max(D, D2)
        datos.append([[i + 1], [f"{aleatoriosKg[i]:.5f}"], [f"{D1:.5f}"], [f"{D2:.5f}"]])
        Xi.append(i + 1)
        Yi.append(D1)
    
    creacionFrameK(datos.copy())

    global Dkg
    Dkg = D
    menuKg()

def menuKg():
    limpiar_frame(window)
    frameKg = Frame(window, width=700, height=600)
    frameKg.config(background='pink')
    frameKg.pack(fill='both', expand=True)
    
    labelOpcionKg = Label(frameKg, text='Seleccione una opcion:')
    labelOpcionKg.config(bg='Pink',font=('Arial', 15))
    labelOpcionKg.pack(padx=5, pady=5)

    botonOpc1 = Button(frameKg, text='Tabla Kolmogorov', command=botonOpc1Kg)
    botonOpc1.config(font=10, width=18, height=1)
    botonOpc1.pack(pady=10)
    
    botonOpc2 = Button(frameKg, text='Grafica Kolmogorov', command=botonOpc2Kg)
    botonOpc2.config(font=10, width=18, height=1)
    botonOpc2.pack(pady=10)
    
    botonVolver= Button(frameKg, text='Volver al Menú', command=menuMetodos)
    botonVolver.config(font=10, width=20, height=2)
    botonVolver.pack(side="bottom", pady=10)

def botonOpc1Kg():
    limpiar_frame(window)
    limpiar_frame(frameKg)
    frameKg.pack(fill='both', expand=True)
    frameTablaKg.pack(fill='both', expand=True, side=TOP)
    tablaKg.pack(side=LEFT,fill='both',expand=True)
    scrollTablaKg.pack(side=RIGHT, fill=Y)
    botonVolverKg.pack(side="bottom", pady=10)

    N = len(aleatorios)
    Err5=ksone.ppf(0.95, N)
    Err10=ksone.ppf(0.90, N)

    txxt=f"{Err5:.5f}"
    if ErrorKg==.1:
        txxt=f"{Err10:.5f}"

    label=Label(frameKg, text='Di mayor: '+ f"{Dkg:.5f}"+" <= "+txxt)
    label.config(font=('Arial', 15),background='pink')
    label.pack(fill='both', expand=True)

    txt="no estan uniformemente distribuidos" 

    if (ErrorKg==.05 and Dkg<=Err5) or (ErrorKg==0.1 and Dkg<=Err10):
        txt="estan uniformemente distribuidos"
    label2=Label(frameKg, text='Los numeros: '+txt)
    label2.config(font=('Arial', 15),background='pink')
    label2.pack(fill='both', expand=True)

def botonOpc2Kg():
    limpiar_frame(window)
    limpiar_frame(frameKg)
    frameKg.pack(fill='both', expand=True)
    botonVolverKg.pack(side="bottom", pady=10)
    canvas.get_tk_widget().pack(fill='x', expand=True)

def creacionFrameK(datos):
    global frameKg
    frameKg = Frame(window, width=700, height=500)
    frameKg.config(background='pink')
    
    global frameTablaKg
    frameTablaKg = Frame(frameKg, width=700, height=500)
    frameTablaKg.config(background='pink')
    
    global tablaKg
    tablaKg =ttk.Treeview(frameTablaKg, columns=(1, 2, 3, 4), show='headings')
    tablaKg.column("#1", anchor="center", minwidth=0, width=100)
    tablaKg.column("#2", anchor="center", minwidth=0, width=100)
    tablaKg.column("#3", anchor="center", minwidth=0, width=100)
    tablaKg.column("#4", anchor="center", minwidth=0, width=100)      
    tablaKg.heading(1, text="i")
    tablaKg.heading(2, text="Ui")
    tablaKg.heading(3, text="i/N")
    tablaKg.heading(4, text="Di")

    global scrollTablaKg
    scrollTablaKg= ttk.Scrollbar(frameTablaKg, orient="vertical", command=tablaKg.yview)
    tablaKg.configure(yscrollcommand=scrollTablaKg.set)

    for x in range(len(datos)):
        tablaKg.insert("", index=END, values=datos[x])

    #print(len(Xi), len(Yi))
    plt.clf()
    plt.plot(Xi, Yi, linewidth = 5.0)
    plt.plot([1.0, len(aleatorios)], [1 / len(aleatorios), 1.0], linestyle = '--')
    plt.ylabel('Ui')
    plt.xlabel('Numeros')
    plt.title('Grafica Kolmogorov')
    plt.tick_params(axis='x', labelsize=8)
    fig = plt.gcf()
    global canvas
    canvas = FigureCanvasTkAgg(fig, master=frameKg)
    canvas.draw()

    global botonVolverKg
    botonVolverKg= Button(frameKg, text='Volver al Menú', command=menuKg)
    botonVolverKg.config(font=10, width=20, height=2)

def comprobarKg(error):
    if error == "":
        messagebox.showwarning("Advertencia", "Datos nulos.")
    else:
        if error == "5%":
            error=0.05
        else:
            error=0.1
        kolmogorov(error)   

def frameKolmogorov():
    limpiar_frame(window)
    framErrorSeries = Frame(window, width=700, height=500)
    framErrorSeries.config(background='pink')
    framErrorSeries.pack(fill='both', expand=True)
    
    labelError = Label(framErrorSeries, text='Seleccione el porcentaje de fallo')
    labelError.config(bg='Pink',font=('Arial', 15))
    labelError.pack(padx=5, pady=5)
    combo = ttk.Combobox(framErrorSeries, values=["5%", "10%"],state="readonly")
    combo.pack()

    botonEnviar = Button(framErrorSeries, text='Enviar', command=lambda: comprobarKg(combo.get()))
    botonEnviar.config(font=10, width=20, height=2)
    botonEnviar.pack(pady=10)
    
    botonVolver= Button(framErrorSeries, text='Volver al Menú', command=menuMetodos)
    botonVolver.config(font=10, width=20, height=2)
    botonVolver.pack(side="bottom", pady=10) 
#Fin Prueba Kolmogorov

#Inicio Prueba series
def comprobarSeries(error):
    if error == "":
        messagebox.showwarning("Advertencia", "Datos nulos.")
    else:
        if error == "5%":
            error=0.05
        else:
            error=0.1
        datosSeries(error)

def datosSeries(error):
    global errorS
    errorS=error
    global parOrdenado
    global parX
    global parY
    global e
    global observado
    global esperadoTabla
    global chiCuadrado
    parX=[]
    parY=[]
    parOrdenado=[]
    e=len(aleatorios)/(5**2)
    esperadoTabla=[[e,e,e,e,e],
               [e,e,e,e,e],
               [e,e,e,e,e],
               [e,e,e,e,e],
               [e,e,e,e,e]]
    observado=[[0,0,0,0,0],
               [0,0,0,0,0],
               [0,0,0,0,0],
               [0,0,0,0,0],
               [0,0,0,0,0]]
    
    chiCuadrado=[[0,0,0,0,0],
               [0,0,0,0,0],
               [0,0,0,0,0],
               [0,0,0,0,0],
               [0,0,0,0,0]]
    
    creacionFrameSeries()
    
    lon=len(aleatorios)
    for x in range(lon-1):
        posX=int(aleatorios[x]/.2)
        posY=int(aleatorios[x+1]/.2)
        observado[posY][posX]+=1
        parOrdenado.append([x+1,aleatorios[x],aleatorios[x+1]])
        parX.append(aleatorios[x])
        parY.append(aleatorios[x+1])
    parOrdenado.append([lon,aleatorios[lon-1],aleatorios[lon-1]])
    pos=int(aleatorios[lon-1]/.2)
    observado[pos][pos]+=1
    parX.append(aleatorios[lon-1])
    parY.append(aleatorios[lon-1])
    lon=len(parOrdenado)
    global sumatoriaChi
    sumatoriaChi=0.0
    for x in range(5):
        for(y) in range(5):
            chi=((observado[x][y]-esperadoTabla[x][y])**2)/esperadoTabla[x][y]
            chiCuadrado[x][y]=round(chi,3)
            sumatoriaChi+=chiCuadrado[x][y]
    sumatoriaChi=round(sumatoriaChi,4)
    for x in range(lon-1):
        tablaParesSeries.insert("", index=END, values=parOrdenado[x])
    tablaParesSeries.insert("", index=END, values=parOrdenado[lon-1])
    
    menuSeries()
    
def menuSeries():
    limpiar_frame(window)
    frameSeries = Frame(window, width=700, height=600)
    frameSeries.config(background='pink')
    frameSeries.pack(fill='both', expand=True)
    
    labelOpcionSeries = Label(frameSeries, text='Seleccione una opcion:')
    labelOpcionSeries.config(bg='Pink',font=('Arial', 15))
    labelOpcionSeries.pack(padx=5, pady=5)

    botonOpc1 = Button(frameSeries, text='Tabla Pares', command=botonOpc1Series)
    botonOpc1.config(font=10, width=18, height=1)
    botonOpc1.pack(pady=10)
    
    botonOpc2 = Button(frameSeries, text='Grafica Pares', command=botonOpc2Series)
    botonOpc2.config(font=10, width=18, height=1)
    botonOpc2.pack(pady=10)
    
    botonOpc3 = Button(frameSeries, text='Observados', command=botonOpc3Series)
    botonOpc3.config(font=10, width=18, height=1)
    botonOpc3.pack(pady=10)
    
    botonOpc4 = Button(frameSeries, text='Esperado', command=botonOpc4Series)
    botonOpc4.config(font=10, width=18, height=1)
    botonOpc4.pack(pady=10)
    
    botonOpc5 = Button(frameSeries, text='Chi Cuadrado', command=botonOpc5Series)
    botonOpc5.config(font=10, width=18, height=1)
    botonOpc5.pack(pady=10)
    
    botonVolver= Button(frameSeries, text='Volver al Menú', command=menuMetodos)
    botonVolver.config(font=10, width=20, height=2)
    botonVolver.pack(side="bottom", pady=10)
    
def creacionFrameSeries():
    global frameSeries
    frameSeries = Frame(window, width=700, height=500)
    frameSeries.config(background='pink')
    
    global frameTablaSeries
    frameTablaSeries = Frame(frameSeries, width=700, height=500)
    frameTablaSeries.config(background='pink')
    
    global tablaParesSeries
    tablaParesSeries =ttk.Treeview(frameTablaSeries, columns=(1, 2, 3), show='headings')
    tablaParesSeries.column("#1", anchor="center")
    tablaParesSeries.column("#2", anchor="center")
    tablaParesSeries.column("#3", anchor="center")      
    tablaParesSeries.heading(1, text="N")
    tablaParesSeries.heading(2, text="U1")
    tablaParesSeries.heading(3, text="U2")

    global scrollTablaSeries
    scrollTablaSeries= ttk.Scrollbar(frameTablaSeries, orient="vertical", command=tablaParesSeries.yview)
    tablaAleatorios.configure(yscrollcommand=scrollTablaSeries.set)
    
    global botonVolverSeries 
    botonVolverSeries= Button(frameSeries, text='Volver al Menú', command=menuSeries)
    botonVolverSeries.config(font=10, width=20, height=2)
    
def botonOpc1Series():
    limpiar_frame(window)
    limpiar_frame(frameSeries)
    frameSeries.pack(fill='both', expand=True)
    frameTablaSeries.pack(fill='both', expand=True, side=TOP)
    tablaParesSeries.pack(side=LEFT,fill='both',expand=True)
    scrollTablaSeries.pack(side=RIGHT, fill=Y)
    botonVolverSeries.pack(side="bottom", pady=10)
    
def botonOpc2Series():
    limpiar_frame(window)
    limpiar_frame(frameSeries)
    frameSeries.pack(fill='both', expand=True)
    fig = plt.figure()
    # Crear los ejes con las dimensiones proporcionales
    ax = fig.add_axes([0.2, 0.2, 0.6, 0.6])
    ax.set_xlim(0, 1)
    ax.set_ylim(0, 1)

    etiquetas_y = [0.2, 0.4, 0.6, 0.8, 1.0]
    ax.set_yticks(etiquetas_y)

    etiquetas_x = [0.2, 0.4, 0.6, 0.8, 1.0]
    ax.set_xticks(etiquetas_x)
    ax.grid(True)
    ax.scatter(parX, parY)
    canvas = FigureCanvasTkAgg(fig, master=frameSeries)
    canvas.draw()
    canvas.get_tk_widget().pack(fill='x', expand=True)
    botonVolverSeries.pack(side="bottom", pady=10)

def botonOpc3Series():
    limpiar_frame(window)
    limpiar_frame(frameSeries)
    frameSeries.pack(fill='both', expand=True)
    
    fig = plt.figure()
    ax = fig.add_axes([0.2, 0.2, 0.6, 0.6])
    ax.set_xlim(0, 1)
    ax.set_ylim(0, 1)
    etiquetas_y = [0.2, 0.4, 0.6, 0.8, 1.0]
    ax.set_yticks(etiquetas_y)
    etiquetas_x = [0.2, 0.4, 0.6, 0.8, 1.0]
    ax.set_xticks(etiquetas_x)
    ax.grid(True)

    # Agregar los valores en el centro de cada celda
    for i in range(len(observado)):
        for j in range(len(observado[i])):
            valor = observado[i][j]
            x = etiquetas_x[j] - 0.1  # Restar 0.1 para centrar en cada celda
            y = etiquetas_y[i] - 0.1  # Restar 0.1 para centrar en cada celda
            ax.text(x, y, valor, ha='center', va='center')

    canvas = FigureCanvasTkAgg(fig, master=frameSeries)
    canvas.draw()
    canvas.get_tk_widget().pack(fill='x', expand=True)
    botonVolverSeries.pack(side="bottom", pady=10)
    
def botonOpc4Series():
    limpiar_frame(window)
    limpiar_frame(frameSeries)
    frameSeries.pack(fill='both', expand=True)
    fig = plt.figure()
    ax = fig.add_axes([0.2, 0.2, 0.6, 0.6])
    ax.set_xlim(0, 1)
    ax.set_ylim(0, 1)
    etiquetas_y = [0.2, 0.4, 0.6, 0.8, 1.0]
    ax.set_yticks(etiquetas_y)
    etiquetas_x = [0.2, 0.4, 0.6, 0.8, 1.0]
    ax.set_xticks(etiquetas_x)
    ax.grid(True)

    # Agregar los valores en el centro de cada celda
    for i in range(len(esperadoTabla)):
        for j in range(len(esperadoTabla[i])):
            valor = esperadoTabla[i][j]
            x = etiquetas_x[j] - 0.1  # Restar 0.1 para centrar en cada celda
            y = etiquetas_y[i] - 0.1  # Restar 0.1 para centrar en cada celda
            ax.text(x, y, valor, ha='center', va='center')

    canvas = FigureCanvasTkAgg(fig, master=frameSeries)
    canvas.draw()
    canvas.get_tk_widget().pack(side=TOP,fill='x', expand=True)
    botonVolverSeries.pack(side="bottom", pady=10)

def botonOpc5Series():
    limpiar_frame(window)
    limpiar_frame(frameSeries)
    frameSeries.pack(fill='both', expand=True)
    fig = plt.figure()
    ax = fig.add_axes([0.2, 0.2, 0.6, 0.6])
    ax.set_xlim(0, 1)
    ax.set_ylim(0, 1)
    etiquetas_y = [0.2, 0.4, 0.6, 0.8, 1.0]
    ax.set_yticks(etiquetas_y)
    etiquetas_x = [0.2, 0.4, 0.6, 0.8, 1.0]
    ax.set_xticks(etiquetas_x)
    ax.grid(True)

    # Agregar los valores en el centro de cada celda
    for i in range(len(chiCuadrado)):
        for j in range(len(chiCuadrado[i])):
            valor = chiCuadrado[i][j]
            x = etiquetas_x[j] - 0.1  # Restar 0.1 para centrar en cada celda
            y = etiquetas_y[i] - 0.1  # Restar 0.1 para centrar en cada celda
            ax.text(x, y, valor, ha='center', va='center')

    canvas = FigureCanvasTkAgg(fig, master=frameSeries)
    canvas.draw()
    canvas.get_tk_widget().pack(side=TOP,fill='x', expand=True)
    err5=36.4150
    err10=33.1962
    txxt=str(err5)
    if errorS==.1:
        txxt=str(err10)
    labelChi=Label(frameSeries, text='Sumatoria chi: '+str(sumatoriaChi)+" <= "+txxt)
    labelChi.config(font=('Arial', 15),background='pink')
    labelChi.pack(fill='both', expand=True)
    txt="no son independientes"
    if (errorS==.05 and sumatoriaChi<=err5) or (errorS==0.1 and sumatoriaChi<=err10):
        txt="son independientes"
    labelChi2=Label(frameSeries, text='Los numeros: '+txt)
    labelChi2.config(font=('Arial', 15),background='pink')
    labelChi2.pack(fill='both', expand=True)
    botonVolverSeries.pack(side="bottom", pady=10)
    
def frameSeriesError():
    limpiar_frame(window)
    framErrorSeries = Frame(window, width=700, height=500)
    framErrorSeries.config(background='pink')
    framErrorSeries.pack(fill='both', expand=True)
    
    labelError = Label(framErrorSeries, text='Seleccione el porcentaje de fallo')
    labelError.config(bg='Pink',font=('Arial', 15))
    labelError.pack(padx=5, pady=5)
    combo = ttk.Combobox(framErrorSeries, values=["5%", "10%"],state="readonly")
    combo.pack()

    botonEnviar = Button(framErrorSeries, text='Enviar', command=lambda: comprobarSeries(combo.get()))
    botonEnviar.config(font=10, width=20, height=2)
    botonEnviar.pack(pady=10)
    
    botonVolver= Button(framErrorSeries, text='Volver al Menú', command=menuMetodos)
    botonVolver.config(font=10, width=20, height=2)
    botonVolver.pack(side="bottom", pady=10)    
#Fin Prueba Series

#Inicio Prueba Huecos
def frameHuecosInput():
    limpiar_frame(window)
    inputHuecos = Frame(window, width=700, height=500)
    inputHuecos.config(background='pink')
    inputHuecos.pack(fill='both', expand=True)

    labelError = Label(inputHuecos, text='Seleccione el porcentaje de fallo')
    labelError.config(bg='Pink',font=('Arial', 15))
    labelError.pack(padx=5, pady=5)
    combo = ttk.Combobox(inputHuecos, values=["5%", "10%"],state="readonly")
    combo.pack()

    labelAlpha = Label(inputHuecos, text='Ingrese alpha:', font=('Arial', 15, ))
    labelAlpha.config(bg='Pink')
    labelAlpha.pack(padx=5, pady=5)

    alpha = Entry(inputHuecos, font=15, width=15)
    alpha.pack(padx=5, pady=5)

    labelBeta = Label(inputHuecos, text='Ingrese beta:', font=('Arial', 15, ))
    labelBeta.config(bg='Pink')
    labelBeta.pack(padx=5, pady=5)

    beta = Entry(inputHuecos, font=15, width=15)
    beta.pack(padx=5, pady=5)

    botonEnviar = Button(inputHuecos, text='Enviar entrada manual', command=lambda:
        (comprobarHuecos(alpha.get(), beta.get(), combo.get()) ))
    botonEnviar.config(font=10, width=20, height=2)
    botonEnviar.pack(pady=10)
    
    botonVolver= Button(inputHuecos, text='Volver al Menú', command=menuMetodos)
    botonVolver.config(font=10, width=20, height=2)
    botonVolver.pack(pady=10)  
    
def comprobarHuecos(alpha,beta,error):
    if error == "" or alpha=="" or beta=="":
        messagebox.showwarning("Advertencia", "Datos nulos o invalidos.")
    else:
        if error == "5%":
            error=0.05
        else:
            error=0.1
        datosHuecos(float(alpha),float(beta),error)   

def datosHuecos(alpha,beta,error):
    global resHuecos
    resHuecos=[]
    global errorHuecos
    errorHuecos=error
    tetha = beta-alpha
    pf = 1 - tetha
    ind=0
    sumOi=0
    obsMapa=OrderedDict()
    # Determinar si hay un hueco y encontrar su tamaño
    while ind < len(aleatorios):
        cta=0
        # Pertenece al rango
        if alpha <= aleatorios[ind]<= beta:
            # Encontrar el ultimo elemento perteneciente al rango
            for j in range(ind, len(aleatorios)):
                e = alpha <= aleatorios[j] <= beta
                if e:
                    cta+=1
                else:
                    break
            for j in range(ind,ind+cta):
                resHuecos.append([j+1, aleatorios[j], 1, 0])
            if 0 in obsMapa:
                obsMapa[0] += 1
            else:
                obsMapa[0] = 1
            sumOi+=1
            ind+=cta
        else:   # No pertenece al rango
                # Encontrar el ultimo elemento no perteneciente al rango
            for j in range(ind, len(aleatorios)):
                e = alpha <= aleatorios[j] <= beta
                if not e:
                    cta+=1
                else:
                    break
            for j in range(ind, ind+cta):   
                resHuecos.append([j+1, aleatorios[j], 0, cta]) 
            if cta in obsMapa:
                obsMapa[cta]+=1
            else:
                obsMapa[cta]=1
            ind+=cta
            sumOi+=1
            
    
    obsMapa = OrderedDict(sorted(obsMapa.items(), key=lambda x: x[0]))
    global maxTam
    maxTam=0
    for x,y in obsMapa.items():
        if maxTam+1==x:
            maxTam=x
    maxTam+=1
    
    obs=[0]*(maxTam+1)
    for x,y in obsMapa.items():
        if x>=maxTam:
            obs[maxTam]+=y
        else:
            obs[x]+=y
    
    global tablaFinal
    tablaFinal=[]
    global sumChi2Huecos
    sumChi2Huecos=0
    sumPi=0
    sumEi=0
    for i in range(len(obs)):
        if i < len(obs) - 1: 
            pi = (pf ** i) * (tetha) 
        else:
            pi = pf ** i
        ei = sumOi * pi
        oiEi = obs[i]-ei
        chi = (oiEi ** 2) / ei
        sumChi2Huecos+=chi
        sumPi+=pi
        sumEi+=ei
        tablaFinal.append([i,round(pi,5),obs[i], round(ei,5),round(oiEi,5),round(chi,5)])    
    tablaFinal.append(["",round(sumPi,5),sumOi, round(sumEi,5),"Σ=",round(sumChi2Huecos,5)])
    menuHuecos()
    
def menuHuecos():
    limpiar_frame(window)
    fmenuHuecos = Frame(window, width=700, height=500)
    fmenuHuecos.config(background='pink')
    fmenuHuecos.pack(fill='both', expand=True)
    
    labelOpcionSeries = Label(fmenuHuecos, text='Seleccione una opcion:')
    labelOpcionSeries.config(bg='Pink',font=('Arial', 15))
    labelOpcionSeries.pack(padx=5, pady=5)

    botonOpc1 = Button(fmenuHuecos, text='Tabla Pares', command=opc1Huecos)
    botonOpc1.config(font=10, width=18, height=1)
    botonOpc1.pack(pady=10)
    
    botonOpc2 = Button(fmenuHuecos, text='Grafica Pares', command=opc2Huecos)
    botonOpc2.config(font=10, width=18, height=1)
    botonOpc2.pack(pady=10)
    
    botonVolver= Button(fmenuHuecos, text='Volver al Menú', command=menuMetodos)
    botonVolver.config(font=10, width=20, height=2)
    botonVolver.pack(side="bottom", pady=10)

def opc1Huecos():
    limpiar_frame(window)
    frameHuecos = Frame(window, width=700, height=500)
    frameHuecos.config(background='pink')
    frameHuecos.pack(fill='both', expand=True)
    
    tabla1Huecos = ttk.Treeview(frameHuecos, columns=(1,2,3,4), show='headings', height=20)
    tabla1Huecos.column("#1", anchor="center", width=50)
    tabla1Huecos.column("#2", anchor="center", width=50)
    tabla1Huecos.column("#3", anchor="center", width=50)     
    tabla1Huecos.column("#4", anchor="center", width=50)  
    tabla1Huecos.heading(1, text="n")
    tabla1Huecos.heading(2, text="Ui")
    tabla1Huecos.heading(3, text="Pertenece")
    tabla1Huecos.heading(4, text="i")

    for dato in resHuecos:
        tabla1Huecos.insert("", "end", values=dato)
    
    scrollHuecos= ttk.Scrollbar(frameHuecos, orient=VERTICAL, command=tabla1Huecos.yview)
    scrollHuecos.pack(side=RIGHT, fill=Y)
    tabla1Huecos.configure(yscrollcommand=scrollHuecos.set)
    tabla1Huecos.pack(fill=BOTH, expand=True)
    
    botonVolver= Button(frameHuecos, text='Volver al Menú', command=menuHuecos)
    botonVolver.config(font=10, width=20, height=2)
    botonVolver.pack(side="bottom", pady=10)

def opc2Huecos():
    limpiar_frame(window)
    frameHuecos = Frame(window, width=700, height=500)
    frameHuecos.config(background='pink')
    frameHuecos.pack(fill='both', expand=True)
    
    tabla2Huecos = ttk.Treeview(frameHuecos, columns=(1,2,3,4,5,6), show='headings',height=10)
    tabla2Huecos.column("#1", anchor="center", width=50)
    tabla2Huecos.column("#2", anchor="center", width=100)
    tabla2Huecos.column("#3", anchor="center", width=50)     
    tabla2Huecos.column("#4", anchor="center", width=50)  
    tabla2Huecos.column("#5", anchor="center", width=100)  
    tabla2Huecos.column("#6", anchor="center", width=100)  
    tabla2Huecos.heading(1, text="i")
    tabla2Huecos.heading(2, text="Pi")
    tabla2Huecos.heading(3, text="Oi")
    tabla2Huecos.heading(4, text="Ei")
    tabla2Huecos.heading(5, text="Oi-Ei")
    tabla2Huecos.heading(6, text="(Oi-Ei)²/Ei")

    for dato in tablaFinal:
        tabla2Huecos.insert("", "end", values=dato)
    
    tabla2Huecos.pack(fill='x', expand=True)
    
    Err5=chi2.ppf(0.95, maxTam)
    Err10=chi2.ppf(0.90, maxTam)

    txxt=f"{Err5:.5f}"
    if errorHuecos==.1:
        txxt=f"{Err10:.5f}"

    label=Label(frameHuecos, text='Sumatoria chi: '+ f"{sumChi2Huecos:.5f}"+" <= "+txxt)
    label.config(font=('Arial', 15),background='pink')
    label.pack(fill='both', expand=True)

    txt="no estan uniformemente distribuidos" 

    if (errorHuecos==.05 and sumChi2Huecos<=Err5) or (errorHuecos==0.1 and sumChi2Huecos<=Err10):
        txt="estan uniformemente distribuidos"
    label2=Label(frameHuecos, text='Los numeros: '+txt)
    label2.config(font=('Arial', 15),background='pink')
    label2.pack(fill='both', expand=True)
    
    botonVolver= Button(frameHuecos, text='Volver al Menú', command=menuHuecos)
    botonVolver.config(font=10, width=20, height=2)
    botonVolver.pack()
#Fin Prueba Huecos

#Inicio Prueba poker  
def frameErrorPoker():
    limpiar_frame(window)
    inputPoker = Frame(window, width=700, height=500)
    inputPoker.config(background='pink')
    inputPoker.pack(fill='both', expand=True)

    labelError = Label(inputPoker, text='Seleccione el porcentaje de fallo')
    labelError.config(bg='Pink',font=('Arial', 15))
    labelError.pack(padx=5, pady=5)
    combo = ttk.Combobox(inputPoker, values=["5%", "10%"],state="readonly")
    combo.pack()

    botonEnviar = Button(inputPoker, text='Enviar entrada manual', 
        command=lambda:(comprobarPoker(combo.get()) ))
    botonEnviar.config(font=10, width=20, height=2)
    botonEnviar.pack(pady=10)
    
    botonVolver= Button(inputPoker, text='Volver al Menú', command=menuMetodos)
    botonVolver.config(font=10, width=20, height=2)
    botonVolver.pack(pady=10)  

def comprobarPoker(error):
    if error == "":
        messagebox.showwarning("Advertencia", "Datos nulos o invalidos.")
    else:
        if error == "5%":
            error=0.05
        else:
            error=0.1
        datosPoker(error)   

def tipoEvento(numero):
    repeticiones = np.zeros(10, dtype=int)
    caso = str(numero)
    caso = caso.ljust(7, '0')
    for i in range(2, 7):
        repeticiones[int(caso[i])] += 1
    repeticiones.sort()
    if repeticiones[9] == 5:
        return "Quintilla"
    if repeticiones[9] == 4:
        return "Poker"
    if repeticiones[9] == 3 and repeticiones[8] == 2:
        return "Full"
    if repeticiones[9] == 3 and repeticiones[8] != 2:
        return "Tercia"
    if repeticiones[9] == 2 and repeticiones[8] == 2:
        return "2 Pares"
    if repeticiones[9] == 2 and repeticiones[8] != 2:
        return "1 Par"
    return "Pachuca"

def datosPoker(error):
    global errorPoker
    global resPoker
    global foPoker
    global noms
    global pePoker
    errorPoker=error
    resPoker=[]
    foPoker=[0]*7
    noms=["Pachuca","1 Par","Tercia","2 Pares","Full","Poker","Quintilla"]
    pePoker=[0.3024,0.5040,0.0720,0.1080,0.0090,0.0045,0.0001]
    for i in range(len(aleatorios)):
        tipo=tipoEvento(aleatorios[i])
        resPoker.append([i+1,aleatorios[i],tipo])
        foPoker[noms.index(tipo)]+=1
    menuPoker()
        
def menuPoker():
    limpiar_frame(window)
    framePoker = Frame(window, width=700, height=500)
    framePoker.config(background='pink')
    framePoker.pack(fill='both', expand=True)
    
    labelOpcPoker = Label(framePoker, text='Seleccione una opcion:')
    labelOpcPoker.config(bg='Pink',font=('Arial', 15))
    labelOpcPoker.pack(padx=5, pady=5)

    botonOpc1 = Button(framePoker, text='Tabla', command=opc1Poker)
    botonOpc1.config(font=10, width=18, height=1)
    botonOpc1.pack(pady=10)
    
    botonOpc2 = Button(framePoker, text='Chi2', command=opc2Poker)
    botonOpc2.config(font=10, width=18, height=1)
    botonOpc2.pack(pady=10)
    
    botonVolver= Button(framePoker, text='Volver al Menú', command=menuMetodos)
    botonVolver.config(font=10, width=20, height=2)
    botonVolver.pack(side="bottom", pady=10)
    
def opc1Poker():
    limpiar_frame(window)
    frameOp1Poker = Frame(window, width=700, height=500)
    frameOp1Poker.config(background='pink')
    frameOp1Poker.pack(fill='both', expand=True)
    
    tabla1Poker = ttk.Treeview(frameOp1Poker, columns=(1,2,3), show='headings', height=20)
    tabla1Poker.column("#1", anchor="center", width=50)
    tabla1Poker.column("#2", anchor="center", width=50)
    tabla1Poker.column("#3", anchor="center", width=50)     
    tabla1Poker.heading(1, text="n")
    tabla1Poker.heading(2, text="ri")
    tabla1Poker.heading(3, text="Evento")

    for dato in resPoker:
        tabla1Poker.insert("", "end", values=dato)
    
    scrollPoker= ttk.Scrollbar(frameOp1Poker, orient=VERTICAL, command=tabla1Poker.yview)
    scrollPoker.pack(side=RIGHT, fill=Y)
    tabla1Poker.configure(yscrollcommand=scrollPoker.set)
    tabla1Poker.pack(fill=BOTH, expand=True)
    
    botonVolver= Button(frameOp1Poker, text='Volver al Menú', command=menuPoker)
    botonVolver.config(font=10, width=20, height=2)
    botonVolver.pack(side="bottom", pady=10)
    
def opc2Poker():
    limpiar_frame(window)
    frameOp2Poker = Frame(window, width=700, height=500)
    frameOp2Poker.config(background='pink')
    frameOp2Poker.pack(fill='both', expand=True)
    
    tabla2Poker = ttk.Treeview(frameOp2Poker, columns=(1,2,3,4,5), show='headings', height=20)
    tabla2Poker.column("#1", anchor="center", width=50)
    tabla2Poker.column("#2", anchor="center", width=50)
    tabla2Poker.column("#3", anchor="center", width=50)     
    tabla2Poker.column("#4", anchor="center", width=50)    
    tabla2Poker.column("#5", anchor="center", width=50)    
    tabla2Poker.heading(1, text="Evento")
    tabla2Poker.heading(2, text="FO")
    tabla2Poker.heading(3, text="PE")
    tabla2Poker.heading(4, text="FE")
    tabla2Poker.heading(5, text="(FOi-FEi)² / FEi")
    
    sumChiPok=0
    sumFo=0
    sumPe=0
    sumFe=0
    for i in range(7):
        fe=round(len(aleatorios)*pePoker[i],5)
        chiP=round(((foPoker[i]-fe)**2)/fe,5)
        sumPe+=pePoker[i]
        sumChiPok+=chiP
        sumFo+=foPoker[i]
        sumFe+=fe
        dato=[noms[i],foPoker[i],pePoker[i],fe,chiP]
        tabla2Poker.insert("", "end", values=dato)
    sumPe=round(sumPe,5)
    sumFe=round(sumFe,5)
    tabla2Poker.insert("", "end", values=["Total de la muestra",sumFo,sumPe,sumFe,sumChiPok])
    tabla2Poker.pack(fill=BOTH, expand=True)
    
    Err5=chi2.ppf(0.95, 6)
    Err10=chi2.ppf(0.90, 6)

    txxt=f"{Err5:.5f}"
    if errorPoker==.1:
        txxt=f"{Err10:.5f}"

    label=Label(frameOp2Poker, text='Sumatoria chi: '+ f"{sumChiPok:.5f}"+" <= "+txxt)
    label.config(font=('Arial', 15),background='pink')
    label.pack(fill='both', expand=True)

    txt="no son independientes entre si" 

    if (errorPoker==.05 and sumChiPok<=Err5) or (errorPoker==0.1 and sumChiPok<=Err10):
        txt="son independientes entre si"
    label2=Label(frameOp2Poker, text='Los numeros: '+txt)
    label2.config(font=('Arial', 15),background='pink')
    label2.pack(fill='both', expand=True)
    
    botonVolver= Button(frameOp2Poker, text='Volver al Menú', command=menuPoker)
    botonVolver.config(font=10, width=20, height=2)
    botonVolver.pack(side="bottom", pady=10)
    
#Fin Prueba Poker    

#Menu
def menuMetodos():
    limpiar_frame(window)
    frameMetodos = Frame(window, width=700, height=500)
    frameMetodos.config(background='pink')
    frameMetodos.pack(fill='both', expand=True)

    labelOpcion = Label(frameMetodos, text='Seleccione una opcion:')
    labelOpcion.config(bg='Pink',font=('Arial', 15))
    labelOpcion.pack(padx=5, pady=5)

    botonMetodo1 = Button(frameMetodos, text='Chi2', command=frameChiError)
    botonMetodo1.config(font=10, width=18, height=1)
    botonMetodo1.pack(pady=5)
    
    botonMetodo2 = Button(frameMetodos, text='Kolmogorov', command=frameKolmogorov)
    botonMetodo2.config(font=10, width=18, height=1)
    botonMetodo2.pack(pady=5)
    
    botonSeries = Button(frameMetodos, text='Series', command=frameSeriesError)
    botonSeries.config(font=10, width=18, height=1)
    botonSeries.pack(pady=5)
    
    botonMetodo4 = Button(frameMetodos, text='Distancia o Huecos', command=frameHuecosInput)
    botonMetodo4.config(font=10, width=18, height=1)
    botonMetodo4.pack(pady=5)
    
    botonMetodo5 = Button(frameMetodos, text='Poker', command=frameErrorPoker)
    botonMetodo5.config(font=10, width=18, height=1)
    botonMetodo5.pack(pady=5)
    
    botonMostrarNumeros = Button(frameMetodos, text='Mostrar Numeros')
    botonMostrarNumeros.config(command=mostrarAleatorios,font=10, width=18, height=1)
    botonMostrarNumeros.pack(pady=5)
    
    botonGenerar = Button(frameMetodos, text='Volver a generar', command=menu)
    botonGenerar.config(font=10, width=18, height=1)
    botonGenerar.pack(pady=5)
    
    botonSalir = Button(frameMetodos, text='Salir', command=window.quit)
    botonSalir.config(font=10, width=18, height=1)
    botonSalir.pack(pady=5)

def menu():
    limpiar_frame(window)
    frameInicio = Frame(window, width=640, height=360)
    frameInicio.config(background='pink')
    frameInicio.pack(fill='both', expand=True)

    labelEnviar = Label(frameInicio, text='Introduzca la cantidad de números aleatorios:')
    labelEnviar.config(bg='Pink',font=('Arial', 15))
    labelEnviar.pack(padx=5, pady=5)

    entryManual = Entry(frameInicio, font=15, width=15)
    entryManual.pack(padx=5, pady=5)

    botonEnviar = Button(frameInicio, text='Enviar', font=10, width=20, height=2)
    botonEnviar.config(command=lambda: comprobarEntry(entryManual.get()))
    botonEnviar.pack(pady=10)
    botonSalir = Button(frameInicio, text='Salir', command=window.quit)
    botonSalir.config(font=10, width=20, height=2)
    botonSalir.pack(pady=10)

window = Tk()
window.geometry('700x600')
window.resizable(False, False)
window.title('Simulasao')
menu()
window.mainloop()