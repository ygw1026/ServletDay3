<jsp:useBean id="status_code" scope="application" type=""/>
<%--
  Created by IntelliJ IDEA.
  User: nhnacademy
  Date: 25. 1. 21.
  Time: 오후 5:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>
</head>
<body>
<table>
    <tbody>
    <tr>
        <th>status_code</th>
        <td>${status_code}</td>
    </tr>
    <tr>
        <th>exception_type</th>
        <td><!-- todo exception_type 출력 --></td>
    </tr>
    <tr>
        <th>message</th>
        <td><!-- todo message 출력 --></td>
    </tr>
    <tr>
        <th>exception</th>
        <td><!-- todo exception 출력 --></td>
    </tr>
    <tr>
        <th>request_uri</th>
        <td><!-- todo request_uri 출력 --></td>
    </tr>
    </tbody>
</body>
</html>
