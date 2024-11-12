package Services.UserService;

import Domain.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserService {
    Scanner sc = new Scanner(System.in);
    DatabaseServices.UserServices.UserService userService = new DatabaseServices.UserServices.UserService();

    public User iniciariSesion(Connection conn) throws SQLException {
        User user = null;
        System.out.println("Ingrese su nombre de usuario: ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese su contraseña: ");
        String contrasena = sc.nextLine();
        ResultSet rs = userService.loginUser(conn, nombre, contrasena);
        if(rs != null){
            user = new User(rs.getString("username"), rs.getInt("id"));
        }
        return user;
    }


    public void registrarse(Connection conn) throws SQLException {
        System.out.println("Ingrese un nombre de usuario:  ");
        String nombre = sc.nextLine();
        String contrasena = "";
        String contrasenaV;

        boolean ciclo = true;
        while (ciclo) {
            System.out.println("Ingrese su contraseña: ");
            contrasena = sc.nextLine();
            System.out.println("Repita su contraseña:");
            contrasenaV = sc.nextLine();
            if (contrasena.equals(contrasenaV)) {
                break;
            }
            System.out.println("las contraseñas no coinciden");
        }
        userService.registrationUser(conn,nombre,contrasena);
    }
}
