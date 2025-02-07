package controllers;
import models.Payment;
import repositories.IPaymentRepositories;

import java.util.List;


public class PaymentController implements IPaymentController {
    private final IPaymentRepositories repo;

    public PaymentController(IPaymentRepositories repo) {
        this.repo = repo;
    }

    @Override
    public String createPayment(int ticketId, double amount, String paymentMethod) {
        try {
            if (amount <= 0) {
                return "Payment failed: Amount must be greater than zero.";
            }
            Payment payment = new Payment(ticketId, amount, paymentMethod);
            boolean created = repo.createPayment(payment);
            return (created) ? "Payment was created successfully" : "Payment creation failed";
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public String getPaymentById(int id) {
        Payment payment = repo.getPaymentById(id);
        return (payment == null) ? "Payment was not found" : payment.toString();
    }

    @Override
    public String getPaymentsByTicketId(int ticketId) {
        List<Payment> payments = repo.getPaymentsByTicketId(ticketId);
        if (payments == null || payments.isEmpty()) {
            return "No payments found for this ticket.";
        }
        StringBuilder response = new StringBuilder();
        for (Payment payment : payments) {
            response.append(payment.toString()).append("\n");
        }
        return response.toString();
    }

    @Override
    public String updatePaymentStatus(int id, String newStatus) {
        boolean updated = repo.updatePaymentStatus(id, newStatus);
        return (updated) ? "Payment status was updated successfully" : "Payment update failed";
    }

    @Override
    public String createReview(int flightId, int userId, int rating, String comment) {
        return "";
    }

    @Override
    public String getReviewById(int id) {
        return "";
    }

    @Override
    public String getReviewsByFlightId(int flightId) {
        return "";
    }

    @Override
    public String deleteReview(int id) {
        return "";
    }
}