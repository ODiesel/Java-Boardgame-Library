package gamelibrary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.border.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;


public class GameView extends JPanel {
    private Game game;
    private final JLabel gameIconLabel;
    private final JLabel gameTitleLabel;
    private final JTextArea gameDescriptionArea;
    private final int maxImageDimension = 500;
    private final JPanel reviewPanel;
    private final JPanel reviewInputPanel;

    private final ReviewList reviewList;
    private final ReviewListView reviewListView;
    private final ReviewListController reviewListController;
    private final JButton favoriteButton;
    private final JButton collectionAddButton;
    private final JButton collectionRemoveButton;
    private final JComboBox<String> collectionCombo;
    private boolean isFavorite;
    private List<String> collectionListForComboBox;

    GameView(String title, String genre, String releaseDate, String description) {
        reviewList = new ReviewList();
        reviewListView = new ReviewListView();
        reviewListController = new ReviewListController(reviewList, reviewListView);

        // Create labels for game title, genre, and release date
        gameTitleLabel = new JLabel("Title: " + title);
        gameIconLabel = new JLabel();
        gameDescriptionArea = new JTextArea(description);
        gameDescriptionArea.setEditable(false);

        // Initialize the favorite button
        favoriteButton = new JButton("Favorite");
        favoriteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        favoriteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toggleFavorite();
            }
        });

        // Initialize the favorite button
        collectionCombo = new JComboBox();
        collectionCombo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Initialize the add to collection button
        collectionAddButton = new JButton("Add to collection");
        collectionAddButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        collectionAddButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String collection = (String)collectionCombo.getSelectedItem();
                firePropertyChange("ADD_TO_COLLECTION",game,collection);
            }
        });

        // Initialize the add to collection button
        collectionRemoveButton = new JButton("Remove from collection");
        collectionRemoveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        collectionRemoveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String collection = (String)collectionCombo.getSelectedItem();
                firePropertyChange("REMOVE_FROM_COLLECTION",game,collection);
            }
        });




        // Create panel for reviews
        reviewPanel = new JPanel();
        reviewPanel.setLayout(new BoxLayout(reviewPanel, BoxLayout.Y_AXIS));



        // Set layout and add components to the panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Add padding around the panel
        int padding = 10;
        panel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));

        // Customize game title label
        gameTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gameTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Center the game icon
        gameIconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Customize game description area
        gameDescriptionArea.setLineWrap(true);
        gameDescriptionArea.setWrapStyleWord(true);
        gameDescriptionArea.setFont(new Font("Arial", Font.PLAIN, 14));
        gameDescriptionArea.setMargin(new Insets(padding, padding, padding, padding));
        gameDescriptionArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Customize review panel
        reviewPanel.setBorder(BorderFactory.createTitledBorder("Reviews"));

        // Create panel for adding reviews
        reviewInputPanel = new JPanel();
        reviewInputPanel.setLayout(new GridLayout(0, 2));
        JLabel reviewTitleLabel = new JLabel("Title:");
        reviewInputPanel.add(reviewTitleLabel);
        JTextField reviewTitleField = new JTextField();
        reviewInputPanel.add(reviewTitleField);
        JLabel numberOfStarsLabel = new JLabel("Stars (1-5):");
        reviewInputPanel.add(numberOfStarsLabel);
        JTextField numberOfStarsField = new JTextField();
        reviewInputPanel.add(numberOfStarsField);
        JLabel reviewTextLabel = new JLabel("Text:");
        reviewInputPanel.add(reviewTextLabel);
        JTextArea reviewTextArea = new JTextArea();
        reviewTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add an outline to the JTextArea
        reviewInputPanel.add(reviewTextArea);
        JButton addReviewButton = new JButton("Add gamelibrary.Review");
        reviewInputPanel.add(addReviewButton);

        // Customize review input panel
        reviewInputPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Add a gamelibrary.Review"));

        // Add components to the panel
        panel.add(Box.createRigidArea(new Dimension(0, padding))); // Add some space between components
        panel.add(gameIconLabel);
        panel.add(Box.createRigidArea(new Dimension(0, padding))); // Add some space between components
        panel.add(gameTitleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, padding))); // Add some space between components
        panel.add(gameDescriptionArea);
        panel.add(Box.createRigidArea(new Dimension(0, padding))); // Add some space between components
        panel.add(reviewPanel);
        panel.add(Box.createRigidArea(new Dimension(0, padding))); // Add some space between components
        panel.add(reviewInputPanel);
        panel.add(Box.createRigidArea(new Dimension(0, padding))); // Add some space between components


        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);

        // Add action listener to add review button
        addReviewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = reviewTitleField.getText();
                int numberOfStars = Integer.parseInt(numberOfStarsField.getText());
                String text = reviewTextArea.getText();
                Review review = new Review(title, numberOfStars, text);
                reviewListController.addReview(review);
                reviewTitleField.setText("");
                numberOfStarsField.setText("");
                reviewTextArea.setText("");
                reviewListController.UpdateView();
                updateReviewPanel(); // Call this method to update the review panel
            }
        });

        // Create panel for removing reviews
        //JPanel removeReviewPanel = new JPanel();
        //JButton removeReviewButton = new JButton("Remove Review");
        //removeReviewPanel.add(removeReviewButton);

        // Add action listener to remove review button
        //removeReviewButton.addActionListener(new ActionListener() {
        //    public void actionPerformed(ActionEvent e) {
        //        removeReview();
        //    }
        //});

        // Set layout and add components to the panel
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(gameIconLabel);
        panel.add(gameTitleLabel);
        // Add favorite button to the panel
        panel.add(Box.createRigidArea(new Dimension(0, padding))); // Add some space between components
        panel.add(favoriteButton);
        panel.add(collectionCombo);
        panel.add(collectionAddButton);
        panel.add(collectionRemoveButton);
        panel.add(gameDescriptionArea);
        panel.add(reviewPanel);
        panel.add(reviewInputPanel);
        //panel.add(removeReviewPanel);

        // Add panel to the frame
        this.setLayout(new BorderLayout());
        //this.add(panel, BorderLayout.CENTER);

        JScrollPane InnerScrollingListPanel = new JScrollPane(
                panel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        InnerScrollingListPanel.getVerticalScrollBar().setUnitIncrement(20);
        this.add(InnerScrollingListPanel);
        this.revalidate();
        //ListPanel.add(InnerScrollingListPanel);
        //ListPanel.revalidate();
    }

    public void setComboBox(List<String> collectionList){
        collectionListForComboBox = collectionList;
        updateComboBox();
    }

    public void updateComboBox(){
        collectionCombo.removeAllItems();
        for (String s:collectionListForComboBox) {
            collectionCombo.addItem(s);
        }
    }

    public void SetGame(Game game) {
        this.game = game;
        // Initialize the favorite button
        favoriteButton.setText(game.favorite ? "Unfavorite" : "Favorite");
    }

    public void UpdateView() {
        if (game == null) {
            return;
        }
        ImageIcon icon = new ImageIcon(game.GetResizedImage(maxImageDimension));
        this.gameIconLabel.setIcon(icon);
        this.gameTitleLabel.setText(game.getName());
        this.gameDescriptionArea.setText(game.getDescription());
        if(game.favorite){
            favoriteButton.setText("Unfavorite");
        }
        else {
            favoriteButton.setText("Favorite");
        }
        updateComboBox();
    }

    // Update the review panel with the latest reviews
    private void updateReviewPanel() {
        reviewPanel.removeAll();
        for (Review review : reviewListController.getReviewList()) {
            JLabel titleLabel = new JLabel("Title: " + review.getTitle());
            JTextArea textArea = new JTextArea(review.getComment());
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);

            JPanel reviewItemPanel = new JPanel();
            reviewItemPanel.setLayout(new BoxLayout(reviewItemPanel, BoxLayout.Y_AXIS));
            reviewItemPanel.add(titleLabel);
            reviewItemPanel.add(textArea);

            JButton removeReviewButton = new JButton("Remove Review");
            reviewItemPanel.add(removeReviewButton);
            removeReviewButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    removeReview(review);
                }
            });

            reviewPanel.add(reviewItemPanel);
        }
        reviewPanel.revalidate();
        reviewPanel.repaint();
    }
    private void toggleFavorite() {
        //isFavorite = !isFavorite;
        game.favorite = !game.favorite;
                favoriteButton.setText(game.favorite ? "Unfavorite" : "Favorite");
        firePropertyChange("FAVORITE",this.game,game.favorite);
    }
    // Remove the latest review from the review list
    private void removeReview() {
        if (!reviewListController.getReviewList().isEmpty()) {
            Review lastReview = reviewListController.getReviewList().get(reviewListController.getReviewList().size() - 1);
            reviewListController.deleteReview(lastReview);
            reviewListController.UpdateView();
            updateReviewPanel(); // Call this method to update the review panel
        }
    }

    private void removeReview(String reviewTitle) {
        reviewListController.deleteReview(reviewTitle);
        reviewListController.UpdateView();
        updateReviewPanel(); // Call this method to update the review panel
    }

    private void removeReview(Review review) {
        reviewListController.deleteReview(review);
        reviewListController.UpdateView();
        updateReviewPanel(); // Call this method to update the review panel
    }
}
