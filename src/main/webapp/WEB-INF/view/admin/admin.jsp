<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
        <title>Admin - Home</title>

        <jsp:include page="common/styles.jsp"></jsp:include>
    </head>

    <body>
        <jsp:include page="common/navigation.jsp"></jsp:include>
        
        <!-- Main Content -->
		<br>
        <br>
        
        <jsp:include page="common/footer.jsp"></jsp:include>
        <jsp:include page="common/scripts.jsp"></jsp:include>

    </body>

    </html>