package Main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Sercurity.*;


public class Register extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldUsername;
    private JPasswordField passwordField;
    private JTextField textFieldEmail;
    private JTextField textFieldPhoneNumber;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Register frame = new Register();
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
    public Register() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 479, 364);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 248, 255));
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

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblEmail.setBounds(63, 156, 100, 17);
        contentPane.add(lblEmail);

        JLabel lblPhoneNumber = new JLabel("Phone Number:");
        lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPhoneNumber.setBounds(63, 196, 120, 17);
        contentPane.add(lblPhoneNumber);

        textFieldUsername = new JTextField();
        textFieldUsername.setBounds(193, 76, 194, 25);
        contentPane.add(textFieldUsername);
        textFieldUsername.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(193, 116, 194, 25);
        contentPane.add(passwordField);

        textFieldEmail = new JTextField();
        textFieldEmail.setBounds(193, 156, 194, 25);
        contentPane.add(textFieldEmail);
        textFieldEmail.setColumns(10);

        textFieldPhoneNumber = new JTextField();
        textFieldPhoneNumber.setBounds(193, 196, 194, 25);
        contentPane.add(textFieldPhoneNumber);
        textFieldPhoneNumber.setColumns(10);

        JButton btnRegister = new JButton("Register");
        btnRegister.setBackground(new Color(70, 130, 180));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnRegister.setBounds(193, 253, 89, 30);
        contentPane.add(btnRegister);
        
        btnRegister.addActionListener(e -> {
            String username = textFieldUsername.getText();
            String password = String.valueOf(passwordField.getPassword());
            String email = textFieldEmail.getText();
            String phoneNumber = textFieldPhoneNumber.getText();

            
            if (username.isEmpty() || password.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()) {
                JOptionPane.showMessageDialog(contentPane, "Please fill in all fields.", "Empty Fields",
                        JOptionPane.ERROR_MESSAGE);
                return; // Exit the ActionListener
            }

            
            if (!UserValidator.isValidUsername(username)) {
                JOptionPane.showMessageDialog(contentPane, "Invalid username. Please enter at least 6 characters.",
                        "Invalid Username", JOptionPane.ERROR_MESSAGE);
                return; // Exit the ActionListener
            }

          
            if (!UserValidator.isValidPassword(password)) {
                JOptionPane.showMessageDialog(contentPane,
                        "Invalid password. Password must have at least 6 characters, including at least one uppercase letter, one lowercase letter, one digit, and one special character.",
                        "Invalid Password", JOptionPane.ERROR_MESSAGE);
                return; // Exit the ActionListener
            }

        
            if (!EmailValidator.isValidEmail(email)) {
                JOptionPane.showMessageDialog(contentPane, "Invalid email. Please enter a valid email address.",
                        "Invalid Email", JOptionPane.ERROR_MESSAGE);
                return; // Exit the ActionListener
            }

     
            if (!PhoneNumberValidator.isValidPhoneNumber(phoneNumber)) {
                JOptionPane.showMessageDialog(contentPane,
                        "Invalid phone number. Phone number must start with '84' or '0' and have a length of 9 to 11 digits.",
                        "Invalid Phone Number", JOptionPane.ERROR_MESSAGE);
                return; // Exit the ActionListener
            }
            int rs= register(username, password, email, phoneNumber);
             
            if (rs > 0) {
                JOptionPane.showMessageDialog(contentPane, "User registered successfully.", "Registration Success", JOptionPane.INFORMATION_MESSAGE);
            } 
		
          
        });
      

        JLabel lblTitle = new JLabel("Registration Form");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitle.setBounds(110, 21, 220, 29);
        contentPane.add(lblTitle);

        Border textFieldBorder = BorderFactory.createLineBorder(new Color(70, 130, 180), 1);
        textFieldUsername.setBorder(textFieldBorder);
        passwordField.setBorder(textFieldBorder);
        textFieldEmail.setBorder(textFieldBorder);
        textFieldPhoneNumber.setBorder(textFieldBorder);
    }
 

 

 private int register(String username, String password, String email, String phoneNumber) {
     int result = 0;
     try {
         Connection conn = JDBC.ConnectJDBC.getConnection();

         // Check if the username already exists
         PreparedStatement usernameCheckStmt = conn.prepareStatement("SELECT * FROM users WHERE Username = ?");
         usernameCheckStmt.setString(1, username);
         ResultSet usernameResult = usernameCheckStmt.executeQuery();

         if (usernameResult.next()) {
             JOptionPane.showMessageDialog(contentPane, "Username already exists.", "Registration Error", JOptionPane.ERROR_MESSAGE);
             return result; // Return 0 to indicate failure
         }

         // Check if the email already exists
         PreparedStatement emailCheckStmt = conn.prepareStatement("SELECT * FROM users WHERE Email = ?");
         emailCheckStmt.setString(1, email);
         ResultSet emailResult = emailCheckStmt.executeQuery();

         if (emailResult.next()) {
             JOptionPane.showMessageDialog(contentPane, "Email already exists.", "Registration Error", JOptionPane.ERROR_MESSAGE);
             return result; // Return 0 to indicate failure
         }

         // Check if the phone number already exists
         PreparedStatement phoneNumberCheckStmt = conn.prepareStatement("SELECT * FROM users WHERE PhoneNumber = ?");
         phoneNumberCheckStmt.setString(1, phoneNumber);
         ResultSet phoneNumberResult = phoneNumberCheckStmt.executeQuery();

         if (phoneNumberResult.next()) {
             JOptionPane.showMessageDialog(contentPane, "Phone number already exists.", "Registration Error", JOptionPane.ERROR_MESSAGE);
             return result; // Return 0 to indicate failure
         }

         // If all checks pass, insert the user into the database
         PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO users (Username, Password, Email, PhoneNumber) VALUES (?, ?, ?, ?)");
         insertStmt.setString(1, username);
         insertStmt.setString(2, password);
         insertStmt.setString(3, email);
         insertStmt.setString(4, phoneNumber);
         result = insertStmt.executeUpdate();
         conn.close();
         usernameCheckStmt.close();
         usernameResult.close();
         emailCheckStmt.close();
         emailResult.close();
         phoneNumberCheckStmt.close();
         phoneNumberResult.close();
         
       
         return result;
     } catch (Exception e) {
         e.printStackTrace();
     }
     return result;
 }

}
