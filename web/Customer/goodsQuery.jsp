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
            $.ajax({
                url:"doSel",
                data:"action=selNameAll",
                type:"post",
                dataType:"json",
                success:function (list) {
                    $.each(list,function (index,sel) {
                        $("#sellerName").append("<option value='"+sel.sellerName+"'>"+sel.sellerName+"</option>")
                    })
                }
            })
        })
    </script>
</head>
<body>
<form action="doGoods?action=goodsCusQueryLike&pageNumber=1" method="post" >
    <label>商品名称</label>
    <input type="text" name="goodsName">
    <label>商家名称</label>
    <select name="sellerName" id="sellerName">
        <option></option>
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
            <c:forEach items="${Page.pageData}" var="all">
                <tr>
                    <td>${all.goods.goodsName}</td>
                    <td><img src="GoodsImage/${all.goods.goodsImage}" ></td>
                    <td><a class="layui-btn" href="doGoods?action=goodsLookByIg&id=${all.goods.id}">详情</a></td>
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
