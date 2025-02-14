package factory;

import models.User;
import models.UserRole;

import java.time.LocalDate;

public class UserFactory {
    public static User createUser(String role, String username, String password, String name, String surname, LocalDate birthdate, boolean gender) {
        UserRole userRole;
        switch (role.toLowerCase()) {
            case "admin":
                userRole = UserRole.ADMIN;
                break;
            case "manager":
                userRole = UserRole.MANAGER;
                break;
            case "passenger":
                userRole = UserRole.USER;
                break;
            default:
                throw new IllegalArgumentException("Invalid role: " + role);
        }

        // Convert boolean gender to String ("Male" or "Female")
        String genderStr = gender ? "Male" : "Female";

        return new User(username, password, name, surname, birthdate, genderStr);
    }
}