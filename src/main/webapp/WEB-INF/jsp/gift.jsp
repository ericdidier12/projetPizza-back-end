<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="include/importTags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
    <title><tiles:getAsString name="title"/></title>
    <link type="text/css" href="<spring:url value='/css/accept_Of_tab.css'/>" rel="stylesheet" />
</head>


<spring:url var="localeFr" value="">
    <spring:param name="locale" value="fr" />
</spring:url>

<spring:url var="localeEn" value="">
    <spring:param name="locale" value="en" />
</spring:url>


<a href="${localeFr}">pour fr </a>
<br>
<br>
<br>

<a href="${localeEn}">pour en</a>

<spring:message code="Congratulations"> </spring:message>
${nameChild}, <spring:message code="Sentence"> </spring:message> :${hobby} <spring:message code="gift"> </spring:message> : ${cadeauChild }!!!!!<br>


<div class="container">

    <form:form id="form" method="POST" action="/firstSpring/gift/send"  modelAttribute="banckAccount"  >
    <table border="1" width="8%">

        <tr>
            <td> <p>voulez vous ouverture compte bancaire sans rendez vous ?<br> <f:hidden path="iban"/></td>
        </tr>

        <tr>
            <td>  <input type="submit" value="OUI"/> </td>
        </tr>

        <tr>
            <td>
                <a  href="${pageContext.request.contextPath}/inscription">NON</a>
            </td>
        </tr>
        </form:form>
    </table>
</div>






