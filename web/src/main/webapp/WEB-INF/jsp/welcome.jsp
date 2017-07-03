<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <p class="lead"><spring:message code="label.product.categories"/></p>

                <div id="category-options" class="list-group">
                    <c:forEach var="name" items="${categoryName}">
                        <a charset="UTF-8" href="<c:url value="/welcome/${name}/${1}"/>"
                           class="list-group-item category-select-option">${name}</a>
                    </c:forEach>
                </div>

                <form:form action="/welcome/filter/${1}" modelAttribute="dto" method="get">

                    <label><spring:message code="label.product.price"/></label>

                    <div class="form-group">
                        <form:input path="priceFrom" class="form-control" placeholder="From"/>
                        <form:errors path="priceFrom"/>
                    </div>

                    <div class="form-group">
                        <form:input path="priceTo" class="form-control" placeholder="To"/>
                        <form:errors path="priceTo"/>
                    </div>

                    <input class="btn btn-primary" value="apply" type="submit" role="button">
                </form:form>

                <form method="get" action="<c:url value="/welcome/search/${1}"/>" class="navbar-form navbar-left">
                    <div class="form-group">
                        <input type="text" id="name" name="name" class="form-control" placeholder="Search"/>
                    </div>
                    <input type="submit" class="btn btn-default"/>
                </form>

            </div>

            <div class="col-md-9">
                <div class="row">
                    <c:forEach var="product" items="${products}">
                        <div class="col-xs-12">
                            <img src="/resources/font/${product.photo}"/>
                            <div class="caption">
                                <h4 class="pull-right">${product.price} $</h4>
                                <h4><a href="#">${product.name}</a></h4>
                                <p>${product.description}</p>
                                <div class="col-md-4">
                                    <a href="<c:url value="/welcome/info/${product.id}"/>">
                                        <button type="button" class="btn btn-primary">
                                            <spring:message code="label.product.show"/>
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

    <c:url var="categoryUrl" value="/welcome/${category}/${pageCategory}"/>
    <c:url var="mainUrl" value="/welcome/${page}"/>
    <c:url var="searchUrl" value="/welcome/search/${searchPage}"/>
    <c:url var="filterUrl" value="/welcome/filter/${filterPage}"/>

    <c:choose>
        <c:when test="${requestScope['javax.servlet.forward.request_uri'] eq mainUrl}">
            <c:forEach var="page" begin="1" end="${size}">
                <ul class="pagination">
                    <li><a href="/welcome/${page}">${page}</a></li>
                </ul>
            </c:forEach>
        </c:when>
        <c:when test="${requestScope['javax.servlet.forward.request_uri'] eq categoryUrl}">
            <c:forEach var="page" begin="1" end="${sizeCategory}">
                <ul class="pagination">
                    <li><a href="/welcome/${category}/${page}">${page}</a></li>
                </ul>
            </c:forEach>
        </c:when>
        <c:when test="${requestScope['javax.servlet.forward.request_uri'] eq searchUrl}">
            <c:forEach var="page" begin="1" end="${searchSize}">
                <ul class="pagination">
                    <li><a href="/welcome/search/${page}?name=${params}">${page}</a></li>
                </ul>
            </c:forEach>
        </c:when>
        <c:when test="${requestScope['javax.servlet.forward.request_uri'] eq filterUrl}">
            <c:forEach var="page" begin="1" end="${filterSize}">
                <ul class="pagination">
                    <li><a href="/welcome/filter/${page}?priceFrom=${dto.priceFrom}&priceTo=${dto.priceTo}">${page}</a></li>
                </ul>
            </c:forEach>
        </c:when>
    </c:choose>

    </br>
    <hr>
    <hr>
</br>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="../../resources/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>