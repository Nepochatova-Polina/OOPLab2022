package database;

import database.implementations.User_impl;
import java.sql.*;
import java.util.logging.Logger;


public class Connection_db {
    private static final Logger log = Logger.getLogger(User_impl.class.getName());
    private static Connection connection;
    private static Connection_db c_db = new Connection_db();

    private Connection_db(){
        String url = "jdbc:postgresql://localhost:15435/postgres";
        String login = "postgres";
        String password = "postgres";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            log.warning("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }
        log.info("PostgreSQL JDBC Driver successfully connected");
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            log.warning("Connection Failed");
            e.printStackTrace();
            return;
        }
        if (connection != null) {
            log.info("You successfully connected to database now");
        } else {
           log.warning("Failed to make connection to database");
        }
    }

    public static Connection getConnection(){
        return connection;
    }
    public static Connection_db getC_db() { return c_db; }

    public void stop() throws SQLException {
        connection.close();
    }
}
