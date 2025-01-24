package dao;
import database.PostgresConnection;
import classes.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
    public boolean createPerson(Person person) {
        String sql = "INSERT INTO Person (name, surname, email, phone_number, passport_number) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = PostgresConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, person.getName());
            pstmt.setString(2, person.getSurname());
            pstmt.setString(3, person.getEmail());
            pstmt.setString(4, person.getPhoneNumber());
            pstmt.setString(5, person.getPassportNumber());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Person created successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error creating person: " + e.getMessage());
        }

        return false;
    }

    public List<Person> getAllPersons() {
        String sql = "SELECT * FROM Person";
        List<Person> persons = new ArrayList<>();

        try (Connection conn = PostgresConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setSurname(rs.getString("surname"));
                person.setEmail(rs.getString("email"));
                person.setPhoneNumber(rs.getString("phone_number"));
                person.setPassportNumber(rs.getString("passport_number"));

                persons.add(person);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching persons: " + e.getMessage());
        }

        return persons;
    }

    public boolean updatePersonEmail(int personId, String newEmail) {
        String sql = "UPDATE Person SET email = ? WHERE id = ?";

        try (Connection conn = PostgresConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newEmail);
            pstmt.setInt(2, personId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Person email updated successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error updating person email: " + e.getMessage());
        }

        return false;
    }

    public boolean deletePerson(int personId) {
        String sql = "DELETE FROM Person WHERE id = ?";

        try (Connection conn = PostgresConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, personId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Person deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error deleting person: " + e.getMessage());
        }

        return false;
    }
}

