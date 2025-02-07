package models;

public class Tickets {
    private int id;
    private int bookingId;
    private String ticket_type;
    private String seatNumber;
    private double price;

    public Tickets(int bookingId, String ticketType, String seatNumber, double price) {}

    public Tickets(int bookingId, String seatNumber, double price) {
        this.bookingId = bookingId;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    public String getTicket_type() {
        return ticket_type;
    }

    public void setTicket_type(String ticket_type) {
        this.ticket_type = ticket_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", bookingId=" + bookingId +
                ", ticket type='" + ticket_type + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                ", price=" + price +
                '}';
    }
}
