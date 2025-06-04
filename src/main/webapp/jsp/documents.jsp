<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="uk">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/documents.css">
    <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/i18n.js"></script>
</head>

<body class="body">
<div class="container" <c:if test="${role=='ROLE_USER'}">style="display: flex; flex-flow: column nowrap; align-items: center; justify-content: space-between;"</c:if>>

    <c:choose>
        <c:when test="${role == 'ROLE_USER'}">
            <header class="header">
                <div class="header-logo-block">
                    <img src="${pageContext.request.contextPath}/images/avatars/${user.imageUrl}" alt="user-avatar" class="header-logo">
                    <h1 class="header-heading"><spring:message code="user.header-heading"/></h1>
                </div>
                <ul class="header-list">
                    <li class="header-list-item"><a href="/home" class="header-list-item-link"><spring:message code="user.anchor1"/></a></li>
                    <li class="header-list-item"><a href="/universities" class="header-list-item-link"><spring:message code="user.anchor2"/></a></li>
                    <li class="header-list-item"><a href="/makeApplication" class="header-list-item-link"><spring:message code="user.anchor3"/></a></li>
                    <li class="header-list-item" style="text-decoration: underline;"><a href="/documents" class="header-list-item-link"><spring:message code="user.anchor4"/></a></li>
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
        </c:when>
        <c:when test="${role == 'ROLE_ADMIN'}">
            <header class="header">
                <div class="header-logo-block admin-header-logo-block">
                    <img src="${pageContext.request.contextPath}/images/avatars/${user.imageUrl}" alt="user-avatar" class="header-logo">
                    <h1 class="header-heading"><spring:message code="admin.header-heading"/></h1>
                </div>
                <ul class="header-list">
                    <li class="header-list-item"><a href="/home" class="header-list-item-link" style="text-decoration: underline;"><spring:message code="admin.anchor1"/></a></li>
                    <li class="header-list-item"><a href="/universities" class="header-list-item-link"><spring:message code="admin.anchor2"/></a></li>
                    <li class="header-list-item"><a href="/addMenu" class="header-list-item-link"><spring:message code="admin.anchor3"/></a></li>
                    <li class="header-list-item"><a href="/documents" class="header-list-item-link"><spring:message code="user.anchor4"/></a></li>
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
        </c:when>
    </c:choose>

    <c:choose>
        <c:when test="${role == 'ROLE_USER'}">
            <c:choose>
                <c:when test="${documentsMode == 'UPLOAD'}">
                    <main class="upload-main">
                        <form:form action="documents" class="main-form" method="post" enctype="multipart/form-data">
                            <div class="main-form-block">
                                <label for="passport" class="main-form-block-label"><spring:message code="form.passport"/></label>
                                <input name="passport" id="passport" class="main-form-block-file-input" type="file" required>
                            </div>
                            <div class="main-form-block">
                                <label for="identificationCode" class="main-form-block-label"><spring:message code="form.identification-code"/></label>
                                <input name="identificationCode" id="identificationCode" class="main-form-block-file-input" type="file" required>
                            </div>
                            <div class="main-form-block">
                                <label for="address" class="main-form-block-label"><spring:message code="form.address"/></label>
                                <input name="address" id="address" class="main-form-block-file-input" type="file" required>
                            </div>
                            <div class="main-form-submit-block">
                                <input class="main-form-submit-block-button" type="submit" value="<spring:message code="addFaculty.button"/>">
                            </div>
                        </form:form>
                    </main>
                </c:when>
                <c:when test="${documentsMode == 'UPLOADED'}">
                    <main class="uploaded-main">
                        <c:forEach var="document" items="${documents}">
                            <div class="info-block">
                                <p class="info-block-text"><spring:message code="document.type" /> ${document.documentType}</p>
                                <p class="info-block-text"><spring:message code="application.status" /> ${document.status}</p>
                                <p class="info-block-text"><a class="website-link" href="${pageContext.request.contextPath}/documents/${user.email}/${document.path}">${document.path}</a></p>
                            </div>
                        </c:forEach>
                    </main>
                </c:when>
            </c:choose>
        </c:when>
        <c:when test="${role == 'ROLE_ADMIN'}">
            <main class="main">
                <c:forEach var="document" items="${documents}">
                    <div class="info-block" style="height: 350px;">
                        <h1 class="info-block-header">${document.applicant.surname} ${document.applicant.name} ${document.applicant.email}</h1>
                        <p class="info-block-text"><spring:message code="document.type" /> ${document.documentType}</p>
                        <p class="info-block-text"><a class="website-link" href="${pageContext.request.contextPath}/documents/${document.applicant.email}/${document.path}">${document.path}</a></p>
                        <div class="info-block-buttons">
                            <a href="/acceptDocument?id=${document.id}" style="text-decoration: none;">
                                <button class="edit-button"><spring:message code="infoBlock.accept-button" /></button>
                            </a>
                            <a href="/declineDocument?id=${document.id}" style="text-decoration: none;">
                                <button class="delete-button"><spring:message code="infoBlock.decline-button" /></button>
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </main>
        </c:when>
    </c:choose>
    <jsp:include page="footer.jsp"></jsp:include>
</div>
</body>

</html>