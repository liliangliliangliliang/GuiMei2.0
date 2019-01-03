<%--
  Created by IntelliJ IDEA.
  User: 52203
  Date: 2019/1/2
  Time: 16:24
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
<form action="doCus?action=froGetPwd" method="post">
    <label>输入新密码</label>
    ID<input type="text" value="${Cus.id}" name="id" readonly>
    姓名<input type="text" value="${Cus.cusName}" name="cusName" readonly>
    密码<input type="text" value="${Cus.cusPassword}" name="cusPassword">
    <input type="submit" value="提交" class="layui-btn">
</form>
</body>
</html>
