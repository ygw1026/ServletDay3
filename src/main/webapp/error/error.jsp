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
<form method="post" action="error/error">
    <table>
        <tbody>
        <tr>
            <th>status_code</th>
            <td>404</td>
        </tr>
        <tr>
            <th>exception_type</th>
            <td>java.lang.Exception</td>
        </tr>
        <tr>
            <th>message</th>
            <td>Resource not found</td>
        </tr>
        <tr>
            <th>exception</th>
            <td>NullPointerException</td>
        </tr>
        <tr>
            <th>request_uri</th>
            <td>/non-existent-page</td>
        </tr>
        </tbody>
    </table>
</form>

</body>
</html>
