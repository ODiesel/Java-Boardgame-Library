package gamelibrary;

import java.util.ArrayList;


/**
 * The class Review list
 */
public class ReviewList {
    private final ArrayList<Review> reviewList;

    /**
     *
     * It is a constructor for ReviewList
     *
     */
    public ReviewList() {
        this.reviewList = new ArrayList<>();
    }

    /**
     *
     * Gets the review list
     *
     * @return the review list
     */
    public ArrayList<Review> getReviewList() {
        return reviewList;
    }

    /**
     *
     * Add review
     *
     * @param review  the review.
     */
    public void addReview(Review review) {
        reviewList.add(review);
    }


    /**
     *
     * Delete review
     *
     * @param review  the review.
     */
    public void deleteReview(Review review)
    {
        reviewList.remove(review);
    }
}
