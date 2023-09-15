.MODEL SMALL
.STACK 100H
.DATA ;Declaracion de variables
    A DB -1
    B DB -2
    C DB ?
    D DW 10000
    E DW 5000
    F DW ?
.CODE
MAIN PROC
    ;Inicializar el segmento de datos
    MOV AX,@DATA
    MOV DS,AX

    ;C=A+B
    MOV AH,A
    ADD AH,B
    MOV C,AH

    ;F=E-D
    MOV AX,E
    SUB AX,D
    MOV F,AX
    
    ;Terminar programa
    MOV AH,4CH
    INT 21H
MAIN ENDP
END MAIN
