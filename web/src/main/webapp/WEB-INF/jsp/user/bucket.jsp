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

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">
            <small><spring:message code="label.product.item.list"/></small>
        </h1>
    </div>
</div>
<!-- /.row -->

<!-- page content  -->
<div>

    <div class="row">
        <!-- left category  -->
        <c:set var="price" value="0"/>
        <c:forEach var="pr" items="${sessionScope.product}">
            <c:set var="price" value="${price + pr.product.price * pr.count}"/>
            <div class="row">
                <div class="col-lg-3 col-md-3">
                    <img class="img-responsive" src="${img}${pr.product.photo}" alt="">
                </div>
                <div class="col-lg-3 col-md-3">
                    <p>${pr.product.name}, ${pr.product.price * pr.count} </p>
                    <p>${pr.count}</p>
                </div>
            </div>
            <div class="col-lg-6 col-md-6">
                <a href="/product/bucket/delete/${pr.product.id}" class="btn-primary">
                    <spring:message code="label.product.delete"/>
                </a>
            </div>
            <hr>
        </c:forEach>

        <div class="col-lg-3 col-md-3">
            <p><spring:message code="label.product.total"/> ${price} $</p>
        </div>
    </div>

    <div class="row">
    </div>

    <spring:url value="/product/orders" var="orderURL"/>

    <c:choose>
        <c:when test="${price == 0}">
            <div class="row">
                <button type="" onclick="location.href='${orderURL}'" id="checkout-btn" type="button"
                        class="btn btn-primary btn-md pull-right" disabled="disabled">
                    <spring:message code="label.product.checkout"/>
                </button>
            </div>
        </c:when>
        <c:otherwise>
            <div class="row">
                <button onclick="location.href='${orderURL}'" id="checkout-btn1" type="button"
                        class="btn btn-primary btn-md pull-right">
                    <spring:message code="label.product.checkout"/>
                </button>
            </div>
        </c:otherwise>
    </c:choose>

    </div>
</div>
</body>
</html>
