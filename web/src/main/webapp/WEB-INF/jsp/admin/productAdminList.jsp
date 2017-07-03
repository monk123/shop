<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="img" value="/resources/font/"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>AdminListPage</title>

    <jsp:include page="../fragments/layouts.jsp"/>

</head>
<body>

<jsp:include page="../fragments/header.jsp"/>

<div class="container">

    <c:if test="${! empty products}">
        <table class="table jsgrid-header-sortable">
            <thead>
            <tr>
                <th><spring:message code="label.product.name"/></th>
                <th><spring:message code="label.product.description"/></th>
                <th><spring:message code="label.product.price"/></th>
                <th><spring:message code="label.product.category"/></th>
                <th><spring:message code="label.product.photo"/></th>
                <th><spring:message code="label.product.edit"/></th>
                <th><spring:message code="label.product.delete"/></th>
                <th><spring:message code="label.product.show"/></th>
            </tr>
            </thead>
            <tbody class="table table-hover">
            <c:forEach items="${products}" var="product">
                <tr>
                    <td>${product.name}</td>
                    <td>${product.description}</td>
                    <td>${product.price}</td>
                    <td>${product.category.categoryName}</td>
                    <td><img src="${img}${product.photo}"/></td>

                    <spring:url value="/admin/product/showById/${product.id}" var="productUrl" />
                    <spring:url value="/admin/product/delete/${product.id}" var="deleteUrl" />
                    <spring:url value="/admin/product/edit/${product.id}" var="updateUrl" />

                    <td>
                        <button class="btn btn-primary" onclick="location.href='${updateUrl}'">
                            <spring:message code="label.product.edit"/>
                        </button>
                    </td>
                    <td>
                        <button class="btn btn-danger" onclick="location.href='${deleteUrl}'">
                            <spring:message code="label.product.delete"/>
                        </button>
                    </td>
                    <td>
                        <button class="btn btn-info" onclick="location.href='${productUrl}'">
                            <spring:message code="label.product.show"/>
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <div class="row">
        <div class="col">
        <spring:url value="/admin/product/adminAddProductForm" var="addURL"/>
        <button class="btn btn-primary" onclick="location.href='${addURL}'">
            <spring:message code="label.product.add"/>
        </button>
        </div>
        <div class="col">
            <spring:url value="/admin/product/category/addCategory" var="addCategoryURL"/>
            <button class="btn btn-primary" onclick="location.href='${addCategoryURL}'">
                <spring:message code="label.button.add.category"/>
            </button>
        </div>
    </div>

    <div class="row">
        <c:forEach var="page"  begin="1" end="${size}">
        <ul class="pagination">
            <li><a href="/admin/product/list/${page}">${page}</a></li>
        </ul>
        </c:forEach>
    </div>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="../../../resources/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>
