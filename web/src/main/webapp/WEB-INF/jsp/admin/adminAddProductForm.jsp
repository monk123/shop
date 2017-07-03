<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>AdminAddPage</title>

    <jsp:include page="../fragments/layouts.jsp"/>

</head>
<body>

<div class="container">

    <form:form modelAttribute="productForm" method="post" class="form-horizontal">

        <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="label.product.name"/></label>
            <div class="col-md-8">
                <form:input type="text" path="name" class="form-control" placeholder="Name"/>
                <form:errors path="name"/>
            </div>
        </div>

        <form:hidden path="category.id"/>

        <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="label.product.category"/></label>
            <div class="col-md-8">
                <form:select class="form-control" path="category.categoryName">
                    <form:options class="form-control" items="${categories}" placeholder="CategoryName"/>
                </form:select>
                <form:errors path="category.categoryName"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="label.product.description"/></label>
            <div class="col-md-8">
                <form:textarea type="textarea" path="description" class="form-control" placeholder="Description"/>
                <form:errors path="description"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="label.product.quantity"/></label>
            <div class="col-md-8">
                <form:input type="text" path="count" class="form-control" placeholder="Count"/>
                <form:errors path="count" cssClass="error" element="div"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="label.product.price"/></label>
            <div class="col-md-8">
                <form:input type="text" path="price" class="form-control" placeholder="Price"/>
                <form:errors path="price"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="label.product.photo"/></label>
            <div class="col-md-8">
                <form:input type="file" path="photo" class="form-control"/>
                <form:errors path="photo"/>
            </div>
        </div>

        <!-- ADD PRODUCT -->
        <div class="form-group">

            <label class="col-md-3 control-label"></label>

            <div class="col-md-8">
                <c:if test="${!empty productForm.name}">
                    <input class="btn btn-lg btn-primary btn-block"
                           value="<spring:message code="label.product.edit"/>" role="button" type="submit"/>
                </c:if>
                <c:if test="${empty productForm.name}">
                    <input class="btn btn-lg btn-primary btn-block"
                           value="<spring:message code="label.product.add"/>" role="button" type="submit"/>
                </c:if>
            </div>
        </div>
    </form:form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="../../../resources/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>
