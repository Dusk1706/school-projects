package automatas;

public class Token {
    private String valor;
    private String tipo;
    private int linea, columna;

    public Token(String valor, String tipo, int linea, int columna) {
        this.valor = valor;
        this.tipo = tipo;
        this.linea = linea;
        this.columna = columna;
    }

    public String getValor() {
        return valor;
    }

    public String getTipo() {
        return tipo;
    }
    public int getLinea() {
        return linea;
    }
    public int getColumna() {
        return columna;
    }
}
