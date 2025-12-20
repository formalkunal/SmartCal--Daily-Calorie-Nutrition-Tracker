package com.guvi.calorie;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class CalorieServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String foodName = request.getParameter("food");
        int calories = Integer.parseInt(request.getParameter("calories"));
        int protein = Integer.parseInt(request.getParameter("protein"));
        int carbs = Integer.parseInt(request.getParameter("carbs"));
        int fats = Integer.parseInt(request.getParameter("fats"));

        Food food = new Food(foodName, calories, protein, carbs, fats);

        request.setAttribute("totalCalories", CalorieCalculator.calculateTotalCalories(food));
        request.setAttribute("healthy", CalorieCalculator.isHealthy(food));

        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);
    }
}
