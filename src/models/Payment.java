package models;

import strategy.PaymentStrategy;

public class Payment {
    private int id;
    private int ticketId;
    private double amount;
    private String paymentMethod;
    private String status;
    private PaymentStrategy paymentStrategy; // Strategy instance

    public Payment(int ticketId, double amount, String paymentMethod, PaymentStrategy paymentStrategy) {
        this.ticketId = ticketId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStrategy = paymentStrategy;
        this.status = "Pending"; // Default status
    }

    public boolean processPayment() {
        return paymentStrategy.processPayment(amount);
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getTicketId() { return ticketId; }
    public void setTicketId(int ticketId) { this.ticketId = ticketId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Payment{id=" + id + ", ticketId=" + ticketId + ", amount=" + amount + ", paymentMethod='" + paymentMethod + "', status='" + status + "'}";
    }
}