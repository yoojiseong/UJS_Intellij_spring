<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 25. 6. 25.
  Time: 오후 4:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <c:forEach items="${dtoList}" var = "dto">
        <li>
            <form action="/todo/update" method="get">
                <input type="hidden" name="tno" value="${dto.tno}" />
                <input type="hidden" name="title" value="${dto.title}" />

                <button type="submit">수정</button>
            </form>
        </li>
    </c:forEach>
<form action="/todo/update" method="post">
    <div>
        <input type="text" name="title" placeholder="수정할 todo의 제목을 입력해주세요">
    </div>
    <div>
        <input type="date" name="dueDate">
    </div>
    <div>
        <input type="date" name="dueDate">
    </div>
    <button type="reset">리셋</button>
    <button type="submit">등록처리</button>
</form>
</body>
</html>
