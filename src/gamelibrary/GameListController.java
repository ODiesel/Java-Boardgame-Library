package gamelibrary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;

public class GameListController {
    private GameList model;
    private final GameListView view;
    private sortDirectionEnum sortDirection;
    private gameListSortEnum sort;
    private String searchString;

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

    public GameListView GetGameListView(){
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

    private void updateGameList(ArrayList<Game> games){
        // search first
        ArrayList<Game> searchedGameList = searchGameList(searchString);

        // then filter

        // then sort
        if(sortDirection.getBool()){sortGameList(searchedGameList, sort.comp);}
        else{sortGameList(searchedGameList, sort.comp.reversed());}

        view.showGames(searchedGameList);
    }

    public GameList getGameList() {return model;}
    public void setGameList(GameList gameList) {
        model = gameList;
        UpdateView();
    }
    public String getName() {return model.getName();}
    public void setName(String name) {model.setName(name);}
    public void addGame(Game game){model.addGame(game);}
    public void removeGame(Game game){model.removeGame(game);}
    public void editGame() {model.editGame();}
    public ArrayList<Game> searchGameList(String search) {
        searchString = search;
        return model.searchGameList(search);
    }
    public void filterGameList() {model.filterGameList();}
    public void sortGameList(Comparator<Game> comp) {model.sortGameList(comp);}
    public void sortGameList(ArrayList<Game> gameList, Comparator<Game> comp) {model.sortGameList(gameList, comp);}
    public void UpdateView(){
        updateGameList(model.getGameList());
        view.UpdateView();
    }

    // ViewGameDetails()

    public enum gameListSortEnum {
        NAME("Name", SortByName()),
        ID("ID", SortByID()),
        YEAR("Year", SortByPublicationYear());
        private final String text;
        private final Comparator<Game> comp;
        public String getText() {return text;}
        public Comparator<Game> getComp() {return comp;}
        gameListSortEnum(String text, Comparator<Game> comp) {this.text = text;this.comp = comp;}
        @Override
        public String toString() {return text;}
    }

    public enum sortDirectionEnum {
        ASCENDING("Ascending", true),
        Descending("Descending", false);
        private final String text;
        private final boolean bool;
        public String getText() {return text;}
        public boolean getBool() {return bool;}
        sortDirectionEnum(String text, boolean bool) {this.text = text;this.bool = bool;}
        @Override
        public String toString() {return text;}
    }

    /**
     * Sorts the Games by Name
     * @return -1 if this object precedes the other, 1 if this object succeeds the other, 0 if they are equal
     */
    public static Comparator<Game> SortByName() {
        // Change name sort to not include "The" in the beginning
        return Comparator.comparing(Game::getName);
    }

    /**
     * Sorts the Games by ID
     * @return -1 if this object precedes the other, 1 if this object succeeds the other, 0 if they are equal
     */
    public static Comparator<Game> SortByID() {return Comparator.comparing(Game::getId);}

    /**
     * Sorts the Games by Publication Year
     * @return -1 if this object precedes the other, 1 if this object succeeds the other, 0 if they are equal
     */
    public static Comparator<Game> SortByPublicationYear() {return Comparator.comparing(Game::getPublicationYear);}
}
