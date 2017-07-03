<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddCategory</title>
    <jsp:include page="../fragments/layouts.jsp"/>
</head>
<body>

<jsp:include page="../fragments/header.jsp"/>

<div class="container">
    <form:form method="post" modelAttribute="newCategory" action="/admin/product/category/addCategory">

        <form:hidden path="id"/>

        <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="label.product.name"/></label>
            <div class="col-md-8">
                <form:input type="text" path="categoryName" class="form-control" placeholder="Name"/>
                <form:errors path="categoryName" cssClass="error" element="div"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="label.product.description"/></label>
            <div class="col-md-8">
                <form:textarea type="text" path="description" class="form-control" placeholder="Price"/>
                <form:errors path="description"/>
            </div>
        </div>

        <div class="form-group">

            <label class="col-md-3 control-label"></label>

            <div class="col-md-8">
                <c:if test="${!empty newCategory.categoryName}">
                    <input class="btn btn-lg btn-primary btn-block"
                           value="<spring:message code="label.product.edit"/>" role="button" type="submit"/>
                </c:if>
                <c:if test="${empty newCategory.categoryName}">
                    <input class="btn btn-lg btn-primary btn-block"
                           value="<spring:message code="label.product.add"/>" role="button" type="submit"/>
                </c:if>

                <spring:url value="/admin/product/list/${1}" var="adminListURL"/>
                    <input type="reset" class="btn btn-default" onclick="location.href='${adminListURL}'"
                           value="<spring:message code="label.button.cancel"/> ">
            </div>
        </div>
    </form:form>
</div>
</body>
</html>
