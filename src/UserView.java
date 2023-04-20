import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class UserView extends JPanel {
    //private final JFrame frame = new JFrame();
    private final JLabel loginMessage = new JLabel("LOGIN");
    private final JLabel message = new JLabel("Please sign in with your username and password");
    private final JTextField usernameField = new JTextField("Username",15);
    private final JPasswordField passwordField = new JPasswordField("Password", 15);
    private final JLabel signupMessage = new JLabel("Don't have an account?");
    private final JButton signupButton = new JButton("Create one");
    private String usernameInput;
    private String passwordInput;
    UserView(){
        //this.setBackground(new Color(-16580864));
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(500,500);
        //frame.setLayout(null);
        this.setVisible(true);

        loginMessage.setFont(new Font(null, Font.PLAIN, 44));
        loginMessage.setHorizontalAlignment(SwingConstants.CENTER);
        loginMessage.setBounds(150, 100, 200, 50);
        this.add(loginMessage);

        message.setFont(new Font(null, Font.PLAIN, 12));
        message.setHorizontalAlignment(SwingConstants.CENTER);
        message.setBounds(90, 140, 300, 50);
        this.add(message);

        usernameField.setBounds(130, 195, 230, 30);
        this.add(usernameField);

        usernameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                usernameField.setText("");
            }
        });

        passwordField.setBounds(130, 235, 230, 30);
        this.add(passwordField);

        passwordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                passwordField.setText("");
            }
        });


        signupMessage.setFont(new Font(null, Font.PLAIN, 16));
        signupMessage.setHorizontalAlignment(SwingConstants.CENTER);
        signupMessage.setBounds(125, 305, 230, 30);
        this.add(signupMessage);


        signupButton.setFont(new Font(null, Font.PLAIN, 16));
        signupButton.setHorizontalAlignment(SwingConstants.CENTER);
        signupButton.setForeground(Color.BLUE);
        signupButton.setBounds(185, 335, 100, 30);
        this.add(signupButton);

        signupButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                OpenNewAccountUI();
            }
        });

        this.revalidate();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            usernameInput = usernameField.getText();
            passwordInput = passwordField.getText();
            isValidUser();
        }
    }


    public boolean isValidUser() {
        FileReader inFile;
        Scanner inFileScanner;
        try {
            inFile = new FileReader("src/UserCredentials.txt");
            inFileScanner = new Scanner(inFile);
            while (inFileScanner.hasNextLine()) {
                String u = inFileScanner.next();
                System.out.println(u);
                String p = inFileScanner.next();
                System.out.println(p);
                if ((u == usernameInput) && (p == passwordInput))
                {
                    return true;
                }
            }
        }
        catch (FileNotFoundException ex) {
            System.out.print("\n");
            System.err.println("File could not be located.");
            return false;
        }
        return false;

    }

    private void OpenNewAccountUI() {

    }

    public void UpdateView() {}
}
