package controllers.Interface;

public interface ITicketController {
    String createTicket(int bookingId, String ticketType, String seatNumber, double price);

    String getTicketById(int id);

    String getAllTickets();

    String deleteTicket(int id);
}
