<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getContextPath()%>/"/>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script src="static/js/jquery-2.1.1.min.js"></script>
    <%@include file="layUI.jsp"%>

    <script>
        $(function () {
            $.ajax({
                url:"doSmall",
                data:"action=smallNameAll",
                type:"post",
                dataType:"json",
                success:function (list) {
                    $.each(list,function (index,all) {
                        $("#smallName").append("<option value='"+all.smallName+"'>"+all.smallName+"</option>")
                    })
                }
            })
       /*     $.ajax({
                url:"doSel",
                data:"action=selNameAll",
                type:"post",
                dataType:"json",
                success:function (list) {
                    $.each(list,function (index,sel) {
                        $("#sellerName").append("<option value='"+sel.sellerName+"'>"+sel.sellerName+"</option>")
                    })
                }
            })*/
        })
    </script>
</head>
<body>
<form action="doGoods?action=QueryLike&pageNumber=1" method="post" >
    <label>商品名称</label>
    <input type="text" name="goodsName">
    <label>商家名称</label>
    <select name="sellerName" id="sellerName">
        <option value="${Page.pageData[0].seller.sellerName}">${Page.pageData[0].seller.sellerName}</option>
    </select>
    <label>小分类名称</label>
    <select name="smallName" id="smallName">
        <option></option>
    </select>
    <input class="layui-btn" type="submit" value="查询">
</form>
<c:choose>
    <c:when test="${not empty Page.pageData}">
        <table width="100%" style="text-align: center" class="layui-table">
            <tr>
                <th>商品id</th>
                <th>商品名称</th>
                <th>小分类名称</th>
                <th>商品的价格</th>
                <th>商品的数量</th>
                <th>商品的图像</th>
                <th>商品的运费</th>
                <th>商品的类型</th>
                <th>商家名称</th>
                <th>商品的折扣</th>
                <th colspan="2">操作</th>
            </tr>
            <c:forEach items="${Page.pageData}" var="all">
                <tr>
                    <td>${all.goods.id}</td>
                    <td>${all.goods.goodsName}</td>
                    <td>${all.smallName}</td>
                    <td>${all.goods.goodsMoney}</td>
                    <td>${all.goods.goodsNumber}</td>
                    <td><img src="GoodsImage/${all.goods.goodsImage}" ></td>
                    <td>${all.goods.goodsCarriage}</td>
                    <td>${all.goods.goodsType==0?'新品':'二手'}</td>
                    <td>${all.seller.sellerName}</td>
                    <td>${all.discount.discRate}</td>
                    <td><a class="layui-btn" href="doGoods?action=UpdateById&id=${all.goods.id}">修改</a></td>
                    <td><a class="layui-btn" href="doGoods?action=goodsDel&id=${all.goods.id}">删除</a></td>
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
        <h3>没有商品了</h3>
    </c:otherwise>
</c:choose>
</body>
</html>
