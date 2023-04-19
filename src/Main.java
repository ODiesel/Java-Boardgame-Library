public class Main {
    public static void main(String[] args) {
        final String GAMEDATAXML_TEST = "simple1.xml";
        final String GAMEDATAXML_SAMPLE = "samplecatalog1.xml";
        final String GAMEDATAXML_SAMPLE50 = "bgg50Games.xml";

        DataManager data = new DataManager();
        data.loadGameDataXml(GAMEDATAXML_SAMPLE50);

        GameList primaryGameList = data.getPrimaryGameList();

        GameListView gameListView = new GameListView();
        GameListController gameListController = new GameListController(primaryGameList,gameListView);

        GameView gameView = new GameView();

        UserView userView = new UserView();

        HomeView homeView = new HomeView(gameListView, gameView, userView);

        homeView.setVisible(true);
    }
}