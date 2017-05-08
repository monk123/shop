<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <c:if test="${not empty product}">
            <div class="row">
                <div class="col-lg-3 col-md-3">
                    <img class="img-responsive" src="${product.photo}" alt="">
                </div>
                <div class="col-lg-3 col-md-3">
                    <p>${product.name}, ${product.price} $</p>
                </div>
                <div class="col-lg-3 col-md-3">
                    <p>Quantity: ${product.count}</p>
                </div>
                <div class="col-lg-3 col-md-3">
                    <!-- <p>Total: {product.getProductTotalPrice()}</p> сделать метод тотал price-->
                </div>
            </div>
            <hr>
        </c:if>
    </div>
    <div class="row">
        <!--<span class="pull-right">Total: { totalPrice }</span> сделать метод тотал price -->
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
