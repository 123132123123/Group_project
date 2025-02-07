package repositories.rInterface;
import models.Flight;

import java.util.List;
public interface IFlightRepository {
    boolean createFlight(Flight flight);

    Flight getFlightById(int id);

    List<Flight> getAllFlights();

    boolean deleteFlight(int id);

    boolean updateFlightSeats(int id, int newSeats);
}
