$('#new-password-btn').on('click', function (e) {
    var newPassword = $('#new-password-input').val();
    var messageNewPass = $("#messageNewPass");
    var homePage = $("#homePage");
    
    var formInput =$("#formInput");
    var btn = $("#new-password-btn");

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
                    messageNewPass.text(message);
                    formInput.hide();
                    btn.hide();
                    homePage.show();
                } else {
                    messageNewPass.text(message);
                }
            }
        });
    }
    e.preventDefault();
});
