<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'ua_UA'}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.lang" />
        <!DOCTYPE html>

        <html>

        <head>

            <!-- Meta -->
            <meta charset="utf-8">

            <!-- Mobile Metas -->
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>PayKick - <fmt:message key="title.withdraw" /></title>

            <jsp:include page="common/styles.jsp"></jsp:include>
            <jsp:include page="common/scripts.jsp"></jsp:include>
        </head>

        <body>
            <jsp:include page="common/navigation.jsp"></jsp:include>

            <!-- Main Content -->
            <main class="valign-wrapper grey lighten-3">
                <div class="container valign">
                    <h4 class="center-align"><fmt:message key="title.withdraw" />:</h4>
                    <br>
                    <div class="row">
                        <div class="input-field col s6 offset-s3 center-align">
                            <input id="amount" type="number" min="1" max="1000" length="7">
                            <br><br>
                            <label for="amount"><fmt:message key="caption.amount" /></label>
                            <a class="waves-effect waves-light btn green" id="submitWithdraw"><i class="material-icons right">attach_money</i><fmt:message key="title.withdraw" /></a>
                        </div>
                        
                    </div>
                </div>
            </main>

            <jsp:include page="common/footer.jsp"></jsp:include>
<script>
    $("#submitWithdraw").click(function () {
        var amountSum = new Number($("#amount").val());
        if (amountSum < 1 || amountSum > 1000000) {
            Materialize.toast("Wrong input, must be from 1 to 1000000!",5000);
            return;
        }

        $.ajax({
            type: "POST",
            url: "withdraw",
            data: {
                "amountSum":amountSum
            },
            success: function (data) {
                var response = JSON.parse(data);
                var status = response.status;
                var message = response.message;

                if (status == 'OK') {
                    window.location = 'withdraw';
                } else {
                    Materialize.toast(message,5000)
                }
            }
        });

    });
</script>
        </body>

        </html>