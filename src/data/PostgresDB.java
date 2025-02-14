package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDB implements IDB {
    private static PostgresDB instance;
    private Connection connection;
    private final String url;
    private final String username;
    private final String password;

    private PostgresDB(String host, String username, String password, String dbName) {
        this.url = "jdbc:postgresql://" + host + "/" + dbName;
        this.username = username;
        this.password = password;
    }

    public static synchronized PostgresDB getInstance(String host, String username, String password, String dbName) {
        if (instance == null) {
            instance = new PostgresDB(host, username, password, dbName);
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    return connection;
                }
            } catch (SQLException ignored) {}
        }
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("failed to connect to database: " + e.getMessage());
        }
        return connection;
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                System.out.println("failed to close connection: " + e.getMessage());
            }
        }
    }
}