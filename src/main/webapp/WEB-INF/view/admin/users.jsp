<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

<head>

<!-- Meta -->
<meta charset="utf-8">

<!-- Mobile Metas -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>PayKick. One bet - one hit!</title>

<jsp:include page="common/styles.jsp"></jsp:include>
</head>

<body>
	<jsp:include page="common/navigation.jsp"></jsp:include>

	<!-- Main Content -->
	<main class="valign-wrapper grey lighten-3">
	<div class="container valign" style="margin-top: 20px;">
		<h4 class="center-align">Users:</h4>
		<br>
		<div class="row">
			<div class="col s12 l6 offset-l3">
				<ul class=" transparent">
					<c:choose>
						<c:when test="${param.type == 'ALL'}">
							<li class="tab col s4 l2"><a class="active" href="">ALL</a></li>
							<li class="tab col s4 l2"><a
								href="UsersPagination?type=CLIENT&page=1">CLIENTS</a></li>
							<li class="tab col s4 l2"><a
								href="UsersPagination?type=BOOKMAKER&page=1">BOOKMAKERS</a></li>
						</c:when>
						<c:when test="${param.type == 'CLIENT'}">
							<li class="tab col s4 l2"><a
								href="UsersPagination?type=ALL&page=1">ALL</a></li>
							<li class="tab col s4 l2"><a class="active" href="">CLIENTS</a></li>
							<li class="tab col s4 l2"><a
								href="UsersPagination?type=BOOKMAKER&page=1">BOOKMAKERS</a></li>
						</c:when>
						<c:when test="${param.type == 'BOOKMAKER'}">
							<li class="tab col s4 l2"><a
								href="UsersPagination?type=ALL&page=1">ALL</a></li>
							<li class="tab col s4 l2"><a
								href="UsersPagination?type=CLIENT&page=1">CLIENTS</a></li>
							<li class="tab col s4 l2"><a class="active" href="">BOOKMAKERS</a></li>
						</c:when>
					</c:choose>
				</ul>
			</div>
		</div>

		<ul class="collapsible popout" data-collapsible="expandable">
			<c:forEach var="user" items="${requestScope.users}">
				<li><c:choose>
						<c:when test="${user.isBanned()}">
							<div class="collapsible-header center-align">
								<i class="material-icons red-text">perm_identity</i><span
									class="red-text"><strong><c:out value="${user.login}"></c:out></strong></span>
							</div>
						</c:when>
						<c:otherwise>
							<div class="collapsible-header center-align">
								<i class="material-icons green-text">person_pin</i><span
									class="green-text"><strong><c:out value="${user.login}"></c:out></strong></span>
							</div>
						</c:otherwise>
					</c:choose>

					<div class="collapsible-body center-align">
						<table class="centered responsive-table">
							<thead>
								<tr>
									<th>Full Name</th>
									<th>Sex</th>
									<th>Age</th>
									<th>E-Mail</th>
									<th>Balance</th>
									<th>Role</th>
									<th>Banned</th>
								</tr>
							</thead>
							<tbody>

								<tr>
									<td><c:out value="${user.fullName}"></c:out></td>
									<td><c:out value="${user.gender}"></c:out></td>
									<td><c:out value="${user.age}"></c:out></td>
									<td><c:out value="${user.email}"></c:out></td>
									<td>$<c:out value="${user.balance}"></c:out></td>
									<td>
										<div class="input-field col l1">
											<form name="changeRole_${user.id}" action="changeUserRole">
												<input type="hidden" name="userId"
													value="<c:out value="${user.id}"></c:out>" /> <select>
													<c:choose>
														<c:when test="${user.role == 'CLIENT'}">
															<option value="CLIENT" selected>CLIENT</option>
															<option value="BOOKMAKER">BOOKMAKER</option>
															<option value="ADMIN">ADMIN</option>
														</c:when>
														<c:when test="${user.role == 'BOOKMAKER'}">
															<option value="CLIENT">CLIENT</option>
															<option value="BOOKMAKER" selected>BOOKMAKER</option>
															<option value="ADMIN">ADMIN</option>
														</c:when>
														<c:when test="${user.role == 'ADMIN'}">
															<option value="CLIENT">CLIENT</option>
															<option value="BOOKMAKER">BOOKMAKER</option>
															<option value="ADMIN" selected>ADMIN</option>
														</c:when>
													</c:choose>
												</select>
											</form>
										</div>
									</td>
									<td>
										<div class="switch">
											<label> No <c:choose>
													<c:when test="${user.isBanned()}">
														<input type="checkbox" checked>
													</c:when>
													<c:otherwise>
														<input type="checkbox">
													</c:otherwise>
												</c:choose> <span class="lever"></span> Yes
											</label>
										</div>
									</td>
								</tr>

							</tbody>
						</table>
						<a class="waves-effect waves-light modal-trigger btn green"
							href="#modal-stats"><i class="material-icons right">timeline</i>Statistics</a>
						<br> <br>
					</div></li>
			</c:forEach>
		</ul>

		<ul class="pagination center-align">

			<c:choose>
				<c:when test="${param.page <= 1}">
					<li class="disabled"><a href="#!"><i
							class="material-icons">chevron_left</i></a></li>
				</c:when>
				<c:otherwise>
					<li class="material-icons"><a
						href="UsersPagination?type=${param.type}&page=${param.page-1}"><i
							class="material-icons">chevron_left</i></a></li>
				</c:otherwise>
			</c:choose>

			<c:forEach var="i" begin="1" end="${requestScope.pages}">

				<c:choose>
					<c:when test="${i == param.page}">
						<li class="active green"><a href="#!"></a></li>
					</c:when>
					<c:otherwise>
						<li class="active green"><a
							href="UsersPagination?type=${param.type}&page=${i}"><c:out
									value="${i}"></c:out></a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:choose>
				<c:when test="${param.page == pages}">
					<li class="disabled"><a href="#!"><i
							class="material-icons">chevron_right</i></a></li>
				</c:when>
				<c:otherwise>
					<li class="material-icons"><a
						href="UsersPagination?type=${param.type}&page=${param.page+1}"><i
							class="material-icons">chevron_right</i></a></li>
				</c:otherwise>
			</c:choose>

		</ul>
		<br>
	</div>
	</main>

	<!-- Statistics Modal Structure -->
	<div id="modal-stats" class="modal my-stats">
		<div class="modal-content">
			<div class="row">
				<div class="col s12">
					<ul class="tabs transparent">
						<li class="tab col s6"><a href="">Chart</a></li>
						<li class="tab col s6"><a href="" class="active">Diagram</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="common/footer.jsp"></jsp:include>
	<jsp:include page="common/scripts.jsp"></jsp:include>

</body>

</html>