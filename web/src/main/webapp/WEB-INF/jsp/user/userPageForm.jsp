<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserPageForm</title>

    <jsp:include page="../fragments/layouts.jsp"/>

</head>
<body>

<jsp:include page="../fragments/header.jsp"/>

<div class="container">

    <h1><spring:message code="label.user.profile"/></h1>
    <hr>
    <div class="row">

        <!-- edit form column -->
        <div class="col-md-9 personal-info">

            <h3><spring:message code="label.user.info"/></h3>

            <spring:url value="/welcome/user/edit/${userForm.id}" var="userURL"/>

            <form:form class="form-horizontal" modelAttribute="userForm" method="post" action="${userURL}">

                <!-- id -->
                <form:hidden path="id"/>

                <!-- username -->
                <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="label.user.username"/></label>
                    <div class="col-md-8">
                        <form:input class="form-control" type="text" path="username"/>
                        <form:errors path="username" cssClass="error"/>
                    </div>
                </div>

                <!-- lastName -->
                <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="label.user.lastName"/></label>
                    <div class="col-md-8">
                        <form:input class="form-control" type="text" path="lastName"/>
                        <form:errors path="lastName" cssClass="error"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="label.user.email"/></label>
                    <div class="col-md-8">
                        <form:input class="form-control" type="text" path="email"/>
                        <form:errors path="email" cssClass="error"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="label.user.phone"/></label>
                    <div class="col-md-8">
                        <form:input class="form-control" type="text" path="phone"/>
                        <form:errors path="phone" cssClass="error"/>
                    </div>
                </div>

                <form:hidden path="address.id"/>

                <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="label.user.country"/></label>
                    <div class="col-md-8">
                        <form:input path="address.country" class="form-control" type="text"/>
                        <form:errors path="address.country" cssClass="error"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="label.user.region"/></label>
                    <div class="col-md-8">
                        <form:input class="form-control" type="text" path="address.region"/>
                        <form:errors path="address.region" cssClass="error"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="label.user.city"/></label>
                    <div class="col-md-8">
                        <form:input class="form-control" type="text" path="address.city"/>
                        <form:errors path="address.city" cssClass="error"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="label.user.street"/></label>
                    <div class="col-md-8">
                        <form:input class="form-control" type="text" path="address.street"/>
                        <form:errors path="address.street" cssClass="error"/>
                    </div>
                </div>

                <form:hidden path="password"/>

                <form:hidden path="confirmPassword"/>

                <div class="form-group">

                    <label class="col-md-3 control-label"></label>

                    <div class="col-md-8">
                        <c:if test="${!empty userForm.username}">
                            <input class="btn btn-primary"
                                   value="<spring:message code="label.button.edit"/>" role="button" type="submit"/>
                        </c:if>
                        <c:if test="${empty userForm.username}">
                            <input class="btn btn-primary btn-block" role="button" type="submit"
                                   value="<spring:message code="label.button.save"/>"/>
                        </c:if>
                    </div>

                </div>
            </form:form>
        </div>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="../../../resources/bootstrap/js/bootstrap.min.js"></script>

</div>
<hr>
</body>
</html>
