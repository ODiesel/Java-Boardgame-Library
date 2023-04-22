package gamelibrary;

import java.util.ArrayList;

public class ReviewListController {
    private final ReviewList model;
    private final ReviewListView view;

    public ReviewListController(ReviewList model, ReviewListView view) {
        this.model = model;
        this.view = view;
    }

    public ArrayList<Review> getReviewList() {return model.getReviewList();}
    public void setReviewList(ArrayList<Review> reviewList) {model.setReviewList(reviewList);}
    public void addReview(Review review) {model.addReview(review);}
    public void deleteReview(Review review)
    {
        model.deleteReview(review);
    }
    public void deleteReview(String reviewTitle) {
        Review review = findReviewWithTitle(reviewTitle);
        model.deleteReview(review);
    }
    public Review findReviewWithTitle(String title){
        ArrayList<Review> reviewList = getReviewList();
        for (Review review: reviewList) {
            if(review.getTitle().equals(title)){
                return review;
            }
        }
        return null;
    }
    public void editReview(){model.editReview();}
    public void UpdateView(){view.UpdateView();}

    // ViewReviewDetails()
}
