package controllers.Interface;

import java.time.LocalDate;

public interface IUserController {
    String createUser(String username, String password, String name, String surname, LocalDate birthdate, String gender);

    String getUserById(int id);
    String getAllUsers();
    String getUserByUsername(String username);
}
