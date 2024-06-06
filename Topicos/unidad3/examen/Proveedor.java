/*
 * Alumno: Dylan Michel Garcia Figueroa
 * Numero de control: 21170331
 * Materia: Topicos Avanzados de Programacion
 * Unidad: 3
 * Proyecto: Examen
 * Horario: 9:00 a 10:00
 * Fecha: 17/05/2024
 * Profesor: Dr. Clemente Garcia Gerardo
 */

package unidad3.examen;

public class Proveedor extends Thread {
    private final int OPERACIONES_OBJETIVO;
    private int operacionesRealizadas;
    private RecursosCompartidos rc;

    public Proveedor(RecursosCompartidos recursosCompartidos) {
        this.rc = recursosCompartidos;    
        OPERACIONES_OBJETIVO = 100;
        operacionesRealizadas = 0;
    }

    @Override
    public void run() {
        while (operacionesRealizadas < OPERACIONES_OBJETIVO) {
            rc.getSIngenieroTrabajando().espera();
        
            if (rc.isIngenieroTrabajando()) {
                rc.getSIngenieroTrabajando().libera();
                continue;
            }

            rc.getSPersonas().espera();
            rc.setPersonasComprandoOSurtiendo(rc.getPersonasComprandoOSurtiendo() + 1);
            rc.getSPersonas().libera();

            rc.getSIngenieroTrabajando().libera();

            int productoASurtir = Rutinas.nextInt(1, rc.getCantidadProductos());
            int unidadesASurtir = Rutinas.nextInt(5, 10);
            
            rc.getSProducto(productoASurtir).espera();

            int existenciasProducto = rc.getProducto(productoASurtir);
            existenciasProducto += unidadesASurtir;

            System.out.println("Proveedor surtiendo " + unidadesASurtir + " unidades del producto " + productoASurtir + " existencias: " + existenciasProducto);

            rc.setProducto(productoASurtir, existenciasProducto);
            operacionesRealizadas++;

            rc.getSProducto(productoASurtir).libera();

            rc.getSPersonas().espera();
            rc.setPersonasComprandoOSurtiendo(rc.getPersonasComprandoOSurtiendo() - 1);
            rc.getSPersonas().libera();
            
            Rutinas.dormirHilo(5);
        }   
    }
    
}
