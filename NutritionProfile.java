public class NutritionProfile {
    private User user;

    public NutritionProfile(User user) {
        this.user = user;
    }

    // Basal Metabolic Rate (Mifflin-St Jeor) — simple BMR example
    public double calculateBMR() {
        double weight = user.getWeightKg();
        double height = user.getHeightCm();
        int age = user.getAge();
        if ("male".equalsIgnoreCase(user.getGender())) {
            return 10 * weight + 6.25 * height - 5 * age + 5;
        } else {
            return 10 * weight + 6.25 * height - 5 * age - 161;
        }
    }

    // Activity multiplier: sedentary by default
    public double calculateDailyCalories(String activityLevel) {
        double bmr = calculateBMR();
        switch (activityLevel.toLowerCase()) {
            case "sedentary": return bmr * 1.2;
            case "light": return bmr * 1.375;
            case "moderate": return bmr * 1.55;
            case "active": return bmr * 1.725;
            default: return bmr * 1.2;
        }
    }
}
