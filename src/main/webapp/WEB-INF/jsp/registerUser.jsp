<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="include/importTags.jsp" %>
<%@include file="include/importLinks.jsp" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="type=text/html ; charset=UTF-8"/>
    <title>Inscription</title>


    <style>
        .error {
            color: red;
        }
    </style>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap 4 DatePicker</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/gijgo@1.9.10/js/gijgo.min.js" type="text/javascript"></script>
    <link href="https://cdn.jsdelivr.net/npm/gijgo@1.9.10/css/gijgo.min.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="row"> <h2><spring:message code="registerUser"/> </h2>  </div>
<form:form id="form" method="POST" action="${pageContext.request.contextPath}/user/register/send"  modelAttribute="currentUser"  >

    <div class="form-row">
        <div class="form-group col-md-6" <spring:message code="fakeName" var="fakenamePlaceHolder"/>>
            <form:label  path="name" for="name"><spring:message code="firstSecondName"/></form:label>
            <form:input  path="name" class="form-control" id="name" placeholder="${fakenamePlaceHolder}"/>
            <form:errors path="name" cssClass="error"/>
        </div>

        <div class="form-group col-md-6"<spring:message code="fakeusername" var="fakeusernamePlaceHolder"/>>
            <form:label  path="username" for="username"><spring:message code="username"/></form:label>
            <form:input  path="username" class="form-control" id="username" placeholder="${fakeusernamePlaceHolder}"/>
            <form:errors path="username" cssClass="error"></form:errors>

        </div>

        <div class="form-group col-md-6">
            <form:label  path="password" for="password"><spring:message code="password"/></form:label>
            <form:password path="password" class="form-control" id="password" placeholder="********************"/>
            <form:errors path="password" cssClass="error"></form:errors>
        </div>

        <div class="form-group col-md-6" <spring:message code="fakeEmail" var="fakeEmailPlaceHolder"/>>
            <form:label path="email" for="email">Email</form:label>
            <form:input  path="email"  class="form-control" id="email" placeholder="${fakeEmailPlaceHolder}"/>
            <form:errors path="email" cssClass="error"></form:errors>
        </div>

        <div class="form-group col-md-4">
            <spring:message code="fakeBirth_date" var="fakeBirth_date"/>
            <form:label path="birth_date" for="birth_date"><spring:message code="birth_date"/> </form:label>
            <form:input path="birth_date" id="birth_date"/>
            <form:errors path="birth_date" cssClass="error"></form:errors>
        </div>

        <div class="form-group col-md-6" <spring:message code="fakeAddress" var="fakeAddressPlaceHolder"/>>
            <form:label path="adress" for="adress"><spring:message code="address"/> </form:label>
            <form:input  path="adress" class="form-control" id="adress" placeholder="${fakeAddressPlaceHolder}"/>
            <form:errors path="adress" cssClass="error"></form:errors>
        </div>
    </div>

    <button type="submit" class="btn btn-primary"><spring:message code="Signin"/></button>
</form:form>


<script>
    $('#birth_date').datepicker({
        uiLibrary: 'bootstrap4'
    });
</script>

<script type="text/javascript">
    $(function () {
        $('#birth_date').datepicker();
    });
</script>
</body>

</html>