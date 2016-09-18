<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'ua_UA'}" scope="session" />
            <fmt:setLocale value="${language}" />
            <fmt:setBundle basename="i18n.lang" />
            <!-- Footer -->
            <footer class="page-footer">
                <div class="footer-copyright grey darken-3">
                    <div class="center-align">
                        <a href=""><span class="orange-text"><strong>PayKick</strong></span></a> © 2016
                        <fmt:message key="footer.rightsreserved" />
                        <div class="right lang">
                            <a href="?language=ua_UA">
                                <div id="ua"></div>
                            </a>
                            <a href="?language=en_EN">
                                <div id="gb"></div>
                            </a>
                        </div>
                    </div>
                </div>
            </footer>