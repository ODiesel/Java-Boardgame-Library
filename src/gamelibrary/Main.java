package gamelibrary;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        final String GAMEDATAXML_TEST = "data/simple1.xml";
        final String GAMEDATAXML_SAMPLE = "data/samplecatalog1.xml";
        final String GAMEDATAXML_SAMPLE50 = "data/bgg50Games.xml";

        DataManager data = new DataManager();
        data.loadGameDataXml(GAMEDATAXML_SAMPLE50);

        GameList primaryGameList = data.getPrimaryGameList();

        GameListView gameListView = new GameListView();
        GameListController gameListController = new GameListController(primaryGameList,gameListView);

        GameView gameView = new GameView("Default Title", "Default Genre", "Default Release Date", "Default Description");

        UserView userView = new UserView();
        User userModel = new User(primaryGameList);
        UserController userController = new UserController(userModel, userView);

        HomeView homeView = new HomeView(gameListController, gameView, userController);

        homeView.setVisible(true);
    }
}