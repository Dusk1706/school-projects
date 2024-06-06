package unidad4.proyecto1.modelo;

import unidad4.proyecto1.Rutinas;

public class Orden {
    private static int numeroOrdenes = 0;
    private int numeroTabs;
    private String fecha, codigoOrden, codigoCliente, codigoEmpleado;
    private OrdenDetalle[] detalles;

    public Orden(int numeroTabs, int numeroDetalles) {
        this.numeroTabs = numeroTabs;
        generarFecha();
        generarCodigoCliente();
        generarCodigoEmpleado();
        numeroOrdenes++;
        codigoOrden = "orden" + numeroOrdenes;
        generarDetalles(numeroDetalles);
    }

    public static int getNumeroOrdenes() {
        return numeroOrdenes;
    }

    public void generarFecha() {
        fecha = Rutinas.generarFecha();
    }

    public void generarDetalles(int numeroDetalles) {
        detalles = new OrdenDetalle[numeroDetalles];
        for (int i = 0; i < detalles.length; i++) {
            detalles[i] = new OrdenDetalle(numeroTabs + 2, codigoOrden);
        }
    }

    public void generarCodigoCliente() {
        codigoCliente = "cliente" + Rutinas.nextInt(1, Cliente.getNumeroClientes());
    }

    public void generarCodigoEmpleado() {
        codigoEmpleado = "empleado" + Rutinas.nextInt(1, Empleado.getNumeroEmpleados());
    }

    @Override
    public String toString() {
        String tabs = Rutinas.generarTabs(numeroTabs);
        String tabs1 = Rutinas.generarTabs(numeroTabs + 1);
        String atributos = "codigo = \"" + codigoOrden + "\"" +
            " cliente = \"" + codigoCliente + "\"" +
            " empleado = \"" + codigoEmpleado + "\"";
        String etiqueta = tabs + "<orden " + atributos + ">\n";
        etiqueta += Rutinas.generarEtiquetaFecha(fecha, numeroTabs + 1);
        etiqueta += tabs1 + "<orden_detalles>\n";
        for (OrdenDetalle detalle : detalles) {
            etiqueta += detalle.toString();
        }
        etiqueta += tabs1 + "</orden_detalles>\n";
        etiqueta += tabs + "</orden>\n";
        return etiqueta;
    }

}
