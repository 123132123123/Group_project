package models;

import java.time.LocalDate;

public class User {
    private int id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private boolean gender;

    public User(String username, String password, String name, String surname, LocalDate birthdate, boolean gender) {
        setUsername(username);
        setPassword(password);
        setName(name);
        setSurname(surname);
        setBirthdate(birthdate);
        setGender(gender);
    }

    public User(String name, String surname, boolean gender) {
        setName(name);
        setSurname(surname);
        setGender(gender);
    }

    private void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public User(int id, String name, String surname, boolean gender) {
        this(name, surname, gender);
        setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender=" + gender +
                '}';
    }

    public Object getPassword() {
        return password;
    }
}
