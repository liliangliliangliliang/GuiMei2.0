<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getContextPath()%>/"/>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script src="/static/js/jquery-1.8.2.min.js"></script>
    <%@include file="layUI.jsp"%>

    <script>
        $(function () {
            var sellerNameOne=$("#sellerName").val().trim();
            $("#sellerName").blur(function () {
                var sellerName=$(this).val().trim();
                var status=true;
                if(sellerNameOne==sellerName){
                    status=false;
                }
                if(status){
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
                }
            })
        })
    </script>
</head>

<body>
    <form action="doSel?action=selUpdateLogin" method="post">
        <div id="d1"></div>
        ID<input type="text" value="${Sel.id}" name="id" readonly>
        姓名<input type="text" value="${Sel.sellerName}" name="sellerName" id="sellerName">
        登录账号<input type="text" value="${Sel.sellerUser}" name="sellerUser">
        性别<input type="radio" value="男" name="sellerSex" ${Sel.sellerSex=='男'?'checked':''}>男
        <input type="radio" value="女" name="sellerSex" ${Sel.sellerSex=='女'?'checked':''}>女 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        生日<input type="date" value="${Sel.sellerBirthday}" name="sellerBirthday"><br>
        证件号<input type="text" value="${Sel.sellerIdCard}" name="sellerIdCard">
        Email<input type="text" value="${Sel.sellerEmail}" name="sellerEmail"><br>
        电话<input type="text" value="${Sel.sellerTel}" name="sellerTel">
        地址<input type="text" value="${Sel.sellerAddress}" name="sellerAddress">
        <input type="submit" value="修改完成" class="layui-btn" id="sub">
    </form>
</body>
</html>
