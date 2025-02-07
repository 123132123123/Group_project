package repositories.rInterface;

import models.Tickets;

import java.util.List;

public interface ITicketRepository {
    boolean createTicket(Tickets ticket);

    Tickets getTicketById(int id);

    List<Tickets> getAllTickets();

    boolean deleteTicket(int id);

    Tickets getTicketByDestination(String destination);
}
