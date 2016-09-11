var namePattern = /^[0-9A-z\u0400-\u04FF]+$/;
var loginPattern = /^[A-z0-9]+$/;
var emailPattern = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;


function validateAge(age, message) {
    if (age == null || age < 18 || age > 100) {
        message.text("Wrong age");
        return false;
    }
    return true;
}

function validateName(name, message) {
    if (name == null || name.length < 2) {
        message.text("Wrong name");
        return false;
    }
    if (!namePattern.test(name)) {
        message.text("Wrong name");
        return false;
    }

    return true;


}
function validateSurname(surname, message) {
    if (surname == null || surname.length < 2) {
        message.text("Wrong surname");
        return false;
    }

    if (!namePattern.test(surname)) {
        message.text("Wrong surname");
        return false;
    }

    return true;

}
function validateLogin(login, message) {
    if (login == null || login.length < 4) {
        message.text("Wrong login");
        return false;
    }
    if (!loginPattern.test(login)) {
        message.text("Wrong login");
        return false;
    }

    return true;

}
function validatePassword(pass, message) {
    if (pass == null || pass.length < 4) {
        message.text("Wrong password");
        return false;
    }

    if (!loginPattern.test(pass)) {
        message.text("Wrong password");
        return false;
    }

    return true;

}
function validateEmail(email, message) {
    if (email == null || email.length < 4) {
        message.text("Wrong email");
        return false;
    }

    if (!emailPattern.test(email)) {
        message.text("Wrong email");
        return false;
    }

    return true;
}
