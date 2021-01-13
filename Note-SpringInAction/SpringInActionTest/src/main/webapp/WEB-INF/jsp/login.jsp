<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>myLoginJsp</title>
</head>
<body>
    <form id="loginForm" action="/login" method="post">
        用户名：<input type="text" name="username">
        密码：<input type="password" name="password">
        用户名：<input type="submit" value="登录">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        记住我：<input type="checkbox" name="remember-me"/>
    </form>
</body>
</html>
