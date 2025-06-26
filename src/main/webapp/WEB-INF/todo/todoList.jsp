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
<h1>임시 확인용 세션 정보 확인</h1>
<%--<p>EL표기법, 간단히 로그인 정보 : ${sessionScope.loginInfo}</p>--%>
<%--조건부 출력--%>
<c:if  test="${not empty sessionScope.loginInfo}">
<p>로그인 상태 : 로그인이 된 경우</p>
<p>EL표기법, 간단히 로그인 정보 : ${sessionScope.loginInfo}</p>
    </c:if>
<c:if test="${empty sessionScope.loginInfo}">
    <p>로그인 상태 : 로그인 되지 않음</p>
</c:if>

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
