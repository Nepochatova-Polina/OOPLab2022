package entities;

public class Administrator extends User{
    public Administrator(int id, String name, String password, UserRole type) {
        super(id, name, password, type);
    }
}
