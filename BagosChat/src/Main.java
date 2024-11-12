import DataBase.ConexionBDD;
import DataBase.Pruebas;
import Services.MenuService.MenuService;
import Services.UserService.UserService;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection conexion = ConexionBDD.conectar();
        if (conexion != null) {
            System.out.println("Conexion exitosa");
        } else {
            System.out.println("Error de conexion");
        }

        MenuService menuService = new MenuService();
        menuService.inicioMenu(conexion);


    }
}