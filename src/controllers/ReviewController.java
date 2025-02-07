package controllers;
import controllers.Interface.IReviewController;
import models.Review;
import repositories.IReviewRepository;

import java.util.List;

public class ReviewController implements IReviewController {
    private final IReviewRepository repo;

    public ReviewController(IReviewRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createReview(int flightId, int userId, int rating, String comment) {
        try {
            Review review = new Review(flightId, userId, rating, comment);
            boolean created = repo.createReview(review);
            return (created) ? "Review was created successfully" : "Review creation failed";
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public String getReviewById(int id) {
        Review review = repo.getReviewById(id);
        return (review == null) ? "Review was not found" : review.toString();
    }

    @Override
    public String getReviewsByFlightId(int flightId) {
        List<Review> reviews = repo.getReviewsByFlightId(flightId);
        if (reviews == null || reviews.isEmpty()) {
            return "No reviews found for this flight.";
        }
        StringBuilder response = new StringBuilder();
        for (Review review : reviews) {
            response.append(review.toString()).append("\n");
        }
        return response.toString();
    }

    @Override
    public String deleteReview(int id) {
        boolean deleted = repo.deleteReview(id);
        return (deleted) ? "Review was deleted successfully" : "Review deletion failed";
    }
}

