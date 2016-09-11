<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>

<head>

    <!-- Meta -->
    <meta charset="utf-8">

    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Manage Teams</title>

    <jsp:include page="common/styles.jsp"></jsp:include>
    <jsp:include page="common/scripts.jsp"></jsp:include>
</head>

<body>
<jsp:include page="common/navigation.jsp"></jsp:include>

<!-- Main Content -->
<main class="valign-wrapper grey lighten-3">
    <div class="container valign" style="margin-top: 20px;">
        <h4 class="center-align">Teams:</h4>
        <br>
        <ul class="collapsible popout" data-collapsible="expandable" id="allTeams">
            <c:forEach var="team" items="${teams}">
                <li id="${team.id}">
                    <div class="collapsible-header center-align" id="header_${team.id}"><i
                            class="material-icons green-text">group</i><span class="green-text"><strong><c:out
                            value="${team.name}"></c:out></strong></span></div>
                    <div class="collapsible-body center-align">
                        <div class="row">
                            <br>
                            <ul class="collection col s10 offset-s1" id="playersInTeam_${team.id}">

                                <c:forEach var="player" items="${team.players}">
                                    <li id="player_id_${player.id}" class="collection-item player"><i
                                            class="material-icons left green-text">person</i><c:out
                                            value="${player.fulName}"></c:out><a id="player_id_${player.id}" href="#"><i
                                            class="material-icons right red-text">clear</i></a></li>
                                </c:forEach>
                                <!--                                     <li id="lionel-messi-container" class="collection-item player"><i class="material-icons left green-text">person</i>Lionel Messi<a id="lionel-messi" href="#"><i class="material-icons right red-text">clear</i></a></li> -->
                                <!--                                     <li id="machen-drachen-container" class="collection-item player"><i class="material-icons left green-text">person</i>Machen Drachen<a id="machen-drachen" href="#"><i class="material-icons right red-text">clear</i></a></li> -->
                                <!--                                     <li id="arsen-nikinak-container" class="collection-item player"><i class="material-icons left green-text">person</i>Arsen Nikinak<a id="arsen-nikinak" href="#"><i class="material-icons right red-text">clear</i></a></li> -->
                                <!--                                     <li id="bitte-dritte-container" class="collection-item player"><i class="material-icons left green-text">person</i>Bitte Dritte<a id="bitte-dritte" href="#"><i class="material-icons right red-text">clear</i></a></li> -->

                            </ul>

                        </div>
                        <a class="waves-effect waves-light btn modal-trigger green darken-1" href="#add-player-modal"
                           target="_self" id="addPlayer_team_${team.id}"><i class="material-icons right">person_add</i>Add
                            Player</a>
                        <br>
                        <br>
                    </div>
                </li>
            </c:forEach>
        </ul>
        <br>
        <a class="waves-effect waves-light modal-trigger btn right green darken-2" href="#new-team-modal"><i
                class="material-icons right">group_add</i>New Team</a>
        <br>
        <ul class="pagination center-align">
            <c:choose>
                <c:when test="${page <= 1}">
                    <li class="disabled"><a href="#!"><i
                            class="material-icons">chevron_left</i></a></li>
                </c:when>
                <c:otherwise>
                    <li class="material-icons"><a
                            href="teams?page=${page-1}&itemsOnPage=${itemsOnPage}"><i
                            class="material-icons">chevron_left</i></a></li>
                </c:otherwise>
            </c:choose>

            <c:forEach var="i" begin="1" end="${requestScope.pages}">

                <c:choose>
                    <c:when test="${i == page}">
                        <li class="active green"><a href="#!">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="waves-effect"><a
                                href="teams?page=${i}&itemsOnPage=${itemsOnPage}"><c:out
                                value="${i}"></c:out></a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:choose>
                <c:when test="${page >= pages}">
                    <li class="disabled"><a href="#!"><i
                            class="material-icons">chevron_right</i></a></li>
                </c:when>
                <c:otherwise>
                    <li class="material-icons"><a
                            href="teams?page=${page+1}&itemsOnPage=${itemsOnPage}"><i
                            class="material-icons">chevron_right</i></a></li>
                </c:otherwise>
            </c:choose>

        </ul>
        <br>
    </div>
</main>

<!-- Add Player Modal Structure -->
<div id="add-player-modal" class="modal my-narrow">
    <div class="modal-content">
        <div class="row">
            <div class="col s12">
                <h5 class="center-align">Add Player</h5>
                <br>
                <ul class="tabs transparent">
                    <li class="tab col s6"><a href="#new-player-form" class="active">New</a></li>
                    <li class="tab col s6"><a href="#existing-player-form">Existing</a></li>
                </ul>
            </div>
            <div id="new-player-form" class="col s12">
                <div class="row">
                    <div class="input-field col s12">
                        <input id="fname" type="text">
                        <label for="fname">First Name</label>
                    </div>
                    <div class="input-field col s12">
                        <input id="surname" type="text">
                        <label for="surname">Surname</label>
                    </div>
                    <div class="col s12">
                        <p class="big">Age:</p>
                        <p class="range-field">
                            <input type="range" id="age" min="0" max="100" value="0"/>
                        </p>
                    </div>
                </div>
                <div class="col s12 center-align">
                    <button class="btn waves-effect waves-light"  id="addNewPlayer">Add
                        Player
                        <i class="material-icons right">person_add</i>
                    </button>
                    <br>
                    <br>
                    <span class="center-align red-text" id="newPlayerFormMessage"></span>
                </div>
            </div>
            <div id="existing-player-form" class="col s12">
                <div class="row">
                    <div class="input-field col s12">
                        <input type="text" id="existing-player" class="autocomplete player-input" autocomplete="off">
                        <label for="existing-player">Player</label>
                    </div>
                </div>
                <div class="col s12 center-align">
                    <button class="btn waves-effect waves-light" id="addExistingPlayer">Add Player
                        <i class="material-icons right">person_add</i>
                    </button>
                    <br>
                    <br>
                    <span class="center-align red-text" id="existingPlayerFormMessage"></span>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- New Team Modal Structure -->
<div id="new-team-modal" class="modal my-narrow">
    <div class="modal-content">
        <div class="row">
            <h5 class="center-align">New Team</h5>
            <br>
            <div class="col s12" id="createNewTeam">
                <div class="row">
                    <div class="input-field col s12">
                        <input id="teamTitle" type="text" required>
                        <label for="teamTitle">Name</label>
                    </div>
                    <div class="input-field col s12">
                        <input id="teamLocation" type="text" required>
                        <label for="teamLocation">Location</label>
                    </div>
                </div>
                <div class="col s12 center-align">
                    <button class="btn waves-effect waves-light" id="createTeamBtn">Create Team
                        <i class="material-icons right">group_add</i>
                    </button>
                    <br>
                    <br>
                    <span class="center-align red-text" id="newTeamFormMessage"></span>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="common/footer.jsp"></jsp:include>


<!-- Here must be generated all the players -->
<script>

    $(function () {
        $('input.autocomplete.player-input').autocomplete({
            data: {
                <c:forEach var="player" items="${allPlayers}">
                "${player.getFulName()}": null,
                </c:forEach>
            }
        });
    });
</script>


<script>

    //Remove player
    //Update player team to null
    $("li.player a").on('click', function () {
        var removeBtnId = $(this).attr("id");

        $('#' + removeBtnId).slideUp(300);
        var values = removeBtnId.split("_");
        var playerID = values[2];

        $.ajax({
            type: "POST",
            url: "removePlayer",
            data: {
                "playerID": playerID
            }
        });

    });


    //Add player to game
    $("a.btn.green.darken-1").click(function () {
        var addPlayerBtn = $(this).attr("id");

        var values = addPlayerBtn.split("_");
        var teamID = values[2];

        $("#addNewPlayer").off().click(function () {
            var firstName = $("#new-player-form #fname").val();
            var surname =$("#new-player-form #surname").val()
            var age = $("#new-player-form #age").val();

            var infoMessage = $('#newPlayerFormMessage');

            if(firstName.length==0 || surname.length==0 || age.length==0){
                infoMessage.text("Fields can't be empty!");
            }else{
                var playerName = firstName + " " + surname;

                $.ajax({
                    type: "POST",
                    url: "addNewPlayer",
                    data: {
                        "teamID": teamID,
                        "playerName": playerName,
                        "age":age
                    },
                    success: function (data) {
                        var response = JSON.parse(data);

                        var status = response.status;
                        var message = response.message;

                        if (status == 'OK') {
                            $('#add-player-modal').closeModal();
                            var list = '<li class="collection-item player"><i class="material-icons left green-text">person</i>' + playerName + '<a href="#"><i class="material-icons right red-text">clear</i></a></li>';
                            $("ul #playersInTeam_" + teamID).append(list);
                        } else {
                            infoMessage.text(message);
                        }
                    }
                });
            }


        });

        $("#addExistingPlayer").off().click(function () {

            var infoMessage = $('#existingPlayerFormMessage');

            var playerName = $("#existing-player").val();

            if (playerName.length == 0) {
                infoMessage.text("Fields can't be empty!");
            }else {
                $.ajax({
                    type: "POST",
                    url: "addExistingPlayer",
                    data: {
                        "teamID": teamID,
                        "playerName": playerName
                    },
                    success: function (data) {
                        var response = JSON.parse(data);

                        var status = response.status;
                        var message = response.message;

                        if (status == 'OK') {
                            $('#add-player-modal').closeModal();
                            var list = '<li class="collection-item player"><i class="material-icons left green-text">person</i>' + playerName + '<a href="#"><i class="material-icons right red-text">clear</i></a></li>';
                            $("ul #playersInTeam_" + teamID).append(list);
                        } else {
                            infoMessage.text(message);
                        }
                    }
                });
            }

        });

    });

    //Add new Team
    $("#createTeamBtn").click(function () {
        var teamName = $("#teamTitle").val();
        var teamLocation = $("#teamLocation").val();

        var infoMessage = $('#newTeamFormMessage');
        if (teamName.length == 0 || teamLocation.length == 0) {
            infoMessage.text("Fields can't be empty!");
        } else {
            $.ajax({
                type: "POST",
                url: "createTeam",
                data: {
                    "teamName": teamName,
                    "teamLocation": teamLocation
                },
                success: function (data) {
                    var response = JSON.parse(data);

                    var status = response.status;
                    var message = response.message;

                    if (status == 'OK') {
                        $('#new-team-modal').closeModal();
                        window.location = 'teams';
                    } else {
                        infoMessage.text(message);
                    }
                }
            });
        }
    });
</script>

</body>

</html>