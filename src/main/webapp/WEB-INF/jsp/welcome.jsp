<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="include/importTags.jsp"%>
<%--<%@include file="include/importLinks.jsp"%>--%>

<!-- Page Content -->
<div class="container">
    <header class="jumbotron my-4">
        <h1 class="display-3">

            <spring:message code="messagewelcome"/>

        </h1>
        <p class="lead">
            <spring:message code="messageHome"/>
        <div>
            <img src="<spring:url value="/images/imagePiza.jpg"/>"/>
        </div>
    </header>
</div>
