public class FoodItem {
    protected int id;
    protected String name;
    protected double caloriesPer100g;
    protected double proteinPer100g;
    protected double carbsPer100g;
    protected double fatPer100g;
    protected String category;

    public FoodItem() {}

    public FoodItem(String name, double cal, double p, double c, double f, String category) {
        this.name = name;
        this.caloriesPer100g = cal;
        this.proteinPer100g = p;
        this.carbsPer100g = c;
        this.fatPer100g = f;
        this.category = category;
    }

    public double caloriesForAmount(double grams) {
        return caloriesPer100g * (grams / 100.0);
    }

    // getters/setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public double getCaloriesPer100g() { return caloriesPer100g; }
    public String getCategory() { return category; }

    @Override
    public String toString() {
        return name + " (" + caloriesPer100g + " kcal/100g)";
    }
}
