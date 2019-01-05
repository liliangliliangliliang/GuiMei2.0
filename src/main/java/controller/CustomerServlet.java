package controller;

import core.util.MD5Util;
import core.util.UploadFile;
import pojo.Customer;
import pojo.Page;
import service.impl.CustomerServiceImplDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CustomerServlet",urlPatterns = "/doCus")
public class CustomerServlet extends HttpServlet {
    private CustomerServiceImplDao cusService=CustomerServiceImplDao.getInstance();
    private String id;
    private String cusName;
    private String cusSex;
    String cpwd2;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession();
        String action=request.getParameter("action");

        // 顾客注册
        if(action!=null && action.equals("regCus")){
            String cusPath = "/CusImage";

            Map<String,String> map = UploadFile.uploadUtil(cusPath,request,1024*1024*100L,"C_hobby");
            System.out.println(map);
            Customer customer = new Customer();
            customer.setCusName(map.get("C_name"));
            customer.setCusLoginName(map.get("C_loginName"));
            customer.setCusPassword(map.get("C_pwd"));
            if(map.get("C_pwd").equals(map.get("C_pwd2"))){
                customer.setCusPassword(map.get("C_pwd"));
            }else {
                session.setAttribute("errorPwd","2次输入密码不一致");
                response.sendRedirect("register.jsp");
                return;
            };
            customer.setCusEmail(map.get("C_email"));
            customer.setCusSex(map.get("C_sex"));
            customer.setCusPhoto(map.get("C_photo"));
            customer.setCusHobby(map.get("C_hobby"));
            //String birth1 = map.get("C_birthday");

            customer.setCusBirthday(Date.valueOf(map.get("C_birthday")));
            customer.setCusCode(map.get("C_code"));
            if(cusService.addCus(customer)){
                request.getRequestDispatcher("cusLog.jsp").forward(request,response);
            }else {
                session.setAttribute("errorReg","注册失败");
                response.sendRedirect("register.jsp");
            }
        }
        doGet(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession();
        String action=request.getParameter("action");
        String path=request.getContextPath()+"/";

        int pageSize=5;




        //顾客登录
        /*if(action!=null && action.equals("cusLogin")){
            String cusLoginName=request.getParameter("cusLoginName");
            String cusPassword=MD5Util.toMD5(request.getParameter("cusPassword"));
            String flag=request.getParameter("forGetPwd");
            Customer cus=cusService.cusLogin(cusLoginName,cusPassword);
            if(cus.getCusName()!=null && cus.getCusName().length()>0){
                if ("记住密码".equals(flag)) {
                    System.out.println("进入记住密码");
                   Cookie nameCookie = new Cookie("username", cusLoginName);
                   nameCookie.setMaxAge(60 * 60 * 24 * 30);
                   Cookie pwdCookie = new Cookie("password", cusPassword);
                   pwdCookie.setMaxAge(60 * 60 * 24 * 30);
                   response.addCookie(nameCookie);
                   response.addCookie(pwdCookie);
                    }
                session.setAttribute("Cus",cus);
                response.sendRedirect(path+"Customer/index.jsp");
            }else {
                request.setAttribute("login","输入的账号或密码有误");
                request.getRequestDispatcher(path+"CusLogin.jsp").forward(request,response);
            }
        }*/


        //查询所有顾客
        if(action!=null && action.equals("cusQuery")){
            int pageNumber=Integer.valueOf(request.getParameter("pageNumber"));
            Page<Customer> page=cusService.cusQuery(pageSize,pageNumber);
            session.setAttribute("Page",page);
            request.setAttribute("servletURL","doCus?action=cusQuery");
            request.getRequestDispatcher(path+"page/customer/cusQuery.jsp").forward(request,response);
        }
        //级联查询顾客
        if(action!=null && action.equals("cusQueryLike")){
            if(request.getParameter("id")!=null){
                id=request.getParameter("id");
            }
            if(request.getParameter("cusName")!=null){
                cusName=request.getParameter("cusName");
            }
            if(request.getParameter("cusSex")!=null){
                cusSex=request.getParameter("cusSex");
            }
            int pageNumber=Integer.valueOf(request.getParameter("pageNumber"));
            Page<Customer> page=cusService.cusQuery(pageSize,pageNumber,id,cusName,cusSex);
            session.setAttribute("Page",page);
            request.setAttribute("servletURL","doCus?action=cusQueryLike");
            request.getRequestDispatcher(path+"page/customer/cusQuery.jsp").forward(request,response);
        }
        //按ID查一个顾客
        if(action!=null && action.equals("cusQueryById")){
            String id=request.getParameter("id");
            Customer cus=cusService.cusQueryById(id);
            session.setAttribute("Cus",cus);
            response.sendRedirect(path+"page/customer/cusUpdate.jsp");
        }
        //按ID查登录顾客
        if(action!=null && action.equals("QueryById")){
            String id=request.getParameter("id");
            Customer cus=cusService.cusQueryById(id);
            session.setAttribute("Cus",cus);
            response.sendRedirect(path+"Customer/cusQuery.jsp");
        }
        //修改一个顾客信息
        if(action!=null && action.equals("cusUpdate")){
            Customer cus=new Customer();
            cus.setId(Integer.valueOf(request.getParameter("id")));
            cus.setCusName(request.getParameter("cusName"));
            cus.setCusLoginName(request.getParameter("cusLoginName"));
            cus.setCusEmail(request.getParameter("cusEmail"));
            cus.setCusSex(request.getParameter("cusSex"));
            cus.setCusHobby(request.getParameter("cusHobby"));
            cus.setCusCode(request.getParameter("cusCode"));
            cus.setCusBirthday(Date.valueOf(request.getParameter("cusBirthday")));
            int i=cusService.cusAdd(cus);
            if(i>0){
                out.print("修改成功");
            }else {
                out.print("修改失败");
            }
        }

        //注册一个顾客信息
        if(action!=null && action.equals("cusAdd")){
            String filePatu="CusImage";
            String paths=getServletContext().getRealPath(filePatu);
            Map<String,String> map= UploadFile.uploadUtil(paths,request,1024*1024*10,"userHobby");
            Customer cus=new Customer();
            cus.setCusPassword(MD5Util.toMD5(map.get("cusPassword")));
            cus.setCusName(map.get("cusName"));
            cus.setCusLoginName(map.get("cusLoginName"));
            cus.setCusEmail(map.get("cusEmail"));
            cus.setCusSex(map.get("cusSex"));
            cus.setCusHobby(map.get("cusHobby"));
            cus.setCusPhoto(map.get("cusPhoto"));
            cus.setCusCode(map.get("cusCode"));
            cus.setCusBirthday(Date.valueOf(map.get("cusBirthday")));
            int i=cusService.cusAdd(cus);
            if(i>0){
                request.setAttribute("login","注册成功 请登录");
                request.getRequestDispatcher(path+"CusLogin.jsp").forward(request,response);
            }else {
                out.print("注册失败");
            }
        }

        //根据ID删除顾客
        if(action!=null && action.equals("cusDelById")){
            String id=request.getParameter("id");
            int i=cusService.cusDelById(id);
            if(i>0){
                out.print("删除成功");
            }else {
                out.print("删除失败");
            }
        }

        //顾客退出注销
        if(action!=null && action.equals("out")){
            session.invalidate();
            response.sendRedirect(path+"CusLogin.jsp");
        }

        if(action!=null && action.equals("froGetName")){
            String cusName=request.getParameter("cusName");
            String cusCode=request.getParameter("cusCode");
            Customer cus=cusService.cusQueryById(cusName,cusCode);
            if(cus!=null){
                request.setAttribute("Cus",cus);
                request.getRequestDispatcher(path+"froGetPwd.jsp").forward(request,response);
            }else {
                out.print("输入密保错误");
            }

        }
        if(action!=null && action.equals("froGetPwd")){
            String id=request.getParameter("id");
            String cusPassword= MD5Util.toMD5(request.getParameter("cusPassword"));
            int i=cusService.cusUpdatePwd(id,cusPassword);
            if(i>0){
                request.setAttribute("intoNew","新密码已录入");
                request.getRequestDispatcher(path+"CusLogin.jsp").forward(request,response);

            }else {
                out.print("密码重置失败");
            }
        }

        // 顾客登录
        if(action!=null && action.equals("login")){
            String cusLoginName = request.getParameter("C_loginName");
            String cusPwd = request.getParameter("C_pwd");
            Customer customer = cusService.cusLogin(cusLoginName,cusPwd);

            if(customer.getCusLoginName()!=null){
                session.setAttribute("customer",customer);
                response.sendRedirect("Customer/GUIMEI/homepage.jsp");
            }else{
                session.setAttribute("error","您输入的用户名和密码错误");
                response.sendRedirect("cusLog.jsp");
            }
        }


    }
}
