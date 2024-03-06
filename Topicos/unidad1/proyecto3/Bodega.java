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

public class Bodega {
    private int capacidadToneladas, pesoActualKg; 

    public Bodega(int capacidadToneladas) {
        this.capacidadToneladas = capacidadToneladas;
        pesoActualKg = 0;
    }

    public boolean isLlena() {
        return pesoActualKg == getCapacidadKg();
    }

    public int getCapacidadToneladas() {
        return capacidadToneladas;
    }

    public int getCapacidadKg() {
        return capacidadToneladas * 1000;
    }

    public void setPesoActualKg(int pesoActualKg) {
        this.pesoActualKg = pesoActualKg;
    }

    public int getPesoActualToneladas() {
        return pesoActualKg / 1000;
    }

    public int getPesoActualKg() {
        return pesoActualKg;
    }

    @Override
    public String toString() {
        return "Bodega [capacidadToneladas=" + capacidadToneladas + ", pesoActualKg=" + pesoActualKg + "]";
    }

}