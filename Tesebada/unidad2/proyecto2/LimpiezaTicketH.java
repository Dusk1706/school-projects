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

public class LimpiezaTicketH {
    static Conexion conexion;
    public static void main(String[] args) {
        conexion = new Conexion();
        String rutaArchivo = "TicketH.CVS";
        try {
            
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
            String linea = br.readLine();
            String lineaX;
            System.out.println(linea);
            // FOLIO,FECHA,ESTADO,CIUDAD,TIENDA
            while ((lineaX = br.readLine()) != null) {
                lineaX = removerCaracteres(lineaX);
                String [] datos = lineaX.split(",");
                String folio = datos[0];
                String fecha = convertirFecha(datos[1]);
                int estado = Integer.parseInt(datos[2]);
                int ciudad = Integer.parseInt(datos[3]);
                int tienda = Integer.parseInt(datos[4]);
                
                insertarTicketH(folio, tienda, fecha);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error: Archivo no encontrado: " + e.getMessage());
        }
    }

    public static void insertarTicketH(String folio, int tienda, String fecha) {
        String sql = "INSERT INTO TICKETH (FOLIO, TIENDA, FECHA) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            ps.setString(1, folio);
            ps.setInt(2, tienda);
            ps.setString(3, fecha);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String removerCaracteres(String linea) {
        linea = linea.replace(" ", "");
        linea = linea.replace("$", "");
        linea = linea.replace(".00", "");
        return linea;
    }

    public static void insertarTiendas(HashMap<Integer, Integer> tiendas) {
        String sql = "INSERT INTO TIENDA_DIM (CLAVE, NOMBRE, TIPO, HORARIO_ATENCION, VENTAS_MENSUALES, CANTIDAD_EMPLEADOS, INVENTARIO, RENTABILIDAD, CIUDAD) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            for (Integer tienda : tiendas.keySet()) {
                PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
                ps.setInt(1, tienda);
                ps.setString(2, Rutinas.nombreTienda());
                ps.setString(3, Rutinas.tipoTienda());
                ps.setString(4, Rutinas.horarioAtencion());
                ps.setString(5, Rutinas.calidad());
                ps.setInt(6, Rutinas.nextInt(1, 100));
                ps.setString(7, Rutinas.tamaños());
                ps.setString(8, Rutinas.margenBeneficio());
                ps.setInt(9, tiendas.get(tienda));
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertarCiudades(HashMap<Integer, Integer> ciudades) {
        String sql = "INSERT INTO CIUDAD (CLAVE, NOMBRE, ESTADO) VALUES (?, ?, ?)";
        try {
            for (Integer ciudad : ciudades.keySet()) {
                PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
                ps.setInt(1, ciudad);
                ps.setString(2, Rutinas.ciudad());
                ps.setInt(3, ciudades.get(ciudad));
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String convertirFecha(String fechaOriginal) {
        DateFormatSymbols dfs = new DateFormatSymbols(new Locale("es", "MX"));
        String[] meses = { "ene", "feb", "mar", "abr", "may", "jun", "jul", "ago", "sep", "oct", "nov", "dic" };
        dfs.setShortMonths(meses);
        try {
            Date fecha = new SimpleDateFormat("dd/MMM/yy", dfs).parse(fechaOriginal);
            return new SimpleDateFormat("yyyy-MM-dd").format(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Error fecha " + fechaOriginal);
            return null;
        }
    }
}
