import controllers.Interface.IAirplaneController;
import controllers.Interface.IFlightController;
import controllers.Interface.ITicketController;
import controllers.Interface.IUserController;
import controllers.*;

import data.PostgresDB;
import data.IDB;

import repositories.UserRepository;
import repositories.rInterface.IUserRepository;
import repositories.FlightRepository;
import repositories.rInterface.IAirplaneRepository;
import repositories.rInterface.IFlightRepository;
import repositories.rInterface.ITicketRepository;
import repositories.AirplaneRepository;
import repositories.TicketRepository;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        PostgresDB db = PostgresDB.getInstance("localhost", "postgres", "password", "airline");

        Connection conn = db.getConnection();
        if (conn != null) {
            System.out.println("Connection to database successful!");
        } else {
            System.out.println("Error connecting to database.");
        }
        db.close();

        // Initialize repositories
        IUserRepository userRepo = new UserRepository(db);
        IFlightRepository flightRepo = new FlightRepository(db);
        IAirplaneRepository airplaneRepo = new AirplaneRepository(db);
        ITicketRepository ticketRepo = new TicketRepository(db);

        // Initialize controllers
        IUserController userController = new UserController(userRepo);
        IFlightController flightController = new FlightController(flightRepo);
        IAirplaneController airplaneController = new AirplaneController(airplaneRepo);
        ITicketController ticketController = new TicketController(ticketRepo);

        // Launch the application
        AirplaneTicketApp app = new AirplaneTicketApp(ticketController, airplaneController, flightController);
        app.start();

        db.close();
    }
}