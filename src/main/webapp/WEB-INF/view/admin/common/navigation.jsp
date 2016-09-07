<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'ua_UA'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.lang"/>
    <!-- Navigation -->
    <header>
        <!-- Top Dropdowns -->
        <ul id="languageDropdown" class="dropdown-content grey darken-3">
            <li><a href="?language=ua_UA" class="white-text">УКРАЇНСЬКА</a></li>
            <li><a href="?language=en_EN" class="white-text">ENGLISH</a></li>
        </ul>

        <ul id="userDropdown" class="dropdown-content grey darken-3">
            <li><a href="" class="white-text">EDIT ACCOUNT<i class="material-icons right green-text">mode_edit</i></a></li>
            <li><a href="logout" class="white-text">LOG OUT<i class="material-icons right red-text">power_settings_new</i></a></li>
        </ul>

        <!-- Sidebar Dropdowns -->
        <ul id="languageSideDropdown" class="dropdown-content sidebar grey darken-3">
            <li><a href="?language=ua_UA" class="white-text">УКРАЇНСЬКА<i class="material-icons right green-text">language</i></a></li>
            <li><a href="?language=en_EN" class="white-text">ENGLISH<i class="material-icons right green-text">language</i></a></li>
        </ul>

        <ul id="userSideDropdown" class="dropdown-content sidebar grey darken-3">
            <li><a href="" class="white-text">EDIT ACCOUNT<i class="material-icons right green-text">mode_edit</i></a></li>
            <li><a href="logout" class="white-text">LOG OUT<i class="material-icons right red-text">power_settings_new</i></a></li>
        </ul>
        <nav>
            <div class="nav-wrapper grey darken-3">
                <a href="#" data-activates="sidebar-nav" class="button-collapse"><i class="material-icons">menu</i></a>
                <ul class="right hide-on-med-and-down">

                    <li><a class="dropdown-button" data-activates="userDropdown">Hello, ${name}<i class="material-icons right orange-text" style="padding-left:2em;">perm_identity</i></a></li>
                    <li><a href="home" class="white-text">HOME<i class="material-icons right red-text text-accent-3">home</i></a></li>
                    <li><a class="dropdown-button" data-activates="languageDropdown">LANGUAGE<i class="material-icons right green-text">language</i></a></li>
                </ul>
                <ul class="side-nav fixed grey darken-4" id="sidebar-nav">
                    <li>
                        <a href="" class="center-align logo"><img src="static/img/logo.png" alt="PayKick Logo" width="50" height="44"></a>
                    </li>
<<<<<<< HEAD
                    <li><a href="">MANAGE MATCHES<i class="material-icons right red-text text-accent-3">list</i></a></li>
                    <li><a href="">MANAGE TEAMS<i class="material-icons right orange-text">supervisor_account</i></a></li>
                    <li><a href="">MANAGE USERS<i class="material-icons right blue-text">person_pin</i></a></li>
                    <li><a href="">STATISTICS<i class="material-icons right green-text text-accent-4">equalizer</i></a></li>
=======
                    <li><a href="matches">MANAGE MATCHES<i class="material-icons right red-text text-accent-3">list</i></a></li>
                    <li><a href="teams">MANAGE TEAMS<i class="material-icons right orange-text">supervisor_account</i></a></li>
                    <li><a href="users">MANAGE USERS<i class="material-icons right blue-text">person_pin</i></a></li>
                    <li><a href="statistics">STATISTICS<i class="material-icons right green-text text-accent-4">trending_up</i></a></li>
>>>>>>> 7f0e615841aa5aa3b8ca019106930cb2f5e09871
                    <div class="hide-on-large-only">
                        <hr>
                        <li><a class="dropdown-button" data-activates="userSideDropdown">${name}<i class="material-icons right orange-text">perm_identity</i></a></li>
                        <li><a href="home" class="white-text">HOME<i class="material-icons right red-text text-accent-3">home</i></a></li>
                        <li><a class="dropdown-button" data-activates="languageSideDropdown">LANGUAGE<i class="material-icons right green-text">language</i></a></li>
                    </div>
                </ul>
            </div>
        </nav>
    </header>