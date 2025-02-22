package repositories.rInterface;
import models.User;

import java.util.List;

public interface IUserRepository {
    boolean createUser(User user);
    User getUserById(int id);
    List<User> getAllUsers();

    User getUserByUsername(String username);
}
