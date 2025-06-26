<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 25. 6. 23.
  Time: 오전 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--JSTL 설정 적용하기, 메타 태그 붙여넣기--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Todo List 임시 화면</h1>
<span><a href="/todo/register2">글쓰기</a></span>
    <c:forEach items="${dtoList}" var = "dto">
<%--        <li>${dto}</li>--%>
        <li>
            <span><a href="/todo/read2?tno=${dto.tno}">${dto.tno}</a></span>
            <span>${dto.title}</span>
            <span>${dto.dueDate}</span>
            <span>${dto.finished ? "완료" : "미완료"}</span>
        </li>
    </c:forEach>

</body>
</html>
