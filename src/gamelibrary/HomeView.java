package gamelibrary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;


/**
 * The class Home view extends J frame
 */
public class HomeView extends JFrame {
    private final JPanel HomePanelRight;
    private final JButton HomeButton;
    private final JButton GameDetailButton;
    private final JButton AccountButton;
    private final GameView gameView;
    private final JPanel collectionPanel;
    private ArrayList<GameList> collections;
    private final GameListController gameListController;
    private final UserController userController;
    CardLayout HomePanelRightCardLayout;
    private final GameList mainGameList;


    /**
     *
     * Constructor for HomeView
     *
     * @param gameListController  the game list controller.
     * @param gameView  the game view.
     * @param userController  the user controller.
     *
     * */
    public HomeView(GameListController gameListController, GameView gameView, UserController userController) {
        this.gameListController = gameListController;
        this.userController = userController;
        mainGameList = gameListController.getGameList();
        this.gameView = gameView;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200,800);
        final JSplitPane splitPane1 = new JSplitPane();
        this.add(splitPane1);
        JPanel homePanelLeft = new JPanel();
        homePanelLeft.setMinimumSize(new Dimension(100, 200));
        homePanelLeft.setPreferredSize(new Dimension(200, 400));

        GridBagLayout homePanelLeftGridBag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        homePanelLeft.setLayout(homePanelLeftGridBag);
        splitPane1.setLeftComponent(homePanelLeft);

        HomeButton = new JButton();
        HomeButton.setText("Home");
        HomeButton.setEnabled(false);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 0.1;
        c.ipady = 20;
        c.anchor = GridBagConstraints.NORTH;
        homePanelLeft.add(HomeButton, c);

        GameDetailButton = new JButton();
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        collectionPanel = new JPanel();
        homePanelLeft.add(collectionPanel, c);

        AccountButton = new JButton();
        AccountButton.setText("Login");
        c.anchor = GridBagConstraints.SOUTH;
        c.gridy = 2;
        homePanelLeft.add(AccountButton, c);

        HomePanelRight = new JPanel();
        HomePanelRight.setLayout(new CardLayout(0, 0));
        HomePanelRight.setMinimumSize(new Dimension(100, 100));
        HomePanelRight.setPreferredSize(new Dimension(10, 10));
        splitPane1.setRightComponent(HomePanelRight);

        GameListView gameListView = gameListController.GetGameListView();
        gameListView.setLayout(new CardLayout(0, 0));
        gameListView.addPropertyChangeListener(new PropertyChangeListener() {
            @Override

            public void propertyChange(PropertyChangeEvent e) {
                if (e.getPropertyName().equals("OPEN_GAME")) {
                    var game = (Game)e.getNewValue();
                    OpenGameDetailUI(game);
                }
            }
        });
        HomePanelRight.add(gameListView, "GameCollectionCard");
        gameView.addPropertyChangeListener(new PropertyChangeListener() {
            @Override

            public void propertyChange(PropertyChangeEvent e) {
                if (e.getPropertyName().equals("FAVORITE")) {
                    Game game = (Game) e.getOldValue();
                    boolean favorite = (boolean)e.getNewValue();
                    if(favorite){
                        collections.get(0).addGame(game);
                    }
                    else{
                        collections.get(0).removeGame(game);
                    }
                    userController.saveUserData();
                }
                if (e.getPropertyName().equals("ADD_TO_COLLECTION")) {
                    Game game = (Game) e.getOldValue();
                    String collectionString = (String)e.getNewValue();
                    for (GameList collection:collections) {
                        if(collection.getName().equals(collectionString)){
                            collection.addGame(game);
                        }
                    }
                    userController.saveUserData();
                }
                if (e.getPropertyName().equals("REMOVE_FROM_COLLECTION")) {
                    Game game = (Game) e.getOldValue();
                    String collectionString = (String)e.getNewValue();
                    for (GameList collection:collections) {
                        if(collection.getName().equals(collectionString)){
                            collection.removeGame(game);
                        }
                    }
                    userController.saveUserData();
                }
            }
        });
        HomePanelRight.add(gameView, "GameDetailCard");
        userController.getView().addPropertyChangeListener(new PropertyChangeListener() {
            @Override

            public void propertyChange(PropertyChangeEvent e) {
                if (e.getPropertyName().equals("OPEN_HOME")) {
                    HomePanelRightCardLayout.show(HomePanelRight, "GameCollectionCard");
                    HomeButton.setEnabled(true);
                    AccountButton.setText("Logout");
                    // load collections from user
                    loadUserCollections();
                }
            }
        });
        HomePanelRight.add(userController.getView(), "AccountCard");

        HomePanelRightCardLayout = (CardLayout) HomePanelRight.getLayout();

        addHomeButtonListener(new HomeButtonListener());
        addGameDetailsButtonListener(new GameDetailsButtonListener());
        addAccountButtonListener(new AccountButtonListener());

        collectionPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Collections"));
        collectionPanel.setLayout(new GridBagLayout());
        HomePanelRightCardLayout.show(HomePanelRight, "AccountCard");
    }


    /**
     *
     * Load user collections
     *
     */
    private void loadUserCollections(){
        collectionPanel.removeAll();
        this.collections = userController.getCollections();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        int y = 0;
        ArrayList<String> collectionStringList = new ArrayList<String>();
        for (GameList collection : collections){
            c.gridx = 0;
            if(collection.getName().equals("Favorites")){
                c.weightx = 1.0;
                c.gridwidth = 2;
            }
            else{
                c.weightx = 0.9;
                c.gridwidth = 1;
                collectionStringList.add(collection.getName());
            }

            JButton b = new JButton(collection.getName());
            b.setName(Integer.toString(y));
            y++;
            c.gridy = y;
            b.addActionListener(new CollectionButtonListener());
            collectionPanel.add(b, c);

            if(!collection.getName().equals("Favorites")){
                c.gridx = 1;
                c.weightx = 0.1;
                JButton delButton = new JButton("-");
                delButton.addActionListener(new ActionListener() {
                    @Override

                    public void actionPerformed(ActionEvent e) {

                        userController.removeCollection(collection);
                        loadUserCollections();
                    }
                });
                collectionPanel.add(delButton, c);
            }

        }

        c.weightx = 1.0;
        c.gridwidth = 2;
        JTextField newCollectionField = new JTextField();
        y++;
        c.gridx = 0;
        c.gridy = y;
        collectionPanel.add(newCollectionField, c);

        JButton b = new JButton("Add new");
        y++;
        c.gridy = y;
        b.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                GameList newCollection = new GameList(newCollectionField.getText());
                userController.addCollection(newCollection);
                loadUserCollections();
            }
        });
        collectionPanel.add(b, c);
        collectionPanel.revalidate();
        gameView.setComboBox(collectionStringList);
    }


    /**
     *
     * Open game detail UI
     *
     * @param game  the game.
     */
    private void OpenGameDetailUI(Game game){
        this.gameView.SetGame(game);
        this.gameView.UpdateView();
        HomePanelRightCardLayout.show(HomePanelRight, "GameDetailCard");
    }


    /**
     *
     * Add home button listener
     *
     * @param listen  the listen.
     */
    private void addHomeButtonListener(ActionListener listen) {
        HomeButton.addActionListener(listen);
    }


    /**
     *
     * Add game details button listener
     *
     * @param listen  the listen.
     */
    private void addGameDetailsButtonListener(ActionListener listen) {
        GameDetailButton.addActionListener(listen);
    }


    /**
     *
     * Add account button listener
     *
     * @param listen  the listen.
     */
    private void addAccountButtonListener(ActionListener listen) {
        AccountButton.addActionListener(listen);
    }
    private class CollectionButtonListener implements ActionListener {
        @Override

        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton)e.getSource();
            String title = b.getText();
            int index = Integer.parseInt(b.getName());
            GameList found = collections.get(index);
            gameListController.setGameList(found);
            gameListController.UpdateView();
            HomePanelRightCardLayout.show(HomePanelRight, "GameCollectionCard");
        }
    }

    private class HomeButtonListener implements ActionListener {
        @Override

        public void actionPerformed(ActionEvent e) {
            gameListController.setGameList(mainGameList);
            HomePanelRightCardLayout.show(HomePanelRight, "GameCollectionCard");
        }
    }

    private class GameDetailsButtonListener implements ActionListener {
        @Override

        public void actionPerformed(ActionEvent e) {
            HomePanelRightCardLayout.show(HomePanelRight, "GameDetailCard");
        }
    }

    private class AccountButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(AccountButton.getText().equals("Logout")){
                userController.logout();
                AccountButton.setText("Login");
                collectionPanel.removeAll();
                HomeButton.setEnabled(false);
            }
            HomePanelRightCardLayout.show(HomePanelRight, "AccountCard");
        }
    }
}
