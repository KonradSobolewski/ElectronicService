<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">

    <title>Electronic Service</title>

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/style.css" rel="stylesheet">
    <script src="static/js/bootstrap.min.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">

</head>
<body>

    <nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top">
        <a class="navbar-brand" href="home">Home</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-item nav-link" href="allEquipments">All Equipments</a>
                <a class="nav-item nav-link" href="newEquipment">New Equipments</a>
                <div class="btn-group" style="position: absolute; right: 60px;">
                    <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Options
                    </button>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="settings">User Settings</a>
                        <a class="dropdown-item" href="logout">Logout</a>
                    </div>
                </div>
            </div>
        </div>
    </nav>
    <c:choose>
        <c:when test="${mode == 'MODE_HOME'}">
            <div class="container" id="homeDiv">
                <div class="jumbotron text-center">
                    <h1>Welcome to Electronic Service</h1>
                </div>
            </div>
        </c:when>
        <c:when test="${mode == 'MODE_EQUIPMENTS'}">
            <div class="container text-center" id="equipmentDiv">
                <h2>Equipments</h2>
                <hr>
                <div class="table-responsive">
                    <table class="table table-striped table-hover table-light">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Category</th>
                            <th>Status</th>
                            <th>Description</th>
                            <th>Attributes</th>
                            <%--<th>Last Update</th>--%>
                            <th></th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="equipment" items="${equipments}">
                            <tr>
                                <td>${equipment.id}</td>
                                <td>${equipment.name}</td>
                                <td>${equipment.category.name}</td>
                                <td>${equipment.status}</td>
                                <td>${equipment.description}</td>
                                <td>
                                <c:forEach var="attribute" items="${equipment.attributes}">
                                    ${attribute.name} = ${attribute.value} <br/>
                                </c:forEach>
                                </td>
                                <%--<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${equipment.updatedAt}"/></td>--%>
                                <td>
                                    <a href="updateEquipment?id=${equipment.id}">
                                        <span class="btn btn-info">Modify</span>
                                    </a>
                                </td>
                                <td>
                                    <a href="allAttributes?id=${equipment.id}">
                                        <span class="btn btn-info">Attributes</span>
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteEquipment?id=${equipment.id}">
                                        <span class="btn btn-info">Delete</span>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:when>
        <c:when test="${mode == 'MODE_NEW' || mode == 'MODE_UPDATE'}">
            <div class="container text-center">

                <c:choose>
                <c:when test="${mode == 'MODE_UPDATE'}">
                    <h2>Manage Equipment</h2>
                    <hr>
                  <form class="form-horizontal" method="POST" action="saveEquipment">
                     <input type="hidden" name="id" value="${equipment.id}">
                </c:when>
                <c:when test="${mode == 'MODE_NEW'}">
                      <h2>New Equipment</h2>
                      <hr>
                  <form class="form-horizontal" method="POST" action="createEquipment">
                     <input type="hidden" name="equipment.id" value="${equipment.id}">
                </c:when>
                </c:choose>
                      <div class="form-group  ">
                          <div class="input-group mb-3">
                              <div class="input-group-prepend">
                                  <span class="input-group-text" id="basic-addon1">Name</span>
                              </div>
                            <input type="text" class="form-control" name="name" value="${equipment.name}">
                        </div>
                          <div class="input-group mb-3">
                              <div class="input-group-prepend">
                                  <span class="input-group-text" id="basic-addon5">Category</span>
                              </div>
                              <input type="text" class="form-control" name="category.name" value="${equipment.category.name}">
                          </div>
                          <div class="input-group mb-3">
                              <div class="input-group-prepend">
                                  <span class="input-group-text" id="basic-addon2">Description</span>
                              </div>
                            <input type="text" class="form-control" name="description" value="${equipment.description}">
                        </div>
                          <div class="input-group mb-3">
                              <div class="input-group-prepend">
                                  <span class="input-group-text" id="basic-addon3">Status</span>
                              </div>
                            <select name="status" class="form-control">
                                <option value="EFFICIENT">EFFICIENT</option>
                                <option value="WRONG">WRONG</option>
                            </select>
                        </div>
                        <input type="submit" class="btn btn-lg btn-info btn-block"  value="Save">
                    </div>
                </form>
            </div>
        </c:when>
        <c:when test="${mode == 'MODE_ATTRIBUTE'}">
        <div class="container text-center" id="equipmentDiv">
            <h2>Attributes of Equipment</h2>
            <hr>
            <div class="table-responsive">
                <table class="table table-striped table-hover table-light">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Value</th>
                        <th>Equipment</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="attribute" items="${attributes}">
                        <tr>
                            <td>${attribute.name}</td>
                            <td>${attribute.value}</td>
                            <td>${attribute.equipment.id}</td>
                            <td>
                                <a href="deleteAttribute?id=${attribute.id}&equipment_id=${attribute.equipment.id}">
                                    <span class="btn btn-info">Delete</span>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <form class="form-horizontal inputForm" method="POST" action="createAttribute" align="center">
                <h5>New attribute</h5>
                <input type="hidden" name="attribute.id" value="${attribute.id}">
                <input type="hidden" name="equipment.id" value="<%= request.getParameter("id") %>">
                <div class="form-group ">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="basic-addon10">Nazwa</span>
                        </div>
                        <input type="text" class="form-control" name="name" value="${attribute.name}">
                    </div>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="basic-addon8">Opis</span>
                        </div>
                        <input type="text" class="form-control" name="value" value="${attribute.value}">
                    </div>
                    <input type="submit" class="btn btn-lg btn-info btn-block"  value="Save">
                </div>
            </form>
        </div>
        </c:when>
    </c:choose>
    <footer >
        <div class="center-block footer-block">
            &copy; 2018 copyright to
            <a class="black" target="_blank" rel="noopener noreferrer" href="https://github.com/KonradSobolewski">Sobolewski Konrad</a>
        </div>
    </footer>
</body>
</html>