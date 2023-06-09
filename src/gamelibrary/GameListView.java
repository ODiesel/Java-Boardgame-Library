package gamelibrary;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;


/**
 * The class Game list view extends J panel implements mouse listener
 */
public class GameListView extends JPanel implements MouseListener{
    private final JPanel ListPanel = new JPanel();
    private final JTextField searchBar = new JTextField(20);
    private final JComboBox<GameListController.gameListSortEnum> sortComboBox;
    private final JComboBox<GameListController.sortDirectionEnum> sortDirectionComboBox;

    /**
     * Constructor for GameListView
     */
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

        ListPanel.setLayout(new BorderLayout());
        ListPanel.setBackground(Color.magenta);
        gameListViewPanel.add(ListPanel);

        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new GridBagLayout());
        gameListViewPanel.add(footerPanel,BorderLayout.PAGE_END);

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


    /**
     * The class Private static game tile extends J panel
     */
    private static class GameTile extends JPanel {
        Game game;
        BufferedImage img;
        String title;

        /**
         * Constructor for GameTile
         * @param game the game to display in the game tile
         */
        GameTile(Game game) {
            this.game = game;
            this.title = game.getName();
            this.img = game.GetThumbnail();

            this.setPreferredSize(new Dimension(200, 200));
            this.setLayout(new GridBagLayout());
            this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            GridBagConstraints g = new GridBagConstraints();
            g.fill = GridBagConstraints.HORIZONTAL;
            g.gridx = 0;
            g.gridy = 0;
            g.weightx = 1.0;
            g.weighty = 0.9;

            ImageIcon icon = new ImageIcon(img);
            JLabel iconLabel = new JLabel(icon);
            this.add(iconLabel, g);

            JLabel gameLabel = new JLabel(title, SwingConstants.CENTER);
            g.gridy = 1;
            g.weighty = 0.1;
            this.add(gameLabel, g);
            this.setVisible(true);
        }
    }

    /**
     *
     * Show games
     *
     * @param games  the games.
     */
    public void showGames(ArrayList<Game> games) {
        ListPanel.removeAll();

        GameTile tile;
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
            tile = new GameTile(game);

            tile.addMouseListener(this);
            GamesGrid.add(tile, c);
            xCount += 1;
        }

        JScrollPane InnerScrollingListPanel = new JScrollPane(
                GamesGrid,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        InnerScrollingListPanel.getVerticalScrollBar().setUnitIncrement(20);
        ListPanel.add(InnerScrollingListPanel);
        ListPanel.revalidate();
    }


    /**
     *
     * Add sort combo listener
     *
     * @param listenForSort  the listen for sort.
     */
    public void addSortComboListener(ActionListener listenForSort) {
        sortComboBox.addActionListener(listenForSort);
    }

    /**
     *
     * Add sort direction combo listener
     *
     * @param listenForSort  the listen for sort.
     */
    public void addSortDirectionComboListener(ActionListener listenForSort) {
        sortDirectionComboBox.addActionListener(listenForSort);
    }

    /**
     *
     * Add search bar listener
     *
     * @param listenForSearch  the listen for search.
     */
    public void addSearchBarListener(ActionListener listenForSearch) {
        searchBar.addActionListener(listenForSearch);
    }


    /**
     *
     * Gets the sort selection
     *
     * @return the sort selection
     */
    public GameListController.gameListSortEnum getSortSelection() {
        return (GameListController.gameListSortEnum) sortComboBox.getSelectedItem();
    }


    /**
     *
     * Gets the sort direction selection
     *
     * @return the sort direction selection
     */
    public GameListController.sortDirectionEnum getSortDirectionSelection() {
        return (GameListController.sortDirectionEnum) sortDirectionComboBox.getSelectedItem();
    }


    /**
     *
     * Gets the search bar text
     *
     * @return the search bar text
     */
    public String getSearchBarText() {
        return searchBar.getText();
    }

    @Override


    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        GameTile tile = (GameTile) e.getSource();
        tile.setBackground(Color.gray);
    }

    @Override

    public void mouseReleased(MouseEvent e) {
        GameTile tile = (GameTile) e.getSource();
        tile.setBackground(null);
        firePropertyChange("OPEN_GAME",null,tile.game);
    }

    @Override

    public void mouseEntered(MouseEvent e) {
        GameTile tile = (GameTile) e.getSource();
        tile.setBackground(Color.LIGHT_GRAY);
    }

    @Override

    public void mouseExited(MouseEvent e) {
        GameTile tile = (GameTile) e.getSource();
        tile.setBackground(null);
    }
}
