<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Waiters</title>

    <link rel="stylesheet" type="text/css" href="../css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap-theme.css"/>
    <link rel="stylesheet" type="text/css" href="../css/main.css"/>
</head>
<body>
<div class="container">
    <%@ include file="/WEB-INF/views/title.jspf" %>
    <h3 class="h3">Our waiters</h3>

    <div class="text-center">
        <table>
            <tr>
                <c:forEach var="waiter" items="${waiters}">
                    <td><h4>${waiter.name} </h4></td>
                </c:forEach>

            </tr>
            <tr>
                <c:forEach var="waiter" items="${waiters}">
                    <td><img src="/images/photo.jpg" height="40%" width="40%"></td>
                </c:forEach>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
