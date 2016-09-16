$(".button1").click(function (event) {
    var id = event.target.id;
    var values = id.split('/');

    id = values[0];
    var role = values[1];
    var chartType;
    if (role === "BOOKMAKER") {
        chartType = 'bookmaker_games';
    } else {
        chartType = 'user_bat';
    }
    var diagram_info = [];
    var chart_info = [];
    var win = 0;
    var loss = 0;
    var drawCh = function () {
        $.ajax({
            type: "POST",
            data: {
                "type": chartType,
                "id": id
            },
            url: "chart",
            success: function (data) {
                var allProfit = 0;
                var currentBookmakerProfit = 0;
                $.each(data, function (index, value) {
                    if (value.id === -1) {
                        allProfit = value.profit;
                    } else {
                        if (role === "BOOKMAKER") {
                            diagram_info.push([Date.parse(value.date), value.profit]);
                            currentBookmakerProfit = currentBookmakerProfit + value.profit;
                        } else {
                            diagram_info.push([Date.parse(value.date), value.award]);
                            if (value.status === "WON") {
                                win = win + value.award;
                            } else if (value.status === "LOST") {
                                loss = loss + value.award;
                            }
                        }
                    }
                });
                if (role === "BOOKMAKER") {
                    chart_info = [
                    ['All profit', allProfit-currentBookmakerProfit],
                    ['Current bookmaker profit', currentBookmakerProfit]
                ];
                } else {
                    chart_info = [
                ['Wins', win],
                ['Loss', loss]
            ];
                }
                var title;
                if (role !== "BOOKMAKER") {
                    title = "Bets Results";
                    if (win === 0 && loss === 0) {
                        chart_info = [];
                        diagram_info = [];
                    }
                } else {
                    title = "Games Results";
                }
                /*diagram*/
                $('#diagram').highcharts({
                    chart: {
                        type: 'pie',
                        backgroundColor: '#FAFAFA',
                    },
                    title: {
                        text: title
                    },
                    /*
                                    tooltip: {
                                        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                                    },*/
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
                        name: 'Amount',
                        colors: ['#64DD17', '#FF5722'],
                        colorByPoint: true,
                        data: chart_info
                }]
                });


                /*chart*/
                $('#chart').highcharts({
                    chart: {
                        zoomType: 'x',
                        type: 'line',
                        backgroundColor: '#FAFAFA'
                    },
                    title: {
                        text: 'Profits'
                    },
                    subtitle: {
                        text: document.ontouchstart === undefined ?
                            'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
                    },
                    xAxis: {
                        type: 'datetime'
                    },
                    yAxis: {
                        title: {
                            text: '$'
                        }
                    },
                    legend: {
                        enabled: false
                    },
                    plotOptions: {
                        area: {
                            fillColor: {
                                linearGradient: {
                                    x1: 0,
                                    y1: 0,
                                    x2: 0,
                                    y2: 1
                                },
                                stops: [
                                [0, '#FF9800'],
                                [1, '#FF9800']
                            ]
                            },
                            marker: {
                                radius: 2
                            },
                            lineWidth: 1,
                            states: {
                                hover: {
                                    lineWidth: 1
                                }
                            },
                            threshold: null
                        }
                    },
                    credits: {
                        enabled: false
                    },
                    series: [{
                        marker: {
                            enabled: true,
                            radius: 5
                        },
                        type: 'area',
                        color: '#FF9800',
                        name: 'win amount',
                        data: diagram_info
                }]
                });
            }
        });
    }
    drawCh();
    $('ul.tabs').tabs({
        onShow: function () {
            var b = $(this).attr("href");
            $(b).fadeOut(1).fadeIn(30);
            drawCh();
        }
    });

});