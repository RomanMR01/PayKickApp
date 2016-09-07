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
                            <c:choose>
                                <c:when test="${hideForm==null}">
                                    <form id="new-password-form"class="col s8 offset-s2 m6 offset-m3 white center-align">
                                        <br><br>
                                        <h5 class="center-align">Enter New Password:</h5>
                                        <div id="formInput" class="row">
                                            <div class="input-field col s12">
                                                <input id="new-password-input" type="password">
                                                <label class="left-align" for="new-password-input">New Password</label>
                                            </div>
                                        </div>
                                        <button id="new-password-btn"class="btn waves-effect waves-light" type="submit" name="action">Confirm
                                            <i class="material-icons right">lock_outline</i>
                                        </button>
                                        <br>
                                        <p class="center-align"><a href="home" class="orange-text">Home page!</a></p>
                                        <br>
                                        <span id="messageNewPass" class="center-align red-text"></span>
                                        <br><br>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <form class="col s8 offset-s2 m6 offset-m3 white center-align">
                                        <br><br>
                                        <div class="row">
                                            <h5 class="center-align">Link not valid!</h5>
                                        </div>
                                        <div class="row">
                                            <h5 class="center-align">The link is active for 24 hours!</h5>
                                        </div>
                                        <div class="row">
                                            <h5><a href="home" class="orange-text">Home page!</a></h5>
                                            <br><br>
                                        </div>
                                    </form>
                                </c:otherwise>
                            </c:choose>

                            <form id="go-home-form" class="col s8 offset-s2 m6 offset-m3 white center-align" style="display: none">
                                <br><br>
                                <div class="row">
                                    <h5 class="center-align">Password restored successfully!</h5>
                                </div>
                               <div class="row">
                                   <h5><a href="home" class="orange-text">Home page!</a></h5>
                               </div>
                                <br><br>
                            </form>
                        </div>
                    </div>
                </main>

                <script src="static/js/materialize.min.js"></script>
                <script src="static/js/init.js"></script>
                <script src="static/js/login.js"></script>
                <script src="static/js/new-password.js"></script>

            </body>

            </html>