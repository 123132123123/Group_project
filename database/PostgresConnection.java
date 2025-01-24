package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection {
    private static final String URL = "";
    private static final String USER = "asem";
    private static final String PASSWORD = "0000";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }

