package gamelibrary;

public class GameController {
    private final Game model;
    private final GameView view;

    public GameController(Game model, GameView view) {
        this.model = model;
        this.view = view;
    }

    public String getName() {return model.getName();}
    public ReviewList getReviewList() {return model.getReviewList();}
    public String getType() {return model.getType();}
    public String getDescription() {return model.getDescription();}
    public Integer getMinimumPlayers() {return model.getMinimumPlayers();}
    public Integer getMaximumPlayers() {return model.getMaximumPlayers();}
    public void addReview(Review review) {model.addReview(review);}
    public void deleteReview(Review review) {model.deleteReview(review);}
    public void UpdateView() {view.UpdateView();}
}
