<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="img" value="/resources/font/"/>
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
            <img class="img-responsive" src="${img}${product.photo}" alt="">
        </div>

        <!-- right category  -->
        <div class="col-md-6">
            <div>

                <h3>${product.name}</h3>
                <p>${product.price} $</p>

                <div class="row">

                    <div class="col-lg-3 col-md-3">
                        <spring:url value="/product/bucket/${product.id}" var="bucketURL"/>
                            <button id="add-to-basket-btn data-product-code=" type="button"
                                    class="btn btn-primary btn-md" data-toggle="modal"
                                    data-target="#success-modal" onclick="location.href='${bucketURL}'">
                                <spring:message code="label.product.addBucket"/>
                            </button>
                    </div>
                </div>
                <br />
                <div class="row">
                    <table class="table">
                        <thead>
                        <td><spring:message code="label.product.details"/></td>
                        </thead>
                        <tbody>
                        <tr>
                            <td><spring:message code="label.product.description"/>:</td>
                            <td>${product.description}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <c:forEach var="commentProduct" items="${comments}">
    <div class="row">
        <div class="col-sm-8">
            <div class="panel panel-white post panel-shadow">
                <div class="post-heading">
                    <div class="pull-left meta">
                        <div class="title h5">
                            <a href="#"><b>${username}</b></a>
                            made a post.
                        </div>
                        <h6 class="text-muted time">1 minute ago</h6>
                    </div>
                </div>
                <div class="post-description">
                    <p>${commentProduct.comment}</p>
                   <!-- <div class="stats">
                        <a href="#" class="btn btn-default stat-item">
                            <i class="fa fa-thumbs-up icon"></i>2
                        </a>
                        <a href="#" class="btn btn-default stat-item">
                            <i class="fa fa-thumbs-down icon"></i>12
                        </a>
                    </div> -->
                </div>
            </div>
        </div>
    </div>
    </c:forEach>

    <div class="row">
        <spring:url value="/welcome/add/comment/${product.id}" var="commentURL"/>

        <form:form role="form" id="contact-form" class="contact-form"
               modelAttribute="commit" method="post" action="${commentURL}">

        <div class="row">

            <form:hidden path="product.id"/>

            <div class="col-md-12">
                <div class="form-group">
                    <form:textarea class="form-control textarea" rows="3"
                               name="Message" id="Message" placeholder="Message" path="comment"/>
                    <form:errors path="comment"/>
                </div>
            </div>
        </div>

        <label class="col-md-3 control-label"></label>

        <div class="col-md-8">
            <c:if test="${empty commit.comment}">
                <input class="btn main-btn pull-right" role="button" type="submit"
                       value="<spring:message code="label.button.comment"/>"/>
            </c:if>
        </div>
    </form:form>
    </div>
</div>
</body>
</html>