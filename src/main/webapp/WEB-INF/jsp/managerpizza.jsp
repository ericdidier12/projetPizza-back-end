<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="include/importTags.jsp" %>
<%@include file="include/importLinks.jsp" %>


<!-- Page Content -->
<div class="container">

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
                               action="${pageContext.request.contextPath}/admin/managePizza/send"
                               modelAttribute="Newpizza">
                        <div class="form-row">
                            <table class="table">

                                <div class="form-group col-md-3">
                                    <tr>
                                        <td>
                                            <form:label path="name">Name :</form:label>
                                            <form:input path="name"/>
                                        </td>
                                    </tr>
                                </div>
                                <div class="form-group col-md-3">
                                    <tr>
                                        <td>
                                            <form:label path="price">Price :</form:label>
                                            <form:input path="price"/>
                                        </td>
                                    </tr>
                                </div>

                                <div class="form-group col-md-3">
                                    <tr>

                                        <td>
                                            <form:label path="month_promo">Month_promo :</form:label>
                                            <form:radiobutton path="month_promo" value="true"/>True
                                            <form:radiobutton path="month_promo" value="false"/>False
                                        </td>
                                    </tr>
                                </div>

                                <div class="form-group col-md-3">
                                    <tr>

                                        <td>
                                            <form:label path="fixed">Pizza fixed : </form:label>
                                            <form:radiobutton path="fixed" value="true"/>True
                                            <form:radiobutton path="fixed" value="false"/>False
                                        </td>
                                    </tr>
                                </div>

                                <div class="form-group col-md-3">
                                    <tr>
                                        <td><form:select path="Categorie">
                                            <form:options items="${categories}" itemValue="name"
                                                          itemLabel="name"></form:options>
                                        </form:select>
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
                            <th scope="col">Price</th>
                            <th scope="col">month_promo</th>
                            <th scope="col">fixed</th>
                        </tr>
                        </thead>
<%--
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
                        </tbody>--%>
                    </table>

                </div>
            </div>
            <!-- /.card -->
        </div>
        <!-- /.col-lg-9 -->
    </div>

</div>
<!-- /.container -->
