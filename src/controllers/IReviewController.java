package controllers;

public interface IReviewController {
    String createReview(int flightId, int userId, int rating, String comment);

    String getReviewById(int id);

    String getReviewsByFlightId(int flightId);

    String deleteReview(int id);
}
