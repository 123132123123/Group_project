package dao;

import database.PostgresConnection;
import classes.Flight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO {
    public boolean createFlight(Flight flight) {
        String sql = "INSERT INTO Flights (departure_city, arrival_city, departure_time, arrival_time, seats, price) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = PostgresConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, flight.getDepartureCity());
            pstmt.setString(2, flight.getArrivalCity());
            pstmt.setInt(3, flight.getDepartureTime());
            pstmt.setInt(4, flight.getArrivalTime());
            pstmt.setInt(5, flight.getSeats());
            pstmt.setDouble(6, flight.getPrice());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error creating flight: " + e.getMessage());
        }
        return false;
    }

    public List<Flight> getAllFlights() {
        String sql = "SELECT * FROM Flights";
        List<Flight> flights = new ArrayList<>();

        try (Connection conn = PostgresConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Flight flight = new Flight();
                flight.setId(rs.getInt("id"));
                flight.setDepartureCity(rs.getString("departure_city"));
                flight.setArrivalCity(rs.getString("arrival_city"));
                flight.setDepartureTime(rs.getInt("departure_time"));
                flight.setArrivalTime(rs.getInt("arrival_time"));
                flight.setSeats(rs.getInt("seats"));
                flight.setPrice(rs.getDouble("price"));

                flights.add(flight);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching flights: " + e.getMessage());
        }
        return flights;
    }

    public boolean updateFlightSeats(int flightId, int newSeats) {
        String sql = "UPDATE Flights SET seats = ? WHERE id = ?";

        try (Connection conn = PostgresConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, newSeats);
            pstmt.setInt(2, flightId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating flight seats: " + e.getMessage());
        }
        return false;
    }

    public boolean deleteFlight(int flightId) {
        String sql = "DELETE FROM Flights WHERE id = ?";

        try (Connection conn = PostgresConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, flightId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting flight: " + e.getMessage());
        }
        return false;
    }
}

