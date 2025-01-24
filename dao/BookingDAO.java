package dao;

import database.PostgresConnection;
import classes.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    public boolean createBooking(Booking booking) {
        String sql = "INSERT INTO Booking (customer_id, flight_id, booking_date, total_price) VALUES (?, ?, ?, ?)";

        try (Connection conn = PostgresConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, booking.getCustomerId());
            pstmt.setInt(2, booking.getFlightId());
            pstmt.setString(3, booking.getBookingDate());
            pstmt.setDouble(4, booking.getTotalPrice());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error creating booking: " + e.getMessage());
        }
        return false;
    }

    public List<Booking> getAllBookings() {
        String sql = "SELECT * FROM Booking";
        List<Booking> bookings = new ArrayList<>();

        try (Connection conn = PostgresConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setCustomerId(rs.getInt("customer_id"));
                booking.setFlightId(rs.getInt("flight_id"));
                booking.setBookingDate(rs.getString("booking_date"));
                booking.setTotalPrice(rs.getDouble("total_price"));

                bookings.add(booking);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching bookings: " + e.getMessage());
        }
        return bookings;
    }

    public boolean deleteBooking(int bookingId) {
        String sql = "DELETE FROM Booking WHERE id = ?";

        try (Connection conn = PostgresConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookingId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting booking: " + e.getMessage());
        }
        return false;
    }
}
