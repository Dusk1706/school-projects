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

public class PlantaEnsambladora {
    private LineaProduccion lineasProduccion[];
    private static final int LIMITE_AUTOS = 1000;

    public PlantaEnsambladora(int numeroLineasProduccion) {
        crearLineasProduccion(numeroLineasProduccion);
    }

    private void crearLineasProduccion(int numeroLineasProduccion) {
        lineasProduccion = new LineaProduccion[numeroLineasProduccion];

        for (int i = 0; i < numeroLineasProduccion; i++) {
            String nombre = "Linea " + (i + 1);
            lineasProduccion[i] = new LineaProduccion(LIMITE_AUTOS, nombre, i + 1);
        }

    }

    public void iniciarLineasProduccion() {
        for (int i = 0; i < lineasProduccion.length; i++) {
            lineasProduccion[i].iniciarEstaciones();
        }
    }
}
