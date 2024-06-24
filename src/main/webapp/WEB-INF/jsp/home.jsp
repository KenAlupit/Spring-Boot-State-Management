<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Home</title>
</head>
<body>
<h1>Welcome ${userSession.username}!</h1>

<!-- Link to the exam page -->
<a href = "exam">${examMessage}</a>
<br />
<!-- Link to view grades page -->
<a href = "grade">View Grades</a>
<br />
<br />

<!-- Link to logout  -->
<a href = "logout">Logout</a>
</body>
</html>