package database.interfaces;

import entities.Users.User;
import services.UserService;

import java.util.List;

public interface UserDAO {

    void registerUser(User user);

    User findUser(String name, String password);

    void editUser(User user);

    List<User> getClientUsers();

    User getUser(int id);

}
