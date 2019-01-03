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
            var smallNameOne=$("#smallName").val().trim();
            $.ajax({
                url:"doBig",
                data:"action=queryNameAll",
                type:"post",
                dataType:"json",
                success:function (list) {
                    $.each(list,function (index,big) {
                        $("#smallBigId").append("<option value='"+big.id+"'>"+big.bigName+"</option>")
                    })
                }
            })

            $("#smallName").blur(function () {
                var smallName=$(this).val().trim();
                var status=true;
                if(smallNameOne==smallName){
                    status=false;
                }
                if(status){
                    var url="doSmall";
                    var data="action=queryName&smallName="+smallName;
                    $.post(url,data,function (i) {
                        if(i==1){
                            $("#sub").prop("disabled",true);
                            $("#d1").html("输入名字并不能重名").css("color","red");
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
<form action="doSmall?action=smallUpdate" method="post">
    <di id="d1"></di>
    小分类ID<input type="text" value="${Small.id}" name="id" readonly>
    小分类名称<input type="text" value="${Small.smallName}" name="smallName" id="smallName">
    大分类名称<select name="smallBigId" id="smallBigId">
        <option value="${Small.bigclass.id}">${Small.bigclass.bigName}</option>
    </select>
    小分类描述<input type="text" value="${Small.smallText}" name="smallText" >
    <input type="submit" value="修改完成" class="layui-btn" id="sub"  >
</form>
</body>
</html>
