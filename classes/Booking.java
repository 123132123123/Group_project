package classes;

public class Booking {
    private int id;
    private int customerId;
    private int flightId;
    private String bookingDate;
    private double totalPrice;

    public Booking() {}

    public Booking(int customerId, int flightId, String bookingDate, double totalPrice) {
        this.customerId = customerId;
        this.flightId = flightId;
        this.bookingDate = bookingDate;
        this.totalPrice = totalPrice;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", flightId=" + flightId +
                ", bookingDate='" + bookingDate + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

