<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Info</title>
    <jsp:include page="../fragments/layouts.jsp"/>
</head>
<body>

<jsp:include page="../fragments/header.jsp"/>

<div class="container">

    <!-- page content  -->
    <div class="row">
        <!-- left category  -->
        <div class="col-md-6">
            <img class="img-responsive" src="${product.photo}" alt="">
        </div>

        <!-- right category  -->
        <div class="col-md-6">
            <div>

                <h3>${product.name}</h3>
                <p>${product.price} $</p>

                <form:form modelAttribute="product" method="post">

                    <div class="row">

                    <spring:bind path="count">
                        <div class="col-lg-6 col-md-6">
                            <div class="input-group ${status.error ? 'has-error' : ''}">
                                <span class="input-group-addon" ><spring:message code="label.product.quantity"/></span>
                                <form:input id="quantity-input" type="text" class="form-control" placeholder="0"
                                            aria-describedby="quantity-input" path="count"/>
                                <form:errors path="count"/>
                            </div>
                         </div>
                    </spring:bind>

                    <div class="col-lg-3 col-md-3">
                        <spring:url value="/bucket" var="bucketURL"/>
                            <button id="add-to-basket-btn data-product-code=" type="button"
                                    class="btn btn-primary btn-md" data-toggle="modal"
                                    data-target="#success-modal" onclick="location.href='${bucketURL}'">
                                <spring:message code="label.product.addBucket"/>
                            </button>
                    </div>
                </div>
                </form:form>

                <br />

                <div class="row">
                    <table class="table">
                        <thead>
                        <td>Product Details</td>
                        </thead>
                        <tbody>
                        <tr>
                            <td><spring:message code="label_product_description"/>:</td>
                            <td>${product.description}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    </div>
</body>
</html>