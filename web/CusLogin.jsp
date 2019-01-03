<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>贵美商城登录</title>
    <link href="static/css/userLogin.css" rel="stylesheet" type="text/css">
    <script src="static/js/jquery-2.1.1.min.js"></script>
    <script>
        $(function () {
            $("#name2").val("");
            $("#pwd2").val("");
            var nameSta = false;
            var pwsSta = false;
            $("#name2").blur(function () {
                var name = $("#name2").val().trim();
                if (name.length == 0) {
                    nameSta = false;
                    $("#name2").val("");
                    $("#name2").attr('placeholder', '请输入账号')
                    $(".d7").css("border", "1px red solid")
                } else {
                    nameSta = true;
                    $(".d7").css("border", "1px rgba(171,228,9,0.85) solid")
                }
            });
            $("#pwd2").blur(function () {
                var pwd = $("#pwd2").val().trim();
                if (pwd.length == 0) {
                    pwsSta = false;
                    $(this).val("");
                    $(this).attr('placeholder', '请输入密码')
                    $(".d8").css("border", "1px red solid")
                } else {
                    pwsSta = true;
                    $(".d8").css("border", "1px rgba(171,228,9,0.85) solid")
                }
            });
        })


    </script>

</head>
<body>
<div class="wrap">
    <form action="../doCus?action=cusLogin" method="post">
        <div class="d1">
            <img src="static/Image/logog.jpg" class="d6">
            <div class="d5">您好，欢迎光临贵美商城！
                <a href="Login.jsp">管理员/商家登录</a>
            </div>

        </div>
        <div class="d2">
            <div>
                <img src="static/Image/login_pic.png" alt="">
            </div>

        </div>
        <div class="d3">
            <div>
                <label>${login}</label>
                <label>${intoNew}</label>
                <b class="d15">贵美商城用户登录</b>
                <a href="cusAdd.jsp">注册新账号</a>
            </div>
            <div class="d7">
                <img src="static/Image/80739f70c892a1875fa66449c3fbd60.png" alt="">
                <input id="name2" type="text" name="cusLoginName" placeholder="用户名" class="d9" >
            </div>
            <div class="d8"> <img src="static/Image/19fc63a3b6bc36b1e78d4d65c91e7de.png" alt="" >
                <input id="pwd2" type="password" name="cusPassword"  placeholder="密码" class="d9" >
            </div>
            <div class="d10">
                <input type="checkbox" value="记住密码" name="forGetPwd">记住密码
                <a href="forGet.jsp"  class="d11">忘记密码？</a>
            </div>
            <div class="d12">
                <input id="butt" type="submit"  value="登录"  >
            </div>
            <div >
                <img src="static/Image/df4f08459dc59880c4b61a56fa1042e.png" alt="abb" class="d13">
                <input type="button" value="⬇" class="d19">
                <img src="static/Image/972ae79439f91eeacd0d43ac6933c63.png" class="d16">
            </div>
        </div>
        <div>
            <img src="static/Image/150faade4f0d044b0bc4c93bdae8269.png" alt="" class="d14">

        </div>
    </form>
</div>
</body>
</html>