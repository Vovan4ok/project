<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${faculty.name}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/faculty.css">
    <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/i18n.js"></script>
</head>

<body class="body">
<div class="container">
    <jsp:include page="welcome-header.jsp"></jsp:include>
    <main class="main">
        <div class="faculty-block">
            <h1>${faculty.name}</h1>
            <p><spring:message code="faculty.plan-number" /> ${faculty.planNumber}</p>
            <p><spring:message code="faculty.head" /> ${faculty.headOfFaculty}</p>
            <p><spring:message code="faculty.year" /> ${faculty.establishedYear}</p>
            <p><spring:message code="faculty.number-of-students" /> ${faculty.numberOfStudents}</p>
            <p><i class="fa-solid fa-location-dot"></i> ${faculty.address}</p>
            <p><i class="fa-solid fa-globe"></i> <a target="_blank" href="${faculty.websiteUrl}">${faculty.websiteUrl}</a></p>
            <p><i class="fa-regular fa-envelope"></i> ${faculty.email}</p>
            <p><i class="fa-solid fa-phone"></i> ${faculty.phone}</p>
            <p>${faculty.description}</p>
        </div>
    </main>
    <jsp:include page="footer.jsp"></jsp:include>
</div>
</body>

</html>