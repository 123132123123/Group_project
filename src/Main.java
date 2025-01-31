import controllers.UserController;
import controllers.IUserController;
import controllers.FlightController;
import controllers.IAirplaneController;
import controllers.IFlightController;
import controllers.ITicketController;
import controllers.AirplaneController;
import controllers.TicketController;

import data.PostgresDB;
import data.IDB;

import repositories.UserRepository;
import repositories.IUserRepository;
import repositories.FlightRepository;
import repositories.IAirplaneRepository;
import repositories.IFlightRepository;
import repositories.ITicketRepository;
import repositories.AirplaneRepository;
import repositories.TicketRepository;

public class Main {
    public static void main(String[] args) {
        // Initialize database connection
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "0000", "aviatickets");

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
        AirplaneTicketApp app = new AirplaneTicketApp(userController, ticketController, airplaneController, flightController);
        app.start();

        db.close();
    }
}
