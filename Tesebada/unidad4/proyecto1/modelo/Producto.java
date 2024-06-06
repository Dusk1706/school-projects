package unidad4.proyecto1.modelo;

import unidad4.proyecto1.Rutinas;

public class Producto {
    private static int numeroProductos = 0;
    private int numeroTabs;
    private String codigo;
    private String nombre, precio_unitario;
    private String codigoProveedor, codigoCategoria;

    public Producto(int numeroTabs) {
        this.numeroTabs = numeroTabs;
        generarNombre();
        generarPrecio();
        generarProveedor();
        generarCategoria();
        numeroProductos++;
        codigo = "producto" + (numeroProductos);
    }

    private void generarNombre() {
        String[] nombresProductos = {
            "Teléfono inteligente", "Camiseta", "Batidora", "Rompecabezas",
            "Novela de ficción", "Bicicleta", "Crema hidratante", "Chocolate gourmet",
            "Juego de herramientas", "Vitaminas"
        };
        nombre = nombresProductos[Rutinas.nextInt(0, nombresProductos.length - 1)];
    }

    private void generarPrecio() {
        precio_unitario = "$" + Rutinas.nextInt(50, 300);
    }

    private void generarProveedor() {
        codigoProveedor = "proveedor" + Rutinas.nextInt(1, Proveedor.getNumeroProveedores());
    }

    private void generarCategoria() {
        codigoCategoria = "categoria" + Rutinas.nextInt(1, Categoria.getNumeroCategorias());
    }

    public static int getNumeroProductos() {
        return numeroProductos;
    }

    @Override
    public String toString() {
        String atributos = "codigo = \"" + codigo + "\" ";
        atributos += "proveedor = \"" + codigoProveedor + "\" ";
        atributos += "categoria = \"" + codigoCategoria + "\"";
        String tabs = Rutinas.generarTabs(numeroTabs);
        String tabs1 = Rutinas.generarTabs(numeroTabs + 1);
        String etiqueta = 
                tabs + "<producto " + atributos + ">\n" +
                tabs1 + "<nombre>" + nombre + "</nombre>\n" +
                tabs1 + "<precio_unitario>" + precio_unitario + "</precio_unitario>\n" +
                tabs + "</producto>\n";
        return etiqueta;
    }
}
