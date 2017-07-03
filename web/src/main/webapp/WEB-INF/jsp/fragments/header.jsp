<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<nav class="navbar navbar-inverse">
    <div class="navbar-header" >
        <button type="button" class="navbar-toggle" data-toggle="collapse"
                data-target="#bs-example-navbar-collapse-1">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="<c:url value="/welcome/${1}"/>"><spring:message code="label.shop.name"/></a>
    </div>
    <div class="navbar-collapse collapse" id="bs-example-navbar-collapse-1">

        <ul class="nav navbar-nav navbar-right">

            <c:set var="locale" value="${request.LOCALE.language}"/>
            <c:if test="${locale eq 'en'}">
                <li><a href="?locale=en"><span class="lang-sm lang-lbl" lang="en"></span></a> </li>
            </c:if>
            <c:if test="${locale eq 'ru'}">
                <li><a href="?locale=ru"><span class="lang-sm lang-lbl" lang="ru"></span></a></li>
            </c:if>
            <c:if test="${empty locale}">
                <li><a href="?locale=ru"><span class="lang-sm lang-lbl" lang="ru"></span></a> </li>
                <li class="active"><a href="?locale=en"><span class="lang-sm lang-lbl" lang="en"></span></a></li>
            </c:if>

            <sec:authorize access="isAuthenticated() and hasRole('ROLE_USER')">
            <li>
                <spring:url value="/product/bucket" var="bucketURL"/>
                <li>
                    <button class="btn btn-success btn-lg" onclick="location.href='${bucketURL}'">
                        <spring:message code="label.user.bucket"/>
                    </button>
                </li>
            </li>
            </sec:authorize>

            <sec:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
                <spring:url value="/admin/user/list/${1}" var="listURL"/>
            <li>
                <button class="btn btn-lg btn-info" onclick="location.href='${listURL}'">
                    <spring:message code="label.user.list"/>
                </button>
            </li>
            </sec:authorize>

            <sec:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
                <spring:url value="/admin/product/list/${1}" var="listURL"/>
                <li>
                    <button class="btn btn-lg btn-info" onclick="location.href='${listURL}'">
                        <spring:message code="label.product.list"/>
                    </button>
                </li>
            </sec:authorize>

            <sec:authorize access="isAuthenticated() and hasRole('ROLE_USER')">
                <spring:url value="/welcome/user/${pageContext.request.userPrincipal.name}" var="infoURL"/>
                <li>
                    <button class="btn btn-primary btn-lg" onclick="location.href='${infoURL}'">
                        <spring:message code="label.user.user.info"/>
                    </button>
                </li>
            </sec:authorize>

            <sec:authorize access="isAuthenticated()">
                <li>
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <form id="logoutForm" method="post" action="${contextPath}/logout">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                    <li>
                        <button class="btn btn-lg btn-danger" onclick="document.forms['logoutForm'].submit()">
                            <spring:message code="label.locale.logout"/>
                        </button>
                    </li>
                </c:if>
                </li>
            </sec:authorize>

        </ul>
    </div>
</nav>
