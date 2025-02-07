package models;
public class Payment {
    private int id;
    private int ticketId;
    private double amount;
    private String paymentMethod; // 'credit_card', 'paypal', 'crypto'
    private String status; // 'Pending', 'Completed', 'Failed'

    public Payment() {
        this.status = "Pending"; // По умолчанию платеж ожидает обработки
    }

    public Payment(int ticketId, double amount, String paymentMethod) {
        this.ticketId = ticketId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = "Pending";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма платежа должна быть положительной.");
        }
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void processPayment() {
        if (amount > 0) {
            this.status = "Completed";
        } else {
            this.status = "Failed";
        }
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", ticketId=" + ticketId +
                ", amount=" + amount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}