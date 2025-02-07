package repositories.rInterface;
import data.IDB;
import models.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IPaymentRepository implements IPaymentRepositories {
    private final IDB db;

    public IPaymentRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createPayment(Payment payment) {
        try (Connection connection = db.getConnection()) {
            String sql = "INSERT INTO payments (ticket_id, amount, payment_method, status) VALUES (?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, payment.getTicketId());
            st.setDouble(2, payment.getAmount());
            st.setString(3, payment.getPaymentMethod());
            st.setString(4, payment.getStatus());

            st.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Payment getPaymentById(int id) {
        try (Connection connection = db.getConnection()) {
            String sql = "SELECT * FROM payments WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new Payment(
                        rs.getInt("ticket_id"),
                        rs.getDouble("amount"),
                        rs.getString("payment_method")
                );
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Payment> getPaymentsByTicketId(int ticketId) {
        try (Connection connection = db.getConnection()) {
            String sql = "SELECT * FROM payments WHERE ticket_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, ticketId);
            ResultSet rs = st.executeQuery();

            List<Payment> payments = new ArrayList<>();
            while (rs.next()) {
                Payment payment = new Payment(
                        rs.getInt("ticket_id"),
                        rs.getDouble("amount"),
                        rs.getString("payment_method")
                );
                payment.setId(rs.getInt("id"));
                payments.add(payment);
            }
            return payments;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updatePaymentStatus(int id, String newStatus) {
        try (Connection connection = db.getConnection()) {
            String sql = "UPDATE payments SET status = ? WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, newStatus);
            st.setInt(2, id);

            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }
}
