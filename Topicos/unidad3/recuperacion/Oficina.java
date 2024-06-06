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

public class Oficina extends Thread {

    private RecursosCompartidos rc;
    private int numeroOficina;

    public Oficina(int numeroOficina, RecursosCompartidos rc) {
        this.numeroOficina = numeroOficina;        
        this.rc = rc;
    }

    @Override
    public void run() {
        while (true) {
            rc.getSemaforoPermisosHechos().espera();
            int permisosHechos = rc.getNumeroPermisosHechos();
            int numeroHectareas = rc.getNUMERO_HECTAREAS();
            int hectareasTotales = numeroHectareas * numeroHectareas;
            rc.getSemaforoPermisosHechos().libera();
            
            if (permisosHechos == hectareasTotales) {
                break;
            }

            int x = Rutinas.nextInt(0, numeroHectareas - 1);
            int y = Rutinas.nextInt(0, numeroHectareas - 1);

            rc.getSemaforoPermisoOficina(x, y).espera();
            int numeroPermisos = rc.getPermisoOficina(x, y);
            
            if (numeroPermisos == numeroOficina - 1) {
                rc.setPermisoOficina(x, y, numeroOficina);
                System.out.println("Se creo un nuevo permiso en la coordenada " + x + "," + y + " por la oficina " + numeroOficina);
                
                if (numeroOficina == rc.getNUMERO_OFICINAS()) {
                    rc.getSemaforoPermisosHechos().espera();
                    rc.setNumeroPermisosHechos(rc.getNumeroPermisosHechos() + 1);
                    rc.getSemaforoPermisosHechos().libera();
                }
            }
            rc.getSemaforoPermisoOficina(x, y).libera();
        }
    }

    
}
