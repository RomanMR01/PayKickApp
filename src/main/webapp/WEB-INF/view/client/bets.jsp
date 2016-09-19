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
    <title>PayKick - <fmt:message key="title.mybets" /></title>

    <jsp:include page="common/styles.jsp"></jsp:include>
    <jsp:include page="common/scripts.jsp"></jsp:include>
</head>

<body>
<jsp:include page="common/navigation.jsp"></jsp:include>

<!-- Main Content -->
<main class="valign-wrapper grey lighten-3">
    <div class="container valign" style="margin-top: 20px;">
        <h4 class="center-align"><fmt:message key="title.mybets" />:</h4>
        <br>
        <div class="row">
            <div class="col s12 l8 offset-l2">
                <ul class="tabs transparent">
                    <c:forTokens var="status" items="ALL,ACTIVE,WON,LOST,CANCELED" delims=",">
                        <li class="tab col s4 l2">
                            <c:choose>
                                <c:when test="${status==type}">
                                    <a href="#" class="active">${status}</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="bets?type=${status}" target="_self">${status}</a>
                                </c:otherwise>
                            </c:choose>
                        </li>
                    </c:forTokens>
                </ul>
            </div>
        </div>
        <ul class="collapsible popout" data-collapsible="expandable">
            <c:forEach var="totalBet" items="${totalBets}">
                <li>
                    <div class="collapsible-header center-align">
                        <c:choose>
                            <c:when test="${totalBet.status=='ACTIVE'}">
                                <i class="material-icons orange-text">receipt</i>
                                                <span class="orange-text"><strong>#${totalBet.id} -
										ACTIVE</strong></span>
                            </c:when>
                            <c:when test="${totalBet.status=='WON'}">
                                <i class="material-icons green-text">receipt</i>
                                <span class="green-text"><strong>#${totalBet.id} - WON</strong></span>
                            </c:when>
                            <c:when test="${totalBet.status=='LOST'}">
                                <i class="material-icons red-text">receipt</i>
                                <span class="red-text"><strong>#${totalBet.id} - LOST</strong></span>
                            </c:when>
                            <c:when test="${totalBet.status=='CANCELED'}">
                                <i class="material-icons grey-text">receipt</i>
                                                <span class="grey-text"><strong>#${totalBet.id} -
										CANCELED</strong></span>
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="collapsible-body center-align">
                        <div class="row">
                            <table class="centered responsive-table col s10 offset-s1">
                                <thead>
                                <tr>
                                    <th><fmt:message key="caption.date" /></th>
                                    <th><fmt:message key="caption.match" /></th>
                                    <th><fmt:message key="caption.type" /></th>
                                    <th><fmt:message key="caption.bet" /></th>
                                    <th><fmt:message key="caption.status" /></th>
                                    <th><fmt:message key="caption.amount" /></th>
                                    <th><fmt:message key="caption.coefficient" /></th>
                                    <th><fmt:message key="caption.award" /></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:set var="totalCoefficient" value="${1}"></c:set>
                                <c:forEach var="singleBet" items="${totalBet.singleBets}">
                                    <tr>
                                        <td>
                                            <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${singleBet.game.date}" />
                                        </td>
                                        <td>
                                            <c:out value="${singleBet.game.title}"></c:out>
                                        </td>
                                        <td>
                                            <c:out value="${singleBet.category}"></c:out>
                                        </td>
                                        <c:choose>
                                            <c:when test="${singleBet.category=='RESULT'}">
                                                <td>
                                                    <c:out value="${singleBet.betResult.result}"></c:out>
                                                </td>
                                            </c:when>
                                            <c:when test="${singleBet.category=='GOALS'}">
                                                <td>
                                                    <c:out value="${singleBet.betTotalGoals.totalGoal}"></c:out>
                                                </td>
                                            </c:when>
                                            <c:when test="${singleBet.category=='SCORE'}">
                                                <td>${singleBet.betScore.firstTeamScore}- ${singleBet.betScore.secondTeamScore}
                                                </td>
                                            </c:when>
                                            <c:when test="${singleBet.category=='PLAYER'}">
                                                <td>
                                                    <c:out value="${singleBet.betPlayer.player.fulName}"></c:out>
                                                </td>
                                            </c:when>
                                        </c:choose>

                                        <td>
                                            <c:out value="${singleBet.status}"></c:out>
                                        </td>
                                        <td>-</td>
                                        <td>
                                            <c:set var="totalCoefficient" value="${totalCoefficient*singleBet.coefficient}"></c:set>
                                            <c:out value="${singleBet.coefficient}"></c:out>
                                        </td>
                                        <td>-</td>
                                    </tr>
                                </c:forEach>

                                <c:choose>
                                <c:when test="${totalBet.status=='ACTIVE'}">
                                <tr class="orange-text" style="border-top: 1px solid #d0d0d0;">
                                    </c:when>
                                    <c:when test="${totalBet.status=='WON'}">
                                <tr class="green-text" style="border-top: 1px solid #d0d0d0;">
                                    </c:when>
                                    <c:when test="${totalBet.status=='LOST'}">
                                <tr class="red-text" style="border-top: 1px solid #d0d0d0;">
                                    </c:when>
                                    <c:when test="${totalBet.status=='CANCELED'}">
                                <tr class="grey-text" style="border-top: 1px solid #d0d0d0;">
                                    </c:when>
                                    </c:choose>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td><strong>$<fmt:formatNumber value="${totalBet.amount}"
                                                                   type="number" maxFractionDigits="2" minFractionDigits="2"/></strong></td>
                                    <td><strong><fmt:formatNumber value="${totalCoefficient}"
                                                                  type="number" maxFractionDigits="2" minFractionDigits="2"/></strong></td>
                                    <td><strong>$<fmt:formatNumber value="${totalBet.award}"
                                                                   type="number" maxFractionDigits="2" minFractionDigits="2"/></strong></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
        <br>
        <ul class="pagination center-align">
            <c:choose>
                <c:when test="${page <= 1}">
                    <li class="disabled"><a href="#!"><i
                            class="material-icons">chevron_left</i></a></li>
                </c:when>
                <c:otherwise>
                    <li class="material-icons"><a href="bets?type=${type}&page=${page-1}&itemsOnPage=${itemsOnPage}"><i
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
                            <a href="bets?type=${type}&page=${i}&itemsOnPage=${itemsOnPage}">
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
                    <li class="material-icons"><a href="bets?type=${type}&page=${page+1}&itemsOnPage=${itemsOnPage}"><i
                            class="material-icons">chevron_right</i></a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>


</main>

<jsp:include page="common/footer.jsp"></jsp:include>
<script>
    $(document).ready(function () {
        $("td").each(function () {
            var a = $.trim($(this).text());

            if ("C1" == a || "C2" == a || "CX" == a || "C1X" == a || "CX2" == a || "C12" == a) {
                $(this).text($.trim(a).replace("C", ""));
            }



        });
    });
</script>
</body>

</html>