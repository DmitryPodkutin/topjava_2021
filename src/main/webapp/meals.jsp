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

        .tab {
            margin-right: 50px;
        }
    </style>
</head>
<body>
<h2>MealsList</h2>
<h3><a href="meals?action=save">Add Meal</a></h3><table>
    <thead>
    <tr>
        <th>DateTime</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Delete</th>
        <th>Edit</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <jsp:useBean id="meals" scope="request" type="java.util.List"/>
        <c:forEach var="meal" items="${meals}">
    <tr class="${meal.isExcess() == true ? 'color-red':'color-green'}">
        <td>${f:formatLocalDateTime(meal.getDateTime())}</td>
        <td>${meal.getDescription()}</td>
        <td>${meal.getCalories()}</td>
        <td><a href="meals?action=delete&id=<c:out value="${meal.getId()}"/>">Delete</a></td>
        <td><a href="meals?action=edit&id=<c:out value="${meal.getId()}"/>">Edit</a></td>
        </c:forEach>
    </tr>
    </tbody>
</table>
<h3><a href="index.html">Home</a></h3>
<hr>
</body>
</html>