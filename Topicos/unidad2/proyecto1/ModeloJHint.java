/*
 * Alumno: Dylan Michel Garcia Figueroa
 * Numero de control: 21170331
 * Materia: Topicos Avanzados de Programacion
 * Unidad: 2
 * Proyecto: Componentes
 * Horario: 9:00 a 10:00
 * Fecha: 11/04/2024
 * Profesor: Dr. Clemente Garcia Gerardo
 */

package unidad2.proyecto1;

import java.sql.ResultSet;

public class ModeloJHint {
    private Conexion conexion;

    public ModeloJHint() {
        conexion = new Conexion();
    }

    public String buscarHint(String hint) {
        String query = "SELECT urlImagen FROM hints WHERE urlImagen LIKE '" + hint + ".%'";
        //caballoso.extension
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
