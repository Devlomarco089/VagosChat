package DatabaseServices.GroupService;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class GroupService {

    static Scanner sc = new Scanner(System.in);


    public ResultSet seleGrupo(Connection conn,String id_grupo) throws SQLException {
        String sql = "SELECT * FROM grupos WHERE id = ?";
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1,id_grupo);
        return stat.executeQuery();
    }

    public ResultSet buscarGrupos(Connection conn,String idUsuario) throws SQLException {
        // La consulta SQL (adaptada de la consulta anterior)
        String sql = "SELECT g.id, g.name FROM miembros_grupos mg INNER JOIN grupos g ON mg.id_grupo = g.id WHERE mg.id_usuario = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, idUsuario);

        return statement.executeQuery();
    }

    public void crearGrupo(Connection conn,String nombreGrupo,String id_admin) throws SQLException, SQLException {
        String sql = "INSERT INTO grupos (name,id_admin) VALUES (?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, nombreGrupo);
        stmt.setString(2,id_admin);
        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            int generatedId = rs.getInt(1);
            agregarMiembros(conn, id_admin, generatedId);
        } else {
            System.out.println("Error al obtener el ID generado");
        }

    }

    public void agregarMiembros(Connection conn,String idUsuario,int idGrupo) throws SQLException {
        String sql = "INSERT INTO miembros_grupos (id_usuario, id_grupo) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, idUsuario);
        stmt.setInt(2, idGrupo);
        stmt.executeUpdate();
    }


    public void eliminarMiembro(Connection conn, int idGrupo) throws SQLException {
        System.out.print("Ingrese el ID del usuario a eliminar del grupo: ");
        int idUsuario = sc.nextInt();

        String sql = "DELETE FROM miembros_grupo WHERE id_grupo = ? AND id_usuario = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idGrupo);
        stmt.setInt(2, idUsuario);
        stmt.executeUpdate();
        stmt.close();

        System.out.println("Miembro eliminado del grupo exitosamente.");
    }

    public void eliminarGrupo(Connection conn,int idGrupo) throws SQLException {

        String sql = "DELETE FROM grupos WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idGrupo);
        stmt.executeUpdate();

        System.out.println("Grupo eliminado exitosamente.");
    }

    public ResultSet mensajes(Connection connection,String id_grupo) throws SQLException {
        String sql = "select * from mensajes_grupo WHERE grupo_id = ?";
        PreparedStatement stat = connection.prepareStatement(sql);
        stat.setString(1,id_grupo);
        return stat.executeQuery();
    }

    public ResultSet miembros(Connection conn,String id_grupo) throws SQLException {
        String sql = "SELECT * FROM miembros_grupos WHERE id_grupo = ?";
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1,id_grupo);
        return  stat.executeQuery();
    }



}
