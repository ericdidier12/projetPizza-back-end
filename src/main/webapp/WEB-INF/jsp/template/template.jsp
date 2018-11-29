<%@ include file="../include/importTags.jsp" %>
<%--<%@ include file="../include/importLinks.jsp" %>--%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title><tiles:getAsString name="title"/></title>
    <link type="text/css" href="<spring:url value='/css/webApp.css'/>" rel="stylesheet"/>
    <!-- Bootstrap core CSS -->
    <link type="text/css" href="<spring:url value='/vendor/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet"/>
    <link type="text/css" href="<spring:url value='/vendor/bootstrap/js/bootstrap.bundle.js'/>" rel="stylesheet"/>
    <link type="text/css" href="<spring:url value='/vendor/bootstrap/js/popper.min.js'/>" rel="stylesheet"/>
    <!-- Custom styles for this template -->
    <link type="text/css" href="<spring:url value='/css/heroic-features.css'/>" rel="stylesheet"/>
    <link type="text/css" href="<spring:url value='/jquery-ui/jquery.datetimepicker.min.css'/>" rel="stylesheet"/>
    <link type="text/css" href="<spring:url value='/jquery-ui/jquery.js'/>" rel="stylesheet"/>
    <link type="text/css" href="<spring:url value='/jquery-ui/jquery.datetimepicker.full.js'/>" rel="stylesheet"/>




    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</head>
<body>
<div>
    <tiles:insertAttribute name="header"/>
</div>

<div class="container">
    <tiles:insertAttribute name="main-content"/>
</div>

<!---- footer --->
<div>
    <tiles:insertAttribute name="footer"/>
</div>

</body>
</html>