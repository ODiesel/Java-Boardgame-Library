import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class HomeView extends JFrame {
    private final JPanel HomePanelRight;
    private final JButton HomeButton;
    private final JButton GameDetailButton;
    private final JButton AccountButton;
    private final GameView gameView;
    private final JPanel collectionPanel;
    private ArrayList<GameList> collections;
    private final GameListController gameListController;
    CardLayout HomePanelRightCardLayout;

    public HomeView(GameListController gameListController, GameView gameView, UserView userView, ArrayList<GameList> collections) {
        this.gameListController = gameListController;
        this.collections = collections;
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
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 0.1;
        c.ipady = 20;
        c.anchor = GridBagConstraints.NORTH;
        homePanelLeft.add(HomeButton, c);

        GameDetailButton = new JButton();
        //GameDetailButton.setText("Game Detail");
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        collectionPanel = new JPanel();
        //homePanelLeft.add(GameDetailButton, c);
        homePanelLeft.add(collectionPanel, c);

        AccountButton = new JButton();
        AccountButton.setText("Account");
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
                    Boolean favorite = (boolean)e.getNewValue();
                    if(favorite){
                        collections.get(1).addGame(game);
                    }
                    else{
                        collections.get(1).removeGame(game);
                    }
                }
            }
        });
        HomePanelRight.add(gameView, "GameDetailCard");
        HomePanelRight.add(userView, "AccountCard");

        HomePanelRightCardLayout = (CardLayout) HomePanelRight.getLayout();

        addHomeButtonListener(new HomeButtonListener());
        addGameDetailsButtonListener(new GameDetailsButtonListener());
        addAccountButtonListener(new AccountButtonListener());

        JLabel collectionTitle = new JLabel("Collections");
        collectionTitle.setHorizontalAlignment(SwingConstants.CENTER);
        collectionPanel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        Integer y = 0;
        collectionPanel.add(collectionTitle, c);
        for (GameList collection : collections){
            JButton b = new JButton(collection.getName());
            b.setName(Integer.toString(y));
            y++;
            c.gridy = y;
            b.addActionListener(new CollectionButtonListener());
            collectionPanel.add(b, c);
        }
    }

    private void OpenGameDetailUI(Game game){
        this.gameView.SetGame(game);
        this.gameView.UpdateView();
        HomePanelRightCardLayout.show(HomePanelRight, "GameDetailCard");
    }

    private void addHomeButtonListener(ActionListener listen) {
        HomeButton.addActionListener(listen);
    }

    private void addGameDetailsButtonListener(ActionListener listen) {
        GameDetailButton.addActionListener(listen);
    }

    private void addAccountButtonListener(ActionListener listen) {
        AccountButton.addActionListener(listen);
    }
    private class CollectionButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton)e.getSource();
            String title = b.getText();
            //GameList found = FindGameListInCollections(collections, title);
            int index = Integer.parseInt(b.getName());
            GameList found = collections.get(index);
            gameListController.setGameList(found);
            gameListController.UpdateView();
            HomePanelRightCardLayout.show(HomePanelRight, "GameCollectionCard");
        }
    }

    private GameList FindGameListInCollections(ArrayList<GameList> collections, String name){
        return collections.stream().filter(gameList -> name.equals(name)).findFirst().orElse(null);
    }
    private class HomeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
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
            HomePanelRightCardLayout.show(HomePanelRight, "AccountCard");
        }
    }
}