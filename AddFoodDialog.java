import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import dao.FoodDAO;
import model.FoodItem;
import util.Validator;
import util.exceptions.SmartCalException;

public class AddFoodDialog extends JDialog {
    private JTextField nameField, calField, protField, carbField, fatField, catField;
    private FoodDAO dao = new FoodDAO();

    public AddFoodDialog(JFrame owner) {
        super(owner, "Add Food", true);
        setSize(350,350);
        setLocationRelativeTo(owner);
        initUI();
        setVisible(true);
    }

    private void initUI() {
        JPanel p = new JPanel(new GridLayout(7,2,5,5));
        nameField = new JTextField();
        calField = new JTextField("0");
        protField = new JTextField("0");
        carbField = new JTextField("0");
        fatField = new JTextField("0");
        catField = new JTextField();

        p.add(new JLabel("Name:")); p.add(nameField);
        p.add(new JLabel("Calories /100g:")); p.add(calField);
        p.add(new JLabel("Protein /100g:")); p.add(protField);
        p.add(new JLabel("Carbs /100g:")); p.add(carbField);
        p.add(new JLabel("Fat /100g:")); p.add(fatField);
        p.add(new JLabel("Category:")); p.add(catField);

        JButton add = new JButton("Add");
        add.addActionListener(e -> onAdd());
        JPanel bot = new JPanel(); bot.add(add);

        add(p, BorderLayout.CENTER);
        add(bot, BorderLayout.SOUTH);
    }

    private void onAdd() {
        try {
            Validator.requireNonEmpty(nameField.getText(), "Name");
            double cal = Double.parseDouble(calField.getText());
            Validator.requirePositive(cal, "Calories");
            FoodItem f = new FoodItem(nameField.getText(), cal,
                    Double.parseDouble(protField.getText()),
                    Double.parseDouble(carbField.getText()),
                    Double.parseDouble(fatField.getText()),
                    catField.getText());
            dao.create(f);
            JOptionPane.showMessageDialog(this, "Food added");
            dispose();
        } catch (SmartCalException sce) {
            JOptionPane.showMessageDialog(this, sce.getMessage());
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Invalid number format");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
