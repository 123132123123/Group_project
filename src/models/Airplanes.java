package models;

public class Airplanes {
    private int id;
    private int totalSeats;

    public Airplanes() {}

    public Airplanes(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "id=" + id +
                ", totalSeats=" + totalSeats +
                '}';
    }
}
