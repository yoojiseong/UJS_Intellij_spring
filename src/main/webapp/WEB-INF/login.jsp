<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 25. 6. 26.
  Time: 오후 2:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>임시 로그인 화면</title>
</head>
<body>
<h1>임시 로그인 화면</h1>
<form action="/login" method="post">
    <input type="text" name="mid">
    <input type="password" name="mpw">
    <button type="submit"></button>
</form>
</body>
</html>
