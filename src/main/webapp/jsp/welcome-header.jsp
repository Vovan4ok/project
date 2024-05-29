<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<header class="header">
    <div class="header-logo-block">
        <img src="${pageContext.request.contextPath}/images/knu-logo.png" alt="knu logo" class="header-logo">
        <h1 class="header-heading"><spring:message code="enter.header-heading"/></h1>
    </div>
    <select id="locales">
        <option value="en">EN</option>
        <option value="uk">UA</option>
    </select>
</header>