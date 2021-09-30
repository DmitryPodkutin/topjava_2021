<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <title>Meals</title>
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
    </style>
</head>
<body>
<h2>MealsList</h2>
<table>
    <thead>
    <tr>
        <th>DateTime</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <jsp:useBean id="meals" scope="request" type="java.util.List"/>
        <c:forEach var="meal" items="${meals}">
    <tr class="${meal.isExcess() == true ? 'color-red':'color-green'}">
        <td>${f:formatLocalDateTime(meal.getDateTime(), 'dd-MM-yyy HH:mm:ss')}</td>
        <td>${meal.getDescription()}</td>
        <td>${meal.getCalories()}</td>
        </c:forEach>
    </tr>
    </tbody>
</table>

<h3><a href="index.html">Home</a></h3>
<hr>
</body>
</html>