package database;

import java.sql.Connection;

public interface IDB {
    Connection getConnection();
}
