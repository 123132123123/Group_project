package repositories;
import data.IDB;
import models.Review;
import repositories.rInterface.IReviewRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository implements IReviewRepository {
    private final IDB db;

    public ReviewRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createReview(Review review) {
        try (Connection connection = db.getConnection()) {
            String sql = "INSERT INTO reviews (flight_id, user_id, rating, comment) VALUES (?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, review.getFlightId());
            st.setInt(2, review.getUserId());
            st.setInt(3, review.getRating());
            st.setString(4, review.getComment());

            st.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Review getReviewById(int id) {
        try (Connection connection = db.getConnection()) {
            String sql = "SELECT * FROM reviews WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new Review(
                        rs.getInt("flight_id"),
                        rs.getInt("user_id"),
                        rs.getInt("rating"),
                        rs.getString("comment")
                );
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Review> getReviewsByFlightId(int flightId) {
        try (Connection connection = db.getConnection()) {
            String sql = "SELECT * FROM reviews WHERE flight_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, flightId);
            ResultSet rs = st.executeQuery();

            List<Review> reviews = new ArrayList<>();
            while (rs.next()) {
                Review review = new Review(
                        rs.getInt("flight_id"),
                        rs.getInt("user_id"),
                        rs.getInt("rating"),
                        rs.getString("comment")
                );
                review.setId(rs.getInt("id"));
                reviews.add(review);
            }
            return reviews;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean deleteReview(int id) {
        try (Connection connection = db.getConnection()) {
            String sql = "DELETE FROM reviews WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }
}

