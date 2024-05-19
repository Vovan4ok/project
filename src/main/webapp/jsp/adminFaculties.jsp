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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/adminFaculties.css">
    <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/i18n.js"></script>
</head>

<body class="body">
<div class="container">
    <header class="header">
        <div class="header-logo-block admin-header-logo-block">
            <img src="${pageContext.request.contextPath}/images/knu-logo.png" alt="knu logo" class="header-logo">
            <h1 class="header-heading"><spring:message code="admin.header-heading"/></h1>
        </div>
        <ul class="header-list">
            <li class="header-list-item"><a href="/home" class="header-list-item-link" style="margin-right: 30px;"><spring:message code="admin.anchor1"/></a></li>
            <li class="header-list-item"><a href="/adminFaculties" class="header-list-item-link" style="margin-right: 30px; text-decoration: underline;"><spring:message code="admin.anchor2"/></a></li>
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
    <main class="main">
        <table class="main-table">
            <thead class="table-thead">
            <tr class="table-row">
                <th class="table-number-column table-th">#</th>
                <th class="table-faculty-column table-th"><spring:message code="adminFaculties.table-faculty" /></th>
                <th class="table-plan-column table-th"><spring:message code="adminFaculties.table-plan" /></th>
                <th class="table-edit-column table-th"></th>
                <th class="table-delete-column table-th"></th>
            </tr>
            </thead>
            <tbody class="table-tbody">
            <c:forEach var="faculty" items="${faculties}">
                <tr class="table-row">
                    <td class="table-number-column table-td">${faculty.id}</td>
                    <td class="table-faculty-column table-td">${faculty.name}</td>
                    <td class="table-plan-column table-td">${faculty.planNumber}</td>
                    <td class="table-edit-column table-td">
                        <a href="/update?id=${faculty.id}" style="text-decoration: none;">
                            <button class="edit-button"><spring:message code="adminFaculties.table-edit" /></button>
                        </a>
                    </td>
                    <td class="table-delete-column table-td">
                        <a href="/delete?id=${faculty.id}" style="text-decoration: none;">
                            <button class="delete-button"><spring:message code="adminFaculties.table-delete" /></button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </main>
    <jsp:include page="footer.jsp"></jsp:include>
</div>
</body>

</html>