<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html ; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="include/importTags.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="type=text/html ; charset=UTF-8"/>
        <title>Welcome</title>
    </head>
    <body <spring:message code="username" var = "usernameplaceholder"/><spring:message code="password" var="passwordPlaceHolder"/>>

                <!-- Default form login -->
                <form:form method="post" modelAttribute="userLogin" class="text-center border border-light p-5">

                    <p class="h4 mb-4"><spring:message code="Signin"/></p>

                    <!-- username -->
                    <form:input path="username" placeholder= "${usernameplaceholder}" class="form-control mb-4"/>

                    <!-- Password -->
                    <form:password path="password" class="form-control mb-4" placeholder="${passwordPlaceHolder}"/>
                    <!-- Sign in button -->
                    <form:button class="btn btn-info btn-block my-4" type="submit"><spring:message code="Signin"/></form:button>
                    <!-- Register -->
                    <p><spring:message code="Notamember"/>
                        <a href="${pageContext.request.contextPath}/user/register"><spring:message code="register"/></a>
                    </p>

                </form:form>
                <!-- Default form login -->

    </body>
</html>
