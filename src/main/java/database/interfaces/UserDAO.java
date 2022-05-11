package database.interfaces;

import entities.User;
import services.UserService;

import java.util.List;

public interface UserDAO {

    void registerUser(User user);

    UserService.UserInfo findUser(String name, String password);

    List<User> getClientUsers();

    User getUser(int id);

}
