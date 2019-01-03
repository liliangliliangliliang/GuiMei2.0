<%--
  Created by IntelliJ IDEA.
  User: 52203
  Date: 2018/12/27
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getContextPath()%>/"/>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script src="/static/js/jquery-1.8.2.min.js"></script>
    <%@include file="../layUI.jsp"%>

    <script>
        $(function () {
            $("#aTitle").blur(function () {
                var aTitle=$(this).val().trim();
                var url="doAn";
                var data="action=queryName&aTitle="+aTitle;
                $.post(url,data,function (i) {
                    if(i==1){
                        $("#sub").prop("disabled",true);
                        $("#d1").html("要输入标题并不能重名").css("color","red");
                    }else {
                        $("#sub").prop("disabled",false);
                        $("#d1").html("输入标题可以使用").css("color","red");
                    }
                },"json");
            })
        })
    </script>
</head>

<body>
    <form action="doAn?action=anAdd" method="post">
        <div id="d1"></div>
        标题<input type="text" name="aTitle" id="aTitle">
        内容<input type="text" name="aText">
        日期<input type="date" name="aDate"><br>
        <input type="submit" value="添加" class="layui-btn" id="sub">
    </form>
</body>
</html>
