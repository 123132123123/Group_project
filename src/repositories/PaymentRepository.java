package repositories;

import data.IDB;
import models.Payment;
import repositories.rInterface.IPaymentRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository implements IPaymentRepository {
    private final IDB db;

    public PaymentRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createPayment(Payment payment) {
        // Implementation of createPayment
        return false;
    }

    @Override
    public Payment getPaymentById(int id) {
        // Implementation of getPaymentById
        return null;
    }

    @Override
    public List<Payment> getPaymentsByTicketId(int ticketId) {
        return new ArrayList<>();
    }


    @Override
    public boolean updatePaymentStatus(int id, String newStatus) {
        return false;
    }
    @Override
    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        try (Connection connection = db.getConnection()) {
            // Оптимизированный SQL-запрос с сортировкой
            String sql = "SELECT id, ticket_id, amount, payment_method, status FROM payments ORDER BY amount DESC";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Payment payment = new Payment(
                        rs.getInt("ticket_id"),
                        rs.getDouble("amount"),
                        rs.getString("payment_method"),
                        null
                );
                payment.setId(rs.getInt("id"));
                payment.setStatus(rs.getString("status"));
                payments.add(payment);
            }
        } catch (SQLException e) {
            System.err.println("SQL error (getAllPayments): " + e.getMessage());
        }
        payments.sort((p1, p2) -> Double.compare(p2.getAmount(), p1.getAmount()));

        return payments;
    }

}