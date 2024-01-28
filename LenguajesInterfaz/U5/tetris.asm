.MODEL SMALL

dibujarFila Macro x,y,z,color  ;x - fila, y - inicio columna, z - fin columna
    Local L1
    MOV AH, 0CH
    MOV AL, color
    MOV CX, y
    MOV DX, x
L1: INT 10h
    INC CX
    CMP CX, z
    JL L1
EndM

dibujarColumna Macro x,y,z,color ;x - columna, y - inicio fila, z - fin fila
    Local L2
    MOV AH, 0CH
    MOV AL, color
    MOV CX, x
    MOV DX, y
L2: INT 10h
    INC DX
    CMP DX, z
    JL L2
EndM

mostrarString Macro x,fila,columna,longitud,color
    push ax
    push bx

    MOV AX, @DATA
    MOV ES, AX

    MOV AH, 13H ; Escribir el string
    MOV AL, 0H
    XOR BH,BH
    mov bl,color

    MOV BP, OFFSET x ; direccion del string
    MOV CX, longitud ; longitud del string
    MOV DH, fila ; fila del string
    MOV DL, columna ; columna del string
    INT 10H

    pop bx
    pop ax

EndM

dibujarBloque Macro inicioFila, finFila, inicioCol, finCol, colorBloque
   Local filaSigue

    MOV DX, inicioFila

    filaSigue:

    dibujarFila  DX, inicioCol, finCol,colorBloque
         INC DX
         CMP DX, finFila
         JLE filaSigue

    EndM


dibujarBloqueCompleto Macro bloqueNombre, color, comparar
    Local dibuja


    MOV BX,0

    dibuja:
    dibujarBloque [bloqueNombre+BX],[bloqueNombre+BX+2],[bloqueNombre+BX+4],[bloqueNombre+BX+6],color
        ADD BX,8
        CMP BX,comparar
        JLE dibuja

EndM

actualizarBloque Macro bloqueNombre, bloqueActualizar
    local ciclo

    push ax
    push bx
    push cx
    mov cx,16
    mov bx,0

    ciclo:
       mov ax,bloqueNombre[bx]
       mov bloqueActualizar[bx],ax
       add bx,2
       loop ciclo

    pop cx
    pop bx
    pop ax
EndM

modificarElementoFila Macro bloqueNombre, auxi, numeroBloques
    local ciclo2,salirAux

    push ax
    push bx
    PUSH CX
    PUSH DX

    mov cx,numeroBloques
    mov bx,0

    ciclo2:
        xor ax,ax
        xor dx,dx

        MOV ax,bloqueNombre[bx]
        add ax,auxi
        MOV bloqueNombre[bx],ax

        add bx,2

        MOV dx,bloqueNombre[bx]
        add dx,auxi
        MOV bloqueNombre[bx],dx

        add bx,6

        loop ciclo2


    salirAux:
        pop dx
        pop cx
        pop bx
        pop ax

EndM

cambiarElementosColD Macro bloqueNombre, auxi, comparar
    local salirAux2,regresa

   push ax
   push bx
   PUSH CX
   PUSH DX

    mov bx,4

    regresa:

   cicloAux3:
        MOV cx,bloqueNombre[bx]
        add cx,auxi
        cmp cx,380
        jge salirAux2
        MOV bloqueNombre[bx],cx

        add bx,2
        MOV dx,bloqueNombre[bx]

        add dx,auxi
        MOV bloqueNombre[bx],dx

        add bx,6
        xor cx,cx
        xor dx,dx

        cmp bx,comparar
        jle regresa

    salirAux2:
        pop dx
        pop cx
        pop bx
        pop ax

    EndM


cambiarElementosColA Macro bloqueNombre, auxi, comparar
    local salirAux2,regresa

        push ax
        push bx
        PUSH CX
        PUSH DX


    mov bx,4

    regresa:


        MOV cx,bloqueNombre[bx]
        sub cx,auxi
        cmp cx,220
        jle salirAux2
        MOV bloqueNombre[bx],cx

        add bx,2
        MOV dx,bloqueNombre[bx]
        sub dx,auxi
        MOV bloqueNombre[bx],dx


        add bx,6
        xor cx,cx
        xor dx,dx

        cmp bx,comparar
        jle regresa


    salirAux2:
            pop dx
            pop cx
            pop bx
            pop ax


EndM

tiempoDelay Macro bloqueNombre
    Local td,td1,td2,td3,td4,td5,td6,td7,td8,td9,td10,td11,retornar,show
    push bx
    push cx
    push dx

    td:
        CMP tiempoFlag, 1
        JNE td
        MOV tiempoFlag, 0

        CALL moverBloque

        mov bx,bloqueNombre[16]
        cmp bX,158
        jg retornar


    td2:
        CMP tiempoFlag, 1
        JNE td2
        MOV tiempoFlag, 0
    td3:
        CMP tiempoFlag,1
        JNE td3
        MOV tiempoFlag,0
    td4:
        CMP tiempoFlag,1
        JNE td4
        MOV tiempoFlag,0
    td5:
        CMP tiempoFlag,1
        JNE td5
        MOV tiempoFlag,0
    td6:
        CMP tiempoFlag,1
        JNE td6
        MOV tiempoFlag,0
    td7:
        CMP tiempoFlag,1
        JNE td7
        MOV tiempoFlag,0
    td8:
        CMP tiempoFlag,1
        JNE td8
        MOV tiempoFlag,0
    td9:
        CMP tiempoFlag,1
        JNE td9
        MOV tiempoFlag,0
    td10:
        CMP tiempoFlag,1
        JNE td10
        MOV tiempoFlag,0
    td11:
        CMP tiempoFlag,1
        JNE td11
        MOV tiempoFlag,0

      JMP td


    retornar:
    pop dx
    pop cx
    pop bx
EndM

AX_BX_CX_DX MACRO
    xor ax, ax
    xor bx, bx
    xor cx, cx
    xor dx, dx
ENDM



.Stack 100h
.DATA
    txtTetris           db "TETRIS$"
    txtPuntuacion       db "Puntuacion$"
    txtPuntos           db "0000$"
    txtSiguiente        db "Siguiente$",0
    txtIzquierda        db "A - Izquierda$"
    txtDerecha          db "D - Derecha$"
    txtBajarRapido      db "Espacio - Bajar Rapido$"
    txtSalir            db "ESC - SALIR$"
    txtJuegoTerminado   db "Juego Terminado!$"

    new_timer_vec       dw ?,?
    old_timer_vec       dw ?,?
    a1                  dw ?
    a2                  dw ?
    b1                  dw ?
    b2                  dw ?
    score               dw ?
    color               db 4
    ENTERO_FIN          db 0
    siguiente_color          db 9
    linea               dw 0
    tiempoFlag          db 0
    bloqueActual        dw 0
    sigBloque           dw 0

    horizontal          dw 51,56,290,300
                        dw 51,56,300,310
                        dw 51,56,310,320
                        dw 0,0,0,0

    next_horizontal     dw 71,76,430,440
                        dw 71,76,440,450
                        dw 71,76,450,460
                        dw 0,0,0,0

    L_shape             dw 51,56,290,300
                        dw 51,56,300,310
                        dw 51,56,310,320
                        dw 45,50,290,300

    next_L_shape        dw 71,76,430,440
                        dw 71,76,440,450
                        dw 71,76,450,460
                        dw 65,70,430,440

    right_L             dw 51,56,290,300
                        dw 51,56,300,310
                        dw 51,56,310,320
                        dw 45,50,310,320

    piezaSigLInvertida        dw 71,76,430,440
                        dw 71,76,440,450
                        dw 71,76,450,460
                        dw 65,70,450,460

    piezaActual        dw 0,0,0,0
                        dw 0,0,0,0
                        dw 0,0,0,0
                        dw 0,0,0,0

    piezaSig          dw 0,0,0,0
                        dw 0,0,0,0
                        dw 0,0,0,0
                        dw 0,0,0,0

    piezaRandom         dw 0,0,0,0
                        dw 0,0,0,0
                        dw 0,0,0,0
                        dw 0,0,0,0
.CODE

MAIN PROC
   MOV AX, @DATA
   MOV DS, AX

   mov AH, 0
   MOV AL, 0eh
   int 10h

   ;Color del fondo
   mov ah,0bh
   mov bh,0
   mov bl,10
   int 10h


    call pintarPantalla

    ; set up timer interrupt vector
    MOV new_timer_vec, offset tiempoContador
    MOV new_timer_vec+2, CS
    MOV AL, 1CH; interrupt type
    LEA DI, old_timer_vec
    LEA SI, new_timer_vec
    CALL interrupciones

    mov score,0

cicloInicio:
        inc [bloqueActual]
        cmp [bloqueActual],3
        jle continuarJuego

        mov [bloqueActual],1

        continuarJuego:

            call generarBloque

            actualizarBloque piezaRandom, piezaActual

            call generarSigPieza
            call mostrarSigPieza

            tiempoDelay piezaActual

            call INICIALIZA_AXBXCXDX
            call mostrarPuntuacion
            call juegoTerminadoProc
            cmp ENTERO_FIN,23
            je JUEGO_TERMINADO
            add color,5
            add siguiente_color,5
            cmp color,14
            jg auux1

            cmp siguiente_color,14
            jg auux2

            jmp cicloInicio
auux1:
        mov color,4
        jmp cicloInicio

auux2:
        mov siguiente_color,4
        jmp cicloInicio
JUEGO_TERMINADO:
    mov ah,0
    mov al,13h
    int 10h

    mov ah,0bh
    mov bh,0
    mov bl,10
    int 10h

    mostrarString txtJuegoTerminado ,10,15,15,12

MAIN ENDP

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

pintarPantalla PROC near
    dibujarPantallaCuadroJuego:
        ;Lado arriba
        dibujarFila 42,228,381,5
        ;Lado abajo
        dibujarFila 165,228,381,5
        ;Lado izquierdo
        dibujarColumna 228,42,165,5
        ;Lado derecho
        dibujarColumna 381,42,165,5

    dibujarPantallaSigPieza:
        ;Lado arriba
        dibujarFila 50,400,487,5
        ;Lado izquierdo
        dibujarColumna 400,50,90,5
        ;Lado derecho
        dibujarColumna 487,50,90,5
        ;Lado abajo
        dibujarFila 90,400,487,5


    dibujarTextos:
        ;mostrarString X, Y, longitud, color
        ;X MENOR = Arriba, Y MENOR = Izquierda
        mostrarString txtIzquierda,10,10,13,9
        mostrarString txtDerecha,12,10,11,12
        mostrarString txtBajarRapido,14,3,22,8
        mostrarString txtSalir,16,10,11,12
        mostrarString txtTetris,3,35,6,5
        mostrarString txtSiguiente,12,51,9,5
        mostrarString txtPuntuacion,4,6,10,9
        mostrarString txtPuntos,4,17,4,8
        ret
pintarPantalla endp


interrupciones Proc
    ;guardar viejo vector
    MOV AH, 35h     ; obtener vector
    INT 21h
    MOV [DI], BX
    MOV [DI+2], ES

    ; nuevo vector
    MOV DX, [SI]
    PUSH DS
    MOV DS, [SI+2]
    MOV AH, 25h
    INT 21h
    POP DS
    RET
interrupciones EndP


tiempoContador Proc
    PUSH DS
    PUSH AX

    MOV AX, Seg tiempoFlag
    MOV DS, AX
    MOV tiempoFlag, 1
salirAux:
    POP AX
    POP DS

    IRET
tiempoContador EndP

moverBloque Proc
    mov ah, 1
    int 16h
    jz auxx1
    mov ah, 0   ; Entrada tecla
    int 16h
    cmp al,97 ; Compara si es una a
    je teclaA
    cmp al,100 ; Compara si es una d
    je aux2
    cmp al, 32 ;Compara si es espacio
    je aaux
    cmp al, 27 ;Compara si es escape
    je FINALIZAR
    FINALIZAR:
        mov ah, 4ch
        int 21h

    auxx1: jmp aux5

    teclaA:
       xor cx,cx
       xor dx,dx
       mov cx,piezaActual[20]
       sub cx,45
       mov dx,piezaActual[16]
    fix1:
       jmp fix3
    aux2:
       jmp teclaD
    aaux:
        jmp dour
    fix3:
       mov ah,0dh
       int 10h
       cmp al,09h
       je aux5
       cmp al,0eh
       je aux5
       cmp al,04h
       je aux5
       add dx,10
       int 10h
       cmp al,0eh
       je aux5
       cmp al,09h
       je aux5
       cmp al,04h
       je aux5

       dibujarBloqueCompleto piezaActual,15 , 24
       cambiarElementosColA piezaActual,30,30

    aux5:
        jmp exittt

    dour:
         jmp exitttt

    teclaD:
       xor cx,cx
       xor dx,dx
       mov cx,piezaActual[20]
       add cx,15
       mov dx,piezaActual[16]

       mov ah,0dh

       int 10h
       cmp al,09h
       je exittt
       cmp al,0eh
       je exittt
       cmp al,04h
       je exittt

       add dx,10
       int 10h
       cmp al,09h
       je exittt
       cmp al,0eh
       je exittt
       cmp al,04h
       je exittt


    dibujarBloqueCompleto piezaActual,15 , 30
    cambiarElementosColD piezaActual,30,30
        jmp exittt

exittt:
      dibujarBloqueCompleto piezaActual,15, 24
      jmp tiempoTest


 exitttt:
 dibujarBloqueCompleto piezaActual,15, 24
       xor cx,cx
       xor dx,dx
       mov cx,piezaActual[4]
       inc cx
       mov dx,piezaActual[16]

        xor ax,ax
        mov ah,0dh

       cmp dx,140
       jg tiempoTest
       add dx,24

       int 10h
       cmp al,09h
       je tiempoTest
       cmp al,0eh
       je tiempoTest
       cmp al,04h
       je tiempoTest
       add cx,20
       int 10h
       cmp al,09h
       je tiempoTest
       cmp al,0eh
       je tiempoTest
       cmp al,04h
       je tiempoTest
       modificarElementoFila piezaActual,12, 4
    jmp tiempoTest

tiempoTest:
    xor ax,ax
    CMP tiempoFlag, 1
    JNE tiempoTest

    modificarElementoFila piezaActual,6 ,16
    dibujarBloqueCompleto piezaActual,color,24
       xor cx,cx
       xor dx,dx
       mov cx,piezaActual[4]
       inc cx
       mov dx,piezaActual[16]
       add dx,10
       mov ah,0dh

       int 10h
       cmp al,09h
       je exits2
       cmp al,0eh
       je exits2
       cmp al,04h
       je exits2

       add cx,20

       int 10h
       cmp al,09h
       je exits2
       cmp al,0eh
       je exits2
       cmp al,04h
       je exits2

    MOV tiempoFlag, 0
    exits:
        RET

    exits2:

    mov piezaActual[16],170
    RET
moverBloque EndP


INICIALIZA_AXBXCXDX Proc
   AX_BX_CX_DX

lineaPantalla:
    mov ah, 0dh   ; Interrupción de video para escribir píxeles en modo gráfico
    mov a1, 164   ; Coordena X1 para el primer punto de la línea
    mov b1, 56    ; Coordena Y1 para el primer punto de la línea
    mov a2, 230   ; Coordena X2 para el segundo punto de la línea
    mov b2, 240   ; Coordena Y2 para el segundo punto de la línea

    mov linea, 170

khuru:
      mov cx,235
      sub linea,6
      mov dx,linea
      mov a1,dx
dhuru:
    add cx,10
    cmp cx,380
    jg cambiarPuntuacion
    xor ax,ax
    mov ah,0dh
    int 10h
    cmp al,09h
    je dhuru
    cmp al,0eh
    je dhuru
    cmp al,04h
    je dhuru
    jmp hh3

cambiarPuntuacion:
    add score,10
hh1:
    mov a2,220
    sub a1,6
hh2:
    cmp a1,55
    jl hh3

    add a2,10
    cmp a2,370
    jg hh1

    mov bx,a1
    add bx,6
    mov b1,bx

    mov bx,a2
    add bx,10
    mov b2,bx
    AX_BX_CX_DX
    mov cx,a2
    inc cx

    mov dx,a1
    sub dx,4
    mov ah,0dh
    int 10h
    dibujarBloque a1,b1,a2,b2,al
    jmp hh2

hh3:
    mov a2,220
    cmp linea,60
    jl fillhoynai
    jmp khuru
fillhoynai:
    RET
INICIALIZA_AXBXCXDX Endp


mostrarPuntuacion proc
    push ax
    push bx
    push cx
    push dx
    mov cx,10d
    xor bx,bx
    mov bx,3
    mov ax,score
    convertirDigitos:
        xor dx,dx
        div cx
        or dl,30h
        mov txtPuntos[bx],dl
        dec bx
        or ax,ax
        jne convertirDigitos
    mostrarString txtPuntos,4,17,4,8
    pop dx
    pop cx
    pop bx
    pop ax
    ret
mostrarPuntuacion Endp


generarBloque proc
    cmp [bloqueActual],1
    je pieza_Horizontal

    cmp [bloqueActual],2
    je pieza_L

    cmp [bloqueActual],3
    je pieza_L_Invertida
    pieza_Horizontal:
            actualizarBloque horizontal, piezaRandom
            mov [sigBloque],2
            ret
    pieza_L:
            actualizarBloque L_shape, piezaRandom
            mov [sigBloque],3
            ret
    pieza_L_Invertida:
             actualizarBloque right_L, piezaRandom
             mov [sigBloque],1
             ret
    ret
generarBloque endp


generarSigPieza proc
    cmp [sigBloque],1
    je piezaHorizontal

    cmp [sigBloque],2
    je piezaL

    cmp [sigBloque],3
    je piezaLInvertida
    piezaHorizontal:
            actualizarBloque next_horizontal, piezaSig
            ret
    piezaL:
           actualizarBloque next_L_shape, piezaSig
           ret
    piezaLInvertida:
             actualizarBloque piezaSigLInvertida, piezaSig
             ret
    ret
generarSigPieza endp


mostrarSigPieza proc
    push bx
    push cx
    push dx

    dibujarBloque 61,89,401,470,0Fh
    dibujarBloqueCompleto piezaSig, [siguiente_color], 24
    pop dx
    pop cx
    pop bx
    ret

mostrarSigPieza endp

juegoTerminadoProc proc
    xor ax,ax
    xor cx,cx
    xor dx,dx
    mov dx,59
    mov cx,297
    mov ah,0dh
    int 10h
    cmp al,09h
    je aux1
    cmp al,04h
    je aux1
    cmp al,0eh
    je aux1


    retornar: RET

    aux1:


   mov ah,0bh
   mov bh,1
   mov bl,13
   int 10h
   mov ENTERO_FIN,23
   RET
juegoTerminadoProc endp
End main

