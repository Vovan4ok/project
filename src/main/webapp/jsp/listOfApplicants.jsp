<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="uk">

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
            <li class="header-list-item" style="margin-right: 30px;text-decoration: underline;"><a href="/universities" class="header-list-item-link"><spring:message code="user.anchor2"/></a></li>
            <li class="header-list-item"><a href="/makeApplication" class="header-list-item-link"><spring:message code="user.anchor3"/></a></li>
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
            <div class="applicant-card">
                <div class="card-header">
                    <div class="place">#${applicationDTO.place}</div>
                    <div class="name">${applicationDTO.application.applicant.surname} ${applicationDTO.application.applicant.name} ${applicationDTO.application.applicant.patronimic}</div>
                    <div class="status
                        ${applicationDTO.place <= speciality.budgetPlaces ? 'budget' :
                          applicationDTO.place <= speciality.planNumber ? 'contract' : 'miss'}">
                        <c:choose>
                            <c:when test="${applicationDTO.place <= speciality.budgetPlaces}">Budget</c:when>
                            <c:when test="${applicationDTO.place <= speciality.planNumber}">Contract</c:when>
                            <c:otherwise>Miss</c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="card-body">
                    <div class="marks">
                        <div><span><spring:message code="application.maths" /></span> ${applicationDTO.application.mathsMark}</div>
                        <div><span><spring:message code="application.ukrainian" /></span> ${applicationDTO.application.ukrainianMark}</div>
                        <div><span><spring:message code="application.english" /></span> ${applicationDTO.application.englishMark}</div>
                        <div><span><spring:message code="application.certificate" /></span> ${applicationDTO.application.certificateMark}</div>
                    </div>
                    <div class="rating-div">
                        <div class="rating"><spring:message code="application.priority" /> ${applicationDTO.application.priority}</div>
                        <div class="rating" style="margin-top: 20px;"><spring:message code="application.rating" /> ${applicationDTO.application.ratingMark}</div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </main>
    <jsp:include page="footer.jsp"></jsp:include>
</div>
</body>
</html>