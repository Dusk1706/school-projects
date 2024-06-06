package unidad4.proyecto1.modelo;

import unidad4.proyecto1.Rutinas;

public class Categoria {
    private static int numeroCategorias = 0;
    private String codigo;
    private String nombre, descripcion;
    private int numeroTabs;

    public Categoria(int numeroTabs) {
        this.numeroTabs = numeroTabs;
        generarDatos();
        numeroCategorias++;
        codigo = "categoria" + (numeroCategorias);
    }

    private void generarDatos() {
        String[] nombresCategorias = {
            "Electrónica", "Ropa", "Hogar y Cocina", "Juguetes", "Libros",
            "Deportes y Aire Libre", "Belleza y Cuidado Personal", "Alimentos y Bebidas",
            "Automotriz", "Salud y Bienestar"
        };

        String[] descripciones = {
            "Encuentra la última tecnología en teléfonos, laptops, televisores y más. Todo lo que necesitas para estar conectado.",
            "Moda para todas las edades y estilos. Descubre nuestra selección de ropa, calzado y accesorios.",
            "Todo lo que necesitas para hacer de tu casa un hogar. Cocina, muebles, decoración y más.",
            "Una gran variedad de juguetes para niños de todas las edades. Desde educativos hasta los más divertidos.",
            "Explora nuestro catálogo de libros de todos los géneros. Desde ficción hasta no ficción, tenemos algo para todos.",
            "Equipo y accesorios para todas tus actividades al aire libre y deportes favoritos. Calidad y rendimiento garantizados.",
            "Productos de belleza y cuidado personal para que te veas y sientas increíble. Cosméticos, cuidado de la piel y más.",
            "Deliciosos alimentos y bebidas para todas las ocasiones. Desde snacks hasta bebidas gourmet.",
            "Accesorios y herramientas para mantener tu vehículo en perfecto estado. Todo lo que necesitas para el cuidado automotriz.",
            "Productos y suplementos para tu salud y bienestar. Todo lo que necesitas para una vida sana y activa."
        };
        int indice = Rutinas.nextInt(0, nombresCategorias.length - 1);
        nombre = nombresCategorias[indice];
        boolean tieneDescripcion = Rutinas.nextInt(0, 1) == 1;
        if (tieneDescripcion) {
            descripcion = descripciones[indice];
        }
    }

    public static int getNumeroCategorias() {
        return numeroCategorias;
    }

    @Override
    public String toString() {
        String atributo = "codigo = \"" + codigo;
        String tabs = Rutinas.generarTabs(numeroTabs);
        String tabs1 = Rutinas.generarTabs(numeroTabs + 1);
        String tabs2 = Rutinas.generarTabs(numeroTabs + 2);
        String etiqueta = tabs + "<categoria " + atributo + "\">\n" +
                tabs1 + "<nombre>" + nombre + "</nombre>\n";
        if (descripcion != null) {
            etiqueta += tabs1 + "<descripcion>\n" + 
                tabs2 + descripcion + "\n" +
                tabs1 + "</descripcion>\n";
        }
        etiqueta += tabs + "</categoria>\n";
        return etiqueta;
    }

}
