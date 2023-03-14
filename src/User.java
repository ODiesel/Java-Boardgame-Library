import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<GameList> collections;
    private boolean loggedIn;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        collections = new ArrayList<>();
    }

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
    public ArrayList<GameList> getCollections() {return collections;}
    public void setCollections(ArrayList<GameList> collections) {this.collections = collections;}
    public boolean isLoggedIn() {return loggedIn;}
    public void setLoggedIn(boolean loggedIn) {this.loggedIn = loggedIn;}
    public void login() {}
    public void logout() {}
    public void addCollection(GameList collection) {collections.add(collection);}
    public void removeCollection(GameList collection) {collections.remove(collection);}
    public void editCollection() {}
    public void loadUserData() {}
    public void saveUserData() {}
    public void getCollection() {}
}
