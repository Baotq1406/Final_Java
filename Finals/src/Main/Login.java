package Main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldUsername;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 248, 255)); // Set background color
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblUsername.setBounds(63, 76, 100, 17);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPassword.setBounds(63, 116, 100, 17);
        contentPane.add(lblPassword);

        textFieldUsername = new JTextField();
        textFieldUsername.setBounds(152, 74, 194, 25);
        contentPane.add(textFieldUsername);
        textFieldUsername.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(152, 114, 194, 25);
        contentPane.add(passwordField);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(70, 130, 180)); // Set button background color
        btnLogin.setForeground(Color.WHITE); // Set button text color
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnLogin.setBounds(168, 165, 89, 30);
        contentPane.add(btnLogin);

        JLabel lblTitle = new JLabel("Login Form");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitle.setBounds(137, 21, 165, 29);
        contentPane.add(lblTitle);

        // Add a decorative border to the text fields
        Border textFieldBorder = BorderFactory.createLineBorder(new Color(70, 130, 180), 1);
        textFieldUsername.setBorder(textFieldBorder);
        passwordField.setBorder(textFieldBorder);
    }
}
