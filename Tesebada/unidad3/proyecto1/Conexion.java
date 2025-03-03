
package unidad3.proyecto1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
  private String url;
  private Statement statement;
  private Connection conn;

  public Conexion() {
    url = "jdbc:sqlserver://localhost;"+
        "databaseName=TesebadaUnidad3;" +
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