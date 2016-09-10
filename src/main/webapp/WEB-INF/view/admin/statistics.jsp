<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>

<head>

    <!-- Meta -->
    <meta charset="utf-8">

    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PayKick. One bet - one hit!</title>

    <jsp:include page="common/styles.jsp"></jsp:include>
    <script src="${pageContext.request.contextPath}/static/js/Chart.min.js"></script>

</head>

<body>
<jsp:include page="common/navigation.jsp"></jsp:include>

<!-- Main Content -->
<main>
    <div>
        <canvas id="mainChart"></canvas>
    </div>
</main>
>

<jsp:include page="common/scripts.jsp"></jsp:include>
<jsp:include page="common/footer.jsp"></jsp:include>


</body>

</html>