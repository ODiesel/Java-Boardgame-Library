import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeView extends JFrame {
    private final JPanel HomePanelRight;
    private final JButton HomeButton;
    private final JButton GameDetailButton;
    private final JButton AccountButton;

    CardLayout HomePanelRightCardLayout;

    public HomeView(GameListView gameListView, GameView gameView, UserView userView) {
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
        GameDetailButton.setText("Game Detail");
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        homePanelLeft.add(GameDetailButton, c);

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

        gameListView.setLayout(new CardLayout(0, 0));
        //gameListView.setBackground(new Color(-16056065));
        HomePanelRight.add(gameListView, "GameCollectionCard");

        //JPanel gameDetailPanel = new JPanel();
        //gameDetailPanel.setLayout(new CardLayout(0, 0));
        //gameDetailPanel.setBackground(new Color(-60672));
        //HomePanelRight.add(gameDetailPanel, "GameDetailCard");
        HomePanelRight.add(gameView, "GameDetailCard");

        //JPanel accountPanel = new JPanel();
        //accountPanel.setLayout(new CardLayout(0, 0));
        //accountPanel.setBackground(new Color(-16580864));
        //HomePanelRight.add(accountPanel, "AccountCard");
        HomePanelRight.add(userView, "AccountCard");

        HomePanelRightCardLayout = (CardLayout) HomePanelRight.getLayout();

        addHomeButtonListener(new HomeButtonListener());
        addGameDetailsButtonListener(new GameDetailsButtonListener());
        addAccountButtonListener(new AccountButtonListener());
    }


    void addHomeButtonListener(ActionListener listen) {
        HomeButton.addActionListener(listen);
    }

    void addGameDetailsButtonListener(ActionListener listen) {
        GameDetailButton.addActionListener(listen);
    }

    void addAccountButtonListener(ActionListener listen) {
        AccountButton.addActionListener(listen);
    }

    class HomeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            HomePanelRightCardLayout.show(HomePanelRight, "GameCollectionCard");
        }
    }

    class GameDetailsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            HomePanelRightCardLayout.show(HomePanelRight, "GameDetailCard");
        }
    }

    class AccountButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            HomePanelRightCardLayout.show(HomePanelRight, "AccountCard");
        }
    }
}