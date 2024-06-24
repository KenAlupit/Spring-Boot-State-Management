<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Exam</title>
</head>
<body>
<form action="exam" method="post">
    <c:forEach var="questionAndOptions" items="${questionsAndOptions}">
        <div style="text-align: left;">
            <p>${questionAndOptions[0]}</p>
            <c:set var="shuffledOptions" value="${questionAndOptions}" />
            <c:forEach items="${shuffledOptions}" var="option" begin="1">
                <input type="radio" id="${option}" name="${fn:toLowerCase(fn:replace((fn:replace(questionAndOptions[0], '?', '')), ' ', '_'))}" value="${option}" required/>
                <label for="${option}">${option}</label><br/>
            </c:forEach>
        </div>
    </c:forEach>

    <br/>
    <!-- Reset button to clear form inputs -->
    <input type="reset"/>

    <!-- Submit button to submit the form -->
    <input type="submit" value="Submit"/>

    <br /> <br />

    <!-- Link to navigate back to the home page -->
    <a href="home">Back</a>

</form>

</body>
</html>