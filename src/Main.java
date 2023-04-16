import java.awt.*;



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
        HomeView homeView = new HomeView(gameListView);
        homeView.setVisible(true);

    }
}