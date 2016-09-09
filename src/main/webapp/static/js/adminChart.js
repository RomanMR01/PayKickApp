$(document).ready(function () {
    console.log("admin js");
    var diagram_info = [];

    var win = 0;
    var loss = 0;
    $.ajax({
        type: "POST",
        data: {"type": "all_bats"},
        url: "chart",
        success: function (data) {
            $.each(data, function (index, value) {
                diagram_info.push([Date.parse(value.date), value.award]);
                if (value.status === "WON") {
                    win = win + value.award;
                    console.log("win" + win);
                } else if (value.status === "LOST") {
                    loss = loss - value.award;
                    console.log("loss" + loss);
                }
            });
            chart_info = [
                ['Wins', win],
                ['Loss', loss],
            ];

            /*diagram*/
            $('#admin_diagram').highcharts({
                chart: {
                    zoomType: 'x'
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