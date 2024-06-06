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

package unidad3.proyecto1;

import unidad3.programs.Rutinas;
import unidad3.proyecto1.modelo.PlantaEnsambladora;

public class Main {
    public static void main(String[] args) {
        int numeroLineas = Rutinas.nextInt(8, 15);
        new Vista(numeroLineas);
        PlantaEnsambladora modelo = new PlantaEnsambladora(numeroLineas);
        modelo.iniciarLineasProduccion();
    }
}
