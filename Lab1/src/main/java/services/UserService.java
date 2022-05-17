package services;

import database.implementations.User_impl;
import database.interfaces.UserDAO;
import entities.Users.User;

import java.util.List;

public class UserService {


    public static void registerUser(User user) {
        UserDAO userDao = new User_impl();
        userDao.registerUser(user);
    }

    public static User findUser(String name, String password) {
        UserDAO userDao = new User_impl();
        return userDao.findUser(name, password);
    }
    public static void editUser(User user){
        UserDAO userDao = new User_impl();
        userDao.editUser(user);
    }
    public static List<User> getClientUsers() {
        UserDAO userDao = new User_impl();
        return userDao.getClientUsers();
    }

    public static User getUser(int id) {
        UserDAO userDao = new User_impl();
        return userDao.getUser(id);
    }


}
