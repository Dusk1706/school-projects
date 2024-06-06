package unidad4.proyecto1.modelo;

import unidad4.proyecto1.Rutinas;

public class Contacto {
    private String nombre, telefono;
    private int numeroTabs;

    public Contacto(int numeroTabs) {
        this.numeroTabs = numeroTabs;
        generarNombre();
        generarTelefono();
    }

    private void generarNombre() {
        nombre = Rutinas.nextNombre(2);
    }

    private void generarTelefono() {
        telefono = Rutinas.generarTelefono();
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public String toString() {
        String tabs = Rutinas.generarTabs(numeroTabs);
        String tabs1 = Rutinas.generarTabs(numeroTabs + 1);
        return  tabs + "<contacto>\n" +
                tabs1 + "<nombre>" + nombre + "</nombre>\n" +
                tabs1 + "<telefono>" + telefono + "</telefono>\n" +
                tabs + "</contacto>\n";
    }

}
