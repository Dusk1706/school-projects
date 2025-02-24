/*
 * Alumno: Dylan Michel Garcia Figueroa
 * Numero de control: 21170331
 * Materia: Ingenieria Web
 * Unidad: 1
 * Proyecto: Persistencia de objetos
 * Horario: 10:00 a 11:00
 * Fecha: 10/02/2025
 * Profesor: Dr. Clemente Garcia Gerardo
 */

package proyecto1;

import java.io.Serializable;

public class Motor implements Serializable{
    private String tipo;
    private String cilindrada;
    private String potencia;
    private String combustible;
    private String serial;

    public Motor(String tipo, String cilindrada, String potencia, String combustible, String serial) {
        this.tipo = tipo;
        this.cilindrada = cilindrada;
        this.potencia = potencia;
        this.combustible = combustible;
        this.serial = serial;
    }

    @Override
    public String toString() {
        return "Motor{" +
                "tipo='" + tipo + '\'' +
                ", cilindrada='" + cilindrada + '\'' +
                ", potencia=" + potencia +
                ", combustible=" + combustible +
                ", serial=" + serial +
                '}';
    }
    
}
