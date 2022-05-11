package database;

import org.postgresql.jdbc2.optional.ConnectionPool;

import java.sql.*;


public class Connection_db {
    public static Connection connection;
    public static Statement statement;
//    private static final Logger log = Logger.getLogger(Connection.class.getName());

    public Connection_db() throws Exception {
        String url = "jdbc:postgresql://localhost:15435/postgres";
        String login = "postgres";
        String password = "postgres";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
//            log.warning("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }
//        log.info("PostgreSQL JDBC Driver successfully connected");
        System.out.println("PostgreSQL JDBC Driver successfully connected");
        try {
            connection = DriverManager.getConnection(url, login, password);

        } catch (SQLException e) {
//            log.warning("Connection Failed");
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
        if (connection != null) {
//            log.info("You successfully connected to database now");
            System.out.println("You successfully connected to database now");
        } else {
//           log.warning("Failed to make connection to database");
            System.out.println("Failed to make connection to database");
        }
        statement = connection.createStatement();
    }

    public static Connection getConnection() {
        return connection;
    }

    public void stop() throws SQLException {
        connection.close();
    }
}

