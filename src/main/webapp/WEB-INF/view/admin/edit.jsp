<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>

<head>

    <!-- Meta -->
    <meta charset="utf-8">

    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Edit Account</title>

    <jsp:include page="common/styles.jsp"></jsp:include>
    <jsp:include page="common/scripts.jsp"></jsp:include>
</head>

<body>
<jsp:include page="common/navigation.jsp"></jsp:include>

<!-- Main Content -->
<main class="valign-wrapper grey lighten-3">
    <div class="container valign" style="margin-top: 20px;">
        <h4 class="center-align">Edit Account:</h4>
        <br>
        <div class="row">
            <div id="userAccount" class="col s12 m8 offset-m2 l6 offset-l3">
                <div class="row">
                    <div class="input-field col s12">
                        <!-- Replace value -->
                        <input id="fname" type="text" value="${user.getFullName().split(" ")[0]}">
                        <label for="fname">First Name</label>
                    </div>
                    <div class="input-field col s12">
                        <!-- Replace value -->
                        <input id="surname" type="text" value="${user.getFullName().split(" ")[1]}">
                        <label for="surname">Surname</label>
                    </div>
                    <div class="col s12">
                        <p class="big">Age:</p>
                        <p class="range-field">
                            <input type="range" id="age" min="0" max="100" value="${user.getAge()}"/>
                        </p>
                    </div>
                    <div class="col s12" id="genderForm">
                        <p class="big">Sex:</p>
                        <p>
                            <input name="gender" type="radio" id="male" value="male"
                            <c:choose>
                                    <c:when test="${user.getGender()=='MALE'}">required checked</c:when>
                            </c:choose>
                            />
                            <label for="male">Male</label>
                        </p>
                        <p>
                            <input name="gender" type="radio" id="female" value="female" <c:choose>
                                <c:when test="${user.getGender()=='FEMALE'}">required checked</c:when>
                            </c:choose>/>
                            <label for="female">Female</label>
                        </p>
                    </div>
                </div>
                <div class="col s12 center-align">
                    <button class="btn waves-effect waves-light green" id="saveChanges">Save
                        <i class="material-icons right">done</i>
                    </button>
                    <br>
                    <br>
                    <span class="center-align green-text" id="message">Saved</span>
                </div>
            </div>
        </div>
        <h4 class="center-align">Change password:</h4>
        <br>
        <div class="row">
            <div id="userPassword" class="col s12 m8 offset-m2 l6 offset-l3">
                <div class="row">
                    <div class="input-field col s12">
                        <!-- Replace value -->
                        <input id="currentPassword" type="password" >
                        <label for="currentPassword">Current Password</label>
                    </div>
                    <div class="input-field col s12">
                        <!-- Replace value -->
                        <input id="newPassword" type="password">
                        <label for="newPassword">New Password</label>
                    </div>
                    <div class="input-field col s12">
                        <!-- Replace value -->
                        <input id="repeatPassword" type="password" >
                        <label for="repeatPassword">Repeat Password</label>
                    </div>
                </div>
                <div class="col s12 center-align">
                    <button class="btn waves-effect waves-light green" id="saveNewPassword">Change
                        <i class="material-icons right">done</i>
                    </button>
                    <br>
                    <br>
                    <span class="center-align green-text" id="messageForPassword">Saved</span>
                </div>
            </div>
        </div>
    </div>
</main>

<jsp:include page="common/footer.jsp"></jsp:include>

<script src="${pageContext.request.contextPath}/static/js/validation.js"></script>
<script>
$("#saveChanges").click(function () {
    var userID = "${user.id}";
    var name = $("#fname").val();
    var surname = $("#surname").val();
    var age = $("#age").val();
    var gender = $('input[name=gender]:checked', '#genderForm').val()

    var infoMessage = $("#message");


    if(!validateName(name,infoMessage)){
        return;
    }
    if(!validateSurname(surname,infoMessage)){
        return;
    }
    if(!validateAge(age,infoMessage)){
        return;
    }

    //Ajax check full name
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

            if (status == 'FAIL') {
              alert('ff');
            }else{
                alert('okkk');
            }
        }
    });

    alert(userID);
    alert(name);
    alert(surname);
    alert(age);
    alert(gender);


});

$("#saveNewPassword").click(function () {
    var userID = "${user.id}";
    var currentPassword = $("#currentPassword").val();
    var newPassword = $("#newPassword").val();
    var repeatPassword = $("#repeatPassword").val();
    var infoMessage = $("#messageForPassword");


    if(validatePassword(currentPassword,infoMessage) && validateName(newPassword,infoMessage) && validatePassword(repeatPassword,infoMessage)){
        alert('ok');

        if(newPassword==repeatPassword){
            alert("pass confirm");

        }else{
            alert("passwords do not confirm");
        }
    }else{
        alert('fail');
    }
    alert(userID);
    alert(currentPassword);
    alert(newPassword);
    alert(repeatPassword);


});
</script>

</body>

</html>