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
    <%@include file="page/layUI.jsp"%>
</head>

<body>
    <form action="doCus?action=cusAdd" method="post" enctype="multipart/form-data"><br><br><br><br>
        姓名<input type="text"  name="cusName" ><br><br>
        登录账号<input type="text"  name="cusLoginName"><br><br>
        登录密码<input type="text"  name="cusPassword"><br><br>
        Email<input type="text"  name="cusEmail"><br><br>
        性别<input type="radio" value="男" name="cusSex" checked>男
        <input type="radio" value="女" name="cusSex" >女<br><br>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;头像<input type="file"  name="cusPhoto"><br><br>
        爱好<input type="text"  name="cusHobby"><br><br>
        证件号<input type="text" name="cusCode"><br><br>
        生日<input type="date"  name="cusBirthday"><br><br>
        <input type="submit" value="注册" class="layui-btn"><br><br>
    </form>
</body>
</html>
