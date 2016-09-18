<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'ua_UA'}" scope="session" />
            <fmt:setLocale value="${language}" />
            <fmt:setBundle basename="i18n.lang" />
<!DOCTYPE html>

<html>

<head>

<!-- Meta -->
<meta charset="utf-8">

<!-- Mobile Metas -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><fmt:message key="title.bookmaker_matches" /></title>

<jsp:include page="common/styles.jsp"></jsp:include>
<jsp:include page="common/scripts.jsp"></jsp:include>
</head>

<body>
	<jsp:include page="common/navigation.jsp"></jsp:include>

	<!-- Main Content -->
	<main class="valign-wrapper grey lighten-3 bookmaker"> <c:forEach
		var="game" items="${games}">
		<form id="form_${game.id}" action="ConfirmCoefficients" method="post">
		<input type="hidden" value="${game.id}" name="gameId"/>
		</form>
	</c:forEach>
	<div class="container valign" style="margin-top: 20px;">
		<h4 class="center-align"><fmt:message key="common.matches" />:</h4>
		<br>
		<div class="row">
			<div class="col s12 l8 offset-l2">
				<ul class="tabs transparent">
					<c:forTokens var="status" items="NEW,ACTIVE"
						delims=",">
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
				<li id="li_${game.id}">
					<div class="collapsible-header center-align">
						<c:choose>
							<c:when test="${game.status=='NEW'}">
								<i class="material-icons orange-text">list</i>
							</c:when>
							<c:when test="${game.status=='ACTIVE'}">
								<i class="material-icons green-text">list</i>
							</c:when>
						</c:choose>
						<span class="grey-text"> <c:out value="${game.title}"></c:out>
						</span>
						<c:choose>
							<c:when test="${game.status=='NEW'}">
								<span class="orange-text">
							</c:when>
							<c:when test="${game.status=='ACTIVE'}">
								<span class="green-text">
							</c:when>
						</c:choose>
						<strong> <c:out value="${game.firstTeam.name}"></c:out> -
							<c:out value="${game.secondTeam.name}"></c:out></strong></span>
					</div>
					<div class="collapsible-body center-align">
						<div class="row">
							<table class="centered responsive-table col s6 offset-s3">
								<thead>
									<tr>
										<th><fmt:message key="caption.location" /></th>
										<th><fmt:message key="caption.date" /></th>
										<th><fmt:message key="caption.profit" /></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><c:out value="${game.location}"></c:out></td>
										<td><fmt:formatDate type="both" dateStyle="short"
												timeStyle="short" value="${game.date}" /></td>
										<c:choose>
											<c:when test="${game.profit > 0}">
												<td class="green-text"><c:out value="+ $${game.profit}"></c:out>
												</td>
											</c:when>
											<c:when test="${game.profit < 0}">
												<td class="red-text"><c:out
														value="- $${(-game.profit)}"></c:out></td>
											</c:when>
											<c:otherwise>
												<td><c:out value="$${game.profit}"></c:out></td>
											</c:otherwise>
										</c:choose>
									</tr>
								</tbody>
							</table>
							<div class="col s12 center-align">
								<hr>
								<br>
								<h5><fmt:message key="coefficients.result" />:</h5>
								<table
									class="centered responsive-table col s10 offset-s1 big-height">
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
											<c:choose>
												<c:when
													test="${game.resultCoefficients!=null&&game.resultCoefficients.size()>0}">
													<c:forEach var="resultCoefficient"
														items="${game.resultCoefficients}">
														<td>
															<div class="input-field col s12">
																<input id="coef_${resultCoefficient.id}" type="number" min="1.01" max="100"
																	step="0.01" form="form_${game.id}"
																	name="resultCoefficients" value="${resultCoefficient.coefficient}"> <label
																	for="coef_${resultCoefficient.id}"><fmt:message key="caption.coefficient" /></label>
															</div>
														</td>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<c:forEach var="i" begin="0" end="5">
														<td>
															<div class="input-field col s12">
																<input id="coef_${i}_${game.id}" type="number" min="1.01" max="100"
																	step="0.01" form="form_${game.id}"
																	name="resultCoefficients"> <label
																	for="coef_${i}_${game.id}"><fmt:message key="coefficients.name" /></label>
															</div>
														</td>
													</c:forEach>
												</c:otherwise>
											</c:choose>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="col s12 center-align">
								<hr>
								<br>
								<h5><fmt:message key="coefficients.goals" />:</h5>
								<table
									class="centered responsive-table col s6 offset-s3 big-height">
									<thead>
										<tr>
											<th><c:out value="${game.firstTeam.name}"></c:out> <fmt:message key="common.goal" /></th>
											<th><fmt:message key="coefficients.start" /></th>
											<th><c:out value="${game.secondTeam.name}"></c:out> <fmt:message key="common.goal" /></th>
										</tr>
									</thead>
									<tbody>
										<tr>
										<c:choose>
										<c:when test="${game.status=='ACTIVE'}">
											<td>
												<div class="input-field col s12">
													<input id="coef_start_${game.scoreCoefficient.id}" type="number" step="0.01" min="1.01" max="100"
														form="form_${game.id}" name="goalsCoefficients" value="${game.scoreCoefficient.firstTeamCoefficient}"> <label
														for="coef_start_${game.scoreCoefficient.id}"><fmt:message key="coefficients.name" /></label>
												</div>
											</td>
											<td>
												<div class="input-field col s12">
													<input id="coef_t1_${game.scoreCoefficient.id}" type="number" step="0.01" min="1.01" max="100"
														form="form_${game.id}" name="goalsCoefficients" value="${game.scoreCoefficient.startCoefficient}"> <label
														for="coef_t1_${game.scoreCoefficient.id}"><fmt:message key="coefficients.name" /></label>
												</div>
											</td>
											<td>
												<div class="input-field col s12">
													<input id="coef_t2_${game.scoreCoefficient.id}" type="number" step="0.01" min="1.01" max="100"
														form="form_${game.id}" name="goalsCoefficients" value="${game.scoreCoefficient.secondTeamCoefficient}"> <label
														for="coef_t2_${game.scoreCoefficient.id}"><fmt:message key="coefficients.name" /></label>
												</div>
											</td>
											</c:when>
											<c:otherwise>
											<td>
												<div class="input-field col s12">
													<input id="coef_start_${game.scoreCoefficient.id}" type="number" step="0.01" min="1.01" max="100"
														form="form_${game.id}" name="goalsCoefficients">
															<label for="coef_start_${game.scoreCoefficient.id}"><fmt:message key="coefficients.name" /></label>
														</div>
											</td>
											<td>
												<div class="input-field col s12">
													<input id="coef_t1_${game.scoreCoefficient.id}" type="number" step="0.01" min="1.01" max="100"
														form="form_${game.id}" name="goalsCoefficients" > <label
														for="coef_t1_${game.scoreCoefficient.id}"><fmt:message key="coefficients.name" /></label>
												</div>
											</td>
											<td>
												<div class="input-field col s12">
													<input id="coef_t2_${game.scoreCoefficient.id}" type="number" step="0.01" min="1.01" max="100"
														form="form_${game.id}" name="goalsCoefficients" > <label
														for="coef_t2_${game.scoreCoefficient.id}"><fmt:message key="coefficients.name" /></label>
												</div>
											</td>
											</c:otherwise>
											</c:choose>
										</tr>
									</tbody>
								</table>
							</div>
							<br> <br>
							<div class="col s12 center-align">
								<hr>
								<br>
								<h5><fmt:message key="coefficients.player" />:</h5>
								<br>
								<div class="row">
									<div class="col s4 offset-s2 m3 offset-m3">
										<h6>
											<strong><c:out value="${game.firstTeam.name}"></c:out></strong>
										</h6>
										<c:choose>
											<c:when test="${game.status=='NEW'}">
												<c:forEach var="player" items="${game.firstTeam.players}">
													<div class="input-field col s12">
													<input type="hidden" name="firstTeamPlayerId" value="${player.id}" form="form_${game.id}" />
														<input id="player${player.id}" type="number" step="0.01" min="1.01" max="100"
															value="5.00" form="form_${game.id}"
															name="firstTeamPlayerCoefficients"> <label
															for="player${player.id}"><c:out
																value="${player.fulName}"></c:out></label>
													</div>
												</c:forEach>
											</c:when>
											<c:when test="${game.status=='ACTIVE'}">
												<c:forEach var="playerCoefficient"
													items="${game.firstTeamPlayerCoefficients}">
													<div class="input-field col s12">
													<input type="hidden" name="firstTeamPlayerId" value="${playerCoefficient.player.id}" form="form_${game.id}"/>
														<input id="player${playerCoefficient.player.id}"
															type="number" step="0.01" min="1.01" max="100"
															value="${playerCoefficient.coefficient}"
															form="form_${game.id}" name="firstTeamPlayerCoefficients">
														<label for="player${player.id}"><c:out
																value="${playerCoefficient.player.fulName}"></c:out></label>
													</div>
												</c:forEach>
											</c:when>
										</c:choose>
									</div>
									<div class="col s4 m3">
										<h6>
											<strong><c:out value="${game.secondTeam.name}"></c:out></strong>
										</h6>
										<c:choose>
											<c:when test="${game.status=='NEW'}">
												<c:forEach var="player" items="${game.secondTeam.players}">
													<div class="input-field col s12">
													<input type="hidden" name="secondTeamPlayerId" value="${player.id}" form="form_${game.id}" />
														<input id="player${player.id}" type="number" step="0.01" min="1.01" max="100"
															value="5.00" form="form_${game.id}"
															name="secondTeamPlayerCoefficients"> <label
															for="player${player.id}"><c:out
																value="${player.fulName}"></c:out></label>
													</div>
												</c:forEach>
											</c:when>
											<c:when test="${game.status=='ACTIVE'}">
												<c:forEach var="playerCoefficient"
													items="${game.secondTeamPlayerCoefficients}">
													<div class="input-field col s12">
													<input type="hidden" name="secondTeamPlayerId" value="${playerCoefficient.player.id}" form="form_${game.id}"/>
														<input id="player${playerCoefficient.player.id}"
															type="number" step="0.01" min="1.01" max="100"
															value="${playerCoefficient.coefficient}"
															form="form_${game.id}"
															name="secondTeamPlayerCoefficients"> <label
															for="player${player.id}"><c:out
																value="${playerCoefficient.player.fulName}"></c:out></label>
													</div>
												</c:forEach>
											</c:when>
										</c:choose>
									</div>
								</div>
							</div>
						</div>
						<button class="waves-effect waves-light btn green" type="submit" form="form_${game.id}"><i
							class="material-icons right">done</i><fmt:message key="common.confirm" /></button> <br> <br>
					</div>
				</li>
			</c:forEach>
		</ul>
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
							href="matches?type=${type}&page=${i}&itemsOnPage=${itemsOnPage}">
								<c:out value="${i}"></c:out>
						</a></li>
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

	<jsp:include page="common/footer.jsp"></jsp:include>

</body>

</html>