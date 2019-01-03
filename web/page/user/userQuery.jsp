<%--
  Created by IntelliJ IDEA.
  User: 52203
  Date: 2018/12/27
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getContextPath()%>/"/>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@include file="../layUI.jsp"%>
</head>
<body>
    <c:choose>
        <c:when test="${not empty User}">
            <table class="layui-table">
                <tr>
                    <th>管理员ID</th>
                    <th>真实姓名</th>
                    <th>密码</th>
                    <th>头像</th>
                    <th>状态</th>
                    <th>身份证号</th>
                    <th>登录名</th>
                </tr>
            <c:forEach items="User" var="user">
                <tr>
                    <td>${User.id}</td>
                    <td>${User.userName}</td>
                    <td>${User.userPassword}</td>
                    <td><img src="${User.userImage}" ></td>
                    <td>${User.userStatus}</td>
                    <td>${User.userId}</td>
                    <td>${User.userLoginName}</td>
                </tr>
            </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <h3>没有管理员信息</h3>
        </c:otherwise>
    </c:choose>
</body>
</html>
