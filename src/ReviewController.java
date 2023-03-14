public class ReviewController {
    private final Review model;
    private final ReviewView view;

    public ReviewController(Review model, ReviewView view) {
        this.model = model;
        this.view = view;
    }

    public Integer getNumberOfStars() {return model.getNumberOfStars();}
    public void setNumberOfStars(Integer numberOfStars) {model.setNumberOfStars(numberOfStars);}
    public String getComment() {return model.getComment();}
    public void setComment(String comment) {model.setComment(comment);}
    public void UpdateView(){view.UpdateView();}
}
