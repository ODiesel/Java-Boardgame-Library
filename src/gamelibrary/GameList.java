package gamelibrary;

import java.util.ArrayList;
import java.util.Comparator;

public class GameList {
    private ArrayList<Game> gameList;
    private String name;

    public GameList(String name) {
        this.gameList = new ArrayList<>();
        this.name = name;
    }

    public GameList(String name, ArrayList<Game> gameList) {
        this.gameList = gameList;
        this.name = name;
    }

    public ArrayList<Game> getGameList() {return gameList;}
    public void setGameList(ArrayList<Game> gameList) {this.gameList = gameList;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public void addGame(Game game){
        // need to add way to make sure no data can be changed on primary game list
        gameList.add(game);
    }
    public void removeGame(Game game){
        // need to add way to make sure no data can be changed on primary game list
        gameList.remove(game);
    }
    public void editGame() {
        // need to add way to make sure no data can be changed on primary game list
    }
    public ArrayList<Game> searchGameList(String search) {
        // make case insensitive
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
    public void filterGameList() {}
    public void sortGameList(Comparator<Game> comp) {
        gameList.sort(comp);
    }
    public void sortGameList(ArrayList<Game> gameListToSort, Comparator<Game> comp) {
        gameListToSort.sort(comp);
    }
    public void printGameList(){
        if(gameList.isEmpty()) {System.out.println("gamelibrary.Game list: " + name + " is empty!!");}
        else{
            System.out.println("Printing " + gameList.size() + " games from game list: " + name);
            for (Game g : gameList) {System.out.println(g.toString());}
            System.out.println();
        }
    }

    public Game findGameInList(String gameTitle){
        for (Game game:gameList) {
            if(gameTitle.equals(game.getName())){
                return game;
            }
        }
        return null;
    }
}
