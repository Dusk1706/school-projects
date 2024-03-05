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

public class BodegaBarco {
    private int capacidadToneladas, pesoActualKg; 

    public BodegaBarco() {
        capacidadToneladas = Rutinas.nextInt(60, 90);
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
        return "BodegaBarco [capacidadToneladas=" + capacidadToneladas + ", pesoActualKg=" + pesoActualKg + "]";
    }

}