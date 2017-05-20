<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <a class="navbar-brand" href="<c:url value="/welcome"/>"><spring:message code="label.shop.name"/></a>
    </div>
    <div class="navbar-collapse collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li>
                <a href="#"><spring:message code="label_shop_about"/></a>
            </li>
            <li>
                <c:url var="product" value="/welcome"/>
                <a href="${product}"><spring:message code="label_shop_product"/></a>
            </li>
        </ul>

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
                <button class="btn btn-lg btn-primary" onclick="location.href='${bucketURL}'">
                    <spring:message code="label.user.bucket"/>
                </button>
            </li>
            </sec:authorize>

            <sec:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
                <spring:url value="/admin/user/list" var="listURL"/>
            <li>
                <button class="btn btn-lg btn-info" onclick="location.href='${listURL}'">
                    <spring:message code="label.user.list"/>
                </button>
            </li>
            </sec:authorize>

            <sec:authorize access="isAuthenticated() and hasRole('ROLE_USER')">
                <li>
                    <spring:url value="/user/info/${pageContext.request.userPrincipal.name}" var="infoURL"/>
                    <button class="btn btn-lg btn-info" onclick="location.href='${infoURL}'">
                        <spring:message code="label.user.info"/>
                    </button>
                </li>
            </sec:authorize>

            <sec:authorize access="!isAuthenticated()">
                <li>
                    <spring:url value="/login" var="login"/>
                    <button  class="btn btn-lg btn-success" onclick="location.href='${login}'" role="button">
                        <spring:message code="label_locale_login"/>
                    </button>
                </li>
            </sec:authorize>

            <sec:authorize access="isAuthenticated()">
                <li>
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <form id="logoutForm" method="post" action="${contextPath}/logout">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                    <a class="btn btn-lg btn-danger" onclick="document.forms['logoutForm'].submit()" role="button">
                        <spring:message code="label_locale_logout"/>
                    </a>
                </c:if>
                </li>
            </sec:authorize>

        </ul>
    </div>
</nav>
