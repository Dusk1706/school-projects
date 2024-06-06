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

public class RecursosCompartidos {

    private final int NUMERO_HECTAREAS;
    private final int NUMERO_OFICINAS;
    private final int NUMERO_HIJOS;

    private Semaforo sHectareasOcupadas;
    private int numeroHectareasOcupadas;

    private Semaforo sNumeroPermisosHechos;
    private int numeroPermisosHechos;
    
    private Semaforo sHectareasHijos[][];
    private int hectareasHijos[][];

    private Semaforo sPermisosOficinas[][];
    private int permisosOficinas[][];

    public RecursosCompartidos(int numeroHectareas, int numeroOficinas, int numeroHijos) {
        NUMERO_HECTAREAS = numeroHectareas;
        NUMERO_HIJOS = numeroHijos;
        NUMERO_OFICINAS = numeroOficinas;
        numeroHectareasOcupadas = 0;
        numeroPermisosHechos = 0;

        crearHectareas();
        crearSemaforos();
    }

    private void crearHectareas() {
        permisosOficinas = new int[NUMERO_HECTAREAS][NUMERO_HECTAREAS];
        hectareasHijos = new int[NUMERO_HECTAREAS][NUMERO_HECTAREAS];
    }

    private void crearSemaforos() {
        sHectareasOcupadas = new Semaforo(1);
        sNumeroPermisosHechos = new Semaforo(1);


        sHectareasHijos = new Semaforo[NUMERO_HECTAREAS][NUMERO_HECTAREAS];
        for (int i = 0; i < NUMERO_HECTAREAS; i++) {
            for (int j = 0; j < NUMERO_HECTAREAS; j++) {
                sHectareasHijos[i][j] = new Semaforo(1);
            }
        }

        sPermisosOficinas = new Semaforo[NUMERO_HECTAREAS][NUMERO_HECTAREAS];
        for (int i = 0; i < NUMERO_HECTAREAS; i++) {
            for (int j = 0; j < NUMERO_HECTAREAS; j++) {
                sPermisosOficinas[i][j] = new Semaforo(1);
            }
        }
        
    }
    
    public Semaforo getSemaforoHectareaHijo(int x, int y) {
        return sHectareasHijos[x][y];
    }

    public Semaforo getSemaforoPermisoOficina(int x, int y) {
        return sPermisosOficinas[x][y];
    }

    public Semaforo getSemaforoHectareasOcupadas() {
        return sHectareasOcupadas;
    }

    public Semaforo getSemaforoPermisosHechos() {
        return sNumeroPermisosHechos;
    }

    public int getNumeroPermisosHechos() {
        return numeroPermisosHechos;
    }

    public void setNumeroPermisosHechos(int permisosHechos) {
        this.numeroPermisosHechos = permisosHechos;
    }

    public int getHectareaHijo(int x, int y) {
        return hectareasHijos[x][y];
    }

    public int getPermisoOficina(int x, int y) {
        return permisosOficinas[x][y];
    }

    public void setHectareaHijo(int x, int y, int numeroHijo) {
        hectareasHijos[x][y] = numeroHijo;
    }

    public void setPermisoOficina(int x, int y, int numeroOficina) {
        permisosOficinas[x][y] = numeroOficina;
    }

    public int getNumeroHectareasOcupadas() {
        return numeroHectareasOcupadas;
    }

    public void setNumeroHectareasOcupadas(int hectareasOcupadas) {
        this.numeroHectareasOcupadas = hectareasOcupadas;
    }

    public int getNUMERO_HECTAREAS() {
        return NUMERO_HECTAREAS;
    }

    public int getNUMERO_OFICINAS() {
        return NUMERO_OFICINAS;
    }

    public int getNUMERO_HIJOS() {
        return NUMERO_HIJOS;
    }

}