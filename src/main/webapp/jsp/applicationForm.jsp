<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="uk">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Application</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/formPage.css">
    <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/i18n.js"></script>
    <script>
        const allFaculties = ${facultiesJson};
        const allDepartments = ${departmentsJson};
        const allSpecialities = ${specialitiesJson};
    </script>
</head>

<body class="body">
<div class="container">
    <header class="header">
        <div class="header-logo-block">
            <img src="${pageContext.request.contextPath}/images/avatars/${user.imageUrl}" alt="user-avatar" class="header-logo">
            <h1 class="header-heading"><spring:message code="user.header-heading"/></h1>
        </div>
        <ul class="header-list">
            <li class="header-list-item" style="margin-right: 30px;"><a href="/home" class="header-list-item-link"><spring:message code="user.anchor1"/></a></li>
            <li class="header-list-item" style="margin-right: 30px;"><a href="/universities" class="header-list-item-link"><spring:message code="user.anchor2"/></a></li>
            <li class="header-list-item" style="text-decoration: underline;"><a href="/makeApplication" class="header-list-item-link"><spring:message code="user.anchor3"/></a></li>
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
        <h2 class="main-heading"><spring:message code="application.heading"/></h2>
        <form:form class="main-form" method="post">
            <div class="main-form-block" <c:if test="${msg == null}">style="margin-top: 0;"</c:if>>
                <span class="main-form-block-label" style="color: red;">${msg}</span>
            </div>
            <div class="main-form-block">
                <label for="mathsMark" class="main-form-block-label"><spring:message code="form.maths-label"/></label>
                <input name="mathsMark" value="${marks.mathsMark}" required id="mathsMark" type="number" class="main-form-block-input" placeholder="<spring:message code="form.maths-input"/>" min="100" max="200">
            </div>
            <div class="main-form-block">
                <label for="englishMark" class="main-form-block-label"><spring:message code="form.english-label"/></label>
                <input name="englishMark" value="${marks.englishMark}" required id="englishMark" type="number" class="main-form-block-input" placeholder="<spring:message code="form.english-input"/>" min="100" max="200">
            </div>
            <div class="main-form-block">
                <label for="ukrainianMark" class="main-form-block-label"><spring:message code="form.ukrainian-label"/></label>
                <input name="ukrainianMark" value="${marks.ukrainianMark}" required id="ukrainianMark" type="number" class="main-form-block-input" placeholder="<spring:message code="form.ukrainian-input"/>" min="100" max="200">
            </div>
            <div class="main-form-block">
                <label for="certificateMark" class="main-form-block-label"><spring:message code="form.certificate-label"/></label>
                <input name="certificateMark" value="${marks.certificateMark}" step="0.1" required id="certificateMark" type="number" class="main-form-block-input" placeholder="<spring:message code="form.certificate-input"/>" min="0" max="12">
            </div>
            <div class="main-form-block">
                <label for="priority" class="main-form-block-label"><spring:message code="form.priority-label"/></label>
                <select class="main-form-block-input select" required id="priority" name="priority">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
            </div>
            <div class="main-form-block">
                <label for="universityId" class="main-form-block-label"><spring:message code="form.university-choose-label"/></label>
                <select class="main-form-block-input select" required id="universityId" name="universityId">
                    <c:forEach var="university" items="${universities}">
                        <option <c:if test="${specialityParam != null && specialityParam.department.faculty.university.id == university.id}">selected</c:if> value="${university.id}">${university.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="main-form-block">
                <label for="facultyId" class="main-form-block-label"><spring:message code="form.faculty-choose-label"/></label>
                <select class="main-form-block-input select" required id="facultyId" name="facultyId">
                    <c:forEach var="faculty" items="${faculties}">
                        <option <c:if test="${specialityParam != null && specialityParam.department.faculty.id == faculty.id}">selected</c:if> value="${faculty.id}">${faculty.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="main-form-block">
                <label for="departmentId" class="main-form-block-label"><spring:message code="form.department-choose-label"/></label>
                <select class="main-form-block-input select" required id="departmentId" name="departmentId">
                    <c:forEach var="department" items="${departments}">
                        <option <c:if test="${specialityParam != null && specialityParam.department.id == department.id}">selected</c:if> value="${department.id}">${department.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="main-form-block">
                <label for="specialityId" class="main-form-block-label"><spring:message code="form.speciality-label"/></label>
                <select class="main-form-block-input select" required id="specialityId" name="specialityId">
                    <c:forEach var="speciality" items="${specialities}">
                        <option <c:if test="${specialityParam != null && specialityParam.id == speciality.id}">selected</c:if> value="${speciality.id}">${speciality.code} - ${speciality.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="main-form-submit-block">
                <span class="main-form-submit-block-span">
                    <a href="/specialities" class="main-form-submit-block-span-link"><spring:message code="application.specialities"/> </a>
                </span>
                <input class="main-form-submit-block-button" type="submit" value="<spring:message code="application.button"/>">
                <span class="main-form-submit-block-span">
                    <a href="/applicationHistory" class="main-form-submit-block-span-link"><spring:message code="application.history"/> </a>
                </span>
            </div>
        </form:form>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const universitySelect = document.getElementById("universityId");
                const facultySelect = document.getElementById("facultyId");
                const departmentSelect = document.getElementById("departmentId");
                const specialitySelect = document.getElementById("specialityId");

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

                departmentSelect.addEventListener("change", function () {
                    const selectedDepartmentId = parseInt(this.value);
                    const specialities = allSpecialities.filter(s => s.department.id === selectedDepartmentId);
                    populateSelect(specialitySelect, specialities, "id", "name");
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