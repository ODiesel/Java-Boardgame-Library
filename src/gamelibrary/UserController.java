package gamelibrary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * The class User controller
 */
public class UserController {
    private final User model;
    private final UserView view;

    private String username = "";
    private String password = "";


    /**
     *
     * It is a constructor for UserController
     *
     * @param model  the model.
     * @param view  the view.
     */
    public UserController(User model, UserView view) {
        this.model = model;
        this.view = view;

        this.view.addLoginButtonListener(new LoginButtonListener());
        this.view.addCreateAccountButtonListener(new CreateAccountButtonListener());
    }


    /**
     *
     * Gets the view
     *
     * @return the view
     */
    public UserView getView(){
        return this.view;
    }

    class LoginButtonListener implements ActionListener {
        @Override

        public void actionPerformed(ActionEvent e) {
            login();
        }
    }


    /**
     *
     * Validate user
     *
     */
    public void validateUser() {
        FileReader inFile;
        Scanner inFileScanner;
        try {
            inFile = new FileReader("data/UserCredentials.txt");
            inFileScanner = new Scanner(inFile);
            while (inFileScanner.hasNextLine()) {
                String u = inFileScanner.next();
                System.out.println(u);
                String p = inFileScanner.next();
                System.out.println(p);
                if ((u.equals(username)) && (p.equals(password)))
                {
                    this.model.setUsername(username);
                    this.model.setPassword(password);
                    this.model.loggingIn();
                }
            }
        }
        catch (FileNotFoundException ex) {
            System.out.print("\n");
            System.err.println("File could not be located.");
        }
    }

    class CreateAccountButtonListener implements ActionListener {
        @Override

        public void actionPerformed(ActionEvent e) {
            createUser();
        }
    }

    /**
     *
     * Gets the collections
     *
     * @return the collections
     */
    public ArrayList<GameList> getCollections() {
        return model.getCollections();
    }

    /**
     *
     * Login
     *
     */
    public void login() {
        username = view.getUsernameField();
        password = view.getPasswordField();
        validateUser();
        if(model.isLoggedIn()){
            // load collections
            view.switchToHomeView();
        }
        else{
            view.errorLoggingIn();
        }
    }


    /**
     *
     * Create user
     *
     */
    public void createUser(){
        username = view.getUsernameField();
        password = view.getPasswordField();

        FileWriter outFile;
        try {
            outFile = new FileWriter("data/UserCredentials.txt",true);
            outFile.write('\n'+username + " " + password);
            outFile.close();
            login();
        }
        catch (FileNotFoundException ex) {
            System.err.println("File could not be located.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * Logout
     *
     */
    public void logout() {
        username = "";
        password = "";
        model.logout();
    }

    /**
     *
     * Add collection
     *
     * @param collection  the collection.
     */
    public void addCollection(GameList collection) {
        model.addCollection(collection);
        model.saveUserData();
    }

    /**
     *
     * Remove collection
     *
     * @param collection  the collection.
     */
    public void removeCollection(GameList collection) {
        model.removeCollection(collection);
        model.saveUserData();
    }

    /**
     *
     * Save user data
     *
     */
    public void saveUserData() {
        model.saveUserData();
    }
}
