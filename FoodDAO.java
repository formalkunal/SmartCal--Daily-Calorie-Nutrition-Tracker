import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.FoodItem;

public class FoodDAO {
    public synchronized FoodItem create(FoodItem f) throws SQLException {
        String sql = "INSERT INTO foods (name, calories_per_100g, protein_per_100g, carbs_per_100g, fat_per_100g, category) VALUES (?,?,?,?,?,?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, f.getName());
            ps.setDouble(2, f.getCaloriesPer100g());
            ps.setDouble(3, f.proteinPer100g);
            ps.setDouble(4, f.carbsPer100g);
            ps.setDouble(5, f.fatPer100g);
            ps.setString(6, f.getCategory());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) f.setId(rs.getInt(1));
            return f;
        }
    }

    public synchronized List<FoodItem> allFoods() throws SQLException {
        List<FoodItem> list = new ArrayList<>();
        String sql = "SELECT * FROM foods";
        try (Connection c = DBConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                FoodItem f = new FoodItem();
                f.setId(rs.getInt("id"));
                // using setter on base fields
                // reflection omitted for brevity
                // direct access assumed for simplicity
                f.name = rs.getString("name");
                f.caloriesPer100g = rs.getDouble("calories_per_100g");
                f.proteinPer100g = rs.getDouble("protein_per_100g");
                f.carbsPer100g = rs.getDouble("carbs_per_100g");
                f.fatPer100g = rs.getDouble("fat_per_100g");
                f.category = rs.getString("category");
                list.add(f);
            }
        }
        return list;
    }
}
