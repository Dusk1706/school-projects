.MODEL SMALL
.STACK 100H
.DATA 
    MSG1 DB 10,13,'INTRODUZCA UN NUMERO DECIMAL DE HASTA 5 DIGITOS:$'
    MSG2 DB 10,13,'EL NUMERO INTRODUCIDO ES:$'

.CODE 
MAIN PROC
    MOV AX,@DATA
    MOV DS,AX
    
    ;Desplegar mensaje
    LEA DX,MSG1
    MOV AH,9
    INT 21H
    
    ;INICIALIZAR BX EN 0
    XOR BX,BX
    ;Leer 5 caracteres
    MOV CX,5
    CICLO:
    
    ;MULTIPLICAR POR 10
    MOV AX,10
    MUL BX    
    ;RESULTADO QUEDA EN DX:AX, SOLO USARE LA PARTE BAJA,
    MOV BX,AX ;LO QUE HAY EN DX SE PIERDE
    
    ;LEER CARACTER QUEDA EN AL
    MOV AH,1
    INT 21H
    
    ;CONVIERTO A NUMERO
    AND AL,0FH
    
    ;EXTIENDO A 16 BITS
    XOR AH,AH
    
    ;EL NUMERO QUE LEI ESTA AHORA EN AX
    ADD BX,AX ;SUMAR EK NUMERO DECIMAL    
    LOOP CICLO
    
    
    MOV AH,4CH
    INT 21H
   MAIN ENDP
   END MAIN