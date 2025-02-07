package controllers;

import controllers.Interface.ITicketController;
import models.Tickets;
import repositories.rInterface.ITicketRepository;

import java.util.List;
public class TicketController implements ITicketController {
    private final ITicketRepository repo;

    public TicketController(ITicketRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createTicket(int bookingId, String ticketType, String seatNumber, double price) {
        Tickets ticket = new Tickets(bookingId, ticketType, seatNumber, price);
        boolean created = repo.createTicket(ticket);
        return (created) ? "Ticket was created successfully" : "Ticket creation failed";
    }

    @Override
    public String getTicketById(int id) {
        Tickets ticket = repo.getTicketById(id);
        return (ticket == null) ? "Ticket was not found" : ticket.toString();
    }

    @Override
    public String getAllTickets() {
        List<Tickets> tickets = repo.getAllTickets();
        if (tickets == null || tickets.isEmpty()) {
            return "No tickets found.";
        }
        StringBuilder response = new StringBuilder();
        for (Tickets ticket : tickets) {
            response.append(ticket.toString()).append("\n");
        }
        return response.toString();
    }

    @Override
    public String deleteTicket(int id) {
        boolean deleted = repo.deleteTicket(id);
        return (deleted) ? "Ticket was deleted successfully" : "Ticket deletion failed";
    }

    @Override
    public String getTicketByDestination(String destination) {
        Tickets ticket = repo.getTicketByDestination(destination);
        return (ticket == null) ? "Ticket was not found" : ticket.toString();
    }

}
