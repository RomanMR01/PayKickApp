var namePattern = /^[0-9A-z\u0400-\u04FF]+$/;
var loginPattern = /^[A-z0-9]+$/;
var emailPattern = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;


function validateAge(age) {
    if (age == null || age < 18 || age > 100) {
        Materialize.toast("Wrong age!",5000);
        return false;
    }
    return true;
}

function validateName(name) {
    if (name == null || name.length < 2) {
        Materialize.toast("Wrong name!",5000);
        return false;
    }
    if (!namePattern.test(name)) {
        Materialize.toast("Wrong name!",5000);
        return false;
    }

    return true;


}
function validateSurname(surname) {
    if (surname == null || surname.length < 2) {
        Materialize.toast("Wrong surname!",5000);
        return false;
    }

    if (!namePattern.test(surname)) {
        Materialize.toast("Wrong surname!",5000);
        return false;
    }
    return true;

}
function validateLogin(login, message) {
    if (login == null || login.length < 4) {
        Materialize.toast("Wrong login!",5000);
        return false;
    }
    if (!loginPattern.test(login)) {
        Materialize.toast("Wrong login!",5000);
        return false;
    }

    return true;

}
function validatePassword(pass) {
    if (pass == null || pass.length < 4) {
        Materialize.toast("Wrong password!",5000);
        return false;
    }

    if (!loginPattern.test(pass)) {
        Materialize.toast("Wrong password!",5000);
        return false;
    }

    return true;

}
function validateEmail(email) {
    if (email == null || email.length < 4) {
        Materialize.toast("Wrong email!",5000);
        return false;
    }

    if (!emailPattern.test(email)) {
        Materialize.toast("Wrong email!",5000);
        return false;
    }

    return true;
}
