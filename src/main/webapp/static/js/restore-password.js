$('#restore-email-btn').on('click', function (e) {
    var emailAddress = $('#restore-email').val();
    var messageRestore = $("#messageRestore");
    
    if (validateEmail(emailAddress,messageRestore)) {
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
                    messageRestore.text(message);
                } else {
                    messageRestore.text(message);
                }
            }
        });
    }
    e.preventDefault();
});
