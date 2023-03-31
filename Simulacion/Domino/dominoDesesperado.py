import tkinter as tk
from tkinter import font
from tkinter import messagebox
from tkinter import ttk
import random
from matplotlib.figure import Figure
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg

Fichas = [(0, 0, 50), (0, 1, 1), (0, 2, 2), (0, 3, 3), (0, 4, 4), (0, 5, 5), (0, 6, 6), (1, 1, 41), (1, 2, 3), (1, 3, 4), (1, 4, 5), (1, 5, 6), (1, 6, 7), (2, 2, 42), (2, 3, 5), (2, 4, 6), (2, 5, 7), (2, 6, 8), (3, 3, 43), (3, 4, 7), (3, 5, 8), (3, 6, 9), (4, 4, 44), (4, 5, 9), (4, 6, 10), (5, 5, 45), (5, 6, 11), (6, 6, 46)]

fichasP = [[0] * 7, [0] * 7, [0] * 7, [0] * 7]
fichasU = [[True] * 7, [True] * 7, [True] * 7, [True] * 7]
ruta_imagen = "images/ficha{}.png"

datos = [0] * 8
victorias = [0] * 4
etiqueta = ["Jugador 1", "Jugador 2", "Jugador 3", "Jugador 4"]
num_Juegos = 1

def cambiar_panel(panel):
    if panel == "Inicio":
        panel_Inicio.pack(side = "left", expand = True, fill = "both")
        panel_Juegos.pack_forget()
        panel_Tabla.pack_forget()
        panel_grafico.pack_forget()
    elif panel == "Juegos":
        panel_Inicio.pack_forget()
        panel_Juegos.pack(side = "left", expand = True, fill = "both")
        panel_Tabla.pack_forget()
        panel_grafico.pack_forget()
    elif panel == "Tabla":
        panel_Inicio.pack_forget()
        panel_Juegos.pack_forget()
        panel_Tabla.pack(side = "right", expand = True, fill = "both")
        panel_grafico.pack_forget()
    else:
        panel_Inicio.pack_forget()
        panel_Juegos.pack_forget()
        panel_Tabla.pack_forget()
        panel_grafico.pack(side = "right", expand = True, fill = "both")


def comprobar_entrada(num):
    if num.isdigit():
        simular(num)
    else:
        messagebox.showerror("Error", "Solo se permiten numeros")


def simular(num):

    if int(num) <= 0:
        menu_bar.entryconfig(3, state="normal")
        return
    canvas.delete("all")
    canvas.create_image(0, 0, anchor="nw", image=fondo)
    cambiar_panel("Juegos")
    menu_bar.entryconfig(2, state="normal")

    repartir_fichas()
        
    for j in range(4):
        if fichasP[j][6] == 27:
            turno = j
            break

    fp = [0] * 4
    winner = jugar(turno, True, fp, 0, 6, 6)
    if winner == 0:
        label = tk.Label(canvas, text="Gano el jugador 1", font= ("Chilanka", 20))
        canvas.create_window(350, 300, anchor='center', window=label)
        print("Gano el jugador 1")
    elif winner == 1:
        label = tk.Label(canvas, text="Gano el jugador 2", font = ("Chilanka", 20))
        canvas.create_window(350, 300, anchor='center', window=label)
        print("Gano el jugador 2")
    elif winner == 2:
        label = tk.Label(canvas, text="Gano el jugador 3", font = ("Chilanka", 20))
        canvas.create_window(350, 300, anchor='center', window=label)
        print("Gano el jugador 3")
    elif winner == 3:
        label = tk.Label(canvas, text="Gano el jugador 4", font = ("Chilanka", 20))
        canvas.create_window(350, 300, anchor='center', window=label)
        print("Gano el jugador 4")
    
    canvas.after(2000, lambda: simular(int(num) - 1))
    global num_Juegos
    num_Juegos += 1


def repartir_fichas():
    f1 = f2 = f3 = f4 = 0
    z = 0
    while(z < 28):
        num = random.random()
        if num < 0.25 and f1 < 7:
            fichasP[0][f1] = z
            fichasU[0][f1] = True
            z += 1
            f1 += 1
        elif 0.25 < num < 0.5 and f2 < 7:
            fichasP[1][f2] = z
            fichasU[1][f2] = True
            z += 1
            f2 += 1
        elif 0.5 < num < 0.75 and f3 < 7:
            fichasP[2][f3] = z
            fichasU[2][f3] = True
            z += 1
            f3 += 1
        elif 0.75 < num and f4 < 7:
            fichasP[3][f4] = z
            fichasU[3][f4] = True
            z += 1
            f4 += 1
    
    #Repartir graficamente las fichas
    dar_ficha(0, 200, 10, 30, 0, 0)
    dar_ficha(0, 640, 100, 0, 50, 1)
    dar_ficha(0, 200, 520, 30, 0, 2)
    dar_ficha(0, 10, 100, 0, 50, 3)


def dar_ficha(n, x, y, xi, yi, player):
    if n == 7:
        return
    if xi == 0:
        es = n * 5
        Y = yi * n
        canvas.create_image(x, y + Y + es, anchor = "nw", image = img_fichas[fichasP[player][n]])
        canvas.update()
        canvas.after(150, lambda: dar_ficha(n+1, x, y, xi, yi, player))
    else:
        es = n * 10
        X = xi * n
        canvas.create_image(x + X + es, y, anchor = "nw", image = img_fichas[fichasP[player][n]])
        canvas.update()
        canvas.after(150, lambda: dar_ficha(n+1, x, y, xi, yi, player))

def jugar(turno, start, fp, p, izq, der):
    if start:
        fp[turno] += 1
        fichasU[turno][6] = False #Ficha jugada
        return jugar((turno+3)%4, False, fp, p, izq, der)
    if p == 4:
        return definir_ganador()
    else:
        best = 0
        id = -1
        for i in range(7):
            if fichasU[turno][i]:
                f = fichasP[turno][i]
                if Fichas[f][0] == izq or Fichas[f][1] == izq or Fichas[f][0] == der or Fichas[f][1] == der:
                    if Fichas[f][2] > best:
                        best = Fichas[f][2]
                        id = i
                
        if best == 0:
            return jugar((turno+3)%4, False, fp, p+1, izq, der)
        else:
            f = fichasP[turno][id]
            fichasU[turno][id] = False
            fp[turno] += 1
            if fp[turno] == 7: ##Calcular puntos
                definir_ganador()
                return turno
            if Fichas[f][0] == izq:
                return jugar((turno+3)%4, False, fp, 0, Fichas[f][1], der)
            elif Fichas[f][1] == izq:
                return jugar((turno+3)%4, False, fp, 0, Fichas[f][0], der)
            elif Fichas[f][0] == der:
                return jugar((turno+3)%4, False, fp, 0, izq, Fichas[f][1])
            elif Fichas[f][1] == der:
                return jugar((turno+3)%4, False, fp, 0, izq, Fichas[f][0])
            
    return 1

def definir_ganador():
    win = -1
    loss = -1
    suma = -1
    suma2 = 200
    s = [0] * 4
    m = [0] * 4
    for i in range(4):
        for j in range(7):
            if fichasU[i][j]:
                s[i] += Fichas[fichasP[i][j]][0] + Fichas[fichasP[i][j]][1]
                if fichasP[i][j] == 0:
                    m[i] += 1
                    s[i] += 50
        if m[i] == 0 and s[i] < suma2:
            win = i
            suma2 = s[i]
        if s[i] > suma:
            loss = i
            suma = s[i]
    ##Agregar datos al historial
    datos = [num_Juegos, s[0], s[1], s[2], s[3], win+1 , loss+1]
    victorias[win] += 1
    values = [0.0] * 4
    for i in range(4):
        values[i] = victorias[i]/num_Juegos
    
    for widget in panel_grafico.winfo_children():
        widget.destroy()

    fig = Figure(figsize=(5, 5), dpi=100)
    ax = fig.add_subplot(111)
    ax.pie(values, labels=etiqueta, autopct='%1.1f%%')
    ax.set_title('Porcentajes de eventos')

    grafica = FigureCanvasTkAgg(fig, master=panel_grafico)
    grafica.get_tk_widget().pack(side=tk.LEFT,fill=tk.BOTH,expand=True)
    tabla.insert("", "end", values=datos)

    return win

# Crear una nueva ventana
ventana = tk.Tk()
# Configurar las dimensiones de la ventana
ventana.geometry("700x600")

Font = font.Font(family="Chilanka", size=12, weight="bold")

# Crear un paneles
panel_Inicio = tk.Frame(ventana, bg = "cyan", width = 700, height = 600)
panel_Inicio.pack(side = "left", expand = True, fill = "both")

panel_hist = tk.Frame(ventana, bg = "gray", width = 700, height = 600)
panel_hist.pack(side = "right", expand = True, fill = "both")
panel_hist.pack_forget()

panel_Juegos = tk.Frame(ventana, bg = "black", width = 700, height = 600)
panel_Juegos.pack(side = "left", expand = True, fill = "both")
panel_Juegos.pack_forget()

panel_Tabla = tk.Frame(ventana, bg = "gray", width = 700, height = 600)
panel_Tabla.pack(side = "right", expand = True, fill = "both")
panel_Tabla.pack_forget()

panel_grafico = tk.Frame(ventana, bg = "gray", width = 700, height = 600)
panel_grafico.pack(side = "right", expand = True, fill = "both")
panel_grafico.pack_forget()

# Crear el menu bar
menu_bar = tk.Menu(ventana)
ventana.config(menu=menu_bar)

# Crear un menu
opciones_menu = tk.Menu(menu_bar, tearoff=0)
estadisticas_menu = tk.Menu(menu_bar, tearoff=0)

menu_bar.add_cascade(label="Opciones", font = Font, menu=opciones_menu)
menu_bar.add_command(label="Juegos", font = Font, command=lambda: cambiar_panel("Juegos"))
menu_bar.add_cascade(label="Estadisticas", font = Font, menu=estadisticas_menu)

menu_bar.entryconfig(2, state="disabled")
menu_bar.entryconfig(3, state="disabled")

# Añadir elementos al menu
opciones_menu.add_command(label="Nuevo", font = Font, command=lambda: cambiar_panel("Inicio"))
opciones_menu.add_separator()
opciones_menu.add_command(label="Salir", font = Font, command=ventana.quit)
estadisticas_menu.add_command(label="Tabla", font = Font, command=lambda: cambiar_panel("Tabla"))
estadisticas_menu.add_command(label="Grafico", font = Font, command=lambda: cambiar_panel("Grafico"))

# Crear interfas de inicio

lb_pregunta = tk.Label(panel_Inicio, text="¿Cuantos juegos quieres simular?", font = ("Chilanka", 30), bg = "cyan")
lb_pregunta.place(x=35, y=200)

txt_numS = tk.Entry(panel_Inicio, width=30, font = ("Chilanka", 20))
txt_numS.place(x=120, y=280)

btn_simular = tk.Button(panel_Inicio, text="Simular", font = ("Chilanka", 20), command = lambda: comprobar_entrada(txt_numS.get()))
btn_simular.place(x=290, y=350)

#Fin de la interfaz de inicio

# Crear un canvas dentro de la ventana
canvas = tk.Canvas(panel_Juegos, width=700, height=600)
canvas.pack()

# Agregar imagen de fondo al canvas
fondo = tk.PhotoImage(file="images/fondo.png")
canvas.create_image(-5, -10, anchor="nw", image=fondo)
img_fichas = [fondo] * 28
for i in range(0, 28):
    ruta = ruta_imagen.format(i)
    img_fichas[i] = (tk.PhotoImage(file=ruta))

# Creamos la gráfica circular

#Crear una tabla
tabla = ttk.Treeview(panel_Tabla, columns=(1,2,3,4,5,6,7), show='headings')
tabla.pack(side=tk.LEFT,fill=tk.BOTH, expand=True)

for i in range(1, 8):
    tabla.column(i, width=80, anchor='c')

tabla.heading(1, text='N')
tabla.heading(2, text='J1')
tabla.heading(3, text='J2')
tabla.heading(4, text='J3')
tabla.heading(5, text='J4')
tabla.heading(6, text='Ganador')
tabla.heading(7, text='Perdedor')

scrollbar_tabla = ttk.Scrollbar(panel_Tabla, orient=tk.VERTICAL, command=tabla.yview)
scrollbar_tabla.pack(side=tk.RIGHT, fill=tk.Y)
tabla.configure(yscrollcommand=scrollbar_tabla.set)

#Centrar la ventana
ventana.eval('tk::PlaceWindow %s center' % ventana.winfo_toplevel())
ventana.title("Simulando Domino")
ventana.resizable(0, 0)
# Mostrar la ventana
ventana.mainloop()
