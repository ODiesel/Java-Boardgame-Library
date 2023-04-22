package gamelibrary;

import java.util.ArrayList;
import java.util.Comparator;


/**
 * The class Game list
 */
public class GameList {
    private ArrayList<Game> gameList;
    private final String name;


    /**
     *
     * It is a constructor for GameList
     *
     * @param name  the name.
     */
    public GameList(String name) {
        this.gameList = new ArrayList<>();
        this.name = name;
    }

    /**
     *
     * Gets the game list
     *
     * @return the game list
     */
    public ArrayList<Game> getGameList() {
        return gameList;
    }

    /**
     *
     * Sets the game list
     *
     * @param gameList  the game list.
     */
    public void setGameList(ArrayList<Game> gameList) {
        this.gameList = gameList;
    }

    /**
     *
     * Gets the name
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * Add game
     *
     * @param game  the game.
     */
    public void addGame(Game game){
        gameList.add(game);
    }

    /**
     *
     * Remove game
     *
     * @param game  the game.
     */
    public void removeGame(Game game){
        gameList.remove(game);
    }

    /**
     *
     * Search game list
     *
     * @param search  the search.
     * @return ArrayList<Game>
     */
    public ArrayList<Game> searchGameList(String search) {
        if(search == null) {
            return gameList;}
        if(search.equals("")) {
            return gameList;}

        ArrayList<Game> currentFilteredList = new ArrayList<>();
        for(Game g : gameList) {
            if(g.matchesSearch(search)){
                currentFilteredList.add(g);
            }
        }
        return currentFilteredList;
    }

    /**
     *
     * Sort game list
     *
     * @param gameListToSort  the game list to sort.
     * @param comp  the comp.
     */
    public void sortGameList(ArrayList<Game> gameListToSort, Comparator<Game> comp) {
        gameListToSort.sort(comp);
    }

    /**
     *
     * Print game list
     *
     */
    public void printGameList(){
        if(gameList.isEmpty()) {System.out.println("gamelibrary.Game list: " + name + " is empty!!");}
        else{
            System.out.println("Printing " + gameList.size() + " games from game list: " + name);
            for (Game g : gameList) {System.out.println(g.toString());}
            System.out.println();
        }
    }


    /**
     *
     * Find game in list
     *
     * @param gameTitle  the game title.
     * @return Game
     */
    public Game findGameInList(String gameTitle){
        for (Game game:gameList) {
            if(gameTitle.equals(game.getName())){
                return game;
            }
        }
        return null;
    }
}
