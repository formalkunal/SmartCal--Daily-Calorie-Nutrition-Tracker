public class Dairy extends FoodItem {
    private double lactosePercent;

    public Dairy(String name, double cal, double p, double c, double f, double lactosePercent) {
        super(name, cal, p, c, f, "Dairy");
        this.lactosePercent = lactosePercent;
    }

    public double getLactosePercent() { return lactosePercent; }
}
