<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
         <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'ua_UA'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.lang"/>
<!DOCTYPE html>

<html>

<head>

    <!-- Meta -->
    <meta charset="utf-8">

    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="title.adminedit" /></title>

    <jsp:include page="common/styles.jsp"></jsp:include>
    <jsp:include page="common/scripts.jsp"></jsp:include>
</head>

<body>
<jsp:include page="common/navigation.jsp"></jsp:include>

<!-- Main Content -->
<main class="valign-wrapper grey lighten-3">
    <div class="container valign" style="margin-top: 20px;">
        <h4 class="center-align"><fmt:message key="edit.editaccount" />:</h4>
        <br>
        <div class="row">
            <div id="userAccount" class="col s12 m8 offset-m2 l6 offset-l3">
                <div class="row">
                    <div class="input-field col s12">
                        <!-- Replace value -->
                        <input id="fname" type="text" value="${user.getFullName().split(" ")[0]}">
                        <label for="fname"><fmt:message key="caption.fname" /></label>
                    </div>
                    <div class="input-field col s12">
                        <!-- Replace value -->
                        <input id="surname" type="text" value="${user.getFullName().split(" ")[1]}">
                        <label for="surname"><fmt:message key="caption.surname" /></label>
                    </div>
                    <div class="col s12">
                        <p class="big"><fmt:message key="caption.age" />:</p>
                        <p class="range-field">
                            <input type="range" id="age" min="0" max="100" value="${user.getAge()}"/>
                        </p>
                    </div>
                    <div class="col s12" id="genderForm">
                        <p class="big"><fmt:message key="caption.sex" />:</p>
                        <p>
                            <input name="gender" type="radio" id="male" value="male"
                            <c:choose>
                                    <c:when test="${user.getGender()=='MALE'}">required checked</c:when>
                            </c:choose>
                            />
                            <label for="male"><fmt:message key="caption.male" /></label>
                        </p>
                        <p>
                            <input name="gender" type="radio" id="female" value="female" <c:choose>
                                <c:when test="${user.getGender()=='FEMALE'}">required checked</c:when>
                            </c:choose>/>
                            <label for="female"><fmt:message key="caption.female" /></label>
                        </p>
                    </div>
                </div>
                <div class="col s12 center-align">
                    <button class="btn waves-effect waves-light green" id="saveChanges"><fmt:message key="common.save" />
                        <i class="material-icons right">done</i>
                    </button>
                    <br>
                </div>
            </div>
        </div>
        <h4 class="center-align"><fmt:message key="edit.changepassword" />:</h4>
        <br>
        <div class="row">
            <div id="userPassword" class="col s12 m8 offset-m2 l6 offset-l3">
                <div class="row">
                    <div class="input-field col s12">
                        <!-- Replace value -->
                        <input id="currentPassword" type="password" >
                        <label for="currentPassword"><fmt:message key="edit.currentpassword" /></label>
                    </div>
                    <div class="input-field col s12">
                        <!-- Replace value -->
                        <input id="newPassword" type="password">
                        <label for="newPassword"><fmt:message key="new_password.new" /></label>
                    </div>
                    <div class="input-field col s12">
                        <!-- Replace value -->
                        <input id="repeatPassword" type="password" >
                        <label for="repeatPassword"><fmt:message key="edit.repeat" /></label>
                    </div>
                </div>
                <div class="col s12 center-align">
                    <button class="btn waves-effect waves-light green" id="saveNewPassword"><fmt:message key="common.change" />
                        <i class="material-icons right">done</i>
                    </button>
                    <br>
                </div>
            </div>
        </div>
    </div>
</main>

<jsp:include page="common/footer.jsp"></jsp:include>

<script src="${pageContext.request.contextPath}/static/js/validatorToasts.js"></script>
<script>
$("#saveChanges").click(function () {
    var userID = "${user.id}";
    var name = $("#fname").val();
    var surname = $("#surname").val();
    var age = $("#age").val();
    var gender = $('input[name=gender]:checked', '#genderForm').val()

    if(!validateName(name)){
        return;
    }
    if(!validateSurname(surname)){
        return;
    }
    if(!validateAge(age)){
        return;
    }

    $.ajax({
        type: "POST",
        url: "edit",
        data: {
            "userID":userID,
            "name":name,
            "surname":surname,
            "age":age,
            "gender":gender
        },
        success: function (data) {
            var response = JSON.parse(data);

            var status = response.status;
            var message = response.message;

            Materialize.toast(message,5000);

        }
    });

});

$("#saveNewPassword").click(function () {
    var userID = "${user.id}";
    var currentPassword = $("#currentPassword").val();
    var newPassword = $("#newPassword").val();
    var repeatPassword = $("#repeatPassword").val();


    if(validatePassword(currentPassword) && validatePassword(newPassword) && validatePassword(repeatPassword)){
        if(newPassword==repeatPassword){
            $.ajax({
                type: "POST",
                url: "changePassword",
                data: {
                    "userID":userID,
                    "currentPassword":currentPassword,
                    "newPassword":newPassword,
                },
                success: function (data) {
                    var response = JSON.parse(data);

                    var status = response.status;
                    var message = response.message;

                    Materialize.toast(message,5000);

                }
            });

        }else{
            Materialize.toast("Passwords do not match!",5000);
        }
    }


});
</script>

</body>

</html>