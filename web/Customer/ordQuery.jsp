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
                <th>订单ID</th>
                <th>商品名称</th>
                <th>顾客名称</th>
                <th>订单日期</th>
                <th>收货地址</th>
                <th>订单金额</th>
                <th>发货状态</th>
            </tr>
            <c:forEach items="${Page.pageData}" var="all">
                <tr>
                    <td>${all.orderse.id}</td>
                    <td>${all.goods.goodsName}</td>
                    <td>${all.customer.cusName}</td>
                    <td>${all.orderse.orderseDate}</td>
                    <td>${all.orderse.orderseAddress}</td>
                    <td>${all.orderse.orderseMoney}</td>
                    <td>${all.orderse.orderseStatus==0?"买家已下单":"卖家已发货"}</td>
                  </tr>
            </c:forEach>
            <tr>
                <td colspan="7">
                    <%@include file="Page.jsp"%>
                </td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <h3>没有订单了</h3>
    </c:otherwise>
</c:choose>
</body>
</html>
