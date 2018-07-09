<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value=""/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="Konrad Sobolewski">

    <title>LogIn</title>
    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/style.css" rel="stylesheet">
    <script src="static/js/bootstrap.min.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
</head>

<body>
    <div class="container ">
        <div class="text-center">
            <img src="static/login_image.png" class="rounded resize" alt="...">
        </div>
        <form method="POST" action="/login" class="form-signin">
            <h2 class="form-heading">Electronic Service</h2>
            <div class="form-group  ">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1">Username</span>
                    </div>
                    <input name="username" type="text" class="form-control" placeholder="Username"
                       autofocus="true"/>
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon2"> Password </span>
                    </div>
                    <input name="password" type="password" class="form-control" placeholder="Password"/>
                </div>
                <button class="btn btn-lg btn-info btn-block" type="submit">Log In</button>
                <div class="row-fluid">
                    <div class="span12 text-center">
                        <a class="black text-center" href="${"/register"}">Register here</a>
                    </div>
                </div>

                <c:if test="${param.error != null}">
                    <div class="alert alert-danger">
                        <p>Invalid username or password.</p>
                    </div>
                </c:if>
                <c:if test="${param.logout != null}">
                    <div class="alert alert-success">
                        <p>You have been logged out successfully.</p>
                    </div>
                </c:if>
                <c:if test="${param.register != null}">
                    <div class="alert alert-success">
                        <p>You have been registered successfully.</p>
                    </div>
                </c:if>
            </div>
        </form>
    </div>
<footer >
    <div class="center-block footer-block">
        &copy; 2018 copyright to
        <a class="black" target="_blank" rel="noopener noreferrer" href="https://github.com/KonradSobolewski">Sobolewski Konrad</a>
    </div>
</footer>

</body>
</html>
