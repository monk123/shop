<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>AdminAddPage</title>

    <jsp:include page="../fragments/layouts.jsp"/>

</head>
<body>
<div class="container">

<h1><spring:message code="label_product_add"/></h1>

<form:form modelAttribute="productForm" method="post" class="form-signin">

    <spring:bind path="name">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="name" class="form-control" placeholder="Name"/>
            <form:errors path="name"/>
        </div>
    </spring:bind>

    <spring:bind path="category">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="category.categoryName" class="form-control" placeholder="Category"/>
            <form:errors path="category.categoryName"/>
        </div>
    </spring:bind>

    <spring:bind path="description">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:textarea type="textarea" path="description" class="form-control" placeholder="Description"/>
            <form:errors path="description"/>
        </div>
    </spring:bind>

    <spring:bind path="price">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="price" class="form-control" placeholder="Price"/>
            <form:errors path="price"/>
        </div>
    </spring:bind>

    <spring:bind path="photo">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="file" path="photo" class="form-control" placeholder="Photo"/>
            <form:errors path="photo"/>
        </div>
    </spring:bind>
        <!-- ADD PRODUCT -->
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <c:if test="${!empty productForm.name}">
                <input class="btn btn-lg btn-primary btn-block" role="button" type="submit"/>
            </c:if>
            <c:if test="${empty productForm.name}">
                <input class="btn btn-lg btn-primary btn-block" role="button" type="submit"/>
            </c:if>
        </div>
    </div>
</form:form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="../../../resources/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>
