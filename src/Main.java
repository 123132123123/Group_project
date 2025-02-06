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
        AirplaneTicketApp app = new AirplaneTicketApp(ticketController, airplaneController, flightController, new AuthController(userRepo));
        app.start();

        db.close();
    }
}
