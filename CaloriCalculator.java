package com.guvi.calorie;

public class CalorieCalculator {

    public static int calculateTotalCalories(Food food) {
        return food.getCalories();
    }

    public static boolean isHealthy(Food food) {
        return food.getCalories() < 500;
    }
}
