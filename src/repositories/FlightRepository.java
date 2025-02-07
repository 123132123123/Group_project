package repositories;
import data.IDB;
import models.Flight;
import repositories.rInterface.IFlightRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class FlightRepository implements IFlightRepository{
    private final IDB db;

    public FlightRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createFlight(Flight flight) {
        try (Connection connection = db.getConnection()) {
            String sql = "INSERT INTO flights (departure_city, arrival_city, departure_time, arrival_time, seats, price) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, flight.getDepartureCity());
            st.setString(2, flight.getArrivalCity());
            st.setInt(3, flight.getDepartureTime());
            st.setInt(4, flight.getArrivalTime());
            st.setInt(5, flight.getSeats());
            st.setDouble(6, flight.getPrice());

            st.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Flight getFlightById(int id) {
        try (Connection connection = db.getConnection()) {
            String sql = "SELECT * FROM flights WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new Flight(
                        rs.getString("departure_city"),
                        rs.getString("arrival_city"),
                        rs.getInt("departure_time"),
                        rs.getInt("arrival_time"),
                        rs.getInt("seats"),
                        rs.getDouble("price")
                );
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Flight> getAllFlights() {
        try (Connection connection = db.getConnection()) {
            String sql = "SELECT * FROM flights";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            List<Flight> flights = new ArrayList<>();
            while (rs.next()) {
                Flight flight = new Flight(
                        rs.getString("departure_city"),
                        rs.getString("arrival_city"),
                        rs.getInt("departure_time"),
                        rs.getInt("arrival_time"),
                        rs.getInt("seats"),
                        rs.getDouble("price")
                );
                flight.setId(rs.getInt("id"));
                flights.add(flight);
            }
            return flights;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean deleteFlight(int id) {
        try (Connection connection = db.getConnection()) {
            String sql = "DELETE FROM flights WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateFlightSeats(int id, int newSeats) {
        try (Connection connection = db.getConnection()) {
            String sql = "UPDATE flights SET seats = ? WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, newSeats);
            st.setInt(2, id);

            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

}
