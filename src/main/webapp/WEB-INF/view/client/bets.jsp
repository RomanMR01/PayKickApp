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
                <title>PayKick - My Bets</title>

                <jsp:include page="common/styles.jsp"></jsp:include>
                <jsp:include page="common/scripts.jsp"></jsp:include>
            </head>

            <body>
                <jsp:include page="common/navigation.jsp"></jsp:include>

                <!-- Main Content -->
                <main class="valign-wrapper grey lighten-3">
                    <div class="container valign" style="margin-top: 20px;">
                        <h4 class="center-align">My Bets:</h4>
                        <br>
                        <div class="row">
                            <div class="col s12 l8 offset-l2">
                                <ul class="tabs transparent">
                                    <li class="tab col s4 l2"><a href="">ALL</a></li>
                                    <li class="tab col s4 l2"><a href="">ACTIVE</a></li>
                                    <li class="tab col s4 l2"><a href="">WON</a></li>
                                    <li class="tab col s4 l2"><a href="">LOST</a></li>
                                    <li class="tab col s4 l2"><a href="">CANCELED</a></li>
                                </ul>
                            </div>
                        </div>
                        <ul class="collapsible popout" data-collapsible="expandable">
                            <li>
                                <div class="collapsible-header center-align"><i class="material-icons orange-text">receipt</i><span class="orange-text"><strong>#0532412 - ACTIVE</strong></span></div>
                                <div class="collapsible-body center-align">
                                    <div class="row">
                                        <table class="centered responsive-table col s10 offset-s1">
                                            <thead>
                                                <tr>
                                                    <th>Date</th>
                                                    <th>Match</th>
                                                    <th>Type</th>
                                                    <th>Bet</th>
                                                    <th>Amount</th>
                                                    <th>Coefficient</th>
                                                    <th>Award</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>25.10.2016</td>
                                                    <td>Barcelona - Real</td>
                                                    <td>Result</td>
                                                    <td>1X</td>
                                                    <td>—</td>
                                                    <td>1.50</td>
                                                    <td>—</td>
                                                </tr>
                                                <tr>
                                                    <td>01.10.2016</td>
                                                    <td>Manchester - Liverpool</td>
                                                    <td>Player</td>
                                                    <td>Agueiro</td>
                                                    <td>—</td>
                                                    <td>1.50</td>
                                                    <td>—</td>
                                                </tr>
                                                <tr class="orange-text" style="border-top: 1px solid #d0d0d0;">
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td><strong>$100.00</strong></td>
                                                    <td><strong>2.25</strong></td>
                                                    <td><strong>$225.00</strong></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="collapsible-header center-align"><i class="material-icons green-text">receipt</i><span class="green-text"><strong>#0938911 - WON</strong></span></div>
                                <div class="collapsible-body center-align">
                                    <div class="row">
                                        <table class="centered responsive-table col s10 offset-s1">
                                            <thead>
                                                <tr>
                                                    <th>Date</th>
                                                    <th>Match</th>
                                                    <th>Type</th>
                                                    <th>Bet</th>
                                                    <th>Amount</th>
                                                    <th>Coefficient</th>
                                                    <th>Award</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>25.10.2016</td>
                                                    <td>Barcelona - Real</td>
                                                    <td>Result</td>
                                                    <td>1X</td>
                                                    <td>—</td>
                                                    <td>1.50</td>
                                                    <td>—</td>
                                                </tr>
                                                <tr>
                                                    <td>01.10.2016</td>
                                                    <td>Manchester - Liverpool</td>
                                                    <td>Player</td>
                                                    <td>Agueiro</td>
                                                    <td>—</td>
                                                    <td>1.50</td>
                                                    <td>—</td>
                                                </tr>
                                                <tr class="green-text" style="border-top: 1px solid #d0d0d0;">
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td><strong>$100.00</strong></td>
                                                    <td><strong>2.25</strong></td>
                                                    <td><strong>$225.00</strong></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="collapsible-header center-align"><i class="material-icons red-text">receipt</i><span class="red-text"><strong>#0251664 - LOST</strong></span></div>
                                <div class="collapsible-body center-align">
                                    <div class="row">
                                        <table class="centered responsive-table col s10 offset-s1">
                                            <thead>
                                                <tr>
                                                    <th>Date</th>
                                                    <th>Match</th>
                                                    <th>Type</th>
                                                    <th>Bet</th>
                                                    <th>Amount</th>
                                                    <th>Coefficient</th>
                                                    <th>Award</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>25.10.2016</td>
                                                    <td>Barcelona - Real</td>
                                                    <td>Result</td>
                                                    <td>1X</td>
                                                    <td>—</td>
                                                    <td>1.50</td>
                                                    <td>—</td>
                                                </tr>
                                                <tr>
                                                    <td>01.10.2016</td>
                                                    <td>Manchester - Liverpool</td>
                                                    <td>Player</td>
                                                    <td>Agueiro</td>
                                                    <td>—</td>
                                                    <td>1.50</td>
                                                    <td>—</td>
                                                </tr>
                                                <tr class="red-text" style="border-top: 1px solid #d0d0d0;">
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td><strong>$100.00</strong></td>
                                                    <td><strong>2.25</strong></td>
                                                    <td><strong>$225.00</strong></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="collapsible-header center-align"><i class="material-icons grey-text">receipt</i><span class="grey-text"><strong>#0992747 - CANCELED</strong></span></div>
                                <div class="collapsible-body center-align">
                                    <div class="row">
                                        <table class="centered responsive-table col s10 offset-s1">
                                            <thead>
                                                <tr>
                                                    <th>Date</th>
                                                    <th>Match</th>
                                                    <th>Type</th>
                                                    <th>Bet</th>
                                                    <th>Amount</th>
                                                    <th>Coefficient</th>
                                                    <th>Award</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>25.10.2016</td>
                                                    <td>Barcelona - Real</td>
                                                    <td>Result</td>
                                                    <td>1X</td>
                                                    <td>—</td>
                                                    <td>1.50</td>
                                                    <td>—</td>
                                                </tr>
                                                <tr>
                                                    <td>01.10.2016</td>
                                                    <td>Manchester - Liverpool</td>
                                                    <td>Player</td>
                                                    <td>Agueiro</td>
                                                    <td>—</td>
                                                    <td>1.50</td>
                                                    <td>—</td>
                                                </tr>
                                                <tr class="grey-text" style="border-top: 1px solid #d0d0d0;">
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td><strong>$100.00</strong></td>
                                                    <td><strong>2.25</strong></td>
                                                    <td><strong>$225.00</strong></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>

                </main>

                <jsp:include page="common/footer.jsp"></jsp:include>

            </body>

            </html>