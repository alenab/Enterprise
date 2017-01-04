<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>List of Items</title>

    <link rel="stylesheet" type="text/css" href="../css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap-theme.css"/>
</head>
<body>
<h1 align="center">TODO list</h1>
<hr>

<div class="container">
    <form accept-charset="UTF-8" method="POST" action="update">
        <c:forEach var="item" items="${sessionScope.itemList}">
            <c:choose>
                <c:when test="${item.isDone}">
                    <div class="input-group">
                        <span class="input-group-addon">
                            <input class="checkbox" name="${item.id}" type="checkbox" checked="checked"/>
                        </span>
                        <input class="form-control" name="txt_${item.id}" type="text" value="${item.text}"/>
                        <span class="input-group-btn">
                            <a href="/delete?id=${item.id}">
                                <button class="btn btn-danger" type="button">
                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Удалить
                                </button>
                            </a>
                        </span>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="input-group">
                     <span class="input-group-addon">
                    <input class="checkbox" name="${item.id}" type="checkbox"/>
                     </span>
                        <input class="form-control" name="txt_${item.id}" type="text" onchange="alert(${item.text})"
                               value="${item.text}"/><br/>
                    </div>
                </c:otherwise>
            </c:choose>
        <br/>
        </c:forEach>
        <br/>
        <c:if test="${itemList != null && !itemList.isEmpty()}">
            <input type="submit" class="btn btn-default" value="Save checked">
        </c:if>
        <a href="list/add.html" class="btn btn-info">Add</a>
    </form>
</div>
</body>
</html>
