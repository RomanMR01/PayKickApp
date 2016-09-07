<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'ua_UA'}" scope="session" />
            <fmt:setLocale value="${language}" />
            <fmt:setBundle basename="i18n.lang" />
            <!DOCTYPE html>

            <html>

            <head>

                <!-- Meta -->
                <meta charset="utf-8">

                <!-- Mobile Metas -->
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>PayKick. Restoring Password!</title>

                <!-- Favicon -->
                <link rel="shortcut icon" href="static/img/favicon.ico">

                <!-- Fonts -->
                <link href='http://fonts.googleapis.com/css?family=Raleway:100,200,300,400,500,600,700,800,900' rel='stylesheet' type='text/css'>
                <link href='http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700' rel='stylesheet' type='text/css'>
                <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

                <!-- Styles -->
                <link href="static/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
                <link href="static/css/style.css" rel="stylesheet">

                <script src="static/js/jquery-2.1.1.min.js"></script>
                <script src="static/js/validation.js"></script>

            </head>

            <body class="grey lighten-3">
                <main class="valign-wrapper no-padding">
                    <div class="container valign">
                        <div class="row">
                            <form id="restore-password-form" class="col s8 offset-s2 m6 offset-m3 white center-align">
                                <br><br>
                                <h5 class="center-align">Restoring Password:</h5>
                                <div class="row">
                                    <div class="input-field col s12">
                                        <input id="restore-email" type="email">
                                        <label class="left-align" for="restore-email" data-error="wrong" data-success="right">Email</label>
                                    </div>
                                </div>
                                <button id="restore-email-btn" class="btn waves-effect waves-light" type="submit" name="action">Restore
                                    <i class="material-icons right">lock_outline</i>
                                </button>
                                <br>
                                <p class="center-align"><a href="home" class="orange-text">Home page</a></p>
                                <span id="messageRestoreFail" class="center-align red-text"></span>
                                <span id="messageRestoreOk" class="center-align green-text"></span>
                                <br><br>
                            </form>
                        </div>
                    </div>
                </main>

                <script src="static/js/materialize.min.js"></script>
                <script src="static/js/init.js"></script>
                <script src="static/js/login.js"></script>
                <script src="static/js/restore-password.js"></script>

            </body>

            </html>