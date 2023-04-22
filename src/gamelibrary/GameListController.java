package gamelibrary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;


/**
 * The class Game list controller
 */
public class GameListController {
    private GameList model;
    private final GameListView view;
    private sortDirectionEnum sortDirection;
    private gameListSortEnum sort;
    private String searchString;


    /**
     *
     * It is a constructor.
     *
     * @param model  the model.
     * @param view  the view.
     */
    public GameListController(GameList model, GameListView view) {
        this.model = model;
        this.view = view;

        sortDirection = sortDirectionEnum.ASCENDING;
        sort = gameListSortEnum.NAME;

        String searchString = "";

        updateGameList(model.getGameList());
        this.view.addSortComboListener(new SortListener());
        this.view.addSortDirectionComboListener(new SortDirectionListener());
        this.view.addSearchBarListener(new SearchBarListener());
    }


    /**
     *
     * Get game list view
     *
     * @return GameListView
     */
    public GameListView GetGameListView() {
        return this.view;
    }

    class SortListener implements ActionListener {
        @Override

        public void actionPerformed(ActionEvent e) {
            sort = view.getSortSelection();
            updateGameList(model.getGameList());
        }
    }

    class SortDirectionListener implements ActionListener {
        @Override

        public void actionPerformed(ActionEvent e) {
            sortDirection = view.getSortDirectionSelection();
            updateGameList(model.getGameList());
        }
    }

    class SearchBarListener implements ActionListener {
        @Override

        public void actionPerformed(ActionEvent e) {
            searchString = view.getSearchBarText();
            updateGameList(model.getGameList());
        }
    }

    /**
     *
     * Update game list
     *
     * @param games  the games.
     */
    private void updateGameList(ArrayList<Game> games) {
        // search first
        ArrayList<Game> searchedGameList = searchGameList(searchString);

        // then sort
        if (sortDirection.getBool()) {
            sortGameList(searchedGameList, sort.comp);
        } else {
            sortGameList(searchedGameList, sort.comp.reversed());
        }

        view.showGames(searchedGameList);
    }


    /**
     *
     * Gets the game list
     *
     * @return the game list
     */
    public GameList getGameList() {
        return model;
    }

    /**
     *
     * Sets the game list
     *
     * @param gameList  the game list.
     */
    public void setGameList(GameList gameList) {
        model = gameList;
        UpdateView();
    }

    /**
     *
     * Search game list
     *
     * @param search  the search.
     * @return ArrayList<Game>
     */
    public ArrayList<Game> searchGameList(String search) {
        searchString = search;
        return model.searchGameList(search);
    }

    /**
     *
     * Sort game list
     *
     * @param gameList  the game list.
     * @param comp  the comp.
     */
    public void sortGameList(ArrayList<Game> gameList, Comparator<Game> comp) {
        model.sortGameList(gameList, comp);
    }


    /**
     *
     * Update view
     *
     */
    public void UpdateView() {
        updateGameList(model.getGameList());
    }

    public enum gameListSortEnum {
        NAME("Name", SortByName()),
        ID("ID", SortByID()),
        YEAR("Year", SortByPublicationYear());
        private final String text;
        private final Comparator<Game> comp;

        gameListSortEnum(String text, Comparator<Game> comp) {
            this.text = text;
            this.comp = comp;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public enum sortDirectionEnum {
        ASCENDING("Ascending", true),
        Descending("Descending", false);
        private final String text;
        private final boolean bool;

        /**
         *
         * Gets the bool
         *
         * @return the bool
         */
        public boolean getBool() {
            return bool;
        }

        sortDirectionEnum(String text, boolean bool) {
            this.text = text;
            this.bool = bool;
        }

        @Override
        public String toString() {
            return text;
        }
    }


    /**
     * Sorts the Games by Name
     * @return -1 if this object precedes the other, 1 if this object succeeds the other, 0 if they are equal
     */
    public static Comparator<Game> SortByName() {
        return Comparator.comparing(Game::getName);
    }

    /**
     * Sorts the Games by ID
     * @return -1 if this object precedes the other, 1 if this object succeeds the other, 0 if they are equal
     */
    public static Comparator<Game> SortByID() {
        return Comparator.comparing(Game::getId);
    }

    /**
     * Sorts the Games by Publication Year
     * @return -1 if this object precedes the other, 1 if this object succeeds the other, 0 if they are equal
     */
    public static Comparator<Game> SortByPublicationYear() {
        return Comparator.comparing(Game::getPublicationYear);
    }
}
