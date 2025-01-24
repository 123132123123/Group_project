import dao.*;
import classes.*;
import database.IDB;
import database.PostgresConnection;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresConnection("jdbc:postgresql://localhost:5432", "postgres", "0000", "aviatickets");

    }
}
