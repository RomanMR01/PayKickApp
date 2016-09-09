// /Method for getting upcoming matches with ajax

    $(document).ready(function () {
        var $container = $("#newMatches");
        var getNewMatches = function () {
            $.ajax({
                url: "homeStatistics",
                data: {type: 'newMatches'},
                type: "POST",
                success: function (responseText) {
                    $container.find("li").remove();
                    var response = JSON.parse(responseText);

                    /*
                     Writing each collapsible into #newMatches
                     */
                    $.each(response, function (i, item) {
                        var header = '<div class="collapsible-header center-align"><i class="material-icons">reorder</i><span class="orange-text"><strong>' + response[i].title + ':' + '</strong></span>' +  response[i].firstTeam + ' - ' + response[i].secondTeam + '</div>'
                        var body = '<div class="collapsible-body"> <table class="centered">'
                        var thead = '<thead> <tr> <th>1</th> <th>X</th> <th>2</th> <th>1X</th> <th>X2</th> <th>12</th> </tr> </thead>';
                        var coeff = '<tbody><tr> <td>' + response[i].c1 + '</td> <td>' + response[i].cX + '</td> <td>' + response[i].c2 + '</td> <td>' + response[i].c1X + '</td> <td>' + response[i].cX2 + '</td> <td>' + response[i].c12 + '</td> </tr></tbody>';
                        var full = header + body + thead + coeff + '</table></div>';

                        $('<li>').html(full).appendTo('#newMatches');
                    });
                    //expandAll();
                }

            });
        }
        getNewMatches();
        setInterval(getNewMatches, 20000);
    });


//Open all collapsible
function expandAll(){
    $(".collapsible-header").addClass("active");
    $(".collapsible").collapsible({accordion: false});
}