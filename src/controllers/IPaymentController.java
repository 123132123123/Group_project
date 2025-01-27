package controllers;

public interface IPaymentController {
    String createPayment(int ticketId, double amount, String paymentMethod);

    String getPaymentById(int id);

    String getPaymentsByTicketId(int ticketId);

    String updatePaymentStatus(int id, String newStatus);

    String createReview(int flightId, int userId, int rating, String comment);

    String getReviewById(int id);

    String getReviewsByFlightId(int flightId);

    String deleteReview(int id);
}
