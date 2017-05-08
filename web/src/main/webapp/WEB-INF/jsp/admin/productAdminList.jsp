<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <th><spring:message code="label_product_id"/></th>
                <th><spring:message code="label_product_name"/></th>
                <th><spring:message code="label_product_description"/></th>
                <th><spring:message code="label_product_price"/></th>
                <th><spring:message code="label_product_category"/></th>
                <th><spring:message code="label_product_photo"/></th>
                <th><spring:message code="label_product_edit"/></th>
                <th><spring:message code="label_product_delete"/></th>
                <th><spring:message code="label_product_show"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.description}</td>
                    <td>${product.price}</td>
                    <td>${product.category.categoryName}</td>
                    <td><img src="${product.photo}"/></td>

                    <spring:url value="admin/product/showById/${product.id}" var="productUrl" />
                    <spring:url value="admin/product/delete/${product.id}" var="deleteUrl" />
                    <spring:url value="admin/product/edit/${product.id}" var="updateUrl" />

                    <td>
                        <button class="btn btn-primary" onclick="location.href='${updateUrl}'">
                            <spring:message code="label_product_edit"/>
                        </button>
                    </td>
                    <td>
                        <button class="btn btn-danger" onclick="location.href='${deleteUrl}'">
                            <spring:message code="label_product_delete"/>
                        </button>
                    </td>
                    <td>
                        <button class="btn btn-info" onclick="location.href='${productUrl}'">
                            <spring:message code="label_product_show"/>
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <div class="row">
        <spring:url value="/admin/product/adminAddProductForm" var="addURL"/>
        <button class="btn btn-primary" onclick="location.href='${addURL}'">
            <spring:message code="label_product_add"/>
        </button>
    </div>

    <div class="row">
        <ul class="pagination">
            <li href="">1</li>
        </ul>
    </div>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="../../../resources/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>
