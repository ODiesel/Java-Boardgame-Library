package gamelibrary;

import java.io.*;
import java.util.ArrayList;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.Preferences;

public class User {
    private String username;
    private String password;
    private ArrayList<GameList> collectionList;
    private boolean loggedIn = false;
    Preferences allUserPreferences;
    Preferences thisUserPreferences;
    Preferences collectionsPreferences;
    private final GameList primaryGameList;

    public User(GameList primaryGameList){
        this.primaryGameList = primaryGameList;
    }

    public void initCollections(){
        GameList favorites = new GameList("Favorites");
        collectionList.add(favorites);
    }

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
    public ArrayList<GameList> getCollections() {
        if(collectionList.size() == 0){
            initCollections();
        }
        return collectionList;
    }
    public void setCollections(ArrayList<GameList> collections) {this.collectionList = collections;}
    public boolean isLoggedIn() {return loggedIn;}
    public void loggingIn() {
        this.loggedIn = true;
        loadUserData();
    }
    public void login() {}
    public void logout() {
        saveUserData();
        loggedIn = false;
        username = "";
        password = "";
        collectionList.clear();
    }
    public void addCollection(GameList collection) {collectionList.add(collection);}
    public void removeCollection(GameList collection) {
        collectionList.remove(collection);
    }
    public void editCollection() {}
    public void loadUserData() {

        collectionList = new ArrayList<GameList>();

        if(allUserPreferences == null){
            allUserPreferences = Preferences.userNodeForPackage(getClass());
        }

        try {
            if(allUserPreferences.childrenNames().length == 0)
            {
                importUserDataFromXML();
            }
        } catch (BackingStoreException e) {
            throw new RuntimeException(e);
        }

        // find/make node for this user
        thisUserPreferences = allUserPreferences.node(username);

        // find/make node for collections
        collectionsPreferences = thisUserPreferences.node("Collections");

        try {
            var collectionsPulled = collectionsPreferences.childrenNames();
            for (String s : collectionsPulled) {
                Preferences collection = collectionsPreferences.node(s);
                String collectionName = collection.name();
                GameList gameList = new GameList(collectionName);

                var gamesInCollection = collection.keys();
                for (String value : gamesInCollection) {
                    Game game = primaryGameList.findGameInList(value);
                    if (collectionName.equals("Favorites")) {
                        game.favorite = true;
                    }
                    gameList.addGame(game);
                }
                collectionList.add(gameList);
            }
        } catch (BackingStoreException e) {
            throw new RuntimeException(e);
        }
    }
    public void clearAllPreferences(){
        Preferences prefs = Preferences.userNodeForPackage(getClass());
        try {
            prefs.removeNode();
        } catch (BackingStoreException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUserData() {
        try {
            collectionsPreferences.removeNode();
            collectionsPreferences = thisUserPreferences.node("Collections");
        } catch (BackingStoreException e) {
            throw new RuntimeException(e);
        }

        for (GameList gameList: this.collectionList) {
            // find/make node for favorites
            Preferences collectionNode = collectionsPreferences.node(gameList.getName());
            try {
                collectionNode.clear();
                for (Game game: gameList.getGameList()) {
                    collectionNode.putBoolean(game.getName(), true);
                    var testing = 1;
                }
            } catch (BackingStoreException e) {
                throw new RuntimeException(e);
            }
        }
        exportAllUserData();
    }

    public void exportAllUserData(){
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("data/UserPreferences.xml");
            allUserPreferences.exportSubtree(fos);
        } catch (BackingStoreException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void importUserDataFromXML(){
        FileInputStream is;
        try{
            is = new FileInputStream("data/UserPreferences.xml");
        } catch (FileNotFoundException e) {
            return;
        }
        try {
            Preferences.importPreferences(is);
        } catch (IOException | InvalidPreferencesFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public void getCollection() {}
}
