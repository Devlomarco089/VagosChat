package DatabaseServices.ContactService;

import Domain.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContacServices {





    public void enviarSolicitudAmistad(Connection conn,String idUsuarioOrigen, String idUsuarioDestino,String nombre) throws SQLException {
        String sql = "INSERT INTO solicitud (usuario1_id, usuario2_id, username) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, idUsuarioOrigen);
        stmt.setString(2, idUsuarioDestino);
        stmt.setString(3,nombre);
        stmt.executeUpdate();
        stmt.close();
    }

    public ResultSet listaSolic(Connection conn, String id) throws SQLException {
        String sql = "SELECT * FROM solicitud WHERE usuario2_id = ?";
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1,id);
        return stat.executeQuery();
    }

    public void aceptarSolicitudAmistad(Connection conn,String idUsuario1,String idUsuario2) throws SQLException {
        String sql = "INSERT INTO amigos (usuario1_id, usuario2_id) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, idUsuario1);
        stmt.setString(2, idUsuario2);
        stmt.executeUpdate();
        String sql2 = "DELETE FROM solicitud WHERE usuario2_id = ?";
        PreparedStatement stat2 = conn.prepareStatement(sql2);
        stat2.setString(1,idUsuario2);
    }

    public ResultSet listaAmistad(Connection conn,String id) throws SQLException {
        String sql = "SELECT * FROM amigos WHERE usuario1_id = ? OR usuario2_id = ?";
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1,id);
        stat.setString(2,id);
        return  stat.executeQuery();
    }
}
