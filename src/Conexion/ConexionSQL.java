package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQL {

    String url = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=JoyeriaHernandez;"
            + "user=Merinfap;"
            + "password=123456";
    private Connection conectar = null;

    public ConexionSQL() {
    }

    public Connection obtener_conexion() {
        try {
            conectar = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            System.out.println("Error al intentar conectarse a la base de datos!! " + ex);
        }
        if (conectar != null) {
            System.out.println("Conectado a la base de datos....");
        }
        return conectar;
    }
}
