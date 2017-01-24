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
        <th>Phone Number</th>
        <th>Salary</th>
    </tr>
    <tr>
        <td>${employee.name}</td>
        <td>${employee.surname}</td>
        <td>${employee.position}</td>
        <td>${employee.phoneNumber}</td>
        <td>${employee.salary}</td>
    </tr>
</table>
</body>
</html>
