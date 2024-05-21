<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Faculties</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/faculties.css">
    <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/i18n.js"></script>
</head>

<body class="body">
<div class="container">
    <jsp:include page="welcome-header.jsp"></jsp:include>
    <main class="main">
        <c:forEach var="faculty" items="${faculties}">
            <div class="faculty-block">
                <h1 class="faculty-block-header">${faculty.name}</h1>
                <p class="faculty-block-text"><spring:message code="faculties.number-of-students" /> ${faculty.numberOfStudents}</p>
                <p class="faculty-block-text"><spring:message code="faculties.plan-number" /> ${faculty.planNumber}</p>
                <p class="faculty-block-text"><spring:message code="faculties.website-url" /> <a target="_blank" class="website-link" href="${faculty.websiteUrl}">${faculty.websiteUrl}</a></p>
                <a href="/faculty?id=${faculty.id}" class="faculty-block-details"><spring:message code="faculties.details" /></a>
            </div>
        </c:forEach>
    </main>
    <jsp:include page="footer.jsp"></jsp:include>
</div>
</body>

</html>