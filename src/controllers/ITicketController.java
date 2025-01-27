package controllers;

public interface ITicketController {
    String createTicket(int bookingId, String seatNumber, double price);

    String getTicketById(int id);

    String getAllTickets();

    String deleteTicket(int id);
}
