<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
            <!DOCTYPE html>

            <html>

            <head>

                <!-- Meta -->
                <meta charset="utf-8">

                <!-- Mobile Metas -->
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Paykick - Statistics</title>

                <jsp:include page="common/styles.jsp"></jsp:include>
                <jsp:include page="common/scripts.jsp"></jsp:include>
            </head>

            <body>
                <jsp:include page="common/navigation.jsp"></jsp:include>

                <!-- Main Content -->
                <main class="valign-wrapper grey lighten-3">
                    <div class="container valign" style="margin-top: 20px;">
                        <h4 class="center-align">Statistics:</h4>
                        <div class="row">
                            <div class="input-field col s6 offset-s3 l4 offset-l4">
                                <i class="material-icons prefix">person</i>
                                <input type="text" id="autocomplete-input" class="autocomplete player-input" autocomplete="off">
                                <label for="autocomplete-input">Player Search</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s8 offset-s2 l6 offset-l3">
                                <ul class="tabs transparent">
                                    <li class="tab col s4 l2"><a href="">TEAMS</a></li>
                                    <li class="tab col s4 l2"><a href="">PLAYERS</a></li>
                                </ul>
                            </div>
                        </div>
                        <ul class="collapsible popout" data-collapsible="expandable">
                            <li>
                                <div class="collapsible-header center-align"><i class="material-icons green-text">group</i><span class="green-text"><strong>Barcelona</strong></span></div>
                                <div class="collapsible-body center-align">
                                    <br>
                                    <a class="waves-effect waves-light btn modal-trigger green" href="#modal-stats"><i class="material-icons right">timeline</i>Statistics</a>
                                    <br>
                                    <br>
                                </div>
                            </li>
                            <li>
                                <div class="collapsible-header center-align"><i class="material-icons green-text">person</i><span class="green-text"><strong>Fabien Bartez</strong></span></div>
                                <div class="collapsible-body center-align">
                                    <br>
                                    <a class="waves-effect waves-light btn modal-trigger green" href="#modal-stats"><i class="material-icons right">timeline</i>Statistics</a>
                                    <br>
                                    <br>
                                </div>
                            </li>
                        </ul>
                        <ul class="pagination center-align">
                            <li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
                            <li class="active green"><a href="#!">1</a></li>
                            <li class="waves-effect"><a href="#!">2</a></li>
                            <li class="waves-effect"><a href="#!">3</a></li>
                            <li class="waves-effect"><a href="#!">4</a></li>
                            <li class="waves-effect"><a href="#!">5</a></li>
                            <li class="waves-effect"><a href="#!"><i class="material-icons">chevron_right</i></a></li>
                        </ul>
                    </div>

                </main>

                <!-- Statistics Modal Structure -->
                <div id="modal-stats" class="modal my-stats">
                    <div class="modal-content">
                        <div class="row">
                            <div id="statistics" class="col s12 center-align">
                                <h5>Bastian Schwarz</h5> Olya, Put Your Code Here
                            </div>
                        </div>
                    </div>
                </div>

                <jsp:include page="common/footer.jsp"></jsp:include>

            </body>

            </html>