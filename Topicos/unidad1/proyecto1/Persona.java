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

public class Persona {
    private String nombre;
    private int edad, sig;

    public Persona() {
        crearNombre();
        edad = Rutinas.nextInt(0, 100);
        sig = 0;
    }

    private void crearNombre() {
        String aux = Rutinas.nextNombre(Rutinas.nextInt(1, 5));
        aux = Rutinas.PonBlancos(aux, 50);
        nombre = aux.substring(0, 50);
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public int getSig() {
        return sig;
    }

}
