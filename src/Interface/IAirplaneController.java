package Interface;

public interface IAirplaneController {
    String createAirplane(int totalSeats);

    String getAirplaneById(int id);

    String getAllAirplanes();

    String updateAirplaneSeats(int id, int totalSeats);

    String deleteAirplane(int id);
}
