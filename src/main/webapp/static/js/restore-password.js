$('#restore-email-btn').on('click', function (e) {
    var emailAddress = $('#restore-email').val();
    var messageRestoreFail = $("#messageRestoreFail");

    if (validateEmail(emailAddress,messageRestoreFail)) {
        $.ajax({
            type: "POST",
            url: "restorePassword",
            data: {"email": emailAddress},
            success: function (data) {
                var response = JSON.parse(data);

                var status = response.status;
                var url = response.url;
                var message = response.message;

                if (status == 'OK') {
                    $("#restore-password-form").hide();
                    $("#email-sent-form").show();
                } else {
                    messageRestoreFail.text(message);
                }
            }
        });
    }
    e.preventDefault();
});
