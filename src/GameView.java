import javax.swing.*;

public class GameView extends JPanel {
    private Game game;
    private final JLabel gameIconLabel;
    private final JLabel gameTitleLabel;
    private final JTextArea gameDescriptionArea;
    private final int maxImageDimension = 500;

    GameView() {
        // I just added some basic things here just to test the functionality of opening up
        // the GameView for a specific game. Feel free to modify everything as you need

        this.gameIconLabel = new JLabel();
        this.gameTitleLabel = new JLabel();
        this.gameDescriptionArea = new JTextArea();
        this.add(gameIconLabel);
        this.add(gameTitleLabel);
        this.add(gameDescriptionArea);

        // Selasi add your ui code here
    }

    public void SetGame(Game game){this.game = game;}

    // you'll also need to update this function to reflect all of the fields that you add
    // to make sure that they update whenever the GameView is opened with a new game
    public void UpdateView() {
        if(game == null){
            return;
        }
        ImageIcon icon = new ImageIcon(game.GetResizedImage(maxImageDimension));
        this.gameIconLabel.setIcon(icon);
        this.gameTitleLabel.setText(game.getName());
        this.gameDescriptionArea.setText(game.getDescription());
    }
}
