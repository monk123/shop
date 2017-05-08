<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserPage</title>

    <jsp:include page="../fragments/layouts.jsp"/>

</head>
<body>
<jsp:include page="../fragments/header.jsp"/>

<div class="container">

    <h1><spring:message code="label.user.profile"/></h1>
    <hr>
    <div class="row">
        <!-- left column
        <div class="col-md-3">
            <div class="text-center">
                <img src="//placehold.it/100" class="avatar img-circle img-thumbnail" alt="avatar">
                <h6></h6>

                <input type="file" class="form-control">
            </div>
        </div>-->

        <!-- edit form column -->
        <div class="col-md-9 personal-info">
            <div class="alert alert-info alert-dismissable">
                <a class="panel-close close" data-dismiss="alert">×</a>
                <i class="fa fa-coffee"></i>
                This is an <strong>.alert</strong>. Use this to show important messages to the user.
            </div>
            <h3><spring:message code="label.user.info"/></h3>

            <form:form class="form-horizontal" role="form" modelAttribute="user" method="post">

                <!-- username -->
                <spring:bind path="username">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="col-lg-3 control-label"><spring:message code="label.user.username"/></label>
                        <div class="col-lg-8">
                            <form:input class="form-control" type="text" path="username"/>
                            <form:errors path="username"/>
                        </div>
                    </div>
                </spring:bind>

                <!-- lastName -->
                <spring:bind path="lastName">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-lg-3 control-label"><spring:message code="label.user.lastName"/></label>
                    <div class="col-lg-8">
                        <form:input class="form-control" type="text" path="lastName"/>
                        <form:errors path="lastName"/>
                    </div>
                </div>
                </spring:bind>

                <!-- email -->
                <spring:bind path="email">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-lg-3 control-label"><spring:message code="label.user.email"/></label>
                    <div class="col-lg-8">
                        <form:input class="form-control" type="text" path="email"/>
                        <form:errors path="email"/>
                    </div>
                </div>
                </spring:bind>

                <!-- phone -->
                <spring:bind path="phone">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-lg-3 control-label"><spring:message code="label.user.phone"/></label>
                    <div class="col-lg-8">
                        <form:input class="form-control" type="text" path="phone"/>
                        <form:errors path="phone"/>
                    </div>
                </div>
                </spring:bind>

                <!-- country -->
                <spring:bind path="addresses.country">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="col-lg-3 control-label"><spring:message code="label.user.country"/></label>
                        <form:input path="addresses.country" class="form-control" type="text"/>
                        <form:errors path="addresses.country"/>
                    </div>
                </spring:bind>

                <!-- region -->
                <spring:bind path="region">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-md-3 control-label"><spring:message code="label.user.region"/></label>
                    <div class="col-md-8">
                        <form:input class="form-control" type="text" path="addresses.region"/>
                        <form:errors path="addresses.region"/>
                    </div>
                </div>
                </spring:bind>

                <!-- city -->
                <spring:bind path="city">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="col-md-3 control-label"><spring:message code="label.user.city"/></label>
                        <div class="col-md-8">
                            <form:input class="form-control" type="text" path="addresses.city"/>
                            <form:errors path="addresses.city"/>
                        </div>
                    </div>
                </spring:bind>

                <!-- street -->
                <spring:bind path="street">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="col-md-3 control-label"><spring:message code="label.user.street"/></label>
                        <div class="col-md-8">
                            <form:input class="form-control" type="text" path="addresses.street"/>
                            <form:errors path="addresses.street"/>
                        </div>
                    </div>
                </spring:bind>

                <!-- password -->
                <spring:bind path="password">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-md-3 control-label"><spring:message code="label.user.password"/></label>
                    <div class="col-md-8">
                        <form:input path="password" class="form-control" type="password"/>
                        <form:errors path="password"/>
                    </div>
                </div>
                </spring:bind>

                <!-- confirmPassword -->
                <spring:bind path="confirmPassword">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="col-md-3 control-label"><spring:message code="label.user.confirmPassword"/></label>
                        <div class="col-md-8">
                            <form:input path="confirmPassword" class="form-control" type="password"/>
                            <form:errors path="confirmPassword"/>
                        </div>
                    </div>
                </spring:bind>

                <div class="form-group">
                    <label class="col-md-3 control-label"></label>
                    <div class="col-md-8">
                        <input type="button" class="btn btn-primary" value="Save Changes">
                        <span></span>
                        <input type="reset" class="btn btn-default" value="Cancel">
                    </div>
                </div>

            </form:form>
        </div>
    </div>

    <jsp:include page="../fragments/footer.jsp"/>

</div>
<hr>
</body>
</html>
