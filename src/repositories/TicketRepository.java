package repositories;
import data.IDB;
import models.Tickets;
import repositories.rInterface.ITicketRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class TicketRepository implements ITicketRepository{
    private final IDB db;

    public TicketRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createTicket(Tickets ticket) {
        try (Connection connection = db.getConnection()) {
            String sql = "INSERT INTO tickets (booking_id, seatNumber, price) VALUES (?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, ticket.getBookingId());
            st.setString(2, ticket.getSeatNumber());
            st.setDouble(3, ticket.getPrice());

            st.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Tickets getTicketById(int id) {
        try (Connection connection = db.getConnection()) {
            String sql = "SELECT * FROM tickets WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();


            if (rs.next()) {
                return new Tickets(
                        rs.getInt("id"),
                        rs.getString("seatNumber"),
                        rs.getDouble("price")
                );
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Tickets> getAllTickets() {
        try (Connection connection = db.getConnection()) {
            String sql = "SELECT * FROM tickets";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Tickets> tickets = new ArrayList<>();

            while (rs.next()) {
                Tickets ticket1 = new Tickets(
                        rs.getInt("id"),
                        rs.getString("seatNumber"),
                        rs.getDouble("price")
                );
                tickets.add(ticket1);
            }
            return tickets;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean deleteTicket(int id) {
        try (Connection connection = db.getConnection()) {
            String sql = "DELETE FROM tickets WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }
}
