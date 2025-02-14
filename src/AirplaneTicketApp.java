import controllers.Interface.IAirplaneController;
import controllers.Interface.IFlightController;
import controllers.Interface.ITicketController;
import controllers.PaymentController;
import data.IDB;
import data.PostgresDB;
import models.User;
import roles.AdminUser;
import roles.GuestUser;
import roles.ManagerUser;
import repositories.PaymentRepository;
import security.RoleChecker;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.Period;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AirplaneTicketApp {
    private final Scanner scanner = new Scanner(System.in);
    private boolean isRunning = true;
    private final ITicketController ticketController;
    private final IAirplaneController airplaneController;
    private final IFlightController flightController;

    public AirplaneTicketApp(ITicketController ticketController, IAirplaneController airplaneController, IFlightController flightController) {
        this.ticketController = ticketController;
        this.airplaneController = airplaneController;
        this.flightController = flightController;
    }

    public void start() {
        System.out.println("Welcome to the Airplane Ticket Management System!");
        mainMenu();
    }

    private void mainMenu() {
        while (isRunning) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Buy Ticket");
            System.out.println("2. Manage Airplanes (Admin only)");
            System.out.println("3. Manage Flights (Admin/Manager)");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            try {
                int option = scanner.nextInt();
                scanner.nextLine(); // Clear buffer

                switch (option) {
                    case 1 -> buyTicket();
                    case 2 -> securedEndpoint("Admin", this::manageAirplanes);
                    case 3 -> securedEndpoint("Manager", this::manageFlights);
                    case 0 -> exitApplication();
                    default -> System.out.println("Invalid option! Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private void buyTicket() {
        try (Connection connection = PostgresDB.getInstance("localhost", "postgres", "password", "airline").getConnection()) {
            if (connection == null) {
                System.out.println("Error connecting to database.");
                return;
            }

            PaymentRepository paymentRepository = new PaymentRepository((IDB) connection);
            PaymentController paymentController = new PaymentController(paymentRepository);

            System.out.print("Enter your birthdate (YYYY-MM-DD): ");
            LocalDate birthdate = LocalDate.parse(scanner.nextLine());

            double price = 100.0;
            int age = calculateAge(birthdate);

            DiscountChecker ageChecker = (a) -> a < 18;
            if (ageChecker.isEligibleForDiscount(age)) {
                price *= 0.8;
                System.out.println("You are under 18. -Discounted ticket price: " + price);
            } else {
                System.out.println("Standard ticket price: " + price);
            }

            System.out.println("Choose payment method:");
            System.out.println("1. Credit Card");
            System.out.println("2. PayPal");
            System.out.print("Select an option (1-2): ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> processCreditCardPayment(paymentController, price);
                case 2 -> processPayPalPayment(paymentController, price);
                default -> System.out.println("Invalid payment option!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void processCreditCardPayment(PaymentController paymentController, double price) {
        System.out.print("Enter card number: ");
        String cardNumber = scanner.nextLine();
        System.out.print("Enter card holder name: ");
        String cardHolder = scanner.nextLine();
        paymentController.processPayment(price, "CreditCard", 0);
    }

    private void processPayPalPayment(PaymentController paymentController, double price) {
        System.out.print("Enter PayPal email: ");
        String email = scanner.nextLine();
        paymentController.processPayment(price, "PayPal", 0);
    }

    private void manageAirplanes() {
        System.out.println("Airplane management functionality here...");
    }

    private void manageFlights() {
        System.out.println("Flight management functionality here...");
    }

    private void securedEndpoint(String requiredRole, Runnable action) {
        System.out.print("Enter your role (Admin/Manager/Guest): ");
        String role = scanner.nextLine();
        User currentUser = switch (role.toLowerCase()) {
            case "admin" -> new AdminUser(1, "adminUser", "admin123", "Admin", "User", LocalDate.of(1980, 1, 1), "male");
            case "manager" -> new ManagerUser(2, "managerUser", "manager123", "Manager", "User", LocalDate.of(1990, 2, 10), "female");
            case "guest" -> new GuestUser(3, "guestUser", "guest123", "Guest", "User", LocalDate.of(2005, 5, 5), "female");
            default -> null;
        };

        if (currentUser == null || !RoleChecker.hasPermission(currentUser, requiredRole)) {
            System.out.println("Access denied. You do not have permission to perform this action.");
            return;
        }

        action.run();
    }

    private void exitApplication() {
        System.out.println("Exiting the application. Goodbye!");
        isRunning = false;
    }

    private int calculateAge(LocalDate birthdate) {
        return Period.between(birthdate, LocalDate.now()).getYears();
    }
}

@FunctionalInterface
interface DiscountChecker {
    boolean isEligibleForDiscount(int age);
}

