<%--
  Created by IntelliJ IDEA.
  User: nhnacademy
  Date: 25. 1. 21.
  Time: 오후 5:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Student - Update</title>
</head>
<body>

<form method="post" action = "/student/delete.do">
  <label for="id">아이디:</label>
  <input type="text" id="id" name="id" required/><br/>

  <label for="name">이름:</label>
  <input type="text" id="name" name="name" required/><br />

  <button type="submit">삭제</button>
</form>

<ul>
  <li><a href="/student/list.do">리스트로 돌아가기</a></li>
</ul>

</body>
</html>