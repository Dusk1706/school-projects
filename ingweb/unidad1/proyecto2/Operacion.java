/*
 * Alumno: Dylan Michel Garcia Figueroa
 * Numero de control: 21170331
 * Materia: Ingenieria Web
 * Unidad: 1
 * Proyecto: MVC
 * Horario: 10:00 a 11:00
 * Fecha: 24/02/2025
 * Profesor: Dr. Clemente Garcia Gerardo
 */

package proyecto2;

import java.io.Serializable;

public class Operacion implements Serializable {

    private String operacion;
    private int valor, resultado;

    public Operacion(String operacion, int valor, int resultado) {
        this.operacion = operacion;
        this.valor = valor;
        this.resultado = resultado;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "Operacion [operacion=" + operacion + ", valor=" + valor + ", resultado=" + resultado + "]";
    }

}
