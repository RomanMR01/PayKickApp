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
            <title>PayKick - Withdraw</title>

            <jsp:include page="common/styles.jsp"></jsp:include>
            <jsp:include page="common/scripts.jsp"></jsp:include>
        </head>

        <body>
            <jsp:include page="common/navigation.jsp"></jsp:include>

            <!-- Main Content -->
            <main class="valign-wrapper grey lighten-3">
                <div class="container valign">
                    <h4 class="center-align">Withdraw:</h4>
                    <br>
                    <div class="row">
                        <div class="input-field col s6 offset-s3 center-align">
                            <input id="amount" type="number" min="0">
                            <br><br>
                            <label for="amount">Amount</label>
                            <a class="waves-effect waves-light btn green"><i class="material-icons right">attach_money</i>Withdraw</a>
                        </div>
                        
                    </div>
                </div>
            </main>

            <jsp:include page="common/footer.jsp"></jsp:include>

        </body>

        </html>