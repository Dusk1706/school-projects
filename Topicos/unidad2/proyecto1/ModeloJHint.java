package unidad2.proyecto1;

import java.sql.ResultSet;

public class ModeloJHint {
    private Conexion conexion;

    public ModeloJHint() {
        conexion = new Conexion();
    }

    public String buscarHint(String hint) {
        String query = "SELECT urlImagen FROM hints WHERE urlImagen LIKE '" + hint + ".%'";
        System.out.println(query);
        try {
            conexion.setStatement();
            ResultSet rs = conexion.getStatement().executeQuery(query);
            System.out.println("NO hubo error");
            return rs.getString(1);
        } catch (Exception e) {
            System.out.println("Hubo un error " + e.getMessage());
            return null;
        }
    }
}
