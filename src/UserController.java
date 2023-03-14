import java.util.ArrayList;

public class UserController {
    private final User model;
    private final UserView view;

    public UserController(User model, UserView view) {
        this.model = model;
        this.view = view;
    }

    public String getUsername() {return model.getUsername();}
    public void setUsername(String username) {model.setUsername(username);}
    public void setPassword(String password) {model.setPassword(password);}
    public ArrayList<GameList> getCollections() {return model.getCollections();}
    public void setCollections(ArrayList<GameList> collections) {model.setCollections(collections);}
    public boolean isLoggedIn() {return model.isLoggedIn();}
    public void login() {model.login();}
    public void logout() {model.logout();}
    public void addCollection(GameList collection) {model.addCollection(collection);}
    public void removeCollection(GameList collection) {model.removeCollection(collection);}
    public void editCollection() {model.editCollection();}
    public void loadUserData() {model.loadUserData();}
    public void saveUserData() {model.saveUserData();}
    public void getCollection() {model.getCollection();}
    public void UpdateView(){view.UpdateView();}
}
