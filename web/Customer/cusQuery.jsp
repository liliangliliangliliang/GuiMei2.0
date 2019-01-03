<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getContextPath()%>/"/>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@include file="layUI.jsp"%>
</head>
<body>
            <table width="100%" style="text-align: center" class="layui-table">
                <tr>
                    <th>ID</th>
                    <th>姓名</th>
                    <th>登录账号</th>
                    <th>Email</th>
                    <th>性别</th>
                    <th>头像</th>
                    <th>爱好</th>
                    <th>证件号</th>
                    <th>生日</th>
                </tr>
                <tr>
                    <td>${Cus.id}</td>
                    <td>${Cus.cusName}</td>
                    <td>${Cus.cusLoginName}</td>
                    <td>${Cus.cusEmail}</td>
                    <td>${Cus.cusSex}</td>
                    <td><img src="CusImage/${Cus.cusPhoto}" ></td>
                    <td>${Cus.cusHobby}</td>
                    <td>${Cus.cusCode}</td>
                    <td>${Cus.cusBirthday}</td>
                </tr>
            </table>


</body>
</html>
