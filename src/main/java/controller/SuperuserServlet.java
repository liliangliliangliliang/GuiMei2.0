package controller;

import core.util.MD5Util;
import pojo.Superuser;
import service.impl.SuperuserServiceImplDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SuperuserServlet" ,urlPatterns = "/doUser")
public class SuperuserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession();
        String action=request.getParameter("action");
        String path=request.getContextPath()+"/";
        SuperuserServiceImplDao userService=SuperuserServiceImplDao.getInstance();

        //管理员登录
        if(action!=null && action.equals("userLogin")){
            String userLoginName=request.getParameter("userLoginName");
            String userPassword= MD5Util.toMD5(request.getParameter("userPassword"));
            Superuser user=userService.userQuery(userLoginName,userPassword);
            if(user.getUserName()!=null){
                session.setAttribute("User",user);
                response.sendRedirect(path+"page/index.jsp");
            }else {
                request.setAttribute("login","输入的账号或密码错误");
                request.getRequestDispatcher(path+"Login.jsp").forward(request,response);
            }
        }
        //修改管理员基本信息 除密码
        if(action!=null && action.equals("userUpdate")){
            Superuser sup=(Superuser)session.getAttribute("User");
            Superuser user=new Superuser();
            user.setUserImage(sup.getUserImage());
            user.setId(Integer.valueOf(request.getParameter("id")));
            user.setUserName(request.getParameter("userName"));
            user.setUserId(request.getParameter("userId"));
            user.setUserLoginName(request.getParameter("userLoginName"));
            int i=userService.userUpdate(user);
            if(i>0){
                session.setAttribute("User",user);
                response.sendRedirect(path+"page/new.jsp");
            }else {
                out.print("修改失败");
            }
        }
        //依ID查管理员修改密码
        if(action!=null && action.equals("userQueryById")){
            String id=request.getParameter("id");
            Superuser user=userService.userQuery(id);
            session.setAttribute("User",user);
            response.sendRedirect(path+"page/user/userUpdatePwd.jsp");
        }
        //修改管理员密码
        if(action!=null && action.equals("userUpdatePwd")){
            String id=request.getParameter("id");
            String userPassword= MD5Util.toMD5(request.getParameter("userPassword"));
            int i=userService.userUpdate(userPassword,id);
            if(i>0){
                out.print("修改成功");
            }else {
                out.print("修改失败");
            }
        }
        //管理员退出注销
        if(action!=null && action.equals("out")){
            session.invalidate();
            response.sendRedirect(path+"Login.jsp");
        }
        //查看管理员信息
        if(action!=null && action.equals("QueryById")){
            String id=request.getParameter("id");
            Superuser user=userService.userQuery(id);
            session.setAttribute("User",user);
            response.sendRedirect(path+"page/user/userQuery.jsp");
        }

    }
}
