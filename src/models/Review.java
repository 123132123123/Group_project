package models;
public class Review {
    private int id;
    private int flightId;
    private int userId;
    private int rating; // Оценка от 1 до 5
    private String comment;

    public Review() {}

    public Review(int flightId, int userId, int rating, String comment) {
        this.flightId = flightId;
        this.userId = userId;
        setRating(rating);
        this.comment = comment;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Рейтинг должен быть от 1 до 5.");
        }
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", flightId=" + flightId +
                ", userId=" + userId +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}

