<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
    <jsp:include page="../fragments/layouts.jsp"/>
</head>
<body>

<jsp:include page="../fragments/header.jsp"/>

<div class="container">
    <c:if test="${! empty orders}">
        <div class="table-responsive">
            <table class="table">
                <thead>
                <tr>
                    <th><spring:message code="label.order.id"/></th>
                    <th><spring:message code="label.order.date"/></th>
                    <th><spring:message code="label.order.amount"/></th>
                    <th><spring:message code="label.order.user.username"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${orders}" var="order">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.orderDate}</td>
                        <td>${order.amount}</td>
                        <td>${order.user.username}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>
</div>

</body>
</html>
