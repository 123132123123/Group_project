package controllers;

import models.User;
import repositories.IUserRepository;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class AuthController {
    private IUserRepository userRepo;
    private boolean isAdministrator = false;
    private final Scanner scanner = new Scanner(System.in);

    public AuthController() {
    }

    public void register() {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        System.out.print("Enter name: ");
        String name = scanner.next();
        System.out.print("Enter surname: ");
        String surname = scanner.next();
        System.out.print("Enter birthdate (YYYY-MM-DD): ");
        String birthdateStr = scanner.next();
        System.out.println("Gender: ");
        String gender = scanner.next();

        LocalDate birthdate = LocalDate.parse(birthdateStr);
        int age = Period.between(birthdate, LocalDate.now()).getYears();
        if (age < 18) {
            System.out.println("Registration failed: You must be at least 18 years old.");
            return;
        }

        User user = new User(username, password, name, surname, birthdate, gender);
        boolean created = userRepo.createUser(user);
        System.out.println(created ? "User registered successfully!" : "Registration failed.");
    }

    public boolean login() {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        if (username.equals("Nurassyl") && password.equals("Erkejan7")) {
            isAdministrator = true;
        }

        User user = userRepo.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful! Welcome, " + user.getName());
            return true;
        }
        System.out.println("Invalid username or password.");
        return false;
    }

    public boolean isAdministrator() {
        return isAdministrator;
    }
}
