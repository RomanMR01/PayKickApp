<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <!-- Navigation -->
    <header>
        <!-- Top Dropdowns -->
        <ul id="languageDropdown" class="dropdown-content grey darken-3">
            <li><a href="?language=ua_UA" class="white-text">УКРАЇНСЬКА</a></li>
            <li><a href="?language=en_EN" class="white-text">ENGLISH</a></li>
        </ul>

        <ul id="userDropdown" class="dropdown-content grey darken-3">
            <li><a href="" class="white-text">EDIT ACCOUNT<i class="material-icons right green-text">mode_edit</i></a></li>
            <li><a href="" class="white-text">LOG OUT<i class="material-icons right red-text">power_settings_new</i></a></li>
        </ul>

        <!-- Sidebar Dropdowns -->
        <ul id="languageSideDropdown" class="dropdown-content sidebar grey darken-3">
            <li><a href="?language=ua_UA" class="white-text">УКРАЇНСЬКА<i class="material-icons right green-text">language</i></a></li>
            <li><a href="?language=en_EN" class="white-text">ENGLISH<i class="material-icons right green-text">language</i></a></li>
        </ul>

        <ul id="userSideDropdown" class="dropdown-content sidebar grey darken-3">
            <li><a href="" class="white-text">EDIT ACCOUNT<i class="material-icons right green-text">mode_edit</i></a></li>
            <li><a href="" class="white-text">LOG OUT<i class="material-icons right red-text">power_settings_new</i></a></li>
        </ul>
        <nav>
            <div class="nav-wrapper grey darken-3">
                <a href="#" data-activates="sidebar-nav" class="button-collapse"><i class="material-icons">menu</i></a>
                <ul class="right hide-on-med-and-down">

                    <li><a class="dropdown-button" data-activates="userDropdown">Hello, Arsen Nikitenko<i class="material-icons right orange-text">perm_identity</i></a></li>
                    <li><a href="" class="white-text">HOME<i class="material-icons right red-text text-accent-3">store</i></a></li>
                    <li><a class="dropdown-button" data-activates="languageDropdown">LANGUAGE<i class="material-icons right green-text">language</i></a></li>
                </ul>
                <ul class="side-nav fixed grey darken-4" id="sidebar-nav">
                    <li>
                        <a href="" class="center-align logo"><img src="static/img/logo.png" alt="PayKick Logo" width="60" height="54"></a>
                    </li>
                    <li><a href="">MANAGE MATCHES<i class="material-icons right red-text text-accent-3">list</i></a></li>
                    <li><a href="">MANAGE TEAMS<i class="material-icons right orange-text">supervisor_account</i></a></li>
                    <li><a href="">MANAGE USERS<i class="material-icons right blue-text">person_pin</i></a></li>
                    <li><a href="">STATISTICS<i class="material-icons right green-text text-accent-4">trending_up</i></a></li>
                    <div class="hide-on-large-only">
                        <hr>
                        <li><a class="dropdown-button" data-activates="userSideDropdown">Arsen Nikitenko<i class="material-icons right orange-text">perm_identity</i></a></li>
                        <li><a href="" class="white-text">HOME<i class="material-icons right red-text text-accent-3">store</i></a></li>
                        <li><a class="dropdown-button" data-activates="languageSideDropdown">LANGUAGE<i class="material-icons right green-text">language</i></a></li>
                    </div>
                </ul>
            </div>
        </nav>
    </header>