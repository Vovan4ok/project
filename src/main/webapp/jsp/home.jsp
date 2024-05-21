<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/home.css">
    <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/i18n.js"></script>
</head>

<body class="body">
<div class="container" <c:if test="${role=='ROLE_USER'}">style="display: flex; flex-flow: column nowrap; align-items: center; justify-content: space-between;"</c:if>>

    <c:choose>
        <c:when test="${role == 'ROLE_USER'}">
            <header class="header">
                <div class="header-logo-block">
                    <img src="${pageContext.request.contextPath}/images/avatars/${user.imageUrl}" alt="user-avatar" class="header-logo">
                    <h1 class="header-heading"><spring:message code="user.header-heading"/></h1>
                </div>
                <ul class="header-list">
                    <li class="header-list-item" style="margin-right: 30px; text-decoration: underline;"><a href="/home" class="header-list-item-link"><spring:message code="user.anchor1"/></a></li>
                    <li class="header-list-item" style="margin-right: 30px;"><a href="/userFaculties" class="header-list-item-link"><spring:message code="user.anchor2"/></a></li>
                    <li class="header-list-item"><a href="/application" class="header-list-item-link"><spring:message code="user.anchor3"/></a></li>
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
                    <img src="${pageContext.request.contextPath}/images/knu-logo.png" alt="knu logo" class="header-logo">
                    <h1 class="header-heading"><spring:message code="admin.header-heading"/></h1>
                </div>
                <ul class="header-list">
                    <li class="header-list-item"><a href="/home" class="header-list-item-link" style="margin-right: 30px; text-decoration: underline;"><spring:message code="admin.anchor1"/></a></li>
                    <li class="header-list-item"><a href="/adminFaculties" class="header-list-item-link" style="margin-right: 30px;"><spring:message code="admin.anchor2"/></a></li>
                    <li class="header-list-item"><a href="/addFaculty" class="header-list-item-link"><spring:message code="admin.anchor3"/></a></li>
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

    </header>
    <c:choose>
        <c:when test="${role == 'ROLE_USER'}">
            <main class="user-main">
                <img src="${pageContext.request.contextPath}/images/knu-photo.jpg" alt="knu-photo" class="user-main-image">
                <div class="user-main-text">
                    <spring:message code="home.user-text" />
                </div>
            </main>
        </c:when>
        <c:when test="${role == 'ROLE_ADMIN'}">
            <main class="main">
                <h1 class="main-heading"><spring:message code="home.admin-heading" /></h1>
                <table class="main-table">
                    <thead class="table-thead">
                    <tr class="table-row">
                        <th class="table-name-column table-th"><spring:message code="listOfApplicants.table-name" /></th>
                        <th class="table-surname-column table-th"><spring:message code="listOfApplicants.table-surname" /></th>
                        <th class="table-maths-column table-th"><spring:message code="listOfApplicants.table-maths" /></th>
                        <th class="table-english-column table-th"><spring:message code="listOfApplicants.table-english" /></th>
                        <th class="table-physics-column table-th"><spring:message code="listOfApplicants.table-physics" /></th>
                        <th class="table-certificate-column table-th"><spring:message code="listOfApplicants.table-certificate" /></th>
                        <th class="table-rating-column table-th"><spring:message code="listOfApplicants.table-rating" /></th>
                        <th class="table-faculty-column table-th"><spring:message code="listOfApplicants.table-faculty" /></th>
                        <th class="table-button-column table-th"></th>
                        <th class="table-button-column table-th"></th>
                    </tr>
                    </thead>
                    <tbody class="table-tbody">
                    <c:forEach var="application" items="${applications}">
                        <tr class="table-row">
                            <td class="table-name-column table-td">${application.applicant.name}</td>
                            <td class="table-surname-column table-td">${application.applicant.surname}</td>
                            <td class="table-maths-column table-td">${application.mathsMark}</td>
                            <td class="table-english-column table-td">${application.englishMark}</td>
                            <td class="table-physics-column table-td">${application.physicsMark}</td>
                            <td class="table-certificate-column table-td">${application.certificateMark}</td>
                            <td class="table-rating-column table-td">${application.ratingMark}</td>
                            <td class="table-faculty-column table-td">${application.faculty.name}</td>
                            <td class="table-button-column table-td">
                                <a href="/acceptApplication?id=${application.id}" class="table-button-column-button"><spring:message code="listOfApplicants.table-accept" /></a>
                            </td>
                            <td class="table-button-column table-td">
                                <a href="/declineApplication?id=${application.id}" class="table-button-column-button delete-button"><spring:message code="listOfApplicants.table-delete" /></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </main>
        </c:when>
    </c:choose>
    <jsp:include page="footer.jsp"></jsp:include>
</div>
</body>

</html>