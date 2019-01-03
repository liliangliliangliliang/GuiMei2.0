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
    <%@include file="../layUI.jsp"%>
</head>
<body>
    <form action="doBig?action=bigUpdate" method="post">
        大分类ID<input type="text" value="${Big.id}" name="id" readonly>
        大分类名称<input type="text" value="${Big.bigName}" name="bigName" >
        大分类描述<input type="text" value="${Big.bigText}" name="bigText" >
        <input type="submit" value="修改完成" class="layui-btn">
    </form>
</body>
</html>
