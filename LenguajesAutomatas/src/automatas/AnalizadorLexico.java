package automatas;

import java.lang.reflect.Array;
import java.util.*;

public class AnalizadorLexico {
    private String errores, tablaSimbolos;
    Scanner scLineas, scTokens;
    private ArrayList<Token> tokens;

    public String analizar(String codigo) {
        scLineas = new Scanner(codigo);
        StringBuilder sb = new StringBuilder();
        tokens = new ArrayList<>();
        // Variables para el manejo de errores
        errores = "";
        tablaSimbolos = "";
        int linea = 0;
        String tipoAnterior = "";
        Set<Map.Entry<String, String>> identificadores = new HashSet<>();
        while (scLineas.hasNextLine()) {
            linea++;
            int columna = 1;
            scTokens = new Scanner(scLineas.nextLine());
            scTokens.useDelimiter("\\s+|(?=[{}()\\[\\]=;,.])|(?<=[{}()\\[\\]=;,])"); // Delimitadores entre tokens
            while (scTokens.hasNext()) {
                String token = scTokens.next();
                String tipo = getTipo(token);
                if (tipo.equals("Palabra reservada")) {
                    tipoAnterior = token;
                }
                if (tipo.equals("Identificador") &&
                        !tipoAnterior.equals("si") &&
                        !tipoAnterior.equals("sino") &&
                        !tipoAnterior.equals("mientras")) {
                    boolean existe = false;
                    for (Map.Entry<String, String> entrada : identificadores) {
                        if (token.equals(entrada.getKey())) {
                            existe = true;
                            break;
                        }
                    }
                    if (!existe) {
                        Map.Entry<String, String> aux = new AbstractMap.SimpleEntry<>(token, tipoAnterior);
                        identificadores.add(aux);
                        tablaSimbolos += (token + "->" + tipoAnterior + "\n");
                    }
                }
                sb.append(token);
                sb.append(" -> ");
                sb.append(tipo);
                sb.append("\n");
                if (tipo.equals("Error")) {
                    errores += "Error en la linea " + linea + " columna " + columna + ": " + token + "\n";
                }
                columna++;
                tokens.add(new Token(token, tipo, linea, columna));
            }
        }
        return sb.toString();
    }

    public String getErrores() {
        return errores;
    }

    public String getTablaSimbolos() {
        return tablaSimbolos;
    }

    private static String getTipo(String token) {
        if (token.matches("clase|def|si|sino|mientras|return")) {
            return "Palabra reservada";
        }
        if (token.matches("int|float|boolean|string")) {
            return "Tipo de dato";
        }
        if (token.matches("[a-zA-Z][a-zA-Z0-9]*")) {
            return "Identificador";
        }
        if (token.matches("\".*?\"")) {
            return "Cadena";
        }
        if (token.matches("0|[1-9][0-9]{0,2}|1000")) {
            return "Numero";
        }
        if (token.matches("[{}()\\[\\]=;,.]")) {
            return "Simbolo";
        }
        if (token.matches("\\+|-|\\*|/|==|<|>|<=|>=")) {
            return "Operador Binario";
        }
        if (token.matches("=|;")) {
            return "Operador";
        }
        return "Error";
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }
}
