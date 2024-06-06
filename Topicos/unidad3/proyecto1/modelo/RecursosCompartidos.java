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

import unidad3.proyecto1.auxiliar.Semaforo;

public class RecursosCompartidos {
    private static int LIMITE_AUTOS;
    private static int autoActual = 0;
    private static Semaforo sAutoActual = new Semaforo(1);

    private Semaforo [] sEstaciones, sCarros;
    private int numeroLinea;
    private String nombreLinea;
    private boolean terminado;
    private int [] autoActualEstaciones;
    
    public RecursosCompartidos(int limiteAutos, String nombreLinea, int numeroLinea) {
        LIMITE_AUTOS = limiteAutos;
        this.nombreLinea = nombreLinea;
        this.numeroLinea = numeroLinea;
        terminado = false;
        autoActualEstaciones = new int[6];

        crearSemaforoEstaciones();
        crearSemaforoCarros();
    }

    private void crearSemaforoEstaciones() {
        sEstaciones = new Semaforo[6];
        
        sEstaciones[0] = new Semaforo(1);
        for (int i = 1; i < sEstaciones.length; i++) {
            sEstaciones[i] = new Semaforo(0);
        }
    }

    private void crearSemaforoCarros() {
        sCarros = new Semaforo[6];
        for (int i = 0; i < sCarros.length; i++) {
            sCarros[i] = new Semaforo(0);
        }
    }

    public String getNombreLinea() {
        return nombreLinea;
    }

    public boolean isTerminado() {
        return terminado;
    }

    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }

    public int getLIMITE_AUTOS() {
        return LIMITE_AUTOS;
    }

    public int getAutoActual() {
        return autoActual;
    }

    public void setAutoActual(int autoActual) {
        RecursosCompartidos.autoActual = autoActual;
    }

    public Semaforo getSEstacion(int estacion) {
        return sEstaciones[estacion - 1];
    }

    public Semaforo getSCarro(int estacion) {
        return sCarros[estacion - 1];
    }

    public Semaforo getSAutoActual() {
        return sAutoActual;
    }

    public int getAutoActualEstacion(int estacion) {
        return autoActualEstaciones[estacion - 1];
    }

    public void setAutoActualEstacion(int estacion, int auto) {
        autoActualEstaciones[estacion - 1] = auto;
    }

    public int getNumeroLinea() {
        return numeroLinea;
    }

}
