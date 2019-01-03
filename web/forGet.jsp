<%--
  Created by IntelliJ IDEA.
  User: 52203
  Date: 2019/1/2
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getContextPath()%>/"/>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@include file="layUI.jsp"%>
</head>
<body>
    <form action="doCus?action=froGetName" method="post">
        <div>密码找回中</div>
        <label>你的真实姓名是</label>
        <input type="text" name="cusName">
        <label>你的身份证号是</label>
        <input type="text" name="cusCode">
        <input type="submit" value="提交" class="layui-btn">
    </form>
</body>
</html>
