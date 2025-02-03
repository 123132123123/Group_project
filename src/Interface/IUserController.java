package Interface;

public interface IUserController {

    String createUser(String username, String password, String name, String surname, int birthdate, String gender);

    String getUserById(int id);
    String getAllUsers();
    String getUserByUsername(String username);
}
