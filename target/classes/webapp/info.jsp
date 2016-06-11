<%--
  Created by IntelliJ IDEA.
  User: FromxSoul
  Date: 10.06.2016
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
  <c:out value="${customer.firstName}"/><br> </br>
  <c:out value="${customer.lastName}"/><br> </br>
  <c:out value="${customer.loginEmail}"/><br> </br>
  <a href="ForA?action=logout">Logout</a>
</body>
</html>
