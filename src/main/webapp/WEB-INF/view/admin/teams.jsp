<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <!DOCTYPE html>

    <html>

    <head>

        <!-- Meta -->
        <meta charset="utf-8">

        <!-- Mobile Metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin - Manage Teams</title>

        <jsp:include page="common/styles.jsp"></jsp:include>
    </head>

    <body>
        <jsp:include page="common/navigation.jsp"></jsp:include>

        <!-- Main Content -->
        <main class="valign-wrapper grey lighten-3">
            <div class="container valign" style="margin-top: 20px;">
                <h4 class="center-align">Teams:</h4>
                <br>
                <ul class="collapsible popout" data-collapsible="expandable">
                    <li>
                        <div class="collapsible-header center-align"><i class="material-icons green-text">group</i><span class="green-text"><strong>Barcelona</strong></span></div>
                        <div class="collapsible-body center-align">
                            <div class="row">
                                <br>
                                <ul class="collection col s10 offset-s1">
                                    <li id="oscar-dovaahkin-container" class="collection-item player"><i class="material-icons left green-text">person</i>Oscar Dovaahkin<a id="oscar-dovaahkin" href="#"><i class="material-icons right red-text">clear</i></a></li>

                                    <li id="lionel-messi-container" class="collection-item player"><i class="material-icons left green-text">person</i>Lionel Messi<a id="lionel-messi" href="#"><i class="material-icons right red-text">clear</i></a></li>
                                    <li id="machen-drachen-container" class="collection-item player"><i class="material-icons left green-text">person</i>Machen Drachen<a id="machen-drachen" href="#"><i class="material-icons right red-text">clear</i></a></li>
                                    <li id="arsen-nikinak-container" class="collection-item player"><i class="material-icons left green-text">person</i>Arsen Nikinak<a id="arsen-nikinak" href="#"><i class="material-icons right red-text">clear</i></a></li>
                                    <li id="bitte-dritte-container" class="collection-item player"><i class="material-icons left green-text">person</i>Bitte Dritte<a id="bitte-dritte" href="#"><i class="material-icons right red-text">clear</i></a></li>

                                </ul>

                            </div>
                            <a class="waves-effect waves-light btn modal-trigger green" href="#add-player-modal" target="_self"><i class="material-icons right">person_add</i>Add Player</a>
                            <br>
                            <br>
                        </div>
                    </li>
                </ul>
                <br>
                <a class="waves-effect waves-light modal-trigger btn right green" href="#new-team-modal"><i class="material-icons right">group_add</i>New Team</a>
                <br>
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

        <!-- Add Player Modal Structure -->
        <div id="add-player-modal" class="modal my-narrow">
            <div class="modal-content">
                <div class="row">
                    <div class="col s12">
                        <h5 class="center-align">Add Player</h5>
                        <br>
                        <ul class="tabs transparent">
                            <li class="tab col s6"><a href="#new-player-form" class="active">New</a></li>
                            <li class="tab col s6"><a href="#existing-player-form">Existing</a></li>
                        </ul>
                    </div>
                    <form id="new-player-form" class="col s12">
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="fname" type="text">
                                <label for="fname">First Name</label>
                            </div>
                            <div class="input-field col s12">
                                <input id="surname" type="text">
                                <label for="surname">Surname</label>
                            </div>
                            <div class="col s12">
                                <p class="big">Age:</p>
                                <p class="range-field">
                                    <input type="range" id="age" min="0" max="100" value="0" />
                                </p>
                            </div>
                        </div>
                        <div class="col s12 center-align">
                            <button class="btn waves-effect waves-light" type="submit" name="action">Add Player
                                <i class="material-icons right">person_add</i>
                            </button>
                            <br>
                            <br>
                            <span class="center-align red-text" id="messageRegistration"></span>
                        </div>
                    </form>
                    <form id="existing-player-form" class="col s12">
                        <div class="row">
                            <div class="input-field col s12">
                                <input type="text" id="existing-player" class="autocomplete player-input" autocomplete="off">
                                <label for="existing-player">Player</label>
                            </div>
                        </div>
                        <div class="col s12 center-align">
                            <button class="btn waves-effect waves-light" type="submit" name="action">Add Player
                                <i class="material-icons right">person_add</i>
                            </button>
                            <br>
                            <br>
                            <span class="center-align red-text" id="messageRegistration"></span>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- New Team Modal Structure -->
        <div id="new-team-modal" class="modal my-narrow">
            <div class="modal-content">
                <div class="row">
                    <h5 class="center-align">New Team</h5>
                    <br>
                    <form class="col s12">
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="title" type="text">
                                <label for="title">Name</label>
                            </div>
                            <div class="input-field col s12">
                                <input id="location" type="text">
                                <label for="location">Location</label>
                            </div>
                        </div>
                        <div class="col s12 center-align">
                            <button class="btn waves-effect waves-light" type="submit" name="action">Create Team
                                <i class="material-icons right">group_add</i>
                            </button>
                            <br>
                            <br>
                            <span class="center-align red-text" id="messageRegistration"></span>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <jsp:include page="common/footer.jsp"></jsp:include>
        <jsp:include page="common/scripts.jsp"></jsp:include>

        <!-- Here must be generated all the players -->
        <script>
            $(function () {
                $('input.autocomplete.player-input').autocomplete({
                    data: {
                        "Andrew Bright": null,
                        "Jim Carey": null,
                        "Adam Beast": null,
                        "Jessica Alba": null,
                        "Bald From Brazzers": null
                    }
                });
            });
        </script>


        <script>
            $("li.player a").on('click', function () {
                var removeBtnId = $(this).attr("id");
                removeBtnId = "#" + removeBtnId + "-container";
                $(removeBtnId).slideUp(300);
                //Here must be appended ajax for unbinding player from team
            });
        </script>

    </body>

    </html>