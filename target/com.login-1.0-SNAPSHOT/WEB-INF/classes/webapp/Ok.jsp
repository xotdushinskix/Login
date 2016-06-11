<%--
  Created by IntelliJ IDEA.
  User: FromxSoul
  Date: 09.06.2016
  Time: 0:32
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
  Ok
  <c:out value="${customer.firstName}"/><br> </br>

  <a href="ForA?action=info">Info</a><br> </br>
  <a href="ForA?action=logout">Logout</a>
</body>
</html>
