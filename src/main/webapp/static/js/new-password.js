$('#new-password-btn').on('click', function (e) {
    var newPassword = $('#new-password-input').val();
    var messageNewPass = $("#messageNewPass");


    if (validatePassword(newPassword,messageNewPass)) {
        $.ajax({
            type: "POST",
            url: "newPassword",
            data: {"newPassword": newPassword},
            success: function (data) {
                var response = JSON.parse(data);

                var status = response.status;
                var message = response.message;

                if (status == 'OK') {
                    $("#new-password-form").hide();
                    $("#go-home-form").show();
                } else {
                    messageNewPass.text(message);
                }
            }
        });
    }
    e.preventDefault();
});
