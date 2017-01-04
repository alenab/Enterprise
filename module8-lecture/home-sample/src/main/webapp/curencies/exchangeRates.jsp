<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Exchange Rates</title>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.css"/>
</head>
<body>
<h1 align="center">Exchange Rates</h1>
<div class="container">
    <div class="form-block">
        <div class="left-block">
            <span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
                <span class="text-muted"> Дата обновления: ${applicationScope.timeStamp}</span>
                <br/>
                <br/>
            <c:if test="${requestScope.organization != null}">
                <div class="alert alert-info" role="alert">
                    Текущий банк: <span id="bank">${requestScope.organization.title}</span>
                </div>
            </c:if>
        </div>
        <form method="get" action="/">
            <div class="input-group">
                <select class="form-control" name="bank">
                    <c:forEach var="bank" items="${applicationScope.list}" varStatus="status">
                        <option value="${status.index}">${bank.title}</option>
                    </c:forEach>
                </select>
                <span class="input-group-btn"/>
                <input class="btn btn-default" type="submit" value="Выбрать"/>
            </div>
        </form>
    </div>
    <br/>
    <br/>
    <c:if test="${requestScope.organization != null}">
        <div class="container">
            <table class="table">
                <tr>
                    <th>Код валюты</th>
                    <th>Наименование валюты</th>
                    <th>Курс продажи</th>
                    <th>Курс покупки</th>

                </tr>
                <c:forEach var="cur" items="${requestScope.organization.currencies}">
                    <tr>
                        <td>${cur.shortName}</td>
                        <td>${cur.name}</td>
                        <td>${cur.valueAsk}</td>
                        <td>${cur.valueBid}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>
</div>
</body>
</html>