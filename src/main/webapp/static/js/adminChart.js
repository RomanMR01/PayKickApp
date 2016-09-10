$(document).ready(function () {
    console.log("admin js");

    var dateLabels = [];
    var  backgroundColors = [];
    var  borderColors = [];
    var  profitData = [];
    var  hoverBackgroundColors = [];
    $.ajax({
        type: "POST",
        data: {"type": "MAIN_CHART"},
        url: "chart",
        success: function (resp) {
            $.each(resp, function (index, value) {
                var time = new Date(Date.parse(value.date));
                dateLabels.push(time.getDate() + "." + (time.getMonth()+1) + "." + time.getFullYear());

                var profit = value.profit;

                if(profit==0){
                    backgroundColors.push('rgba(0, 0, 255, 0.3)');
                    borderColors.push('rgba(0,0,200,0.5)');
                    hoverBackgroundColors.push('rgba(0,0,200,0.5)');
                }else if(profit>0){
                    backgroundColors.push('rgba(0, 255, 0, 0.3)');
                    borderColors.push('rgba(0,200,0,0.5)');
                    hoverBackgroundColors.push('rgba(0,200,0,0.5)');
                }else if(profit<0){
                    backgroundColors.push('rgba(255, 0, 0, 0.3)');
                    borderColors.push('rgba(200, 0, 0, 0.5)');
                    hoverBackgroundColors.push('rgba(200, 0, 0, 0.5)');
                }

                profitData.push(profit);
                // console.log(value.profit)
                // if (value.status === "WON") {
                //     win = win + value.award;
                //     console.log("win" + win);
                // } else if (value.status === "LOST") {
                //     loss = loss - value.award;
                //     console.log("loss" + loss);
                // }
            });

            var ctx = document.getElementById("mainChart");
            
            var data = {
                labels: dateLabels,
                datasets: [
                    {
                        label: "Profit: ",
                        backgroundColor: backgroundColors,
                        borderColor: borderColors,
                        hoverBackgroundColor:hoverBackgroundColors,
                        borderWidth: 3,
                        data: profitData,
                    },
                ]
            };
            var myBarChart = new Chart(ctx, {
                type: 'bar',
                data: data,
                options: {
                    legend: {
                        display: false,
                    },
                    scales: {
                        xAxes: [{
                            stacked: true,
                            scaleLabel: {
                                display: true,
                                labelString: 'DATE'
                            }
                        }],
                        yAxes: [{
                            stacked: true,
                            scaleLabel: {
                                display: true,
                                labelString: 'PROFIT'
                            }
                        }],

                    }

                }
            });


        }
    });
});