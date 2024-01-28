TITLE EJEMPLOS DE MACROS
;aqui macros
FACTORIAL MACRO N
    K = 1
    FACTO = 1
    REPT N
         FACTO = FACTO*K    
         K = K+1
    ENDM
    MOV AX, FACTO
ENDM

.MODEL SMALL
.STACK 100H
.DATA    

    NUMEROS LABEL WORD
    N=1
    REPT 1000
        DW N
        N=N+1
    ENDM
.CODE
MAIN PROC
    MOV AX, @DATA
    MOV DS, AX
    
    MOV AX, N
    FACTORIAL 5
    
    MOV AH, 4CH
    INT 21H
MAIN ENDP
    END MAIN