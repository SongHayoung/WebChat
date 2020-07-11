<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: summerflower
  Date: 11/07/2020
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HOME</title>
</head>
<body>
<table>
    <tbody>
    <c:forEach items="${list}" var="chatroom">
        <tr>
            <td>방 번호 : <c:out value="${chatroom.roomId}"/></td>
            <td>방 이름 : <c:out value="${chatroom.roomName}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
