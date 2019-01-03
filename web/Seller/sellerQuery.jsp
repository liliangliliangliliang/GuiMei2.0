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
                    <th>性别</th>
                    <th>生日</th>
                    <th>证件号</th>
                    <th>Email</th>
                    <th>电话</th>
                    <th>地址</th>
                </tr>
                <tr>
                    <td>${Sel.id}</td>
                    <td>${Sel.sellerName}</td>
                    <td>${Sel.sellerUser}</td>
                    <td>${Sel.sellerSex}</td>
                    <td>${Sel.sellerBirthday}</td>
                    <td>${Sel.sellerIdCard}</td>
                    <td>${Sel.sellerEmail}</td>
                    <td>${Sel.sellerTel}</td>
                    <td>${Sel.sellerAddress}</td>
            </table>

</body>
</html>
