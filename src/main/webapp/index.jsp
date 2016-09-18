<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en_EN'}" scope="session" />
            <fmt:setLocale value="${language}" />
            <fmt:setBundle basename="i18n.lang" />
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
                <link href='http://fonts.googleapis.com/css?family=Raleway:100,200,300,400,500,600,700,800,900' rel='stylesheet' type='text/css'>
                <link href='http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700' rel='stylesheet' type='text/css'>
                <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

                <!-- Styles -->
                <link href="${pageContext.request.contextPath}/static/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
                <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">

                <!-- LayerSlider stylesheet -->
                <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/layerslider.css" type="text/css">

                <script src="static/js/jquery-2.1.1.min.js"></script>

                <!-- Script for updating top users and upcoming matches with AJAX-->
                <script src="${pageContext.request.contextPath}/static/js/top-users.js"></script>
                <script src="${pageContext.request.contextPath}/static/js/upcoming-matches.js"></script>
                <script src="${pageContext.request.contextPath}/static/js/validation.js"></script>
            </head>

            <body>

                <div class="main-wrapper">
                    <div id="home">
                        <!-- SLider -->
                        <div id="bg-slider-home">
                            <div id="slider-wrapper">
                                <div id="full-slider-wrapper">
                                    <div id="layerslider" style="width: 100%; height: 780px; max-width: 100%;">
                                        <div class="ls-slide" data-ls="slidedelay:8000;transition2d:4;">
                                            <img src="static/img/slider/slide-b-bg.jpg" class="ls-bg" alt="Slide background" /> <img class="ls-l" style="top: 180px; left: 790px; white-space: nowrap;" data-ls="offsetxin:50;durationin:2000;delayin:800;offsetxout:50;durationout:1000;parallaxlevel:1;" src="static/img/slider/gerrard.png" alt=""> <img class="ls-l" style="top: 120px; left: 500px; white-space: nowrap;" data-ls="offsetxin:100;durationin:2000;delayin:1200;offsetxout:100;durationout:1000;parallaxlevel:3;" src="static/img/slider/beckham.png" alt="">
                                            <h6 class="ls-l" style="top: 350px; left: 236px; height: 40px; white-space: nowrap; color: #FFF; font-size: .9em; font-weight: bold;" data-ls="
							durationin:2000;
							delayin:2000;
							rotatein:20;
							rotatexin:30;
							scalexin:1.5;
							scaleyin:1.5;
							transformoriginin:
							left 50% 0;
							durationout:750;
							rotateout:20;
							rotatexout:-30;
							scalexout:0;
							scaleyout:0;
							transformoriginout:left 50% 0;">
                                <fmt:message key="home.choosematch" /></h6>
                                            <h2 class="ls-l" style="top: 370px; left: 110px; text-transform: uppercase; font-size: 3em; color: #FFDD00; margin-bottom: 20px; font-weight: bold; white-space: nowrap;" data-ls="
							offsetxin:0;
							durationin:2000;
							delayin:2300;
							rotateyin:90;
							skewxin:60;
							transformoriginin:25% 50% 0;
							offsetxout:100;
							durationout:750;
							skewxout:60;">
                                <fmt:message key="home.extremelysimple" /></h2>
                                            <p class="ls-l" style="top: 435.68px; left: 43.359px; font-size: 13.8141px; padding: 0px 29.6016px; color: #FFF; line-height: 24.668px; width: 600px; height: auto; margin-left: 0px; margin-top: 0px; opacity: 1; visibility: visible; text-align: center;" data-ls="
							durationin:2000;
							delayin:2800;
							rotatein:20;
							rotatexin:30;
							scalexin:1.5;
							scaleyin:1.5;
							transformoriginin:left 50% 0;
							durationout:750;
							rotateout:20;
							rotatexout:-30;
							scalexout:0;
							scaleyout:0;
							transformoriginout:left 50% 0;">
                                                <fmt:message key="home.thehigh" />
                                                <br> <fmt:message key="home.rightchoise" />
                                            </p>
                                            <h6 class="ls-l" style="top: 490.148px; left: 274.359px; color: #FFF; font-size: .9em; font-weight: bold;" data-ls="
							offsetxin:0;
							durationin:2000;
							delayin:3200;
							rotateyin:90;
							skewxin:60;
							transformoriginin:25% 50% 0;
							offsetxout:100;
							durationout:750;
							skewxout:60;">
                                <fmt:message key="home.betsfrom" /> <span
                                    style="font-family: 'Open Sans'; font-size: 2em; letter-spacing: 0px; color: #FF0000;">$1</span>
                                <fmt:message key="home.perbet" />
                            </h6>
                                            <c:if test="${login==null}">
                                                <div class="ls-l" style="top: 565.148px; left: 258.359px; font-size: 1.2em; font-weight: bold;" data-ls="
							offsetxin:0;
							durationin:2000;
							delayin:3600;
							rotateyin:90;
							skewxin:60;
							transformoriginin:25% 50% 0;
							offsetxout:100;
							durationout:750;
							skewxout:60;"><a id="home_bet" href="#login-modal" class="waves-effect waves-light btn orange modal-trigger"><span
                                    class="grey-text text-darken-3"><fmt:message key="home.makeabet" /></span></a></div>
                                            </c:if>
                                            <div class="ls-l nav-wrapper hide-on-large-only" style="top: 20px; left: 20px;">
                                                <a href="#" data-activates="sidebar-nav" class="button-collapse"><i
                                        class="material-icons white-text" style="font-size: 3rem;">menu</i></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Upcoming Matches -->
                        <div id="home-matches" class="grey lighten-3">
                            <br>
                            <br>
                            <div class="container">
                                <h4 class="center-align" style="margin-bottom: 10px;"><fmt:message key="home.upcoming" /></h4>
                                <div class="row">
                                    <div class="col s12 m10 offset-m1 l8 offset-l2">
                                        <ul class="collapsible popout" data-collapsible="expandable" id="newMatches">

                                        </ul>
                                    </div>
                                </div>
                                <br>
                            </div>
                        </div>

                        <!-- Top Users -->
                        <div id="home-top-users" class="grey lighten-2">
                            <br>
                            <br>
                            <div class="container">
                                <h4 class="center-align" style="margin-bottom: 10px;"><fmt:message key="home.topusers" /></h4>
                                <div class="row">
                                    <div class="col s12 m10 offset-m1 l8 offset-l2">
                                        <table class="striped centered">
                                            <thead>
                                                <tr>
                                                    <th><fmt:message key="caption.nickname" /></th>
                                                    <th><fmt:message key="caption.totalwin" /></th>
                                                    <th><fmt:message key="caption.lastbet" /></th>
                                                </tr>
                                            </thead>

                                            <tbody id="topUsers">

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <br>
                            </div>
                        </div>

                        <!-- Footer -->
                        <footer class="page-footer grey darken-1">
                            <div id="contacts" class="container">
                                <div class="row">
                                    <div class="col s10 offset-s1 m6">
                                        <h6 class="white-text"><fmt:message key="footer.ourcontacts" /></h6>
                                        <ul>
                                            <li><a class="grey-text text-lighten-3 valign-wrapper" href="mailto:paykick.team@gmail.com"><i
                                    class="material-icons orange-text valign">email</i>&nbsp; paykick.team@gmail.com</a>
                                            </li>
                                            <li><a class="grey-text text-lighten-3 valign-wrapper" href=""><i
                                    class="material-icons red-text valign">phone</i>&nbsp;+38 (063) 583-80-88</a></li>
                                            <li><a class="grey-text text-lighten-3 valign-wrapper" href=""><i
                                    class="material-icons green-text valign">location_on</i>&nbsp; <fmt:message key="footer.address" /></a></li>
                                        </ul>
                                    </div>
                                    <div class="col s10 offset-s1 m4 offset-m1">
                                        <h6 class="white-text "><fmt:message key="footer.ourrules" /></h6>
                                        <ul>
                                            <li><a class="white-text valign-wrapper modal-trigger" href="#terms-modal" href="" target="_blank"><i class="material-icons valign green-text">assignment</i>&nbsp;    <fmt:message key="footer.termsofuse" /></a></li>
                                            <li><a class="white-text valign-wrapper modal-trigger" href="#policy-modal" target="_blank"><i
                                    class="material-icons valign orange-text">https</i>&nbsp; <fmt:message key="footer.privacypolicy" /></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>

                            <div class="footer-copyright grey darken-3">
                                <div class="center-align">
                                    <a href="${pageContext.request.contextPath}/home"><span class="orange-text"><strong>PayKick</strong></span></a> © 2016 <fmt:message key="footer.rightsreserved" />
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

                        <!-- Top Menu -->
                        <div class="container bs-main">
                            <div class="row">
                                <div class="col s12 l10 offset-l1">
                                    <!-- Top Header and Navigation -->
                                    <div class="top-header hide-on-med-and-down">
                                        <div class="top-navigation">
                                            <ul class="top-nav list-unstyled list-inline">
                                                <li><a href="#home-matches"><fmt:message key="common.matches" /></a></li>
                                                <li><a href="#home-top-users"><fmt:message key="home.topusers" /></a></li>
                                                <li>
                                                    <a href="${pageContext.request.contextPath}/home"><img src="${pageContext.request.contextPath}/static/img/logo.png" alt="PayKick Logo" /></a>
                                                </li>
                                                <li><a href="#contacts"><fmt:message key="home.contacts" /></a></li>
                                                <c:choose>
                                                    <c:when test="${login==null}">
                                                        <li><a href="#login-modal" class="my-red modal-trigger"><fmt:message key="home.login" /></a></li>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <li><a href="${pageContext.request.contextPath}/${role}" class="my-orange"><fmt:message key="home.myaccount" /></a></li>
                                                        <li><a href="${pageContext.request.contextPath}/logout" class="my-red"><fmt:message key="home.logout" /></a></li>
                                                    </c:otherwise>
                                                </c:choose>

                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Sidebar Menu -->
                        <nav class="transparent" style="height: 0;">
                            <div>
                                <ul id="sidebar-nav" class="side-nav grey darken-4">
                                    <li><a href="#home-matches"><fmt:message key="home.matches" /></a></li>
                                    <li><a href="#home-top-users"><fmt:message key="home.topusers" /></a></li>
                                    <li><a href="#contacts"><fmt:message key="home.contacts" /></a></li>

                                    <c:choose>
                                        <c:when test="${login==null}">
                                            <li><a class="modal-trigger" href="#login-modal"><fmt:message key="home.login" /><i
                                    class="material-icons right red-text">input</i></a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li><a href=""><fmt:message key="home.myaccount" /><i class="material-icons right orange-text">perm_identity</i></a>
                                            </li>
                                            <li><a href="logout"><fmt:message key="home.logout" /><i
                                    class="material-icons right red-text">power_settings_new</i></a></li>
                                        </c:otherwise>
                                    </c:choose>


                                    <li><a class="dropdown-button" data-activates="languageDropdown"><fmt:message key="common.language" /><i
                            class="material-icons right green-text">language</i></a></li>
                                </ul>
                                <ul id="languageDropdown" class="dropdown-content sidebar grey darken-3">
                                    <li><a href="?language=ua_UA" class="white-text">УКРАЇНСЬКА<i
                            class="material-icons right green-text">language</i></a></li>
                                    <li><a href="?language=en_EN" class="white-text">ENGLISH<i class="material-icons right green-text">language</i></a>
                                    </li>
                                </ul>
                            </div>
                        </nav>

                    </div>
                </div>
                <%
    String userLogin = "";
    String userPassword = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null)
        for (Cookie cookie : cookies) {
            if (cookie.getName() != null) {
                if (cookie.getName().equals("userLogin")) {
                    userLogin = cookie.getValue();
                }
                if (cookie.getName().equals("userPassword")) {
                    userPassword = cookie.getValue();
                }
            }

        }
%>
                    <!-- Login Modal Structure -->
                    <div id="login-modal" class="modal">
                        <div class="modal-content">
                            <div class="row">
                                <form id="home-login-form" class="col s12">
                                    <h5 class="center-align"><fmt:message key="home.login" /></h5>
                                    <br>
                                    <div class="row">
                                        <div class="input-field col s12">
                                            <input id="login" type="text" length="50" value="<%=userLogin%>" required>
                                            <label for="login"><fmt:message key="caption.login" /></label>
                                        </div>
                                        <div class="input-field col s12">
                                            <input id="password" type="password" length="50" value="<%=userPassword%>" required>
                                            <label for="password"><fmt:message key="caption.password" /></label>
                                        </div>
                                        <div class="col s12">
                                            <p>
                                                <input type="checkbox" id="remember-me" value="rememberMe" />
                                                <label for="remember-me"><fmt:message key="home.login.remember" /></label>
                                            </p>
                                        </div>
                                    </div>
                                    <div id="home-submit-login" class="center-align">
                                        <button id="loginBtn" class="btn waves-effect waves-light"><fmt:message key="home.login" /></button>
                                        <br>
                                        <br>
                                        <p class="message center-align"><fmt:message key="home.login.stillnot" /> <a><span class="orange-text"><strong><fmt:message key="home.login.create" /></strong></span></a>
                                        </p>
                                        <span class="center-align red-text" id="messageLogin"></span>
                                        <p class="center-align"><a href="restorePassword" class="orange-text text-darken-3"><fmt:message key="home.login.forgot" /></a></p>
                                    </div>
                                </form>

                                <form id="home-registration-form" class="col s12">
                                    <h5 class="center-align"><fmt:message key="home.registration" /></h5>
                                    <div class="row">
                                        <div class="input-field col s12">
                                            <input id="fname" type="text" length="100" required>
                                            <label for="fname"><fmt:message key="caption.fname" /></label>
                                        </div>
                                        <div class="input-field col s12">
                                            <input id="surname" type="text" length="100" required>
                                            <label for="surname"><fmt:message key="caption.surname" /></label>
                                        </div>
                                        <div class="col s12">
                                            <p class="big"><fmt:message key="caption.sex" /></p>
                                            <p>
                                                <input name="gender" type="radio" id="male" value="male" required checked/>
                                                <label for="male"><fmt:message key="caption.male" /></label>
                                            </p>
                                            <p>
                                                <input name="gender" type="radio" id="female" value="female" />
                                                <label for="female"><fmt:message key="caption.female" /></label>
                                            </p>
                                        </div>
                                        <div class="col s12">
                                            <p class="big"><fmt:message key="caption.age" /></p>
                                            <p class="range-field">
                                                <input type="range" id="age" min="0" max="100" value="0" />
                                            </p>
                                        </div>
                                        <div class="input-field col s12">
                                            <input id="email" type="email" required>
                                            <label for="email"><fmt:message key="caption.email" /></label>
                                        </div>
                                        <div class="input-field col s12">
                                            <input id="login-reg" type="text" length="50" required>
                                            <label for="login-reg"><fmt:message key="caption.login" /></label>
                                        </div>
                                        <div class="input-field col s12">
                                            <input id="password-reg" type="password" length="50" required>
                                            <label for="password-reg"><fmt:message key="caption.password" /></label>
                                        </div>
                                    </div>
                                    <div id="home-submit-reg" class="center-align">
                                        <button id="register" class="btn waves-effect waves-light"><fmt:message key="home.registration.signup" /></button>
                                        <p class="message center-align"><fmt:message key="home.registration.alreadyhave" /> <a><span class="orange-text"><fmt:message key="home.login.camelcase" />!</span></a>
                                        </p>
                                        <span class="center-align red-text" id="messageRegistration"></span>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <!-- Privacy Policy Modal Structure -->
                    <div id="policy-modal" class="modal">
                        <div class="modal-content">
                            <h4>Privacy Policy</h4>
                            <ol class="grey-text text-darken-1">
                                <li>
                                    <strong>GENERAL PROVISIONS</strong>
                                    <ol>
                                        <li>PayKick resource administrator (hereinafter - the Administrator) publishes this privacy policy (hereinafter - the Policy) that provides wich information about the Users Administrator collects in according to provision the services with Site, the purpose of the collection, the order of using and disclosuring of such information to third persons. The Administrator is obliged to protect and respect the privacy of the users.
                                        </li>
                                        <li>While registrating on the Site, the User accepts this Policy and agrees for collection, storage, using and disclosuring of their personal datain accordance with the Law of Ukraine "Personal Data Protection", "Consumer Protection", "Protection of information in telecommunication systems".
                                        </li>
                                        <li>If the user visits the public sections of the Site, Administrator does not collect any of his personal information.
                                        </li>
                                    </ol>
                                </li>
                                <br>
                                <li>
                                    <strong>USER INFO</strong>
                                    <ol>
                                        <li>During registration of the user on the site Administrator asks to povide all requested information in the extent, necessary to provide services.
                                        </li>
                                        <li>
                                            The administrator has the right to collect such information about users:
                                            <ol>
                                                <li>name and surname of user;</li>
                                                <li>email address;</li>
                                                <li>photo (image) of user;</li>
                                            </ol>
                                        </li>
                                        <li>This information is included to the User Personal Account, through which User use the website. Other users will only get access to such information as the photo (image) of user.
                                        </li>
                                        <li>Administrator collects some data automatically,when user visits the Site, such as the IP-address of the computer, used browser and operating system, date and time of user’s access to the Site. This facilitates the work of the Site and using itby the users.
                                        </li>
                                    </ol>
                                </li>
                                <br>
                                <li>
                                    <strong>DATA PRIVACY</strong>
                                    <ol>
                                        <li>Your personal information is protected by the password, so we recommend choose a password that is difficult to pick or guess automatically, but that you will easily remember, while you are registering on a website PayKick. Our managers have no access to information about your password.
                                        </li>
                                        <li>To prevent the accident of swindling, your credit carddata is not stored in any separate database.During making the online transactions through Liqpay, which is connected to the resource PayKick, credit card information is transmitted in encrypted form. Reliability of the system Liqpay technology is provided by OTP (while the transaction is confirmed within the system by dynamic one-time password that is sent to you via SMS) and confirmed by certificates of GoDaddy Secure Web Site, Verified by Visa and MasterCard SecureCode. More details about the system Liqpay and payment security with it you can read on the official website <a class="orange-text" href="http://www.liqpay.com" target="_blank">www.liqpay.com.</a>
                                        </li>
                                        <li>The Administrator ensures that all private information,stored in our system will be used for the purposes of service only, and will not be transmitted in any form to third persons or organizations.
                                        </li>
                                    </ol>
                                </li>
                            </ol>
                        </div>
                    </div>

                    <!-- Terms Modal Structure -->
                    <div id="terms-modal" class="modal">
                        <div class="modal-content">
                            <h4>Terms of Use</h4>
                            <br>
                            <h6>Main terms and points:</h6>
                            <ul class="grey-text text-darken-1">
                                <li><strong>Client (bettor)</strong> – a person who makes a bet with the Betting company.</li>
                                <li><strong>Wager</strong> – agreement between the Client and the Betting company where the loser must fulfill his obligations according to the rules set by the Betting company. The bets are accepted in accordance to the rules and odds made by the Betting company.
                                </li>
                                <li><strong>Stake</strong> – the amount used for betting.</li>
                                <li><strong>Outcome</strong> – the result on which the bet has been placed.</li>
                                <li><strong>Odds to win</strong> – the quotation of the Betting company for outcomes of different events
                                </li>
                                <li><strong>Service PayKick (hereinafter Service)</strong> – our bookmaker network service model.</li>
                            </ul>
                            <h6>General terms:</h6>
                            <ol class="grey-text text-darken-1">
                                <li>The Service will accept bets on sport events (hereinafter - Events). All stakes are accepted according to the current rules and the bettor must be familiar with all rules and must agree with them.
                                </li>
                                <li>The Service can change odds, rules and payout methods without personal notice to the clients. The bets placed before the public notice are accepted according to the terms and rules valid before the notice, the bets placed after the public notice are accepted according to the terms and rules valid after the public notice.
                                </li>
                                <li>Registration and beting is allowed only for persons that have minimum age of 18. The clients are responsible for checking that internet beting is allowed in the countries where they live. The clients are responsible for giving information about winnings and losses to their local authorities if necessary.
                                </li>
                                <li>The Service trusts completely in the information given by the Client during registration. The Betting company accepts no responsiblity if the Client provides the Service with wrong information.
                                </li>
                                <li>To be responsible to prevent cheating and other conflicts the betting company can demand the client to show personal identification (documents, passport) in order to verify the information the client has presented during registration. The Client agrees on presenting all necessary documentation during registration if demanded by the Betting company.
                                </li>
                                <li>The payouts are made according to the appropriate rules.</li>
                                <li>The Client accepts complete responsibility for keeping his/her password and account information secret. The Service guarantees that the information concerning the Client is kept secret and the Betting company takes no responsibility if the information is spread to a third part.
                                </li>
                                <li>All events confirmed with the login/email and the betting account holder`s password are valid and official. The only restriction is the saldo on the betting account. If the Client believes that the given information is lost he/she can contact the Service administrator and request for a change of the current password.
                                </li>
                                <li>The Service does not accept any responsibility on damage or losses caused by use of the site or by the contents of the site
                                </li>
                                <li>The same applies to the use or misuse of the site contents by any person, inability to be connected to the site, inability to use the site, delays in site functioning or data transmitting, failures in communication lines or to any mistakes, typing errors or misses in the maintanence of the site.
                                </li>
                                <li>If there are differences in rules and regulations between the Ukrainian language site or the site in any other language, the rules in the Ukrainian language will apply.
                                </li>
                                <li>The bets are accepted until the event starts. The bets that are placed after the start of the event are not valid and the stakes will be refunded. These bets are removed from multibets.
                                </li>
                                <li>The date and the time for the event on the list is informative only. A wrong date is no reason to refund the bet if the bet has been placed before the start of the event. If there is a wrong time on the list, the correct time will be the time on official documents (sites used by the betting company) and the realistic starting time of the event.
                                </li>
                                <li>The Service accepts no responsibilty for correct spelling of surnames, team names and names of places for the event.
                                </li>
                                <li>15. The Service reserves the right to refuse and cancel bets caused by mistakes by employees or by the computer programmes (mistakes in odds and others) and other reasons causing incorrect bets. The management of the
                                </li>
                                <li>The bets cannot be changed or cancelled after the Client has placed the bet and received a verification.
                                </li>
                                <li>A connection failure and other techinal mistakes in communication are no reasons to change or cancel a bet if the bet has been registered in the server.
                                </li>
                                <li>Loss of password is no reason to change or cancel a bet or to cancel a payout request.</li>
                                <li>The Client cannot give a permission to anybody else to use his/her betting account.</li>
                                <li>The Service reserves the right to refuse from accepting bets without written or verbal notice in advance regardless of the Client.
                                </li>
                                <li>The Client must check that his/her betting slip is filled in according to the rules. If it has been filled in uncorrectly regardless of why and who is guilty for uncorrect filling, the Service reserves the right to refund the stakes and cancel the bet.
                                </li>
                            </ol>
                            <ul class="grey-text text-darken-1">
                                <li>The complaints will be accepted in written form 10 days after the result of the event has been registered. After 10 days no complaints are accepted. In the questionable situations, which do not have precedents, the final solution is taken by the Service.
                                </li>
                            </ul>
                            <br>
                            <h6>Types of bets:</h6>
                            <ul class="grey-text text-darken-1">
                                <li>
                                    The Service offers following types of bets:
                                    <ol>
                                        <li><strong>Singlebet</strong> – betting on a single event.</li>
                                        <li><strong>Multibet</strong> – betting on many events not dependable on each other;</li>
                                    </ol>
                                </li>
                                <br>
                                <li>Win on multinbet is the sum of stakes multiplied by multibet odd that is obtained by multiplying the result odds of all events.The Client can include any events in multibet. Multibet is counted as winners if all events in multibet are predicted correctly. If at least one event is not predicted correctly, the multibet is losing.
                                </li>
                                <br>
                                <li>The bets on football are determined by the result of the match in regular playing time.</li>
                            </ul>
                            <h6>The Service allows next events:</h6>
                            <ol class="grey-text text-darken-1">
                                <li>Final score of the game;</li>
                                <li>
                                    Game result – you can bet on each game result:
                                    <ol>
                                        <li>First team will win (“1”);</li>
                                        <li>Second team will win (“2”);</li>
                                        <li>Draw (“X”);</li>
                                        <li>First team will win or draw (“1X”);</li>
                                        <li>Second team will win or draw (“X2”);</li>
                                        <li>First or Second team will win (“12”).</li>
                                    </ol>
                                </li>
                                <li>Final total score (sum of goals made by both teams during game);</li>
                                <li>Number of goals made by a footballer during game.</li>
                            </ol>
                        </div>
                    </div>

                    <!-- Scripts -->

                    <script src="static/js/materialize.min.js"></script>
                    <script src="static/js/init.js"></script>
                    <script src="static/js/login.js"></script>

                    <script src="static/js/layerslider/greensock.js" type="text/javascript"></script>
                    <script src="static/js/layerslider/layerslider.transitions.js" type="text/javascript"></script>
                    <script src="static/js/layerslider/layerslider.kreaturamedia.jquery.js" type="text/javascript"></script>
                    <script>
                        //slider init
                        jQuery("#layerslider").layerSlider({
                            responsive: false,
                            responsiveUnder: 1280,
                            layersContainer: 1280,
                        });
                    </script>

            </body>

            </html>