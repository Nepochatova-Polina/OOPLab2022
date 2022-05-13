package entities.Users;

public class Administrator extends User {

    public Administrator(int id, String name, String password) {
        super(id, name, password, UserRole.ADMINISTRATOR);
    }

    public Administrator(String name, String password, UserRole type) {
        super(name, password, type);
    }
}
