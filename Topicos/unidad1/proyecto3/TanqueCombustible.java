/*
 * Alumno: Dylan Michel Garcia Figueroa
 * Numero de control: 21170331
 * Materia: Topicos Avanzados de Programacion
 * Unidad: 1
 * Proyecto: Barcos
 * Horario: 9:00 a 10:00
 * Fecha: 8/03/2024
 * Profesor: Dr. Clemente Garcia Gerardo
 */

package unidad1.proyecto3;

public class TanqueCombustible {
    private int capacidadLitros, litrosActuales;

    public TanqueCombustible(int capacidadBodega) {
        capacidadLitros = capacidadBodega / 2;
        litrosActuales = 0;
    }

    public int getCapacidadLitros() {
        return capacidadLitros;
    }

    public void setLitrosActuales(int litrosActuales) {
        this.litrosActuales = litrosActuales;
    }

    public int getLitrosActuales() {
        return litrosActuales;
    }

    @Override
    public String toString() {
        return "TanqueCombustible [capacidadLitros=" + capacidadLitros + ", litrosActuales=" + litrosActuales + "]";
    }

}
