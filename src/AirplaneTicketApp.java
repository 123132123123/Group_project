import controllers.AuthController;
import controllers.Interface.ITicketController;
import controllers.Interface.IAirplaneController;
import controllers.Interface.IFlightController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AirplaneTicketApp {
    private final ITicketController ticketController;
    private final IAirplaneController airplaneController;
    private final IFlightController flightController;
    private AuthController authController;
    private final Scanner scanner = new Scanner(System.in);

    public AirplaneTicketApp(ITicketController ticketController, IAirplaneController airplaneController, IFlightController flightController, AuthController authController) {
        this.ticketController = ticketController;
        this.airplaneController = airplaneController;
        this.flightController = flightController;
        this.authController = authController;
    }

    private void loginMenu() {
        System.out.println("Welcome to Airplane Ticket Management!");
        System.out.println("Do you have acaccount?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.println("0. Exit");
        System.out.print("Select an option (1-2): ");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                authController.login();
            case 2:
                authController.register();
                loginMenu();
            case 0:
                System.out.println("Exiting the application. Goodbye!");
                System.exit(0);
            default:
                System.out.println("Please select a valid option!");
        }
    }

    private void adminMenu() {
        System.out.println();
        System.out.println("Select one of the following options:");
        System.out.println("1. Manage Tickets");
        System.out.println("2. Manage Airplanes");
        System.out.println("3. Manage Flights");
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Select an option (1-3): ");
    }

    private void mainMenu() {
        System.out.println();
        System.out.println("Select one of the following options:");
        System.out.println("1. Manage Tickets");
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Select an option (0-1): ");
    }

    public void start() {
        while (true) {
            loginMenu();
            if(authController.isAdministrator()) {
                adminMenu();
            }
            else {
                mainMenu();
            }
            try {
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        manageTicketsMenu();
                        break;
                    case 2:
                        manageAirplanesMenu();
                        break;
                    case 3:
                        manageFlightsMenu();
                        break;
                    case 0:
                        System.out.println("Exiting the application. Goodbye!");
                        return;
                    default:
                        System.out.println("Please select a valid option!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
            System.out.println("----------------------------------------");
        }
    }

    // Manage Flights
    private void manageFlightsMenu() {
        System.out.println("1. Create a Flight");
        System.out.println("2. View all Flights");
        System.out.println("3. View Flight by ID");
        System.out.println("4. Update Seats in a Flight");
        System.out.println("0. Back to Main Menu");
        System.out.print("Select an option (1-4): ");

        int option = scanner.nextInt();
        switch (option) {
            case 1:
                createFlightMenu();
                break;
            case 2:
                viewAllFlightsMenu();
                break;
            case 3:
                viewFlightByIdMenu();
                break;
            case 4:
                updateFlightSeatsMenu();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid option!");
        }
    }

    private void createFlightMenu() {
        System.out.println("Enter departure city:");
        String departureCity = scanner.next();
        System.out.println("Enter arrival city:");
        String arrivalCity = scanner.next();
        System.out.println("Enter departure time (HHMM):");
        int departureTime = scanner.nextInt();
        System.out.println("Enter arrival time (HHMM):");
        int arrivalTime = scanner.nextInt();
        System.out.println("Enter number of seats:");
        int seats = scanner.nextInt();
        System.out.println("Enter price:");
        double price = scanner.nextDouble();

        String response = flightController.createFlight(departureCity, arrivalCity, departureTime, arrivalTime, seats, price);
        System.out.println(response);
    }

    private void viewAllFlightsMenu() {
        System.out.println("All flights:");
        System.out.println(flightController.getAllFlights());
    }

    private void viewFlightByIdMenu() {
        System.out.println("Enter flight ID:");
        int id = scanner.nextInt();
        System.out.println(flightController.getFlightById(id));
    }

    private void updateFlightSeatsMenu() {
        System.out.println("Enter flight ID:");
        int id = scanner.nextInt();
        System.out.println("Enter new number of seats:");
        int seats = scanner.nextInt();

        String response = flightController.updateFlightSeats(id, seats);
        System.out.println(response);
    }

    // Manage Airplanes
    private void manageAirplanesMenu() {
        System.out.println("1. Add an Airplane");
        System.out.println("2. View all Airplanes");
        System.out.println("3. View Airplane by ID");
        System.out.println("0. Back to Main Menu");
        System.out.print("Select an option (1-3): ");

        int option = scanner.nextInt();
        switch (option) {
            case 1:
                createAirplaneMenu();
                break;
            case 2:
                viewAllAirplanesMenu();
                break;
            case 3:
                viewAirplaneByIdMenu();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid option!");
        }
    }

    private void createAirplaneMenu() {
        System.out.println("Enter total seats for the airplane:");
        int totalSeats = scanner.nextInt();
        System.out.println(airplaneController.createAirplane(totalSeats));
    }

    private void viewAllAirplanesMenu() {
        System.out.println("All airplanes:");
        System.out.println(airplaneController.getAllAirplanes());
    }

    private void viewAirplaneByIdMenu() {
        System.out.println("Enter airplane ID:");
        int id = scanner.nextInt();
        System.out.println(airplaneController.getAirplaneById(id));
    }

    // Manage Tickets
    private void manageTicketsMenu() {
        System.out.println("1. Create a Ticket");
        System.out.println("2. View all Tickets");
        System.out.println("3. View Ticket by ID");
        System.out.println("0. Back to Main Menu");
        System.out.print("Select an option (1-3): ");

        int option = scanner.nextInt();
        switch (option) {
            case 1:
                createTicketMenu();
                break;
            case 2:
                viewAllTicketsMenu();
                break;
            case 3:
                viewTicketByIdMenu();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid option!");
        }
    }

    private void createTicketMenu() {
        System.out.println("Enter booking ID:");
        int bookingId = scanner.nextInt();
        System.out.println("Enter seat number:");
        String seatNumber = scanner.next();
        System.out.println("Enter ticket price:");
        double price = scanner.nextDouble();

        String response = ticketController.createTicket(bookingId, seatNumber, price);
        System.out.println(response);
    }

    private void viewAllTicketsMenu() {
        System.out.println("All tickets:");
        System.out.println(ticketController.getAllTickets());
    }

    private void viewTicketByIdMenu() {
        System.out.println("Enter ticket ID:");
        int id = scanner.nextInt();
        System.out.println(ticketController.getTicketById(id));
    }
}

