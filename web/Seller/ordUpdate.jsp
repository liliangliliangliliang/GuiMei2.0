<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getContextPath()%>/"/>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script src="/static/js/jquery-1.8.2.min.js"></script>
    <%@include file="layUI.jsp"%>

</head>

<body>
    <form action="doOrd?action=ordUpdate" method="post">
        <div id="d1"></div>
        订单ID<input type="text" value="${Ord.orderse.id}" name="id" readonly>
        商品名称<input type="text" value="${Ord.goods.goodsName}" name="goodsName" readonly>
        顾客名称<input type="text" value="${Ord.customer.cusName}" name="cusName" readonly>
        订单日期<input type="date" value="${Ord.orderse.orderseDate}" name="sellerBirthday" readonly><br>
        收货地址<input type="text" value="${Ord.orderse.orderseAddress}" name="orderseAddress">
        订单金额<input type="text" value="${Ord.orderse.orderseMoney}" name="orderseMoney"><br>
        发货状态<select name="orderseStatus">
        <option value="${Ord.orderse.orderseStatus}">${Ord.orderse.orderseStatus==0?"买家已下单":"卖家已发货"} </option>
        <option value="0">买家已下单</option>
        <option value="1">卖家已发货</option>
    </select>
        <input type="submit" value="修改完成" class="layui-btn" >
    </form>
</body>
</html>
