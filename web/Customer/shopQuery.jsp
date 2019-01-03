<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getContextPath()%>/"/>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script src="static/js/jquery-2.1.1.min.js"></script>
    <%@include file="layUI.jsp"%>
</head>
<body>
<c:choose>
    <c:when test="${not empty Page.pageData}">
        <table width="100%" style="text-align: center" class="layui-table">
            <tr>
                <th>购物车id</th>
                <th>顾客名称</th>
                <th>商品名称</th>
                <th>购物的数量</th>
                <th colspan="2">操作</th>
            </tr>
            <c:forEach items="${Page.pageData}" var="all">
                <tr>
                    <td>${all.shoppingcar.id}</td>
                    <td>${all.customer.cusName}</td>
                    <td>${all.goods.goodsName}</td>
                    <td>${all.shoppingcar.scNumber}</td>
                    <td><a class="layui-btn" href="doShop?action=shopUpdateById&id=${all.shoppingcar.id}">修改</a></td>
                    <td><a class="layui-btn" href="doShop?action=shopDel&id=${all.shoppingcar.id}">删除</a></td>
                    <td><a class="layui-btn" href="doOrd?action=toOrdAdd&id=${all.shoppingcar.id}">结账</a></td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="12">
                    <%@include file="Page.jsp"%>
                </td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <h3>购物车空了</h3>
    </c:otherwise>
</c:choose>
</body>
</html>
