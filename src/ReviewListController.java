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
    public void editReview(){model.editReview();}
    public void UpdateView(){view.UpdateView();}

    // ViewReviewDetails()
}
