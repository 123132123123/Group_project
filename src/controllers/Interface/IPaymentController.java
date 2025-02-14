package controllers.Interface;

public interface IPaymentController {
    String processPayment(double amount, String paymentMethod, int ticketId);
    String getPaymentById(int id);
    String getAllPayments();
}