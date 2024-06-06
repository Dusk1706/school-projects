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

import unidad3.proyecto1.modelo.hilos.Estacion1;
import unidad3.proyecto1.modelo.hilos.Estacion2;
import unidad3.proyecto1.modelo.hilos.Estacion3;
import unidad3.proyecto1.modelo.hilos.Estacion4;
import unidad3.proyecto1.modelo.hilos.Estacion5;
import unidad3.proyecto1.modelo.hilos.Estacion6;

public class LineaProduccion {

    private RecursosCompartidos recursosCompartidos;

    private Estacion1 estacion1;
    private Estacion2 estacion2;
    private Estacion3 estacion3;
    private Estacion4 estacion4;
    private Estacion5 estacion5;
    private Estacion6 estacion6;

    public LineaProduccion(int limiteAutos, String nombreLinea, int numeroLinea) {
        recursosCompartidos = new RecursosCompartidos(limiteAutos, nombreLinea, numeroLinea);

        final int tiempoFijo = 1;
        crearEstaciones(tiempoFijo);
    }

    public void iniciarEstaciones() {
        estacion1.start();
        estacion2.start();
        estacion3.start();
        estacion4.start();
        estacion5.start();
        estacion6.start();
    }

    private void crearEstaciones(int tiempoFijo) {
        crearEstacionChasisYCableado(tiempoFijo);
        crearEstacionMotorTransmision(tiempoFijo);
        crearEstacionCarroceria(tiempoFijo);
        crearEstacionInteriores(tiempoFijo);
        crearEstacionLlantas(tiempoFijo);
        crearEstacionPruebas(tiempoFijo);
    }

    private void crearEstacionChasisYCableado(int tiempoFijo) {
        String nombreProceso = "Chasis y Cableado";
        int tiempoEspera = (tiempoFijo == -1) ? 20 : tiempoFijo, numeroRobots = 5;
        ProcesoEstacion datosChasisYCableado = new ProcesoEstacion(
            nombreProceso, tiempoEspera, numeroRobots
        );
        estacion1 = new Estacion1(datosChasisYCableado, recursosCompartidos);
    }

    private void crearEstacionMotorTransmision(int tiempoFijo) {
        String nombreProceso1 = "Motor";
        int tiempoEspera = (tiempoFijo == -1) ? 6 : tiempoFijo, numeroRobots = 4;
        ProcesoEstacion datosMotor = new ProcesoEstacion(
            nombreProceso1, tiempoEspera, numeroRobots
        );
        

        String nombreProceso2 = "Transmision";
        tiempoEspera = (tiempoFijo == -1) ? 4 : tiempoFijo; 
        numeroRobots = 2;
        ProcesoEstacion datosTransmision = new ProcesoEstacion(
            nombreProceso2, tiempoEspera, numeroRobots
        );

        estacion2 = new Estacion2(datosMotor, datosTransmision, recursosCompartidos);
    }

    private void crearEstacionCarroceria(int tiempoFijo) {
        String nombreProceso = "Carroceria";
        int tiempoEspera = (tiempoFijo == -1) ? 10 : tiempoFijo, numeroRobots = 3;
        ProcesoEstacion datosCarroceria = new ProcesoEstacion(
            nombreProceso, tiempoEspera, numeroRobots
        );
        estacion3 = new Estacion3(datosCarroceria, recursosCompartidos);
    }

    private void crearEstacionInteriores(int tiempoFijo) {
        String nombreProceso = "Interiores";
        int tiempoEspera = (tiempoFijo == -1) ? 5 : tiempoFijo, numeroRobots = 3;
        ProcesoEstacion datosInteriores = new ProcesoEstacion(
            nombreProceso, tiempoEspera, numeroRobots
        );
        estacion4 = new Estacion4(datosInteriores, recursosCompartidos);
    }

    private void crearEstacionLlantas(int tiempoFijo) {
        String nombreProceso = "Llantas";
        int tiempoEspera = (tiempoFijo == -1) ? 5 : tiempoFijo, numeroRobots = 2;
        ProcesoEstacion datosLlantas = new ProcesoEstacion(
            nombreProceso, tiempoEspera, numeroRobots
        );
        estacion5 = new Estacion5(datosLlantas, recursosCompartidos);
    }

    private void crearEstacionPruebas(int tiempoFijo) {
        String nombreProceso = "Pruebas";
        int tiempoEspera = (tiempoFijo == -1) ? 10 : tiempoFijo, numeroRobots = 5;
        ProcesoEstacion datosPruebas = new ProcesoEstacion(
            nombreProceso, tiempoEspera, numeroRobots
        );
        estacion6 = new Estacion6(datosPruebas, recursosCompartidos);
    }

}
