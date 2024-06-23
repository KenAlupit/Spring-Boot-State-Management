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

<a href = "exam.jsp">test</a>
<br />
<!-- Link to view grades page -->
<a href = "grade.jsp">View Grades</a>
<br />
<br />

<!-- Link to logout (redirects to login and deletes cookies) -->
<a href = "logout">Logout</a>
<%
    // Set cache control headers to prevent caching
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setHeader("Expires", "0"); // Proxies.
%>
</body>
</html>