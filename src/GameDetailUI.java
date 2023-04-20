import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class GameDetailUI extends JFrame {

    private JLabel gameTitleLabel, gameGenreLabel, gameReleaseDateLabel, reviewTitleLabel, reviewTextLabel;
    private JTextArea gameDescriptionArea, reviewTextArea;
    private JButton playButton, buyButton, favoriteButton, unfavoriteButton, addReviewButton, removeReviewButton;
    private JPanel reviewPanel, reviewInputPanel;
    private ArrayList<JPanel> reviewList = new ArrayList<>();
    private ArrayList<Review> reviews = new ArrayList<>();

    public GameDetailUI(String title, String genre, String releaseDate, String description) {
        // Set window title
        setTitle("Game Detail");

        // Create labels for game title, genre, and release date
        gameTitleLabel = new JLabel("Title: " + title);
        gameGenreLabel = new JLabel("Genre: " + genre);
        gameReleaseDateLabel = new JLabel("Release Date: " + releaseDate);

        // Create text area for game description
        gameDescriptionArea = new JTextArea(description);
        gameDescriptionArea.setEditable(false);

        // Create buttons for playing and buying the game
        playButton = new JButton("Play");
        buyButton = new JButton("Buy");

        // Create buttons for favoriting and unfavoriting the game
        favoriteButton = new JButton("Favorite");
        unfavoriteButton = new JButton("Unfavorite");
        unfavoriteButton.setVisible(false); // Hide unfavorite button by default

        // Create panel for reviews
        reviewPanel = new JPanel();
        reviewPanel.setLayout(new BoxLayout(reviewPanel, BoxLayout.Y_AXIS));

        // Create panel for adding reviews
        reviewInputPanel = new JPanel();
        reviewInputPanel.setLayout(new GridLayout(0, 2));
        reviewTitleLabel = new JLabel("Stars:");
        reviewInputPanel.add(reviewTitleLabel);
        JTextField starsField = new JTextField();
        reviewInputPanel.add(starsField);
        reviewTextLabel = new JLabel("Comment:");
        reviewInputPanel.add(reviewTextLabel);
        reviewTextArea = new JTextArea();
        reviewInputPanel.add(reviewTextArea);
        addReviewButton = new JButton("Add Review");
        reviewInputPanel.add(addReviewButton);


        // Add action listener to add review button
        addReviewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /*String title = reviewTitleField.getText();
                String text = reviewTextArea.getText();
                int numberOfStars = Integer.parseInt(starsField.getText());
                Review review = new Review(numberOfStars, text);
                addReview(review);
                reviewTitleField.setText("");
                reviewTextArea.setText("");
                starsField.setText("");*/
            }
        });

        // Create panel for removing reviews
        JPanel removeReviewPanel = new JPanel();
        removeReviewButton = new JButton("Remove Review");
        removeReviewPanel.add(removeReviewButton);

        // Add action listener to remove review button
        removeReviewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeReview();
            }
        });

        // Set layout and add components to the panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(gameTitleLabel);
        panel.add(gameGenreLabel);
        panel.add(gameReleaseDateLabel);
        panel.add(gameDescriptionArea);
        panel.add(playButton);
        panel.add(buyButton);
        panel.add(favoriteButton);
        panel.add(unfavoriteButton);
        panel.add(reviewPanel);
        panel.add(reviewInputPanel);
        panel.add(removeReviewPanel);

        // Add panel to the frame
        setVisible(true);
    }

    // Method to add a review to the list and update the review panel
    private void addReview(Review review) {
        reviews.add(review);
        JPanel reviewPanelItem = new JPanel();
        reviewPanelItem.setLayout(new BoxLayout(reviewPanelItem, BoxLayout.Y_AXIS));
        JLabel starsLabel = new JLabel("Stars: " + review.getNumberOfStars());
        JLabel textLabel = new JLabel(review.getComment());
        reviewPanelItem.add(starsLabel);
        reviewPanelItem.add(textLabel);
        reviewList.add(reviewPanelItem);
        reviewPanel.add(reviewPanelItem);
        validate();
        repaint();
    }

    // Method to remove the last review from the list and update the review panel
    private void removeReview() {
        int index = reviewList.size() - 1;
        if (index >= 0) {
            reviews.remove(index);
            JPanel reviewPanelItem = reviewList.remove(index);
            reviewPanel.remove(reviewPanelItem);
            validate();
            repaint();
        }
    }

    /* Class to represent a game review
    private class Review {
        private String title;
        private String text;

        public Review(String title, String text) {
            this.title = title;
            this.text = text;
        }

        public String getTitle() {
            return title;
        }

        public String getText() {
            return text;
        }
    }*/
}
