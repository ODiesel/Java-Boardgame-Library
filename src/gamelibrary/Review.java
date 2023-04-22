package gamelibrary;

/**
 * The class Review
 */
public class Review {
    private final String title;
    private final Integer numberOfStars;
    private final String comment;


    /**
     *
     * It is a constructor for Review
     *
     * @param title  the title.
     * @param numberOfStars  the number of stars.
     * @param comment  the comment.
     */
    public Review(String title, Integer numberOfStars, String comment) {
        this.title = title;
        this.numberOfStars = numberOfStars;
        this.comment = comment;
    }


    /**
     *
     * Gets the title
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * Gets the comment
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }
}
