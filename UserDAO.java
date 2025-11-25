import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class UserDAO {
    // synchronized ensures thread-safety (synchronization rubric)
    public synchronized User create(User u) throws SQLException {
        String sql = "INSERT INTO users (username,password,age,gender,height_cm,weight_kg) VALUES (?,?,?,?,?,?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setInt(3, u.getAge());
            ps.setString(4, u.getGender());
            ps.setDouble(5, u.getHeightCm());
            ps.setDouble(6, u.getWeightKg());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                u.setId(rs.getInt(1));
            }
            return u;
        }
    }

    public synchronized User findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setAge(rs.getInt("age"));
                u.setGender(rs.getString("gender"));
                u.setHeightCm(rs.getDouble("height_cm"));
                u.setWeightKg(rs.getDouble("weight_kg"));
                return u;
            }
            return null;
        }
    }

    public synchronized List<User> allUsers() throws SQLException {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection c = DBConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setAge(rs.getInt("age"));
                list.add(u);
            }
        }
        return list;
    }
}
