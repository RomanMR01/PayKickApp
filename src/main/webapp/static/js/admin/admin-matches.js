//Setting game results
$("a.btn.green.darken-1").click(function () {

    var chipId = $(this).attr("id");
    var values = chipId.split("_");

    var gameID = values[1];
    var firstTeamID = values[3];
    var secondTeamID = values[5];

    var firstInputId = "input#g_" + gameID + "_t_" + firstTeamID;
    var secondInputId = "input#g_" + gameID + "_t_" + secondTeamID;

    var firstTeamScore = parseInt($(firstInputId).val());
    var secondTeamScore = parseInt($(secondInputId).val());

    var firstTeamPlayers = "";
    var secondTeamPlayers = "";

    $("#chip_" + gameID + "_" + firstTeamID).find(".chip").each(function () {
        var txt = $(this).text();
        firstTeamPlayers += txt.substring(0, txt.length - 5) + ";";
    });

    $("#chip_" + gameID + "_" + secondTeamID).find(".chip").each(function () {
        var txt = $(this).text();
        secondTeamPlayers += txt.substring(0, txt.length - 5) + ";";
    });

    $.ajax({
        type: "POST",
        url: "addWinner",
        data: {
            "gameID": gameID,
            "firstTeamID": firstTeamID,
            "secondTeamID": secondTeamID,
            "firstTeamScore": firstTeamScore,
            "secondTeamScore": secondTeamScore,
            "firstTeamPlayers": firstTeamPlayers,
            "secondTeamPlayers": secondTeamPlayers
        },
        success: function (data) {
            var response = JSON.parse(data);

            var status = response.status;
            var message = response.message;

            if (status == 'OK') {
                Materialize.toast(message,5000);
                $("li#"+gameID + "").hide();
            } else {
                Materialize.toast(message,5000);
            }

        }
    });
});

//Canceling game
$("a.btn.red").click(function () {

    var gameId = $(this).attr("id");
    //Canceling game

    $.ajax({
        type: "POST",
        url: "cancelGame",
        data: {
            "gameID": gameId
        },
        success: function (data) {
            var val = $("li a.active").text();//Active tab
            window.location = 'matches?type=' + val;//Reopen this tab
        }
    });
});

//Add new game
$('#addGameBtn').on('click', function (e) {
    var title = $('#title').val();
    var location = $('#location').val();
    var date = $('#date').val();
    var firstTeamName = $('#first-team').val();
    var secondTeamName = $('#second-team').val();
    var bookmaker = $('#bookmaker').val();

    var messageAddGame = $("#messageAddGame");


    if(title==null || location==null || date==null || firstTeamName==null || secondTeamName==null || bookmaker==null){
        Materialize.toast("Found empty fields!",5000);
        return;
    }
    if(title.length==0 || location.length==0 || date.length==0 || firstTeamName.length==0 || secondTeamName.length==0 || bookmaker.length==0){
        Materialize.toast("Found empty fields!",5000);
        return;
    }

    if(firstTeamName==secondTeamName){
        Materialize.toast("Teams can't be the same!",5000);
        return;
    }

    $.ajax({
        type: "POST",
        url: "addGame",
        data: {
            "title": title,
            "location": location,
            "date": date,
            "firstTeamName": firstTeamName,
            "secondTeamName": secondTeamName,
            "bookmaker": bookmaker
        },
        success: function (data) {
            var response = JSON.parse(data);

            var status = response.status;
            var message = response.message;

            if (status == 'OK') {
                $('#new-match-modal').closeModal();
                window.location = 'matches?type=NEW';
            } else {
                Materialize.toast(message,5000);
            }
        }
    });
});