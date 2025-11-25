import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static final String DB_URL = "jdbc:sqlite:db/smartcal.db";
    private static Connection conn;

    public static synchronized Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(DB_URL);
        }
        return conn;
    }

    // Create tables if missing
    public static void initDatabase() {
        try (Connection c = getConnection(); Statement st = c.createStatement()) {
            String users = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "username TEXT UNIQUE NOT NULL, password TEXT NOT NULL, age INTEGER NOT NULL, gender TEXT, height_cm REAL, weight_kg REAL)";
            String foods = "CREATE TABLE IF NOT EXISTS foods ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, calories_per_100g REAL NOT NULL, protein_per_100g REAL, carbs_per_100g REAL, fat_per_100g REAL, category TEXT)";
            String diary = "CREATE TABLE IF NOT EXISTS diary ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER NOT NULL, food_id INTEGER NOT NULL, grams REAL NOT NULL, entry_date TEXT NOT NULL,"
                    + "FOREIGN KEY(user_id) REFERENCES users(id), FOREIGN KEY(food_id) REFERENCES foods(id))";

            st.execute(users);
            st.execute(foods);
            st.execute(diary);

            // Optionally insert sample foods
            String insertSample = "INSERT INTO foods (name, calories_per_100g, protein_per_100g, carbs_per_100g, fat_per_100g, category) "
                    + "SELECT 'Apple', 52, 0.3, 14, 0.2, 'Fruit' WHERE NOT EXISTS (SELECT 1 FROM foods WHERE name='Apple');";
            st.execute(insertSample);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
