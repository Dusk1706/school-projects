package proyecto2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Random;


public class LimpiezaImportacionAutos {
    static Conexion conexion;
    public static void main(String[] args) {
        String rutaArchivo = "ImportacionAutos111.csv";
        conexion = new Conexion();
        try {
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
            String linea = br.readLine();
            String lineaX;
            System.out.println(linea);
            while ((lineaX = br.readLine()) != null) {
                String [] datos = lineaX.split(",");
                
                String estado = datos[0];
                
                String marca = datos[2];
                String fecha = convertirFecha(datos[5]);
                String importe = datos[6];
                String paisOrigen = getPaisOrigen(marca);
                String medioTransporte = getMedioTransporte(fecha, estado);
                
                insertarDatos(paisOrigen, medioTransporte, fecha, importe);
            }

            br.close();
        } catch (Exception e) {
            System.out.println("Error: Archivo no encontrado: " + e.getMessage());
            e.printStackTrace();
        }
    
    }

    public static void insertarDatos(String paisOrigen, String medioTransporte, String fecha, String importe) {
        String sql = "INSERT INTO IMPORTES_AUX (FECHA, TRANSPORTE, PAIS, IMPORTE, UNIDADES) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            ps.setString(1, fecha);
            ps.setString(2, medioTransporte);
            ps.setString(3, paisOrigen);
            ps.setString(4, importe);
            ps.setInt(5, 1);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public static String convertirFecha(String fechaOriginal) {
        try {
            Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fechaOriginal);

            return new SimpleDateFormat("yyyy-MM-dd").format(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getPaisOrigen(String marca) {
        marca = marca.toUpperCase();
        if (marca.equals("VW") || marca.equals("DINA")) {
            return "JAPON";
        }
        
        return "ESTADOS UNIDOS";
    }

    public static String getMedioTransporte(String fecha, String estado) {
        String [] fechaArray = fecha.split("-");
        int year = Integer.parseInt(fechaArray[0]);
        if (year == 2020) {
            return getTransporte2020(estado);
        }
        if (year == 2021) {
            return getTransporte2021();
        }

        return null;
    }

    public static String getTransporte2020(String estado) {
        estado = estado.toUpperCase();
        if (estado.equals("NAY")) {
            return "TREN";
        }
        return "CARRETERA";
    }

    public static String getTransporte2021() {
        Random random = new Random();
        String [] transportes  = {"CARRETERA", "MAR", "CIELO", "RIELES"};
        return transportes[random.nextInt(4)];
    }

}