<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="include/importTags.jsp" %>
<%@include file="include/importLinks.jsp" %>


<!-- Page Content -->
<div class="container">
<%--
    <div class="row">

        <div class="col-lg-3">
            <h1 class="my-4">Manager Admin </h1>
            <div class="list-group">
                <a href="#" class="list-group-item">manage command</a>
                <a href="${pageContext.request.contextPath}/admin/manage-stock" class="list-group-item">manage stock</a>
                <a href="${pageContext.request.contextPath}/admin" class="list-group-item">Manage Pizza</a>
            </div>
        </div>
        <!-- /.col-lg-3 -->

        <div class="col-lg-9">

            <div class="card mt-4">
                <div class="card-header">
                    Add Pizza
                </div>
                <div class="card-body">
                    <form:form id="form" method="POST"
                               action="${pageContext.request.contextPath}/adminmanage-stock/send"
                               modelAttribute="NewIngredient">
                        <div class="form-row">
                            <table class="table">

                                <div class="form-group col-md-3">
                                    <tr>
                                        <td>
                                            <form:label path="name"> Name :</form:label>
                                            <form:input path="name"/>
                                        </td>
                                    </tr>
                                </div>
                                <div class="form-group col-md-3">
                                    <tr>
                                        <td>
                                            <form:label path="recipe_qunatity">Recipe_qunatity :</form:label>
                                            <form:input path="recipe_qunatityprice"/>
                                        </td>
                                    </tr>
                                </div>

                                <div class="form-group col-md-3">
                                <tr>
                                    <td>
                                        <form:label path="stock_quantity">Stock_quantity :</form:label>
                                        <form:input path="stock_quantity"/>
                                    </td>
                                </tr>
                                </div>


                                <div class="form-group col-md-3">
                                    <tr>
                                        <td>
                                            <form:label path="unit_price">Unit_price :</form:label>
                                            <form:input path="unit_price"/>
                                        </td>
                                    </tr>
                                </div>



                                <div class="form-group col-md-3">
                                    <tr>
                                        <td><input type="submit" value="send"/></td>
                                    </tr>
                                </div>
                            </table>
                        </div>
                    </form:form>
                </div>
            </div>
            <!-- /.card -->

            <div class="card card-outline-secondary my-3">
                <div class="card-header">
                    List de Pizza
                </div>

                <div class="card-body">
                    <table class="table table-striped">
                        <thead>

                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">name</th>
                            <th scope="col">recipe_qunatity</th>
                            <th scope="col">stock_quantity</th>
                            <th scope="col">unit_price</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="pizza" items="${pizzas}">
                        <tr>
                            <th scope="row"><c:out value="${pizza.id}"></c:out></th>
                            <td><c:out value="${pizza.name}"></c:out></td>
                            <td><c:out value="${pizza.month_promo}"></c:out></td>
                            <td><c:out value="${pizza.Price}"></c:out></td>
                            <td><c:out value="${pizza.Price}"></c:out></td>
                            </c:forEach>
                        </tr>
                        </tbody>
                    </table>

                </div>
            </div>
            <!-- /.card -->
        </div>
        <!-- /.col-lg-9 -->
    </div>--%>

    Encours de developpement.....

</div>
<!-- /.container -->

