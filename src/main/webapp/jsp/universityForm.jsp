<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="uk">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>University Form</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/formPage.css">
    <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/i18n.js"></script>
</head>

<body class="body">
<div class="container">
    <header class="header">
        <div class="header-logo-block admin-header-logo-block">
            <img src="${pageContext.request.contextPath}/images/avatars/${user.imageUrl}" alt="user-avatar" class="header-logo">
            <h1 class="header-heading"><spring:message code="admin.header-heading"/></h1>
        </div>
        <ul class="header-list">
            <li class="header-list-item"><a href="/home" class="header-list-item-link" style="margin-right: 30px;"><spring:message code="admin.anchor1"/></a></li>
            <li class="header-list-item"><a href="/universities" class="header-list-item-link" style="margin-right: 30px;"><spring:message code="admin.anchor2"/></a></li>
            <li class="header-list-item"><a href="/addMenu" class="header-list-item-link" style="text-decoration: underline;"><spring:message code="admin.anchor3"/></a></li>
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
        <h2 class="main-heading"><spring:message code="universityForm.heading" /></h2>
        <form:form modelAttribute="universityForm" class="main-form" method="post">
            <div class="main-form-block" <c:if test="${msg == null}">style="margin-top: 0;"</c:if>>
                <span class="main-form-block-label" style="color: red;">${msg}</span>
            </div>
            <div class="main-form-block">
                <label for="name" class="main-form-block-label"><spring:message code="form.university-name-label" /></label>
                <input name="name" id="name" type="text" required class="main-form-block-input" placeholder="<spring:message code="form.university-name-input" />" <c:if test="${mode == 'MODE_UPDATE'}">value="${universityForm.name}"</c:if>>
            </div>
            <div class="main-form-block">
                <label for="shortName" class="main-form-block-label"><spring:message code="form.university-short-name-label" /></label>
                <input name="shortName" id="shortName" required type="text" class="main-form-block-input" placeholder="<spring:message code="form.university-short-name-input" />" <c:if test="${mode == 'MODE_UPDATE'}">value="${universityForm.shortName}"</c:if>>
            </div>
            <div class="main-form-block">
                <label for="description" style="align-self: start;" class="main-form-block-label"><spring:message code="form.university-description-label" /></label>
                <textarea name="description" id="description" style="height: 200px;" required class="main-form-block-input" placeholder="<spring:message code="form.university-description-input" />"><c:if test="${mode == 'MODE_UPDATE'}">${universityForm.description}</c:if></textarea>
            </div>
            <div class="main-form-block">
                <label for="establishedYear" class="main-form-block-label"><spring:message code="form.university-established-year-label" /></label>
                <input name="establishedYear" id="establishedYear" required type="number" class="main-form-block-input" placeholder="<spring:message code="form.university-established-year-input" />" <c:if test="${mode == 'MODE_UPDATE'}">value="${universityForm.establishedYear}"</c:if> min="0" max="32767">
            </div>
            <div class="main-form-block">
                <label for="rector" class="main-form-block-label"><spring:message code="form.university-rector-label" /></label>
                <input name="rector" id="rector" required type="text" class="main-form-block-input" placeholder="<spring:message code="form.university-rector-input" />" <c:if test="${mode == 'MODE_UPDATE'}">value="${universityForm.rector}"</c:if>>
            </div>
            <div class="main-form-block">
                <label for="address" style="align-self: start;" class="main-form-block-label"><spring:message code="form.university-address-label" /></label>
                <textarea name="address" id="address" required class="main-form-block-input" style="height: 65px;" placeholder="<spring:message code="form.university-address-input" />"><c:if test="${mode == 'MODE_UPDATE'}">${universityForm.address}</c:if></textarea>
            </div>
            <div class="main-form-block">
                <label for="websiteUrl" class="main-form-block-label"><spring:message code="form.university-website-url-label" /></label>
                <input name="websiteUrl" id="websiteUrl" required type="text" class="main-form-block-input" placeholder="<spring:message code="form.university-website-url-input" />" <c:if test="${mode == 'MODE_UPDATE'}">value="${universityForm.websiteUrl}"</c:if>>
            </div>
            <div class="main-form-block">
                <label for="email" class="main-form-block-label"><spring:message code="form.university-email-label" /></label>
                <input name="email" id="email" type="email" required class="main-form-block-input" placeholder="<spring:message code="form.university-email-input" />" <c:if test="${mode == 'MODE_UPDATE'}">value="${universityForm.email}"</c:if>>
            </div>
            <div class="main-form-block">
                <label for="phone" class="main-form-block-label"><spring:message code="form.university-phone-label" /></label>
                <input name="phone" id="phone" type="text" required class="main-form-block-input" placeholder="<spring:message code="form.university-phone-input" />" <c:if test="${mode == 'MODE_UPDATE'}">value="${universityForm.phone}"</c:if>>
            </div>
            <div class="main-form-submit-block">
                <input class="main-form-submit-block-button" required type="submit" value="<spring:message code="addFaculty.button" />">
            </div>
        </form:form>
    </main>
    <jsp:include page="footer.jsp"></jsp:include>
</div>
</body>

</html>