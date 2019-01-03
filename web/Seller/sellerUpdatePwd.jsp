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
    <form action="doSel?action=sellerUpdatePwd" method="post">
        <div id="d1"></div>
        ID<input type="text" value="${Sel.id}" name="id" readonly>
        姓名<input type="text" value="${Sel.sellerName}" name="sellerName" id="sellerName" readonly>
        登录账号<input type="text" value="${Sel.sellerUser}" name="sellerUser" readonly>
        密码<input type="text" value="${Sel.sellerPassword}" name="sellerPassword">
        <input type="submit" value="修改完成" class="layui-btn" id="sub">
    </form>
</body>
</html>
