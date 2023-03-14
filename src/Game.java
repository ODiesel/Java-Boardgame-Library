import java.awt.*;
import java.util.Comparator;

// make all fields that shouldn't ever change final (if they can be always initialized)
// make sure there aren't setters where there don't need to be
// make getters of mutable types use clone instead of returning reference to actual data
// strings are IMMUTABLE
// in java, everything but strings are mutable by default
// use assertions instead of checks?

public class Game {
    final private String name;
    final private String id;
    final private ReviewList reviewList;
    final private String type;
    final private String description;
    final private int minimumPlayers;
    final private int maximumPlayers;
    final private int publicationYear;
    final private String thumbURI;
    final private String imgURI;

    public Game(String name, String id, String tnuri, String imguri, int pubyear , String description, int minPlayers, int maxPlayers) {
        this.name = name;
        this.publicationYear = pubyear;
        this.id = id;
        this.thumbURI = tnuri;
        this.imgURI = imguri;
        this.description = description;
        this.minimumPlayers = minPlayers;
        this.maximumPlayers = maxPlayers;
        this.reviewList = new ReviewList();
        this.type = "";

    }

    public boolean matchesSearch(String search) {
        if(this.name.contains(search)){return true;}
        if(this.id.contains(search)){return true;}
        if(this.type.contains(search)){return true;}
        // maybe add publication year? min players? max players?
        // maybe way later search inside reviews??
        if(this.description.contains(search)){return true;}
        return false;
    }

    public String toString()
    {
        //return "[" + name + ", "+ publicationYear + ", BGG Rank: " + rank + ", BGG ID: " + id + ", thumbUri: " + thumbURI + "]";
        //return "[" + name + ", "+ publicationYear + ", BGG ID: " + id + ", Min Players: " + minimumPlayers + ", Max Players: " + maximumPlayers + "\nDescription: " + description + "thumbUri: " + thumbURI + "\nimgUri: " + imgURI + "]\n";
        return "[" + name + ", "+ publicationYear + ", BGG ID: " + id + ", Min Players: " + minimumPlayers + ", Max Players: " + maximumPlayers + "]";
    }

    public String getName() { return name; }
    public ReviewList getReviewList() {
        // need to clone here and implement clone in the reviewList class
        return reviewList;
    }
    public String getType() {return type;}
    public String getDescription() {return description;}
    public int getMinimumPlayers() {return minimumPlayers;}
    public int getMaximumPlayers() {return maximumPlayers;}
    public void addReview(Review review) {reviewList.addReview(review);}
    public void deleteReview(Review review) {reviewList.deleteReview(review);}
    public String getId() {return id;}
    public int getPublicationYear() {return publicationYear;}
    public String getThumbURI() {return thumbURI;}
    public String getImgURI() {return imgURI;}
}
