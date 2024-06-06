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

public class Hijo extends Thread {

    private int numeroHijo;
    private RecursosCompartidos rc;

    public Hijo(int numeroHijo, RecursosCompartidos rc) {
        this.numeroHijo = numeroHijo;
        this.rc = rc;
    }

    @Override
    public void run() {
        while (true) {
            rc.getSemaforoHectareasOcupadas().espera();
            int hectareasOcupadas = rc.getNumeroHectareasOcupadas();
            rc.getSemaforoHectareasOcupadas().libera();
            
            int numeroHectareas = rc.getNUMERO_HECTAREAS();
            int hectareasTotales = numeroHectareas * numeroHectareas;            

            if (hectareasOcupadas == hectareasTotales) {
                break;
            }


            int x = Rutinas.nextInt(0, numeroHectareas - 1);
            int y = Rutinas.nextInt(0, numeroHectareas - 1);

            rc.getSemaforoPermisoOficina(x, y).espera();
            int numeroPermisos = rc.getPermisoOficina(x, y);
            rc.getSemaforoPermisoOficina(x, y).libera();

            if (numeroPermisos == rc.getNUMERO_OFICINAS()) {
                rc.getSemaforoHectareaHijo(x, y).espera();
                int hectareaHijo = rc.getHectareaHijo(x, y);

                if (hectareaHijo != 0) {
                    rc.getSemaforoHectareaHijo(x, y).libera();
                    continue;
                }

                rc.setHectareaHijo(x, y, numeroHijo);
                System.out.println("Se le dio una hectarea en la coordenada " + x + "," + y + " al hijo " + numeroHijo);

                rc.getSemaforoHectareasOcupadas().espera();
                rc.setNumeroHectareasOcupadas(rc.getNumeroHectareasOcupadas() + 1);
                rc.getSemaforoHectareasOcupadas().libera();

                rc.getSemaforoHectareaHijo(x, y).libera();
            }
        }
    }
    
}
