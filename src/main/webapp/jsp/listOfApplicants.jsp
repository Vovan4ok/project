<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List of applicants</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/listOfApplicants.css">
    <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/i18n.js"></script>
</head>
<body class="body">
<div class="container">
    <header class="header">
        <div class="header-logo-block">
            <img src="${pageContext.request.contextPath}/images/avatars/${user.imageUrl}" alt="user-avatar" class="header-logo">
            <h1 class="header-heading"><spring:message code="user.header-heading"/></h1>
        </div>
        <ul class="header-list">
            <li class="header-list-item" style="margin-right: 30px;"><a href="/home" class="header-list-item-link"><spring:message code="user.anchor1"/></a></li>
            <li class="header-list-item" style="margin-right: 30px;"><a href="/universities" class="header-list-item-link"><spring:message code="user.anchor2"/></a></li>
            <li class="header-list-item" style="text-decoration: underline;"><a href="/makeApplication" class="header-list-item-link"><spring:message code="user.anchor3"/></a></li>
        </ul>
        <select id="locales">
            <option value="en">EN</option>
            <option value="uk">UA</option>
        </select>
        <form class="header-logout-form" action="/logout" method="post">
            <button type="submit" class="header-logout-form-button">
                <i class="fa-solid fa-right-from-bracket header-icon"></i>
            </button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </header>
    <main class="main">
        <h1 class="main-heading">${speciality.department.faculty.university.name}/${speciality.department.faculty.name}/${speciality.department.name}/${speciality.name} (${speciality.shortName})</h1>
        <c:forEach var="applicationDTO" items="${applicationDTOS}">
            <div class="info-block">
                <h2 class="info-block-place">${applicationDTO.place}</h2>
                <div class="info-block-marks">
                    <p class="info-block-text"><span><spring:message code="application.applicant" /></span> ${applicationDTO.application.applicant.surname} ${applicationDTO.application.applicant.name} ${applicationDTO.application.applicant.patronimic}</p>
                    <p class="info-block-text"><span><spring:message code="application.maths" /></span> ${applicationDTO.application.mathsMark}</p>
                    <p class="info-block-text"><span><spring:message code="application.physics" /></span> ${applicationDTO.application.physicsMark}</p>
                    <p class="info-block-text"><span><spring:message code="application.english" /></span> ${applicationDTO.application.englishMark}</p>
                    <p class="info-block-text"><span><spring:message code="application.certificate" /></span> ${applicationDTO.application.certificateMark}</p>
                </div>
                <div class="info-block-rating">
                    <p class="info-block-text"><span><spring:message code="application.rating" /></span> ${applicationDTO.application.ratingMark}</p>
                    <p class="info-block-text" style="margin-top: 20px;">
                        <c:choose>
                            <c:when test="${applicationDTO.place <= speciality.budgetPlaces}"><span style="color: #5cb815;">Budget</span></c:when>
                            <c:when test="${applicationDTO.place <= speciality.planNumber}"><span style="color: #d2c914;">Contract</span></c:when>
                            <c:when test="${applicationDTO.place > speciality.planNumber}"><span style="color: #bc0505;">Miss</span></c:when>
                        </c:choose>
                    </p>
                </div>
            </div>
        </c:forEach>
    </main>
    <jsp:include page="footer.jsp"></jsp:include>
</div>
</body>

</html>