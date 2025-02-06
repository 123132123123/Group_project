package controllers;
import controllers.Interface.IUserController;
import models.User;
import repositories.rInterface.IUserRepository;

import java.util.List;

public class UserController implements IUserController {
    private final IUserRepository repo;

    public UserController(IUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createUser(String username, String password, String name, String surname, int birthdate, String gender) {
        boolean male = gender.equalsIgnoreCase("male");
        User user = new User(username, password, name, surname, birthdate, male);
        boolean created = repo.createUser(user);
        return (created) ? "User was created" : "User creation was failed";
    }

    @Override
    public String getUserById(int id) {
        User user = repo.getUserById(id);
        return (user == null) ? "User was not found" : user.toString();
    }

    @Override
    public String getAllUsers() {
        List<User> users = repo.getAllUsers();
        StringBuilder responce = new StringBuilder();
        for (User user : users) {
            responce.append(user.toString()).append("\n");
        }
        return responce.toString();
    }

    @Override
    public String getUserByUsername(String username) {
        User user = repo.getUserByUsername(username);
        return (user == null) ? "User was not found" : user.toString();
    }
}
