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
           $("#bigName").blur(function () {
               var bigName=$("#bigName").val().trim();
               $.ajax({
                   url:"doBig",
                   data:"action=bigName&bigName="+bigName,
                   type:"post",
                   dataType:"json",
                   success:function (big) {
                       if(big==1){
                           $("#sub").prop("disabled",true);
                           $("#d1").html("当前名字已被使用，换一个").css("color","red")
                       }else {
                           $("#sub").prop("disabled",false);
                           $("#d1").html("当前名字可以使用").css("color","red")
                       }
                   }
               })
           })
       })

    </script>
</head>
<body>
    <form action="doBig?action=bigAdd" method="post">
        <div id="d1">&nbsp;</div>
        大分类名称<input type="text" name="bigName" id="bigName">
        大分类描述<input type="text" name="bigText">
        <input type="submit" value="添加" disabled="true" id="sub" class="layui-btn">
    </form>
</body>
</html>
