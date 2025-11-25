import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import model.User;
import dao.FoodDAO;
import model.FoodItem;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class MainDashboard extends JFrame {
    private User user;
    private JTable table;
    private FoodTableModel tableModel;
    private FoodDAO foodDAO = new FoodDAO();

    public MainDashboard(User user) {
        this.user = user;
        setTitle("SmartCal - Dashboard (" + user.getUsername() + ")");
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI();
        setVisible(true);
        loadFoodsInBackground();
    }

    private void initUI() {
        JButton addFoodBtn = new JButton("Add Food");
        JButton refresh = new JButton("Refresh");
        JButton calc = new JButton("My Calories");

        addFoodBtn.addActionListener(e -> new AddFoodDialog(this));
        refresh.addActionListener(e -> loadFoodsInBackground());
        calc.addActionListener(e -> showCalories());

        JPanel top = new JPanel();
        top.add(addFoodBtn);
        top.add(refresh);
        top.add(calc);

        tableModel = new FoodTableModel();
        table = new JTable(tableModel);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void showCalories() {
        // Calculate basic BMR and daily calories for example (activity "moderate")
        model.NutritionProfile profile = new model.NutritionProfile(user);
        double bmr = profile.calculateBMR();
        double daily = profile.calculateDailyCalories("moderate");
        JOptionPane.showMessageDialog(this, String.format("BMR: %.1f kcal\nDaily (moderate): %.1f kcal", bmr, daily));
    }

    private void loadFoodsInBackground() {
        new SwingWorker<List<FoodItem>, Void>() {
            @Override
            protected List<FoodItem> doInBackground() throws Exception {
                return foodDAO.allFoods();
            }

            @Override
            protected void done() {
                try {
                    List<FoodItem> list = get();
                    tableModel.setData(list);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }.execute();
    }
}
