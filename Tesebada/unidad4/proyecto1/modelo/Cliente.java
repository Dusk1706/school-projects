package unidad4.proyecto1.modelo;

import unidad4.proyecto1.Rutinas;

public class Cliente {
    private static int numeroClientes = 0;
    private int numeroTabs;
    private String codigo;
    private String nombre, fecha_nacimiento, direccion, telefono, correo;

    public Cliente(int numeroTabs) {
        this.numeroTabs = numeroTabs;
        generarNombre();
        generarFechaNacimiento();
        generarDireccion();
        generarTelefono();
        generarCorreo();
        numeroClientes++;
        codigo = "cliente" + (numeroClientes);
    }

    private void generarNombre() {
        nombre = Rutinas.nextNombre(2);
    }

    private void generarFechaNacimiento() {
        fecha_nacimiento = Rutinas.generarFecha();
    }

    private void generarDireccion() {
        direccion = Rutinas.generarDireccion();
    }

    private void generarTelefono() {
        telefono = Rutinas.generarTelefono();
    }

    private void generarCorreo() {
        boolean tieneCorreo = Rutinas.nextInt(0, 1) == 1;
        if (tieneCorreo) {
            correo = Rutinas.generarCorreo(nombre);
        }
    }

    public static int getNumeroClientes() {
        return numeroClientes;
    }

    @Override
    public String toString() {
        String atributo = "codigo = \"" + codigo;
        String tabs = Rutinas.generarTabs(numeroTabs);
        String tabs1 = Rutinas.generarTabs(numeroTabs + 1);
        String etiqueta = 
                tabs + "<cliente " + atributo + "\">\n" +
                tabs1 + "<nombre>" + nombre + "</nombre>\n" +
                tabs1 + "<fecha_nacimiento>" + 
                Rutinas.generarEtiquetaFecha(fecha_nacimiento, numeroTabs + 2) + 
                tabs1 + "</fecha_nacimiento>\n" +
                tabs1 + "<direccion>" + direccion + "</direccion>\n" +
                tabs1 + "<telefono>" + telefono + "</telefono>\n";
        if (correo != null) {
            etiqueta += tabs1 + "<correo>" + correo + "</correo>\n";
        }
        etiqueta += tabs + "</cliente>\n";
        return etiqueta;
    }
}
