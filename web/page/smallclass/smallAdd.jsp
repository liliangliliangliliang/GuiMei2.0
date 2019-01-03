<%--
  Created by IntelliJ IDEA.
  User: 52203
  Date: 2018/12/29
  Time: 9:21
  To change this template use File | Settings | File Templates.
--%>
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
            var url="doBig";
            var data="action=queryNameAll";
            $.post(url,data,function (list) {
                $.each(list,function (index,big) {
                    $("#smallBigId").append("<option value='"+big.id+"'>"+big.bigName+"</option>")
                })
            },"json");
            $("#smallName").blur(function () {
                var smallName=$(this).val().trim();
                var url="doSmall";
                var data="action=queryName&smallName="+smallName;
                $.post(url,data,function (i) {
                    if(i==1){
                        $("#d1").html("输入名字不能使用").css("color","red")
                        $("#sub").prop("disabled",true)
                    }else {
                        $("#d1").html("输入名字可以使用").css("color","red")
                        $("#sub").prop("disabled",false)
                    }
                },"json")
            })
        })
    </script>
</head>
<body>
<form action="doSmall?action=smallAdd" method="post">
    <di id="d1"></di>
    小分类名称<input type="text"  name="smallName" id="smallName">
    大分类名称<select name="smallBigId" id="smallBigId">
    </select>
    小分类描述<input type="text" name="smallText" >
    <input type="submit" value="添加" class="layui-btn" id="sub"  >
</form>
</body>
</html>
