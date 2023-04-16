import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GameListView extends JPanel{
    private final JPanel ListPanel = new JPanel();
    private final JTextArea jTextArea = new JTextArea();
    private final JTextField searchBar = new JTextField(20);
    private final JComboBox<GameListController.gameListSortEnum> sortComboBox;
    private final JComboBox<GameListController.sortDirectionEnum> sortDirectionComboBox;

    GameListView() {
        sortComboBox = new JComboBox<>(
                new DefaultComboBoxModel<>(GameListController.gameListSortEnum.values()));
        sortDirectionComboBox = new JComboBox<>(
                new DefaultComboBoxModel<>(GameListController.sortDirectionEnum.values()));

        JPanel gameListViewPanel = new JPanel();
        gameListViewPanel.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridBagLayout());
        headerPanel.setMinimumSize(new Dimension(100,50));
        headerPanel.setPreferredSize(new Dimension(100,50));
        gameListViewPanel.add(headerPanel,BorderLayout.PAGE_START);
        //HeaderPanel.setBackground(Color.BLUE);

        ListPanel.setLayout(new BorderLayout());
        ListPanel.setBackground(Color.magenta);
        gameListViewPanel.add(ListPanel);

        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new GridBagLayout());
        //FooterPanel.setMinimumSize(new Dimension(10,50));
        //FooterPanel.setPreferredSize(new Dimension(10,50));
        gameListViewPanel.add(footerPanel,BorderLayout.PAGE_END);
        //FooterPanel.setBackground(Color.red);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.8;
        c.weighty = 0.8;
        headerPanel.add(searchBar,c);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.1;

        headerPanel.add(sortComboBox,c);

        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 0.1;
        headerPanel.add(sortDirectionComboBox,c);

        this.add(gameListViewPanel);
    }


    private static class GameTilePane extends JPanel{
        BufferedImage bimg;
        Image img;
        String title;
        int height = 180;
        int width = 180;
        GameTilePane(String pathToImage, String title){
            this.setPreferredSize(new Dimension(200,200));

            //panel = new JPanel();
            this.setLayout(new GridBagLayout());
            this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            GridBagConstraints g = new GridBagConstraints();
            g.fill = GridBagConstraints.HORIZONTAL;
            g.gridx = 0;
            g.gridy = 0;
            g.weightx = 1.0;
            g.weighty = 0.9;

            try {
                bimg = ImageIO.read(new File(pathToImage));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            img = bimg.getScaledInstance(width,height, Image.SCALE_DEFAULT);
            ImageIcon icon = new ImageIcon(img);
            JLabel iconLabel = new JLabel(icon);
            this.add(iconLabel, g);

            JLabel gameLabel = new JLabel(title, SwingConstants.CENTER);
            this.title = title;
            g.gridy = 1;
            g.weighty = 0.1;
            this.add(gameLabel, g);

            this.setVisible(true);
        }
    }

    public void showGames(ArrayList<Game> games) {
        ListPanel.removeAll();

        GameTilePane tile;
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.insets.top = 10;
        c.insets.right = 10;
        c.insets.bottom = 10;
        c.insets.left = 10;

        GridBagLayout layout = new GridBagLayout();
        JPanel GamesGrid = new JPanel(layout);
            // iterate through the game list
        int xCount = 0;
        int yCount = 0;
        for (Game game : games) {
            if (xCount > 3) {
                xCount = 0;
                yCount += 1;
            }
            c.gridx = xCount;
            c.gridy = yCount;

            // for each game in the list, pass it to a game tile
            tile = new GameTilePane("test.png", game.getName());
            GamesGrid.add(tile, c);

            xCount += 1;
        }

        JScrollPane InnerScrollingListPanel = new JScrollPane(
                GamesGrid,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        //InnerScrollingListPanel.setBackground(Color.cyan);
        ListPanel.add(InnerScrollingListPanel);
        ListPanel.revalidate();
    }

    void addSortComboListener(ActionListener listenForSort) {sortComboBox.addActionListener(listenForSort);}
    void addSortDirectionComboListener(ActionListener listenForSort) {sortDirectionComboBox.addActionListener(listenForSort);}
    void addSearchBarListener(ActionListener listenForSearch) {searchBar.addActionListener(listenForSearch);}
    public GameListController.gameListSortEnum getSortSelection() {
        return (GameListController.gameListSortEnum) sortComboBox.getSelectedItem();
    }

    public GameListController.sortDirectionEnum getSortDirectionSelection() {
        return (GameListController.sortDirectionEnum) sortDirectionComboBox.getSelectedItem();
    }

    public String getSearchBarText() {
        return searchBar.getText();
    }

    public void UpdateView() {}
}