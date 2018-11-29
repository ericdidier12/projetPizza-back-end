
<%@ include file="../jsp/include/importTags.jsp" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/home">Logo</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">

                <li class="nav-item">
                    <sec:authorize access="isAuthenticated()">
                        <span style="color: cadetblue"> <spring:message code="welcomemister"/> <sec:authentication property="principal.username"/> !</span>
                    </sec:authorize>
                </li>



                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/home">Home
                        <span class="sr-only">(current)</span>
                    </a>
                </li>



                <li class="nav-item">
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <a class="nav-link" href="${pageContext.request.contextPath}/admin">Managers</a>
                    </sec:authorize>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/pizza">Pizza</a>
                </li>


                <li class="nav-item">
                    <span class="fi-cart"></span>
                    <a class="nav-link" href="${pageContext.request.contextPath}/cart"><spring:message code="buttoncart"/></a>
                </li>

                <spring:url var="localeFr" value="">
                    <spring:param name="locale" value="fr"/>
                </spring:url>

                <spring:url var="localeEn" value="">
                    <spring:param name="locale" value="en"/>
                </spring:url>

                <li class="nav-item">
                    <button type="button" class="btn btn-dark"><a href="${localeFr}">FR</a></button>

                </li>
                <li class="nav-item">
                    <button type="button" class="btn btn-dark"><a href="${localeEn}">EN</a></button>
                </li>

                <li class="nav-item">
                    <sec:authorize access="!isAuthenticated()">
                        <a href="${pageContext.request.contextPath}/login">
                        <button type="button" class="btn"> Login
                        </button>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <a href="${pageContext.request.contextPath}/logout">
                        <button type="button" class="btn"> Logout
                        </button>
                    </sec:authorize>

                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
