package unidad4.proyecto1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import unidad4.proyecto1.modelo.Categoria;
import unidad4.proyecto1.modelo.Cliente;
import unidad4.proyecto1.modelo.Empleado;
import unidad4.proyecto1.modelo.Orden;
import unidad4.proyecto1.modelo.Producto;
import unidad4.proyecto1.modelo.Proveedor;
import unidad4.proyecto1.modelo.Transportista;

public class GeneracionXML {
    public static void main(String[] args) {
        generarArchivo("empresa.xml");
    }

    public static void generarArchivo(String nombreArchivo) {
        try {
            String ruta = "unidad4/proyecto1/";
            BufferedWriter bw = new BufferedWriter(new FileWriter(ruta + nombreArchivo));
            String encabezado = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                                "<!DOCTYPE empresa SYSTEM \"empresa.dtd\">\n" +
                                "<empresa>\n"; 
            bw.write(encabezado);
            bw.write(generarPersonas(200, 2, 2, 2));
            bw.write(generarAlmacen(2, 2));
            bw.write(generarOrdenes(2, 2));
            bw.write("</empresa>");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String generarPersonas(int numEmpleados, int numClientes, int numProveedores, int numTransportistas) {
        String personas = Rutinas.generarTabs(1) + "<personas>\n";
        personas += generarEmpleados(numEmpleados, 2);
        personas += generarClientes(numClientes, 2);
        personas += generarProveedores(numProveedores, 2);
        personas += generarTransportistas(numTransportistas, 2);
        personas += Rutinas.generarTabs(1) + "</personas>\n";
        return personas;
    }

    public static String generarAlmacen(int numCategorias, int numProductos) {
        String productos = Rutinas.generarTabs(1) + "<almacen>\n";
        productos += generarCategorias(numCategorias, 2);
        productos += generarProductos(numProductos, 2);
        productos += Rutinas.generarTabs(1) + "</almacen>\n";
        return productos;
    }

    public static String generarOrdenes(int numeroOrdenes, int numeroDetalles) {
        String ordenes = Rutinas.generarTabs(1) + "<ordenes>\n";
        for (int i = 0; i < numeroOrdenes; i++) {
            ordenes += new Orden(2, numeroDetalles);
        }
        ordenes += Rutinas.generarTabs(1) + "</ordenes>\n";
        return ordenes;
    }

    public static String generarCategorias(int cantidad, int numeroTabs) {
        String categorias = Rutinas.generarTabs(numeroTabs) + "<categorias>\n";
        for (int i = 0; i < cantidad; i++) {
            categorias += new Categoria(numeroTabs + 1);
        }
        categorias += Rutinas.generarTabs(numeroTabs) + "</categorias>\n";
        return categorias;
    }

    public static String generarProductos(int cantidad, int numeroTabs) {
        String productos = Rutinas.generarTabs(numeroTabs) + "<productos>\n";
        for (int i = 0; i < cantidad; i++) {
            productos += new Producto(numeroTabs + 1);
        }
        productos += Rutinas.generarTabs(numeroTabs) + "</productos>\n";
        return productos;
    }

    public static String generarClientes(int cantidad, int numeroTabs) {
        String personas = Rutinas.generarTabs(numeroTabs) + "<clientes>\n";
        for (int i = 0; i < cantidad; i++) {
            personas += new Cliente(numeroTabs + 1);
        }
        personas += Rutinas.generarTabs(numeroTabs) + "</clientes>\n";
        return personas;
    }

    public static String generarEmpleados(int cantidad, int numeroTabs) {
        String personas = Rutinas.generarTabs(numeroTabs) + "<empleados>\n";
        for (int i = 0; i < cantidad; i++) {
            personas += new Empleado(numeroTabs + 1);
        }
        personas += Rutinas.generarTabs(numeroTabs) + "</empleados>\n";
        return personas;
    }

    public static String generarProveedores(int cantidad, int numeroTabs) {
        String proveedores = Rutinas.generarTabs(numeroTabs) + "<proveedores>\n";
        for (int i = 0; i < cantidad; i++) {
            proveedores += new Proveedor(numeroTabs + 1);
        }
        proveedores += Rutinas.generarTabs(numeroTabs) + "</proveedores>\n";
        return proveedores;
    }

    public static String generarTransportistas(int cantidad, int numeroTabs) {
        String transportistas = Rutinas.generarTabs(numeroTabs) + "<transportistas>\n";
        for (int i = 0; i < cantidad; i++) {
            transportistas += new Transportista(numeroTabs + 1);
        }
        transportistas += Rutinas.generarTabs(numeroTabs) + "</transportistas>\n";
        return transportistas;
    }

}