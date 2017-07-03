<%@ taglib prefix="sring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="img" value="/resources/font/"/>
<html>
<head>
    <title>show</title>
    <jsp:include page="../fragments/layouts.jsp"/>
</head>
<body>

<jsp:include page="../fragments/header.jsp"/>

<div class="container">

    <div class="row">
        <label class="col-sm-2"><spring:message code="label.product.id"/></label>
        <div class="col-sm-10">${productForm.id}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="label.product.name"/></label>
        <div class="col-sm-10">${productForm.name}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="label.product.category"/></label>
        <div class="col-sm-10">${productForm.category.categoryName}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="label.product.description"/></label>
        <div class="col-sm-10">${productForm.description}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="label.product.quantity"/></label>
        <div class="col-sm-10">${productForm.count}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="label.product.price"/></label>
        <div class="col-sm-10">${productForm.price}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="label.product.photo"/></label>
        <div class="col-sm-10"><img src="${img}${productForm.photo}"/></div>
    </div>

    <spring:url value="/admin/product/edit/${productForm.id}" var="updateURL"/>
    <button class="btn btn-info" type="submit" onclick="location.href='${updateURL}'">
        <spring:message code="label.product.edit"/>
    </button>

    <spring:url value="/admin/product/list/${1}" var="cancelURL"/>
    <button class="btn btn-info" type="submit" onclick="location.href='${cancelURL}'">
        <spring:message code="label.button.cancel"/>
    </button>

</div>
</body>
</html>
