package classes;

public class Timetable {
    private int id;
    private int flightId;
    private String departureTime;
    private String arrivalTime;

    public Timetable() {}

    public Timetable(int flightId, String departureTime, String arrivalTime) {
        this.flightId = flightId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return "Timetable{" +
                "id=" + id +
                ", flightId=" + flightId +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                '}';
    }
}

