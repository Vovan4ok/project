<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="../styles/login.css">
    <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
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
            <li class="header-list-item" style="margin-right: 75px;"><a href="" class="header-list-item-link"><spring:message code="enter.anchor1"/></a></li>
            <li class="header-list-item"><a href="#" class="header-list-item-link"><spring:message code="enter.anchor2"/></a></li>
        </ul>
        <select id="locales">
            <option value="en">EN</option>
            <option value="uk">UA</option>
        </select>
    </header>
    <main class="main">
        <h2 class="main-heading"><spring:message code="login.heading"/></h2>
        <form:form name="f" action="/login" class="main-form" method="post">
            <div class="main-form-block">
                <label for="email" class="main-form-block-label"><spring:message code="form.email-label"/></label>
                <input name="email" type="email" id="email" class="main-form-block-input" placeholder="<spring:message code="form.email-input"/>">
            </div>
            <div class="main-form-block">
                <label for="password" class="main-form-block-label"><spring:message code="form.password-label"/></label>
                <input name="password" type="password" id="password" class="main-form-block-input"
                       placeholder="<spring:message code="form.password-input"/>">
            </div>
            <div class="main-form-submit-block">
                <input class="main-form-submit-block-button" type="submit" value="<spring:message code="login.submit-button"/>">
                <span class="main-form-submit-block-span">
                        <spring:message code="login.dont-have-account"/> <a href="/registration" class="main-form-submit-block-span-link"><spring:message code="login.registration-link"/></a>
                </span>
            </div>
            <span class="main-form-block-label" style="color: red;">${message}</span>
            <span class="main-form-block-label" style="color: red;">${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
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