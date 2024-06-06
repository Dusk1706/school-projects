package unidad4.proyecto1.modelo;

import unidad4.proyecto1.Rutinas;

public class Proveedor {
    private static int numeroProveedores = 0;
    private int numeroTabs;
    private String codigo;
    private String nombre_empresa, correo;
    private Contacto [] contactos;

    public Proveedor(int numeroTabs) {
        this.numeroTabs = numeroTabs;
        generarNombreEmpresa();
        generarContactos();
        generarCorreo();
        numeroProveedores++;
        codigo = "proveedor" + (numeroProveedores);
    }

    private void generarNombreEmpresa() {
        nombre_empresa = Rutinas.generarNombreEmpresa();
    }

    private void generarContactos() {
        int numeroContactos = Rutinas.nextInt(1, 3);
        contactos = Rutinas.generarContactos(numeroContactos, numeroTabs);
    }

    private void generarCorreo() {
        correo = Rutinas.generarCorreo(nombre_empresa);
    }

    public static int getNumeroProveedores() {
        return numeroProveedores;
    }

    @Override
    public String toString() {
        String atributo = "codigo = \"" + codigo;
        String tabs = Rutinas.generarTabs(numeroTabs);
        String tabs1 = Rutinas.generarTabs(numeroTabs + 1);
        String etiqueta = tabs + "<proveedor " + atributo + "\">\n" +
                tabs1 + "<nombre_empresa>" + nombre_empresa + "</nombre_empresa>\n";
        if (contactos.length == 1) {
            etiqueta += tabs1 + contactos[0].toString();
        }else {
            etiqueta += tabs1 + "<contactos>\n";
            for (Contacto contacto : contactos) {
                etiqueta += contacto.toString();
            }
            etiqueta += tabs1 + "</contactos>\n";
        }
        if (correo != null) {
            etiqueta += tabs1 + "<correo>" + correo + "</correo>\n";
        }
        etiqueta += tabs + "</proveedor>\n";
        return etiqueta;
    }
}
