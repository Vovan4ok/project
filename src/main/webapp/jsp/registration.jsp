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
    <link rel="stylesheet" href="../styles/registration.css">
    <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
</head>

<body class="body">
<div class="container">
    <header class="header">
        <div class="header-logo-block">
            <img src="../images/knu-logo.png" alt="knu logo" class="header-logo">
            <h1 class="header-heading">Admission to KNU</h1>
        </div>
        <ul class="header-list">
            <li class="header-list-item" style="margin-right: 75px;"><a href="#" class="header-list-item-link">All about admission</a></li>
            <li class="header-list-item"><a href="#" class="header-list-item-link">Faculties ratings</a></li>
        </ul>
    </header>
    <main class="main">
        <h2 class="main-heading">Sign up!</h2>
        <form:form action="registration" class="main-form" method="post" modelAttribute="userForm">
            <div class="main-form-block">
                <label for="name" class="main-form-block-label">Name:</label>
                <input name="name" type="text" id="name" class="main-form-block-input" placeholder="Enter your name">
            </div>
            <div class="main-form-block">
                <label for="surname" class="main-form-block-label">Surname:</label>
                <input name="surname" type="text" id="surname" class="main-form-block-input" placeholder="Enter your surname">
            </div>
            <div class="main-form-block">
                <span class="main-form-block-label">${msg}</span>
                <label for="email" class="main-form-block-label">Email:</label>
                <input name="email" type="email" id="email" class="main-form-block-input" placeholder="Enter your email">
            </div>
            <div class="main-form-block">
                <label for="password" class="main-form-block-label">Password:</label>
                <input name="password" type="password" id="password" class="main-form-block-input"
                       placeholder="Enter your password">
            </div>
            <div class="main-form-submit-block">
                <input class="main-form-submit-block-button" type="submit" value="Sign up"/>
                <span class="main-form-submit-block-span">
                        Have an account? <a href="/login" class="main-form-submit-block-span-link">Log In</a>
                </span>
            </div>
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