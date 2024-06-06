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

public class Robot {
    private String nombre, estacion, urlImagen;

    public Robot(String nombre, String estacion, String urlImagen) {
        this.nombre = nombre;
        this.estacion = estacion;
        this.urlImagen = urlImagen;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getEstacion() {
        return estacion;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    @Override
    public String toString() {
        return nombre + "-" + estacion;
    }
}
