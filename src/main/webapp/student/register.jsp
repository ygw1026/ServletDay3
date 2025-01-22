<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nhnacademy
  Date: 25. 1. 20.
  Time: 오후 4:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student - Register</title>
</head>
<body>

<form method="post" action = "/student/register.do">
  <label for="id">아이디:</label>
  <input type="text" id="id" name="id" required /><br />

  <label for="name">이름:</label>
  <input type="text" id="name" name="name" required /><br />

  <label for="gender">성별:</label>
  <select id="gender" name="gender" required>
    <option value="Male">남성</option>
    <option value="Female">여성</option>
  </select><br />

  <label for="age">나이:</label>
  <input type="number" id="age" name="age" required /><br />

  <button type="submit">등록</button>
</form>

<form method="post" action ="/student/update.do">
  <button type="submit">수정</button>
</form>
</body>
</html>
