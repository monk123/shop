<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>

    <title>Welcome</title>

    <jsp:include page="fragments/layouts.jsp"/>

</head>

<body>

<jsp:include page="fragments/header.jsp"/>

<div class="container">
        <div class="row">
            <div class="col-md-3">
                <p class="lead">Categories</p>

                <div id="category-options" class="list-group">
                    <c:forEach var="name" items="${categoryName}">
                        <a charset="UTF-8" href="<c:url value="/product/category/${name}"/>"
                           class="list-group-item category-select-option">${name}</a>
                    </c:forEach>
                </div>
            </div>
            <div class="col-md-9">
                <div class="row">
                    <c:forEach var="product" items="${products}">
                        <div class="col-xs-12">
                            <img src="resources/font/${product.photo}"/>
                            <div class="caption">
                                <h4 class="pull-right">${product.price} $</h4>
                                <h4><a href="#">${product.name}</a></h4>
                                <p>${product.description}</p>
                                <div class="col-md-4">
                                    <a href="<c:url value="/product/info/${product.id}"/>">
                                        <button type="button" class="btn btn-primary">
                                            <spring:message code="label_product_show"/>
                                        </button>
                                    </a>
                                </div>
                                <div class="col-md-6">
                                    <spring:url value="/product/bucket/${product.id}" var="orderURL"/>
                                    <button type="button" class="btn-default" onclick="location.href='${orderURL}'">
                                        <spring:message code="label.product.addBucket"/>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </br>
    <hr>
    <hr>
</br>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="../../resources/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>