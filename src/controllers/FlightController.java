package controllers;
import models.Flight;
import repositories.IFlightRepository;

import java.util.List;


public class FlightController implements IFlightController{
    private final IFlightRepository repo;

    public FlightController(IFlightRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createFlight(String departureCity, String arrivalCity, int departureTime, int arrivalTime, int seats, double price) {
        Flight flight = new Flight(departureCity, arrivalCity, departureTime, arrivalTime, seats, price);
        boolean created = repo.createFlight(flight);
        return (created) ? "Flight was created successfully" : "Flight creation failed";
    }

    @Override
    public String getFlightById(int id) {
        Flight flight = repo.getFlightById(id);
        return (flight == null) ? "Flight was not found" : flight.toString();
    }

    @Override
    public String getAllFlights() {
        List<Flight> flights = repo.getAllFlights();
        if (flights == null || flights.isEmpty()) {
            return "No flights found.";
        }
        StringBuilder response = new StringBuilder();
        for (Flight flight : flights) {
            response.append(flight.toString()).append("\n");
        }
        return response.toString();
    }

    @Override
    public String updateFlightSeats(int id, int newSeats) {
        boolean updated = repo.updateFlightSeats(id, newSeats);
        return (updated) ? "Flight seats were updated successfully" : "Flight update failed";
    }

    @Override
    public String deleteFlight(int id) {
        boolean deleted = repo.deleteFlight(id);
        return (deleted) ? "Flight was deleted successfully" : "Flight deletion failed";
    }
}
