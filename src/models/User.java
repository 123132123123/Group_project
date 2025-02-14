package models;

import java.time.LocalDate;

public class User {
    private int id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String gender;
    private UserRole role;

    public User(int id, String username, String password, String name, String surname, LocalDate birthdate, String gender) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.gender = gender;
    }




    // Constructor without ID (for new users)
    public User(String username, String password, String name, String surname, LocalDate birthdate, String gender) {
        this(0, username, password, name, surname, birthdate, gender);
    }

    public User(int id, String name, String surname, boolean gender) {
        setId(id);
        setName(name);
        setSurname(surname);
    }

    // Gender validation method (ensures "Male" or "Female")
    private String validateGender(String gender) {
        if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female")) {
            return gender;
        }
        return "Unknown"; // Default value for incorrect input
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public LocalDate getBirthdate() { return birthdate; }
    public void setBirthdate(LocalDate birthdate) { this.birthdate = birthdate; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = validateGender(gender); }

    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthdate=" + birthdate +
                ", gender='" + gender + '\'' +
                ", role=" + role +
                '}';
    }
}