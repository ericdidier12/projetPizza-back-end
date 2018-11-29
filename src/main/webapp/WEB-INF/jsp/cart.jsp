<%@ taglib prefix="button" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="include/importTags.jsp"%>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap2-css">

<body>
<div class="container">
    <div class="row">
        <br>
        <div class="col-md-12">
            <div class="col-md-4 col-sm-6 col-xs-12 col-md-push-6 col-sm-push-6">
                <!--REVIEW ORDER-->
                <div class="panel panel-default">
                    <div class="panel-heading text-center">
                        <h4><spring:message code="reviewOrder"/></h4>
                    </div>
                    <div class="panel-body">
                        <div class="col-md-12">
                            <strong><spring:message code="subtotal"/></strong>
                            <div class="pull-right"><div>${SubTotal} €</div></div>
                        </div>
                           <div class="col-md-12">
                            <small><spring:message code="shipping"/></small>
                            <div class="pull-right"><div>${Shipping} €</div></div>
                            <hr>
                        </div>
                        <div class="col-md-12">
                            <strong><spring:message code="TotalPrice"/></strong>
                            <div class="pull-right"><div>${Total} €</div></div>
                            <hr>
                        </div>
                      <form:form  method="POST"
                                    action="/cart/valider"
                                    modelAttribute="pizzaEdit">
                            <form:button class="btn btn-primary btn-lg btn-block"><i><spring:message code="checkout"/></i></form:button>
                        </form:form>
                       <%-- <button type="button" class="btn btn-primary btn-lg btn-block"><spring:message code="checkout"/></button>--%>
                        <div id="paypal-button-container"></div>
                    </div>

                </div>
                <!--REVIEW ORDER END-->
            </div>
            <div class="col-md-8 col-sm-6 col-xs-12 col-md-pull-6 col-sm-pull-6">
                <!--SHIPPING METHOD-->
                <div class="panel panel-default">
                    <div class="panel-heading text-center"><h4><spring:message code="currentCart"/></h4></div>
                    <div class="panel-body">
                        <table class="table borderless">
                            <thead>
                            <tr>
                                <td></td>
                                <td><spring:message code="namePizza"/></td>
                                <td><spring:message code="pricePizza"/></td>
                                <td><spring:message code="numberOfPizza"/></td>
                                <td></td>
                                <td></td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="pizza" items="${ContentCart}">
                            <tr>
                                <td class="col-md-3">
                                    <div class="media">
                                        <a class="thumbnail pull-left">
                                            <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/a3/Eq_it-na_pizza-margherita_sep2005_sml.jpg/280px-Eq_it-na_pizza-margherita_sep2005_sml.jpg"
                                                                                      class="card-img-top"
                                                                                      alt="sample photo">
                                        </a>
                                    </div>
                                </td>
                                <td class="col-md-3">
                                    <div class="media-body">
                                        <h5 class="media-heading"><c:out value="${pizza.name}" /></h5>
                                    </div>
                                </td>
                                <td class="text-center"><c:out value="${pizza.price}" /> €</td>
                                <td class="text-center"><c:out value="${pizza.number}" /></td>
                                <td class="text-right">
                                <form:form  method="POST"
                                            action="/cart/sendAdd"
                                            modelAttribute="pizzaEdit">
                                    <form:hidden path="id" value="${pizza.id}"/>
                                    <form:button><i>+</i></form:button>
                                </form:form>

                                   <%-- <button type="button" class="btn btn-danger">-</button>--%>
                                    <form:form  method="POST"
                                                action="/cart/sendSubstract"
                                                modelAttribute="pizzaEdit">
                                        <form:hidden path="id" value="${pizza.id}"/>
                                        <form:button><i>-</i></form:button>
                                    </form:form>
                                </td>
                                <td class="text-right">
                                    <form:form  method="POST"
                                                action="/cart/sendDelete"
                                                modelAttribute="pizzaEdit">
                                        <form:hidden path="id" value="${pizza.id}"/>
                                        <form:button><i><spring:message code="buttonRemove"/></i></form:button>
                                    </form:form>
                                </td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <!--SHIPPING METHOD END-->
            </div>
        </div>
    </div>
</div>

</body>

