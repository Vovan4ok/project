<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List</title>
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
            <li class="header-list-item" style="margin-right: 30px; text-decoration: underline;"><a href="/userFaculties" class="header-list-item-link"><spring:message code="user.anchor2"/></a></li>
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
    <main class="main">
        <h1 class="main-heading">${faculty.name}</h1>
        <table class="main-table">
            <thead class="table-thead">
            <tr class="table-row">
                <th class="table-th">#</th>
                <th class="table-th"><spring:message code="listOfApplicants.table-name" /></th>
                <th class="table-th"><spring:message code="listOfApplicants.table-surname" /></th>
                <th class="table-th"><spring:message code="listOfApplicants.table-maths" /></th>
                <th class="table-th"><spring:message code="listOfApplicants.table-english" /></th>
                <th class="table-th"><spring:message code="listOfApplicants.table-physics" /></th>
                <th class="table-th"><spring:message code="listOfApplicants.table-certificate" /></th>
                <th class="table-th"><spring:message code="listOfApplicants.table-rating" /></th>
            </tr>
            </thead>
            <tbody class="table-tbody">
            <c:forEach var="applicationDTO" items="${applicationDTOS}">
                <tr class="table-row" <c:if test="${applicationDTO.place <= faculty.planNumber}">style='background-color: #a2f0a7;'</c:if>>
                    <td class="table-td">${applicationDTO.place}</td>
                    <td class="table-td">${applicationDTO.application.applicant.name}</td>
                    <td class="table-td">${applicationDTO.application.applicant.surname}</td>
                    <td class="table-td">${applicationDTO.application.mathsMark}</td>
                    <td class="table-td">${applicationDTO.application.englishMark}</td>
                    <td class="table-td">${applicationDTO.application.physicsMark}</td>
                    <td class="table-td">${applicationDTO.application.certificateMark}</td>
                    <td class="table-td">${applicationDTO.application.ratingMark}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </main>
    <jsp:include page="footer.jsp"></jsp:include>
</div>
</body>

</html>
