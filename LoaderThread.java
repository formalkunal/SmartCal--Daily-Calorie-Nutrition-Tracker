import javax.swing.SwingUtilities;
import dao.FoodDAO;
import model.FoodItem;
import java.util.List;

public class LoaderThread extends Thread {
    private Runnable onComplete;

    public LoaderThread(Runnable onComplete) {
        this.onComplete = onComplete;
    }

    @Override
    public void run() {
        try {
            FoodDAO dao = new FoodDAO();
            final java.util.List<FoodItem> foods = dao.allFoods(); // DB access on background thread
            // Update GUI on EDT
            SwingUtilities.invokeLater(() -> {
                // pass to callback (MainDashboard will set table model)
                onComplete.run();
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
