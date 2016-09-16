$(".buttonPlayerChart").click(function (event) {
    var id = event.target.id;
    var values = id.split('/');
    id = values[0];
    var playerName = values[1];
    var chart_info = [];
    var chartType = "player_goals";
    $.ajax({
        type: "POST",
        data: {"type": chartType, "id": id},
        url: "chart",
        success: function (data) {
            var allGoals = 0;
            var allGames = 0;
            $.each(data, function (index, value) {
                if (value.id === -1) {
                    allGoals = value.age;
                } else {
                    allGames = value.totalGames;
                }
            });

            if (allGames === 0 && allGoals === 0) {
                chart_info = [];
            }else {
                chart_info = [
                    ['Total games', allGames],
                    ['Total goals', allGoals]
                ];
            }
            $("#currentHeader").text(playerName);
            /*chart*/
            $('#chart').highcharts({
                chart: {
                    type: 'pie',
                    options3d: {
                        enabled: true,
                        alpha: 45,
                        beta: 0
                    }
                },
                title: {
                    text: 'Game results'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        depth: 35,
                        dataLabels: {
                            enabled: true,
                            format: '{point.name}'
                        }
                    }
                },
                credits: {
                    enabled: false
                },
                series: [{
                    name: 'ammount',
                    colorByPoint: true,
                    data: chart_info
                }]
            });
        }
    });
});

$(".buttonTeamChart").click(function (event) {
    var teamName = event.target.id;
    var chart_info = [];
    var chartType = "team_win";
    var wins = 0;
    var loses = 0;
    var draws = 0;
    $.ajax({
        type: "POST",
        data: {"type": chartType, "teamName": teamName},
        url: "chart",
        success: function (data) {
            wins=data.totalWins;
            loses=data.totalLoses;
            draws=data.totalDraws;
            if (wins === 0 && loses === 0 && draws === 0) {
                chart_info = [];
            }else {
                chart_info = [
                    ['Wins', wins],
                    ['Loss', loses],
                    ['Draw', draws]
                ];
            }
            $("#currentHeader").text(teamName);
            /*chart*/
            $('#chart').highcharts({
                chart: {
                    type: 'pie',
                    options3d: {
                        enabled: true,
                        alpha: 45,
                        beta: 0
                    }
                },
                title: {
                    text: 'Game results'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        depth: 35,
                        dataLabels: {
                            enabled: true,
                            format: '{point.name}'
                        }
                    }
                },
                credits: {
                    enabled: false
                },
                series: [{
                    name: 'game results',
                    colorByPoint: true,
                    data: chart_info
                }]
            });
        }
    });
});