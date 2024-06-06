package unidad4.proyecto1.modelo;

import unidad4.proyecto1.Rutinas;

public class Transportista {
    private static int numeroTransportistas = 0;
    private String nombre_empresa, codigo;
    private Contacto [] contactos;
    private int numeroTabs;

    public Transportista(int numeroTabs) {
        this.numeroTabs = numeroTabs;
        generarNombreEmpresa();
        generarContactos();
        numeroTransportistas++;
        codigo = "transportista" + (numeroTransportistas);
    }

    private void generarNombreEmpresa() {
        nombre_empresa = Rutinas.generarNombreEmpresa();
    }

    private void generarContactos() {
        int numeroContactos = Rutinas.nextInt(1, 3);
        contactos = Rutinas.generarContactos(numeroContactos, numeroTabs);
    }

    @Override
    public String toString() {
        String atributo = "codigo = \"" + codigo;
        String tabs = Rutinas.generarTabs(numeroTabs);
        String tabs1 = Rutinas.generarTabs(numeroTabs + 1);
        String etiqueta = tabs + "<transportista " + atributo + "\">\n" +
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
        etiqueta += tabs + "</transportista>\n";
        return etiqueta;
    }
    
}
