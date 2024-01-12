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
    <link rel="stylesheet" href="../styles/userFaculties.css">
    <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript"></script>
    <script src="../js/i18n.js"></script>
</head>

<body class="body">
<div class="container">
    <header class="header">
        <div class="header-logo-block">
            <img src="data:image/jpg;base64, ${user.encodedImage}" alt="knu logo" class="header-logo">
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
    </header>
    <main class="main">
        <table class="main-table">
            <thead class="table-thead">
                <tr class="table-row">
                    <th class="table-number-column table-th"><spring:message code="userFaculties.table-id" /></th>
                    <th class="table-faculty-column table-th"><spring:message code="userFaculties.table-faculty" /></th>
                    <th class="table-plan-column table-th"><spring:message code="userFaculties.table-plan" /></th>
                    <th class="table-list-column"></th>
                </tr>
            </thead>
            <tbody class="table-tbody">
                <c:forEach var="faculty" items="${faculties}">
                    <tr class="table-row">
                        <td class="table-number-column table-td">${faculty.id}</td>
                        <td class="table-faculty-column table-td">${faculty.name}</td>
                        <td class="table-plan-column table-td">${faculty.planNumber}</td>
                        <td class="table-list-column table-td"><a href="/listOfApplicants?id=${faculty.id}" class="table-link"><spring:message code="userFaculties.table-link" /></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </main>
    <footer class="footer">
        <ul class="footer-list">
            <li class="footer-list-item">
                <img src="../images/historical-faculty.jpg" alt="historical faculty logo"
                     class="footer-list-item-image">
            </li>
            <li class="footer-list-item">
                <img src="../images/geographical-faculty.jpg" alt="geographical faculty logo"
                     class="footer-list-item-image">
            </li>
            <li class="footer-list-item">
                <img src="../images/psychology-faculty.jpg" alt="psychology faculty logo"
                     class="footer-list-item-image">
            </li>
            <li class="footer-list-item">
                <img src="../images/physical-faculty.png" alt="physical faculty logo" class="footer-list-item-image">
            </li>
            <li class="footer-list-item">
                <img src="../images/iot-faculty.png" alt="iot faculty logo" class="footer-list-item-image">
            </li>
        </ul>
    </footer>
</div>
</body>

</html>