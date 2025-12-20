<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Result</title>
</head>
<body>
<h2>Result</h2>

<p>Total Calories: ${totalCalories}</p>

<c:choose>
    <c:when test="${healthy}">
        <p style="color:green;">Healthy Food Choice ✅</p>
    </c:when>
    <c:otherwise>
        <p style="color:red;">High Calorie Food ⚠</p>
    </c:otherwise>
</c:choose>

</body>
</html>
