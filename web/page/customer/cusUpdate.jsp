<%--
  Created by IntelliJ IDEA.
  User: 52203
  Date: 2018/12/27
  Time: 11:17
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
    <form action="doCus?action=cusUpdate" method="post">
        ID<input type="text" value="${Cus.id}" name="id" readonly>
        姓名<input type="text" value="${Cus.cusName}" name="cusName" readonly>
        登录账号<input type="text" value="${Cus.cusLoginName}" name="cusLoginName">
        Email<input type="text" value="${Cus.cusEmail}" name="cusEmail"><br>
        性别<input type="radio" value="男" name="cusSex" ${Cus.cusSex=='男'?'checked':''}>男
        <input type="radio" value="女" name="cusSex" ${Cus.cusSex=='女'?'checked':''}>女 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        爱好<input type="text" value="${Cus.cusHobby}" name="cusHobby">
        证件号<input type="text" value="${Cus.cusCode}" name="cusCode">
        生日<input type="date" value="${Cus.cusBirthday}" name="cusBirthday"><br>
        <input type="submit" value="修改完成" class="layui-btn">
    </form>
</body>
</html>
