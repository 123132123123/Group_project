
package controllers;

import controllers.Interface.IAirplaneController;
import models.Airplanes;
import repositories.rInterface.IAirplaneRepository;

import java.util.List;

public class AirplaneController implements IAirplaneController {
    private final IAirplaneRepository repo;

    public AirplaneController(IAirplaneRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createAirplane(int totalSeats) {
        Airplanes airplane = new Airplanes(totalSeats);
        boolean created = repo.createAirplane(airplane);
        return (created) ? "Airplane was created successfully" : "Airplane creation failed";
    }

    @Override
    public String getAirplaneById(int id) {
        Airplanes airplane = repo.getAirplaneById(id);
        return (airplane == null) ? "Airplane was not found" : airplane.toString();
    }

    @Override
    public String getAllAirplanes() {
        List<Airplanes> airplanes = repo.getAllAirplanes();
        if (airplanes == null || airplanes.isEmpty()) {
            return "No airplanes found.";
        }
        StringBuilder response = new StringBuilder();
        for (Airplanes airplane : airplanes) {
            response.append(airplane.toString()).append("\n");
        }
        return response.toString();
    }

    @Override
    public String updateAirplaneSeats(int id, int totalSeats) {
        boolean updated = repo.updateAirplane(id, totalSeats);
        return (updated) ? "Airplane seats were updated successfully" : "Airplane update failed";
    }

    @Override
    public String deleteAirplane(int id) {
        boolean deleted = repo.deleteAirplane(id);
        return (deleted) ? "Airplane was deleted successfully" : "Airplane deletion failed";
    }
}

