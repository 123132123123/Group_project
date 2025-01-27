package repositories;
import data.IDB;
import models.Airplanes;
import repositories.IAirplaneRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirplaneRepository implements IAirplaneRepository{
    private final IDB db;

    public AirplaneRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createAirplane(Airplanes airplane) {
        try (Connection connection = db.getConnection()) {
            String sql = "INSERT INTO airplanes (total_seats) VALUES (?)";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, airplane.getTotalSeats());

            st.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Airplanes getAirplaneById(int id) {
        try (Connection connection = db.getConnection()) {
            String sql = "SELECT * FROM airplanes WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new Airplanes(
                        rs.getInt("total_seats")
                );
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Airplanes> getAllAirplanes() {
        try (Connection connection = db.getConnection()) {
            String sql = "SELECT * FROM airplanes";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            List<Airplanes> airplanes = new ArrayList<>();
            while (rs.next()) {
                Airplanes airplane = new Airplanes(
                        rs.getInt("total_seats")
                );
                airplanes.add(airplane);
            }
            return airplanes;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean deleteAirplane(int id) {
        try (Connection connection = db.getConnection()) {
            String sql = "DELETE FROM airplanes WHERE id = ?";
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
    public boolean updateAirplane(int id, int totalSeats) {
        try (Connection connection = db.getConnection()) {
            String sql = "UPDATE airplanes SET total_seats = ? WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, totalSeats);
            st.setInt(2, id);

            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }
}
