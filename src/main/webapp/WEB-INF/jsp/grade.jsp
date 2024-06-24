<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Grade</title>
</head>
<body>
<h2>Grades</h2>
<table border = '1'>
    <tr>
        <th>User name</th>
        <th>Grade</th>
    </tr>
    <c:forEach items="${userGrades}" var="currentGrade">
        <tr>
            <td><c:out value="${currentGrade.key}"/></td>
            <td><c:out value="${currentGrade.value}"/></td>
        </tr>
    </c:forEach>
</table>
<br />

<!-- Link to navigate back to the home page -->
<a href="/">Back</a>
</body>
</html>