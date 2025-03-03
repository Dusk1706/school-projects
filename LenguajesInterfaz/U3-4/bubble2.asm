TITLE BURBLE SORT EN ENSAMBLADOR, VERSION 2

SAVE_REGS MACRO REGS
    IRP D,<REGS>
        PUSH D
    ENDM
ENDM

RESTORE_REGS MACRO REGS
    IRP D,<REGS>
        POP D
    ENDM
ENDM

.MODEL SMALL
.STACK 100H
.DATA
    ARRAY DB 10,9,8,7,6,5,4,3,2,1
    ARRAY2 DB 105,104,103,102,101
.CODE
MAIN PROC
    MOV AX, @DATA
    MOV DS, AX
    ;IMPLEMENTACION DE BURBLE SORT
    
    ;ORDENA EL PASO EN BX, LA DIRECCION DE INICIO DE UN ARREGLO Y EN CS EL TAMA?O
    ; ES UN ARREGLO DE BYTES
    LEA BX, ARRAY
    MOV CX, 10
    CALL ORDENA
    
    LEA BX, ARRAY2
    MOV CX, 5
    CALL ORDENA
    
    MOV AH, 4CH
    INT 21H
MAIN ENDP

ORDENA PROC

    SAVE_REGS <AX, CX, SI, DI, DX>
    
    XOR SI, SI
    ;SE REALIZA SIZE -1 VECES
    DEC CX
CICLO1:
    ; DI ES MI INDICE J, SE HACE IGUAL A I+1
    MOV DI, SI
    INC DI
    ; CX, CONTROLAR AMBOS CICLOS, POR ESO LO METO A LA PILA
    PUSH CX
CICLO2:
    MOV AL, [BX][SI]
    CMP AL,[BX][DI]
    ; SI ES MENOR O IGUAL YA ESTA ORDENADO
    JLE NEXT
INTERCAMBIA:
    MOV AL,[BX][SI]
    XCHG AL,[BX][DI]
    MOV [BX][SI], AL
NEXT:
    INC DI
    LOOP CICLO2
    POP CX
    INC SI
    LOOP CICLO1
    
    ;POP DI
    ;POP SI
    ;POP CX
    ;POP AX
    
    RESTORE_REGS<DX, DI, SI, CX, AX>
    RET
ORDENA ENDP
    END MAIN
    
    