<%--
  Created by IntelliJ IDEA.
  User: 52203
  Date: 2018/12/28
  Time: 8:49
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
<form action="page/bigclass/bigAdd.jsp" method="post">
    <input type="submit" value="添加大分类" class="layui-btn">
</form>
    <c:choose>
        <c:when test="${not empty Page.pageData}">
            <table width="100%" style="text-align: center" class="layui-table">
                <tr>
                    <th>大分类ID</th>
                    <th>大分类名称</th>
                    <th>大分类描述</th>
                    <th colspan="2">操作</th>
                </tr>
            <c:forEach items="${Page.pageData}" var="big">
                <tr>
                    <td>${big.id}</td>
                    <td>${big.bigName}</td>
                    <td>${big.bigText}</td>
                    <td><a class="layui-btn" href="doBig?action=bigUpdateById&id=${big.id}">修改</a></td>
                    <td><a class="layui-btn" href="doBig?action=bigDel&id=${big.id}">删除</a></td>
                </tr>
            </c:forEach>
                <tr>
                    <td colspan="5">
                        <%@include file="../Page.jsp"%>
                    </td>
                </tr>
            </table>
        </c:when>
        <c:otherwise>
            <h3>没有大分类了</h3>
        </c:otherwise>
    </c:choose>
</body>
</html>
