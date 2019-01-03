<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>贵美商城后台管理系统</title>
    <base href="<%=request.getContextPath() %>/" />
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@include file="layUI.jsp"%>
    <link rel="stylesheet" href="static/css/index.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div id="head"  class="layui-header">
        <div class="layui-logo">
            <img style="margin-left: 15px" width="260px" src="static/back/Image/logo_1.png">
        </div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                 欢迎管理员&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   <img src="${User.userImage}" class="layui-nav-img">
                   ${User.userName}
                </a>
                <dl class="layui-nav-child">
                    <dd><a target="iframe_context" href="doUser?action=QueryById&id=${User.id}">查询个人信息</a></dd>
                    <dd><a target="iframe_context" href="page/user/userUpdate.jsp">修改个人信息</a></dd>
                    <dd><a target="iframe_context" href="doUser?action=userQueryById&id=${User.id}">修改密码</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a id="outPage" href="doUser?action=out" class="layui-icon ">&#x1007;</a></li>
        </ul>
    </div>

    <div  class="layui-side layui-bg-black">
        <div class="layui-side-scroll" id="left1">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul id="left2" class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="layui-icon" href="javascript:;" style="color:#9FC105">&#xe770;&nbsp;&nbsp;顾客管理</a>
                    <dl class="layui-nav-child">
                        <!--a标签中写ifream的name 则点击超链接 就会在指定name的ifream中显示-->
                        <dd><a target="iframe_context"  href="doCus?action=cusQuery&pageNumber=1">查询顾客</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="layui-icon" href="javascript:;" style="color:#9FC105">&#xe621;&nbsp;&nbsp;分类管理</a>
                    <dl class="layui-nav-child">
                        <dd><a target="iframe_context" href="doBig?action=bigQuery&pageNumber=1">大分类管理</a></dd>
                        <dd><a target="iframe_context" href="doSmall?action=smallQuery&pageNumber=1">小分类管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="layui-icon" href="javascript:;" style="color:#9FC105">&#xe621;&nbsp;&nbsp;商品管理</a>
                    <dl class="layui-nav-child">
                        <dd><a target="iframe_context" href="doGoods?action=goodsQuery&pageNumber=1">查询商品</a></dd>
                        <dd><a target="iframe_context" href="page/goods/goodsAdd.jsp">添加商品</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="layui-icon" href="javascript:;" style="color:#9FC105">&#xe621;&nbsp;&nbsp;公告管理</a>
                    <dl class="layui-nav-child">
                        <dd><a target="iframe_context" href="doAn?action=anQuery&pageNumber=1">查询公告</a></dd>
                        <dd><a target="iframe_context" href="page/announcement/anAdd.jsp">添加公告</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="layui-icon" href="javascript:;" style="color:#9FC105">&#xe770;&nbsp;&nbsp;商家管理</a>
                    <dl class="layui-nav-child">
                        <dd><a target="iframe_context" href="doSel?action=selQuery&pageNumber=1">查询商家</a></dd>
                        <dd><a target="iframe_context" href="page/seller/sellerAdd.jsp">添加商家</a></dd>
                    </dl>
                </li>

            </ul>
        </div>
    </div>

    <div id="main" class="layui-body">
        <%-- 通过使用静态包含将指定的jsp页面包含到当前页面中--%>
        <div class="bannerWarp">
            <jsp:include page="banner.jsp"/>
        </div>

        <!-- 内容主体区域 -->
        <div style="padding: 15px;">

            <iframe name="iframe_context" frameborder="0" id="context" src=""  ></iframe>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © www.kgc.com - 贵美商城
    </div>
</div>

<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });
    layui.use('carousel', function(){
        var carousel = layui.carousel;
        //建造实例
        carousel.render({
            elem: '#test1'
            ,width: '100%' //设置容器宽度
            ,arrow: 'always' //始终显示箭头
            ,height:'200px'
            /*  ,anim: 'fade' //切换动画方式*/
            ,interval:'3000'
        });
    });
</script>
</body>
</html>