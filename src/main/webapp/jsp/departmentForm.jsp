<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Department Form</title>
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
        <h2 class="main-heading"><spring:message code="departmentForm.heading" /></h2>
        <form:form modelAttribute="departmentForm" class="main-form" method="post">
            <div class="main-form-block" <c:if test="${msg == null}">style="margin-top: 0;"</c:if>>
                <span class="main-form-block-label" style="color: red;">${msg}</span>
            </div>
            <div class="main-form-block">
                <label for="name" class="main-form-block-label"><spring:message code="form.department-name-label" /></label>
                <input name="name" id="name" type="text" required class="main-form-block-input" placeholder="<spring:message code="form.department-name-input" />" <c:if test="${mode == 'MODE_UPDATE'}">value="${departmentForm.name}"</c:if>>
            </div>
            <div class="main-form-block">
                <label for="shortName" class="main-form-block-label"><spring:message code="form.department-short-name-label" /></label>
                <input name="shortName" id="shortName" required type="text" class="main-form-block-input" placeholder="<spring:message code="form.department-short-name-input" />" <c:if test="${mode == 'MODE_UPDATE'}">value="${departmentForm.shortName}"</c:if>>
            </div>
            <div class="main-form-block">
                <label for="headOfDepartment" class="main-form-block-label"><spring:message code="form.department-head-of-department-label" /></label>
                <input name="headOfDepartment" id="headOfDepartment" required type="text" class="main-form-block-input" placeholder="<spring:message code="form.department-head-of-department-input" />" <c:if test="${mode == 'MODE_UPDATE'}">value="${departmentForm.headOfDepartment}"</c:if>>
            </div>
            <div class="main-form-block">
                <label for="establishedYear" class="main-form-block-label"><spring:message code="form.department-established-year-label" /></label>
                <input name="establishedYear" id="establishedYear" required type="number" class="main-form-block-input" placeholder="<spring:message code="form.department-established-year-input" />" min="0" max="32767" <c:if test="${mode == 'MODE_UPDATE'}">value="${departmentForm.establishedYear}"</c:if>>
            </div>
            <div class="main-form-block">
                <label for="numberOfProfessors" class="main-form-block-label"><spring:message code="form.department-number-of-professors-label" /></label>
                <input name="numberOfProfessors" id="numberOfProfessors" required type="number" class="main-form-block-input" placeholder="<spring:message code="form.department-number-of-professors-input" />" min="0" max="32767" <c:if test="${mode == 'MODE_UPDATE'}">value="${departmentForm.numberOfProfessors}"</c:if>>
            </div>
            <div class="main-form-block">
                <label for="numberOfAssistants" class="main-form-block-label"><spring:message code="form.department-number-of-assistants-label" /></label>
                <input name="numberOfAssistants" id="numberOfAssistants" required type="number" class="main-form-block-input" placeholder="<spring:message code="form.department-number-of-assistants-input" />" min="0" max="32767" <c:if test="${mode == 'MODE_UPDATE'}">value="${departmentForm.numberOfAssistants}"</c:if>>
            </div>
            <div class="main-form-block">
                <label for="description" style="align-self: start;" class="main-form-block-label"><spring:message code="form.faculty-description-label" /></label>
                <textarea name="description" id="description" required class="main-form-block-input" style="height: 200px;" placeholder="<spring:message code="form.faculty-description-input" />"><c:if test="${mode == 'MODE_UPDATE'}">${departmentForm.description}</c:if></textarea>
            </div>
            <div class="main-form-block">
                <label for="facultyId" class="main-form-block-label"><spring:message code="form.faculty-label"/></label>
                <select class="main-form-block-input select" required id="facultyId" name="facultyId">
                    <c:forEach var="faculty" items="${faculties}">
                        <option <c:if test="${mode == 'MODE_UPDATE' && departmentForm.faculty.id == faculty.id}">selected</c:if> value="${faculty.id}">${faculty.university.shortName}/${faculty.shortName}</option>
                    </c:forEach>
                </select>
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