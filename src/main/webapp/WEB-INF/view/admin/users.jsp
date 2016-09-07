<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
                        <ul class="tabs transparent">
                            <li class="tab col s4 l2"><a class="active" href="">ALL</a></li>
                            <li class="tab col s4 l2"><a href="">USERS</a></li>
                            <li class="tab col s4 l2"><a href="">BOOKMAKERS</a></li>
                        </ul>
                    </div>
                </div>
                <ul class="collapsible popout" data-collapsible="expandable">
                    <li>
                        <div class="collapsible-header center-align"><i class="material-icons green-text">person_pin</i><span class="green-text"><strong>markiss111</strong></span></div>
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
                                        <td>Arsen Nikitenko</td>
                                        <td>Male</td>
                                        <td>23</td>
                                        <td>markiss.nikita@mail.ru</td>
                                        <td>$535.00</td>
                                        <td>
                                            <div class="input-field col l1">
                                                <select>
                                                    <option value="user" selected>User</option>
                                                    <option value="bookmaker">Bookmaker</option>
                                                    <option value="admin">Admin</option>
                                                </select>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="switch">
                                                <label>
                                                    No
                                                    <input type="checkbox">
                                                    <span class="lever"></span> Yes
                                                </label>
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <a class="waves-effect waves-light modal-trigger btn green" href="#modal-stats"><i class="material-icons right">timeline</i>Statistics</a>
                            <br>
                            <br>
                        </div>
                    </li>
                    <li>
                        <div class="collapsible-header center-align"><i class="material-icons red-text">perm_identity</i><span class="red-text"><strong>bodya12</strong></span></div>
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
                                        <td>Arsen Nikitenko</td>
                                        <td>Male</td>
                                        <td>23</td>
                                        <td>markiss.nikita@mail.ru</td>
                                        <td>$535.00</td>
                                        <td>
                                            <div class="input-field col l1">
                                                <select>
                                                    <option value="user">User</option>
                                                    <option value="bookmaker" selected>Bookmaker</option>
                                                    <option value="admin">Admin</option>
                                                </select>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="switch">
                                                <label>
                                                    No
                                                    <input type="checkbox" checked>
                                                    <span class="lever"></span> Yes
                                                </label>
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <a class="waves-effect waves-light modal-trigger btn green" href="#modal-stats"><i class="material-icons right">timeline</i>Statistics</a>
                            <br>
                            <br>
                        </div>
                    </li>
                </ul>
                <ul class="pagination center-align">
                    <li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
                    <li class="active green"><a href="#!">1</a></li>
                    <li class="waves-effect"><a href="#!">2</a></li>
                    <li class="waves-effect"><a href="#!">3</a></li>
                    <li class="waves-effect"><a href="#!">4</a></li>
                    <li class="waves-effect"><a href="#!">5</a></li>
                    <li class="waves-effect"><a href="#!"><i class="material-icons">chevron_right</i></a></li>
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