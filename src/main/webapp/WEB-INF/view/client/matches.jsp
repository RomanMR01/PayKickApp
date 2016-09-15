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
                <title>PayKick - Matches</title>

                <jsp:include page="common/styles.jsp"></jsp:include>
                <jsp:include page="common/scripts.jsp"></jsp:include>
            </head>

            <body>
                <jsp:include page="common/navigation.jsp"></jsp:include>

                <!-- Main Content -->
                <main class="valign-wrapper grey lighten-3">
                    <div class="container valign" style="margin-top: 20px;">
                        <h4 class="center-align">Matches:</h4>
                        <br>
                        <ul class="collapsible popout" data-collapsible="expandable">
                            <li>
                                <div class="collapsible-header center-align"><i class="material-icons green-text">list</i><span class="orange-text">Title.</span><span class="green-text"><strong>Barca - Real</strong></span></div>
                                <div class="collapsible-body center-align">
                                    <div class="row">
                                        <table class="centered responsive-table col s6 offset-s3">
                                            <thead>
                                                <tr>
                                                    <th>Location</th>
                                                    <th>Date</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>Lviv Arena</td>
                                                    <td>12.05.2015 16:40</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <div class="col s12 center-align my-disabled exclusive" id="game_1_result-bet-block">
                                            <hr>
                                            <br>
                                            <div class="switch my-green">
                                                <label>
                                                    <input type="checkbox" id="game_1_result-bet-checkbox" class="exclusive"> Not Bet <span class="lever"></span> Bet
                                                </label>
                                            </div>
                                            <br>
                                            <h5>Result</h5>
                                            <br>
                                            <table class="centered responsive-table col s10 offset-s1 green-hover">
                                                <thead>
                                                    <tr>
                                                        <th>1</th>
                                                        <th>2</th>
                                                        <th>X</th>
                                                        <th>1X</th>
                                                        <th>X2</th>
                                                        <th>12</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td class="coef">
                                                            3.56
                                                            <input name="game_1_result-bet-radio" type="radio" value="3.56" />
                                                        </td>

                                                        <td class="coef">
                                                            2.19
                                                            <input name="game_1_result-bet-radio" type="radio" value="2.19" />
                                                        </td>
                                                        <td class="coef">
                                                            2.12
                                                            <input name="game_1_result-bet-radio" type="radio" value="2.12" />
                                                        </td>
                                                        <td class="coef">
                                                            3.23
                                                            <input name="game_1_result-bet-radio" type="radio" value="3.23" />
                                                        </td>
                                                        <td class="coef">
                                                            1.16
                                                            <input name="game_1_result-bet-radio" type="radio" value="1.16" />
                                                        </td>
                                                        <td class="coef">
                                                            6.16
                                                            <input name="game_1_result-bet-radio" type="radio" value="6.16" />
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="col s12 center-align my-disabled exclusive" id="game_1_score-bet-block">
                                            <br>
                                            <hr>
                                            <br>
                                            <div class="switch my-green">
                                                <label>
                                                    <input type="checkbox" id="game_1_score-bet-checkbox" class="exclusive"> Not Bet <span class="lever"></span> Bet
                                                </label>
                                            </div>
                                            <br>
                                            <h5>Score</h5>
                                            <table class="centered responsive-table col s6 offset-s3 big-height">
                                                <thead>
                                                    <tr>
                                                        <th>Barca</th>
                                                        <th>Real</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>
                                                            <div class="input-field col s12">
                                                                <input id="game_1_score-bet_fid" type="number" min="0">
                                                                <label for="game_1_score-bet_fid">Score</label>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <div class="input-field col s12">
                                                                <input id="game_1_score-bet-sid" type="number" min="0">
                                                                <label for="game_1_score-bet_sid">Score</label>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <br>
                                        <br>
                                        <div class="col s12 center-align my-disabled exclusive" id="game_1_total-bet-block">
                                            <br>
                                            <hr>
                                            <br>
                                            <div class="switch my-green">
                                                <label>
                                                    <input type="checkbox" id="game_1_total-bet-checkbox"> Not Bet <span class="lever"></span> Bet
                                                </label>
                                            </div>
                                            <br>
                                            <h5>Total Goals</h5>
                                            <table class="centered responsive-table col s6 offset-s3 l4 offset-l4 big-height">
                                                <thead>
                                                    <tr>
                                                        <th>Goals Count</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>
                                                            <div class="input-field col s12">
                                                                <input id="game_1_total-bet-value" type="number" min="0">
                                                                <label for="game_1_total-bet-value">Count</label>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <br>
                                        <br>
                                        <div class="col s12 center-align my-disabled" id="game_1_players-bet-block">
                                            <hr>
                                            <br>
                                            <div class="switch my-green">
                                                <label>
                                                    <input type="checkbox" id="game_1_players-bet-checkbox"> Not Bet <span class="lever"></span> Bet
                                                </label>
                                            </div>
                                            <br>
                                            <h5>Players Coefficients:</h5>
                                            <br>
                                            <div class="row">
                                                <div class="col s6">
                                                    <h6><strong>Barcelona</strong></h6>
                                                    <table class="centered col s12 big-height">
                                                        <thead>
                                                            <tr>
                                                                <th>Player</th>
                                                                <th>Coefficient</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr>
                                                                <td>Richard Agueiro</td>
                                                                <td class="coef">
                                                                    3.31
                                                                    <input name="game_1_players-bet_16" type="checkbox" value="3.31" />
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>Salem Gonzalez</td>
                                                                <td class="coef">
                                                                    2.67
                                                                    <input name="game_1_players-bet_61" type="checkbox" value="2.67" />
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>Nicky Markus</td>
                                                                <td class="coef">
                                                                    7.13
                                                                    <input name="game_1_players-bet_22" type="checkbox" value="7.13" />
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>Christiano Ronaldo</td>
                                                                <td class="coef">
                                                                    4.42
                                                                    <input name="game_1_players-bet_7" type="checkbox" value="4.42" />
                                                                </td>

                                                            </tr>
                                                            <tr>
                                                                <td>Dilly Dong</td>
                                                                <td class="coef">
                                                                    2.98
                                                                    <input name="game_1_players-bet_19" type="checkbox" value="2.98" />
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div class="col s6">
                                                    <h6><strong>Real Madrid</strong></h6>
                                                    <table class="centered col s12 big-height">
                                                        <thead>
                                                            <tr>
                                                                <th>Player</th>
                                                                <th>Coefficient</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr>
                                                                <td>Richard Agueiro</td>
                                                                <td class="coef">
                                                                    3.31
                                                                    <input name="game_1_players-bet_24" type="checkbox" value="3.31" />
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>Salem Gonzalez</td>
                                                                <td class="coef">
                                                                    2.67
                                                                    <input name="game_1_players-bet_44" type="checkbox" value="2.67" />
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>Nicky Markus</td>
                                                                <td class="coef">
                                                                    7.13
                                                                    <input name="game_1_players-bet_161" type="checkbox" value="7.13" />
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>Christiano Ronaldo</td>
                                                                <td class="coef">
                                                                    4.42
                                                                    <input name="game_1_players-bet_168" type="checkbox" value="4.42" />
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>Dilly Dong</td>
                                                                <td class="coef">
                                                                    2.98
                                                                    <input name="game_1_players-bet_2" type="checkbox" value="2.98" />
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <a class="waves-effect waves-light btn green"><i class="material-icons right">done</i>Confirm</a>
                                    <br>
                                    <br>
                                </div>
                            </li>
                        </ul>
                        <ul class="pagination center-align">
                            <c:choose>
                                <c:when test="${page <= 1}">
                                    <li class="disabled"><a href="#!"><i
                            class="material-icons">chevron_left</i></a></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="material-icons"><a href="matches?type=${type}&page=${page-1}&itemsOnPage=${itemsOnPage}"><i
                            class="material-icons">chevron_left</i></a></li>
                                </c:otherwise>
                            </c:choose>

                            <c:forEach var="i" begin="1" end="${requestScope.pages}">

                                <c:choose>
                                    <c:when test="${i == page}">
                                        <li class="active green"><a href="#!">${i}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="waves-effect">
                                            <a href="matches?type=${type}&page=${i}&itemsOnPage=${itemsOnPage}">
                                                <c:out value="${i}"></c:out>
                                            </a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                            <c:choose>
                                <c:when test="${page == pages}">
                                    <li class="disabled"><a href="#!"><i
                            class="material-icons">chevron_right</i></a></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="material-icons"><a href="matches?type=${type}&page=${page+1}&itemsOnPage=${itemsOnPage}"><i
                            class="material-icons">chevron_right</i></a></li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                        <br>
                    </div>
                </main>

                <jsp:include page="common/footer.jsp"></jsp:include>
                <script>
                    (function () {
                        $("input[type=number]").prop("disabled", true);
                        $(".switch input[type=checkbox]").on("change", function () {
                            var temp = $(this);
                            var gameDiv = temp.parent().parent().parent().parent();
                            var betDiv = temp.parent().parent().parent();
                            if (betDiv.hasClass("exclusive")) {
                                if (temp.is(":checked")) {
                                    temp.parent().parent().parent().removeClass("my-disabled");
                                    $(".exclusive input[type=checkbox]", gameDiv).not(temp).prop("checked", false).parent().parent().parent().addClass("my-disabled");
                                    $("input[type=number]", gameDiv).prop("disabled", true);
                                    $("input[type=number]", betDiv).prop("disabled", false);
                                    if (/result-bet-block$/.test(betDiv.attr("id"))) {
                                        $("td.coef", betDiv).addClass("green-hover");
                                        $("td.coef.green-disabled", betDiv).addClass("green-active").removeClass("green-disabled");
                                    } else {
                                        $(".exclusive td.coef", gameDiv).removeClass("green-hover");
                                        $(".exclusive td.coef.green-active", gameDiv).addClass("green-disabled").removeClass("green-active");
                                    }

                                } else {
                                    if (/result-bet-block$/.test(betDiv.attr("id"))) {
                                        $("td.coef", betDiv).removeClass("green-hover");
                                        $("td.coef.green-active", betDiv).addClass("green-disabled").removeClass("green-active");
                                    }
                                    $("input[type=number]", betDiv).prop("disabled", true);
                                    temp.parent().parent().parent().addClass("my-disabled");

                                }
                            } else {
                                if (temp.is(":checked")) {
                                    temp.parent().parent().parent().removeClass("my-disabled");
                                    $("td.coef", betDiv).addClass("green-hover");
                                    $("td.coef.green-disabled", betDiv).addClass("green-active").removeClass("green-disabled");
                                } else {
                                    temp.parent().parent().parent().addClass("my-disabled");
                                    $("td.coef", betDiv).removeClass("green-hover");
                                    $("td.coef.green-active", betDiv).addClass("green-disabled").removeClass("green-active");
                                }
                            }


                        });
                    })();

                    (function () {
                        $("td.coef").on("click", function () {
                            var tdContext = $(this);
                            var trContext = tdContext.parent();
                            if (tdContext.hasClass("green-hover")) {
                                if (tdContext.hasClass("green-active")) {
                                    tdContext.removeClass("green-active");
                                    $("input[type=checkbox]", tdContext).prop("checked", false);
                                } else {
                                    tdContext.addClass("green-active");
                                    $("input[type=checkbox]", tdContext).prop("checked", true);
                                }
                                $("td.green-active", trContext).not(this).removeClass("green-active");
                                $("input[type=radio]", tdContext).prop("checked", true);
                            }

                        });
                    })();
                </script>
            </body>

            </html>