package unidad3.proyecto1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;

public class Main {
    static Conexion conexion;

    public static void main(String[] args) {
        // generarDatosEnArchivoViejoArff();
        generarDatosEnArchivoNuevoArff("creditosPython.arff", 200);
        // generarDatosEnBdNuevo();
    }

    public static void generarDatosEnBdNuevo() {
        conexion = new Conexion();
        for (int i = 0; i < 1_000_000; i++) {
            insertarCredito(new CreditoCriterioNuevo());
        }
    }

    public static void insertarCredito(CreditoCriterioNuevo credito) {
        String sql = "INSERT INTO CREDITO (NivelRenta, Patrimonio, TamanoCredito, EdadPeticionario, NumeroHijos, Funcionario, NivelEstudios, Autorizo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            ps.setString(1, credito.getRenta());
            ps.setString(2, credito.getPatrimonio());
            ps.setInt(3, credito.getCredito());
            ps.setInt(4, credito.getEdad());
            ps.setInt(5, credito.getHijos());
            ps.setString(6, credito.getFuncionario());
            ps.setString(7, credito.getEstudios());
            ps.setString(8, credito.getAutorizo());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void generarDatosEnArchivoViejoArff() {
        String nombreArchivo = "creditosCriterioViejo.arff";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            String texto = "@relation \"creditos\"\n@attribute nivelRenta {ALTO, MEDIO, BAJO}\n@attribute patrimonio {ALTO, MEDIO, BAJO}\n@attribute tamanoCredito numeric\n@attribute edadPeticionario numeric\n@attribute numeroHijos numeric\n@attribute funcionario {SI, NO}\n@attribute nivelEstudios {NINGUNO, LICENCIATURA, POSGRADO}\n@attribute autorizo {SI, NO}\n\n@data"
                    + "\n";
            bw.write(texto);
            for (int i = 0; i < 3_000_000; i++) {
                bw.write(new CreditoCriterioViejo() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generarDatosEnArchivoNuevoArff(String nombreArchivo, int numeroEjemplos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            String texto = "@relation \"creditos\"\n@attribute nivelRenta {ALTO, MEDIO, BAJO}\n@attribute patrimonio {ALTO, MEDIO, BAJO}\n@attribute tamanoCredito numeric\n@attribute edadPeticionario numeric\n@attribute numeroHijos numeric\n@attribute funcionario {SI, NO}\n@attribute nivelEstudios {NINGUNO, LICENCIATURA, POSGRADO}\n@attribute autorizo {SI, NO}\n\n@data\n";
            bw.write(texto);
            for (int i = 0; i < numeroEjemplos; i++) {
                bw.write(new CreditoCriterioNuevo() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}