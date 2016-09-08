<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <!DOCTYPE html>

    <html>

    <head>

        <!-- Meta -->
        <meta charset="utf-8">

        <!-- Mobile Metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin - Home</title>


        <!-- Favicon -->
        <link rel="shortcut icon" href="static/img/favicon.ico">

        <!-- Fonts -->
        <link href='http://fonts.googleapis.com/css?family=Raleway:100,200,300,400,500,600,700,800,900' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700' rel='stylesheet' type='text/css'>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

        <!-- Styles -->
        <link href="static/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
        <link href="static/css/style.css" rel="stylesheet">
    </head>

    <body>

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

                        <li><a class="dropdown-button" data-activates="userDropdown">Hello, Ars<i class="material-icons right orange-text" style="padding-left:2em;">perm_identity</i></a></li>
                        <li><a href="home" class="white-text">HOME<i class="material-icons right red-text text-accent-3">home</i></a></li>
                        <li><a class="dropdown-button" data-activates="languageDropdown">LANGUAGE<i class="material-icons right green-text">language</i></a></li>
                    </ul>
                    <ul class="side-nav fixed grey darken-4" id="sidebar-nav">
                        <li>
                            <a href="" class="center-align logo"><img src="static/img/logo.png" alt="PayKick Logo" width="50" height="44"></a>
                        </li>

                        <li><a href="matches">MANAGE MATCHES<i class="material-icons right red-text text-accent-3">list</i></a></li>
                        <li><a href="teams">MANAGE TEAMS<i class="material-icons right orange-text">group</i></a></li>
                        <li><a href="users">MANAGE USERS<i class="material-icons right blue-text">person_pin</i></a></li>
                        <li><a href="statistics">STATISTICS<i class="material-icons right green-text text-accent-4">trending_up</i></a></li>

                        <div class="hide-on-large-only">
                            <hr>
                            <li><a class="dropdown-button" data-activates="userSideDropdown">Ars<i class="material-icons right orange-text">perm_identity</i></a></li>
                            <li><a href="home" class="white-text">HOME<i class="material-icons right red-text text-accent-3">home</i></a></li>
                            <li><a class="dropdown-button" data-activates="languageSideDropdown">LANGUAGE<i class="material-icons right green-text">language</i></a></li>
                        </div>
                    </ul>
                </div>
            </nav>
        </header>

        <!-- Main Content -->
        <main class="valign-wrapper grey lighten-3">
            <div class="container valign" style="margin-top: 20px;">
                <h4 class="center-align">Edit Account:</h4>
                <br>
                <div class="row">
                    <form id="new-player-form" class="col s12 m8 offset-m2 l6 offset-l3">
                        <div class="row">
                            <div class="input-field col s12">
                                <!-- Replace value -->
                                <input id="fname" type="text" value="Ars">
                                <label for="fname">First Name</label>
                            </div>
                            <div class="input-field col s12">
                                <!-- Replace value -->
                                <input id="surname" type="text" value="Nikinak">
                                <label for="surname">Surname</label>
                            </div>
                            <div class="col s12">
                                <p class="big">Age:</p>
                                <p class="range-field">
                                    <input type="range" id="age" min="0" max="100" value="25" />
                                </p>
                            </div>
                            <div class="col s12">
                                <p class="big">Sex:</p>
                                <p>
                                    <input name="gender" type="radio" id="male" value="male" required checked/>
                                    <label for="male">Male</label>
                                </p>
                                <p>
                                    <input name="gender" type="radio" id="female" value="female" />
                                    <label for="female">Female</label>
                                </p>
                            </div>
                            <div class="input-field col s12">
                                <!-- Replace value -->
                                <input id="password" type="password" value="arsenush">
                                <label for="password">Password</label>
                            </div>
                            <div class="input-field col s12">
                                <!-- Replace value -->
                                <input id="repeat-password" type="password" value="arsenush">
                                <label for="repeat-password">Repeat Password</label>
                            </div>
                        </div>
                        <div class="col s12 center-align">
                            <button class="btn waves-effect waves-light green" type="submit" name="action">Save
                                <i class="material-icons right">done</i>
                            </button>
                            <br>
                            <br>
                            <span class="center-align green-text" id="messageRegistration">Saved</span>
                        </div>
                    </form>
                </div>
            </div>
        </main>


        <!-- Footer -->
        <footer class="page-footer">
            <div class="footer-copyright grey darken-3">
                <div class="center-align">
                    <a href=""><span class="orange-text"><strong>PayKick</strong></span></a> © 2016 All rights Reserved
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
        <script src="static/js/jquery-3.1.0.min.js"></script>
        <script src="static/js/materialize.min.js"></script>
        <script src="static/js/init.js"></script>
        <script src="static/js/login.js"></script>

    </body>

    </html>