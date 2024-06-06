/*
 * Alumno: Dylan Michel Garcia Figueroa
 * Numero de control: 21170331
 * Materia: Topicos Avanzados de Programacion
 * Unidad: 3
 * Proyecto: Recuperacion
 * Horario: 9:00 a 10:00
 * Fecha: 4/06/2024
 * Profesor: Dr. Clemente Garcia Gerardo
 */

package unidad3.recuperacion;

public class Empresa {
    private RecursosCompartidos rc;
    private Oficina oficinas[];
    private Hijo hijos[];
    private final int NUMERO_OFICINAS;
    private final int NUMERO_HIJOS;
    private final int NUMERO_HECTAREAS;

    public Empresa() {
        NUMERO_OFICINAS = 3;
        NUMERO_HIJOS = Rutinas.nextInt(5, 10);
        NUMERO_HECTAREAS = Rutinas.nextInt(100, 200);

        crearRecursosCompartidos();
        crearOficinas();
        crearHijos();
        iniciarHilos();
    }

    private void crearRecursosCompartidos() {
        rc = new RecursosCompartidos(NUMERO_HECTAREAS, NUMERO_OFICINAS, NUMERO_HIJOS);
    }

    private void crearOficinas() {
        oficinas = new Oficina[NUMERO_OFICINAS];
        for (int i = 0; i < NUMERO_OFICINAS; i++) {
            oficinas[i] = new Oficina(i + 1, rc);
        }
    }

    private void crearHijos() {
        hijos = new Hijo[NUMERO_HIJOS];
        for (int i = 0; i < NUMERO_HIJOS; i++) {
            hijos[i] = new Hijo(i + 1, rc);
        }
    }

    private void iniciarHilos() {
        for (int i = 0; i < NUMERO_OFICINAS; i++) {
            oficinas[i].start();
        }

        for (int i = 0; i < NUMERO_HIJOS; i++) {
            hijos[i].start();
        }
    }
}
