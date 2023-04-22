package gamelibrary;

import java.util.ArrayList;

public class ReviewList {
    private ArrayList<Review> reviewList;

    public ReviewList() {
        this.reviewList = new ArrayList<>();
    }
    public ArrayList<Review> getReviewList() {return reviewList;}
    public void setReviewList(ArrayList<Review> reviewList) {this.reviewList = reviewList;}
    public void addReview(Review review) {reviewList.add(review);}
    public void deleteReview(Review review)
    {
        reviewList.remove(review);
    }
    public void editReview(){}
}
