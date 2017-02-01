<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Enjoy</title>

    <link rel="stylesheet" type="text/css" href="../css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap-theme.css"/>
    <link rel="stylesheet" type="text/css" href="../css/main.css"/>

    <script lang="text/javascript">
        function goto(url) {
            window.open(url, "_self");
        }
    </script>
</head>
<body>
<div class="container">
    <%@ include file="/WEB-INF/views/title.jspf" %>

    <h3 class="h3">MENU: ${menuName}</h3>

    <br>
    <div class="row">
        <div class="col-sm-8 col-sm-offset-2">
            <table class="table table-hover">
                <colgroup>
                    <col class="col-sm-10"/>
                    <col class="col-sm-1"/>
                    <col class="col-sm-1"/>
                </colgroup>
                <tr class="find">
                    <div class="form-inline" align="right">
                        <form method="post" action="/research" accept-charset="UTF-8">
                            <input class="form-control" type="search" name="dishName" placeholder="Введите название блюда">
                            <input type="submit" class="btn btn-default" value="Найти">
                        </form>
                    </div>
                </tr>
                <tr>
                    <th>Dish</th>
                    <th>Weight</th>
                    <th>Price</th>
                </tr>
                <c:forEach var="dish" items="${dishes}">
                    <tr onclick="goto('/dish?dishName=${dish.name}')">
                        <td>${dish.name}</td>
                        <td>${dish.weight}</td>
                        <td>${dish.price}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

</div>
</body>
</html>
