public class Review {
    private Integer numberOfStars;
    private String comment;

    public Review(Integer numberOfStars, String comment) {
        this.numberOfStars = numberOfStars;
        this.comment = comment;
    }

    public Integer getNumberOfStars() {return numberOfStars;}
    public void setNumberOfStars(Integer numberOfStars) {this.numberOfStars = numberOfStars;}
    public String getComment() {return comment;}
    public void setComment(String comment) {this.comment = comment;}
}
