<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                    <c:forEach var="game" items="${games}">
                        <li>
                            <div class="collapsible-header center-align"><i
                                    class="material-icons green-text">list</i><span class="orange-text"><c:out
                                    value="${game.title}"/>.</span><span class="green-text"><strong><c:out
                                    value="${game.firstTeam.name}"/> - <c:out value="${game.secondTeam.name}"/></strong></span>
                            </div>
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
                                            <td><c:out value="${game.location}"/></td>
                                            <td><fmt:formatDate type="both" dateStyle="short"
                                                                timeStyle="short" value="${game.date}"/></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                    <div class="col s12 center-align my-disabled exclusive"
                                         id="game_${game.id}_result-bet-block">
                                        <hr>
                                        <br>
                                        <div class="switch my-green">
                                            <label>
                                                <input type="checkbox" id="game_${game.id}_result-bet-checkbox"
                                                       class="exclusive"> Not Bet <span class="lever"></span> Bet
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
                                                        ${game.resultCoefficients[0].coefficient}
                                                    <input name="game_${game.id}_result-bet-radio" class="C1"
                                                           type="radio"
                                                           value="${game.resultCoefficients[0].coefficient}"/>
                                                </td>
                                                <td class="coef">
                                                        ${game.resultCoefficients[1].coefficient}
                                                    <input name="game_${game.id}_result-bet-radio" class="C2"
                                                           type="radio"
                                                           value="${game.resultCoefficients[1].coefficient}"/>
                                                </td>
                                                <td class="coef">
                                                        ${game.resultCoefficients[2].coefficient}
                                                    <input name="game_${game.id}_result-bet-radio" class="CX"
                                                           type="radio"
                                                           value="${game.resultCoefficients[2].coefficient}"/>
                                                </td>
                                                <td class="coef">
                                                        ${game.resultCoefficients[3].coefficient}
                                                    <input name="game_${game.id}_result-bet-radio" class="C1X"
                                                           type="radio"
                                                           value="${game.resultCoefficients[3].coefficient}"/>
                                                </td>
                                                <td class="coef">
                                                        ${game.resultCoefficients[4].coefficient}
                                                    <input name="game_${game.id}_result-bet-radio" class="CX2"
                                                           type="radio"
                                                           value="${game.resultCoefficients[4].coefficient}"/>
                                                </td>
                                                <td class="coef">
                                                        ${game.resultCoefficients[5].coefficient}
                                                    <input name="game_${game.id}_result-bet-radio" class="C12"
                                                           type="radio"
                                                           value="${game.resultCoefficients[5].coefficient}"/>
                                                </td>

                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="col s12 center-align my-disabled exclusive"
                                         id="game_${game.id}_score-bet-block">
                                        <br>
                                        <hr>
                                        <br>
                                        <div class="switch my-green">
                                            <label>
                                                <input type="checkbox" id="game_${game.id}_score-bet-checkbox"
                                                       class="exclusive"> Not Bet <span class="lever"></span> Bet
                                            </label>
                                        </div>
                                        <br>
                                        <h5>Score</h5>
                                        <table class="centered responsive-table col s6 offset-s3 big-height">
                                            <thead>
                                            <tr>
                                                <th><c:out value="${game.firstTeam.name}"/></th>
                                                <th><c:out value="${game.secondTeam.name}"/></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <td>
                                                    <div class="input-field col s12">
                                                        <input id="game_${game.id}_score-bet_fid_${game.firstTeam.id}"
                                                               type="number" min="0" value="0">
                                                        <label for="game_${game.id}_score-bet_fid_${game.firstTeam.id}">Score</label>
                                                        <input type="hidden" id="game_${game.id}_first-team-coef"
                                                               value="${game.scoreCoefficient.firstTeamCoefficient}">
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="input-field col s12">
                                                        <input id="game_${game.id}_score-bet_sid_${game.secondTeam.id}"
                                                               type="number" min="0" value="0">
                                                        <label for="game_${game.id}_score-bet_sid_${game.secondTeam.id}">Score</label>
                                                        <input type="hidden" id="game_${game.id}_second-team-coef"
                                                               value="${game.scoreCoefficient.secondTeamCoefficient}">
                                                    </div>
                                                </td>
                                                <input type="hidden" id="game_${game.id}_start-coef"
                                                       value="${game.scoreCoefficient.startCoefficient}">
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <br>
                                    <br>
                                    <div class="col s12 center-align my-disabled exclusive"
                                         id="game_${game.id}_total-bet-block">
                                        <br>
                                        <hr>
                                        <br>
                                        <div class="switch my-green">
                                            <label>
                                                <input type="checkbox" id="game_${game.id}_total-bet-checkbox"> Not Bet
                                                <span class="lever"></span> Bet
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
                                                        <input id="game_${game.id}_total-bet-value" type="number"
                                                               min="0" value="0">
                                                        <label for="game_${game.id}_total-bet-value">Count</label>
                                                        <input type="hidden" id="game_${game.id}_goal-coef"
                                                               value="${game.scoreCoefficient.startCoefficient}">
                                                    </div>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <br>
                                    <br>
                                    <div class="col s12 center-align my-disabled"
                                         id="game_${game.id}_players-bet-block">
                                        <hr>
                                        <br>
                                        <div class="switch my-green">
                                            <label>
                                                <input type="checkbox" id="game_${game.id}_players-bet-checkbox"> Not
                                                Bet <span class="lever"></span> Bet
                                            </label>
                                        </div>
                                        <br>
                                        <h5>Players Coefficients:</h5>
                                        <br>
                                        <div class="row">
                                            <div class="col s6">
                                                <h6><strong><c:out value="${game.firstTeam.name}"/></strong></h6>
                                                <table class="centered col s12 big-height" id="firstTeamPlayers">
                                                    <thead>
                                                    <tr>
                                                        <th>Player</th>
                                                        <th>Coefficient</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach var="playerCoefficient"
                                                               items="${game.firstTeamPlayerCoefficients}">
                                                        <tr>
                                                            <td>${playerCoefficient.player.fulName}</td>
                                                            <td class="coef">
                                                                    ${playerCoefficient.coefficient}
                                                                <input name="game_${game.id}_players-bet_${playerCoefficient.player.id}"
                                                                       type="checkbox"
                                                                       value="${playerCoefficient.coefficient}"/>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="col s6">
                                                <h6><strong><c:out value="${game.secondTeam.name}"/></strong></h6>
                                                <table class="centered col s12 big-height" id="secondTeamPlayers">
                                                    <thead>
                                                    <tr>
                                                        <th>Player</th>
                                                        <th>Coefficient</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach var="playerCoefficient"
                                                               items="${game.secondTeamPlayerCoefficients}">
                                                        <tr>
                                                            <td>${playerCoefficient.player.fulName}</td>
                                                            <td class="coef">
                                                                    ${playerCoefficient.coefficient}
                                                                <input name="game_${game.id}_players-bet_${playerCoefficient.player.id}"
                                                                       type="checkbox"
                                                                       value="${playerCoefficient.coefficient}"/>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
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
                    <h6><span class="black-text">Total Coefficient:</span> <strong><span
                            id="total-coef">1.00</span></strong></h6>
                    <div class="input-field">
                        <input id="amount" type="number" min="0" value="0" step="1">
                        <label for="amount">Amount</label>
                    </div>
                    <h6><span class="black-text">Award: </span> <strong>$<span id="award">0.00</span></strong></h6>
                    <br>
                    <a class="waves-effect waves-light btn green" id="confirmBets"><i
                            class="material-icons right">done</i>Confirm</a>
                </div>
            </div>
        </div>
    </div>
</main>

<jsp:include page="common/footer.jsp"></jsp:include>
<script>
    /* FOR GENERATING REQUEST OBJECT */
    $("#confirmBets").click(function () {
        var requestObject = [];

        //Generated data
        var allGamesIDs = ${allGamesIDs};
        var firstTeamsIDs = ${firstTeamsIDs};
        var secondTeamsIDs = ${secondTeamsIDs};

        var firstTeamsPlayers = ${firstTeamsPlayers};
        var secondTeamsPlayers = ${secondTeamsPlayers};

        var betsCount = new Number($('#bets-count').text());
        if(betsCount<=0){
            Materialize.toast("Select some bet first!",5000);
            return;
        }else if(betsCount>10){
            Materialize.toast("Too much!",5000);
            return;
        }
        var amount = new Number($('#amount').val());
        var award = new Number($('#award').text());

        if(amount<=0){
            Materialize.toast("Put some money first!",5000);
            return;
        }

        for (var currentGameNumber = 0; currentGameNumber < allGamesIDs.length; currentGameNumber++) {
            var gameId = allGamesIDs[currentGameNumber];

            var firstTeamPlayersId = firstTeamsPlayers[currentGameNumber];
            var secondTeamPlayersId = secondTeamsPlayers[currentGameNumber];

            var firstTeamId = firstTeamsIDs[currentGameNumber];
            var secondTeamId = secondTeamsIDs[currentGameNumber];

            /* Creating new Game object with start parameters */
            var Game = {};
            Game.gameId = gameId;
            Game.firstTeamId = firstTeamId;
            Game.secondTeamId = secondTeamId;

            /*Result bet*/
            //Get checked result bets
            var betResultSwitch = $("#game_" + gameId + "_result-bet-checkbox");
            if (betResultSwitch.is(":checked")) {
                //Get class and value of checked result bet
                var betResultCoef = $("#game_" + gameId + "_result-bet-block .coef input[name=game_" + gameId + "_result-bet-radio]:checked");
                //If one value selected (not undefined)
                if (betResultCoef.val()) {
                    var betResultType = betResultCoef.attr("class");
                    var coefficient = new Number(betResultCoef.val());

                    /* betResult property represents Single bet on result of game */
                    Game.betResult = {"betResultType": betResultType, "coefficient": coefficient};
                }
                else {
                    Materialize.toast("select value first",5000);
                    return;
                }
            }
            /* Score bet */
            var betScoreSwitch = $("#game_" + gameId + "_score-bet-checkbox");
            if (betScoreSwitch.is(":checked")) {
                var firsTeamScoreSelector = $("#game_" + gameId + "_score-bet_fid_" + firstTeamId);
                var firsTeamCoefSelector = $("#game_" + gameId + "_first-team-coef");
                var secondTeamScoreSelector = $("#game_" + gameId + "_score-bet_sid_" + secondTeamId);
                var secondTeamCoefSelector = $("#game_" + gameId + "_second-team-coef");
                var startCoefSelector = $("#game_" + gameId + "_start-coef");
                if (firsTeamScoreSelector.val() == "" || secondTeamScoreSelector.val() == "") {
                    Materialize.toast("Can't be empty score!",5000);
                    return;
                }
                if (firsTeamScoreSelector.val() < 0 || firsTeamScoreSelector.val() > 100 || secondTeamScoreSelector.val() < 0 || secondTeamScoreSelector.val() > 100) {
                    Materialize.toast("Incorrect values!",5000);
                    return;
                }

                var startCoefficient = new Number(startCoefSelector.val());
                var firstTeamScore = new Number(firsTeamScoreSelector.val());
                var secondTeamScore = new Number(secondTeamScoreSelector.val());
                /* betScore property represents Single bet on teams score  */
                Game.betScore = {
                    "startCoefficient": startCoefficient,
                    "firstTeamScore": firstTeamScore,
                    "secondTeamScore": secondTeamScore
                };
            }
            /* Total goals bet*/
            var betTotalGoalsSwitch = $("#game_" + gameId + "_total-bet-checkbox");
            if (betTotalGoalsSwitch.is(":checked")) {
                var totalGoalsSelector = $("#game_" + gameId + "_total-bet-value");
                var totalGoalsCoefSelector = $("#game_" + gameId + "_goal-coef");
                if (totalGoalsSelector.val() == "") {
                    Materialize.toast("Can't be empty goals!",5000);
                    return;
                }
                if (totalGoalsSelector.val() < 0 || totalGoalsSelector.val() > 100) {
                    Materialize.toast("Incorrect values!",5000);
                    return;
                }

                var totalGoalsValue = new Number(totalGoalsSelector.val());
                var totalGoalsCoefficient = new Number(totalGoalsCoefSelector.val());

                /* betTotalGoals property represents Single bet on sum of goals (total goals)  */
                Game.betTotalGoals = {
                    "totalGoalsValue": totalGoalsValue,
                    "totalGoalsCoefficient": totalGoalsCoefficient
                };
            }

            //For storing bets on players
            Game.betPlayer = [];
            /* Players bet */
            var betPlayersSwitch = $("#game_" + gameId + "_players-bet-checkbox");
            if (betPlayersSwitch.is(":checked")) {
                //Selecting players from first team
                var firstTeamSelector = $("#game_" + gameId + "_players-bet-block #firstTeamPlayers .coef");
                var firstTeamPlayerNum = 0;
                firstTeamSelector.each(function () {
                    var checkedPlayersSelector = $("input[name=game_" + gameId + "_players-bet_" + firstTeamPlayersId[firstTeamPlayerNum++] + "]:checked");
                    if (checkedPlayersSelector.val()) {
                        var playerId = firstTeamPlayersId[firstTeamPlayerNum];
                        var playerCoefficient = checkedPlayersSelector.val();

                        /* betPlayer property represents Single bet on some player  */
                        var player = {"playerId": playerId, "playerCoefficient": playerCoefficient};
                        Game.betPlayer.push(player);
                    }
                });

                //Selecting players from second team
                var secondTeamSelector = $("#game_" + gameId + "_players-bet-block #secondTeamPlayers .coef");
                var secondTeamPlayerNum = 0;
                secondTeamSelector.each(function () {
                    var checkedPlayersSelector = $("input[name=game_" + gameId + "_players-bet_" + secondTeamPlayersId[secondTeamPlayerNum++] + "]:checked");

                    if (checkedPlayersSelector.val()) {
                        var playerId = secondTeamPlayersId[secondTeamPlayerNum];
                        var playerCoefficient = checkedPlayersSelector.val();

                        /* betPlayer property represents Single bet on some player  */
                        var player = {"playerId": playerId, "playerCoefficient": playerCoefficient};
                        Game.betPlayer.push(player);
                    }
                });
            }
            requestObject.push(Game);//Adding new game object to array
        }

        var outputArray = JSON.parse(JSON.stringify(requestObject))

        $.ajax({
            type: "POST",
            url: "matches",
            data: {
                "requestJsonArray":JSON.stringify(outputArray),
                "betsCount":betsCount,
                "amount":amount,
                "award":award
            },
            success: function (data) {
                var response = JSON.parse(data);
                var status = response.status;
                var message = response.message;

                if (status == 'OK') {
                   window.location = 'matches';
                } else {
                    Materialize.toast(message,5000);
                }
            }
        });
    });
</script>
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

                var scoreBetStartCoef = parseFloat($("input[id$=start-coef]", $(this)).val());
                var firstTeamGoalCoef = parseFloat($("input[id$=first-team-coef]", $(this)).val());
                var secondTeamGoalCoef = parseFloat($("input[id$=second-team-coef]", $(this)).val());

                var firstTeamScore = parseInt($("input[id*=score-bet_fid]", $(this)).val());
                var secondTeamScore = parseInt($("input[id*=score-bet_sid]", $(this)).val());

                var scoreBlockCoef = scoreBetStartCoef * Math.pow(firstTeamGoalCoef, firstTeamScore) * Math.pow(secondTeamGoalCoef, secondTeamScore);

                totalCof *= scoreBlockCoef;
            }

            if (/total-bet-block$/.test($(this).attr("id"))) {
                betsCount++;

                var totalBetStartCoef = parseFloat($("input[id$=goal-coef]", $(this)).val());
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