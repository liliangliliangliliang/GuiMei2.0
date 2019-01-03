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
        <c:when test="${not empty Page.pageData}">
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
                    <th colspan="2">操作</th>
                </tr>
            <c:forEach items="${Page.pageData}" var="sel">
                <tr>
                    <td>${sel.id}</td>
                    <td>${sel.sellerName}</td>
                    <td>${sel.sellerUser}</td>
                    <td>${sel.sellerSex}</td>
                    <td>${sel.sellerBirthday}</td>
                    <td>${sel.sellerIdCard}</td>
                    <td>${sel.sellerEmail}</td>
                    <td>${sel.sellerTel}</td>
                    <td>${sel.sellerAddress}</td>
                    <td><a class="layui-btn" href="doSel?action=selQueryById&id=${sel.id}">修改</a></td>
                    <td><a class="layui-btn" href="doSel?action=selDelById&id=${sel.id}">删除</a></td>
                </tr>
            </c:forEach>
                <tr>
                    <td colspan="10">
                        <%@include file="../Page.jsp"%>
                    </td>
                </tr>
            </table>
        </c:when>
        <c:otherwise>
            <h3>没有商家</h3>
        </c:otherwise>
    </c:choose>


</body>
</html>
