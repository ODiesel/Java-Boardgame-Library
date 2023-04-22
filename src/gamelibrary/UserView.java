package gamelibrary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class UserView extends JPanel {
    private final JPanel loginPanel = new JPanel();
    private final JLabel loginTitle = new JLabel("LOGIN");
    private final JLabel loginMessage = new JLabel("Please sign in with your username and password");
    private final JLabel loginStatus = new JLabel("");
    private final JTextField usernameField = new JTextField("",15);
    private final JPasswordField passwordField = new JPasswordField("", 15);
    private final JLabel usernameLabel = new JLabel("Username");
    private final JLabel passwordLabel = new JLabel("Password");

    private final JLabel signupMessage = new JLabel("Don't have an account?");
    private final JButton signupButton = new JButton("Create Account");
    private final JButton loginButton = new JButton("Login");
    private String usernameInput;
    private String passwordInput;

    UserView(){
        //this.setBackground(new Color(-16580864));
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(500,500);
        //frame.setLayout(null);
        loginPanel.setVisible(true);
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        GridBagConstraints c = new GridBagConstraints();

        loginTitle.setFont(new Font(null, Font.PLAIN, 44));
        loginTitle.setHorizontalAlignment(SwingConstants.CENTER);
        //loginMessage.setBounds(150, 100, 200, 50);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.0;
        c.gridwidth = 2;
        c.ipady = 10;
        c.ipadx = 10;
        c.insets.bottom = 10;
        c.insets.top = 10;
        c.insets.left = 10;
        c.insets.right = 10;
        c.fill = GridBagConstraints.HORIZONTAL;
        loginPanel.add(loginTitle,c);

        loginMessage.setFont(new Font(null, Font.PLAIN, 16));
        loginMessage.setHorizontalAlignment(SwingConstants.CENTER);
        //message.setBounds(90, 140, 300, 50);
        c.gridy = 1;
        loginPanel.add(loginMessage, c);

        //usernameField.setBounds(130, 195, 230, 30);
        c.gridy = 2;
        c.gridwidth = 1;
        c.insets.bottom = 0;
        loginPanel.add(usernameField, c);
        usernameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                usernameField.setText("");
            }
        });
        c.gridx = 1;
        loginPanel.add(usernameLabel, c);

        c.insets.bottom = 10;
        c.gridx = 0;
        //passwordField.setBounds(130, 235, 230, 30);
        c.gridy = 3;
        loginPanel.add(passwordField, c);
        passwordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                passwordField.setText("");
            }
        });
        c.gridx = 1;
        loginPanel.add(passwordLabel, c);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        c.insets.bottom = 0;
        c.insets.top = 0;
        loginStatus.setHorizontalAlignment(SwingConstants.CENTER);
        loginPanel.add(loginStatus, c);

        c.gridx = 0;
        c.gridy = 5;
        c.insets.bottom = 20;
        loginButton.setFont(new Font(null, Font.PLAIN, 16));
        loginButton.setForeground(Color.BLUE);
        loginPanel.add(loginButton, c);

        signupMessage.setFont(new Font(null, Font.PLAIN, 16));
        signupMessage.setHorizontalAlignment(SwingConstants.CENTER);
        //signupMessage.setBounds(125, 305, 230, 30);
        c.gridy = 7;
        c.insets.bottom = 0;
        loginPanel.add(signupMessage,c);

        signupButton.setFont(new Font(null, Font.PLAIN, 16));
        signupButton.setHorizontalAlignment(SwingConstants.CENTER);
        signupButton.setForeground(Color.BLUE);
        signupButton.setBounds(185, 335, 100, 30);
        c.gridy = 8;
        c.insets.top = 0;
        loginPanel.add(signupButton, c);

        signupButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                OpenNewAccountUI();
            }
        });

        this.setLayout(new BorderLayout());
        loginPanel.revalidate();
        this.add(loginPanel, BorderLayout.CENTER);
    }

    public void errorLoggingIn(){
        loginStatus.setForeground(Color.red);
        loginStatus.setText("Your username or password is incorrect");
        signupMessage.setText("Create an account with these credentials?");
    }

    public void switchToHomeView(){
        //loginPanel.setVisible(false);
        firePropertyChange("OPEN_HOME",null,null);
    }

    public void addLoginButtonListener(ActionListener listen) {
        loginButton.addActionListener(listen);
    }

    public void addCreateAccountButtonListener(ActionListener listen) {
        signupButton.addActionListener(listen);
    }

    public String getUsernameField(){
        return usernameField.getText();
    }

    public String getPasswordField(){
        return passwordField.getText();
    }

    public void keyPressed(KeyEvent e) {
        /*
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            usernameInput = usernameField.getText();
            passwordInput = passwordField.getText();
            isValidUser();
        }
        */
    }

    private void OpenNewAccountUI() {

    }

    public void UpdateView() {}
}
