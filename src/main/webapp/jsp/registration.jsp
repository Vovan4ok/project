<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/registration.css">
    <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/i18n.js"></script>
</head>

<body class="body">
<div class="container">
    <jsp:include page="welcome-header.jsp"></jsp:include>
    <main class="main">
        <h2 class="main-heading"><spring:message code="registration.heading"/></h2>
        <form:form action="registration" class="main-form" method="post" enctype="multipart/form-data">
            <div class="main-form-block">
                <label for="name" class="main-form-block-label"><spring:message code="form.name-label"/></label>
                <input name="name" type="text" id="name" class="main-form-block-input" placeholder="<spring:message code="form.name-input"/>" required>
            </div>
            <div class="main-form-block">
                <label for="surname" class="main-form-block-label"><spring:message code="form.surname-label"/></label>
                <input name="surname" type="text" id="surname" class="main-form-block-input" placeholder="<spring:message code="form.surname-input"/>" required>
            </div>
            <div class="main-form-block">
                <label for="patronimic" class="main-form-block-label"><spring:message code="form.patronimic-label"/></label>
                <input name="patronimic" type="text" id="patronimic" class="main-form-block-input" placeholder="<spring:message code="form.patronimic-input"/>" required>
            </div>
            <div class="main-form-block">
                <span class="main-form-block-label" style="color: red;">${msg}</span>
                <label for="email" class="main-form-block-label"><spring:message code="form.email-label"/></label>
                <input name="email" type="email" id="email" class="main-form-block-input" placeholder="<spring:message code="form.email-input"/>" required>
            </div>
            <div class="main-form-block">
                <label for="password" class="main-form-block-label"><spring:message code="form.password-label"/></label>
                <input name="password" type="password" id="password" class="main-form-block-input"
                       placeholder="<spring:message code="form.password-input"/>" required>
            </div>
            <input name="imageFile" id="imageFile" style="margin-top: 15px; margin-right: 65px;" type="file" required>
            <div class="main-form-submit-block">
                <input class="main-form-submit-block-button" type="submit" value="<spring:message code="registration.submit-button"/>">
                <span class="main-form-submit-block-span">
                        <spring:message code="registration.have-account"/> <a href="/login" class="main-form-submit-block-span-link"><spring:message code="registration.login-link"/></a>
                </span>
            </div>
        </form:form>
    </main>
    <jsp:include page="footer.jsp"></jsp:include>
</div>
</body>

</html>