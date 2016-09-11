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
                <title>Client - Matches</title>

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
                                        <div class="col s12 center-align my-disabled" id="result-bet-block">
                                            <hr>
                                            <br>
                                            <div class="switch my-green">
                                                <label>
                                                    <input type="checkbox" id="result_checkbox"> Not Bet <span class="lever"></span> Bet
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
                                                        <td class="green-hover">3.56</td>
                                                        <td class="green-hover">2.19</td>
                                                        <td class="green-hover">2.12</td>
                                                        <td class="green-hover">3.23</td>
                                                        <td class="green-hover">1.16</td>
                                                        <td class="green-hover">6.16</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="col s12 center-align my-disabled" id="score-bet-block">
                                            <br>
                                            <hr>
                                            <br>
                                            <div class="switch my-green">
                                                <label>
                                                    <input type="checkbox" id="score_checkbox"> Not Bet <span class="lever"></span> Bet
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
                                                                <input id="barca_score" type="number">
                                                                <label for="barca_score">Score</label>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <div class="input-field col s12">
                                                                <input id="real_score" type="number">
                                                                <label for="real_score">Score</label>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <br>
                                        <br>
                                        <div class="col s12 center-align my-disabled" id="total-bet-block">
                                            <br>
                                            <hr>
                                            <br>
                                            <div class="switch my-green">
                                                <label>
                                                    <input type="checkbox" id="total_checkbox"> Not Bet <span class="lever"></span> Bet
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
                                                                <input id="total_goals" type="number">
                                                                <label for="total_goals">Count</label>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <br>
                                        <br>
                                        <div class="col s12 center-align my-disabled" id="players-bet-block">
                                            <hr>
                                            <br>
                                            <div class="switch my-green">
                                                <label>
                                                    <input type="checkbox" id="players_checkbox"> Not Bet <span class="lever"></span> Bet
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
                                                                <td class="green-hover">3.31</td>
                                                            </tr>
                                                            <tr>
                                                                <td>Salem Gonzalez</td>
                                                                <td class="green-hover">2.67</td>
                                                            </tr>
                                                            <tr>
                                                                <td>Nicky Markus</td>
                                                                <td class="green-hover">7.13</td>
                                                            </tr>
                                                            <tr>
                                                                <td>Christiano Ronaldo</td>
                                                                <td class="green-hover">4.42</td>
                                                            </tr>
                                                            <tr>
                                                                <td>Dilly Dong</td>
                                                                <td class="green-hover">2.98</td>
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
                                                                <td class="green-hover">3.31</td>
                                                            </tr>
                                                            <tr>
                                                                <td>Salem Gonzalez</td>
                                                                <td class="green-hover">2.67</td>
                                                            </tr>
                                                            <tr>
                                                                <td>Nicky Markus</td>
                                                                <td class="green-hover">7.13</td>
                                                            </tr>
                                                            <tr>
                                                                <td>Christiano Ronaldo</td>
                                                                <td class="green-hover">4.42</td>
                                                            </tr>
                                                            <tr>
                                                                <td>Dilly Dong</td>
                                                                <td class="green-hover">2.98</td>
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
                    var rbb = $("div#result-bet-block");
                    var sbb = $("div#score-bet-block");
                    var tbb = $("div#total-bet-block");
                    var pbb = $("div#players-bet-block");

                    var rbb_td = $("div#result-bet-block td.green-hover");
                    var pbb_td = $("div#players-bet-block td.green-hover");

                    var sbb_input = $("div#score-bet-block input[type=number]");
                    var tbb_input = $("div#total-bet-block input[type=number]");

                    var rbb_available = false;
                    var pbb_available = false;

                    $(".my-disabled input[type=number]").prop("disabled", true);

                    $("td.green-hover").removeClass("green-hover");


                    $("input#result_checkbox").on("change", function () {
                        if ($(this).is(":checked")) {
                            rbb.removeClass("my-disabled");
                            rbb_td.addClass("green-hover");
                            rbb_available = true;
                            $("td.green-disabled").removeClass("green-disabled").addClass("green-active");
                            $("td.green-hover").off().on("click", function () {
                                if (rbb_available) {
                                    if ($(this).hasClass("green-active")) {
                                        $(this).removeClass("green-active");
                                    } else {
                                        $(this).addClass("green-active");
                                    }
                                }
                            });
                        } else {
                            rbb.addClass("my-disabled");
                            rbb_td.removeClass("green-hover");
                            rbb_available = false;
                            $("td.green-active").removeClass("green-active").addClass("green-disabled");
                        }
                    });

                    $("input#score_checkbox").on("change", function () {
                        if ($(this).is(":checked")) {
                            sbb.removeClass("my-disabled");
                            sbb_input.prop("disabled", false);
                        } else {
                            sbb.addClass("my-disabled");
                            sbb_input.prop("disabled", true);
                        }
                    });

                    $("input#total_checkbox").on("change", function () {
                        if ($(this).is(":checked")) {
                            tbb.removeClass("my-disabled");
                            tbb_input.prop("disabled", false);
                        } else {
                            tbb.addClass("my-disabled");
                            tbb_input.prop("disabled", true);
                        }
                    });

                    $("input#players_checkbox").on("change", function () {
                        if ($(this).is(":checked")) {
                            pbb.removeClass("my-disabled");
                            pbb_td.addClass("green-hover");
                            pbb_available = true;
                            $("td.green-disabled").removeClass("green-disabled").addClass("green-active");
                            $("td.green-hover").off().on("click", function () {
                                if (pbb_available) {
                                    if ($(this).hasClass("green-active")) {
                                        $(this).removeClass("green-active");
                                    } else {
                                        $(this).addClass("green-active");
                                    }
                                }
                            });

                        } else {
                            pbb.addClass("my-disabled");
                            pbb_td.removeClass("green-hover");
                            $("td.green-active").removeClass("green-active").addClass("green-disabled");
                            pbb_available = false;
                        }
                    });
                </script>
            </body>

            </html>