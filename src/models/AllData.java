package models;

public class AllData {
    private int AirplaneId;
    private int FlightId;
    private String Departure;
    private String Destination;
    private String DepartureTime;
    private String ArrivalTime;
    private int TicketId;
    private String TicketType;
    private String SeatNumber;
    private double Price;
    private int userId;
    private String username;
    private String name;

    public AllData(int airplaneId, int flightId, String departure, String destination, String departureTime, String arrivalTime, int ticketId, String ticketType, String seatNumber, double price, int userId, String username, String name) {
        AirplaneId = airplaneId;
        FlightId = flightId;
        Departure = departure;
        Destination = destination;
        DepartureTime = departureTime;
        ArrivalTime = arrivalTime;
        TicketId = ticketId;
        TicketType = ticketType;
        SeatNumber = seatNumber;
        Price = price;
        this.userId = userId;
        this.username = username;
        this.name = name;
    }

    public int getAirplaneId() {
        return AirplaneId;
    }

    public void setAirplaneId(int airplaneId) {
        AirplaneId = airplaneId;
    }

    public int getFlightId() {
        return FlightId;
    }

    public void setFlightId(int flightId) {
        FlightId = flightId;
    }

    public String getDeparture() {
        return Departure;
    }

    public void setDeparture(String departure) {
        Departure = departure;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getDepartureTime() {
        return DepartureTime;
    }

    public void setDepartureTime(String departureTime) {
        DepartureTime = departureTime;
    }

    public String getArrivalTime() {
        return ArrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        ArrivalTime = arrivalTime;
    }

    public int getTicketId() {
        return TicketId;
    }

    public void setTicketId(int ticketId) {
        TicketId = ticketId;
    }

    public String getTicketType() {
        return TicketType;
    }

    public void setTicketType(String ticketType) {
        TicketType = ticketType;
    }

    public String getSeatNumber() {
        return SeatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        SeatNumber = seatNumber;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
