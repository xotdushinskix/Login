<%--
  Created by IntelliJ IDEA.
  User: FromxSoul
  Date: 11.06.2016
  Time: 0:10
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
  Hi, <c:out value="${firstName}"/> you are successfully registered! Please, <a href="http://localhost:8081/login.jsp">login</a>.
</body>
</html>
