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

import unidad3.proyecto1.Vista;
import unidad3.proyecto1.auxiliar.Rutinas;
import unidad3.proyecto1.auxiliar.Semaforo;
import unidad3.proyecto1.modelo.ProcesoEstacion;
import unidad3.proyecto1.modelo.RecursosCompartidos;
import unidad3.proyecto1.modelo.Robot;

public class Estacion3 extends Thread {
    private ProcesoEstacion datos;
    private RecursosCompartidos rc;
    
    private static Semaforo semaforoRobots, semaforoRobotsCola;
    private static Queue<Robot> robots;

    public Estacion3(ProcesoEstacion datos, RecursosCompartidos recursosCompartidos) {
        this.datos = datos;
        this.rc = recursosCompartidos;
        crearRobots();
    }

    public void run() {
        final int ESTACION_UNO = 1;
        final int ESTACION_ANTERIOR = 2;
        final int ESTACION_ACTUAL = 3;
        final int ESTACION_SIGUIENTE = 4;

        while (true) {
            rc.getSCarro(ESTACION_ACTUAL).espera();
            int autoActual = rc.getAutoActualEstacion(ESTACION_ANTERIOR);
            rc.setAutoActualEstacion(ESTACION_ACTUAL, autoActual);

            rc.getSEstacion(ESTACION_ANTERIOR).libera();

            procesoCarroceria(autoActual);

            rc.getSCarro(ESTACION_SIGUIENTE).libera();
            rc.getSEstacion(ESTACION_ACTUAL).espera();

            if (rc.isTerminado() && rc.getAutoActualEstacion(ESTACION_UNO) == autoActual) {
                return;
            }
        }
    }

    private void procesoCarroceria(int autoActual) {
        int linea = rc.getNumeroLinea() - 1;
        Robot robot = obtenerRobot();
        String urlCarroceria = "imagenes/carroceria.png";
        Vista.actualizarInterfaz(linea, 7, autoActual, urlCarroceria, robot.getUrlImagen());
        Rutinas.dormirHilo(datos.getTiempoEspera());
        Vista.quitarInterfaz(linea, 7);
        liberarRobot(robot);
    }

    private void crearRobots() {
        if (robots == null) {
            crearRobotsCola();
            crearSemaforos();
        }
    }

    private void crearSemaforos() {
        semaforoRobots = new Semaforo(datos.getNumeroRobots());
        semaforoRobotsCola = new Semaforo(1);
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

}
