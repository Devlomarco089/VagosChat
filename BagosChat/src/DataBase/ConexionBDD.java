package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionBDD {

    public static Connection conectar() {
        try {
            // Cargar el controlador de MySQL

            // URL de la base de datos
            String url = "jdbc:mysql://localhost:3306/BagosChat";
            String usuario = "root";
            String contrasena = "root";



            // Conectar a la base de datos
            return DriverManager.getConnection(url, usuario, contrasena);

        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
            return null;
        }
    }
}
