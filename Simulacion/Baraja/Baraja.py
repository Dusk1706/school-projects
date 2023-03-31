from tkinter import *
from tkinter import ttk
from tkinter import messagebox
import random
import matplotlib.pyplot as plt
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg
from PIL import Image, ImageTk
ruta_imagen = "Baraja/imagenes/carta{}.png"
etiqueta = ["Jugador 1", "Jugador 2", "Jugador 3", "Jugador 4"]
imgFinalVertical = None
imgFinalHorizontal = None
imgCartasV = []
imgCartasH = []
puntosFinales=[0]*4
def limpiarFrame(frame):
    for widgets in frame.winfo_children():
        widgets.pack_forget()

def comprobarEntry(num,acelerar):
    if num.isdigit():
        if acelerar:
            juego(int(num),0,0)
        else:
            juego(int(num),200,500)
    else:
        messagebox.showerror("Error", "Solo se permiten numeros")

def juego(numJuegos,vel1,vel2):
    datos=[]
    for i in range(1,numJuegos+1):
        datos.append(baraja(vel1,vel2))
    limpiarFrame(window)
    frameJuego = Frame(window)
    frameJuego.config(background='pink')
    frameJuego.pack(fill='both', expand=True)
    
    #Texto Ganadores
    maximo=max(puntosFinales)
    texto="Ganadores: "
    texto2="Puntaje: "
    for i in range(4):
        texto2+="[J"+str(i+1)+" = "+str(puntosFinales[i])+"] "
        if puntosFinales[i]==maximo:
            texto+="["+str(etiqueta[i])+"] "
    labelGanadores=Label(frameJuego,text=texto)
    labelGanadores.config(bg='Pink', font=('Arial', 25))
    labelGanadores.pack(padx=5, pady=5)
    
    labelPuntaje=Label(frameJuego,text=texto2)
    labelPuntaje.config(bg='Pink', font=('Arial', 25))
    labelPuntaje.pack(padx=5)
    
    #Frame Tabla y Grafica 
    frameTabla = Frame(frameJuego)
    frameTabla.config(background='pink')
    frameTabla.pack(fill='both', expand=True)
    tabla = ttk.Treeview(frameTabla, columns=("n","p1", "p2", "p3", "p4", "g"),show='headings')
    tabla.heading("n", text="Juego")
    tabla.heading("p1", text="Jugador 1")
    tabla.heading("p2", text="Jugador 2")
    tabla.heading("p3", text="Jugador 3")
    tabla.heading("p4", text="Jugador 4")
    tabla.heading("g", text="Ganadores")
    
    tabla.column("n",anchor=CENTER,width=1)
    tabla.column("p1",anchor=CENTER,width=1)
    tabla.column("p2",anchor=CENTER,width=1)
    tabla.column("p3",anchor=CENTER,width=1)
    tabla.column("p4",anchor=CENTER,width=1)
    tabla.column("g",anchor=CENTER,width=1)
    
    scrollbar = ttk.Scrollbar(tabla, orient=VERTICAL, command=tabla.yview)
    tabla.config(yscrollcommand=scrollbar.set)
    scrollbar.pack(side=RIGHT, fill=Y)
    
    indice=1
    for juego in datos:
        p1 = juego[0][0]
        p2 = juego[0][1]
        p3 = juego[0][2]
        p4 = juego[0][3]
        tabla.insert("", "end", values=(indice,p1, p2, p3, p4, juego[1]))
        indice+=1
    tabla.pack(fill='x', ipady=50)
        
    fig, ax = plt.subplots()
    ax.pie(puntosFinales, labels=etiqueta, autopct='%1.1f%%')
    ax.axis('equal')  
    
    lienzo = FigureCanvasTkAgg(fig, master=frameTabla)
    lienzo.draw()
    

    lienzo.get_tk_widget().pack(side=TOP, fill=BOTH, expand=1)

def baraja(velocidad1,velocidad2):
    limpiarFrame(window)
    framePartida = Frame(window)
    framePartida.config(background='pink')
    framePartida.pack(fill='both', expand=True)
    
    canvas = Canvas(framePartida, width=1100, height=1000)
    canvas.pack(fill='both',expand=True)

    imageVertical = Image.open("Baraja/Imagenes/CartaDetras.png")
    imageHorizontal = Image.open("Baraja/Imagenes/CartaDetras.png").transpose(Image.ROTATE_90)

    newSize1 = (int(imageHorizontal.width/5), int(imageHorizontal.height/5))
    resizeHorizontal = imageHorizontal.resize(newSize1)
    global imgFinalHorizontal
    imgFinalHorizontal = ImageTk.PhotoImage(resizeHorizontal)

    newSize2 = (int(imageVertical.width/5), int(imageVertical.height/5))
    resizeVertical = imageVertical.resize(newSize2)
    global imgFinalVertical
    imgFinalVertical = ImageTk.PhotoImage(resizeVertical)

    y=100
    x=235
    cartasFondo = []
    
    for i in range (10):
        cartasFondo.append(canvas.create_image(100, y, anchor='nw', image=imgFinalHorizontal))
        cartasFondo.append(canvas.create_image(950, y, anchor='nw', image=imgFinalHorizontal))
        cartasFondo.append(canvas.create_image(x, 100, anchor='nw', image=imgFinalVertical))
        cartasFondo.append(canvas.create_image(x, 620, anchor='nw', image=imgFinalVertical))
        canvas.after(velocidad1)
        canvas.update()
        y+=60
        x+=65

    jugadores = [[], [], [], []]
    cartas = []

    for i in range(4):
        for j in range(10):
            jugadores[i].append(generarCarta(cartas))

    global imgCartasH
    global imgCartasV

    puntosJugadores = [0] * 4
    
    for i in range(10):
        imgJ1 = imgCartasH[jugadores[0][i] - 1]
        imgJ2 = imgCartasV[jugadores[1][i] - 1]
        imgJ3 = imgCartasH[jugadores[2][i] - 1]
        imgJ4 = imgCartasV[jugadores[3][i] - 1]

        canvas.create_image(240, 335, anchor='nw', image=imgJ1)
        canvas.create_image(500, 430, anchor='nw', image=imgJ2)
        canvas.create_image(700, 335, anchor='nw', image=imgJ3)
        canvas.create_image(500, 185, anchor='nw', image=imgJ4)

        canvas.delete(cartasFondo[i * 4])
        canvas.delete(cartasFondo[i * 4 + 1])
        canvas.delete(cartasFondo[i * 4 + 2])
        canvas.delete(cartasFondo[i * 4 + 3])

        canvas.after(velocidad2)
        canvas.update()

        ganador = -1
        palo = 50
        puntos = 0

        for j in range(4):
            px = ((jugadores[j][i] - 1) % 10) + 1
            if px == 1:
                px = 11
            
            if px > puntos:
                ganador = j
                palo = (jugadores[j][i] - 1) // 10
                puntos = px
            elif px == puntos:
                if (jugadores[j][i] - 1) // 10 < palo:
                    ganador = j
                    palo = (jugadores[j][i] - 1) // 10
                    puntos = px
        textWinner = canvas.create_text(550, 50, text="Gana el jugador " + str(ganador + 1), font=('Arial', 30))
        puntosJugadores[ganador] += 1

        canvas.after(velocidad2)
        canvas.update()
        canvas.delete(textWinner)
        canvas.after(velocidad2)
        canvas.update()
        
    ganadores = [0] * 4
    #El maximo de puntosJugadores
    maximo = max(puntosJugadores)
    for i in range(4):
        if puntosJugadores[i] == maximo:
            ganadores[i] += 1
            puntosFinales[i]+=1
    ganadoresFinal=[]        
    maximo = max(ganadores)
    for i in range(4):
       if ganadores[i] == maximo:
            ganadoresFinal.append(i+1)
    return [puntosJugadores,ganadoresFinal]
    
    
    
def generarCarta(cartas):
    ranPalo=random.uniform(0,1)
    ranNumero=random.uniform(0 , 1)
    numero=0
    tipo=""
    if ranPalo<= 1/4:
        tipo=0
    elif ranPalo<=2/4:
        tipo=10
    elif ranPalo<=3/4:
        tipo=20
    else:
        tipo=30
        
    if ranNumero <= 1/10:
        numero = 1
    elif ranNumero <= 2/10:
        numero = 2
    elif ranNumero <= 3/10:
        numero = 3
    elif ranNumero <= 4/10:
        numero = 4
    elif ranNumero <= 5/10:
        numero = 5
    elif ranNumero <= 6/10:
        numero = 6
    elif ranNumero <= 7/10:
        numero = 7
    elif ranNumero <= 8/10:
        numero = 8
    elif ranNumero <= 9/10:
        numero = 9
    else:
        numero = 10
        
    if (numero + tipo) in cartas:
        return generarCarta(cartas)
    else:
        cartas.append(numero + tipo)
        return (numero + tipo)

def menu():
    limpiarFrame(window)
    frameMenu = Frame(window, width=1000, height=1000)
    frameMenu.config(background='pink')
    frameMenu.pack(fill='both', expand=True)

    labelTitle = Label(frameMenu, text='Baraja', font=('Arial', 35))
    labelTitle.config(bg='Pink')
    labelTitle.pack(padx=5, pady=5)

    labelEntry = Label(frameMenu, text='Ingrese la cantidad de juegos:')
    labelEntry.config(bg='Pink', font=('Arial', 25))
    labelEntry.pack(padx=5, pady=5)

    juegoAcelerado = BooleanVar()
    checkButton = Checkbutton(frameMenu, text="Juego Acelerado", variable=juegoAcelerado)
    checkButton.config(bg='Pink')
    checkButton.pack()
    
    entry = Entry(frameMenu, font=15, width=20)
    entry.pack(padx=5, pady=5)

    botonEnviar = Button(frameMenu, text='Iniciar Juego')
    botonEnviar.config(font=10, width=20, height=2, command=lambda: [
                       comprobarEntry(entry.get(),juegoAcelerado.get())])
    botonEnviar.pack(pady=10)

    botonSalir = Button(frameMenu, text='Salir', command=window.quit)
    botonSalir.config(font=10, width=20, height=2)
    botonSalir.pack(pady=10)


window = Tk()
window.geometry('1100x750')
window.resizable(True, True)
window.title('Baraja')

for i in range(0, 40):
    imgAx = Image.open(ruta_imagen.format(i + 1))
    imgAx2 = imgAx.transpose(Image.ROTATE_90)
    imgAx = imgAx.resize((int(imgAx.width/2), int(imgAx.height/2)))
    imgAx2 = imgAx2.resize((int(imgAx2.width/2), int(imgAx2.height/2)))
    imgCartasV.append(ImageTk.PhotoImage(imgAx))
    imgCartasH.append(ImageTk.PhotoImage(imgAx2))

menu()
window.mainloop()