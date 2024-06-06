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

public class Empresa {
    private RecursosCompartidos rc;
    private Ingeniero ingeniero;
    private Cliente clientes[];
    private Proveedor proveedores[];

    public Empresa() {
        crearRecursosCompartidos();
        crearIngeniero();
        crearClientes();
        crearProveedores();
        iniciarHilos();
    }

    private void iniciarHilos() {
        ingeniero.start();
        for (Cliente cliente : clientes) {
            cliente.start();
        }
        for (Proveedor proveedor : proveedores) {
            proveedor.start();
        }
    }

    private void crearRecursosCompartidos() {
        final int CAPACIDAD_PRODUCTOS = 200;
        int cantidadProductosIniciales = Rutinas.nextInt(50, 100);
        rc = new RecursosCompartidos(cantidadProductosIniciales, CAPACIDAD_PRODUCTOS);
    }

    private void crearIngeniero() {
        ingeniero = new Ingeniero(rc);
    }

    private void crearClientes() {
        int cantidadClientes = Rutinas.nextInt(3, 5);
        clientes = new Cliente[cantidadClientes];
        for (int i = 0; i < clientes.length; i++) {
            clientes[i] = new Cliente(rc);
        }
    }

    private void crearProveedores() {
        int cantidadProveedores = Rutinas.nextInt(2, 3);
        proveedores = new Proveedor[cantidadProveedores];
        for (int i = 0; i < proveedores.length; i++) {
            proveedores[i] = new Proveedor(rc);
        }
    }

}
