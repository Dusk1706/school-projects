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

public class Cliente extends Thread {
    private final int OPERACIONES_OBJETIVO;
    private int operacionesRealizadas;
    private RecursosCompartidos rc;

    public Cliente(RecursosCompartidos recursosCompartidos) {
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

            int productoAComprar = Rutinas.nextInt(1, rc.getCantidadProductos());
            int unidadesAComprar = Rutinas.nextInt(1, 3);

            rc.getSProducto(productoAComprar).espera();
            
            int existenciasProducto = rc.getProducto(productoAComprar);
            if (unidadesAComprar > existenciasProducto) {
                rc.getSProducto(productoAComprar).libera();
                rc.getSIngenieroTrabajando().libera();
                continue;
            }
            
            System.out.println("Cliente comprando " + unidadesAComprar + " unidades del producto " + productoAComprar
            + " existencias: " + existenciasProducto);
            
            existenciasProducto -= unidadesAComprar;
            rc.setProducto(productoAComprar, existenciasProducto);
            operacionesRealizadas++;

            rc.getSProducto(productoAComprar).libera();

            rc.getSPersonas().espera();
            rc.setPersonasComprandoOSurtiendo(rc.getPersonasComprandoOSurtiendo() - 1);
            rc.getSPersonas().libera();

            Rutinas.dormirHilo(5);
        }
    }

}
