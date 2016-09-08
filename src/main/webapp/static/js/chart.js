'use strict';
$(document).ready(function () {
    $("#statistics").click(function () {
        var diagram_info = [];
        var chart_info = [];
        /*hardcoded id needs to be changed*/
        var win = 100;
        var loss = 0;
        var elseValue = 0;
        $.ajax({
            type: "POST",
            /*hardcoded id needs to be changed*/
            data: {"type": "user_bat", "id": 6},
            url: "chart",
            success: function (data) {
                $.each(data, function (index, value) {
                    diagram_info.push([Date.parse(value.date), value.award]);
                    if (value.status === "WON") {
                        win = win + value.award;
                        console.log("win"+win);
                    } else if (value.status === "LOST") {
                        loss = loss + value.award;
                        console.log("loss"+loss);
                    } else {
                        console.log("else");
                        elseValue = elseValue + value.award;
                        console.log("elseValue"+elseValue);
                    }
                });
                chart_info = [
                    ['Wins', win],
                    ['Loss', loss],
                    ['Unknown bets', elseValue]
                ];

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
                    tooltip: {
                        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
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
                    series:
                        [{
                            name: 'Percentage',
                            colors: ['#64DD17', '#FF5722', '#0091EA'],
                            colorByPoint: true,
                            data: chart_info}]
                });


                /*diagram*/
                $('#diagram').highcharts({
                    chart: {
                        zoomType: 'x'
                    },
                    title: {
                        text: 'Date of bat'
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
                                    [0, Highcharts.getOptions().colors[0]],
                                    [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
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
                        type: 'area',
                        name: 'win amount',
                        data: diagram_info
                    }]
                });
            }
        });
    });
});
