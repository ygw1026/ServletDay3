<%--
  Created by IntelliJ IDEA.
  User: nhnacademy
  Date: 25. 1. 20.
  Time: 오전 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>student - list</title>
</head>
<body>
<h1>학생 리스트</h1>
<p><a href="/student/register" >학생(등록)</a></p>
<p><a href="/student/update">학생(수정)</a></p>
<p><a href="/student/view" >학생(조회)</a></p>
<table>
  <thead>
  <tr>
    <th>아이디</th>
    <th>이름</th>
    <th>성별</th>
    <th>나이</th>
    <th>생성일</th>
  </tr>
  </thead>
  <tbody>
  <%--@elvariable id="studentList" type="java.util.List"--%>
  <c:forEach var="student" items="${studentList}">
    <tr>
      <td style="text-align: center">${student.id}</td>
      <td style="text-align: center">${student.name}</td>
      <td style="text-align: center">${student.gender}</td>
      <td style="text-align: center">${student.age}</td>
      <td style="text-align: center">
        <fmt:formatDate value="${student.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" />
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>
