<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <!DOCTYPE html>

    <html>

    <head>

        <!-- Meta -->
        <meta charset="utf-8">

        <!-- Mobile Metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Bookmaker - Edit Account</title>

        <jsp:include page="common/styles.jsp"></jsp:include>
        <jsp:include page="common/scripts.jsp"></jsp:include>
    </head>

    <body>
        <jsp:include page="common/navigation.jsp"></jsp:include>

        <!-- Main Content -->
        <main class="valign-wrapper grey lighten-3">
            <div class="container valign" style="margin-top: 20px;">
                <h4 class="center-align">Edit Account:</h4>
                <br>
                <div class="row">
                    <form id="new-player-form" class="col s12 m8 offset-m2 l6 offset-l3">
                        <div class="row">
                            <div class="input-field col s12">
                                <!-- Replace value -->
                                <input id="fname" type="text" value="Ars">
                                <label for="fname">First Name</label>
                            </div>
                            <div class="input-field col s12">
                                <!-- Replace value -->
                                <input id="surname" type="text" value="Nikinak">
                                <label for="surname">Surname</label>
                            </div>
                            <div class="col s12">
                                <p class="big">Age:</p>
                                <p class="range-field">
                                    <input type="range" id="age" min="0" max="100" value="25" />
                                </p>
                            </div>
                            <div class="col s12">
                                <p class="big">Sex:</p>
                                <p>
                                    <input name="gender" type="radio" id="male" value="male" required checked/>
                                    <label for="male">Male</label>
                                </p>
                                <p>
                                    <input name="gender" type="radio" id="female" value="female" />
                                    <label for="female">Female</label>
                                </p>
                            </div>
                            <div class="input-field col s12">
                                <!-- Replace value -->
                                <input id="password" type="password" value="arsenush">
                                <label for="password">Password</label>
                            </div>
                            <div class="input-field col s12">
                                <!-- Replace value -->
                                <input id="repeat-password" type="password" value="arsenush">
                                <label for="repeat-password">Repeat Password</label>
                            </div>
                        </div>
                        <div class="col s12 center-align">
                            <button class="btn waves-effect waves-light green" type="submit" name="action">Save
                                <i class="material-icons right">done</i>
                            </button>
                            <br>
                            <br>
                            <span class="center-align green-text" id="messageRegistration">Saved</span>
                        </div>
                    </form>
                </div>
            </div>
        </main>

        <jsp:include page="common/footer.jsp"></jsp:include>


    </body>

    </html>