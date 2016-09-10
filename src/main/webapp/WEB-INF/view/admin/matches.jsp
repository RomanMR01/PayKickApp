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
    <title>PayKick. One bet - one hit!</title>

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
        <div class="row">
            <div class="col s12 l8 offset-l2">
                <ul class="tabs transparent">

                    <c:forTokens var="status" items="ALL,NEW,ACTIVE,PAST,CANCELED" delims=",">
                        <li class="tab col s4 l2">
                            <c:choose>
                                <c:when test="${status==type}">
                                    <a href="#" class="active">${status}</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="matches?type=${status}" target="_self">${status}</a>
                                </c:otherwise>
                            </c:choose>
                        </li>
                    </c:forTokens>
                </ul>
            </div>
        </div>
        <ul id="teamsPopouts" class="collapsible popout" data-collapsible="expandable">
            <c:forEach var="game" items="${games}">

                <li id="${game.id}">
                    <div class="collapsible-header center-align">
                        <c:choose>
                            <c:when test=""></c:when>
                            <c:when test=""></c:when>
                        </c:choose>
                        <c:choose>
                            <c:when test="${game.status=='NEW'}"><i class="material-icons orange-text">list</i></c:when>
                            <c:when test="${game.status=='ACTIVE'}"><i
                                    class="material-icons green-text">list</i></c:when>
                            <c:when test="${game.status=='FINISHED'}"><i
                                    class="material-icons grey-text">list</i></c:when>
                            <c:when test="${game.status=='CANCELED'}"><i
                                    class="material-icons red-text">list</i></c:when>
                            <c:otherwise></c:otherwise>
                        </c:choose>
                                        <span class="grey-text">
							<c:out value="${game.title}"></c:out>
							</span>
                        <c:choose>
                        <c:when test="${game.status=='NEW'}"><span class="orange-text"></c:when>
                        <c:when test="${game.status=='ACTIVE'}"><span class="green-text"></c:when>
                            <c:when test="${game.status=='FINISHED'}"><span class="grey-text"></c:when>
                                <c:when test="${game.status=='CANCELED'}"><span class="red-text"></c:when>
							<c:otherwise></c:otherwise>
							</c:choose>
							<strong>
							<c:out value="${game.firstTeam.name}"></c:out>
								- <c:out value="${game.secondTeam.name}"></c:out></strong></span>
                    </div>

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
                                    <td>
                                        <c:out value="${game.location}"></c:out>
                                    </td>
                                    <td>
                                        <fmt:formatDate type="both" dateStyle="short" timeStyle="short"
                                                        value="${game.date}"/>
                                    </td>
                                    <td>
                                        <c:out value="${game.bookmaker.login}"></c:out>
                                    </td>
                                    <c:choose>
                                        <c:when test="${game.profit > 0}">
                                            <td class="green-text">
                                                <c:out value="+ $${game.profit}"></c:out>
                                            </td>
                                        </c:when>
                                        <c:when test="${game.profit < 0}">
                                            <td class="red-text">
                                                <c:out value="- $${(-game.profit)}"></c:out>
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>
                                                <c:out value="$${game.profit}"></c:out>
                                            </td>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                                </tbody>
                            </table>
                            <div class="col s6 offset-s3">
                                <div class="row">
                                    <div class="input-field col s6">
                                        <input id="g_${game.id}_t_${game.firstTeam.id}" type="number" disabled
                                               value="0">
                                        <!-- Id must be not just a name of team because one team may have several active games at different dates -->
                                        <label for="g_${game.id}_t_${game.firstTeam.id}">
                                            <c:out value="${game.firstTeam.name}"></c:out>
                                        </label>
                                    </div>
                                    <div class="input-field col s6">
                                        <input id="g_${game.id}_t_${game.secondTeam.id}" type="number" disabled
                                               value="0">
                                        <!-- Id must be not just a name of team because one team may have several active games at different dates -->
                                        <label for="g_${game.id}_t_${game.secondTeam.id}">
                                            <c:out value="${game.secondTeam.name}"></c:out>
                                        </label>
                                    </div>
                                    <c:if test="${game.status=='ACTIVE'}">
                                        <div id="chip_${game.id}_${game.firstTeam.id}"
                                             class="chips chips-placeholder col s6">
                                        </div>
                                        <div id="chip_${game.id}_${game.secondTeam.id}"
                                             class="chips chips-placeholder col s6"></div>
                                    </c:if>

                                </div>
                            </div>
                        </div>
                        <c:if test="${game.status=='NEW'}">
                            <a class="waves-effect waves-light btn red" href="#" id="${game.id}"><i
                                    class="material-icons right">block</i>Cancel</a>
                            <br>
                            <br>
                        </c:if>

                        <c:if test="${game.status=='ACTIVE'}">
                            <a class="waves-effect waves-light btn red" href="#" id="${game.id}"><i
                                    class="material-icons right">block</i>Cancel</a>
                            <a class="waves-effect waves-light btn green darken-1" href="#"
                               id="g_${game.id}_ft_${game.firstTeam.id}_st_${game.secondTeam.id}"><i
                                    class="material-icons right">done</i>Confirm</a>
                            <br>
                            <br>
                        </c:if>

                    </div>
                </li>

            </c:forEach>
        </ul>
        <br> <a class="waves-effect waves-light modal-trigger btn right green" href="#new-match-modal"><i
            class="material-icons right">playlist_add</i>New
        Match</a>
        <br>
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

<!-- New Match Modal Structure -->
<div id="new-match-modal" class="modal my-narrow">
    <div class="modal-content">
        <div class="row">
            <h5 class="center-align">New Match</h5>
            <br>
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
                <br>
                <br>
                <br>
                <br>
                <div class="col s12 center-align">
                    <button class="btn waves-effect waves-light" id="addGameBtn">
                        Confirm <i class="material-icons right">done</i>
                    </button>
                    <br>
                    <br> <span class="center-align red-text" id="messageAddGame"></span>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="common/footer.jsp"></jsp:include>
<script>
    //Setting game results
    $("a.btn.green.darken-1").click(function () {

        var chipId = $(this).attr("id");
        var values = chipId.split("_");

        var gameID = values[1];
        var firstTeamID = values[3];
        var secondTeamID = values[5];

        var firstInputId = "input#g_" + gameID + "_t_" + firstTeamID;
        var secondInputId = "input#g_" + gameID + "_t_" + secondTeamID;

        var firstTeamScore = parseInt($(firstInputId).val());
        var secondTeamScore = parseInt($(secondInputId).val());

        var firstTeamPlayers = "";
        var secondTeamPlayers = "";

        $("#chip_" + gameID + "_" + firstTeamID).find(".chip").each(function () {
            var txt = $(this).text();
            firstTeamPlayers += txt.substring(0, txt.length - 5) + ";";
        });

        $("#chip_" + gameID + "_" + secondTeamID).find(".chip").each(function () {
            var txt = $(this).text();
            secondTeamPlayers += txt.substring(0, txt.length - 5) + ";";
        });

        $.ajax({
            type: "POST",
            url: "addWinner",
            data: {
                "gameID": gameID,
                "firstTeamID": firstTeamID,
                "secondTeamID": secondTeamID,
                "firstTeamScore": firstTeamScore,
                "secondTeamScore": secondTeamScore,
                "firstTeamPlayers": firstTeamPlayers,
                "secondTeamPlayers": secondTeamPlayers
            },
            success: function (data) {
                var response = JSON.parse(data);

                var status = response.status;
                var message = response.message;

                if (status == 'OK') {
                    Materialize.toast(message,5000);
                    $("li#"+gameID + "").hide();
                } else {
                    Materialize.toast(message,5000);
                }

            }
        });
    });

    //Canceling game
    $("a.btn.red").click(function () {

        var gameId = $(this).attr("id");
        //Canceling game

        $.ajax({
            type: "POST",
            url: "cancelGame",
            data: {
                "gameID": gameId
            },
            success: function (data) {
                var val = $("li a.active").text();//Active tab
                window.location = 'matches?type=' + val;//Reopen this tab
            }
        });
    });
</script>
<script>

    <%-- Generating teams names --%>
    $(function () {
        $('input.autocomplete.team-input').autocomplete({
            data: {
                <c:forEach var="team" items="${teams}">
                "${team.getName()}": null,
                </c:forEach>
            }
        });
    });

    <%-- Generating bookmakers names --%>
    $(function () {
        $('input.autocomplete.bookmaker-input').autocomplete({
            data: {
                <c:forEach var="bookmaker" items="${bookmakers}">
                "${bookmaker.getFullName()}": null,
                </c:forEach>
            }
        });
    });
</script>
<script>
    $(function () {

        <%--Generating players for autocomplete for evety team --%>
        <c:forEach var="game" items="${games2}">
        $('#chip_${game.getId()}_${game.getFirstTeam().getId()} input.autocomplete').autocomplete({
            data: {
                <c:forEach var="player" items="${game.getFirstTeam().getPlayers()}">
                "${player.getFulName()}": null,
                </c:forEach>
            }
        });

        $('#chip_${game.getId()}_${game.getSecondTeam().getId()} input.autocomplete').autocomplete({
            data: {
                <c:forEach var="player" items="${game.getSecondTeam().getPlayers()}">
                "${player.getFulName()}": null,
                </c:forEach>
            }
        });

        </c:forEach>

        $('.chips').on('chip.add', function (e, chip) {
            var chipId = $(this).attr("id").replace("chip_", "");
            var gameId = parseInt(chipId);
            var teamId = parseInt(chipId.replace(gameId + "_", ""));
            var inputId = "input#g_" + gameId + "_t_" + teamId;
            var value = parseInt($(inputId).val()) + 1;
            $(inputId).val(value);
        });
        $('.chips').on('chip.delete', function (e, chip) {
            var chipId = $(this).attr("id").replace("chip_", "");
            var gameId = parseInt(chipId);
            var teamId = parseInt(chipId.replace(gameId + "_", ""));
            var inputId = "input#g_" + gameId + "_t_" + teamId;
            var value = parseInt($(inputId).val()) - 1;
            $(inputId).val(value);
        });
    });

    //Add new game
    $('#addGameBtn').on('click', function (e) {
        var title = $('#title').val();
        var location = $('#location').val();
        var date = $('#date').val();
        var firstTeamName = $('#first-team').val();
        var secondTeamName = $('#second-team').val();
        var bookmaker = $('#bookmaker').val();

        $.ajax({
            type: "POST",
            url: "addGame",
            data: {
                "title": title,
                "location": location,
                "date": date,
                "firstTeamName": firstTeamName,
                "secondTeamName": secondTeamName,
                "bookmaker": bookmaker
            },
            success: function (data) {
                var messageAddGame = $("#messageAddGame");
                var response = JSON.parse(data);

                var status = response.status;
                var message = response.message;

                if (status == 'OK') {
                    $('#new-match-modal').closeModal();
                    window.location = 'matches?type=NEW';
                } else {
                    messageAddGame.text(message);
                }
            }
        });
    });
</script>

</body>

</html>