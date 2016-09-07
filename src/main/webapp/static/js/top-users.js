(function ($) {
    $(document).ready(function () {
        var $container = $("#topUsers");
        var getTopUsers = function () {

            $.ajax({
                url: "homeStatistics",
                data: {type: 'topUsers'},
                type: "POST",
                success: function (responseText) {
                    $container.find("tr").remove();
                    var response = JSON.parse(responseText);

                    /*
                     Writing each table row element into #topUsers table
                     */
                    $.each(response, function (i, item) {
                        var lastBet = Number(response[i].lastBetSum);
                        var award = Number(response[i].awardSum);

                        lastBet = lastBet.toFixed(2);
                        award = award.toFixed(2);
                        var td = "";
                        if(lastBet>0){
                            td = '<td class="green-text">'  + "+ $" + lastBet + '</td>';
                        }else{
                            lastBet = -lastBet;
                            lastBet = lastBet.toFixed(2);
                            td = '<td class="red-text">'  + '- $' + lastBet + '</td>';
                        }

                        var td2 = "";
                        if(award>0){
                            td2 = '<td class="black-text">'  + "+ $" + award + '</td>';
                        }else{
                            award = -award;
                            award = award.toFixed(2);
                            td2 = '<td class="black-text">'  + '- $' + award + '</td>';
                        }
                        $('<tr>').html("<td>" + response[i].login + "</td>" + td2 + td).appendTo('#topUsers');
                    });
                }

            });
        }
        getTopUsers();//First call when page ready
        setInterval(getTopUsers, 5000);//Interval call every 5 seconds
    });
})(jQuery);