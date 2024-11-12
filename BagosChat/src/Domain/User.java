package Domain;

import java.util.List;

public class User {
    public String Nombre;
    public int Id;
    public List<Contact> contacts;


    public User(String nombre, int id) {
        Nombre = nombre;
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
