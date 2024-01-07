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
    <link rel="stylesheet" href="../styles/facultyForm.css">
    <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
</head>

<body class="body">
<div class="container">
    <header class="header">
        <div class="header-logo-block">
            <img src="../images/knu-logo.png" alt="knu logo" class="header-logo">
            <h1 class="header-heading">Administrator</h1>
        </div>
        <ul class="header-list">
            <li class="header-list-item"><a href="/home" class="header-list-item-link" style="margin-right: 30px;">See new applications</a></li>
            <li class="header-list-item"><a href="/adminFaculties" class="header-list-item-link" style="margin-right: 30px;">List of faculties</a></li>
            <li class="header-list-item"><a href="/addFaculty" class="header-list-item-link" style="text-decoration: underline;">Add new faculty</a></li>
        </ul>
    </header>
    <main class="main">
        <h2 class="main-heading">Faculty</h2>
        <form:form action="update" class="main-form" method="post" modelAttribute="facultyForm">
            <div class="main-form-block">
                <label for="name" class="main-form-block-label">Name:</label>
                <input name="name" id="name" type="text" class="main-form-block-input" placeholder="Enter the name of faculty" value="${facultyForm.name}">
            </div>
            <div class="main-form-block">
                <label for="planNumber" class="main-form-block-label">Plan number:</label>
                <input name="planNumber" id="planNumber" type="number" class="main-form-block-input" placeholder="Enter the plan number" value="${facultyForm.planNumber}">
            </div>
            <input class="main-form-submit-block-button" style="width: 145px;" type="submit" value="Update faculty">
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