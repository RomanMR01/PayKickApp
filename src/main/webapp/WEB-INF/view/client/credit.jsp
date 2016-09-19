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
            <title>PayKick - Credit</title>

            <jsp:include page="common/styles.jsp"></jsp:include>
            <jsp:include page="common/scripts.jsp"></jsp:include>
        </head>

        <body>
            <jsp:include page="common/navigation.jsp"></jsp:include>

            <!-- Main Content -->
            <main class="valign-wrapper grey lighten-3">
                <div class="container valign">
                    <h4 class="center-align">Credit:</h4>
                    <br>
                    <div class="row">
                        <div class="input-field col s6 offset-s3 center-align">
                            <input id="amount" type="number" min="1" max="10000" length="5">
                            <br><br>
                            <label for="amount">Amount</label>
                            <a class="waves-effect waves-light btn green" id="submitCredit"><i class="material-icons right">attach_money</i>Credit</a>
                        </div>
                        
                    </div>
                </div>
            </main>

            <jsp:include page="common/footer.jsp"></jsp:include>
        <script>
            $("#submitCredit").click(function () {
                var amountSum = new Number($("#amount").val());
                if (amountSum < 1 || amountSum > 10000) {
                    Materialize.toast("Wrong input, must be from 1 to 10000!",5000);
                    return;
                }

                $.ajax({
                    type: "POST",
                    url: "credit",
                    data: {
                       "amountSum":amountSum
                    },
                    success: function (data) {
                        var response = JSON.parse(data);
                        var status = response.status;
                        var message = response.message;

                        if (status == 'OK') {
                            window.location = 'credit';
                        } else {
                            Materialize.toast(message,5000)
                        }
                    }
                });

            });
        </script>
        </body>

        </html>