<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Create an account</title>

    <jsp:include page="fragments/layouts.jsp"/>

</head>

<body>

<jsp:include page="fragments/header.jsp"/>

<div class="container">

    <form:form method="POST" modelAttribute="userForm" class="form-signin">

        <h2 class="form-signin-heading">Create your account</h2>

        <div class="form-group">
            <form:input type="text" path="username" class="form-control" placeholder="Username"
                        autofocus="true"/>
            <form:errors path="username"/>
        </div>

        <div class="form-group">
            <form:input type="password" path="password" class="form-control" placeholder="Password"/>
            <form:errors path="password"/>
        </div>

        <div class="form-group">
            <form:input type="password" path="confirmPassword" class="form-control"
                        placeholder="Confirm your password"/>
            <form:errors path="confirmPassword"/>
        </div>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>

    </form:form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="../../resources/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>