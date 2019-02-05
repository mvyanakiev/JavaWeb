<%@ page import="metube.domain.models.view.TubeDetailsViewModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<% int statusCode = response.getStatus(); %>
<main>

    <div class="row">
        <div class="col col-md-12 d-flex justify-content-center">
            <h1>
                Error <%=statusCode %>
            </h1>
        </div>
    </div>
    <hr/>

    <div class="row">
        <div class="col col-md-12 d-flex justify-content-center">
            <a href="/">Back to home</a>
        </div>
    </div>

    <footer>
        <c:import url="templates/footer.jsp"/>
    </footer>
</main>

</body>
</html>
