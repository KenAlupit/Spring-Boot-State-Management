<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Score</title>
</head>
<body>
<%-- Output the results --%>
<h1> Your Score is ${score} / ${answerKeySize}</h1>
<h1> Your Score Percentage is ${scorePercentage}</h1>

<!-- Link to retake exam page -->
<a href="exam">Retake Exam</a>

<br />

<!-- Link to navigate back to the home page -->
<a href="/">Back</a>
</body>
</html>