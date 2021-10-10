<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <title>SAVE/EDIT</title>
    <style>
        table, th, td {
            padding: 15px;
            text-align: center;
        }

        .color-red {
            color: red;
        }

        .color-green {
            color: green;
        }

        .tab {
            margin-right: 50px;
        }
    </style>
</head>
<body>
<h2>SAVE/EDIT</h2>

<form method="POST" action='meals' name="frmAddMeal">
    <input type="hidden" name="id" value="${meal.getId()}">
    DateTime : <input type="text" name="dateTime"
                      value="<c:out value="${f:formatLocalDateTime(meal.getDateTime())}"/>"/> <br/>
    Description : <input type="text" name="description" value="<c:out value="${meal.getDescription()}"/>"/><br/>
    Calories : <input type="text" name="calories" value="<c:out value="${meal.getCalories()}"/>"/> <br/>
    <button type="submit">Save</button>
    <button type="button" onclick="window.location.href = 'meals';">Cancel</button>
</form>

<h3><a href="index.html">Home</a></h3>
<hr>
</body>
</html>