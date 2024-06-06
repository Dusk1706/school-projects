package proyecto2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;


public class LimpiezaSynergy {
    static Conexion conexion;
    static HashMap<String, String> paises = new HashMap<String, String>();
    public static void main(String[] args) {
        conexion = new Conexion();
        inicializarPaises();
        String rutaArchivo = "synergy_logistics_database111.csv";
        try {
            
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
            String linea = br.readLine();
            String lineaX;
            System.out.println(linea);
            // id , direction, origin,destination,year,date,product,transport_mode,company_name,total_value
            while ((lineaX = br.readLine()) != null) {
                String [] datos = lineaX.split(",");
                
                String direccion = datos[1];
                String origen = datos[2];
                String destino = datos[3];
                
                boolean valido = validarDatos(direccion, origen, destino);
                if (!valido) {
                    continue;
                }
                String paisOrigen = getPaisOrigen(direccion, origen, destino);
                String transporte = getMedioTransporte(datos[7]);
                String fecha = convertirFecha(datos[5]);
                String importe = datos[9];
                insertarDatos(paisOrigen, transporte, fecha, importe);

            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error: Archivo no encontrado: " + e.getMessage());
        }
    
    }

    private static void inicializarPaises() {
        paises = new HashMap<String, String>();
        paises.put("MEXICO", "MEXICO");
        paises.put("USA", "ESTADOS UNIDOS");
        paises.put("JAPAN", "JAPON");
        paises.put("GERMANY", "ALEMANIA");
        paises.put("NETHERLANDS", "PAISES BAJOS");
        paises.put("CHINA", "CHINA");
        paises.put("CANADA", "CANADA");
        paises.put("BRAZIL","BRASIL");
        paises.put("SOUTH KOREA", "COREA DEL SUR");
        paises.put("AUSTRALIA", "AUSTRALIA");
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
            System.out.println(fecha + " " + medioTransporte + " " + paisOrigen + " " + importe);
            System.out.println("Error: " + e.getMessage());
        }

    }

    public static String getMedioTransporte(String transporte) {
        transporte = transporte.toUpperCase();
        if (transporte.equals("SEA")) {
            return "MAR";
        } else if (transporte.equals("AIR")) {
            return "CIELO";
        } else if (transporte.equals("RAIL")) {
            return "TREN";
        }
        return "CARRETERA";
    }

    public static String getPaisOrigen(String direccion, String origen, String destino) {
        destino = destino.toUpperCase();
        origen = origen.toUpperCase();
        String pais;
        if (direccion.equals("Imports")) {
            pais = paises.get(destino);
        } else {
            pais = paises.get(origen);
        }
        
        return pais;
    }

    public static boolean validarDatos(String direccion, String origen, String destino) {
        boolean condicion1 = direccion.equals("Imports") 
            && origen.equals("Mexico");
        boolean condicion2 = direccion.equals("Exports") 
            && destino.equals("Mexico");
        return condicion1 || condicion2;
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

    


}