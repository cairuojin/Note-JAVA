<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>hello</h1>

<form method="post" enctype="multipart/form-data" action="/test/upload">
    <input type="file" name="multipartFile">
    <input type="submit">
</form>
<h1>${Spittle.message}</h1>
<h1>${Spittle.latitude}</h1>
<h1>${Spittle.longitude}</h1>
<h1>${Spittle.time}</h1>


</body>
</html>
