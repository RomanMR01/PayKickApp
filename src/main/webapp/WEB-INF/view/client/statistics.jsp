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
    <title>Paykick - Statistics</title>

    <jsp:include page="common/styles.jsp"></jsp:include>
    <jsp:include page="common/scripts.jsp"></jsp:include>
</head>

<body>
<jsp:include page="common/navigation.jsp"></jsp:include>

<!-- Main Content -->
<main class="valign-wrapper grey lighten-3">
    <div class="container valign" style="margin-top: 20px;">
        <h4 class="center-align">Statistics:</h4>
        <br>
        <div class="row">
            <div class="col s8 offset-s2 l6 offset-l3">
                <ul class="tabs transparent">
                    <c:forTokens var="status" items="TEAMS,PLAYERS" delims=",">
                        <li class="tab col s4 l2"><c:choose>
                            <c:when test="${status==type}">
                                <a href="#" class="active">${status}</a>
                            </c:when>
                            <c:otherwise>
                                <a href="statistics?type=${status}" target="_self">${status}</a>
                            </c:otherwise>
                        </c:choose></li>
                    </c:forTokens>
                </ul>
            </div>
        </div>
        <c:choose>
            <c:when test="${type=='TEAMS'}">
                <ul class="collapsible popout" data-collapsible="expandable">
                    <c:forEach var="team" items="${teams}">
                        <li>
                            <div class="collapsible-header center-align">
                                <i class="material-icons green-text">group</i><span
                                    class="green-text"><strong><c:out value="${team.name}"></c:out></strong></span>
                            </div>
                            <div class="collapsible-body center-align">
                                <br> <a
                                    class="waves-effect waves-light btn modal-trigger green"
                                    href="#modal-stats"> <div class="buttonTeamChart" id="${team.name}"><i
                                    class="material-icons right">timeline</i>Statistics
                            </div></a>
                                <br> <br>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:when test="${type=='PLAYERS'}">
                <ul class="collapsible popout" data-collapsible="expandable">
                    <c:forEach var="player" items="${players}">
                        <li>
                            <div class="collapsible-header center-align">
                                <i class="material-icons green-text">person</i><span
                                    class="green-text"><strong><c:out value="${player.fulName}"></c:out></strong></span>
                            </div>
                            <div class="collapsible-body center-align">
                                <br>
                                <a class="waves-effect waves-light modal-trigger btn green" href="#modal-stats">
                                    <div class="buttonPlayerChart" id="${player.id}/${player.fulName}"><i
                                            class="material-icons right">timeline</i>Statistics
                                    </div>
                                    ></a>
                                <br> <br>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </c:when>
        </c:choose>

        <ul class="pagination center-align">
            <c:choose>
                <c:when test="${page <= 1}">
                    <li class="disabled"><a href="#!"><i
                            class="material-icons">chevron_left</i></a></li>
                </c:when>
                <c:otherwise>
                    <li class="material-icons"><a
                            href="statistics?type=${type}&page=${page-1}&itemsOnPage=${itemsOnPage}"><i
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
                                href="statistics?type=${type}&page=${i}&itemsOnPage=${itemsOnPage}">
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
                            href="statistics?type=${type}&page=${page+1}&itemsOnPage=${itemsOnPage}"><i
                            class="material-icons">chevron_right</i></a></li>
                </c:otherwise>
            </c:choose>
        </ul>

    </div>

</main>

<!-- Statistics Modal Structure -->
<div id="modal-stats" class="modal my-stats">
    <div class="modal-content">
        <div class="row">
            <div id="statistics" class="col s12 center-align">
               <h5><div id="currentHeader"></div></h5>
                <div id="chart" style="width: 100%;"></div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="common/footer.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/static/js/clientChart.js"></script>
</body>

</html>