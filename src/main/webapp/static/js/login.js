'use strict';
$('#loginBtn').on('click', function (e) {
    var login = $('#login').val();
    var password = $('#password').val();
    var messageLogin = $("#messageLogin");
    var rememberMe = document.getElementById("remember-me").checked;

    if (validateLogin(login,messageLogin) && validatePassword(password,messageLogin)) {
        $.ajax({
            type: "POST",
            url: "login",
            data: {"login": login, "password": password, "rememberMe": rememberMe},
            success: function (data) {
                var response = JSON.parse(data);

                var status = response.status;
                var url = response.url;
                var message = response.message;

                if (status == 'OK') {
                    messageLogin.text(message);
                    window.location=url;
//                    $(location).attr('href', '/PayKick/' + url);
                    console.log("success")
                } else {
                    console.log("fail")
                    messageLogin.text(message);
                }
            }
        });
    }
    e.preventDefault();
});
$('#register').on('click', function (e) {

    var nameToRegister = $('#fname').val();
    var surnameToRegister = $('#surname').val();
    var loginToRegister = $('#login-reg').val();
    var gender = $('input[name="gender"]:checked').val();
    var age = $('#age').val();
    var passwordToRegister = $('#password-reg').val();
    var emailToRegister = $('#email').val();
    var messageReg = $("#messageRegistration");


    if (validateName(nameToRegister, messageReg) && validateSurname(surnameToRegister, messageReg) && validateAge(age, messageReg)
        && validateEmail(emailToRegister,messageReg) && validateLogin(loginToRegister,messageReg) && validatePassword(passwordToRegister,messageReg)) {
        $.ajax({
            type: "POST",
            url: "register",
            data: {
                "name": nameToRegister,
                "password": passwordToRegister,
                "email": emailToRegister,
                "surname": surnameToRegister,
                "login": loginToRegister,
                "gender": gender,
                "age": age
            },
            success: function (data) {
                if (data == 'True') {
                    messageReg.text("You are registered!");
                    window.location="home";
//                    $(location).attr('href', '/PayKick/home');
                } else {
                    messageReg.text(data);
                }
            }
        });
    }
    e.preventDefault();
});

