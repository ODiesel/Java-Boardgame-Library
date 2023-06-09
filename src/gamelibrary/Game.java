package gamelibrary;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;


/**
 * The class Game
 */
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
    private BufferedImage thumbnail;
    private BufferedImage image;
    private Image resizedImage;
    public boolean favorite = false;



    /**
     *
     * It is a constructor for Game
     *
     * @param name  the name.
     * @param id  the id.
     * @param tnuri  the tnuri.
     * @param imguri  the imguri.
     * @param pubyear  the pubyear.
     * @param description  the description.
     * @param minPlayers  the min players.
     * @param maxPlayers  the max players.
     */
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


    /**
     *
     * Get thumbnail
     *
     * @return BufferedImage
     */
    public BufferedImage GetThumbnail(){
        if(thumbnail == null){
            try {
                URL url = new URL(thumbURI);
                thumbnail = ImageIO.read(url);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return thumbnail;
    }


    /**
     *
     * Get image
     *
     * @return BufferedImage
     */
    public BufferedImage GetImage(){
        if(image == null){
            try {
                URL url = new URL(imgURI);
                image = ImageIO.read(url);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return image;
    }


    /**
     *
     * Get resized image
     *
     * @param maxDimension  the max dimension.
     * @return Image
     */
    public Image GetResizedImage(int maxDimension){
        if(resizedImage == null)
        {
            BufferedImage img = GetImage();
            int height = img.getHeight();
            int width = img.getWidth();
            int highest = Math.max(height,width);
            float scale = (float)maxDimension/(float)highest;
            int newHeight = (int)(height * scale);
            int newWidth = (int)(width * scale);
            resizedImage = img.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
        }
        return resizedImage;
    }


    /**
     *
     * Matches search
     *
     * @param search  the search.
     * @return boolean
     */
    public boolean matchesSearch(String search) {
        String lower = search.toLowerCase();
        if(this.name.toLowerCase().contains(lower)){return true;}
        if(this.id.contains(lower)){return true;}
        if(this.type.contains(lower)){return true;}
        if(this.description.contains(lower)){return true;}
        return false;
    }



    /**
     *
     * To string
     *
     * @return String
     */
    public String toString()
    {
        return "[" + name + ", "+ publicationYear + ", BGG ID: " + id + ", Min Players: " + minimumPlayers + ", Max Players: " + maximumPlayers + "]";
    }


    /**
     *
     * Gets the name
     *
     * @return the name
     */
    public String getName() {
        return name;
    }


    /**
     *
     * Gets the description
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * Gets the identifier
     *
     * @return the identifier
     */
    public String getId() {
        return id;
    }


    /**
     *
     * Gets the publication year
     *
     * @return the publication year
     */
    public int getPublicationYear() {
        return publicationYear;
    }
}
