package unidad2.proyecto1;

import java.sql.ResultSet;

public class ModeloJHint {
    private Conexion conexion;

    public ModeloJHint() {
        conexion = new Conexion();
    }

    public String buscarHint(String hint) {
        String query = "SELECT urlImagen FROM hints WHERE urlImagen LIKE '" + hint + ".%'";
        try {
            conexion.setStatement();
            ResultSet rs = conexion.getStatement().executeQuery(query);
            rs.next();
            return rs.getString(1);
        } catch (Exception e) {
            return null;
        }
    }
}
