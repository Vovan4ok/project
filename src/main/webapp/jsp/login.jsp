<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="uk">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/login.css">
    <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/i18n.js"></script>
</head>

<body class="body">
<div class="container">
    <jsp:include page="welcome-header.jsp"></jsp:include>
    <main class="main">
        <h2 class="main-heading"><spring:message code="login.heading"/></h2>
        <form:form name="f" action="/login" class="main-form" method="post">
            <div class="main-form-block">
                <label for="email" class="main-form-block-label"><spring:message code="form.email-label"/></label>
                <input name="email" type="email" id="email" required class="main-form-block-input" placeholder="<spring:message code="form.email-input"/>">
            </div>
            <div class="main-form-block">
                <label for="password" class="main-form-block-label"><spring:message code="form.password-label"/></label>
                <input name="password" type="password" id="password" required class="main-form-block-input"
                       placeholder="<spring:message code="form.password-input"/>">
            </div>
            <div class="main-form-submit-block">
                <input class="main-form-submit-block-button" type="submit" value="<spring:message code="login.submit-button"/>">
                <span class="main-form-submit-block-span">
                        <spring:message code="login.dont-have-account"/> <a href="/registration" class="main-form-submit-block-span-link"><spring:message code="login.registration-link"/></a>
                </span>
            </div>
            <span class="main-form-block-label" style="color: red; align-self: center;margin-top: 20px;">${message}</span>
            <span class="main-form-block-label" style="color: red; align-self: center;margin-top: 20px;">${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form:form>
    </main>
    <jsp:include page="footer.jsp"></jsp:include>
</div>
</body>

</html>