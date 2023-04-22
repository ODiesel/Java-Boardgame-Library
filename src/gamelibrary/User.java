package gamelibrary;

import java.io.*;
import java.util.ArrayList;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.Preferences;


/**
 * The class User
 */
public class User {
    private String username;
    private String password;
    private ArrayList<GameList> collectionList;
    private boolean loggedIn = false;
    Preferences allUserPreferences;
    Preferences thisUserPreferences;
    Preferences collectionsPreferences;
    private final GameList primaryGameList;


    /**
     *
     * It is a constructor for User
     *
     * @param primaryGameList  the primary game list.
     */
    public User(GameList primaryGameList){
        this.primaryGameList = primaryGameList;
    }


    /**
     *
     * Init collections
     *
     */
    public void initCollections(){
        GameList favorites = new GameList("Favorites");
        collectionList.add(favorites);
    }

    /**
     *
     * Sets the username
     *
     * @param username  the username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * Sets the password
     *
     * @param password  the password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * Gets the collections
     *
     * @return the collections
     */
    public ArrayList<GameList> getCollections() {
        if(collectionList.size() == 0){
            initCollections();
        }
        return collectionList;
    }

    /**
     *
     * Is logged in
     *
     * @return boolean
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     *
     * Logging in
     *
     */
    public void loggingIn() {
        this.loggedIn = true;
        loadUserData();
    }

    /**
     *
     * Logout
     *
     */
    public void logout() {
        saveUserData();
        loggedIn = false;
        username = "";
        password = "";
        collectionList.clear();
    }

    /**
     *
     * Add collection
     *
     * @param collection  the collection.
     */
    public void addCollection(GameList collection) {
        collectionList.add(collection);
    }

    /**
     *
     * Remove collection
     *
     * @param collection  the collection.
     */
    public void removeCollection(GameList collection) {
        collectionList.remove(collection);
    }

    /**
     *
     * Load user data
     *
     */
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


    /**
     *
     * Clear all preferences
     *
     */
    public void clearAllPreferences(){
        Preferences prefs = Preferences.userNodeForPackage(getClass());
        try {
            prefs.removeNode();
        } catch (BackingStoreException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *
     * Save user data
     *
     */
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
                }
            } catch (BackingStoreException e) {
                throw new RuntimeException(e);
            }
        }
        exportAllUserData();
    }


    /**
     *
     * Export all user data
     *
     */
    public void exportAllUserData(){
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("data/UserPreferences.xml");
            allUserPreferences.exportSubtree(fos);
        } catch (BackingStoreException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *
     * Import user data from XML
     *
     */
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
}
