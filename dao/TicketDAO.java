package dao;

import database.PostgresConnection;
import classes.Tickets;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    public boolean createTicket(Tickets ticket) {
        String sql = "INSERT INTO Tickets (booking_id, seat_number, price) VALUES (?, ?, ?)";

        try (Connection conn = PostgresConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, ticket.getBookingId());
            pstmt.setString(2, ticket.getSeatNumber());
            pstmt.setDouble(3, ticket.getPrice());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error creating ticket: " + e.getMessage());
        }
        return false;
    }

    public List<Tickets> getAllTickets() {
        String sql = "SELECT * FROM Tickets";
        List<Tickets> tickets = new ArrayList<>();

        try (Connection conn = PostgresConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Tickets ticket = new Tickets();
                ticket.setId(rs.getInt("id"));
                ticket.setBookingId(rs.getInt("booking_id"));
                ticket.setSeatNumber(rs.getString("seat_number"));
                ticket.setPrice(rs.getDouble("price"));

                tickets.add(ticket);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching tickets: " + e.getMessage());
        }
        return tickets;
    }

    public boolean deleteTicket(int ticketId) {
        String sql = "DELETE FROM Tickets WHERE id = ?";

        try (Connection conn = PostgresConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, ticketId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting ticket: " + e.getMessage());
        }
        return false;
    }
}
