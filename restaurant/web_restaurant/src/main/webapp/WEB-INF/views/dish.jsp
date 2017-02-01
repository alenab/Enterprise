<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Dish</title>

    <link rel="stylesheet" type="text/css" href="../css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap-theme.css"/>
    <link rel="stylesheet" type="text/css" href="../css/main.css"/>
</head>
<body>
<div class="container">
    <%@ include file="/WEB-INF/views/title.jspf" %>
    <br>
    <h3 class="h3">Dish details</h3>
    <div class="row">
        <div class="col-sm-4 col-sm-offset-4">
            <table class="table">
                <tr>
                    <th>Dish</th>
                    <th>Weight</th>
                    <th>Price</th>
                    <th>Ingredients</th>
                </tr>
                <tr>
                    <td>${dish.get(0).name}</td>
                    <td>${dish.get(0).weight}</td>
                    <td>${dish.get(0).price}</td>
                    <td>
                        <c:forEach var="ingredient" items="${dish.get(0).ingredients}">
                            ${ingredient.name}
                            <br>
                        </c:forEach>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>
