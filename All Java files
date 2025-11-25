import javax.swing.SwingUtilities;
import dao.DBConnection;
import dao.UserDAO;

public class Main {
    public static void main(String[] args) {
        // Ensure DB and tables exist
        DBConnection.initDatabase();
        // Start GUI on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new gui.LoginFrame();
        });
    }
}
