/*
 * Alumno: Dylan Michel Garcia Figueroa
 * Numero de control: 21170331
 * Materia: Topicos Avanzados de Programacion
 * Unidad: 1
 * Proyecto: Examen
 * Horario: 9:00 a 10:00
 * Fecha: 8/03/2024
 * Profesor: Dr. Clemente Garcia Gerardo
 */

package unidad1.examen;


public class Modelo {
    private final int NUM_EMPLEADOS, NUM_GERENTES;
    private Torniquete torniquete;
    private Gerente [] gerentes;

    public Modelo() {
        NUM_EMPLEADOS = Rutinas.nextInt(50, 100);
        NUM_GERENTES = Rutinas.nextInt(5, 10);    
        torniquete = new Torniquete(NUM_EMPLEADOS);
        generarNombreGerentes();
    }

    private void generarNombreGerentes() {
        gerentes = new Gerente[NUM_GERENTES];
        for (int i = 0; i < NUM_GERENTES; i++) {
            String nombre = Rutinas.nextNombre(1);
            gerentes[i] = new Gerente(nombre, getIdEmpleadoNoTomado());
        }
    }

    public int getIdEmpleadoNoTomado() {
        int idEmpleado = Rutinas.nextInt(1, NUM_EMPLEADOS);
        while(esGerente(idEmpleado)) {
            idEmpleado = Rutinas.nextInt(1, NUM_EMPLEADOS);
        }
        return idEmpleado;
    }

    public boolean esGerente(int idEmpleado) {
        for (int i = 0; i < NUM_GERENTES; i++) {
            Gerente gerente = gerentes[i];
            boolean existeGerente = gerentes[i] != null;
            if (existeGerente && gerente.getIdEmpleado() == idEmpleado) {
                return true;
            }
        }
        return false;
    }

    public boolean pasarPorTorniquete(int idEmpleado) {
        IdEmpleado empleado = torniquete.getIdEmpleado(idEmpleado);
        int cantidadAccesos = empleado.getCantidadAccesos();
        if (!empleado.pasoPorTorniquete() && cantidadAccesos > 0) {
            empleado.setPasoPorTorniquete(true);
            decrementarAcceso(idEmpleado);
            return true;
        }
        return false;
    }

    public void decrementarAcceso(int idEmpleado) {
        IdEmpleado empleado = torniquete.getIdEmpleado(idEmpleado);
        int cantidadAccesos = empleado.getCantidadAccesos();

        if (!esGerente(idEmpleado)) {
            empleado.setCantidadAccesos(cantidadAccesos - 1);
        }
    }

    public boolean salirPorTorniquete(int idEmpleado) {
        IdEmpleado empleado = torniquete.getIdEmpleado(idEmpleado);
        if (empleado.pasoPorTorniquete()) {
            empleado.setPasoPorTorniquete(false);
            return true;
        }
        return false;
    }

    public int getIdGerente(String nombreGerente) {
        for (int i = 0; i < NUM_GERENTES; i++) {
            if (gerentes[i].getNombre().equals(nombreGerente)) {
                return gerentes[i].getIdEmpleado();
            }
        }
        return -1;
    }

    public int getNUM_EMPLEADOS() {
        return NUM_EMPLEADOS;
    }

    public String[] getNombreGerentes() {
        String [] nombres = new String[NUM_GERENTES];
        for (int i = 0; i < NUM_GERENTES; i++) {
            nombres[i] = gerentes[i].getNombre();
        }
        return nombres;
    }

    public Gerente[] getGerentes() {
        return gerentes;
    }

}
