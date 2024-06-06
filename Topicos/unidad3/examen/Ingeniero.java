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

public class Ingeniero extends Thread {
    private RecursosCompartidos rc;

    public Ingeniero(RecursosCompartidos recursosCompartidos) {
        this.rc = recursosCompartidos;
    }

    @Override
    public void run() {
        while (rc.getCantidadProductos() < rc.getCapacidadProductos()) {

            rc.getSPersonas().espera();
            int personasComprandoOSurtiendo = rc.getPersonasComprandoOSurtiendo();
            if (personasComprandoOSurtiendo > 0) {
                rc.getSPersonas().libera();
                continue;
            }

            rc.getSIngenieroTrabajando().espera();
            rc.setIngenieroTrabajando(true);
            rc.getSIngenieroTrabajando().libera();
            
            rc.getSPersonas().libera();
            
            int [] productos = rc.getProductos();
            Semaforo [] sProductos = rc.getSProductos();

            int [] productosNuevos = new int[productos.length + 1];
            Semaforo [] sProductosNuevos = new Semaforo[productos.length + 1];

            for (int i = 0; i < productos.length; i++) {
                productosNuevos[i] = productos[i];
                sProductosNuevos[i] = sProductos[i];
            }

            productosNuevos[productos.length] = Rutinas.nextInt(10, 20);
            sProductosNuevos[productos.length] = new Semaforo(1);

            System.out.println("Ingeniero creando producto " + productos.length + " existencias: " + productosNuevos[productos.length]);

            rc.setProductos(productosNuevos);
            rc.setSProductos(sProductosNuevos);

            rc.getSIngenieroTrabajando().espera();
            rc.setIngenieroTrabajando(false);
            rc.getSIngenieroTrabajando().libera();

            Rutinas.dormirHilo(30);
        }
    }


}
