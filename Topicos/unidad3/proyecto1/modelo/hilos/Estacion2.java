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

public class Estacion2 extends Thread {
    private ProcesoEstacion datosMotor;
    private static Semaforo semaforoRobotsMotor, semaforoRobotsMotorCola;
    private static Queue<Robot> robotsMotor;

    private ProcesoEstacion datosTransmision;
    private static Semaforo semaforoRobotsTransmision, semaforoRobotsTransmisionCola;
    private static Queue<Robot> robotsTransmision;

    private RecursosCompartidos rc;

    public Estacion2(ProcesoEstacion datosMotor, ProcesoEstacion datosTransmision,
            RecursosCompartidos recursosCompartidos) {
        this.datosMotor = datosMotor;
        this.datosTransmision = datosTransmision;
        this.rc = recursosCompartidos;
        crearRobots();
    }

    public void run() {
        final int ESTACION_ANTERIOR = 1;
        final int ESTACION_ACTUAL = 2;
        final int ESTACION_SIGUIENTE = 3;
        while (true) {
            rc.getSCarro(ESTACION_ACTUAL).espera(); 
            int autoActual = rc.getAutoActualEstacion(ESTACION_ANTERIOR);
            rc.setAutoActualEstacion(ESTACION_ACTUAL, autoActual);

            rc.getSEstacion(ESTACION_ANTERIOR).libera();
            
            int linea = rc.getNumeroLinea() - 1;
            procesoMotor(linea, autoActual);
            procesoTransmision(linea, autoActual);
            
            rc.getSCarro(ESTACION_SIGUIENTE).libera();
            rc.getSEstacion(ESTACION_ACTUAL).espera();

            if (rc.isTerminado() && rc.getAutoActualEstacion(ESTACION_ANTERIOR) == autoActual) {
                return;
            }
        }
    }

    private void procesoMotor(int linea, int autoActual) {
        Robot robot = obtenerRobotMotor();
        String urlMotor = "imagenes/motor.png";
        Vista.actualizarInterfaz(linea, 3, autoActual, urlMotor, robot.getUrlImagen());
        Rutinas.dormirHilo(datosMotor.getTiempoEspera());
        Vista.quitarInterfaz(linea, 3);
        liberarRobotMotor(robot);
    }

    private void procesoTransmision(int linea, int autoActual) {
        Robot robot = obtenerRobotTransmision();
        String urlTransmision = "imagenes/transmision.png";
        Vista.actualizarInterfaz(linea, 5, autoActual, urlTransmision, robot.getUrlImagen());
        Rutinas.dormirHilo(datosTransmision.getTiempoEspera());
        Vista.quitarInterfaz(linea, 5);
        liberarRobotTransmision(robot);
    }

    private Robot obtenerRobotMotor() {
        semaforoRobotsMotor.espera();
        semaforoRobotsMotorCola.espera();
        Robot robot = robotsMotor.poll();
        semaforoRobotsMotorCola.libera();
        return robot;
    }

    private Robot obtenerRobotTransmision() {
        semaforoRobotsTransmision.espera();
        semaforoRobotsTransmisionCola.espera();
        Robot robot = robotsTransmision.poll();
        semaforoRobotsTransmisionCola.libera();
        return robot;
    }

    private void liberarRobotMotor(Robot robot) {
        semaforoRobotsMotorCola.espera();
        robotsMotor.add(robot);
        semaforoRobotsMotorCola.libera();
        semaforoRobotsMotor.libera();
    }

    private void liberarRobotTransmision(Robot robot) {
        semaforoRobotsTransmisionCola.espera();
        robotsTransmision.add(robot);
        semaforoRobotsTransmisionCola.libera();
        semaforoRobotsTransmision.libera();
    }

    private void crearRobots() {
        if (robotsMotor == null) {
            crearRobotsMotor();
            crearSemaforosMotor();
        }

        if (robotsTransmision == null) {
            crearRobotsTransmision();
            crearSemaforosTransmision();
        }
    }

    private void crearRobotsMotor() {
        robotsMotor = new LinkedList<>();
        String procesoEstacion = datosMotor.getProcesoEstacion();
        for (int i = 0; i < datosMotor.getNumeroRobots(); i++) {
            String nombre = "Robot-" + (i + 1);
            String url = "imagenes/robot" + Rutinas.nextInt(1, 5) + ".png";
            robotsMotor.add(new Robot(nombre, procesoEstacion, url));
        }
    }

    private void crearSemaforosMotor() {
        semaforoRobotsMotor = new Semaforo(datosMotor.getNumeroRobots());
        semaforoRobotsMotorCola = new Semaforo(1);
    }

    private void crearRobotsTransmision() {
        robotsTransmision = new LinkedList<>();
        String procesoEstacion = datosTransmision.getProcesoEstacion();
        for (int i = 0; i < datosTransmision.getNumeroRobots(); i++) {
            String nombre = "Robot " + (i + 1);
            String url = "imagenes/robot" + Rutinas.nextInt(1, 5) + ".png";
            robotsTransmision.add(new Robot(nombre, procesoEstacion, url));
        }
    }

    private void crearSemaforosTransmision() {
        semaforoRobotsTransmision = new Semaforo(datosTransmision.getNumeroRobots());
        semaforoRobotsTransmisionCola = new Semaforo(1);
    }

}
