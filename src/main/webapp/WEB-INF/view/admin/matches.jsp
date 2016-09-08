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
                        <li class="tab col s4 l2"><c:choose>
                            <c:when test="${status==type}">
                                <a href="#" class="active">${status}</a>
                            </c:when>
                            <c:otherwise>
                                <a href="matches?type=${status}" target="_self">${status}</a>
                            </c:otherwise>
                        </c:choose></li>
                    </c:forTokens>
                </ul>
            </div>
        </div>
        <ul class="collapsible popout" data-collapsible="expandable">
            <c:forEach var="game" items="${games}">

                <li>
                    <div class="collapsible-header center-align">
                        <c:choose>
                            <c:when test=""></c:when>
                            <c:when test=""></c:when>
                        </c:choose>
                        <c:choose>
                            <c:when test="${game.status=='NEW'}"><i class="material-icons orange-text">list</i></c:when>
                            <c:when test="${game.status=='ACTIVE'}"><i class="material-icons green-text">list</i></c:when>
                            <c:when test="${game.status=='FINISHED'}"><i class="material-icons grey-text">list</i></c:when>
                            <c:when test="${game.status=='CANCELED'}"><i class="material-icons red-text">list</i></c:when>
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
                                    <td><c:out value="${game.location}"></c:out></td>
                                    <td><fmt:formatDate type="both" dateStyle="short"
                                                        timeStyle="short" value="${game.date}" /></td>
                                    <td><c:out value="${game.bookmaker.login}"></c:out></td>
                                    <td><c:out value="${game.profit}"></c:out></td>
                                </tr>
                                </tbody>
                            </table>
                            <div class="col s6 offset-s3">
                                <div class="row">
                                    <div class="input-field col s6">
                                        <input id="team1_${game.firstTeam.id}" type="number">
                                        <!-- Id must be not just a name of team because one team may have several active games at different dates -->
                                        <label for="team1_${game.firstTeam.id}"><c:out
                                                value="${game.firstTeam.name}"></c:out></label>
                                    </div>
                                    <div class="input-field col s6">
                                        <input id="team2_${game.secondTeam.id}" type="number">
                                        <!-- Id must be not just a name of team because one team may have several active games at different dates -->
                                        <label for="team2_${game.secondTeam.id}"><c:out
                                                value="${game.secondTeam.name}"></c:out></label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <a class="waves-effect waves-light btn green" href="#"><i
                                class="material-icons right">done</i>Confirm</a> <br> <br>
                    </div>
                </li>

            </c:forEach>
            <!-- 			<li> -->
            <!-- 				<div class="collapsible-header center-align"> -->
            <!-- 					<i class="material-icons green-text">list</i><span -->
            <!-- 						class="orange-text">1/8 Euro Cup: </span><span class="green-text"><strong>Barcelona -->
            <!-- 							- Real Madrid</strong></span> -->
            <!-- 				</div> -->
            <!-- 				<div class="collapsible-body center-align"> -->
            <!-- 					<div class="row"> -->
            <!-- 						<table class="centered responsive-table col s6 offset-s3"> -->
            <!-- 							<thead> -->
            <!-- 								<tr> -->
            <!-- 									<th>Location</th> -->
            <!-- 									<th>Date</th> -->
            <!-- 									<th>Bookmaker</th> -->
            <!-- 									<th>Profit</th> -->
            <!-- 								</tr> -->
            <!-- 							</thead> -->
            <!-- 							<tbody> -->
            <!-- 								<tr> -->
            <!-- 									<td>London</td> -->
            <!-- 									<td>25.09.2016 - 19:00</td> -->
            <!-- 									<td>banan12</td> -->
            <!-- 									<td>вЂ”</td> -->
            <!-- 								</tr> -->
            <!-- 							</tbody> -->
            <!-- 						</table> -->
            <!-- 						<div class="col s6 offset-s3"> -->
            <!-- 							<div class="row"> -->
            <!-- 								<div class="input-field col s6"> -->
            <!-- 									<input id="barca" type="number"> -->
            <!-- 									Id must be not just a name of team because one team may have several active games at different dates -->
            <!-- 									<label for="barca">Barcelona</label> -->
            <!-- 								</div> -->
            <!-- 								<div class="input-field col s6"> -->
            <!-- 									<input id="real" type="number"> -->
            <!-- 									Id must be not just a name of team because one team may have several active games at different dates -->
            <!-- 									<label for="real">Real Madrid</label> -->
            <!-- 								</div> -->
            <!-- 							</div> -->
            <!-- 						</div> -->
            <!-- 					</div> -->
            <!-- 					<a class="waves-effect waves-light btn green" href="#"><i -->
            <!-- 						class="material-icons right">done</i>Confirm</a> <br> <br> -->
            <!-- 				</div> -->
            <!-- 			</li> -->
            <!-- 			<li> -->
            <!-- 				<div class="collapsible-header center-align"> -->
            <!-- 					<i class="material-icons grey-text">list</i><span class="grey-text">1/4 -->
            <!-- 						World Cup: <strong>Kongo - Brazil</strong> -->
            <!-- 					</span> -->
            <!-- 				</div> -->
            <!-- 				<div class="collapsible-body center-align"> -->
            <!-- 					<div class="row"> -->
            <!-- 						<table class="centered responsive-table col s6 offset-s3"> -->
            <!-- 							<thead> -->
            <!-- 								<tr> -->
            <!-- 									<th>Location</th> -->
            <!-- 									<th>Date</th> -->
            <!-- 									<th>Bookmaker</th> -->
            <!-- 									<th>Profit</th> -->
            <!-- 								</tr> -->
            <!-- 							</thead> -->
            <!-- 							<tbody> -->
            <!-- 								<tr> -->
            <!-- 									<td>St. Kongo</td> -->
            <!-- 									<td>25.08.2016 - 13:00</td> -->
            <!-- 									<td>banan12</td> -->
            <!-- 									<td class="green-text">+ $350.00</td> -->
            <!-- 								</tr> -->
            <!-- 							</tbody> -->
            <!-- 						</table> -->
            <!-- 						<div class="col s6 offset-s3"> -->
            <!-- 							<div class="row"> -->
            <!-- 								<div class="input-field col s6"> -->
            <!-- 									<input disabled id="kongo" type="number" value="3"> -->
            <!-- 									Id must be not just a name of team because one team may have several active games at different dates -->
            <!-- 									<label for="kongo">Kongo</label> -->
            <!-- 								</div> -->
            <!-- 								<div class="input-field col s6"> -->
            <!-- 									<input disabled id="brazil" type="number" value="2"> -->
            <!-- 									Id must be not just a name of team because one team may have several active games at different dates -->
            <!-- 									<label for="brazil">Brazil</label> -->
            <!-- 								</div> -->
            <!-- 							</div> -->
            <!-- 						</div> -->
            <!-- 					</div> -->
            <!-- 				</div> -->
            <!-- 			</li> -->
        </ul>
        <br> <a
            class="waves-effect waves-light modal-trigger btn right green"
            href="#new-match-modal"><i class="material-icons right">add</i>New
        Match</a> <br>
        <ul class="pagination center-align">
            <c:choose>
                <c:when test="${page <= 1}">
                    <li class="disabled"><a href="#!"><i
                            class="material-icons">chevron_left</i></a></li>
                </c:when>
                <c:otherwise>
                    <li class="material-icons"><a
                            href="matches?type=${type}&page=${page-1}&itemsOnPage=${itemsOnPage}"><i
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
                                href="matches?type=${type}&page=${i}&itemsOnPage=${itemsOnPage}"><c:out
                                value="${i}"></c:out></a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:choose>
                <c:when test="${page == pages}">
                    <li class="disabled"><a href="#!"><i
                            class="material-icons">chevron_right</i></a></li>
                </c:when>
                <c:otherwise>
                    <li class="material-icons"><a
                            href="matches?type=${type}&page=${page+1}&itemsOnPage=${itemsOnPage}"><i
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
            <form class="col s12">
                <div class="row">
                    <div class="input-field col s12">
                        <input id="title" type="text"> <label for="title">Title</label>
                    </div>
                    <div class="input-field col s12">
                        <input id="location" type="text"> <label for="location">Location</label>
                    </div>
                    <div class="col s12">
                        <label for="date">Date</label> <input id="date"
                                                              type="datetime-local">
                    </div>
                    <div class="row">
                        <div class="input-field col s6">
                            <input type="text" id="first-team"
                                   class="autocomplete team-input" autocomplete="off"> <label
                                for="first-team">First Team</label>
                        </div>
                        <div class="input-field col s6">
                            <input type="text" id="second-team"
                                   class="autocomplete team-input" autocomplete="off"> <label
                                for="second-team">Second Team</label>
                        </div>
                    </div>
                    <div class="input-field col s12">
                        <input type="text" id="bookmaker"
                               class="autocomplete bookmaker-input" autocomplete="off">
                        <label for="bookmaker">Bookmaker</label>
                    </div>
                    <br> <br> <br> <br>
                    <div class="col s12 center-align">
                        <button class="btn waves-effect waves-light" type="submit"
                                name="action">
                            Confirm <i class="material-icons right">done</i>
                        </button>
                        <br> <br> <span class="center-align red-text"
                                        id="messageRegistration"></span>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="common/footer.jsp"></jsp:include>


<!-- Here must be generated all the teams; Second parameter - logo of the team -->
<script>
    $(function() {
        $('input.autocomplete.team-input').autocomplete({
            data : {
                "Manchester United" : null,
                "Dynamo Kyiv" : null,
                "Zoria" : null,
                "Arsenal" : null,
                "Bavaria" : null,
                "Tottenham" : null,
                "Real Madrid" : null,
                "Barcelona" : null,
                "Shakhtar" : null
            }
        });
    });
</script>
<!-- Here must be generated all the bookmakers; Second parameter - avatar -->
<script>
    $(function() {
        $('input.autocomplete.bookmaker-input').autocomplete({
            data : {
                "Andrew Bright" : null,
                "Jim Carey" : null,
                "Adam Beast" : null,
                "Jessica Alba" : null,
                "Bald From Brazzers" : null
            }
        });
    });
</script>

</body>

</html>