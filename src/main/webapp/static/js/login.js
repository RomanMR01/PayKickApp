'use strict';
$('#loginBtn').on('click', function (e) {
    var login = $('#login').val();
    var password = $('#password').val();
    var messageLogin = $("#messageLogin");
    console.log("+log");
    if (login && password) {
        $.ajax({
            type: "POST",
            url: "login",
            data: {"login": login, "password": password},
            success: function (data) {
                if (data == 'success') {
                    messageLogin.text(" ");
                    $(location).attr('href', '/home');
                    console.log("suc")
                } else {
                    console.log("fail")
                    messageLogin.text(data);
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
    var male = $('input[name="sex"]:checked').val();
    var age = $('#age').val();
    var passwordToRegister = $('#password-reg').val();
    var emailToRegister = $('#email').val();
    var message = $("#message");
    if (nameToRegister && passwordToRegister && emailToRegister) {
        $.ajax({
            type: "POST",
            url: "register",
            data: {"name": nameToRegister, "password": passwordToRegister, "email": emailToRegister, "surname": surnameToRegister, "login": loginToRegister, "sex":male, "age":age },
            success: function (data) {
                if (data == 'True') {
                    message.text(" ");
                    $(location).attr('href', '/home');
                } else {
                    message.text(data);
                }
            }
        });
    }
    e.preventDefault();
});