<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin user list</title>

    <jsp:include page="../fragments/layouts.jsp"/>

</head>

<body>

<jsp:include page="../fragments/header.jsp"/>

<div class="container">
    <c:if test="${! empty users}">
        <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th><spring:message code="label.user.id"/></th>
                <th><spring:message code="label.user.username"/></th>
                <th><spring:message code="label.user.lastName"/></th>
                <th><spring:message code="label.user.email"/></th>
                <th><spring:message code="label.user.phone"/></th>
                <th><spring:message code="label.user.country"/></th>
                <th><spring:message code="label.user.region"/></th>
                <th><spring:message code="label.user.city"/></th>
                <th><spring:message code="label.user.street"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>${user.phone}</td>
                    <td>${user.address.country}</td>
                    <td>${user.address.region}</td>
                    <td>${user.address.city}</td>
                    <td>${user.address.street}</td>

                    <spring:url value="/admin/user/blocked/${user.id}" var="blockedUrl" />
                    <spring:url value="/admin/user/unblocked/${user.id}" var="unblockedUrl" />

                    <td>
                        <button class="btn btn-primary" onclick="location.href='${unblockedUrl}'">
                            <spring:message code="label.button.user.unblocked"/>
                        </button>
                    </td>
                    <td>
                        <button class="btn btn-danger" onclick="location.href='${blockedUrl}'">
                            <spring:message code="label.button.user.blocked"/>
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </div>
    </c:if>

    <c:forEach var="page"  begin="1" end="${size}">
        <ul class="pagination">
            <li><a href="/admin/user/list/${page}">${page}</a></li>
        </ul>
    </c:forEach>
</div>
</body>
</html>
