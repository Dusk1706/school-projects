package automatas;

import java.util.ArrayList;

public class AnalizadorSintactico {
    private ArrayList<Token> tokens;
    private int index;
    private String erroresSintacticos;

    public void analizar(ArrayList<Token> tokens) {
        this.tokens = tokens;
        this.index = 0;
        this.erroresSintacticos = "";

        programa();
    }

    // Metodos para cada regla gramatical

    private void programa() {
        index += match("clase");
        index += identificador();
        index += match("{");
        while (index < tokens.size() - 1) {
            System.out.println("Analizando token " + index + " " + tokens.get(index).getValor());
            if (tokens.get(index).getValor().equals("def")) {
                System.out.println("Analizando metodo");
                funcion();
            } else if (tokens.get(index).getTipo().equals("Tipo de dato")) {
                System.out.println("Analizar declaracion");
                declaracion();
            } else{
                sentencia();
            }
        }
    }

    private void declaracion() {
        index += tipo();
        index += identificador();
        index += match(";");
    }

    private void funcion() {
        index += match("def");
        index += identificador();
        index += match("(");
        parametros();
        index += match(")");
        index += tipo();
        bloque();
    }

    private int tipo() {
        if(index >= tokens.size() || !tokens.get(index).getTipo().equals("Tipo de dato")) {
            errorSintactico("Se esperaba un tipo de dato");
            return 0;
        }
        return 1;
    }

    private int identificador() {
        if(index >= tokens.size() || !tokens.get(index).getTipo().equals("Identificador")) {
            errorSintactico("Se esperaba un identificador");
            return 0;
        }
        return 1;
    }

    private void parametros() {
        if (index < tokens.size() && !tokens.get(index).getValor().equals(")")) {
            index += tipo();
            index += identificador();
            parametrosResto();
        }
    }

    private void parametrosResto() {
        if (index < tokens.size() && tokens.get(index).getValor().equals(",")) {
            index += match(",");
            index += tipo();
            index += identificador();
            parametrosResto();
        }
    }

    private void bloque() {
        index += match("{");
        while (index < tokens.size() && tokens.get(index).getTipo().equals("Tipo de dato")) {
            System.out.println("Entro a declaracion desde bloque");
            declaracion();
            System.out.println("Salio de declaracion desde bloque");
        }
        while (index < tokens.size() && !tokens.get(index).getValor().equals("}")) {
            sentencia();
        }
        index += match("}");
    }

    private void sentencia() {
        if(index >= tokens.size()){
            return;
        }
        if (tokens.get(index).getTipo().equals("Palabra reservada")) {
            String palabraReservada = tokens.get(index).getValor();
            if (palabraReservada.equals("si")) {
                condicional();
            } else if (palabraReservada.equals("for")) {
                cicloFor();
            }else if (palabraReservada.equals("mientras")) {
                cicloWhile();
            }else if(palabraReservada.equals("return")){
                match("return");
                if (tokens.get(index).getTipo().equals("Identificador")){
                    identificador();
                    match(";");
                }else {
                    expresion();
                    match(";");
                }
            }
            else {
                declaracion();
            }
        } else if (tokens.get(index).getTipo().equals("Identificador")) {
            asignacion();
        }else{
            //Nuevo
            errorSintactico(tokens.get(index).getValor() + " no es una sentencia válida");
            index++;
        }
    }

    private void condicional() {
        index += match("si");
        index += match("(");
        expresion();
        index += match(")");
        System.out.println("Entro a bloque desde condicional");
        bloque();
        System.out.println("Salio de bloque desde condicional");
    }

    private void cicloFor() {
        index += match("for");
        index += match("(");
        declaracion();
        expresion();
        index += match(";");
        asignacion();
        index += match(")");
        bloque();
    }
    private void cicloWhile() {
        index += match("mientras");
        index += match("(");
        expresion();
        index += match(")");
        bloque();
    }
    private void asignacion() {
        index += identificador();
        index += match("=");
        expresion();
        index += match(";");
    }

    private void expresion() {
        termino();
        expresionResto();
    }

    private void expresionResto() {
        if (index < tokens.size() && (tokens.get(index).getValor().equals("+") ||
                tokens.get(index).getValor().equals("-")
                || tokens.get(index).getValor().equals("<")
                || tokens.get(index).getValor().equals(">")
                || tokens.get(index).getValor().equals("=="))) {
            index += match(tokens.get(index).getValor());
            termino();
            expresionResto();
        }
    }

    private void termino() {
        factor();
        terminoResto();
    }

    private void terminoResto() {
        if (index < tokens.size() && tokens.get(index).getValor().equals("*")) {
            index += match("*");
            factor();
            terminoResto();
        }
    }

    private void factor() {
        if (index < tokens.size() && tokens.get(index).getValor().equals("(")) {
            index += match("(");
            expresion();
            index += match(")");
        } else {
            if(index >= tokens.size()){
            return;
           }
            if (!tokens.get(index).getTipo().equals("Identificador") &&
                    !tokens.get(index).getTipo().equals("Numero")) {
                errorSintactico("Se esperaba un identificador o número");
            }
            index++;
        }
    }

    private int match(String expectedToken) {
        if(index >= tokens.size()){
            return 0;
        }
        if(!tokens.get(index).getValor().equals(expectedToken)) {

            if(tokens.get(index).equals(";")){
                System.out.println("entro al error match");
            }
            errorSintactico("Se esperaba '" + expectedToken + "' pero se encontro '" + tokens.get(index).getValor() + "'");
            return 0;
        }
        return 1;
    }
    private void errorSintactico(String mensaje) {
        if (index >= tokens.size()) {
            return;
        }
        int linea = tokens.get(index).getLinea();
        int columna = tokens.get(index).getColumna();
        //System.out.println(mensaje + " en la linea " + linea + ", columna " + columna);
        erroresSintacticos += mensaje + " en la linea " + linea + ", columna " + columna + "\n";
        index++;
    }
    public String getErroresSintacticos() {
    	return erroresSintacticos;
    }
}