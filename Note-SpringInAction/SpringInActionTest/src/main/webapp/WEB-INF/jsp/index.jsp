<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<security:authorize ifAllGranted="ADMIN,USER">
    <security:authentication property="principal.username" var="loginUserName" scope="session"/>
    <br><br>
    你好啊： <security:authentication property="principal.username"/>
    <br><br>
    <security:authentication property="principal"/>
    <br><br>
    <security:authentication property="details"/>
    <br><br>
    <security:authentication property="credentials"/>
    <br><br>
    <security:authentication property="authorities"/>
    <br>
</security:authorize>

</body>
</html>
