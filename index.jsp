<!DOCTYPE html>
<html>
<head>
    <title>Daily Calorie Tracker</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h2>Daily Calorie & Nutrition Tracker</h2>

<form action="calorie" method="post">
    Food Name: <input type="text" name="food" required><br><br>
    Calories: <input type="number" name="calories" required><br><br>
    Protein (g): <input type="number" name="protein" required><br><br>
    Carbs (g): <input type="number" name="carbs" required><br><br>
    Fats (g): <input type="number" name="fats" required><br><br>

    <input type="submit" value="Calculate">
</form>
</body>
</html>
