<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/8/008
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/logout" method="post" id="logoutForm">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="退出">
    </form>
</body>
</html>
