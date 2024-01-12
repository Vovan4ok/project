<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
    <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../styles/403.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript"></script>
    <script src="../js/i18n.js"></script>
</head>

<body class="body">
<div class="container">
    <header class="header">
        <div class="header-logo-block">
            <img src="../images/knu-logo.png" alt="knu logo" class="header-logo">
            <h1 class="header-heading"><spring:message code="enter.header-heading"/></h1>
        </div>
        <ul class="header-list">
            <li class="header-list-item" style="margin-right: 75px;"><a href="#" class="header-list-item-link"><spring:message code="enter.anchor1"/></a></li>
            <li class="header-list-item"><a href="#" class="header-list-item-link"><spring:message code="enter.anchor2"/></a></li>
        </ul>
        <select id="locales">
            <option value="en">EN</option>
            <option value="ua">UA</option>
        </select>
    </header>
    <main class="main">
        <h3 class="error-text"><spring:message code="403.text" /></h3>
        <form class="main-form" action="/logout" method="post">
            <input class="submit-button" type="submit" value="<spring:message code="403.button" />"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
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