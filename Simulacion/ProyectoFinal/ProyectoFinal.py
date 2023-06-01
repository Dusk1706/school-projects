import random as r
from tkinter import *
from tkinter import ttk

def limpiar_frame(frame):
    for widgets in frame.winfo_children():
        widgets.pack_forget()

def mostrarPromedio():
    limpiar_frame(window)
    framePromedio=Frame(window)
    framePromedio.config(background='light sea green')
    framePromedio.pack(fill='both', expand=True)
    
    labelPromedio=Label(framePromedio,text="Resultados Simulacion",font=("Ubuntu",15))
    labelPromedio.config(bg='light sea green',font=('Arial', 20, ))
    labelPromedio.pack(padx=5, pady=10)
    
    tablaProm = ttk.Treeview(framePromedio, columns=(1,2,3,4,5,6), show='headings')
    style = ttk.Style()
    style.configure('Treeview.Heading', rowheight=100)    
    style.configure("Treeview", rowheight=20)  
    
    tablaProm.column("#1",anchor="center",width=50)
    tablaProm.column("#2",anchor="center",width=50)
    tablaProm.column("#3",anchor="center",width=50)
    tablaProm.column("#4",anchor="center",width=50)
    tablaProm.column("#5",anchor="center",width=50)
    tablaProm.column("#6",anchor="center",width=50)
    tablaProm.heading("#0", text="\n\n\n")
    tablaProm.heading(1, text="Tamaño\ndel equipo")
    tablaProm.heading(2, text="Salario\nNormal")
    tablaProm.heading(3, text="Salario\nExtra")
    tablaProm.heading(4, text="Ocio\ndel Camion")
    tablaProm.heading(5, text="Operacion del\nalmacen")
    tablaProm.heading(6, text="Costos\nTotales")
    
    tablaProm.pack(fill=BOTH, expand=True)
    for x in general:
        tablaProm.insert("", index=END, values=x)
    botonVolver = Button(framePromedio, text='Volver', command=menuSimulacion)
    botonVolver.config(font=10, width=20, height=2)
    botonVolver.pack(pady=5)
        
def mostrarTabla6():
    limpiar_frame(window)
    frame6=Frame(window)
    frame6.config(background='light sea green')
    frame6.pack(fill='both', expand=True)
    
    labelPromedio=Label(frame6,text="Resultados Equipo 6",font=("Ubuntu",15))
    labelPromedio.config(bg='light sea green',font=('Arial', 20, ))
    labelPromedio.pack(padx=5, pady=10)
    
    tabla6 = ttk.Treeview(frame6, columns=(1,2,3,4,5,6,7,8,9,10), show='headings')
    style = ttk.Style()
    style.configure('Treeview.Heading', rowheight=100)    
    style.configure("Treeview", rowheight=20)  
    
    tabla6.column("#1",anchor="center",width=50)
    tabla6.column("#2",anchor="center",width=50)
    tabla6.column("#3",anchor="center",width=50)
    tabla6.column("#4",anchor="center",width=50)
    tabla6.column("#5",anchor="center",width=50)
    tabla6.column("#6",anchor="center",width=50)
    tabla6.column("#7",anchor="center",width=50)
    tabla6.column("#8",anchor="center",width=50)
    tabla6.column("#9",anchor="center", width=50)
    tabla6.column("#10",anchor="center", width=50)
    tabla6.heading("#0", text="\n\n\n")
    tabla6.heading(1, text="\nnRand\n")
    tabla6.heading(2, text="T entre\nllegadas")
    tabla6.heading(3, text="T de\nllegada")
    tabla6.heading(4, text="Iniciacion \ndel servicio")
    tabla6.heading(5, text="nRand")
    tabla6.heading(6, text="T de\nservicio")
    tabla6.heading(7, text="Terminacion \ndel servicio")
    tabla6.heading(8, text="Ocio del\npersonal")
    tabla6.heading(9, text="T de espera\n del camion")
    tabla6.heading(10, text="Longitud de\nla cola")
    
    scroll6 = ttk.Scrollbar(frame6, orient=VERTICAL, command=tabla6.yview)
    tabla6.configure(yscrollcommand=scroll6.set) 
    scroll6.pack(side=RIGHT, fill=Y)
    tabla6.pack(fill=BOTH, expand=True)
    for x in datos6:
        tabla6.insert("", index=END, values=x)
    botonVolver = Button(frame6, text='Volver', command=menuSimulacion)
    botonVolver.config(font=10, width=20, height=2)
    botonVolver.pack(pady=5)

def mostrarTabla5():
    limpiar_frame(window)
    frame5=Frame(window)
    frame5.config(background='light sea green')
    frame5.pack(fill='both', expand=True)

    label5=Label(frame5,text="Resultados Equipo 5",font=("Ubuntu",15))
    label5.config(bg='light sea green',font=('Arial', 20, ))
    label5.pack(padx=5, pady=10)
    
    tabla5 = ttk.Treeview(frame5, columns=(1,2,3,4,5,6,7,8,9,10), show='headings')
    style = ttk.Style()
    style.configure('Treeview.Heading', rowheight=100)    
    style.configure("Treeview", rowheight=20)  
    
    tabla5.column("#1",anchor="center",width=50)
    tabla5.column("#2",anchor="center",width=50)
    tabla5.column("#3",anchor="center",width=50)
    tabla5.column("#4",anchor="center",width=50)
    tabla5.column("#5",anchor="center",width=50)
    tabla5.column("#6",anchor="center",width=50)
    tabla5.column("#7",anchor="center",width=50)
    tabla5.column("#8",anchor="center",width=50)
    tabla5.column("#9",anchor="center", width=50)
    tabla5.column("#10",anchor="center", width=50)
    tabla5.heading("#0", text="\n\n\n")
    tabla5.heading(1, text="\nnRand\n")
    tabla5.heading(2, text="T entre\nllegadas")
    tabla5.heading(3, text="T de\nllegada")
    tabla5.heading(4, text="Iniciacion \ndel servicio")
    tabla5.heading(5, text="nRand")
    tabla5.heading(6, text="T de\nservicio")
    tabla5.heading(7, text="Terminacion \ndel servicio")
    tabla5.heading(8, text="Ocio del\npersonal")
    tabla5.heading(9, text="T de espera\n del camion")
    tabla5.heading(10, text="Longitud de\nla cola")
    
    scroll5 = ttk.Scrollbar(frame5, orient=VERTICAL, command=tabla5.yview)
    tabla5.configure(yscrollcommand=scroll5.set) 
    scroll5.pack(side=RIGHT, fill=Y)
    tabla5.pack(fill=BOTH, expand=True)
    for x in datos5:
        tabla5.insert("", index=END, values=x)
    botonVolver = Button(frame5, text='Volver', command=menuSimulacion)
    botonVolver.config(font=10, width=20, height=2)
    botonVolver.pack(pady=5)
        
def mostrarTabla4():
    limpiar_frame(window)
    frame4=Frame(window)
    frame4.config(background='light sea green')
    frame4.pack(fill='both', expand=True)

    label4=Label(frame4,text="Resultados Equipo 4",font=("Ubuntu",15))
    label4.config(bg='light sea green',font=('Arial', 20, ))
    label4.pack(padx=5, pady=10)
    
    tabla4 = ttk.Treeview(frame4, columns=(1,2,3,4,5,6,7,8,9,10), show='headings')
    style = ttk.Style()
    style.configure('Treeview.Heading', rowheight=100)    
    style.configure("Treeview", rowheight=20)  
    
    tabla4.column("#1",anchor="center",width=50)
    tabla4.column("#2",anchor="center",width=50)
    tabla4.column("#3",anchor="center",width=50)
    tabla4.column("#4",anchor="center",width=50)
    tabla4.column("#5",anchor="center",width=50)
    tabla4.column("#6",anchor="center",width=50)
    tabla4.column("#7",anchor="center",width=50)
    tabla4.column("#8",anchor="center",width=50)
    tabla4.column("#9",anchor="center", width=50)
    tabla4.column("#10",anchor="center", width=50)
    tabla4.heading("#0", text="\n\n\n")
    tabla4.heading(1, text="\nnRand\n")
    tabla4.heading(2, text="T entre\nllegadas")
    tabla4.heading(3, text="T de\nllegada")
    tabla4.heading(4, text="Iniciacion \ndel servicio")
    tabla4.heading(5, text="nRand")
    tabla4.heading(6, text="T de\nservicio")
    tabla4.heading(7, text="Terminacion \ndel servicio")
    tabla4.heading(8, text="Ocio del\npersonal")
    tabla4.heading(9, text="T de espera\n del camion")
    tabla4.heading(10, text="Longitud de\nla cola")
    
    scroll4 = ttk.Scrollbar(frame4, orient=VERTICAL, command=tabla4.yview)
    tabla4.configure(yscrollcommand=scroll4.set) 
    scroll4.pack(side=RIGHT, fill=Y)
    tabla4.pack(fill=BOTH, expand=True)
    for x in datos4:
        tabla4.insert("", index=END, values=x)
    botonVolver = Button(frame4, text='Volver', command=menuSimulacion)
    botonVolver.config(font=10, width=20, height=2)
    botonVolver.pack(pady=5)

def mostrarTabla3():
    limpiar_frame(window)
    frame3=Frame(window)
    frame3.config(background='light sea green')
    frame3.pack(fill='both', expand=True)

    label3=Label(frame3,text="Resultados Equipo 3",font=("Ubuntu",15))
    label3.config(bg='light sea green',font=('Arial', 20, ))
    label3.pack(padx=5, pady=10)
    
    tabla3 = ttk.Treeview(frame3, columns=(1,2,3,4,5,6,7,8,9,10), show='headings')
    style = ttk.Style()
    style.configure('Treeview.Heading', rowheight=100)    
    style.configure("Treeview", rowheight=20)  

    tabla3.column("#1",anchor="center",width=50)
    tabla3.column("#2",anchor="center",width=50)
    tabla3.column("#3",anchor="center",width=50)
    tabla3.column("#4",anchor="center",width=50)
    tabla3.column("#5",anchor="center",width=50)
    tabla3.column("#6",anchor="center",width=50)
    tabla3.column("#7",anchor="center",width=50)
    tabla3.column("#8",anchor="center",width=50)
    tabla3.column("#9",anchor="center", width=50)
    tabla3.column("#10",anchor="center", width=50)
    tabla3.heading("#0", text="\n\n\n")
    tabla3.heading(1, text="\nnRand\n")
    tabla3.heading(2, text="T entre\nllegadas")
    tabla3.heading(3, text="T de\nllegada")
    tabla3.heading(4, text="Iniciacion \ndel servicio")
    tabla3.heading(5, text="nRand")
    tabla3.heading(6, text="T de\nservicio")
    tabla3.heading(7, text="Terminacion \ndel servicio")
    tabla3.heading(8, text="Ocio del\npersonal")
    tabla3.heading(9, text="T de espera\n del camion")
    tabla3.heading(10, text="Longitud de\nla cola")
    
    scroll3 = ttk.Scrollbar(frame3, orient=VERTICAL, command=tabla3.yview)
    tabla3.configure(yscrollcommand=scroll3.set) 
    scroll3.pack(side=RIGHT, fill=Y)
    tabla3.pack(fill=BOTH, expand=True)
    for x in datos3:
        tabla3.insert("", index=END, values=x)
    botonVolver = Button(frame3, text='Volver', command=menuSimulacion)
    botonVolver.config(font=10, width=20, height=2)
    botonVolver.pack(pady=5)

def camionesIniciales(numRandom):   
    if(numRandom < 0.5):
        return 0
    if(numRandom < 0.75):
        return 1
    if(numRandom < 0.9):
        return 2
    return 3

def tiempoLlegada(numRandom):
    if(numRandom < 0.02):
        return 20
    if(numRandom < 0.1):
        return 25
    if(numRandom < 0.22):
        return 30
    if(numRandom < 0.47):
        return 35
    if(numRandom < 0.67):
        return 40
    if(numRandom < 0.82):
        return 45
    if(numRandom < 0.92):
        return 50
    if(numRandom < 0.97):
        return 55
    return 60

def tiempoServicio(numRandom, equipo):
    if(equipo == 3):
        if(numRandom < 0.05): return 20
        if(numRandom < 0.15): return 25
        if(numRandom < 0.35): return 30
        if(numRandom < 0.60): return 35
        if(numRandom < 0.72): return 40
        if(numRandom < 0.82): return 45
        if(numRandom < 0.90): return 50
        if(numRandom < 0.96): return 55
        return 60
    if(equipo == 4):
        if(numRandom < 0.05): return 15
        if(numRandom < 0.2 ): return 20
        if(numRandom < 0.4 ): return 25
        if(numRandom < 0.6 ): return 30
        if(numRandom < 0.75): return 35
        if(numRandom < 0.87): return 40
        if(numRandom < 0.95): return 45
        if(numRandom < 0.99): return 50
        return 55
    if(equipo == 5):
        if(numRandom < 0.1 ): return 10
        if(numRandom < 0.28): return 15
        if(numRandom < 0.5 ): return 20
        if(numRandom < 0.68): return 25
        if(numRandom < 0.78): return 30
        if(numRandom < 0.86): return 35
        if(numRandom < 0.92): return 40
        if(numRandom < 0.97): return 45
        return 50
    if(numRandom < 0.12): return 5
    if(numRandom < 0.27): return 10
    if(numRandom < 0.53): return 15
    if(numRandom < 0.68): return 20
    if(numRandom < 0.80): return 25
    if(numRandom < 0.88): return 30
    if(numRandom < 0.94): return 35
    if(numRandom < 0.98): return 40
    return 45

def menuSimulacion():
    limpiar_frame(window)
    frameMenu = Frame(window, width=700, height=700)
    frameMenu.config(background='pink')
    frameMenu.pack(fill='both', expand=True)

    generarBtn = Label(frameMenu, text='Selecciona una opcion')
    generarBtn.config(bg='Pink',font=('Arial', 15))
    generarBtn.pack(padx=5, pady=5)

    botonEquipo3 = Button(frameMenu, text='Equipo 3', command=mostrarTabla3)
    botonEquipo3.config(font=10, width=20, height=2)
    botonEquipo3.pack(pady=5)
    
    botonEquipo4 = Button(frameMenu, text='Equipo 4', command=mostrarTabla4)
    botonEquipo4.config(font=10, width=20, height=2)
    botonEquipo4.pack(pady=5)
    
    botonEquipo5 = Button(frameMenu, text='Equipo 5', command=mostrarTabla5)
    botonEquipo5.config(font=10, width=20, height=2)
    botonEquipo5.pack(pady=5)
    
    botonEquipo6 = Button(frameMenu, text='Equipo 6', command=mostrarTabla6)
    botonEquipo6.config(font=10, width=20, height=2)
    botonEquipo6.pack(pady=5)
    
    botonPromedio = Button(frameMenu, text='Promedio', command=mostrarPromedio)
    botonPromedio.config(font=10, width=20, height=2)
    botonPromedio.pack(pady=5)
    
    botonVolver = Button(frameMenu, text='Volver', command=menu)
    botonVolver.config(font=10, width=20, height=2)
    botonVolver.pack(pady=5)

def formatoHora(minutos):
    hora = minutos // 60
    minutos = minutos % 60

    if hora > 12:
        hora %= 12

    return str(hora) + ":" + str(minutos)

def generarDatos():
    turnos = 60
    global datos3
    global datos4
    global datos5
    global datos6
    datos3=[]
    datos4=[]
    datos5=[]
    datos6=[]
    global general
    general = [[0]*6, [0]*6, [0]*6, [0]*6]
    general[0][0] = 3
    general[0][1] = 600
    general[1][0] = 4
    general[1][1] = 800
    general[2][0] = 5
    general[2][1] = 1000
    general[3][0] = 6
    general[3][1] = 1200
    #NumRand, T entre llegada, T llegada, 
    # Inicio servicio, NumRand, T servicio, Terminacion Servicio
    # Ocio Personal, T espera camion, Longitud cola
    
    for i in range(4):
        datos = []
        K = 0
        for j in range(turnos):
            camiones = [[0 for _ in range(10)] for _ in range(30)]
            numR = r.uniform(0,1)
            camionesIn = camionesIniciales(numR)
            #Formato en minutos primera hora 11:00
            Hora = 660
            
            C_camiones = 0
                
            for k in range(camionesIn): #Inicializar las horas de llegada de los camiones
                camiones[C_camiones][2] = 660
                camiones[C_camiones][9] = camionesIn - C_camiones #Ajuste Longitud de la cola
                C_camiones += 1
            
            while Hora <= 1170:
                numR = r.uniform(0,1)
                camiones[C_camiones][0] = numR
                camiones[C_camiones][1] = tiempoLlegada(numR)
                camiones[C_camiones][2] = camiones[C_camiones][1] + Hora
                Hora = camiones[C_camiones][2]
                C_camiones += 1

            K += C_camiones
            C_camiones -= 1
            descanso = True
            ocioCamiones = 0

            # [0, nRand]
            # [1, T entre LLegadas]
            # [2, T de llegada]
            # [3, Iniciacion del servicio]
            # [4, nRand]
            # [5, T de servicio]
            # [6, Terminacion del servicio]
            # [7, Ocio del personal]
            # [8, T de espera del camion]
            # [9, Longitud de la cola]

            for x in range(C_camiones):
                numR = r.uniform(0,1)
                if x == 0:
                    camiones[x][3] = camiones[x][2]
                elif (camiones[x][9] == 0):
                    camiones[x][3] = camiones[x][2]
                else:
                    camiones[x][3] = camiones[x-1][6]
                
                if camiones[x][3] >= 900 and descanso:
                    camiones[x][3] += 30
                    descanso = False
                    
                camiones[x][4] = numR
                camiones[x][5] = tiempoServicio(numR, i+3)
                camiones[x][6] = camiones[x][5] + camiones[x][3]
                #Tiempo Ocio Personal
                camiones[x][7] = camiones[x][3] - 660
                if(x != 0):
                    camiones[x][7] = camiones[x][3] - camiones[x - 1][6]
                #Tiempo Ocio Camion
                camiones[x][8] = camiones[x][3] - camiones[x][2]
                ocioCamiones += camiones[x][8]
                
                for y in range(x+1, C_camiones):
                    if camiones[y][2] < camiones[x][6]:
                        camiones[y][9] += 1
                    else:
                        break
                
            datos.append(["Turno", j+1])
            for y in range(C_camiones):
                if y < camionesIn:
                    datos.append(["", "", 
                    formatoHora(camiones[y][2]), formatoHora(camiones[y][3]),
                     f"{camiones[y][4]:.5f}", camiones[y][5], 
                     formatoHora(camiones[y][6]), camiones[y][7], 
                     camiones[y][8], camiones[y][9]])
                else:
                    datos.append([f"{camiones[y][0]:.5f}", camiones[y][1], 
                        formatoHora(camiones[y][2]), formatoHora(camiones[y][3]),
                        f"{camiones[y][4]:.5f}", camiones[y][5], 
                        formatoHora(camiones[y][6]), camiones[y][7], 
                        camiones[y][8], camiones[y][9]])

            #Calculos para la tabla de promedios
            extraHora = max(camiones[C_camiones - 1][6], 1170) - 1170
            general[i][2] += ((extraHora / 60) * 37.5)
            general[i][4] += (4250 + ((extraHora / 60) * 500))
            general[i][3] += ((ocioCamiones / 60) * 100)
        general[i][2] /= 60
        general[i][4] /= 60
        general[i][3] /= 60
        general[i][5] =  general[i][1] +  general[i][2] +  general[i][3] + general[i][4]

        general[i][2] = f"{general[i][2]:.5f}"
        general[i][3] = f"{general[i][3]:.5f}"
        general[i][4] = f"{general[i][4]:.5f}"
        general[i][5] = f"{general[i][5]:.5f}"

        #Agregar datos a la tabla de cada equipo
        if i == 0:
            datos3=datos
        elif i == 1:
            datos4=datos
        elif i == 2:
            datos5=datos
        else:
            datos6=datos
    menuSimulacion()
                
            
def menu():
    limpiar_frame(window)
    frameInicio = Frame(window, width=640, height=360)
    frameInicio.config(background='pink')
    frameInicio.pack(fill='both', expand=True)

    generarBtn = Label(frameInicio, text='\n\n\n\n\n\n\nProyecto Final Simulasao\nPura Gente del Coach Moy!!')
    generarBtn.config(bg='Pink',font=('Arial', 15))
    generarBtn.pack(padx=5, pady=20)

    botonGenerar = Button(frameInicio, text='Simular', command=generarDatos)
    botonGenerar.config(font=10, width=20, height=2)
    botonGenerar.pack(pady=10)

    botonSalir = Button(frameInicio, text='Salir', command=window.quit)
    botonSalir.config(font=10, width=20, height=2)
    botonSalir.pack(pady=10)

window = Tk()
window.geometry('900x600')
window.resizable(False, False)
window.title('Simulasao')
menu()
window.mainloop()