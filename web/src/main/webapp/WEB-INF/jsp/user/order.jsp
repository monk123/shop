<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="img" value="/resources/font/"/>
<html>
<head>
    <title>Order</title>
    <jsp:include page="../fragments/layouts.jsp"/>
</head>
<body>
<jsp:include page="../fragments/header.jsp"/>

<div class="container">

    <c:if test="${! empty order}">
        <table class="table jsgrid-header-sortable">
            <thead>
            <tr>
                <th><spring:message code="label.order.id"/></th>
                <th><spring:message code="label.order.date"/></th>
                <th><spring:message code="label.order.amount"/></th>
                <th><spring:message code="label.order.user.username"/></th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${order.id}</td>
                    <td>${order.orderDate}</td>
                    <td>${order.amount}</td>
                    <td>${order.user.username}</td>
                </tr>
                <c:forEach var="p" items="${order.products}">
                    <tr>
                        <td>${p.name}</td>
                        <td><img src="${img}${p.photo}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <div class="row">
        <spring:url value="/welcome/${1}" var="welcomeUrl"/>
        <button class="btn btn-primary" onclick="location.href='${welcomeUrl}'">
            <spring:message code="label.user.main"/>
        </button>
    </div>

</div>

</body>
</html>
