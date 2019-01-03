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
        <table width="100%" style="text-align: center" class="layui-table">
            <tr>
                <th>商品名称</th>
                <th>小分类名称</th>
                <th>商品的价格</th>
                <th>商品的数量</th>
                <th>商品的图像</th>
                <th>商品的运费</th>
                <th>商品的类型</th>
                <th>商家名称</th>
                <th>商品的折扣</th>
                <th >操作</th>
            </tr>
                <tr>
                    <td>${All.goods.goodsName}</td>
                    <td>${All.smallName}</td>
                    <td>${All.goods.goodsMoney}</td>
                    <td>${All.goods.goodsNumber}</td>
                    <td><img src="GoodsImage/${All.goods.goodsImage}" ></td>
                    <td>${All.goods.goodsCarriage}</td>
                    <td>${All.goods.goodsType==0?'新品':'二手'}</td>
                    <td>${All.seller.sellerName}</td>
                    <td>${All.discount.discRate}</td>
                    <td><a class="layui-btn" href="doShop?action=shopAdd&id=${All.goods.id}">加入购物车</a></td>
                </tr>
        </table>
</body>
</html>
