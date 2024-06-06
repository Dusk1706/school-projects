package unidad4.proyecto1.modelo;

import unidad4.proyecto1.Rutinas;

public class Empleado {
    private static int numeroEmpleados = 0;
    private int numeroTabs;
    private String codigo;
    private String nombre, titulo, fecha_nacimiento, fecha_contratacion;
    private String direccion, telefono, jefe;

    public Empleado(int numeroTabs) {
        this.numeroTabs = numeroTabs;
        generarNombre();
        generarTitulo();
        generarFechaNacimiento();
        generarFechaContratacion();
        generarDireccion();
        generarTelefono();
        generarJefe();
        numeroEmpleados++;
        codigo = "empleado" + (numeroEmpleados);
    }

    private void generarNombre() {
        nombre = Rutinas.nextNombre(2);
    }

    private void generarTitulo() {
        String[] titulos = {
                "Ing. Sistemas", "Ing. Mecanica",
                "Ing. Electrico", "Ing. Bioquimico",
                "Ing. Industrial", "Ing. Civil"
        };
        titulo = titulos[Rutinas.nextInt(titulos.length)];
    }

    private void generarFechaNacimiento() {
        fecha_nacimiento = Rutinas.generarFecha();
    }

    private void generarFechaContratacion() {
        fecha_contratacion = Rutinas.generarFecha();
    }

    private void generarDireccion() {
        direccion = Rutinas.generarDireccion();
    }

    private void generarTelefono() {
        telefono = Rutinas.generarTelefono();
    }

    private void generarJefe() {
        jefe = null;
        boolean tieneJefe = Rutinas.nextInt(0, 1) == 1;
        if (tieneJefe && numeroEmpleados > 0) {
            jefe = "empleado" + Rutinas.nextInt(1, numeroEmpleados);
        }
    }

    public static int getNumeroEmpleados() {
        return numeroEmpleados;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String getFecha_contratacion() {
        return fecha_contratacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getJefe() {
        return jefe;
    }

    @Override
    public String toString() {
        String tabs = Rutinas.generarTabs(numeroTabs);
        String tabs1 = Rutinas.generarTabs(numeroTabs + 1);
        String atributos = "codigo = \"" + codigo + "\"";
        if (jefe != null) {
            atributos += " jefe = \"" + jefe + "\"";
        }
        String etiqueta =  tabs + "<empleado " + atributos + ">\n" +
                tabs1 + "<nombre>" + nombre + "</nombre>\n" +
                tabs1 + "<titulo>" + titulo + "</titulo>\n" +
                tabs1 + "<fecha_nacimiento>\n" + 
                Rutinas.generarEtiquetaFecha(fecha_nacimiento, numeroTabs + 2) + 
                tabs1 + "</fecha_nacimiento>\n" +
                tabs1 + "<fecha_contratacion>\n" + 
                Rutinas.generarEtiquetaFecha(fecha_contratacion, numeroTabs + 2) +
                tabs1 + "</fecha_contratacion>\n" +
                tabs1 + "<direccion>" + direccion + "</direccion>\n" +
                tabs1 + "<telefono>" + telefono + "</telefono>\n";
        
        etiqueta +=  tabs + "</empleado>\n";
        return etiqueta;
    }

}
