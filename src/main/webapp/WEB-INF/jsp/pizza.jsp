<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="include/importTags.jsp" %>
<%@include file="include/importLinks.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="type=text/html ; charset=UTF-8"/>
    <title>Pizza</title>

    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
<!-- Page Content -->
<div class="container">
    <!-- Category of Pizza -->
    <div class="container">
        <div class="row">
            <div class="dropdown">
                <button class="btn btn-block dropdown-toggle" type="button" data-toggle="dropdown"><spring:message
                        code="categoryOfPizzaList"/>
                    <span class="caret"></span></button>
                <ul class="dropdown-menu">
                    <c:forEach var="categorie" items="${categories}">
                        <li>
                            <a href="${pageContext.request.contextPath}/pizza/trieCategorieByName/${categorie.name}"><c:out
                                    value="${categorie.name}"></c:out></a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <hr>
    <!-- Section: Pizza v.2 -->
    <section class="text-center my-5">
        <!-- Grid row -->
        <div class="row">

            <c:if test="${pizzas != null }">

                <c:forEach items="${pizzas}" var="infospizza">

                    <c:if test="${infospizza.fixed ==true}"> <!--affiche unniquement le produit fixed -->

                        <!-- Grid column -->
                        <div class="col-lg-4 col-md-12 mb-lg-0 mb-4">
                            <!-- Card -->
                            <div class="card card-cascade wider card-ecommerce">
                                <!-- Card image -->
                                <div class="view view-cascade overlay">
                                    <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/a3/Eq_it-na_pizza-margherita_sep2005_sml.jpg/280px-Eq_it-na_pizza-margherita_sep2005_sml.jpg"
                                         class="card-img-top"
                                         alt="sample photo">
                                    <a>
                                        <div class="mask rgba-white-slight"></div>
                                    </a>
                                </div>
                                <!-- Card image -->
                                <!-- Card content -->
                                <div class="card-body card-body-cascade text-center">
                                    <!-- Category & Title -->

                                    <a href="" class="text-muted">
                                        <h4 class="card-title">
                                            <strong>
                                                <c:out value="${infospizza.name}"></c:out>
                                            </strong>
                                        </h4>
                                    </a>

                                    <h2 class="card-title">
                                        <strong>
                                            <u> <spring:message code="ingredient"/></u>
                                        </strong>
                                    </h2>

                                    <!-- Description -->
                                    <c:forEach var="ingredient" items="${infospizza.ingredients}">
                                        <p class="card-text">${ingredient.name},</p>
                                    </c:forEach>
                                    <!-- Card footer -->
                                    <div class="card-footer px-1">
                                    <span class="float-left font-weight-bold">
                                    <strong><c:out value="${infospizza.price}"></c:out> €uros</strong>

                                    <form:form action="pizza/ajouterAuPanier" method="post"
                                               modelAttribute="ajoutPanierPizza">
                                        <form:hidden path="id" value="${infospizza.id}" name="id"/>
                                        <spring:message code="quantity"/>
                                        <form:input path="number" value="${infospizza.number}" name="number"/>
                                        <form:errors path="number" cssClass="error"></form:errors>

                                        <input type="submit" value="<spring:message code="addToCart"/>">
                                    </form:form></span>
                                    </div>
                                </div>
                                <!-- Card content -->
                            </div>
                            <!-- Card -->
                        </div>
                    </c:if>
                </c:forEach> </br>
            </c:if>
            <!-- Grid column -->
            <!---------------------------------------------------------------------------------------------------->
            <!--- Pizza Customized -->
            <!-- Grid column -->
            <div class="col-lg-4 col-md-12 mb-lg-0 mb-4">
                <!-- Card -->
                <div class="card card-cascade wider card-ecommerce">
                    <!-- Card image -->
                    <div class="view view-cascade overlay">
                        <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/a3/Eq_it-na_pizza-margherita_sep2005_sml.jpg/280px-Eq_it-na_pizza-margherita_sep2005_sml.jpg"
                             class="card-img-top"
                             alt="sample photo">
                        <a>
                            <div class="mask rgba-white-slight"></div>
                        </a>
                    </div>
                    <!-- Card image -->
                    <!-- Modal content ingredient  -->
                    <div class="card-body card-body-cascade text-center">
                        <!-- Category & Title -->
                        <a href="" class="text-muted">
                            <c:out value="${infospizza.name}"></c:out>
                        </a>
                        <!-- Title  & Description  & add cart pizza custom-->

                        <form:form action="pizza/ajouterAuPanierPizzaCustom" method="post"
                                   modelAttribute="ajoutPanierPizzaCustom">
                            <h4 class="card-title">
                                <form:label path="ingredients"><strong><u> <spring:message
                                        code="pizzacustom"/> </u></strong></form:label>
                            </h4>
                            <!-- Trigger the modal with a button -->
                            <button type="button" class="btn btn-info btn-lg" data-toggle="modal"
                                    data-target="#myModal"><spring:message code="SelectIngredients"/></button>

                            <!-- Modal -->
                            <div class="modal fade" id="myModal" role="dialog">
                                <div class="modal-dialog modal-lg">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <h4 class="modal-title"><spring:message code="ingredient"/></h4>
                                        </div>
                                        <div class="modal-body">

                                            <p>
                                                <form:checkboxes path="ingredients" items="${ingredients}"
                                                                 itemLabel="name" itemValue="id" delimiter="<br/>"/>
                                            </p>

                                        </div>
                                        <div class="modal-footer">
                                            <input type="submit" value="<spring:message code="addToCart"/>">
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </form:form>

                        <!-- Fin Tile & Descriptionn & add cart pizza custom-->
                        <!-- Card footer -->
                    </div>
                    <!-- Card content -->
                </div>
                <!-- Card -->
            </div>
            <!-- Grid column -->
            <!---------------------------------------------------------------------------------------------------->
        </div>
        <!-- Grid row -->
    </section>
    <!-- Section: pizza v.2 -->
    <!-- /.container -->
</div>
</body>
</html>

