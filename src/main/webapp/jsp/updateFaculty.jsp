<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/facultyForm.css">
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
        <h2 class="main-heading"><spring:message code="addFaculty.heading" /></h2>
        <form:form action="update" class="main-form" method="post" modelAttribute="facultyForm">
            <input id="id" name="id" style="display: none;" value="${facultyForm.id}" type="number"/>
            <div class="main-form-block">
                <label for="name" class="main-form-block-label"><spring:message code="form.name-label" /></label>
                <input name="name" id="name" type="text" class="main-form-block-input" placeholder="<spring:message code="form.faculty-name-input" />" value="${facultyForm.name}">
            </div>
            <div class="main-form-block">
                <label for="planNumber" class="main-form-block-label"><spring:message code="form.faculty-plan-number-label" /></label>
                <input name="planNumber" id="planNumber" type="number" class="main-form-block-input" placeholder="<spring:message code="form.faculty-plan-number-input" />" min="0" max="32767" value="${facultyForm.planNumber}">
            </div>
            <div class="main-form-block">
                <label for="headOfFaculty" class="main-form-block-label"><spring:message code="form.faculty-head-of-faculty-label" /></label>
                <input name="headOfFaculty" id="headOfFaculty" required type="text" class="main-form-block-input" placeholder="<spring:message code="form.faculty-head-of-faculty-input" />" value="${facultyForm.headOfFaculty}">
            </div>
            <div class="main-form-block">
                <label for="establishedYear" class="main-form-block-label"><spring:message code="form.faculty-established-year-label" /></label>
                <input name="establishedYear" id="establishedYear" required type="number" class="main-form-block-input" placeholder="<spring:message code="form.faculty-established-year-input" />" min="0" max="32767" value="${facultyForm.establishedYear}">
            </div>
            <div class="main-form-block">
                <label for="numberOfStudents" class="main-form-block-label"><spring:message code="form.faculty-number-of-students-label" /></label>
                <input name="numberOfStudents" id="numberOfStudents" required type="number" class="main-form-block-input" placeholder="<spring:message code="form.faculty-number-of-students-input" />" min="0" max="32767" value="${facultyForm.numberOfStudents}">
            </div>
            <div class="main-form-block">
                <label for="address" style="align-self: start;" class="main-form-block-label"><spring:message code="form.faculty-address-label" /></label>
                <textarea name="address" id="address" required class="main-form-block-input" style="height: 65px;" placeholder="<spring:message code="form.faculty-address-input" />">${facultyForm.address}</textarea>
            </div>
            <div class="main-form-block">
                <label for="websiteUrl" class="main-form-block-label"><spring:message code="form.faculty-website-url-label" /></label>
                <input name="websiteUrl" id="websiteUrl" required type="text" class="main-form-block-input" placeholder="<spring:message code="form.faculty-website-url-input" />" value="${facultyForm.websiteUrl}">
            </div>
            <div class="main-form-block">
                <label for="email" class="main-form-block-label"><spring:message code="form.faculty-email-label" /></label>
                <input name="email" id="email" type="email" required class="main-form-block-input" placeholder="<spring:message code="form.faculty-email-input" />" value="${facultyForm.email}">
            </div>
            <div class="main-form-block">
                <label for="phone" class="main-form-block-label"><spring:message code="form.faculty-phone-label" /></label>
                <input name="phone" id="phone" type="text" required class="main-form-block-input" placeholder="<spring:message code="form.faculty-phone-input" />" value="${facultyForm.phone}">
            </div>
            <div class="main-form-block">
                <label for="description" style="align-self: start;" class="main-form-block-label"><spring:message code="form.faculty-description-label" /></label>
                <textarea name="description" id="description" required class="main-form-block-input" style="height: 200px;" placeholder="<spring:message code="form.faculty-description-input" />">${facultyForm.description}</textarea>
            </div>
            <input class="main-form-submit-block-button" style="width: 145px;" type="submit" value="<spring:message code="updateFaculty.button" />">
        </form:form>
    </main>
    <jsp:include page="footer.jsp"></jsp:include>
</div>
</body>

</html>