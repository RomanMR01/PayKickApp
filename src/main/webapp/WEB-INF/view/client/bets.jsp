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
                <title>Bookmaker - Matches</title>

                <jsp:include page="common/styles.jsp"></jsp:include>
                <jsp:include page="common/scripts.jsp"></jsp:include>
            </head>

            <body>
                <jsp:include page="common/navigation.jsp"></jsp:include>

                <!-- Main Content -->
                <main class="valign-wrapper grey lighten-3 bookmaker">
                    <div class="container valign" style="margin-top: 20px;">
                        <h4 class="center-align">Matches:</h4>
                        <br>
                        <div class="row">
                            <div class="col s12 l8 offset-l2">
                                <ul class="tabs transparent">

                                    <li class="tab col s4 l2"><a href="#test1" target="_self">ALL</a></li>
                                    <li class="tab col s4 l2"><a class="active" href="#test2" target="_self">NEW</a></li>
                                    <li class="tab col s4 l2"><a href="#test3" target="_self">ACTIVE</a></li>
                                    <li class="tab col s4 l2"><a href="#test4" target="_self">PAST</a></li>
                                    <li class="tab col s4 l2"><a href="#test5" target="_self">CANCELED</a></li>
                                </ul>
                            </div>
                        </div>
                        <ul class="collapsible" data-collapsible="accordion">
                            <li>
                                <div class="collapsible-header center-align"><i class="material-icons orange-text">list</i><span class="grey-text">Title.</span><span class="orange-text"><strong>Barca - Real</strong></span></div>
                                <div class="collapsible-body center-align">
                                    <div class="row">
                                        <table class="centered responsive-table col s6 offset-s3">
                                            <thead>
                                                <tr>
                                                    <th>Location</th>
                                                    <th>Date</th>
                                                    <th>Profit</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>Lviv Arena</td>
                                                    <td>12.05.2015 16:40</td>
                                                    <td>+ $500.00</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <div class="col s12 center-align">
                                            <hr>
                                            <br>
                                            <h5>Result Coefficients:</h5>
                                            <table class="centered responsive-table col s10 offset-s1 big-height">
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
                                                        <td>
                                                            <div class="input-field col s12">
                                                                <input id="coef_1" type="number" step="0.01">
                                                                <label for="coef_1">Coefficient</label>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <div class="input-field col s12">
                                                                <input id="coef_2" type="number" step="0.01">
                                                                <label for="coef_2">Coefficient</label>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <div class="input-field col s12">
                                                                <input id="coef_X" type="number" step="0.01">
                                                                <label for="coef_X">Coefficient</label>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <div class="input-field col s12">
                                                                <input id="coef_1X" type="number" step="0.01">
                                                                <label for="coef_1X">Coefficient</label>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <div class="input-field col s12">
                                                                <input id="coef_X2" type="number" step="0.01">
                                                                <label for="coef_X2">Coefficient</label>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <div class="input-field col s12">
                                                                <input id="coef_12" type="number" step="0.01">
                                                                <label for="coef_12">Coefficient</label>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="col s12 center-align">
                                            <hr>
                                            <br>
                                            <h5>Goals Coefficients:</h5>
                                            <table class="centered responsive-table col s6 offset-s3 big-height">
                                                <thead>
                                                    <tr>
                                                        <th>Barca Goal</th>
                                                        <th>Start Coefficient</th>
                                                        <th>Real Goal</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>
                                                            <div class="input-field col s12">
                                                                <input id="coef_start" type="number" step="0.01">
                                                                <label for="coef_start">Coefficient</label>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <div class="input-field col s12">
                                                                <input id="coef_t1_id" type="number" step="0.01">
                                                                <label for="coef_t1_id">Coefficient</label>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <div class="input-field col s12">
                                                                <input id="coef_t2_id" type="number" step="0.01">
                                                                <label for="coef_t2_id">Coefficient</label>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <br>
                                        <br>
                                        <div class="col s12 center-align">
                                            <hr>
                                            <br>
                                            <h5>Players Coefficients:</h5>
                                            <br>
                                            <div class="row">
                                                <div class="col s4 offset-s2 m3 offset-m3">
                                                    <h6><strong>Barcelona</strong></h6>
                                                    <div class="input-field col s12">
                                                        <input id="player1" type="number" step="0.01">
                                                        <label for="player1">Player 1</label>
                                                    </div>
                                                    <div class="input-field col s12">
                                                        <input id="player2" type="number" step="0.01">
                                                        <label for="player2">Player 2</label>
                                                    </div>
                                                    <div class="input-field col s12">
                                                        <input id="player3" type="number" step="0.01">
                                                        <label for="player3">Player 3</label>
                                                    </div>
                                                </div>
                                                <div class="col s4 m3">
                                                    <h6><strong>Real Madrid</strong></h6>
                                                    <div class="input-field col s12">
                                                        <input id="player4" type="number" step="0.01">
                                                        <label for="player4">Player 4</label>
                                                    </div>
                                                    <div class="input-field col s12">
                                                        <input id="player5" type="number" step="0.01">
                                                        <label for="player5">Player 5</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <a class="waves-effect waves-light btn green"><i class="material-icons right">done</i>Confirm</a>
                                    <br><br>
                                </div>
                            </li>
                        </ul>
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

                <jsp:include page="common/footer.jsp"></jsp:include>

            </body>

            </html>