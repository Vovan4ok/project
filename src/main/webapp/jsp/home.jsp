<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main</title>
    <link rel="stylesheet" href="../styles/home.css">
    <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
</head>

<body class="body">
<div class="container">
    <header class="header">
        <c:choose>
            <c:when test="${role == 'DEFAULT'}">
                <div class="header-logo-block">
                    <img src="data:image/jpg;base64, ${user.encodedImage}" alt="knu logo" class="header-logo">
                    <h1 class="header-heading">Admission to KNU</h1>
                </div>
                <ul class="header-list">
                    <li class="header-list-item" style="margin-right: 30px; text-decoration: underline;"><a href="/home" class="header-list-item-link">Main</a></li>
                    <li class="header-list-item" style="margin-right: 30px;"><a href="/userFaculties" class="header-list-item-link">See info about faculties</a></li>
                    <li class="header-list-item"><a href="/application" class="header-list-item-link">Apply for admission</a></li>
                </ul>
            </c:when>
            <c:when test="${role == 'ADMIN'}">
                <div class="header-logo-block admin-header-logo-block">
                    <img src="../images/knu-logo.png" alt="knu logo" class="header-logo">
                    <h1 class="header-heading">Administration</h1>
                </div>
                <ul class="header-list">
                    <li class="header-list-item"><a href="/home" class="header-list-item-link" style="text-decoration: underline; margin-right: 30px;">See new applications</a></li>
                    <li class="header-list-item"><a href="/adminFaculties" class="header-list-item-link" style="margin-right: 30px;">List of faculties</a></li>
                    <li class="header-list-item"><a href="/addFaculty" class="header-list-item-link">Add new faculty</a></li>
                </ul>
            </c:when>
        </c:choose>

    </header>
    <c:choose>
        <c:when test="${role == 'DEFAULT'}">
        <main class="user-main">
            <img src="../images/knu-photo.jpg" alt="knu-photo" class="user-main-image">
            <div class="user-main-text">
                Taras Shevchenko National University of Kyiv is a state higher education institution in Ukraine, located in the city of Kyiv. According to university rankings, as of the year 2020, it held the first position and is the largest university in terms of the number of students and specialties. From 2009 to 2014, it had the status of an autonomous research university. Since April 30, 2021, the position of the university's rector has been held by Volodymyr Bugrov. On April 21, 1994, the President of Ukraine established that Taras Shevchenko National University of Kyiv is a national university with the status of a self-governing (autonomous) state higher education institution, which operates in accordance with its own Statute.
            </div>
        </main>
        </c:when>
        <c:when test="${role == 'ADMIN'}">
            <main class="main">
                <h1 class="main-heading">FIT Faculty</h1>
                <table class="main-table">
                    <thead class="table-thead">
                        <tr class="table-row">
                            <th class="table-name-column table-th">Name</th>
                            <th class="table-surname-column table-th">Surname</th>
                            <th class="table-maths-column table-th">Maths</th>
                            <th class="table-english-column table-th">English</th>
                            <th class="table-physics-column table-th">Physics</th>
                            <th class="table-certificate-column table-th">Certificate</th>
                            <th class="table-rating-column table-th">Rating</th>
                            <th class="table-faculty-column table-th">Faculty Name</th>
                            <th class="table-button-column table-th"></th>
                        </tr>
                    </thead>
                    <tbody class="table-tbody">
                        <c:forEach var="application" items="${applications}">
                            <tr class="table-row">
                                <td class="table-name-column table-td">${application.applicantName}</td>
                                <td class="table-surname-column table-td">${application.applicantSurname}</td>
                                <td class="table-maths-column table-td">${application.mathsMark}</td>
                                <td class="table-english-column table-td">${application.englishMark}</td>
                                <td class="table-physics-column table-td">${application.physicsMark}</td>
                                <td class="table-certificate-column table-td">${application.certificateMark}</td>
                                <td class="table-rating-column table-td">${application.ratingMark}</td>
                                <td class="table-faculty-column table-td">${application.facultyName}</td>
                                <td class="table-button-column table-td">
                                    <a href="acceptApplication?id=${application.applicationId}" class="table-button-column-button">Accept</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </main>
        </c:when>
    </c:choose>
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