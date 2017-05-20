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
        <label class="col-sm-2"><spring:message code="label_product_id"/></label>
        <div class="col-sm-10">${productForm.id}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="label_product_name"/></label>
        <div class="col-sm-10">${productForm.name}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="label_product_category"/></label>
        <div class="col-sm-10">${productForm.category.categoryName}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="label_product_description"/></label>
        <div class="col-sm-10">${productForm.description}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="label_product_price"/></label>
        <div class="col-sm-10">${productForm.price}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="label_product_photo"/></label>
        <div class="col-sm-10"><img src="${img}${productForm.photo}"/></div>
    </div>

    <spring:url value="/admin/product/edit/${productForm.id}" var="updateURL"/>
    <button class="btn btn-info" type="submit" onclick="location.href='${updateURL}'">
        <spring:message code="label_product_edit"/>
    </button>

</div>
</body>
</html>
