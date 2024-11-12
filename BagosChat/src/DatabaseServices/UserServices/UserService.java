package DatabaseServices.UserServices;

import Domain.User;

import java.sql.*;
import java.util.Scanner;

public class UserService {
    Scanner sc = new Scanner(System.in);

    public void registrationUser(Connection conn,String nombre,String contrasena) throws SQLException {
        String sql = "INSERT INTO users (username,password) VALUES (?,?)";
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1,nombre);
        stat.setString(2,contrasena);
        stat.executeUpdate();
    }

    public ResultSet loginUser(Connection conn,String nombre,String contrasena) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        PreparedStatement stat = conn.prepareStatement(sql);

        stat.setString(1,nombre);
        stat.setString(2,contrasena);

        ResultSet rs = stat.executeQuery();

        if(rs.next()){
            System.out.println("conexion exitosa");
            return rs;
        }else {
            System.out.println("Usuario o contraseña incorrectas");
            return null;
        }

    }

    public User buscarUsuario(Connection conn, String id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1,id);
        ResultSet rs = stat.executeQuery();
        User user = null;
        if (rs.next()){
            user = new User(rs.getString("username"),rs.getInt("id"));
        }
        return  user;
    }



}
