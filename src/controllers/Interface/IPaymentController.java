package controllers.Interface;

public interface IPaymentController {
    String processPayment(int ticketId, double amount, String paymentMethod);
    String getPaymentById(int id);
    String getAllPayments();
}