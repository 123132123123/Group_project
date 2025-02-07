package controllers;

public interface IPaymentController {
    String createPayment(int ticketId, double amount, String paymentMethod);

    String getPaymentById(int id);

    String getPaymentsByTicketId(int ticketId);

    String updatePaymentStatus(int id, String newStatus);
}
