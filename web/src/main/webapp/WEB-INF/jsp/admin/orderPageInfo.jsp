<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>OrderInfo</title>
    <jsp:include page="../fragments/layouts.jsp"/>
</head>
<body>
<jsp:include page="../fragments/header.jsp"/>
<div class="container">
    <table class="table">
        <thead class="table-view">
        <tr>
            <th><spring:message code="label.product.name"/></th>
            <th><spring:message code="label.product.quantity"/></th>
            <th><spring:message code="label.product.price"/></th>
            <th><spring:message code="label.product.photo"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${productList}">
        <tr>
            <td></td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
