<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Application</title>
    <link rel="stylesheet" href="../styles/application.css">
    <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript"></script>
    <script src="../js/i18n.js"></script>
</head>

<body class="body">
<div class="container">
    <header class="header">
        <div class="header-logo-block">
            <img src="data:image/jpg;base64, ${user.encodedImage}" alt="User avatar" class="header-logo">
            <h1 class="header-heading"><spring:message code="user.header-heading"/></h1>
        </div>
        <ul class="header-list">
            <li class="header-list-item" style="margin-right: 30px;"><a href="/home" class="header-list-item-link"><spring:message code="user.anchor1"/></a></li>
            <li class="header-list-item" style="margin-right: 30px;"><a href="/userFaculties" class="header-list-item-link"><spring:message code="user.anchor2"/></a></li>
            <li class="header-list-item"><a href="/application" class="header-list-item-link" style="text-decoration: underline;"><spring:message code="user.anchor3"/></a></li>
        </ul>
        <select id="locales">
            <option value="en">EN</option>
            <option value="uk">UA</option>
        </select>
    </header>
    <main class="main">
        <h2 class="main-heading"><spring:message code="application.heading"/></h2>
        <form:form action="application" class="main-form" method="post" modelAttribute="applicationForm">
            <div class="main-form-block">
                <label for="mathsMark" class="main-form-block-label"><spring:message code="form.maths-label"/></label>
                <input name="mathsMark" id="mathsMark" type="number" class="main-form-block-input" placeholder="<spring:message code="form.maths-input"/>">
            </div>
            <div class="main-form-block">
                <label for="englishMark" class="main-form-block-label"><spring:message code="form.english-label"/></label>
                <input name="englishMark" id="englishMark" type="number" class="main-form-block-input" placeholder="<spring:message code="form.english-input"/>">
            </div>
            <div class="main-form-block">
                <label for="physicsMark" class="main-form-block-label"><spring:message code="form.physics-label"/></label>
                <input name="physicsMark" id="physicsMark" type="number" class="main-form-block-input" placeholder="<spring:message code="form.physics-input"/>">
            </div>
            <div class="main-form-block">
                <label for="certificateMark" class="main-form-block-label"><spring:message code="form.certificate-label"/></label>
                <input name="certificateMark" id="certificateMark" type="number" class="main-form-block-input" placeholder="<spring:message code="form.certificate-input"/>">
            </div>
            <div class="main-form-block">
                <label for="facultyId" class="main-form-block-label"><spring:message code="form.faculty-label"/></label>
                <select class="main-form-block-input select" id="facultyId" name="facultyId">
                    <c:forEach var="faculty" items="${faculties}">
                        <option value="${faculty.id}">${faculty.name}</option>
                    </c:forEach>
                </select>
            </div>
            <input class="main-form-submit-button" type="submit" value="<spring:message code="application.button"/>">
        </form:form>
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