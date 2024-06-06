package proyecto2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

public class LimpiezaTicketD {
    static Conexion conexion;
    static Random random = new Random();
    public static void main(String[] args) {
        conexion = new Conexion();
        random = new Random();
        String rutaArchivo = "TicketD.CVS";
        try {
            
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
            String linea = br.readLine();
            String lineaX;
            System.out.println(linea);
            // FOLIO,PRODUCTO,UNIDADES,PRECIO,TOTAL
            while ((lineaX = br.readLine()) != null) {
                lineaX = removerCaracteres(lineaX);

                String [] datos = lineaX.split(",");
                String folio = datos[0];
                int producto = Integer.parseInt(datos[1]);
                int unidades = Integer.parseInt(datos[2]);
                int precio = Integer.parseInt(datos[3]);
                insertarTicketD(folio, producto, unidades);
                
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error: Archivo no encontrado: " + e.getMessage());
            e.printStackTrace();    
        }
    }

    public static String removerCaracteres(String linea) {
        linea = linea.replace(" ", "");
        linea = linea.replace("$", "");
        linea = linea.replace(".00", "");
        return linea;
    }

    public static void insertarTicketD(String folio, int producto, int unidades) {
        String sql = "INSERT INTO TICKETD (FOLIO, PRODUCTO, UNIDADES) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            ps.setString(1, folio);
            ps.setInt(2, producto);
            ps.setInt(3, unidades);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void insertarProductos(HashMap<Integer, Integer> productos) {
        String sql = "INSERT INTO PRODUCTO_DIM (CLAVE, NOMBRE, PESO, COLOR, MARCA, CATEGORIA, CALIDAD, ORIGEN, DIMENSIONES, MARGEN_BENEFICIO, PRECIO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            for(Integer producto : productos.keySet()) {
                PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
                ps.setInt(1, producto);
                ps.setString(2, Rutinas.producto());
                ps.setString(3, Rutinas.nextInt(1, 1000) + "g");
                ps.setString(4, Rutinas.color());
                ps.setString(5, Rutinas.marca());
                ps.setString(6, Rutinas.categoria());
                ps.setString(7, Rutinas.calidad());
                ps.setString(8, Rutinas.origen());
                ps.setString(9, Rutinas.origen());
                ps.setString(10, Rutinas.margenBeneficio());
                ps.setInt(11, productos.get(producto));
                ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    
}
