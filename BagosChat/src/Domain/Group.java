package Domain;

public class Group {
    public int Id;
    public String Nombre;
    public User Idadmin;

    public Group(int id, String nombre) {
        Id = id;
        Nombre = nombre;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public User getIdadmin() {
        return Idadmin;
    }

    public void setIdadmin(User idadmin) {
        Idadmin = idadmin;
    }
}
