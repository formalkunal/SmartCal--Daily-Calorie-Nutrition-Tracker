import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import dao.UserDAO;
import model.User;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private UserDAO userDAO = new UserDAO();

    public LoginFrame() {
        setTitle("SmartCal - Login");
        setSize(350,230);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
        setVisible(true);
    }

    private void initUI() {
        JPanel p = new JPanel(new GridLayout(5,1,5,5));
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        JButton login = new JButton("Login");
        JButton register = new JButton("Register");

        p.add(new JLabel("Username:"));
        p.add(usernameField);
        p.add(new JLabel("Password:"));
        p.add(passwordField);
        JPanel buttons = new JPanel();
        buttons.add(login);
        buttons.add(register);
        add(p, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        login.addActionListener(e -> doLogin());
        register.addActionListener(e -> doRegister());
    }

    private void doLogin() {
        String u = usernameField.getText();
        String p = new String(passwordField.getPassword());
        try {
            User user = userDAO.findByUsername(u);
            if (user != null && user.getPassword().equals(p)) {
                // open main dashboard
                dispose();
                new gui.MainDashboard(user);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void doRegister() {
        String u = usernameField.getText();
        String p = new String(passwordField.getPassword());
        try {
            if (u.isEmpty() || p.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Provide username & password.");
                return;
            }
            User user = new User(u, p, 25, "male", 170, 70); // basic profile defaults; in real app ask details
            userDAO.create(user);
            JOptionPane.showMessageDialog(this, "Registered. Please login.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
