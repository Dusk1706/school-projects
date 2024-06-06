/*
 * Alumno: Dylan Michel Garcia Figueroa
 * Numero de control: 21170331
 * Materia: Topicos Avanzados de Programacion
 * Unidad: 3
 * Proyecto: Planta Ensambladora
 * Horario: 9:00 a 10:00
 * Fecha: 17/05/2024
 * Profesor: Dr. Clemente Garcia Gerardo
 */

package unidad3.proyecto1.modelo;

public class ProcesoEstacion {
    private String procesoEstacion;
    private int tiempoEspera, numeroRobots;

    public ProcesoEstacion(String nombreEstacion, int tiempoEspera, int numeroRobots) {
        this.procesoEstacion = nombreEstacion;
        this.tiempoEspera = tiempoEspera;
        this.numeroRobots = numeroRobots;
    }

    public String getProcesoEstacion() {
        return procesoEstacion;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public int getNumeroRobots() {
        return numeroRobots;
    }
}
