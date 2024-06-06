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

package unidad3.proyecto1.auxiliar;

public class Semaforo {
	private int recursos;

	public Semaforo(int recursos) {
		this.recursos = recursos;
	}

	public synchronized void espera() {
		while (recursos < 1) {
			try {
				wait();
			} catch (Exception e) {
			}
		}
		recursos--;

	}

	public synchronized void libera() {
		recursos++;
		notifyAll();
	}
}
