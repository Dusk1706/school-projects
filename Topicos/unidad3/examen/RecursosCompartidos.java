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

public class RecursosCompartidos {
    private Semaforo sIngenieroTrabajando;
    private Semaforo sPersonasComprandoOSurtiendo;
    private int personasComprandoOSurtiendo;
    private boolean ingenieroTrabajando;
    
    private Semaforo sProductos[];
    private int productos[];
    private int capacidadProductos;

    public RecursosCompartidos(int cantidadProductos, int capacidadProductos) {
        this.capacidadProductos = capacidadProductos;
        personasComprandoOSurtiendo = 0;
        crearSemaforos(cantidadProductos);
        crearProductos(cantidadProductos);
    }

    private void crearSemaforos(int cantidadProductos) {
        sIngenieroTrabajando = new Semaforo(1);
        sPersonasComprandoOSurtiendo = new Semaforo(1);
        sProductos = new Semaforo[cantidadProductos];
        for (int i = 0; i < sProductos.length; i++) {
            sProductos[i] = new Semaforo(1);
        }
    }

    private void crearProductos(int cantidadProductos) {
        productos = new int[cantidadProductos];
        for (int i = 0; i < cantidadProductos; i++) {
            productos[i] = Rutinas.nextInt(10, 20);
        }
    }

    public Semaforo getSIngenieroTrabajando() {
        return sIngenieroTrabajando;
    }

    public Semaforo[] getSProductos() {
        return sProductos;
    }

    public void setSProductos(Semaforo[] sProductos) {
        this.sProductos = sProductos;
    }

    public Semaforo getSProducto(int producto) {
        return sProductos[producto - 1];
    }
    
    public int[] getProductos() {
        return productos;
    }
    
    public void setProductos(int[] productos) {
        this.productos = productos;
    }

    public int getProducto(int producto) {
        return productos[producto - 1];
    }

    public void setProducto(int producto, int cantidad) {
        productos[producto - 1] = cantidad;
    }

    public int getCapacidadProductos() {
        return capacidadProductos;
    }

    public int getCantidadProductos() {
        return productos.length;
    }

    public boolean isIngenieroTrabajando() {
        return ingenieroTrabajando;
    }

    public void setIngenieroTrabajando(boolean ingenieroTrabajando) {
        this.ingenieroTrabajando = ingenieroTrabajando;
    }

    public Semaforo getSPersonas() {
        return sPersonasComprandoOSurtiendo;
    }

    public int getPersonasComprandoOSurtiendo() {
        return personasComprandoOSurtiendo;
    }

    public void setPersonasComprandoOSurtiendo(int personasComprandoOSurtiendo) {
        this.personasComprandoOSurtiendo = personasComprandoOSurtiendo;
    }

}