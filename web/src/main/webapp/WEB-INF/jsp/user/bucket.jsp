<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set value="/resources/font/" var="img"/>
<html>
<head>
    <title>Bucket</title>

    <jsp:include page="../fragments/layouts.jsp"/>
</head>
<body>

<jsp:include page="../fragments/header.jsp"/>

<div class="container">
<!-- Page Heading -->
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Shopping Cart
            <small>Item List</small>
        </h1>
    </div>
</div>
<!-- /.row -->

<!-- page content  -->
<div>

    <div class="row">
        <!-- left category  -->
        <c:forEach var="pr" items="${sessionScope.product}">
            <div class="row">
                <div class="col-lg-3 col-md-3">
                    <img class="img-responsive" src="${img}${pr.product.photo}" alt="">
                </div>
                <div class="col-lg-3 col-md-3">
                    <p>${pr.product.name}, ${pr.product.price} </p>
                    <p>${pr.count}</p>
                </div>
                <div class="col-lg-3 col-md-3">
                     <p><spring:message code="label.product.total"/>$</p>
                </div>
            </div>
            <div class="col-lg-6 col-md-6">
                <a href="/product/bucket/delete/${pr.product.id}" class="btn-primary">
                    <spring:message code="label_product_delete"/>
                </a>
            </div>
            <hr>
        </c:forEach>
    </div>

    <div class="row">
    </div>

    <div class="row">
        <spring:url value="#" var="orderURL"/>
        <button onclick="location.href='${orderURL}'" id="checkout-btn" type="button"
                class="btn btn-primary btn-md pull-right">
            <spring:message code="label.product.checkout"/>
        </button>
    </div>

</div>
</div>
</body>
</html>
