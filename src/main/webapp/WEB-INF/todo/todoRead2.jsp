<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 25. 6. 26.
  Time: 오전 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>임시 하나 조회 화면 : 상세 화면, 현재 데이터가 별로 없다</h1>
<div>
    <input type="text" name="tno" value="${dto.tno}" readonly>
</div>

<div>
    <input type="text" name="title" value="${dto.title}" readonly>
</div>

<div>
    <input type="date" name="dueDate" value="${dto.dueDate}">
</div>

<div>
    <input type="checkbox" name="finished" ${dto.finished ? "checked":""} readonly>
</div>
<div>
    <a href="/todo/update?tno=${dto.tno}">수정폼 미구현</a>
    <a href="/todo/list2">목록가기</a>
</div>

<form id="form2" action="/todo/delete2" method="post">
    <input type="hidden" name="tno" value="${dto.tno}" readonly>
    <div>
        <button type="submit">삭제</button>
    </div>
</form>
</body>
</html>
