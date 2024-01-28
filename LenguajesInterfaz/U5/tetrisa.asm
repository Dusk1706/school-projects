.model small
.stack 100H
.data
        ; MENU
        TETRIS  db  ' T E T R I S $'
        JUGAR db '(J)ugar$'
        SALIR  db '(S)alir$'
        MSG_ABAJO_MENU db 'Lenguajes de interfaz$'

        ; DISENO DEL JUEGO
        CUADRADO_CHAR equ 00DBh ; ASCII Character
        LATERAL_JOGO  db CUADRADO_CHAR, '          ', CUADRADO_CHAR,'$'
        BASE_JOGO db 12 dup(CUADRADO_CHAR), '$'
        BASE_CAIXA_PECA db 8 dup(CUADRADO_CHAR), '$'
        LATERAL_CAIXA_PECA db CUADRADO_CHAR, '      ', CUADRADO_CHAR,'$'
        SCORE_MSG db 'Score$'

        ; Figuras
        PIEZA_T db ' ',CUADRADO_CHAR,'  $',CUADRADO_CHAR,CUADRADO_CHAR,CUADRADO_CHAR,' $','    $','    $'
        PIEZA_S db ' ',CUADRADO_CHAR,CUADRADO_CHAR,' $',CUADRADO_CHAR,CUADRADO_CHAR,'  $','    $','    $'
        PIEZA_Z db CUADRADO_CHAR,CUADRADO_CHAR,'  $',' ',CUADRADO_CHAR,CUADRADO_CHAR,' $','    $','    $'
        PIEZA_I db '    $',CUADRADO_CHAR,CUADRADO_CHAR,CUADRADO_CHAR,CUADRADO_CHAR,'$','    $','    $'
        PIEZA_O db ' ',CUADRADO_CHAR,CUADRADO_CHAR,' $',' ',CUADRADO_CHAR,CUADRADO_CHAR,' $','    $','    $'
        PIEZA_J db CUADRADO_CHAR,'   $',CUADRADO_CHAR,CUADRADO_CHAR,CUADRADO_CHAR,' ','$','    $','    $'
        PIEZA_L db '  ',CUADRADO_CHAR,' $',CUADRADO_CHAR,CUADRADO_CHAR,CUADRADO_CHAR,' $','    $','    $'

        ;  PANTALLA FINAL
        GRACIAS    db 'G R A C I A S $P O R   J U G A R$'

        ;  PANTALLA GAME OVER
        GAME    db 'G A M E$'
        OVER    db 'O V E R$'


    ;  ~Variables ~ Juego
    ;------------------------------------
        SCORE_JUEGO dw ?
        PROX_PIEZA_NUM db ?
        CURR_PIEZA_NUM db ?
        CURR_W db ?
        CURR_H db ?
        PIEZA_TEMP db 20 dup(' ')

        HORA_ULTIMA_LECTURA dd 11111111h  ; Hora del último scroll
        DIFERENCIA_TIEMPO  dd 11111111h  ; DIFERENCIA_TEMPO Calcula la diferencia entre la hora actual y la hora del último scroll

    ; Teclas
    ;------------------------------------
        KeyDown             equ 5000h
        KeyLeft             equ 4B00h
        KeyRight            equ 4D00h
        KeySpaceBar         equ 0020h

.code

PushAXBX macro
    push AX
    push BX
endm

PushAXBXCX macro
    PushAXBX
    push CX
endm

PushAXBXCXDX macro
    PushAXBXCX
    push DX
endm

PopAXBX macro
    pop  BX
    pop  AX
endm

PopAXBXCX macro
    pop  CX
    PopAXBX
endm

PopAXBXCXDX macro
    pop  DX
    PopAXBXCX
endm

GENERATE_RANDOM proc ; Return AL
    push BX
    push CX
    push DX

    ; Interrupcion de tiempo para generar valor aleatorio
    mov  AX, 0h
    int  1Ah

    mov  AX, DX ; Mueve el valor generado por la interrupcion a AX. Este valor se dividira
    mov  CX, 7h ; Define el límite del número a ser generado
    mov  DX, 0h ; Define numero inicial
    div  CX     ; Divide el valor de AX por CX
    mov  AX, DX ; Obtiene el valor generado en el resto de la división y lo coloca en AX
    xor  AH, AH ; Elimina posible basura que pueda existir en la parte alta

    pop DX
    pop CX
    pop BX
    ret
endp

CHAR_DISPLAY proc  ;Procedimiento que imprime en la pantalla UN carácter en un color específico (BL)
    PushAXBXCX
    xor  AH, AH
    xor  CX, CX
    mov  BH , 0
    mov  AH, 09h            ;Interrupción 10h para imprimir carácter en la pantalla
    mov  CX, 01h            ;Número de veces que el carácter será mostrado en la pantalla
    int  10h                ;Interrupcion de video 10h
    PopAXBXCX
    ret
endp

;Establece el cursor en una posición X [BL], Y [BH] AH=02h
;BH = Número de página, DH = Fila, DL = Columna.
GOTO_XY proc
    PushAXBXCXDX
    xor AX, AX
    mov AH, 2       ;interrupción 10h para cambiar la posición del cursor
    int 10h         ;interrupcion de video 10h
    popAXBXCXDX
    ret
endp

; SI = offset de pieza
; DH = linea parcial
; DL = columna de piezas
; BL = color de la pieza
IMP_PIEZA_GENERICO proc
  PushAXBXCXDX
  mov CX,4
  IMPRIME_PIEZA:
    call IMP_STRING
    inc DH
    inc SI
    loop IMPRIME_PIEZA
  PopAXBXCXDX
  ret
endp

IMP_PIEZA proc ; al = PIEZA ; ah [0 = Escribe; 1 = apaga]
    PushAXBXCXDX
    cmp  AL, 00d ; Pieza T
    jne  PRUEBA_PIEZA_S
        mov BL, 04h
        mov SI, OFFSET PIEZA_T
        jmp IMPRIME_PIEZA_AHORA
    PRUEBA_PIEZA_S:
    cmp  AL, 01d ; Pieza S
    jne  PRUEBA_PIEZA_Z
        mov BL, 06h
        mov SI, OFFSET PIEZA_S
        jmp IMPRIME_PIEZA_AHORA
    PRUEBA_PIEZA_Z:
    cmp  AL, 02d ; Pieza Z
    jne  PRUEBA_PIEZA_I
        mov BL, 0Eh
        mov SI, OFFSET PIEZA_Z
        jmp IMPRIME_PIEZA_AHORA
    PRUEBA_PIEZA_I:
    cmp  AL, 03d ; Pieza I
    jne  PRUEBA_PIEZA_O
        mov BL, 02h
        mov SI, OFFSET PIEZA_I
        jmp IMPRIME_PIEZA_AHORA
    PRUEBA_PIEZA_O:
    cmp  AL, 04d ; Pieza O
    jne  PRUEBA_PIEZA_J
        mov BL, 03h
        mov SI, OFFSET PIEZA_O
        jmp IMPRIME_PIEZA_AHORA
    PRUEBA_PIEZA_J:
    cmp  AL, 05d ; Pieza j
    jne  PRUEBA_PIEZA_L
        mov BL, 09h
        mov SI, OFFSET PIEZA_J
        jmp IMPRIME_PIEZA_AHORA
    PRUEBA_PIEZA_L:
        mov BL, 01h
        mov SI, OFFSET PIEZA_L
        jmp IMPRIME_PIEZA_AHORA

    IMPRIME_PIEZA_AHORA:
    cmp ah, 1
    jne SALTA_Y_IMPRIME
    mov bl,0
    SALTA_Y_IMPRIME:
    mov AX,0
    call IMP_PIEZA_GENERICO

    PopAXBXCXDX
ret
endp

; Recibe la palabra, posicion y color para imprimir [palabra SI, posx DH, posy DL, color BL]
IMP_STRING proc
    PushAXBXCXDX
    ;mov SI, AX
    ;xor AX, AX
    mov  AL, [SI]       ;Apunta a la direccion en la memoria de la cadena.
    loop_imp_string:
        call GOTO_XY            ;Llama a la macro que establece el cursor [x,y]
        cmp AL, ' '
        je MOSTRAR_CHAR
        call CHAR_DISPLAY    ;Llama a la macro que imprime el caracter coloreado en la pantalla.
        MOSTRAR_CHAR:
        inc SI             ;Incrementar la posicion de memoria para el siguiente caracter.
        inc DL              ;Aumenta la posicion x en la pantalla para imprimir el siguiente caracter.
        mov AL, [SI]       ;Apunta a la direccion en memoria de la cadena.
        cmp AL, '$'       ;Comprueba si has llegado al final de la cadena.
        jne loop_imp_string
    PopAXBXCXDX
    ret
endp

LIMPAR_PANTALLA proc  ; Limpia la pantalla del usuario
    PushAXBXCXDX
    mov AX, 0620h
    mov BX, 0h
    mov CX, 0h
    mov DX, 1998h
    int 10h
    PopAXBXCXDX
    ret
endp

GENERAR_SIG_PIEZA proc
    PushAXBXCXDX
    mov SI, offset PROX_PIEZA_NUM
    mov AL, [SI]
    mov AH, 1h
    mov  DH, 04h
    mov  DL, 1Dh
    call IMP_PIEZA

    call GENERATE_RANDOM
    mov  AH, 0h
    call IMP_PIEZA
    mov SI, offset PROX_PIEZA_NUM
    mov [SI], AL
    PopAXBXCXDX
ret
endp

DELAY proc
    PushAXBXCXDX
    MOV     AL, 0
    MOV     CX, 0FH
    MOV     DX, 4240H
    MOV     AH, 86H
    INT     15H
    PopAXBXCXDX
ret
endp

OBTENER_POSICION_PIEZA proc
    mov SI, offset CURR_W
    mov DH, [SI]
    mov SI, offset CURR_H
    mov DL, [SI]
    mov SI, offset CURR_PIEZA_NUM
    mov AL, [SI]
ret
endp

CAMBIAR_POSICION_ACTUAL proc
    mov SI, offset CURR_W
    mov [SI], DH
    mov SI, offset CURR_H
    mov [SI], DL
ret
endp

ADD_PROX_PIEZA proc
    ; Agrega la siguiente pieza a la pantalla
    PushAXBXCXDX
    mov SI, offset PROX_PIEZA_NUM    ; Busca la dirección en la memoria para almacenar la próxima pieza
    mov AL, [SI]                    ; Toma el valor actual y colócalo en AL
    mov SI, offset CURR_PIEZA_NUM    ; Buscar direccion de memoria para almacenar la parte actual
    mov [SI], AL                    ; Colocar nueva pieza
    mov DL, 10h                     ; Ajustando la posicion x,y
    mov DH, 2h
    call CAMBIAR_POSICION_ACTUAL    ; Actualizar posicion actual
    call IMP_PIEZA                  ; Imprimir pieza
    PopAXBXCXDX
    call GENERAR_SIG_PIEZA         ; Genera pieza para el proximo movimiento.
ret
endp

; Recibe la posición en DX y verifica si va a colisionar; devuelve el resultado en AX
VERIFICA_COLISION proc
    PushAXBXCXDX
    mov CX,DX                       ; Almacena la posición recibida
    call OBTENER_POSICION_PIEZA     ; Obtiene la pieza y la posición actual

    push DX
    mov BX, 20d                     ; Desplazamiento de la pieza
    mul BX                          ; Obtiene la pieza a través del número * desplazamiento
    mov SI, offset PIEZA_T
    xor AH,AH
    add SI, AX                      ; Guarda la dirección de la pieza
    pop DX
    verifica_desclocamento:
        cmp CH, DH                  ; Verifica si el desplazamiento fue hacia abajo
        jne checkBellow
        cmp CL, DL
        jns checkRight              ; Verifica si el desplazamiento fue hacia la izquierda
        jmp checkLeft               ; Verifica si el desplazamiento fue hacia la derecha

    checkBellow:
        ;mov BX, DX
        ;mov DX, CX
        inc DH
        mov CX, 16d                 ; Establece contador

        innerCheckBellow:

            mov AX, [SI]            ; Obtiene el valor de la posición de la pieza
            xor AH,AH

            cmp AX, CUADRADO_CHAR   ; Verifica si la posición de la pieza es un cuadrado
            jne innerBellowNoValue  ; Si no es así, continúa el bucle

            push AX
            add DH,2
            sub DL, 3
            call GOTO_XY            ; Posiciona el cursor en el elemento deseado
            mov AH, 08h             ; Instrucción para lectura del carácter de la pantalla
            int 10H
            mov BX,AX               ; Guarda el valor del píxel leído en BX
            sub DH,2
            add DL, 3
            call CHAR_DISPLAY
            pop AX
            cmp BH, 0h
            je innerBellowNoValue
            xor BH,BH
            cmp BX, CUADRADO_CHAR
            jne ENCONTRO

            innerBellowNoValue:
            inc SI
            inc DL
            cmp [SI], '$'
            jne skipIncLine
            inc DH
            sub DL, 4
            inc SI
            skipIncLine:
        loop innerCheckBellow
        jmp fin_verifica_colision
    checkLeft:

    checkRight:

    ENCONTRO:
        mov SI, offset SCORE_JUEGO
        add [SI], 100d
        call IMP_SCORE


    fin_verifica_colision:

    popAXBXCXDX
ret
endp

ACTUALIZA_TIEMPO proc ;Guarda la hora actual en la variable lastTime
    PushAXBXCXDX
    mov  AX, 0h                     ; Ajuste para obtener el contador de Tempo System-Timer
    int  1Ah                        ; Realizar la interrupcion

    mov  word ptr HORA_ULTIMA_LECTURA, DX      ; DX = Parte de orden inferior del conteo del reloj
    mov  word ptr HORA_ULTIMA_LECTURA + 2, CX  ; CX = parte de orden superior del recuento de relojes
    PopAXBXCXDX
    ret
endp

; Guarda en DIFERENCIA_TIEMPO el tiempo transcurrido entre la hora actual
; y la hora guardada en HORA_ULTIMA_LECTURA
VERIFICA_ULTIMO_TIEMPO proc
    PushAXBXCXDX
    mov  AX, 0h
    int  1Ah
    mov  AX, word ptr HORA_ULTIMA_LECTURA
    sub  DX, AX
    mov  word ptr DIFERENCIA_TIEMPO, DX
    mov  AX, word ptr HORA_ULTIMA_LECTURA + 2
    sbb  CX, AX
    mov  word ptr DIFERENCIA_TIEMPO + 2, CX
    PopAXBXCXDX
    mov  AX, word ptr DIFERENCIA_TIEMPO
    ret
endp

VERIFICA_INPUT proc
    PushAXBXCX
    call ACTUALIZA_TIEMPO
    verifica_nuevamente:
        push AX                 ; Almacena el valor de AX para poder leer desde el teclado
        mov AH, 01h             ; Lee el búfer
        mov AL, 00h
        int 16h                 ; Interrupción de lectura
        jz salir_verifica_input  ; Si no hay nada que leer, salta al final del bucle
        mov ah, 0   ; get       ; Obtiene el valor del teclado
        int 16h

        ; Borra la Pieza Actual
        push AX                 ; Guarda el valor leído
        mov SI, offset CURR_PIEZA_NUM ; Obtiene el número de la pieza actual
        mov AX, [SI]
        mov AH, 1               ; Ajusta para escribir la pieza en negro
        call IMP_PIEZA           ; Imprime la pieza (como está en negro, se borrará de la pantalla)
        pop AX                  ; Saca el valor leído

        ; Desplaza la pieza según el valor leído desde el teclado
        cmp AX, KeyRight        ; Verifica si se presionó la tecla de flecha derecha
        je moveRight
        cmp AX, KeyLeft         ; Verifica si fue la tecla de flecha izquierda
        jne salir_verifica_input ; No leíste un valor válido, por lo tanto, salta otras verificaciones de lectura.

        dec DL                  ; Decrementa Columna
        ;call VERIFICA_COLISAO
        jmp imprime_pieza_verifica
        moveRight:
        inc DL
        ;call VERIFICA_COLISAO


        imprime_pieza_verifica:
        pop AX
        mov AH,0        ; Ajusta parámetro para imprimir pieza colorida
        call IMP_PIEZA   ;Imprime pieza
        call CAMBIAR_POSICION_ACTUAL
        jmp salir_verifica_input_2

        salir_verifica_input:
        pop AX          ; Devuelve el valor original de AX antes de la lectura del teclado

        salir_verifica_input_2:
        push AX
        call VERIFICA_ULTIMO_TIEMPO
        mov CX, AX
        pop AX
        cmp  CX, 0Fh
        jna  verifica_nuevamente

    PopAXBXCX
ret
endp

BAJA_LINEA proc
    ; Apaga pieza
    mov AH,1
    call IMP_PIEZA

    ; Bajar linea
    inc DH

    ;call VERIFICA_COLISAO

    ; Incrementa Score
    mov SI, offset SCORE_JUEGO
    inc [SI]
    call IMP_SCORE

    ; Imprime Pieza
    mov AH,0
    call IMP_PIEZA
    call CAMBIAR_POSICION_ACTUAL
    call ACTUALIZA_TIEMPO
ret
endp

GAMEPLAY proc
    call GENERAR_SIG_PIEZA
    call DELAY
    loop_jogo:
    call ADD_PROX_PIEZA
    call OBTENER_POSICION_PIEZA

    mov CX,20d
    loop_cai_peca:
        call ACTUALIZA_TIEMPO
        call BAJA_LINEA
        call VERIFICA_INPUT

    loop loop_cai_peca
    jmp loop_jogo
ret
endp

IMP_SCORE proc
    PushAXBXCXDX
    mov DH, 02h                     ; Ajusta Línea
    mov DL, 0Ah                     ; Ajusta Columna (4 + 6) -> El valor también será usado como divisor
    mov BL, 06h                     ; Ajusta Limite minimo caracteres
    mov CX, word ptr SCORE_JUEGO

    LOOP_DIGITOS_SCORE:
        mov  AX, CX
        push DX
        mov  DX, 0h
        mov  CX, 0Ah                    ; Establece el valor del divisor = 10d
        div  CX                         ; Divide AX por CX
        mov  CX, AX                     ; Guarda el valor del resultado en CX
        mov  AX, DX                     ; Obtiene el resultado de la división y lo almacena en AX
        add  AL, '0'                    ; Suma '0' para imprimir el carácter correcto
        pop  DX

        ; Imprime Valor en la Pantalla
        push CX                         ; Guarda CX
        mov CX,1                        ; Informa que es solo un carácter a ser presentado
        sub DX,2
        call GOTO_XY                    ; Ir a la posición
        call CHAR_DISPLAY               ; Imprime
        add DX,2
        pop CX
        cmp  DL, BL
        je   FIM_IMP_SCORE
        dec  DL
    jmp  LOOP_DIGITOS_SCORE

    FIM_IMP_SCORE:
    popAXBXCXDX
    ret
endp

PANTALLA_JUEGO proc
    PushAXBXCXDX
    call LIMPAR_PANTALLA

    ; Muestra Score (Puntuacion)
    mov  word ptr SCORE_JUEGO, 0d
    call IMP_SCORE

    ; Muestra Score (String)
    mov SI, offset SCORE_MSG
    mov DH, 01h
    mov DL, 04h
    mov BL, 07h
    call IMP_STRING

    ; Línea Superior e Inferior del Juego
    mov  SI, offset BASE_JOGO
    mov  DX, 010Ch
    call IMP_STRING
    mov DL, 0Ch
    mov DH, 18h
    mov  SI, offset BASE_JOGO
    call IMP_STRING

    ; Líneas Laterales del Juego
    mov CX,16h
    LOOP_PANTALLA_JUEGO:
    mov  SI, offset LATERAL_JOGO
        inc   CL
        mov DL, 0Ch
        mov  DH, CL
        dec  CL
        mov  DL, 0Ch
        call IMP_STRING
        loop LOOP_PANTALLA_JUEGO

    ; Línea superior e inferior de la caja de la próxima pieza
    mov  SI, offset BASE_CAIXA_PECA
    mov  DH, 1h
    mov  DL, 1Bh
    call IMP_STRING
    mov  SI, offset BASE_CAIXA_PECA
    mov DH, 08h
    call IMP_STRING

    mov CX,06h
    LOOP_PANTALLA_CAJA:
    mov  SI, offset LATERAL_CAIXA_PECA
        inc  CL
        mov  DH, CL
        dec  CL
        call IMP_STRING
        loop LOOP_PANTALLA_CAJA

    ; Imprime primera pieza
    call GAMEPLAY
    PopAXBXCXDX
    ret
endp

PANTALLA_INICIAL proc
   PushAXBXCXDX
   call LIMPAR_PANTALLA

   ; Muestra pantalla inicial
   mov SI, offset TETRIS ; Titulo
   mov BL, 02h
   mov DH, 05h
   mov DL, 0Dh ;12d
   call IMP_STRING

   ; Muestra Figuras
   mov DH, 09h ; Linea inicial
   mov DL, 05h ; Columna inicial
   mov AX, 00h ; Figura inicial
   mov CX, 7
   loop_tetris_menu:
    call IMP_PIEZA
    add DL, 04d
    inc AL
   loop loop_tetris_menu

   ; Opcion de Jugar
   mov SI, offset JUGAR
   mov BL, 0Fh
   mov DH, 0Fh
   mov DL, 0Fh ; 15d
   call IMP_STRING

   ; Opcion de Salir
   mov SI, offset SALIR
   mov DH, 11h ;17d
   mov DL, 0Fh ; 15d
   call IMP_STRING

   ; Mensajes
   mov SI, offset MSG_ABAJO_MENU
   mov BL, 04h
   mov DH, 016h ;22d
   mov DL, 08h
   call IMP_STRING

   ; Realiza la lectura del usuario para entrar o salir del juego
   LECTURA_MENU:
       mov AX, 0h
       int 16h
       cmp    AL, 'J'
       je     INICIAR_JUEGO
       cmp    AL, 'j'
       je     INICIAR_JUEGO
       cmp    AL, 'S'
       je     FINALIZAR
       cmp    AL, 's'
       je     FINALIZAR
   loopne LECTURA_MENU

   INICIAR_JUEGO:
       call PANTALLA_JUEGO
   FINALIZAR:
       call PANTALLA_SALIDA
   PopAXBXCXDX
   ret
endp

GAME_OVER proc
    PushAXBXCXDX
    call LIMPAR_PANTALLA

    mov SI, offset GAME
    mov BL, 0Ch
    mov DH, 05h
    mov DL, 0Fh
    call IMP_STRING

    mov SI, offset OVER
    mov BL, 0Ch
    mov DH, 0Ah
    mov DL, 0Fh
    call IMP_STRING

    mov SI, offset JUGAR ; Opcion de jugar
    mov BL, 0Fh
    mov DH, 0Fh
    mov DL, 0Fh
    call IMP_STRING

    mov SI, offset SALIR ; Opcion de Salir
    mov DH, 11h
    mov DL, 0Fh
    call IMP_STRING

    LECTURA_MENU2:
        mov AX, 0h
        int 16h
        cmp    AL, 'J'
        je     INICIAR_JUEGO2
        cmp    AL, 'j'
        je     INICIAR_JUEGO2
        cmp    AL, 'S'
        je     FINALIZAR2
        cmp    AL, 's'
        je     FINALIZAR2
        loopne LECTURA_MENU2

        INICIAR_JUEGO2:
            call PANTALLA_JUEGO
            loopne LECTURA_MENU2
        FINALIZAR2:
            call PANTALLA_SALIDA

    PopAXBXCXDX
ret
endp


PANTALLA_SALIDA proc
    PushAXBXCXDX
    call LIMPAR_PANTALLA

    ;Mensaje de despedida
    mov SI, offset GRACIAS
    mov BL, 04h
    mov DH, 05h
    mov DL, 0Bh
    call IMP_STRING

    inc SI
    mov BL, 02h
    mov DH, 0Ah
    call IMP_STRING

    mov SI, offset TETRIS
    mov BL, 0Eh
    mov DH, 0Fh
    call IMP_STRING

    mov AL, 01h
    mov BL, 02h
    mov DH, 12h
    mov DL, 5h
    MUESTRA_EMOTICON:
        call GOTO_XY
        call CHAR_DISPLAY
        add DL, 2h
        inc BL
        cmp DL, 21h
        jne MUESTRA_EMOTICON
    PopAXBXCXDX
ret
endp


MAIN:
    mov AX, @DATA
    mov DS, AX

    mov AH, 00h                     ;Interrupción que permite la escritura en la memoria de vídeo
    mov AL, 01h
    int 10h

    mov AX, 0b800h                  ;Inicio de la región de la memoria de video
    mov ES, AX

    call PANTALLA_INICIAL
    call GAME_OVER

    mov AH, 4ch
    mov AL, 00
    int 21h

end MAIN