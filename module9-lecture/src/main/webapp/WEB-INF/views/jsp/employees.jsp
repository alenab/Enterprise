<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table style="align-items: center">
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Position</th>
    </tr>
    <c:forEach var="employee" items="${employees}">
        <tr>
            <td><a href="/employee?employeeName=${employee.name}">${employee.name}</a></td>
            <td>${employee.surname}</td>
            <td>${employee.position}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
