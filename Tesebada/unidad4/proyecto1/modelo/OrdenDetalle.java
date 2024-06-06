package unidad4.proyecto1.modelo;

import unidad4.proyecto1.Rutinas;

public class OrdenDetalle {
    private String cantidad, precio_unitario;
    private String codigoOrden, codigoProducto;
    private int numeroTabs;

    public OrdenDetalle(int numeroTabs, String codigoOrden) {
        this.numeroTabs = numeroTabs;
        this.codigoOrden = codigoOrden;
        generarCantidad();
        generarPrecioUnitario();
        generarCodigoProducto();
    }

    private void generarCantidad() {
        cantidad = Rutinas.nextInt(1, 50) + "";        
    }

    private void generarPrecioUnitario() {
        precio_unitario = Rutinas.nextInt(100, 1000) + "";
    }

    public void generarCodigoProducto() {
        codigoProducto = "producto" + Rutinas.nextInt(1, Producto.getNumeroProductos());
    }

    @Override
    public String toString() {
        String tabs = Rutinas.generarTabs(numeroTabs);
        String tabs1 = Rutinas.generarTabs(numeroTabs + 1);
        String atributos = "orden = \"" + codigoOrden + "\" " +
            "producto = \"" + codigoProducto + "\"";
        String etiqueta = tabs + "<orden_detalle " + atributos +">\n" +
            tabs1 + "<cantidad>" + cantidad + "</cantidad>\n" +
            tabs1 + "<precio_unitario>" + precio_unitario + "</precio_unitario>\n" +
            tabs + "</orden_detalle>\n";
        return etiqueta;
    }
}
