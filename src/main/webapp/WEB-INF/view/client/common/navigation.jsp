<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'ua_UA'}" scope="session" />
            <fmt:setLocale value="${language}" />
            <fmt:setBundle basename="i18n.lang" />
            <!-- Navigation -->
            <header>
                <!-- Top Dropdowns -->
                <ul id="languageDropdown" class="dropdown-content grey darken-3">
                    <li><a href="?language=ua_UA" class="white-text">УКРАЇНСЬКА</a></li>
                    <li><a href="?language=en_EN" class="white-text">ENGLISH</a></li>
                </ul>

                <ul id="balanceDropdown" class="dropdown-content grey darken-3">
                    <li><a href="${pageContext.request.contextPath}/client/credit" class="white-text"><fmt:message key="balance.credit" /></a></li>
                    <li><a href="${pageContext.request.contextPath}/client/withdraw" class="white-text"><fmt:message key="balance.withdraw" /></a></li>
                </ul>

                <ul id="userDropdown" class="dropdown-content grey darken-3">
                    <li><a href="${pageContext.request.contextPath}/${role}" class="white-text"><fmt:message key="menu.mycabinet" /><i class="material-icons right orange-text">perm_identity</i></a></li>
                    <li><a href="${pageContext.request.contextPath}/${role}/edit" class="white-text"><fmt:message key="menu.editaccount" /><i class="material-icons right green-text">mode_edit</i></a></li>
                    <li><a href="${pageContext.request.contextPath}/logout" class="white-text"><fmt:message key="menu.logout" /><i class="material-icons right red-text">power_settings_new</i></a></li>
                </ul>

                <!-- Sidebar Dropdowns -->
                <ul id="languageSideDropdown" class="dropdown-content sidebar grey darken-3">
                    <li><a href="?language=ua_UA" class="white-text">УКРАЇНСЬКА<i class="material-icons right green-text">language</i></a></li>
                    <li><a href="?language=en_EN" class="white-text">ENGLISH<i class="material-icons right green-text">language</i></a></li>
                </ul>

                <ul id="userSideDropdown" class="dropdown-content sidebar grey darken-3">
                    <li><a href="${pageContext.request.contextPath}/${role}" class="white-text"><fmt:message key="menu.mycabinet" /><i class="material-icons right orange-text">perm_identity</i></a></li>
                    <li><a href="${pageContext.request.contextPath}/${role}/edit" class="white-text"><fmt:message key="menu.editaccount" /><i class="material-icons right green-text">mode_edit</i></a></li>
                    <li><a href="${pageContext.request.contextPath}/logout" class="white-text"><fmt:message key="menu.logout" /><i class="material-icons right red-text">power_settings_new</i></a></li>
                </ul>

                <ul id="balanceSideDropdown" class="dropdown-content sidebar grey darken-3">
                    <li><a href="${pageContext.request.contextPath}/client/credit" class="white-text"><fmt:message key="balance.credit" /></a></li>
                    <li><a href="${pageContext.request.contextPath}/client/withdraw" class="white-text"><fmt:message key="balance.withdraw" /></a></li>
                </ul>

                <nav>
                    <div class="nav-wrapper grey darken-3">
                        <a href="#" data-activates="sidebar-nav" class="button-collapse"><i class="material-icons">menu</i></a>
                        <ul class="right hide-on-med-and-down">

                            <li><a class="dropdown-button" data-activates="userDropdown"><fmt:message key="common.hello" />, ${fullName}<i class="material-icons right orange-text">perm_identity</i></a></li>
                            <li><a class="dropdown-button" data-activates="balanceDropdown"><fmt:message key="caption.balance" />: <fmt:formatNumber type="number"
                                                                                                                       maxFractionDigits="2" value="${balance}" /><i class="material-icons right green-text">attach_money</i></a></li>
                            <li><a href="${pageContext.request.contextPath}/home" class="white-text"><fmt:message key="common.home" /><i class="material-icons right red-text text-accent-3">home</i></a></li>
                            <li><a class="dropdown-button" data-activates="languageDropdown"><fmt:message key="common.language" /><i class="material-icons right green-text">language</i></a></li>
                        </ul>
                        <ul class="side-nav fixed grey darken-4" id="sidebar-nav">
                            <li>
                                <a href="${pageContext.request.contextPath}/home" class="center-align logo"><img src="${pageContext.request.contextPath}/static/img/logo.png" alt="PayKick Logo" width="50" height="44"></a>
                            </li>

                            <li><a href="${pageContext.request.contextPath}/client/bets"><fmt:message key="menu.mybets" /><i class="material-icons right green-text text-accent-4">library_books</i></a></li>
                            <li><a href="${pageContext.request.contextPath}/client/matches"><fmt:message key="menu.activematches" /><i class="material-icons right green-text text-accent-4">list</i></a></li>
                            <li><a href="${pageContext.request.contextPath}/client/statistics"><fmt:message key="menu.statistics" /><i class="material-icons right light-blue-text text-accent-2">equalizer</i></a></li>

                            <div class="hide-on-large-only">
                                <hr>
                                <li><a class="dropdown-button" data-activates="userSideDropdown">${fullName}<i class="material-icons right orange-text">perm_identity</i></a></li>
                                <li><a href="${pageContext.request.contextPath}/home" class="white-text"><fmt:message key="common.home" /><i class="material-icons right red-text text-accent-3">home</i></a></li>
                                <li><a class="dropdown-button" data-activates="balanceSideDropdown"><fmt:message key="common.balance" />: <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${balance}" /><i class="material-icons right green-text">attach_money</i></a></li>
                                <li><a class="dropdown-button" data-activates="languageSideDropdown"><fmt:message key="common.language" /><i class="material-icons right green-text">language</i></a></li>
                                  </div>
                        </ul>
                    </div>
                </nav>
            </header>