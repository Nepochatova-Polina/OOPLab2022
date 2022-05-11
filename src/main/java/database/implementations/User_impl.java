package database.implementations;

import database.Connection_db;
import database.interfaces.UserDAO;
import entities.Administrator;
import entities.Client;
import entities.User;
import entities.UserRole;
import services.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class User_impl implements UserDAO {
    private static final Logger log = Logger.getLogger(User_impl.class.getName());

    private static final String USER_BY_ID_QUERY =
            "SELECT users.id, name, password, role FROM users WHERE users.id = ?";
    private static final String GET_ALL_USERS_QUERY =
            "SELECT users.id, name, password, role FROM users";
    private static final String CHECK_USER_QUERY =
            "SELECT users.id, users.role FROM users WHERE name = ? AND password = ?";
    private static final String ADD_USER_QUERY =
            "INSERT INTO users(id,name, password,role) VALUES (?,?, ?, ? )";

    @Override
    public void registerUser(User user) {
        if (user == null) {
            log.warning("Cannot register user because it was null.");
            return;
        }
        Connection con = Connection_db.getConnection();
        try (PreparedStatement prepareStatement = con.prepareStatement(ADD_USER_QUERY)) {
            prepareStatement.setInt(1, user.getId());
            prepareStatement.setString(2, user.getName());
            prepareStatement.setString(3, user.getPassword());
            prepareStatement.setString(4, String.valueOf(user.getRole()));
            if (prepareStatement.executeUpdate() <= 0) {
                log.warning("Cannot register user.");
            }
            log.warning("User successfully added!");
        } catch (SQLException ex) {
            log.warning("Problems with connection");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public UserService.UserInfo findUser(String name, String password) {
        log.info("Checking username and password");
        Connection connection = Connection_db.getConnection();
        log.info("Connected to the database.");
        try (PreparedStatement prepareStatement = connection.prepareStatement(CHECK_USER_QUERY)) {
            prepareStatement.setString(1, name);
            prepareStatement.setString(2, password);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                UserRole role = UserRole.valueOf(resultSet.getString(2).toUpperCase());
                log.info("Found user, redirecting to " + role + " page");
                return new UserService.UserInfo(id, role);
            }
        } catch (SQLException e) {
            log.warning("Problems with connection");
        }
        return null;
    }

    @Override
    public List<User> getClientUsers() {
        log.info("Getting client users from the database.");
        List<User> users = new ArrayList<>();
        Connection connection = Connection_db.getConnection();
        log.info("Connected to the database.");
        try (PreparedStatement prepareStatement = connection.prepareStatement(GET_ALL_USERS_QUERY)) {
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                User user = getUserFromResultSet(resultSet);
                if (user.getRole() == UserRole.CLIENT) users.add(user);
            }
        } catch (SQLException e) {
            log.warning("Problems with connection");
        }
        return users;
    }

    public User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        String password = resultSet.getString(3);
        UserRole type = UserRole.valueOf(resultSet.getString(4).toUpperCase());
        if (type == UserRole.ADMINISTRATOR) {
            return new Administrator(id, name, password);
        } else {
            return new Client(id, name, password);
        }
    }

    @Override
    public User getUser(int id) {
        User user = null;
        Connection connection = Connection_db.getConnection();
        log.info("Connected to the database.");
        try (PreparedStatement prepareStatement = connection.prepareStatement(USER_BY_ID_QUERY)) {
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                user = getUserFromResultSet(resultSet);
                log.info("Found user by id.");
            } else {
                log.info("Couldn't find user with the given id.");
            }
        } catch (SQLException e) {
            log.warning("Problems with connection");
        }
        return user;
    }
}
