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
    <title><fmt:message key="title.adminuser" /></title>

    <jsp:include page="common/styles.jsp"></jsp:include>

</head>

<body>
<jsp:include page="common/navigation.jsp"></jsp:include>

<!-- Main Content -->
<main class="valign-wrapper grey lighten-3">

    <div class="container valign" style="margin-top: 20px;">
        <h4 class="center-align"><fmt:message key="admin.users" />:</h4>
        <br>
        <div class="row">
            <div class="col s12 l6 offset-l3">
                <ul class="tabs transparent">
                    <c:choose>

                        <c:when test="${type == 'CLIENT'}">
                            <li class="tab col s4 l2"><a href="users?type=ALL&page=1" target="_self"><fmt:message key="common.all" /></a></li>
                            <li class="tab col s4 l2"><a class="active" href="" target="_self"><fmt:message key="common.clients" /></a></li>
                            <li class="tab col s4 l2"><a href="users?type=BOOKMAKER&page=1"
                                                         target="_self"><fmt:message key="common.bookmakers" /></a></li>
                        </c:when>
                        <c:when test="${type == 'BOOKMAKER'}">
                            <li class="tab col s4 l2"><a href="users?type=ALL&page=1" target="_self"><fmt:message key="common.all" /></a></li>
                            <li class="tab col s4 l2"><a href="users?type=CLIENT&page=1" target="_self"><fmt:message key="common.clients" /></a></li>
                            <li class="tab col s4 l2"><a class="active" href="" target="_self"><fmt:message key="common.bookmakers" /></a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="tab col s4 l2"><a class="active" href="" target="_self"><fmt:message key="common.all" /></a></li>
                            <li class="tab col s4 l2"><a href="users?type=CLIENT&page=1" target="_self"><fmt:message key="common.clients" /></a></li>
                            <li class="tab col s4 l2"><a href="users?type=BOOKMAKER&page=1"
                                                         target="_self"><fmt:message key="common.bookmakers" /></a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>

        <ul class="collapsible popout" data-collapsible="expandable">
            <c:forEach var="user" items="${requestScope.users}">
                <li id="u_${user.id}">
                    <c:choose>
                        <c:when test="${user.isBanned()}">
                            <div class="collapsible-header center-align">
                                <i class="material-icons red-text">
                                    <c:choose>
                                        <c:when test="${user.role=='CLIENT'}">person_pin</c:when>
                                        <c:otherwise>perm_identity</c:otherwise>
                                    </c:choose>
                                </i><span class="red-text"><strong><c:out
                                    value="${user.login}"></c:out></strong></span>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="collapsible-header center-align">
                                <i class="material-icons green-text">
                                    <c:choose>
                                        <c:when test="${user.role=='CLIENT'}">person_pin</c:when>
                                        <c:otherwise>perm_identity</c:otherwise>
                                    </c:choose>
                                </i><span class="green-text"><strong><c:out
                                    value="${user.login}"></c:out></strong></span>
                            </div>
                        </c:otherwise>
                    </c:choose>
                    <!-- Statistics Modal Structure -->
                    <div id="modal-stats" class="modal">
                        <div class="modal-content">
                            <div class="row">
                                <div class="col s12">
                                    <ul class="tabs transparent">
                                        <li class="tab col s6"><a href="#chart"><fmt:message key="common.chart" /></a></li>
                                        <li class="tab col s6"><a href="#diagram"><fmt:message key="common.diagram" /></a></li>
                                    </ul>
                                </div>
                                <div class="col s12">
                                    <div id="chart" style="width: 100%;"></div>
                                    <div id="diagram" style="width: 100%;"></div>
                                </div>

                            </div>
                        </div>
                    </div>

                    <div class="collapsible-body center-align">
                        <table class="centered responsive-table">
                            <thead>
                            <tr>
                                <th><fmt:message key="caption.fullname" /></th>
                                <th><fmt:message key="caption.sex" /></th>
                                <th><fmt:message key="caption.age" /></th>
                                <th><fmt:message key="caption.email" /></th>
                                <th><fmt:message key="caption.balance" /></th>
                                <th><fmt:message key="caption.role" /></th>
                                <th><fmt:message key="caption.banned" /></th>
                            </tr>
                            </thead>
                            <tbody>

                            <tr>
                                <td>
                                    <c:out value="${user.fullName}"></c:out>
                                </td>
                                <td>
                                    <c:out value="${user.gender}"></c:out>
                                </td>
                                <td>
                                    <c:out value="${user.age}"></c:out>
                                </td>
                                <td>
                                    <c:out value="${user.email}"></c:out>
                                </td>
                                <td>$
                                    <c:out value="${user.balance}"></c:out>
                                </td>
                                <td>
                                    <div class="input-field col l1">
                                        <div name="changeRole_${user.id}" action="changeUserRole">
                                            <input type="hidden" name="userId"
                                                   value="<c:out value=" ${user.id} "></c:out>"/>
                                            <select id="${user.id}">
                                                <c:choose>
                                                    <c:when test="${user.role == 'CLIENT'}">
                                                        <option value="CLIENT" selected><fmt:message key="common.client" /></option>
                                                        <option value="BOOKMAKER"><fmt:message key="common.bookmaker.caps" /></option>
                                                        <option value="ADMIN"><fmt:message key="common.admin" /></option>
                                                    </c:when>
                                                    <c:when test="${user.role == 'BOOKMAKER'}">
                                                        <option value="CLIENT"><fmt:message key="common.client" /></option>
                                                        <option value="BOOKMAKER" selected><fmt:message key="common.bookmaker.caps" /></option>
                                                        <option value="ADMIN"><fmt:message key="common.admin" /></option>
                                                    </c:when>
                                                    <c:when test="${user.role == 'ADMIN'}">
                                                        <option value="CLIENT"><fmt:message key="common.client" /></option>
                                                        <option value="BOOKMAKER"><fmt:message key="common.bookmaker.caps" /></option>
                                                        <option value="ADMIN" selected><fmt:message key="common.admin" /></option>
                                                    </c:when>
                                                </c:choose>
                                            </select>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div class="switch">
                                        <label id="${user.id}"> <fmt:message key="common.no" />
                                            <c:choose>
                                                <c:when test="${user.isBanned()}">
                                                    <input id="checkIt_${user.id}" type="checkbox" checked>
                                                </c:when>
                                                <c:otherwise>
                                                    <input id="checkIt_${user.id}" type="checkbox">
                                                </c:otherwise>
                                            </c:choose> <span class="lever"></span> <fmt:message key="common.yes" />
                                        </label>
                                    </div>
                                </td>
                            </tr>

                            </tbody>
                        </table>
                        <a class="waves-effect waves-light modal-trigger btn green" href="#modal-stats">
                            <div class="button1" id="${user.id}/${user.role}"><i
                                    class="material-icons right">timeline</i><fmt:message key="statistics.h" />
                            </div>
                            ></a>
                        <br>
                        <br>
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
                    <li class="material-icons"><a href="users?type=${type}&page=${page-1}&itemsOnPage=${itemsOnPage}"><i
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
                            <a href="users?type=${type}&page=${i}&itemsOnPage=${itemsOnPage}">
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
                    <li class="material-icons"><a href="users?type=${type}&page=${page+1}&itemsOnPage=${itemsOnPage}"><i
                            class="material-icons">chevron_right</i></a></li>
                </c:otherwise>
            </c:choose>

        </ul>
        <br>
    </div>
</main>

<jsp:include page="common/footer.jsp"></jsp:include>
<jsp:include page="common/scripts.jsp"></jsp:include>

<script src="${pageContext.request.contextPath}/static/js/admin/admin-users.js"></script>

</body>

</html>