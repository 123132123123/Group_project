package controllers.Interface;


public interface IFlightController {
    String createFlight(String departureCity, String arrivalCity, int departureTime, int arrivalTime, int seats, double price);

    String getFlightById(int id);

    String getAllFlights();

    String updateFlightSeats(int id, int newSeats);

    String deleteFlight(int id);

    String getAllDataByDate(String date);
}
