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
        <%-- Iterate over each questionAndOptions array --%>
        <div style="text-align: left;">
        <%-- Display the question --%>
        <p>${questionAndOptions[0]}</p>

        <%-- Set shuffledOptions to questionAndOptions --%>
        <c:set var="shuffledOptions" value="${questionAndOptions}" />

        <%-- Iterate over shuffledOptions (same as questionAndOptions) --%>
        <c:forEach items="${shuffledOptions}" var="option" begin="1">
            <%-- Create radio buttons for each option --%>
            <input type="radio" id="${option}" name="${fn:toLowerCase(fn:replace((fn:replace(questionAndOptions[0], '?', '')), ' ', '_'))}" value="${option}" required/>

            <%-- Label for the radio button --%>
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
<a href="/">Back</a>

</form>

</body>
</html>