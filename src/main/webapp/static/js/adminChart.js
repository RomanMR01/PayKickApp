$(document).ready(function () {
    console.log("admin js");
    var diagram_info = [];

    var win = 0;
    var loss = 0;
    $.ajax({
        type: "POST",
        data: {
            "type": "all_bats"
        },
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
                    zoomType: 'x',
                    type: 'line',
                    backgroundColor: '#EEEEEE'
                },
                title: {
                    text: 'Profits'
                },
                subtitle: {
                    text: document.ontouchstart === undefined ?
                        'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
                },
                xAxis: {
                    /* type: 'datetime'*/
                },
                yAxis: {
                    type: 'logarithmic',
                    title: {
                        text: '$'
                    }
                },
                legend: {
                    enabled: false
                },
                
                credits: {
                    enabled: false
                },
                series: [{
                    marker: {
                        enabled: true,
                        radius: 5
                    },
                    color: '#00C853',
                    name: 'Profit',
                    data: diagram_info
                }]
            });
        }
    });
});