<%--
  Created by IntelliJ IDEA.
  User: 52203
  Date: 2018/12/28
  Time: 9:07
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
    <form action="doShop?action=shopUpdate" method="post">
        购物车id<input type="text" value="${All.shoppingcar.id}" name="id" readonly>
        顾客名称<input type="text" value="${All.customer.cusName}" name="scCusId" readonly >
        商品名称<input type="text" value="${All.goods.goodsName}" name="scGoodsId" readonly>
        购物的数量<input type="text" value="${All.shoppingcar.scNumber}" name="scNumber" >
        <input type="submit" value="修改完成" class="layui-btn">
    </form>
</body>
</html>
