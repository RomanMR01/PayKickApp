//Remove player
//Update player team to null
$("li.player a").on('click', function () {
    var removeBtnId = $(this).attr("id");

    $('#' + removeBtnId).slideUp(300);
    var values = removeBtnId.split("_");
    var playerID = values[2];

    $.ajax({
        type: "POST",
        url: "removePlayer",
        data: {
            "playerID": playerID
        }
    });

});


//Add player to game
$("a.btn.green.darken-1").click(function () {
    var addPlayerBtn = $(this).attr("id");

    var values = addPlayerBtn.split("_");
    var teamID = values[2];

    $("#addNewPlayer").off().click(function () {
        var firstName = $("#new-player-form #fname").val();
        var surname =$("#new-player-form #surname").val()
        var age = $("#new-player-form #age").val();

        var infoMessage = $('#newPlayerFormMessage');

        if(firstName.length>20 || surname.length>25){
            Materialize.toast("Too long fields!",5000);
            return;
        }
        if(firstName.length==0 || surname.length==0 || age.length==0){
            Materialize.toast("Fields can't be empty!",5000);
            return;
        }if(age<1 || age>100){
            Materialize.toast("Wrong age!",5000);
            return;
        } else{
            var playerName = firstName + " " + surname;

            $.ajax({
                type: "POST",
                url: "addNewPlayer",
                data: {
                    "teamID": teamID,
                    "playerName": playerName,
                    "age":age
                },
                success: function (data) {
                    var response = JSON.parse(data);

                    var status = response.status;
                    var message = response.message;

                    if (status == 'OK') {
                        $('#add-player-modal').closeModal();
                        var list = '<li class="collection-item player"><i class="material-icons left green-text">person</i>' + playerName + '<a href="#"><i class="material-icons right red-text">clear</i></a></li>';
                        $("ul #playersInTeam_" + teamID).append(list);
                    } else {
                        Materialize.toast(message,5000);
                    }
                }
            });
        }


    });

    $("#addExistingPlayer").off().click(function () {

        var infoMessage = $('#existingPlayerFormMessage');

        var playerName = $("#existing-player").val();

        if (playerName.length == 0) {
            Materialize.toast("Fields can't be empty!",5000);
            return;
        }else {
            $.ajax({
                type: "POST",
                url: "addExistingPlayer",
                data: {
                    "teamID": teamID,
                    "playerName": playerName
                },
                success: function (data) {
                    var response = JSON.parse(data);

                    var status = response.status;
                    var message = response.message;

                    if (status == 'OK') {
                        $('#add-player-modal').closeModal();
                        var list = '<li class="collection-item player"><i class="material-icons left green-text">person</i>' + playerName + '<a href="#"><i class="material-icons right red-text">clear</i></a></li>';
                        $("ul #playersInTeam_" + teamID).append(list);
                    } else {
                        Materialize.toast(message,5000);
                    }
                }
            });
        }

    });

});

//Add new Team
$("#createTeamBtn").click(function () {
    var teamName = $("#teamTitle").val();
    var teamLocation = $("#teamLocation").val();

    if(teamName.length>50 || teamLocation.length>50){
        Materialize.toast("Too long fields!",5000);
        return;
    }
    var infoMessage = $('#newTeamFormMessage');
    if (teamName.length == 0 || teamLocation.length == 0) {
        Materialize.toast("Fields can't be empty!",5000);
    } else {
        $.ajax({
            type: "POST",
            url: "createTeam",
            data: {
                "teamName": teamName,
                "teamLocation": teamLocation
            },
            success: function (data) {
                var response = JSON.parse(data);

                var status = response.status;
                var message = response.message;

                if (status == 'OK') {
                    $('#new-team-modal').closeModal();
                    window.location = 'teams';
                } else {
                    Materialize.toast(message,5000);
                }
            }
        });
    }
});

