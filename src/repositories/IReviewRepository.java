package repositories;
import models.Review;
import java.util.List;

public interface IReviewRepository {
    boolean createReview(Review review);

    Review getReviewById(int id);

    List<Review> getReviewsByFlightId(int flightId);

    boolean deleteReview(int id);
}

