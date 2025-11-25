import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import model.FoodItem;

public class FoodTableModel extends AbstractTableModel {
    private String[] columns = {"ID","Name","Calories/100g","Protein/100g","Carbs/100g","Fat/100g","Category"};
    private List<FoodItem> data = new ArrayList<>();

    public void setData(List<FoodItem> list) {
        data = list;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() { return data.size(); }
    @Override
    public int getColumnCount() { return columns.length; }
    @Override
    public String getColumnName(int col) { return columns[col]; }
    @Override
    public Object getValueAt(int row, int col) {
        FoodItem f = data.get(row);
        switch (col) {
            case 0: return f.getId();
            case 1: return f.getName();
            case 2: return f.getCaloriesPer100g();
            case 3: return f.proteinPer100g;
            case 4: return f.carbsPer100g;
            case 5: return f.fatPer100g;
            case 6: return f.getCategory();
            default: return null;
        }
    }
}
