<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value=""/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="Konrad Sobolewski">

    <title>Register here</title>
    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/style.css" rel="stylesheet">
    <script src="static/js/bootstrap.min.js"></script>
</head>

<body>
<div class="container ">
    <div class="text-center">
        <img src="static/login_image.png" class="rounded resize" alt="...">
    </div>
    <form:form  method="POST" action="register" class="form-register" modelAttribute="user">
        <h2 class="form-heading">Electronic Service</h2>
        <h4 class="form-heading">Registration</h4>
        <div class="form-group  ">
            <form:input type="hidden" path="id"/>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Username</span>
                </div>
                <form:input type="text" class="form-control" placeholder="Username" path="username"/>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon2"> Password </span>
                </div>
                <form:input type="password" class="form-control" placeholder="Password" path="password"/>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon4"> Password </span>
            </div>
                <form:input type="password" class="form-control" placeholder="Confirm Password" path="confirmPassword"/>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon3">Email</span>
                </div>
                <form:input  th:field="*{email}"  type="email" class="form-control" placeholder="Email" path="email"/>
            </div>
            <button class="btn btn-lg btn-info btn-block" type="submit">Register</button>
            <c:if test="${error == 'Invalid'}">
                <div class="alert alert-danger">
                    <p>Invalid data.</p>
                </div>
            </c:if>
            <c:if test="${error == 'Pass'}">
                <div class="alert alert-danger">
                    <p>Passwords doesn't match.</p>
                </div>
            </c:if>
            <c:if test="${error == 'Exists'}">
                <div class="alert alert-danger">
                    <p>Username or email already exists.</p>
                </div>
            </c:if>
        </div>
    </form:form>
</div>
<footer >
    <div class="center-block footer-block">
        &copy; 2018 copyright to
        <a class="black" target="_blank" rel="noopener noreferrer" href="https://github.com/KonradSobolewski">Sobolewski Konrad</a>
    </div>
</footer>

</body>
</html>
