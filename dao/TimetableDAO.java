package dao;

import database.PostgresConnection;
import classes.Timetable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TimetableDAO {

    public boolean createTimetable(Timetable timetable) {
        String sql = "INSERT INTO Timetables (flight_id, departure_time, arrival_time) VALUES (?, ?, ?)";

        try (Connection conn = PostgresConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, timetable.getFlightId());
            pstmt.setString(2, timetable.getDepartureTime());
            pstmt.setString(3, timetable.getArrivalTime());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error creating timetable: " + e.getMessage());
        }
        return false;
    }

    public List<Timetable> getAllTimetables() {
        String sql = "SELECT * FROM Timetables";
        List<Timetable> timetables = new ArrayList<>();

        try (Connection conn = PostgresConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Timetable timetable = new Timetable();
                timetable.setId(rs.getInt("id"));
                timetable.setFlightId(rs.getInt("flight_id"));
                timetable.setDepartureTime(rs.getString("departure_time"));
                timetable.setArrivalTime(rs.getString("arrival_time"));

                timetables.add(timetable);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching timetables: " + e.getMessage());
        }
        return timetables;
    }


    public boolean deleteTimetable(int timetableId) {
        String sql = "DELETE FROM Timetables WHERE id = ?";

        try (Connection conn = PostgresConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, timetableId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting timetable: " + e.getMessage());
        }
        return false;
    }
}
