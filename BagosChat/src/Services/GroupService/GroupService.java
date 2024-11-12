package Services.GroupService;

import DatabaseServices.MenssagesService.MenssagesService;
import Domain.Group;
import Domain.User;
import Services.MenssageService.MensaggeService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GroupService {

    DatabaseServices.GroupService.GroupService dGroupService = new DatabaseServices.GroupService.GroupService();
    Scanner sc = new Scanner(System.in);
    MensaggeService mensaggeService = new MensaggeService();

    public void crearGrupo(Connection conn,String id) throws SQLException {
        Group grupo = null;
        System.out.print("Ingrese el nombre del grupo: ");
        String nombreGrupo = sc.nextLine();
        dGroupService.crearGrupo(conn,nombreGrupo,id);

    }

    public void listaGrupos(Connection conn, User user) throws SQLException {
        ResultSet resultSet = dGroupService.buscarGrupos(conn, String.valueOf(user.getId()));
        while (resultSet.next()) {
            String nombreGrupo = resultSet.getString("name");
            String idGrupo = resultSet.getString("id");
            System.out.println("Grupo: " + nombreGrupo + " Id: " + idGrupo);
        }
        if (resultSet == null){
            System.out.println("No estas en ningun grupo.");
        }
        resultSet.close();
    }


    public Group grupos(Connection conn,User user) throws SQLException {
        Scanner sc = new Scanner(System.in);
        int eleccion;
        listaGrupos(conn, user);
        System.out.println("Seleccione un grupo por su id: ");
        eleccion = sc.nextInt();
        ResultSet rs = dGroupService.seleGrupo(conn, String.valueOf(eleccion));
        if (rs.next()) {
            Group grupo = new Group(rs.getInt("id"), rs.getNString("name"));
            return grupo;
        } else {
            System.out.println("No group found with that ID.");
            return null;
        }
    }


    public void agregarMiembro(Connection conn,User user,Group grupo) throws SQLException {
        dGroupService.agregarMiembros(conn, String.valueOf(user.getId()),grupo.getId());
    }


    public void menuDeGrupoS(Connection conn,User user) throws SQLException {
        Group grupo = grupos(conn,user);
        mensaggeService.listaMensajes(conn, String.valueOf(grupo.getId()));
        System.out.println("1.Mandar Mensaje");
        System.out.println("2.Otras opciones");
        System.out.println("3.Volver");
        int eleccion = sc.nextInt();
        switch (eleccion){
            case 1:
                mensaggeService.mandarMensaje(conn,user, String.valueOf(grupo.getId()));
                break;
            case 2:
                opcionesGrupo(conn,grupo);
                break;
            case 3:
                break;
        }
    }

    public void opcionesGrupo(Connection conn,Group grupo) throws SQLException {
        System.out.println("1.Agregar Miembro");
        System.out.println("2.Eliminar Miembro");
        System.out.println("3.ListaMiembros");
        System.out.println("4.Eliminar Grupo");
        System.out.println("Ingrese una opcion: ");
        int eleccion = sc.nextInt();
        switch (eleccion){
            case 1:
                System.out.println("Ingrese id de usuario: ");
                String id = sc.next();
                dGroupService.agregarMiembros(conn,id,grupo.getId());
                break;
            case 2:
                System.out.println("Ingrese id de usuario: ");
                int idu = sc.nextInt();
                dGroupService.eliminarMiembro(conn,idu);
                break;
            case 3:
                System.out.println("Lista de miembros: ");
                listaMiembros(conn, String.valueOf(grupo.getId()));
                break;
            case 4:
                System.out.println("ATENCION: ESTAS POR ELIMINAR EL GRUPO SE ELIMINARAN TODOS LOS MENSAJES Y MIEMBROS");
                System.out.println("1.Confirmar");
                System.out.println("2.Cancelar");
                int elec = sc.nextInt();
                switch (elec){
                    case 1:
                        dGroupService.eliminarGrupo(conn,grupo.getId());
                        break;
                    case 2:
                        break;
                }
        }
    }

    public void listaMiembros(Connection conn,String id_grupo) throws SQLException {
        ResultSet rs = dGroupService.miembros(conn,id_grupo);
        while (rs.next()){
            String id = rs.getString("id_usuario");
            System.out.println("Id usuario: " + id);
        }

    }

}
