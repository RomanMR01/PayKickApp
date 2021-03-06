<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
         <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'ua_UA'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.lang"/>
        <!DOCTYPE html>

        <html>

        <head>

            <!-- Meta -->
            <meta charset="utf-8">

            <!-- Mobile Metas -->
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title><fmt:message key="title.adminteams" /></title>

            <jsp:include page="common/styles.jsp"></jsp:include>
            <jsp:include page="common/scripts.jsp"></jsp:include>
        </head>

        <body>
            <jsp:include page="common/navigation.jsp"></jsp:include>

            <!-- Main Content -->
            <main class="valign-wrapper grey lighten-3">
                <div class="container valign" style="margin-top: 20px;">
                    <h4 class="center-align"><fmt:message key="common.teams" />:</h4>
                    <br>
                    <ul class="collapsible popout" data-collapsible="expandable" id="allTeams">
                        <c:forEach var="team" items="${teams}">
                            <li id="${team.id}">
                                <div class="collapsible-header center-align" id="header_${team.id}"><i class="material-icons green-text">group</i><span class="green-text"><strong><c:out
                            value="${team.name}"></c:out></strong></span></div>
                                <div class="collapsible-body center-align">
                                    <div class="row">
                                        <br>
                                        <ul class="collection col s10 offset-s1" id="playersInTeam_${team.id}">
                                            <c:forEach var="player" items="${team.players}">
                                                <li id="player_id_${player.id}" class="collection-item player"><i class="material-icons left green-text">person</i>
                                                    <c:out value="${player.fulName}"></c:out><a id="player_id_${player.id}" href="#"><i
                                            class="material-icons right red-text">clear</i></a></li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                    <a class="waves-effect waves-light btn modal-trigger green darken-1" href="#add-player-modal" target="_self" id="addPlayer_team_${team.id}"><i class="material-icons right">person_add</i>         <fmt:message key="admin.addplayer" /></a>
                                    <br>
                                    <br>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                    <br>
                    <a class="waves-effect waves-light modal-trigger btn right green darken-2" href="#new-team-modal"><i
                class="material-icons right">group_add</i><fmt:message key="admin.newteam" /></a>
                    <br>
                    <ul class="pagination center-align">
                        <c:choose>
                            <c:when test="${page <= 1}">
                                <li class="disabled"><a href="#!"><i
                            class="material-icons">chevron_left</i></a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="material-icons"><a href="teams?page=${page-1}&itemsOnPage=${itemsOnPage}"><i
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
                                        <a href="teams?page=${i}&itemsOnPage=${itemsOnPage}">
                                            <c:out value="${i}"></c:out>
                                        </a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <c:choose>
                            <c:when test="${page >= pages}">
                                <li class="disabled"><a href="#!"><i
                            class="material-icons">chevron_right</i></a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="material-icons"><a href="teams?page=${page+1}&itemsOnPage=${itemsOnPage}"><i
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
                            <h5 class="center-align"><fmt:message key="admin.addplayer" /></h5>
                            <br>
                            <ul class="tabs transparent">
                                <li class="tab col s6"><a href="#new-player-form" class="active"><fmt:message key="common.new" /></a></li>
                                <li class="tab col s6"><a href="#existing-player-form"><fmt:message key="common.existing" /></a></li>
                            </ul>
                        </div>
                        <div id="new-player-form" class="col s12">
                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="fname" type="text" length="20" required>
                                    <label for="fname"><fmt:message key="caption.name" /></label>
                                </div>
                                <div class="input-field col s12">
                                    <input id="surname" type="text" length="25" required>
                                    <label for="surname"><fmt:message key="caption.surname" /></label>
                                </div>
                                <div class="col s12">
                                    <p class="big"><fmt:message key="caption.age" />:</p>
                                    <p class="range-field">
                                        <input type="range" id="age" min="0" max="100" value="0" />
                                    </p>
                                </div>
                            </div>
                            <div class="col s12 center-align">
                                <button class="btn waves-effect waves-light" id="addNewPlayer"><fmt:message key="admin.addplayer" />
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
                                    <input type="text" id="existing-player" class="autocomplete player-input" length="50" autocomplete="off">
                                    <label for="existing-player"><fmt:message key="common.player" /></label>
                                </div>
                            </div>
                            <div class="col s12 center-align">
                                <button class="btn waves-effect waves-light" id="addExistingPlayer"><fmt:message key="admin.addplayer" />
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
                        <h5 class="center-align"><fmt:message key="admin.newteam" /></h5>
                        <br>
                        <div class="col s12" id="createNewTeam">
                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="teamTitle" type="text" length="50" required>
                                    <label for="teamTitle"><fmt:message key="caption.name" /></label>
                                </div>
                                <div class="input-field col s12">
                                    <input id="teamLocation" type="text" length="50" required>
                                    <label for="teamLocation"><fmt:message key="caption.location" /></label>
                                </div>
                            </div>
                            <div class="col s12 center-align">
                                <button class="btn waves-effect waves-light" id="createTeamBtn"><fmt:message key="admin.createteam" />
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
            <script src="${pageContext.request.contextPath}/static/js/admin/admin-teams.js"></script>

        </body>

        </html>