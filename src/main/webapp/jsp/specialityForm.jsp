<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="uk">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Speciality Form</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/formPage.css">
    <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/i18n.js"></script>
    <script>
        const allFaculties = ${facultiesJson};
        const allDepartments = ${departmentsJson};
    </script>
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
        <h2 class="main-heading"><spring:message code="specialityForm.heading" /></h2>
        <form:form modelAttribute="specialityForm" class="main-form" method="post">
            <div class="main-form-block" <c:if test="${msg == null}">style="margin-top: 0;"</c:if>>
                <span class="main-form-block-label" style="color: red;">${msg}</span>
            </div>
            <div class="main-form-block">
                <label for="name" class="main-form-block-label"><spring:message code="form.speciality-name-label" /></label>
                <input name="name" id="name" type="text" required class="main-form-block-input" placeholder="<spring:message code="form.speciality-name-input" />" <c:if test="${mode == 'MODE_UPDATE'}">value="${specialityForm.name}"</c:if>>
            </div>
            <div class="main-form-block">
                <label for="shortName" class="main-form-block-label"><spring:message code="form.speciality-short-name-label" /></label>
                <input name="shortName" id="shortName" required type="text" class="main-form-block-input" placeholder="<spring:message code="form.speciality-short-name-input" />" <c:if test="${mode == 'MODE_UPDATE'}">value="${specialityForm.shortName}"</c:if>>
            </div>
            <div class="main-form-block">
                <label for="code" class="main-form-block-label"><spring:message code="form.speciality-code-label" /></label>
                <input name="code" id="code" type="text" required class="main-form-block-input" placeholder="<spring:message code="form.speciality-code-input" />" <c:if test="${mode == 'MODE_UPDATE'}">value="${specialityForm.code}"</c:if>>
            </div>
            <div class="main-form-block">
                <label for="description" style="align-self: start;" class="main-form-block-label"><spring:message code="form.speciality-description-label" /></label>
                <textarea name="description" id="description" required class="main-form-block-input" style="height: 200px;" placeholder="<spring:message code="form.speciality-description-input" />"><c:if test="${mode == 'MODE_UPDATE'}">${specialityForm.description}</c:if></textarea>
            </div>
            <div class="main-form-block">
                <label for="universityId" class="main-form-block-label"><spring:message code="form.university-label"/></label>
                <select class="main-form-block-input select" required id="universityId" name="universityId">
                    <c:forEach var="university" items="${universities}">
                        <option <c:if test="${mode == 'MODE_UPDATE' && specialityForm.department.faculty.university.id == university.id}">selected</c:if> value="${university.id}">${university.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="main-form-block">
                <label for="facultyId" class="main-form-block-label"><spring:message code="form.faculty-label"/></label>
                <select class="main-form-block-input select" required id="facultyId" name="facultyId">
                    <c:forEach var="faculty" items="${faculties}">
                        <option <c:if test="${mode == 'MODE_UPDATE' && specialityForm.department.faculty.id == faculty.id}">selected</c:if> value="${faculty.id}">${faculty.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="main-form-block">
                <label for="departmentId" class="main-form-block-label"><spring:message code="form.department-label"/></label>
                <select class="main-form-block-input select" required id="departmentId" name="departmentId">
                    <c:forEach var="department" items="${departments}">
                        <option <c:if test="${mode == 'MODE_UPDATE' && specialityForm.department.id == department.id}">selected</c:if> value="${department.id}">${department.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="main-form-block">
                <label for="level" class="main-form-block-label"><spring:message code="form.speciality-level-label" /></label>
                <input name="level" id="level" required type="text" class="main-form-block-input" placeholder="<spring:message code="form.speciality-level-input" />" <c:if test="${mode == 'MODE_UPDATE'}">value="${specialityForm.level}"</c:if>>
            </div>
            <div class="main-form-block">
                <label for="duration" class="main-form-block-label"><spring:message code="form.speciality-duration-label" /></label>
                <input name="duration" id="duration" required type="number" class="main-form-block-input" placeholder="<spring:message code="form.speciality-duration-input" />" min="0" max="32767" <c:if test="${mode == 'MODE_UPDATE'}">value="${specialityForm.duration}"</c:if>>
            </div>
            <div class="main-form-block">
                <label for="planNumber" class="main-form-block-label"><spring:message code="form.speciality-planNumber-label" /></label>
                <input name="planNumber" id="planNumber" required type="number" class="main-form-block-input" placeholder="<spring:message code="form.speciality-planNumber-input" />" min="0" max="32767" <c:if test="${mode == 'MODE_UPDATE'}">value="${specialityForm.planNumber}"</c:if>>
            </div>
            <div class="main-form-block">
                <label for="budgetPlaces" class="main-form-block-label"><spring:message code="form.speciality-budgetPlaces-label" /></label>
                <input name="budgetPlaces" id="budgetPlaces" required type="number" class="main-form-block-input" placeholder="<spring:message code="form.speciality-budgetPlaces-input" />" min="0" max="32767" <c:if test="${mode == 'MODE_UPDATE'}">value="${specialityForm.budgetPlaces}"</c:if>>
            </div>
            <div class="main-form-block">
                <label for="accreditation" class="main-form-block-label"><spring:message code="form.speciality-accreditation-label" /></label>
                <input name="accreditation" id="accreditation" required type="text" class="main-form-block-input" placeholder="<spring:message code="form.speciality-accreditation-input" />" <c:if test="${mode == 'MODE_UPDATE'}">value="${specialityForm.accreditation}"</c:if>>
            </div>
            <div class="main-form-block">
                <label for="curriculum" class="main-form-block-label"><spring:message code="form.speciality-curriculum-label" /></label>
                <textarea name="curriculum" id="curriculum" style="height: 200px;" required class="main-form-block-input" placeholder="<spring:message code="form.speciality-curriculum-input" />"><c:if test="${mode == 'MODE_UPDATE'}">${specialityForm.curriculum}</c:if></textarea>
            </div>
            <div class="main-form-block">
                <label for="employmentProspects" class="main-form-block-label"><spring:message code="form.speciality-employmentProspects-label" /></label>
                <textarea name="employmentProspects" id="employmentProspects" style="height: 200px;" required class="main-form-block-input" placeholder="<spring:message code="form.speciality-employmentProspects-input" />"><c:if test="${mode == 'MODE_UPDATE'}">${specialityForm.employmentProspects}</c:if></textarea>
            </div>
            <div class="main-form-block">
                <label for="pricePerYear" class="main-form-block-label"><spring:message code="form.speciality-pricePerYear-label" /></label>
                <input name="pricePerYear" id="pricePerYear" required type="number" class="main-form-block-input" placeholder="<spring:message code="form.speciality-pricePerYear-input" />" min="0" max="2147483647" <c:if test="${mode == 'MODE_UPDATE'}">value="${specialityForm.pricePerYear}"</c:if>>
            </div>
            <div class="main-form-block">
                <label for="mathsCoef" class="main-form-block-label"><spring:message code="form.speciality-mathsCoef-label" /></label>
                <input name="mathsCoef" id="mathsCoef" step="0.1" required type="number" class="main-form-block-input" placeholder="<spring:message code="form.speciality-mathsCoef-input" />" min="0" max="1" <c:if test="${mode == 'MODE_UPDATE'}">value="${specialityForm.mathsCoef}"</c:if>>
            </div>
            <div class="main-form-block">
                <label for="ukrainianCoef" class="main-form-block-label"><spring:message code="form.speciality-ukrainianCoef-label" /></label>
                <input name="ukrainianCoef" id="ukrainianCoef" step="0.1" required type="number" class="main-form-block-input" placeholder="<spring:message code="form.speciality-ukrainianCoef-input" />" min="0" max="1" <c:if test="${mode == 'MODE_UPDATE'}">value="${specialityForm.ukrainianCoef}"</c:if>>
            </div>
            <div class="main-form-block">
                <label for="englishCoef" class="main-form-block-label"><spring:message code="form.speciality-englishCoef-label" /></label>
                <input name="englishCoef" id="englishCoef" step="0.1" required type="number" class="main-form-block-input" placeholder="<spring:message code="form.speciality-englishCoef-input" />" min="0" max="1" <c:if test="${mode == 'MODE_UPDATE'}">value="${specialityForm.englishCoef}"</c:if>>
            </div>
            <div class="main-form-block">
                <label for="certificateCoef" class="main-form-block-label"><spring:message code="form.speciality-certificateCoef-label" /></label>
                <input name="certificateCoef" id="certificateCoef" step="0.1" required type="number" class="main-form-block-input" placeholder="<spring:message code="form.speciality-certificateCoef-input" />" min="0" max="1" <c:if test="${mode == 'MODE_UPDATE'}">value="${specialityForm.certificateCoef}"</c:if>>
            </div>
            <div class="main-form-submit-block">
                <input class="main-form-submit-block-button" required type="submit" value="<spring:message code="addFaculty.button" />">
            </div>
        </form:form>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const universitySelect = document.getElementById("universityId");
                const facultySelect = document.getElementById("facultyId");
                const departmentSelect = document.getElementById("departmentId");

                function populateSelect(selectElement, items, valueField, textCallback, selectedId) {
                    selectElement.innerHTML = "";
                    for (const item of items) {
                        const option = document.createElement("option");
                        option.value = item[valueField];
                        option.textContent = typeof textCallback === "function" ? textCallback(item) : item[textCallback];
                        if (selectedId && selectedId === item[valueField]) {
                            option.selected = true;
                        }
                        selectElement.appendChild(option);
                    }
                }

                universitySelect.addEventListener("change", function () {
                    const selectedUniversityId = parseInt(this.value);
                    const faculties = allFaculties.filter(f => f.university.id === selectedUniversityId);
                    populateSelect(facultySelect, faculties, "id", "name");

                    facultySelect.dispatchEvent(new Event("change"));
                });

                facultySelect.addEventListener("change", function () {
                    const selectedFacultyId = parseInt(this.value);
                    const departments = allDepartments.filter(d => d.faculty.id === selectedFacultyId);
                    populateSelect(departmentSelect, departments, "id", "name");

                    departmentSelect.dispatchEvent(new Event("change"));
                });

                // Автоматично ініціалізуємо селекти, якщо вже щось обрано
                if (universitySelect.value) {
                    universitySelect.dispatchEvent(new Event("change"));
                }
            });
        </script>
    </main>
    <jsp:include page="footer.jsp"></jsp:include>
</div>
</body>

</html>