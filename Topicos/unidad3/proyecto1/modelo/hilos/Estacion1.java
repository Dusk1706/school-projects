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

package unidad3.proyecto1.modelo.hilos;

import java.util.LinkedList;
import java.util.Queue;

import unidad3.proyecto1.auxiliar.Rutinas;
import unidad3.proyecto1.auxiliar.Semaforo;
import unidad3.proyecto1.modelo.ProcesoEstacion;
import unidad3.proyecto1.modelo.RecursosCompartidos;
import unidad3.proyecto1.modelo.Robot;
import unidad3.proyecto1.Vista;

public class Estacion1 extends Thread {
    private ProcesoEstacion datos;
    private RecursosCompartidos rc;
    
    private static Semaforo semaforoRobots, semaforoRobotsCola;
    private static Queue<Robot> robots;

    public Estacion1(ProcesoEstacion datos, RecursosCompartidos recursosCompartidos) {
        this.datos = datos;
        this.rc = recursosCompartidos;
        crearRobots();
    }

    public void run() {
        final int ESTACION_ACTUAL = 1;
        final int ESTACION_SIGUIENTE = 2;
        while (true) {
            rc.getSEstacion(ESTACION_ACTUAL).espera();
            
            rc.getSAutoActual().espera();
            int autoActual = rc.getAutoActual();
            if (autoActual == rc.getLIMITE_AUTOS()) {
                rc.setTerminado(true);
                rc.getSCarro(ESTACION_SIGUIENTE).libera();
                return;
            }
            autoActual++;
            rc.setAutoActual(autoActual);
            rc.setAutoActualEstacion(ESTACION_ACTUAL, autoActual);
            rc.getSAutoActual().libera();

            procesoChasisYCableado(autoActual);
            
            rc.getSCarro(ESTACION_SIGUIENTE).libera();
        }
    }

    private void procesoChasisYCableado(int autoActual) {
        int linea = rc.getNumeroLinea() - 1;
        Robot robot = obtenerRobot();
        String urlProceso = "imagenes/chasisycableado.png", urlRobot = robot.getUrlImagen();
        Vista.actualizarInterfaz(linea, 1, autoActual, urlProceso, urlRobot);
        Rutinas.dormirHilo(datos.getTiempoEspera());
        Vista.quitarInterfaz(linea, 1);
        liberarRobot(robot);
    }

    private Robot obtenerRobot() {
        semaforoRobots.espera();
        semaforoRobotsCola.espera();
        Robot robot = robots.poll();
        semaforoRobotsCola.libera();
        return robot;
    }

    private void liberarRobot(Robot robot) {
        semaforoRobotsCola.espera();
        robots.add(robot);
        semaforoRobotsCola.libera();
        semaforoRobots.libera();
    }

    private void crearRobots() {
        if (robots == null) {
            crearRobotsCola();
            crearSemaforosRobots();
        }
    }

    private void crearRobotsCola() {
        robots = new LinkedList<>();
        String estacion = datos.getProcesoEstacion();
        for (int i = 0; i < datos.getNumeroRobots(); i++) {
            String nombre = "Robot-" + (i + 1);
            String url = "imagenes/robot" + Rutinas.nextInt(1, 5) + ".png";
            robots.add(new Robot(nombre, estacion, url));
        }
    }

    private void crearSemaforosRobots() {
        semaforoRobots = new Semaforo(datos.getNumeroRobots());
        semaforoRobotsCola = new Semaforo(1);
    }

}
