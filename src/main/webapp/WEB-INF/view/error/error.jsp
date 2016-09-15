<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'ua_UA'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.lang"/>
<!DOCTYPE html>

<html>

<head>

    <!-- Meta -->
    <meta charset="utf-8">

    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PayKick. One bet - one hit!</title>

    <!-- Favicon -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/img/favicon.ico">

    <!-- Fonts -->
    <link href='http://fonts.googleapis.com/css?family=Raleway:100,200,300,400,500,600,700,800,900' rel='stylesheet'
          type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700' rel='stylesheet' type='text/css'>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/materialize.css" type="text/css" rel="stylesheet"
          media="screen,projection"/>
    <link href="${pageContext.request.contextPath}/static/css/style-error.css" type="text/css" rel="stylesheet"
          media="screen,projection"/>

</head>

<body>

<!-- Top Dropdowns -->
<ul id="languageDropdown" class="dropdown-content grey darken-3">
    <li><a href="?language=ua_UA" class="white-text">УКРАЇНСЬКА</a></li>
    <li><a href="?language=en_EN" class="white-text">ENGLISH</a></li>
</ul>

<!-- Sidebar Dropdowns -->
<ul id="languageSideDropdown" class="dropdown-content sidebar grey darken-3">
    <li><a href="?language=ua_UA" class="white-text">УКРАЇНСЬКА</a></li>
    <li><a href="?language=en_EN" class="white-text">ENGLISH</a></li>
</ul>

<nav class="grey darken-3">
    <div class="nav-wrapper">
        <a id="logo-container" href="home" class="brand-logo"><img
                src="${pageContext.request.contextPath}/static/img/logo.png"></a>
        <a href="#" data-activates="sidebar-nav" class="button-collapse"><i
                class="material-icons white-text">menu</i></a>
        <ul class="right hide-on-med-and-down">
            <li><a href="${pageContext.request.contextPath}/home" class="white-text">HOME<i
                    class="material-icons right red-text text-accent-3">home</i></a></li>
            <li><a class="dropdown-button white-text" data-activates="languageDropdown">LANGUAGE<i
                    class="material-icons right green-text">language</i></a></li>

        </ul>
        <ul class="side-nav grey darken-3" id="sidebar-nav">
            <li>
                <a href="${pageContext.request.contextPath}/home" class="center-align logo"><img
                        src="${pageContext.request.contextPath}/static/img/logo.png" alt="PayKick Logo" width="50"
                        height="44"></a>
            </li>

            <div class="hide-on-large-only">
                <li><a href="${pageContext.request.contextPath}/home" class="white-text">HOME<i
                        class="material-icons right red-text text-accent-3">home</i></a></li>
                <li><a class="dropdown-button white-text" data-activates="languageSideDropdown">LANGUAGE<i
                        class="material-icons right green-text">language</i></a></li>
            </div>
        </ul>
    </div>
</nav>

<main class="grey lighten-3">
    <div class="main-wrapper">
        <h1 class="center-align red-text" style="margin-bottom: 10px;">${pageContext.errorData.statusCode}</h1>
        <div class="row"><h3 class="center text-black">Error</h3></div>
        <div class="row"><h3 class="center text-black"><a href="${pageContext.request.contextPath}/home" class="black-text">Return to home page, please!</a></h3></div>
        <div class="grey lighten-3">
            <br>
            <br>
            <div class="container">


                <div class="row"><c:forEach var="trace"
                                            items="${pageContext.exception.stackTrace}">
                    <p>${trace}</p>
                </c:forEach></div>
            </div>
            <br>
        </div>
    </div>
    </div>
</main>


<!-- Footer -->
<footer class="page-footer grey darken-1">
    <div id="contacts" class="container">
        <div class="row">
            <div class="col s10 offset-s1 m6">
                <h6 class="white-text">Our Contacts:</h6>
                <ul>
                    <li><a class="grey-text text-lighten-3 valign-wrapper" href="mailto:paykick.team@gmail.com"><i
                            class="material-icons orange-text valign">email</i>&nbsp; paykick.team@gmail.com</a>
                    </li>
                    <li><a class="grey-text text-lighten-3 valign-wrapper" href=""><i
                            class="material-icons red-text valign">phone</i>&nbsp;+38 (063) 583-80-88</a></li>
                    <li><a class="grey-text text-lighten-3 valign-wrapper" href=""><i
                            class="material-icons green-text valign">location_on</i>&nbsp; Ukraine, Lviv.
                        Kozlaniuka str. 7/5</a></li>
                </ul>
            </div>
            <div class="col s10 offset-s1 m4 offset-m1">
                <h6 class="white-text ">Our Rules:</h6>
                <ul>
                    <li><a class="white-text valign-wrapper modal-trigger" href="#terms-modal" href=""
                           target="_blank"><i class="material-icons valign green-text">assignment</i>&nbsp;
                        Terms of Use</a></li>
                    <li><a class="white-text valign-wrapper modal-trigger" href="#policy-modal" target="_blank"><i
                            class="material-icons valign orange-text">https</i>&nbsp; Privacy Policy</a></li>
                </ul>
            </div>
        </div>
    </div>

    <div class="footer-copyright grey darken-3">
        <div class="center-align">
            <a href="${pageContext.request.contextPath}/home"><span class="orange-text"><strong>PayKick</strong></span></a>
            © 2016 All rights Reserved
            <div class="right lang">
                <a href="?language=ua_UA">
                    <div id="ua"></div>
                </a>
                <a href="?language=en_EN">
                    <div id="gb"></div>
                </a>
            </div>
        </div>
    </div>
</footer>


<!-- Scripts -->
<script src="${pageContext.request.contextPath}/static/js/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/materialize.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/init.js"></script>


</body>

</html>