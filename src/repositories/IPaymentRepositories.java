package repositories;
import models.Payment;
import java.util.List;

public interface IPaymentRepositories {
    boolean createPayment(Payment payment);

    Payment getPaymentById(int id);

    List<Payment> getPaymentsByTicketId(int ticketId);

    boolean updatePaymentStatus(int id, String newStatus);
}
