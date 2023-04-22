package gamelibrary;

import java.util.ArrayList;


/**
 * The class Review list controller
 */
public class ReviewListController {
    private final ReviewList model;

    /**
     *
     * It is a constructor for ReviewListController
     *
     * @param model  the model.
     */
    public ReviewListController(ReviewList model) {
        this.model = model;
    }


    /**
     *
     * Gets the review list
     *
     * @return the review list
     */
    public ArrayList<Review> getReviewList() {
        return model.getReviewList();
    }

    /**
     *
     * Add review
     *
     * @param review  the review.
     */
    public void addReview(Review review) {
        model.addReview(review);
    }

    /**
     *
     * Delete review
     *
     * @param review  the review.
     */
    public void deleteReview(Review review) {
        model.deleteReview(review);
    }
}
