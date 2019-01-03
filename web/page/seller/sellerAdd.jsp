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
            $("#sellerName").blur(function () {
                var sellerName=$(this).val().trim();
                var url="doSel";
                var data="action=queryName&sellerName="+sellerName;
                $.post(url,data,function (i) {
                    if(i==1){
                        $("#sub").prop("disabled",true);
                        $("#d1").html("要输入名字并不能重名").css("color","red");
                    }else {
                        $("#sub").prop("disabled",false);
                        $("#d1").html("输入名字可以使用").css("color","red");
                    }
                },"json");
            })
        })
    </script>
</head>

<body>
    <form action="doSel?action=selAdd" method="post">
        <div id="d1"></div>
        姓名<input type="text"  name="sellerName" id="sellerName">
        登录账号<input type="text"  name="sellerUser">
        密码<input type="text"  name="sellerPassword">
        性别<input type="radio" value="男" name="sellerSex" >男
        <input type="radio" value="女" name="sellerSex" >女 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        生日<input type="date"  name="sellerBirthday"><br>
        证件号<input type="text"  name="sellerIdCard">
        Email<input type="text"  name="sellerEmail"><br>
        电话<input type="text"  name="sellerTel">
        地址<input type="text"  name="sellerAddress">
        <input type="submit" value="添加" class="layui-btn" id="sub">
    </form>
</body>
</html>
