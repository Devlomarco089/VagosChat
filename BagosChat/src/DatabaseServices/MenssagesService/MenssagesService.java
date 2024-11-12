package DatabaseServices.MenssagesService;

import Domain.MenssagePrivate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MenssagesService {


    public  static Scanner sc = new Scanner(System.in);

    public void enviarMensajePrivado(Connection conn, int emisor, int receptor) throws SQLException {

        System.out.println("Escribir...");
        String mensaje = sc.nextLine();

        String sql = "INSERT INTO mensajes (emisor_id, receptor_id, contenido, fecha_envio) VALUES (?, ?, ?, NOW())";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, emisor);
        stmt.setInt(2, receptor);
        stmt.setString(3, mensaje);
        stmt.executeUpdate();
    }

    public ResultSet chat(Connection conn,String id,String id2) throws SQLException {
        String sql = "SELECT * FROM mensajes WHERE emisor_id = ? AND receptor_id = ? OR emisor_id = ? AND receptor_id = ? ORDER BY fecha_envio ASC";
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1,id);
        stat.setString(2,id2);
        stat.setString(3,id2);
        stat.setString(4,id);
        return stat.executeQuery();
    }


}
