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
                        <div class="row">
                            <div class="col s9">
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
                                                <div class="col s12 center-align my-disabled exclusive" id="game_2_result-bet-block">
                                                    <hr>
                                                    <br>
                                                    <div class="switch my-green">
                                                        <label>
                                                            <input type="checkbox" id="game_2_result-bet-checkbox" class="exclusive"> Not Bet <span class="lever"></span> Bet
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
                                                                    <input name="game_2_result-bet-radio" type="radio" value="3.56" />
                                                                </td>

                                                                <td class="coef">
                                                                    2.19
                                                                    <input name="game_2_result-bet-radio" type="radio" value="2.19" />
                                                                </td>
                                                                <td class="coef">
                                                                    2.12
                                                                    <input name="game_2_result-bet-radio" type="radio" value="2.12" />
                                                                </td>
                                                                <td class="coef">
                                                                    3.23
                                                                    <input name="game_2_result-bet-radio" type="radio" value="3.23" />
                                                                </td>
                                                                <td class="coef">
                                                                    1.16
                                                                    <input name="game_2_result-bet-radio" type="radio" value="1.16" />
                                                                </td>
                                                                <td class="coef">
                                                                    6.16
                                                                    <input name="game_2_result-bet-radio" type="radio" value="6.16" />
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div class="col s12 center-align my-disabled exclusive" id="game_2_score-bet-block">
                                                    <br>
                                                    <hr>
                                                    <br>
                                                    <div class="switch my-green">
                                                        <label>
                                                            <input type="checkbox" id="game_2_score-bet-checkbox" class="exclusive"> Not Bet <span class="lever"></span> Bet
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
                                                                        <input id="game_2_score-bet_fid" type="number" min="0" value="0">
                                                                        <label for="game_2_score-bet_fid">Score</label>
                                                                        <input type="hidden" id="game_2_score-bet_first-team-coefficient" value="3.15">
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <div class="input-field col s12">
                                                                        <input id="game_2_score-bet_sid" type="number" min="0" value="0">
                                                                        <label for="game_2_score-bet_sid">Score</label>
                                                                        <input type="hidden" id="game_2_score-bet_second-team-coefficient" value="3.15">
                                                                    </div>
                                                                </td>
                                                                <input type="hidden" id="game_2_score-bet_start-coefficient" value="3.19">
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
                                                            <input type="checkbox" id="game_2_total-bet-checkbox"> Not Bet <span class="lever"></span> Bet
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
                                                                        <input id="game_2_total-bet-value" type="number" min="0" value="0">
                                                                        <label for="game_2_total-bet-value">Count</label>
                                                                        <input type="hidden" id="game_2_total-bet-coefficient" value="3.15">
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <br>
                                                <br>
                                                <div class="col s12 center-align my-disabled" id="game_2_players-bet-block">
                                                    <hr>
                                                    <br>
                                                    <div class="switch my-green">
                                                        <label>
                                                            <input type="checkbox" id="game_2_players-bet-checkbox"> Not Bet <span class="lever"></span> Bet
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
                                                                            <input name="game_2_players-bet_16" type="checkbox" value="3.31" />
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>Salem Gonzalez</td>
                                                                        <td class="coef">
                                                                            2.67
                                                                            <input name="game_2_players-bet_61" type="checkbox" value="2.67" />
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>Nicky Markus</td>
                                                                        <td class="coef">
                                                                            7.13
                                                                            <input name="game_2_players-bet_22" type="checkbox" value="7.13" />
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>Christiano Ronaldo</td>
                                                                        <td class="coef">
                                                                            4.42
                                                                            <input name="game_2_players-bet_7" type="checkbox" value="4.42" />
                                                                        </td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td>Dilly Dong</td>
                                                                        <td class="coef">
                                                                            2.98
                                                                            <input name="game_2_players-bet_19" type="checkbox" value="2.98" />
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
                                                                            <input name="game_2_players-bet_24" type="checkbox" value="3.31" />
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>Salem Gonzalez</td>
                                                                        <td class="coef">
                                                                            2.67
                                                                            <input name="game_2_players-bet_44" type="checkbox" value="2.67" />
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>Nicky Markus</td>
                                                                        <td class="coef">
                                                                            7.13
                                                                            <input name="game_2_players-bet_161" type="checkbox" value="7.13" />
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>Christiano Ronaldo</td>
                                                                        <td class="coef">
                                                                            4.42
                                                                            <input name="game_2_players-bet_168" type="checkbox" value="4.42" />
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>Dilly Dong</td>
                                                                        <td class="coef">
                                                                            2.98
                                                                            <input name="game_2_players-bet_2" type="checkbox" value="2.98" />
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
                                <br>
                            </div>
                            <div class="col s3 white orange-text center-align">
                                <div class="toc-wrapper">
                                    <br>
                                    <h5>Total Bet:</h5>
                                    <br>
                                    <br>
                                    <h6><span class="black-text">Bets:</span> <strong><span id="bets-count">0</span></strong></h6>
                                    <br>
                                    <h6><span class="black-text">Total Coefficient:</span> <strong><span id="total-coef">1.00</span></strong></h6>
                                    <div class="input-field">
                                        <input id="amount" type="number" min="0" value="0.00" step="0.01">
                                        <label for="amount">Amount</label>
                                    </div>
                                    <h6><span class="black-text">Award: </span> <strong>$<span id="award">0.00</span></strong></h6>
                                    <br>
                                    <a class="waves-effect waves-light btn green"><i class="material-icons right">done</i>Confirm</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>

                <jsp:include page="common/footer.jsp"></jsp:include>
                <script>
                    var totalCof = 1.00;

                    (function () {
                        $("input[type=number]").not("input#amount").prop("disabled", true);
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
                            countTotal();

                        });
                    })();

                    (function () {
                        $("td.coef").on("click", function () {
                            var tdContext = $(this);
                            var trContext = tdContext.parent();
                            if (tdContext.hasClass("green-hover")) {
                                if (tdContext.hasClass("green-active")) {
                                    tdContext.removeClass("green-active");
                                    $("input", tdContext).prop("checked", false);
                                } else {
                                    tdContext.addClass("green-active");
                                    $("input", tdContext).prop("checked", true);
                                }
                                $("td.green-active", trContext).not(this).removeClass("green-active");
                                countTotal();
                            }

                        });
                    })();

                    (function () {
                        $("input[type=number]:not(#amount)").on("input", function () {
                            countTotal();
                        });
                    })();

                    (function () {
                        $("input#amount").on("input", function () {
                            countAward();
                        });
                    })();

                    var countAward = function () {
                        var award = totalCof.toFixed(2) * parseFloat($("input#amount").val()).toFixed(2);
                        $("span#award").text(award.toFixed(2));
                    };

                    var countTotal = function () {
                        totalCof = 1.00;
                        var betsCount = 0;

                        $("div[id$=bet-block]:not(.my-disabled)").each(function () {
                            if (/result-bet-block$/.test($(this).attr("id"))) {
                                $("td.green-active input", $(this)).each(function () {
                                    betsCount++;
                                    totalCof *= parseFloat($(this).val());
                                });
                            }

                            if (/score-bet-block$/.test($(this).attr("id"))) {
                                betsCount++;

                                var scoreBetStartCoef = parseFloat($("input[id$=score-bet_start-coefficient]", $(this)).val());
                                var firstTeamGoalCoef = parseFloat($("input[id$=score-bet_first-team-coefficient]", $(this)).val());
                                var secondTeamGoalCoef = parseFloat($("input[id$=score-bet_second-team-coefficient]", $(this)).val());

                                var firstTeamScore = parseInt($("input[id$=score-bet_fid]", $(this)).val());
                                var secondTeamScore = parseInt($("input[id$=score-bet_sid]", $(this)).val());

                                var scoreBlockCoef = scoreBetStartCoef * Math.pow(firstTeamGoalCoef, firstTeamScore) * Math.pow(secondTeamGoalCoef, secondTeamScore);

                                totalCof *= scoreBlockCoef;
                            }

                            if (/total-bet-block$/.test($(this).attr("id"))) {
                                betsCount++;

                                var totalBetStartCoef = parseFloat($("input[id$=total-bet-coefficient]", $(this)).val());
                                var totalGoalsCount = parseInt($("input[id$=total-bet-value]", $(this)).val());

                                var totalBlockCoef = Math.pow(totalBetStartCoef, (totalGoalsCount + 1));

                                totalCof *= totalBlockCoef;
                            }

                            if (/players-bet-block$/.test($(this).attr("id"))) {
                                $("td.green-active input", $(this)).each(function () {
                                    betsCount++;
                                    totalCof *= parseFloat($(this).val());
                                });
                            }

                        });

                        if (isNaN(totalCof)) {
                            totalCof = 1.00;
                        }

                        $("span#bets-count").text(betsCount);
                        $("span#total-coef").text(totalCof.toFixed(2));
                        countAward();
                    }
                </script>
                <script>
                    $(document).ready(function () {
                        $('.toc-wrapper').pushpin({
                            top: 0,
                            offset: 70
                        });
                    });
                </script>
            </body>

            </html>