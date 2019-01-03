<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getContextPath()%>/"/>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@include file="../layUI.jsp"%>
</head>
<body>
<form action="doUser?action=userUpdatePwd" method="post">
    ID<input type="text" value="${User.id}" name="id" readonly>
    姓名<input type="text" value="${User.userName}" name="userName" readonly>
    密码<input type="text" value="${User.userPassword}" name="userPassword">
    <input type="submit" value="修改完成" class="layui-btn">
</form>
</body>
</html>
