public class Fruit extends FoodItem {
    private boolean isCitrus;

    public Fruit(String name, double cal, double p, double c, double f, boolean isCitrus) {
        super(name, cal, p, c, f, "Fruit");
        this.isCitrus = isCitrus;
    }

    public boolean isCitrus() { return isCitrus; }

    @Override
    public String toString() {
        return super.toString() + (isCitrus ? " [citrus]" : "");
    }
}
