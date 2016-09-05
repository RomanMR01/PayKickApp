<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <!DOCTYPE html>

    <html>

    <head>

        <!-- Meta -->
        <meta charset="utf-8">

        <!-- Mobile Metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>PayKick. One bet - one hit!</title>

        <!-- Favicon -->
        <link rel="shortcut icon" href="static/img/favicon.ico">

        <!-- Fonts -->
        <link href='http://fonts.googleapis.com/css?family=Raleway:100,200,300,400,500,600,700,800,900' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700' rel='stylesheet' type='text/css'>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

        <!-- Styles -->


        <link href="static/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />

        <link href="static/css/style.css" rel="stylesheet">

        <!-- LayerSlider stylesheet -->
        <link rel="stylesheet" href="static/css/layerslider.css" type="text/css">

        <script src="static/js/jquery-2.1.1.min.js"></script>

        <!-- Script for updating top users and upcoming matches with AJAX-->
        <script>

            //Method for getting top users with ajax
            (function ($) {
                $(document).ready(function () {
                    var $container = $("#topUsers");
                    var getTopUsers = function () {

                        $.ajax({
                            url: "homeStatistics",
                            data: {type: 'topUsers'},
                            type: "POST",
                            success: function (responseText) {
                                $container.find("tr").remove();
                                var response = JSON.parse(responseText);

                                /*
                                Writing each table row element into #topUsers table
                                 */
                                $.each(response, function (i, item) {
                                    var lastWin = response[i].lastBetSum;
                                    var td = "";
                                    if(lastWin>0){
                                        td = '<td class="green-text">'  + "+" + lastWin  + " $"+ '</td>';

                                    }else{
                                        td = '<td class="red-text">'  + lastWin + " $" + '</td>';
                                    }
                                    $('<tr>').html("<td>" + response[i].login + "</td><td>" + response[i].awardSum + "</td>" + td).appendTo('#topUsers');
                                });
                            }

                        });
                    }
                    getTopUsers();//First call when page ready
                    setInterval(getTopUsers, 5000);//Interval call every 5 seconds
                });
            })(jQuery);

            //Method for getting upcoming matches with ajax
            (function ($) {
                $(document).ready(function () {
                    var $container = $("#newMatches");
                    var getNewMatches = function () {
                        $.ajax({
                            url: "homeStatistics",
                            data: {type: 'newMatches'},
                            type: "POST",
                            success: function (responseText) {
                                $container.find("li").remove();
                                var response = JSON.parse(responseText);

                                /*
                                Writing each collapsible into #newMatches
                                 */
                                $.each(response, function (i, item) {
                                    console.log(response[i].title);
                                    var header = '<div class="collapsible-header center-align"><i class="material-icons">reorder</i><span class="orange-text"><strong>' + response[i].title + ':' + '</strong></span>' +  response[i].firstTeam + ' - ' + response[i].secondTeam + '</div>'
                                    var body = '<div class="collapsible-body"> <table class="centered">'
                                    var thead = '<thead> <tr> <th>1</th> <th>X</th> <th>2</th> <th>1X</th> <th>X2</th> <th>12</th> </tr> </thead>';
                                    var coeff = '<tbody><tr> <td>' + response[i].c1 + '</td> <td>' + response[i].cX + '</td> <td>' + response[i].c2 + '</td> <td>' + response[i].c1X + '</td> <td>' + response[i].cX2 + '</td> <td>' + response[i].c12 + '</td> </tr></tbody>';
                                    var full = header + body + thead + coeff + '</table></div>';

                                    $('<li>').html(full).appendTo('#newMatches');
                                });
                                //expandAll();
                            }

                        });
                    }
                    getNewMatches();
                    setInterval(getNewMatches, 20000);
                });
            })(jQuery);

            //Open all collapsible
            function expandAll(){
                $(".collapsible-header").addClass("active");
                $(".collapsible").collapsible({accordion: false});
            }
        </script>
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
									Choose match - Make bet - Earn money</h6>
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
									Extremely Simple Rules</h2>
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
                                        The high-quality statistics system will help you to make
                                        <br> right choice and to earn your first win!
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
									Bets from <span
										style="font-family: 'Open Sans'; font-size: 2em; letter-spacing: 0px; color: #FF0000;">$1</span>
									per bet
								</h6>
                                    <div class="ls-l" style="top: 565.148px; left: 258.359px; font-size: 1.2em; font-weight: bold;" data-ls="
							offsetxin:0;
							durationin:2000;
							delayin:3600;
							rotateyin:90;
							skewxin:60;
							transformoriginin:25% 50% 0;
							offsetxout:100;
							durationout:750;
							skewxout:60;"><a id="home_bet" href="#login-modal" class="waves-effect waves-light btn orange modal-trigger"><span class="grey-text text-darken-3">Make a Bet</span></a></div>
                                    <div class="ls-l nav-wrapper hide-on-large-only" style="top: 20px; left: 20px;">
                                        <a href="#" data-activates="home-mobile" class="button-collapse"><i class="material-icons white-text" style="font-size: 3rem;">menu</i></a>
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
                        <h4 class="center-align" style="margin-bottom: 10px;">Upcoming Matches</h4>
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
                        <h4 class="center-align" style="margin-bottom: 10px;">Top Users</h4>
                        <div class="row">
                            <div class="col s12 m10 offset-m1 l8 offset-l2">
                                <table class="striped centered">
                                    <thead>
                                        <tr>
                                            <th>Nickname</th>
                                            <th>Total Win</th>
                                            <th>Last Bet</th>
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
                                <h6 class="white-text">Our Contacts:</h6>
                                <ul>
                                    <li><a class="grey-text text-lighten-3 valign-wrapper" href="mailto:paykick.team@gmail.com"><i class="material-icons orange-text valign" >email</i>&nbsp; paykick.team@gmail.com</a></li>
                                    <li><a class="grey-text text-lighten-3 valign-wrapper" href=""><i class="material-icons red-text valign" >phone</i>&nbsp;+38 (063) 583-80-88</a></li>
                                    <li><a class="grey-text text-lighten-3 valign-wrapper" href=""><i class="material-icons green-text valign" >location_on</i>&nbsp; Ukraine, Lviv. Kozlaniuka str. 7/5</a></li>
                                </ul>
                            </div>
                            <div class="col s10 offset-s1 m4 offset-m1">
                                <h6 class="white-text ">Our Rules:</h6>
                                <ul>
                                    <li><a class="white-text valign-wrapper" href="" target="_blank"><i class="material-icons valign green-text" >assignment</i>&nbsp; Terms of Use</a></li>
                                    <li><a class="white-text valign-wrapper" href="" target="_blank"><i class="material-icons valign orange-text" >https</i>&nbsp; Privacy Policy</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="footer-copyright grey darken-3">
                        <div class="center-align">
                            <a href=""><span class="orange-text"><strong>PayKick</strong></span></a> © 2016 All rights Reserved
                            <div class="right lang">
                                <a href="?language=ua_UA"><div id="ua"></div></a>
                                <a href="?language=en_EN"><div id="gb"></div></a>
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
                                        <li><a href="#home-matches">Matches</a></li>
                                        <li><a href="#home-top-users">Top Users</a></li>
                                        <li>
                                            <a href=""><img src="static/img/logo.png" alt="PayKick Logo" /></a>
                                        </li>
                                        <li><a href="#contacts">Contacts</a></li>
                                        <li><a href="#login-modal" class="my-red modal-trigger">Log In</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Sidebar Menu -->
                <nav class="transparent" style="height: 0;">
                    <div class="">
                        <ul id="home-mobile" class="side-nav grey darken-4">
                            <li><a href="#home-matches">MATCHES</a></li>
                            <li><a href="#home-top-users">TOP USERS</a></li>
                            <li><a href="#contacts">CONTACTS</a></li>
                            <li><a href="">MY ACCOUNT<i class="material-icons right orange-text">perm_identity</i></a></li>
                            <li><a class="modal-trigger" href="#login-modal">LOGIN<i class="material-icons right red-text">input</i></a></li>
                            <li><a href="logout">LOGOUT<i class="material-icons right red-text">power_settings_new</i></a></li>
                            <li><a class="dropdown-button" data-activates="languageDropdown">LANGUAGE<i class="material-icons right green-text">language</i></a></li>
                        </ul>
                        <ul id="languageDropdown" class="dropdown-content grey darken-3">
                            <li><a href="?language=ua_UA" class="white-text">УКРАЇНСЬКА<i class="material-icons right green-text">language</i></a></li>
                            <li><a href="?language=en_EN" class="white-text">ENGLISH<i class="material-icons right green-text">language</i></a></li>
                        </ul>
                    </div>
                </nav>

            </div>
        </div>

        <!-- Modal Structure -->
        <div id="login-modal" class="modal">
            <div class="modal-content">
                <div class="row">
                    <form id="home-login-form" class="col s12">
                        <h5 class="center-align">Log In</h5>
                        <br>
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="login" type="text" required>
                                <label for="login">Login</label>
                            </div>
                            <div class="input-field col s12">
                                <input id="password" type="password" required>
                                <label for="password">Password</label>
                            </div>
                            <div class="col s12">
                                <p>
                                    <input type="checkbox" id="remember-me" />
                                    <label for="remember-me">Remember Me</label>
                                </p>
                            </div>
                        </div>
                        <div id="home-submit" class="center-align">
                            <button class="btn waves-effect waves-light" type="submit">Log In</button>
                            <p class="message center-align">Still Not Registered? <a><span class="orange-text">Create an account!</span></a></p>
                        </div>
                    </form>

                    <form id="home-registration-form" class="col s12">
                        <h5 class="center-align">Registration</h5>
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="fname" type="text" required>
                                <label for="fname">Name</label>
                            </div>
                            <div class="input-field col s12">
                                <input id="surname" type="text" required>
                                <label for="surname">Surname</label>
                            </div>
                            <div class="col s12">
                                <p class="big">Sex:</p>
                                <p>
                                    <input name="sex" type="radio" id="male" value="male" required checked />
                                    <label for="male">Male</label>
                                </p>
                                <p>
                                    <input name="sex" type="radio" id="female" value="female" />
                                    <label for="female">Female</label>
                                </p>
                            </div>
                            <div class="col s12">
                                <p class="big">Age:</p>
                                <p class="range-field">
                                    <input type="range" id="age" min="0" max="100" value="0" />
                                </p>
                            </div>
                            <div class="input-field col s12">
                                <input id="email" type="email" class="validate" required>
                                <label for="email">Email</label>
                            </div>
                            <div class="input-field col s12">
                                <input id="login-reg" type="text" required>
                                <label for="login-reg">Login</label>
                            </div>
                            <div class="input-field col s12">
                                <input id="password-reg" type="password" required>
                                <label for="password-reg">Password</label>
                            </div>
                        </div>
                        <div id="home-submit" class="center-align">
                            <button class="btn waves-effect waves-light" type="submit">Sign Up</button>
                            <p class="message center-align">Already Have An Account? <a><span class="orange-text">Log In!</span></a></p>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Scripts -->

        <script src="static/js/materialize.min.js"></script>
        <script src="static/js/init.js"></script>

        <script src="static/js/layerslider/greensock.js" type="text/javascript"></script>
        <script src="static/js/layerslider/layerslider.transitions.js" type="text/javascript"></script>
        <script src="static/js/layerslider/layerslider.kreaturamedia.jquery.js" type="text/javascript"></script>

    </body>

    </html>