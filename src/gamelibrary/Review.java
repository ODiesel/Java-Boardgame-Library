package gamelibrary;

public class Review {
    private String title;
    private Integer numberOfStars;
    private String comment;

    public Review(String title, Integer numberOfStars, String comment) {
        this.title = title;
        this.numberOfStars = numberOfStars;
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {this.title = title;}
    public Integer getNumberOfStars() {return numberOfStars;}
    public void setNumberOfStars(Integer numberOfStars) {this.numberOfStars = numberOfStars;}
    public String getComment() {return comment;}
    public void setComment(String comment) {this.comment = comment;}
}
