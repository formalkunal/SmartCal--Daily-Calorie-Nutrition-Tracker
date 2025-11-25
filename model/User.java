import java.util.Objects;

public class User {
    private int id;
    private String username;
    private String password;
    private int age;
    private String gender;
    private double heightCm;
    private double weightKg;

    public User() {}

    public User(String username, String password, int age, String gender, double heightCm, double weightKg) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.heightCm = heightCm;
        this.weightKg = weightKg;
    }

    // getters/setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String u) { this.username = u; }
    public String getPassword() { return password; }
    public void setPassword(String p) { this.password = p; }
    public int getAge() { return age; }
    public void setAge(int a) { this.age = a; }
    public String getGender() { return gender; }
    public void setGender(String g) { this.gender = g; }
    public double getHeightCm() { return heightCm; }
    public void setHeightCm(double h) { this.heightCm = h; }
    public double getWeightKg() { return weightKg; }
    public void setWeightKg(double w) { this.weightKg = w; }

    @Override
    public String toString() {
        return "User{" + username + ", age=" + age + "}";
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof User)) return false;
        User u = (User) o;
        return Objects.equals(username, u.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
