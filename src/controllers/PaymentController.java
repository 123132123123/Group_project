package controllers;

import controllers.Interface.IPaymentController;
import models.Payment;
import repositories.rInterface.IPaymentRepository;
import strategy.CreditCardPayment;
import strategy.PayPalPayment;
import strategy.PaymentStrategy;

import java.util.List;

public class PaymentController implements IPaymentController {
    private final IPaymentRepository repo;

    public PaymentController(IPaymentRepository repo) {
        this.repo = repo;
    }

    @Override
    public String processPayment(double amount, String paymentMethod, int ticketId) {
        PaymentStrategy paymentStrategy;

        // Selecting strategy dynamically
        switch (paymentMethod.toLowerCase()) {
            case "credit_card":
                paymentStrategy = new CreditCardPayment();
                break;
            case "paypal":
                paymentStrategy = new PayPalPayment();
                break;
            default:
                return "Invalid payment method!";
        }

        // Create Payment instance using Strategy
        Payment payment = new Payment(ticketId, amount, paymentMethod, paymentStrategy);

        // Process payment using Strategy Pattern
        boolean success = payment.processPayment();
        if (success) {
            boolean saved = repo.createPayment(payment);
            return saved ? "Payment processed successfully!" : "Payment processing failed!";
        }
        return "Payment failed!";
    }

    @Override
    public String getPaymentById(int id) {
        Payment payment = repo.getPaymentById(id);
        return (payment == null) ? "Payment not found!" : payment.toString();
    }

    @Override
    public String getAllPayments() {
        List<Payment> payments = repo.getAllPayments();
        if (payments.isEmpty()) {
            return "No payments found!";
        }

        StringBuilder response = new StringBuilder();
        for (Payment payment : payments) {
            response.append(payment.toString()).append("\n");
        }
        return response.toString();
    }
}