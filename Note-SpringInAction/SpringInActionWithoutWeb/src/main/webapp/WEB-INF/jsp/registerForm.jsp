<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <sf:form method="post" commandName="spitter">
        <sf:errors path="*" element="div" ></sf:errors>
        <sf:input path="message"></sf:input><br>
        <sf:input path="time"></sf:input><br>
        <sf:input path="latitude"></sf:input><br>
        <sf:input path="longitude"></sf:input><br>
        <input type="submit" value="register">
    </sf:form>
</body>
</html>
