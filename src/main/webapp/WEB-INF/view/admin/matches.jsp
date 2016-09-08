<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <!DOCTYPE html>

    <html>

    <head>

        <!-- Meta -->
        <meta charset="utf-8">

        <!-- Mobile Metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin - Manage Matches</title>

        <jsp:include page="common/styles.jsp"></jsp:include>
    </head>

    <body>
        <jsp:include page="common/navigation.jsp"></jsp:include>

        <!-- Main Content -->
        <main class="valign-wrapper grey lighten-3">
            <div class="container valign" style="margin-top: 20px;">
                <h4 class="center-align">Matches:</h4>
                <br>
                <div class="row">
                    <div class="col s12 l6 offset-l3">
                        <ul class="tabs transparent">
                            <li class="tab col s4 l2"><a href="#">ALL</a></li>
                            <li class="tab col s4 l2"><a href="#" class="active">ACTIVE</a></li>
                            <li class="tab col s4 l2"><a href="#">PAST</a></li>
                        </ul>
                    </div>
                </div>
                <ul class="collapsible popout" data-collapsible="expandable">
                    <li>
                        <div class="collapsible-header center-align"><i class="material-icons green-text">list</i><span class="orange-text">1/8 Euro Cup: </span><span class="green-text"><strong>Barcelona - Real Madrid</strong></span></div>
                        <div class="collapsible-body center-align">
                            <div class="row">
                                <table class="centered responsive-table col s6 offset-s3">
                                    <thead>
                                        <tr>
                                            <th>Location</th>
                                            <th>Date</th>
                                            <th>Bookmaker</th>
                                            <th>Profit</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>London</td>
                                            <td>25.09.2016 - 19:00</td>
                                            <td>banan12</td>
                                            <td>—</td>
                                        </tr>
                                    </tbody>
                                </table>
                                <div class="col s6 offset-s3">
                                    <div class="row">
                                        <div class="input-field col s6">
                                            <input id="barca" type="number">
                                            <!-- Id must be not just a name of team because one team may have several active games at different dates -->
                                            <label for="barca">Barcelona</label>
                                        </div>
                                        <div class="input-field col s6">
                                            <input id="real" type="number">
                                            <!-- Id must be not just a name of team because one team may have several active games at different dates -->
                                            <label for="real">Real Madrid</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <a class="waves-effect waves-light btn green" href="#"><i class="material-icons right">done</i>Confirm</a>
                            <br>
                            <br>
                        </div>
                    </li>
                    <li>
                        <div class="collapsible-header center-align"><i class="material-icons grey-text">list</i><span class="grey-text">1/4 World Cup: <strong>Kongo - Brazil</strong></span></div>
                        <div class="collapsible-body center-align">
                            <div class="row">
                                <table class="centered responsive-table col s6 offset-s3">
                                    <thead>
                                        <tr>
                                            <th>Location</th>
                                            <th>Date</th>
                                            <th>Bookmaker</th>
                                            <th>Profit</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>St. Kongo</td>
                                            <td>25.08.2016 - 13:00</td>
                                            <td>banan12</td>
                                            <td class="green-text">+ $350.00</td>
                                        </tr>
                                    </tbody>
                                </table>
                                <div class="col s6 offset-s3">
                                    <div class="row">
                                        <div class="input-field col s6">
                                            <input disabled id="kongo" type="number" value="3">
                                            <!-- Id must be not just a name of team because one team may have several active games at different dates -->
                                            <label for="kongo">Kongo</label>
                                        </div>
                                        <div class="input-field col s6">
                                            <input disabled id="brazil" type="number" value="2">
                                            <!-- Id must be not just a name of team because one team may have several active games at different dates -->
                                            <label for="brazil">Brazil</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
                <br>
                <a class="waves-effect waves-light modal-trigger btn right green" href="#new-match-modal"><i class="material-icons right">playlist_add</i>New Match</a>
                <br>
                <ul class="pagination center-align">
                    <li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
                    <li class="active green"><a href="#!">1</a></li>
                    <li class="waves-effect"><a href="#!">2</a></li>
                    <li class="waves-effect"><a href="#!">3</a></li>
                    <li class="waves-effect"><a href="#!">4</a></li>
                    <li class="waves-effect"><a href="#!">5</a></li>
                    <li class="waves-effect"><a href="#!"><i class="material-icons">chevron_right</i></a></li>
                </ul>
                <br>
            </div>
        </main>

        <!-- New Match Modal Structure -->
        <div id="new-match-modal" class="modal my-narrow">
            <div class="modal-content">
                <div class="row">
                    <h5 class="center-align">New Match</h5>
                    <br>
                    <form class="col s12">
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="title" type="text">
                                <label for="title">Title</label>
                            </div>
                            <div class="input-field col s12">
                                <input id="location" type="text">
                                <label for="location">Location</label>
                            </div>
                            <div class="col s12">
                                <label for="date">Date</label>
                                <input id="date" type="datetime-local">
                            </div>
                            <div class="row">
                                <div class="input-field col s6">
                                    <input type="text" id="first-team" class="autocomplete team-input" autocomplete="off">
                                    <label for="first-team">First Team</label>
                                </div>
                                <div class="input-field col s6">
                                    <input type="text" id="second-team" class="autocomplete team-input" autocomplete="off">
                                    <label for="second-team">Second Team</label>
                                </div>
                            </div>
                            <div class="input-field col s12">
                                <input type="text" id="bookmaker" class="autocomplete bookmaker-input" autocomplete="off">
                                <label for="bookmaker">Bookmaker</label>
                            </div>
                        </div>
                        <div class="col s12 center-align">
                            <button class="btn waves-effect waves-light" type="submit" name="action">Confirm
                                <i class="material-icons right">done</i>
                            </button>
                            <br>
                            <br>
                            <span class="center-align red-text" id="messageRegistration"></span>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <jsp:include page="common/footer.jsp"></jsp:include>
        <jsp:include page="common/scripts.jsp"></jsp:include>

        <!-- Here must be generated all the teams; Second parameter - logo of the team -->
        <script>
            $(function () {
                $('input.autocomplete.team-input').autocomplete({
                    data: {
                        "Manchester United": null,
                        "Dynamo Kyiv": null,
                        "Zoria": null,
                        "Arsenal": null,
                        "Bavaria": null,
                        "Tottenham": null,
                        "Real Madrid": null,
                        "Barcelona": null,
                        "Shakhtar": null
                    }
                });
            });
        </script>
        <!-- Here must be generated all the bookmakers; Second parameter - avatar -->
        <script>
            $(function () {
                $('input.autocomplete.bookmaker-input').autocomplete({
                    data: {
                        "Andrew Bright": null,
                        "Jim Carey": null,
                        "Adam Beast": null,
                        "Jessica Alba": null,
                        "Bald From Brazzers": null
                    }
                });
            });
        </script>

    </body>

    </html>