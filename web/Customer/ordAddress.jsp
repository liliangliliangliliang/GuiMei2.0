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
<form action="doOrd?action=ordAdd" method="post">
    请输入要送货的地址<input type="text" name="orderseAddress" ><br>
    订单金额为<input type="text" value="${Ord.orderseMoney}" name="orderseMoney" readonly><br>
    <input type="submit" value="提交订单" class="layui-btn">
</form>
</body>
</html>
