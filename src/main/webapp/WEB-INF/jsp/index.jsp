<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <title>Todo App</title>
</head>
<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
        crossorigin="anonymous"></script>

<script>
    <jsp:include page='items.js'/>
</script>

<div class="container mt-3 mr-3">
    <div class='d-flex justify-content-end'>
        <ul class="nav">
            <a class="nav-link" href="${pageContext.request.contextPath}/login.do">
                <c:choose>
                    <c:when test="${user == null}">
                        Login
                    </c:when>
                    <c:otherwise>
                        Logout &nbsp;
                        <c:out value="${user.name}"/>
                    </c:otherwise>
                </c:choose>
            </a>
            <li class="nav-item">
                <a class="nav-link active" href="reg.do">Signup</a>
            </li>
        </ul>
    </div>
    <p class="h3">Todo App</p>
    <div class="spinner-border" role="status" id="list-spinner">
    </div>

    <div class="row mb-3">
        <div class="col-sm-6">
            <label for="categories">Select category for new task</label>
            <select class="form-control" id="categories" multiple>
            </select>
        </div>
    </div>

    <div class="row mb-3">
        <div class="col">
            <div class="input-group">
                <input name="description" id="item-form" type="text" style="width: 100%" class="form-control"
                       placeholder="Input new task" aria-label="">
            </div>
        </div>
        <div class="col" id="input-spinner-div">
            <div class="spinner-border" role="status">
            </div>
        </div>
        <div class="col d-flex justify-content-end">
            <div class="form-check form-switch">
                <input class="form-check-input" type="checkbox" id="toggleShowDone" value="true" checked>
                <label class="form-check-label" for="toggleShowDone">show done</label>
            </div>
        </div>
    </div>
    <div id="items">
        <ul class="list-group">
        </ul>
    </div>
</div>
</body>
</html>