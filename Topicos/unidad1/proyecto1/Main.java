/*
 * Alumno: Dylan Michel Garcia Figueroa
 * Numero de control: 21170331
 * Materia: Topicos Avanzados de Programacion
 * Unidad: 1
 * Proyecto: Lista Logica
 * Horario: 9:00 a 10:00
 * Fecha: 09/02/2024
 * Profesor: Dr. Clemente Garcia Gerardo
 */

package unidad1.proyecto1;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Archivos.crearArchivo();
            Archivos.actualizarArchivo();
            Archivos.leerArchivos();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
