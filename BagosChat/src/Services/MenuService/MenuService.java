package Services.MenuService;

import Domain.Contact;
import Domain.User;
import Services.ContactoService.ContactService;
import Services.GroupService.GroupService;
import Services.UserService.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuService {

UserService userService = new UserService();
    GroupService groupService = new GroupService();
    ContactService contactService = new ContactService();
    public void inicioMenu(Connection conn) throws SQLException {
        Scanner sc = new Scanner(System.in);
        boolean ciclo = true;
        while (ciclo) {
            System.out.println("Bienvenido a Bagos Chat");
            System.out.println("1.Iniciar Sesion");
            System.out.println("2.Registrarse");
            System.out.println("3.Salir");
            int eleccion = sc.nextInt();
            switch (eleccion) {
                case 1:
                    User instacia = userService.iniciariSesion(conn);
                    if (instacia != null) {
                        menu(conn,instacia);
                    }
                    break;
                case 2:
                    userService.registrarse(conn);
                    break;
                case 3:
                    ciclo = false;
                    break;
            }
        }
    }


    public void menu(Connection conn,User user) throws SQLException {
        Scanner sc = new Scanner(System.in);
        int eleccion;
        boolean sesion = true;
        while (sesion){
            System.out.println("Bienvenido "+ user.getNombre());
            System.out.println("1.Crear Grupo");
            System.out.println("2.Contactos");
            System.out.println("3.Grupos");
            System.out.println("4.Cerrar Sesion");
            eleccion = sc.nextInt();
            switch (eleccion){
                case 1:
                    groupService.crearGrupo(conn, String.valueOf(user.getId()));
                    break;
                case 2:
                    contactService.menuContact(conn,user);
                    break;
                case 3:
                    groupService.menuDeGrupoS(conn,user);
                    break;
                case 4:
                    sesion = false;
                    break;
            }
        }
    }

}
