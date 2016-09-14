$('select').on('change', function() {
    var userID = $(this).attr("id");
    var role = $(this).val();

    $.ajax({
        type: "POST",
        url: "updateUser",
        data: {
            "type": "ROLE",
            "userID":userID,
            "role":role
        },
        success: function (data) {
            var response = JSON.parse(data);

            var status = response.status;
            var message = response.message;


            Materialize.toast(message,5000);

        }
    });
});

$(".switch label").on('change',function(){
    var userID = $(this).attr("id");
    var isBanned = $("#checkIt_"+userID).is(':checked');

    $.ajax({
        type: "POST",
        url: "updateUser",
        data: {
            "type": "BANN",
            "userID":userID,
            "bann":isBanned
        },
        success: function (data) {
            var response = JSON.parse(data);

            var status = response.status;
            var message = response.message;

            Materialize.toast(message,5000);

        }
    });
});