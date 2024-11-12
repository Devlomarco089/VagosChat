package Services.ContactoService;

import DatabaseServices.ContactService.ContacServices;
import DatabaseServices.MenssagesService.MenssagesService;
import DatabaseServices.UserServices.UserService;
import Domain.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ContactService {


    ContacServices contacServices = new ContacServices();
    Scanner sc = new Scanner(System.in);
    UserService userService = new UserService();
    MenssagesService menssagesService = new MenssagesService();

    public void listaSolicitud(Connection conn,String id) throws SQLException {
        ResultSet rs = contacServices.listaSolic(conn,id);
        if (rs != null){
            while (rs.next()){
                String nombre = rs.getString("username");
                String idS = rs.getString("usuario1_id");
                System.out.println(nombre + "#" + idS);
            }
        }
    }
    public void listaContactos(Connection conn,String id,User user) throws SQLException {
        ResultSet rs = contacServices.listaAmistad(conn, id);
        if (rs != null && rs.next()) {
            do {
                int amigoId;
                if (user.getId() != rs.getInt("usuario1_id")) {
                    amigoId = rs.getInt("usuario1_id");
                } else {
                    amigoId = rs.getInt("usuario2_id");
                }
                User friend = userService.buscarUsuario(conn, String.valueOf(amigoId));
                System.out.println(friend.getNombre()+ "#" +friend.getId());


            } while (rs.next());
        } else {
            // Handle case where no results are found
            System.out.println("No contacts found.");
        }

        // Close the result set (assuming contacServices doesn't handle it)
        if (rs != null) {
            rs.close();
        }
    }
    public void enviarMensaje(Connection conn,int idemisor,int id) throws SQLException {
        menssagesService.enviarMensajePrivado(conn,idemisor,id);
    }

    public void enviarSolicitud(Connection conn,String id,String id2,String nombre) throws SQLException {
        contacServices.enviarSolicitudAmistad(conn, id,id2,nombre);
    }

    public void aceptarSolicitud(Connection conn, User user,String id) throws SQLException {;
        contacServices.aceptarSolicitudAmistad(conn, String.valueOf(user.getId()),id);
    }

    public void entrarChat(Connection conn,String id1,String id2) throws SQLException {
        ResultSet rs = menssagesService.chat(conn,id1,id2);
        while (rs.next()){
            System.out.println(rs.getString("emisor_id") + ": " + rs.getString("contenido"));
        }
    }


    public void menuContact(Connection conn,User user) throws SQLException {
        System.out.println("1.Solicitudes");
        System.out.println("2.Contactos");
        int eleccion = sc.nextInt();
        switch (eleccion){
            case 1:
                listaSolicitud(conn, String.valueOf(user.getId()));
                System.out.println("1.Aceptar solicitud por id: ");
                System.out.println("0.Salir");
                int nele = sc.nextInt();
                if(nele != 0){
                    aceptarSolicitud(conn,user, String.valueOf(nele));
                }
                break;
            case 2:
                listaContactos(conn, String.valueOf(user.getId()),user);
                System.out.println("1.Entrar al Chat por id: ");
                System.out.println("2.Agregar Contacto");
                System.out.println("3.Atras");
                int i = sc.nextInt();
                switch (i){
                    case 1:
                        System.out.println("Ingrese id: ");
                        String elec = sc.next();
                        entrarChat(conn, String.valueOf(user.getId()),elec);
                        System.out.println("1.Enviar Mensaje");
                        System.out.println("2.Atras");
                        int s = sc.nextInt();
                        switch (s){
                            case 1:enviarMensaje(conn,user.getId(), Integer.parseInt(elec));
                            break;
                            case 2:
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("Id de usuario: ");
                        String iduss = sc.next();
                        enviarSolicitud(conn, String.valueOf(user.getId()),iduss,user.getNombre());
                        break;
                    case 3:
                        break;
                }
        }
    }



}
