<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="uk">

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
    <c:choose>
        <c:when test="${role == 'ROLE_USER'}">
            <header class="header">
                <div class="header-logo-block">
                    <img src="${pageContext.request.contextPath}/images/avatars/${user.imageUrl}" alt="user-avatar" class="header-logo">
                    <h1 class="header-heading"><spring:message code="user.header-heading"/></h1>
                </div>
                <ul class="header-list">
                    <li class="header-list-item" style="margin-right: 30px;"><a href="/home" class="header-list-item-link"><spring:message code="user.anchor1"/></a></li>
                    <li class="header-list-item" style="margin-right: 30px; text-decoration: underline;"><a href="/universities" class="header-list-item-link"><spring:message code="user.anchor2"/></a></li>
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
        </c:when>
        <c:when test="${role == 'ROLE_ADMIN'}">
            <header class="header">
                <div class="header-logo-block admin-header-logo-block">
                    <img src="${pageContext.request.contextPath}/images/avatars/${user.imageUrl}" alt="user-avatar" class="header-logo">
                    <h1 class="header-heading"><spring:message code="admin.header-heading"/></h1>
                </div>
                <ul class="header-list">
                    <li class="header-list-item"><a href="/home" class="header-list-item-link" style="margin-right: 30px;"><spring:message code="admin.anchor1"/></a></li>
                    <li class="header-list-item"><a href="/universities" class="header-list-item-link" style="margin-right: 30px; text-decoration: underline;"><spring:message code="admin.anchor2"/></a></li>
                    <li class="header-list-item"><a href="/addMenu" class="header-list-item-link"><spring:message code="admin.anchor3"/></a></li>
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
            <h1>${faculty.university.name}/${faculty.name} (${faculty.shortName})</h1>
            <p><span><spring:message code="faculty.head" /></span> ${faculty.headOfFaculty}</p>
            <p><span><spring:message code="faculty.year" /></span> ${faculty.establishedYear}</p>
            <p><span><spring:message code="faculty.number-of-students" /></span> ${faculty.numberOfStudents}</p>
            <p><i class="fa-solid fa-location-dot"></i> ${faculty.address}</p>
            <p><i class="fa-solid fa-globe"></i> <a target="_blank" href="${faculty.websiteUrl}">${faculty.websiteUrl}</a></p>
            <p><i class="fa-regular fa-envelope"></i> ${faculty.email}</p>
            <p><i class="fa-solid fa-phone"></i> ${faculty.phone}</p>
            <p>${faculty.description}</p>
            <a href="/departments?faculty_id=${faculty.id}" class="info-block-details"><spring:message code="faculty.departments" /></a>
            <c:choose>
                <c:when test="${role == 'ROLE_ADMIN'}">
                    <div class="info-block-buttons">
                        <a href="/updateFaculty?id=${faculty.id}" style="text-decoration: none;">
                            <button class="edit-button"><spring:message code="infoBlock.edit-button" /></button>
                        </a>
                        <a href="/deleteFaculty?id=${faculty.id}" style="text-decoration: none;">
                            <button class="delete-button"><spring:message code="infoBlock.delete-button" /></button>
                        </a>
                    </div>
                </c:when>
            </c:choose>
        </div>
    </main>
    <jsp:include page="footer.jsp"></jsp:include>
</div>
</body>

</html>