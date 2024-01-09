<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List</title>
    <link rel="stylesheet" href="../styles/listOfApplicants.css">
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
            <li class="header-list-item" style="margin-right: 30px;"><a href="#" class="header-list-item-link">Main</a></li>
            <li class="header-list-item" style="margin-right: 30px;"><a href="#" class="header-list-item-link">See info about faculties</a></li>
            <li class="header-list-item"><a href="#" class="header-list-item-link">Apply for admission</a></li>
        </ul>
    </header>
    <main class="main">
        <h1 class="main-heading">${faculty.name} Faculty</h1>
        <table class="main-table">
            <thead class="table-thead">
                <tr class="table-row">
                    <th class="table-th">#</th>
                    <th class="table-th">Name</th>
                    <th class="table-th">Surname</th>
                    <th class="table-th">Maths</th>
                    <th class="table-th">English</th>
                    <th class="table-th">Physics</th>
                    <th class="table-th">Certificate</th>
                    <th class="table-th">Rating</th>
                </tr>
            </thead>
            <tbody class="table-tbody">
                <c:forEach var="application" items="${applications}">
                    <tr class="table-row">
                        <td class="table-td">${application.place}</td>
                        <td class="table-td">${application.applicantName}</td>
                        <td class="table-td">${application.applicantSurname}</td>
                        <td class="table-td">${application.mathsMark}</td>
                        <td class="table-td">${application.englishMark}</td>
                        <td class="table-td">${application.physicsMark}</td>
                        <td class="table-td">${application.certificateMark}</td>
                        <td class="table-td">${application.ratingMark}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
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