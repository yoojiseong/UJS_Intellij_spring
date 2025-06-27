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
<%--EL에서 제공하는 기본 객체, param 을 이용해서, 키 : result , 값: error 확인 여부--%>
<c:if test="${param.result == 'error'}">
    <h1>로그인 에러</h1>
</c:if>
<form action="/login" method="post">
    <input type="text" name="mid">
    <input type="password" name="mpw">
    <button type="submit">로그인</button>
</form>
</body>
</html>
