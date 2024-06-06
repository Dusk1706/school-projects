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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
  private String url;
  private Statement statement;
  private Connection conn;

  public Conexion() {
    // String ip = "localhost";
    
    url = "jdbc:sqlserver://localhost;"+
        "databaseName=Hints;" +
        "user=sa;" +
        "password=123;" +
        "encrypt=true; trustServerCertificate=true;";

    iniciarConexion();
  }

  public void iniciarConexion() {
    try {
      conn = DriverManager.getConnection(url);
      statement = conn.createStatement();
    } catch (SQLException e) {
      System.out.print(e.getMessage());
    }

    if (statement == null) {
      System.out.println("No se hizo la conexion");
    } else {
      System.out.println("Conexion exitosa");
    }
  }

  public void setStatement() {
    try {
      statement = conn.createStatement();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  synchronized public Statement getStatement() {
    return statement;
  }

  synchronized public Connection getConexion() {
    return conn;
  }

  synchronized public void cierraConexion() {
    try {
      conn.close();
      statement.close();
    } catch (SQLException E) {

    }
  }

}