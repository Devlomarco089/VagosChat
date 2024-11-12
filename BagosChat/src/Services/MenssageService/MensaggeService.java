package Services.MenssageService;

import DatabaseServices.GroupService.GroupService;
import DatabaseServices.MenssagesService.MenssagesService;
import Domain.MessagesGroup;
import Domain.User;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MensaggeService {

    GroupService groupService = new GroupService();
    Scanner sc = new Scanner(System.in);

    public void listaMensajes(Connection conn, String id) throws SQLException {
         ResultSet rs = groupService.mensajes(conn,id);
        while (rs.next()){
            String nick = rs.getString("nombre_usuario");
            String idUser = rs.getString("usuario_id");
            String fecha = rs.getString("fecha_envio");
            String mensaje = rs.getString("mensaje");
            System.out.println(nick + "#" + idUser + ": " +  mensaje + ". " + fecha);
        }

    }


    public void mandarMensaje(Connection conn, User user, String id_grupo) throws SQLException {
        System.out.println("Escribir...");
        String mensaje = sc.nextLine();
        try {
            String sql = "INSERT INTO mensajes_grupo (grupo_id, mensaje, usuario_id, nombre_usuario, fecha_envio) VALUES (?, ?, ?, ?, NOW())";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id_grupo);
            stmt.setString(2, mensaje);
            stmt.setString(3, String.valueOf(user.getId()));
            stmt.setString(4,user.getNombre());
            stmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Error: El grupo con ID " + id_grupo + " no existe.");
            // Puedes agregar más lógica aquí, como registrar el error o ofrecer al usuario la opción de crear el grupo.
        }
    }

}
