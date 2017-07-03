<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

        <div class="col-md-9 personal-info">

            <h3><spring:message code="label.user.info"/></h3>

                <!-- id -->
            <div class="form-group">
                <label class="col-md-8 control-label"><spring:message code="label.user.id"/></label>
                <div class="col-md-3">${userForm.id}</div>
            </div>
                <!-- username -->
            <div class="form-group">
                <label class="col-md-8 control-label"><spring:message code="label.user.username"/></label>
                <div class="col-md-3">${userForm.username}</div>
            </div>
                <!-- lastName -->
            <div class="form-group">
                <label class="col-md-8 control-label"><spring:message code="label.user.lastName"/></label>
                <div class="col-md-3">${userForm.lastName}</div>
            </div>
                <!-- email -->
            <div class="form-group">
                <label class="col-md-8 control-label"><spring:message code="label.user.email"/></label>
                <div class="col-md-3">${userForm.email}</div>
            </div>
                <!-- phone -->

            <div class="form-group">
                <label class="col-md-8 control-label"><spring:message code="label.user.phone"/></label>
                <div class="col-md-3">${userForm.phone}</div>
            </div>

                <!-- country -->

            <div class="form-group">
                <label class="col-md-8 control-label"><spring:message code="label.user.country"/></label>
                <div class="col-md-3">${userForm.address.country}</div>
            </div>

                <!-- region -->

            <div class="form-group">
                <label class="col-md-8 control-label"><spring:message code="label.user.region"/></label>
                <div class="col-md-3">${userForm.address.region}
                </div>
            </div>
                <!-- city -->
            <div class="form-group">
                <label class="col-md-8 control-label"><spring:message code="label.user.city"/></label>
                <div class="col-md-3">${userForm.address.city}

                </div>
            </div>

                <!-- street -->
            <div class="form-group">
                <label class="col-md-8 control-label"><spring:message code="label.user.street"/></label>
                <div class="col-md-3">${userForm.address.street}</div>
            </div>

            <div class="form-group">

                <label class="col-md-3 control-label"></label>

                <div class="col-md-8">

                    <spring:url value="/welcome/user/edit/${userForm.id}" var="userURL"/>
                    <input type="button" class="btn btn-primary" onclick="location.href='${userURL}'"
                           value="<spring:message code="label.button.edit"/> ">
                    <span></span>
                    <spring:url value="/welcome/${1}" var="adminListURL"/>
                    <input type="reset" class="btn btn-default" onclick="location.href='${adminListURL}'"
                           value="<spring:message code="label.button.cancel"/> ">
                </div>

            </div>

        </div>

    </div>
</div>
</body>
</html>
