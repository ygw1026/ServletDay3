<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: nhnacademy
  Date: 25. 1. 20.
  Time: 오후 4:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student - view</title>
</head>
<body>
<h1>학생 조회</h1>
<form method="get" action="/student/view">
  <label for="id">아이디:</label>
  <input type="text" id="id" name="id" required /><br/>
  <button type="submit">검색</button>
</form>

<form method="get" action="/student/view">
  <label for="name">이름:</label>
  <input type="text" id="name" name="name" required /><br/>
  <button type="submit">검색</button>
</form>

<%-- 검색 결과가 없다면 메시지 출력 --%>
<c:if test="${not empty message}">
  <p>${message}</p>
</c:if>

<%-- 학생 정보가 있다면 출력 --%>
<form method="post"
<c:if test="${not empty student}">
  <p><strong>아이디:</strong> ${student.id}</p>
  <p><strong>이름:</strong> ${student.name}</p>
  <p><strong>성별:</strong> ${student.gender}</p>
  <p><strong>나이:</strong> ${student.age}</p>
  <p><strong>생성일:</strong> <fmt:formatDate value="${student.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
  <li>
    <!-- todo /update -> /update.do  -->
    <c:url var="update_link" value="/student/update.jsp" >
      <c:param name="id" value="${student.id}" />
    </c:url>
    <a href="${update_link}">수정</a>
  </li>
</c:if>

<ul>
  <li><a href="/student/list">리스트로 돌아가기</a></li>
</ul>
</body>
</html>
