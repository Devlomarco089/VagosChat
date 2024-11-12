package DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Pruebas {


    public void consultar(Connection conn) throws SQLException {

        String consulta = "select * from usuario";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(consulta);
        while (rs.next()){
            String nombre = rs.getNString("nombre");
            System.out.println(nombre);
        }

    }

}
