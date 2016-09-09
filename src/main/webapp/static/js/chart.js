console.log("chart js init");

$(".button1").click(function (event) {
    var id = event.target.id;
    console.log("id" + id);
    var diagram_info = [];
    var chart_info = [];
    var win = 0;
    var loss = 0;
    $.ajax({
        type: "POST",
        data: {"type": "user_bat", "id": id},
        url: "chart",
        success: function (data) {
            $.each(data, function (index, value) {
                diagram_info.push([Date.parse(value.date), value.award]);
                if (value.status === "WON") {
                    win = win + value.award;
                    console.log("win" + win);
                } else if (value.status === "LOST") {
                    loss = loss + value.award;
                    console.log("loss" + loss);
                }
            });
            chart_info = [
                ['Wins', win],
                ['Loss', loss],
            ];
            if (win===0&&loss===0){
                chart_info=[];
                diagram_info=[];
            }
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
                },/*
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
                    name: 'ammount',
                    colors: ['#FF5722', '#64DD17'],
                    colorByPoint: true,
                    data: chart_info
                }]
            });


            /*diagram*/
            $('#diagram').highcharts({
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