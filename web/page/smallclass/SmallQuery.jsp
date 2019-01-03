<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getContextPath()%>/"/>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script src="static/js/jquery-2.1.1.min.js"></script>
    <%@include file="../layUI.jsp"%>

    <script>
        $(function () {
                $.ajax({
                    url:"doBig",
                    data:"action=queryNameAll",
                    type:"post",
                    dataType:"json",
                    success:function (list) {
                        $.each(list,function (index,big) {
                            $("#bigName").append("<option value='"+big.bigName+"'>"+big.bigName+"</option>")
                        })
                    }
                })
        })
    </script>
</head>
<body>
<form action="page/smallclass/smallAdd.jsp" method="post">
    <input type="submit" value="添加小分类" class="layui-btn">
</form>
<form action="doSmall?action=smallQueryLike&pageNumber=1" method="post" >
    <label>小分类名</label>
    <input type="text" name="smallName">
    <label>大分类名</label>
    <select name="bigName" id="bigName">
        <option></option>
    </select>
    <input class="layui-btn" type="submit" value="查询">
</form>
<c:choose>
    <c:when test="${not empty Page.pageData}">
        <table width="100%" style="text-align: center" class="layui-table">
            <tr>
                <th>小分类ID</th>
                <th>小分类名称</th>
                <th>大分类名称</th>
                <th>小分类描述</th>
                <th colspan="2">操作</th>
            </tr>
            <c:forEach items="${Page.pageData}" var="small">
                <tr>
                    <td>${small.id}</td>
                    <td>${small.smallName}</td>
                    <td>${small.bigclass.bigName}</td>
                    <td>${small.smallText}</td>
                    <td><a class="layui-btn" href="doSmall?action=smallUpdateById&id=${small.id}">修改</a></td>
                    <td><a class="layui-btn" href="doSmall?action=smallDel&id=${small.id}">删除</a></td>
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
        <h3>没有小类了</h3>
    </c:otherwise>
</c:choose>
</body>
</html>
