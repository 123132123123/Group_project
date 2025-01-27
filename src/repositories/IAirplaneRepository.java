package repositories;
import models.Airplanes;

import java.util.List;

public interface IAirplaneRepository {

    boolean createAirplane(Airplanes airplane);

    Airplanes getAirplaneById(int id);

    List<Airplanes> getAllAirplanes();

    boolean deleteAirplane(int id);

    boolean updateAirplane(int id, int totalSeats);
}