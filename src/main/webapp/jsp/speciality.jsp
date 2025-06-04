<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="uk">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${speciality.name}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/faculty.css">
    <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/i18n.js"></script>
</head>

<body class="body">
<div class="container">
    <c:choose>
        <c:when test="${role == 'ROLE_USER'}">
            <header class="header">
                <div class="header-logo-block">
                    <img src="${pageContext.request.contextPath}/images/avatars/${user.imageUrl}" alt="user-avatar" class="header-logo">
                    <h1 class="header-heading"><spring:message code="user.header-heading"/></h1>
                </div>
                <ul class="header-list">
                    <li class="header-list-item"><a href="/home" class="header-list-item-link"><spring:message code="user.anchor1"/></a></li>
                    <li class="header-list-item" style="text-decoration: underline;"><a href="/universities" class="header-list-item-link"><spring:message code="user.anchor2"/></a></li>
                    <li class="header-list-item"><a href="/makeApplication" class="header-list-item-link"><spring:message code="user.anchor3"/></a></li>
                    <li class="header-list-item"><a href="/documents" class="header-list-item-link"><spring:message code="user.anchor4"/></a></li>
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
        </c:when>
        <c:when test="${role == 'ROLE_ADMIN'}">
            <header class="header">
                <div class="header-logo-block admin-header-logo-block">
                    <img src="${pageContext.request.contextPath}/images/avatars/${user.imageUrl}" alt="user-avatar" class="header-logo">
                    <h1 class="header-heading"><spring:message code="admin.header-heading"/></h1>
                </div>
                <ul class="header-list">
                    <li class="header-list-item"><a href="/home" class="header-list-item-link"><spring:message code="admin.anchor1"/></a></li>
                    <li class="header-list-item"><a href="/universities" class="header-list-item-link" style="text-decoration: underline;"><spring:message code="admin.anchor2"/></a></li>
                    <li class="header-list-item"><a href="/addMenu" class="header-list-item-link"><spring:message code="admin.anchor3"/></a></li>
                    <li class="header-list-item"><a href="/documents" class="header-list-item-link"><spring:message code="user.anchor4"/></a></li>
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
        </c:when>
    </c:choose>
    <main class="main">
        <div class="info-block">
            <h1>${speciality.department.faculty.university.name}/${speciality.department.faculty.name}/${speciality.department.name}/${speciality.name} (${speciality.shortName})</h1>
            <p><span><spring:message code="speciality.code" /></span> ${speciality.code}</p>
            <p><span><spring:message code="speciality.level" /></span> ${speciality.level}</p>
            <p><span><spring:message code="speciality.duration" /></span> <fmt:formatNumber value="${Math.floor(speciality.duration / 12)}" pattern="#" /> years ${speciality.duration % 12} months</p>
            <p><span><spring:message code="speciality.planNumber" /></span> ${speciality.planNumber}</p>
            <p><span><spring:message code="speciality.budgetPlaces" /></span> ${speciality.budgetPlaces}</p>
            <p><span><spring:message code="speciality.accreditation" /></span> ${speciality.accreditation}</p>
            <p><span><spring:message code="speciality.employmentProspects" /></span> ${speciality.employmentProspects}</p>
            <p><span><spring:message code="speciality.curriculum" /></span> ${speciality.curriculum}</p>
            <p><span><spring:message code="speciality.pricePerYear" /></span> ${speciality.pricePerYear}</p>
            <p><span><spring:message code="speciality.mathsCoef" /></span> ${speciality.mathsCoef}</p>
            <p><span><spring:message code="speciality.ukrainianCoef" /></span> ${speciality.ukrainianCoef}</p>
            <p><span><spring:message code="speciality.englishCoef" /></span> ${speciality.englishCoef}</p>
            <p><span><spring:message code="speciality.certificateCoef" /></span> ${speciality.certificateCoef}</p>
            <p>${speciality.description}</p>
            <c:choose>
                <c:when test="${role == 'ROLE_ADMIN'}">
                    <div class="info-block-buttons">
                        <a href="/updateSpeciality?id=${speciality.id}" style="text-decoration: none;">
                            <button class="edit-button"><spring:message code="infoBlock.edit-button" /></button>
                        </a>
                        <a href="/deleteSpeciality?id=${speciality.id}" style="text-decoration: none;">
                            <button class="delete-button"><spring:message code="infoBlock.delete-button" /></button>
                        </a>
                    </div>
                </c:when>
                <c:when test="${role == 'ROLE_USER'}">
                    <div class="info-block-buttons">
                        <a href="/listOfApplicants?speciality_id=${speciality.id}" class="info-block-details" style="width: 230px;"><spring:message code="list-of-applicants" /></a>
                        <a href="/makeApplication?speciality_id=${speciality.id}" class="info-block-details" style="width: 230px;"><spring:message code="user.anchor3" /></a>
                    </div>
                </c:when>
            </c:choose>
        </div>
    </main>
    <jsp:include page="footer.jsp"></jsp:include>
</div>
</body>

</html>