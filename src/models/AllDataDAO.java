package models;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class AllDataDAO {
    private final Connection connection;

    public AllDataDAO() {
        this.connection = connection;
    }

    private List<AllData> executeQuery(String query) {
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            return mapToList(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    private List<AllData> mapToList(ResultSet rs) throws SQLException {
        List<AllData> list = new ArrayList<>();
        while (rs.next()) {
            AllData allData = new AllData(
                    rs.getInt("AirplaneId"),
                    rs.getInt("FlightId"),
                    rs.getString("Departure"),
                    rs.getString("Destination"),
                    rs.getString("DepartureTime"),
                    rs.getString("ArrivalTime"),
                    rs.getInt("TicketId"),
                    rs.getString("TicketType"),
                    rs.getString("SeatNumber"),
                    rs.getDouble("Price"),
                    rs.getInt("userId"),
                    rs.getString("username"),
                    rs.getString("name")
            );
            list.add(allData);
        }
        return list;
    }

    public List<AllData> getAllData() {
        String query = "SELECT TICKETS.ID, TICKETS.TICKET_TYPE, TICKETS.SEAT_NUMBER, TICKETS.PRICE, " +
                "FLIGHTS.DEPARTURE_CITY, FLIGHTS.ARRIVAL_CITY, FLIGHTS.DEPARTURE_TIME, FLIGHTS.ARRIVAL_TIME, " +
                "USERS.USERNAME, USERS.NAME" +
                "FROM TICKETS FULL JOIN FLIGHTS ON TICKETS.FLIGHT_ID = FLIGHTS.ID " +
                "FULL JOIN AIRPLANES ON FLIGHTS.AIRPLANE_ID = AIRPLANES.ID " +
                "FULL JOIN USERS ON TICKETS.USER_ID = USERS.ID";
        return executeQuery(query);
    }
}
