import java.awt.*;



public class Main {
    public static void main(String[] args) {
        final String GAMEDATAXML_TEST = "simple1.xml";
        final String GAMEDATAXML_SAMPLE = "samplecatalog1.xml";
        final String GAMEDATAXML_SAMPLE50 = "bgg50Games.xml";

        DataManager data = new DataManager();
        data.loadGameDataXml(GAMEDATAXML_SAMPLE);

        GameList primaryGameList = data.getPrimaryGameList();
        GameListView gameListView = new GameListView();
        GameListController gameListController = new GameListController(primaryGameList,gameListView);
        gameListView.setVisible(true);

        /*
        primaryGameList.printGameList();

        primaryGameList.sortGameList(GameList.SortByNameAtoZ());
        primaryGameList.printGameList();

        primaryGameList.sortGameList(GameList.SortByNameZtoA());
        primaryGameList.printGameList();

        primaryGameList.sortGameList(GameList.SortByIDHightoLow());
        primaryGameList.printGameList();

        primaryGameList.sortGameList(GameList.SortByIDLowtoHigh());
        primaryGameList.printGameList();

        primaryGameList.sortGameList(GameList.SortByPublicationYearHightoLow());
        primaryGameList.printGameList();

        primaryGameList.sortGameList(GameList.SortByPublicationYearLowtoHigh());
        primaryGameList.printGameList();
        */
    }
}