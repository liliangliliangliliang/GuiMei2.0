package controller;

import com.alibaba.fastjson.JSONArray;
import core.util.MD5Util;
import pojo.Page;
import pojo.Seller;
import service.impl.SellerServiceImplDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "SellerServlet",urlPatterns = "/doSel")
public class SellerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out =response.getWriter();
        String action=request.getParameter("action");
        String path=request.getContextPath()+"/";
        HttpSession session=request.getSession();
        SellerServiceImplDao sellerService=SellerServiceImplDao.getInstance();



        //商家登录
        if(action!=null && action.equals("cusLogin")){
            String sellerUser=request.getParameter("userLoginName");
            String sellerPassword=MD5Util.toMD5(request.getParameter("userPassword"));
            Seller sel=sellerService.selLogin(sellerUser,sellerPassword);
            if(sel!=null && sel.getSellerName().length()>0){
                session.setAttribute("Sel",sel);
                response.sendRedirect(path+"Seller/index.jsp");
            }else {
                request.setAttribute("login","输入的账号或密码有误");
                request.getRequestDispatcher(path+"Login.jsp").forward(request,response);
            }
        }
        if(action!=null && action.equals("selQuery")){
            int pageSize=5;
            int pageNumber=Integer.valueOf(request.getParameter("pageNumber"));
            Page<Seller> page=sellerService.selQuery(pageSize,pageNumber);
            session.setAttribute("Page",page);
            request.setAttribute("servletURL",path+"doSel?action=selQuery");
            request.getRequestDispatcher(path+"page/seller/sellerQuery.jsp").forward(request,response);
        }
        if(action!=null && action.equals("selQueryById")){
            String id=request.getParameter("id");
            Seller sel=sellerService.selQueryBuId(id);
            session.setAttribute("Sel",sel);
            response.sendRedirect(path+"Seller/sellerUpdate.jsp");
        }
        if(action!=null && action.equals("QueryById")){
            String id=request.getParameter("id");
            Seller sel=sellerService.selQueryBuId(id);
            session.setAttribute("Sel",sel);
            response.sendRedirect(path+"Seller/sellerUpdatePwd.jsp");
        }
        if(action!=null && action.equals("queryName")){
            String sellerName=request.getParameter("sellerName");
            boolean i=sellerService.selQueryName(sellerName);
            if(i){
                out.print(1);
            }else {
                out.print(0);
            }
        }
        if(action!=null && action.equals("selUpdate")){
            Seller sel=new Seller();
            sel.setId(Integer.valueOf(request.getParameter("id")));
            sel.setSellerName(request.getParameter("sellerName"));
            sel.setSellerUser(request.getParameter("sellerUser"));
            sel.setSellerBirthday(Date.valueOf(request.getParameter("sellerBirthday")));
            sel.setSellerSex(request.getParameter("sellerSex"));
            sel.setSellerIdCard(request.getParameter("sellerIdCard"));
            sel.setSellerEmail(request.getParameter("sellerEmail"));
            sel.setSellerTel(request.getParameter("sellerTel"));
            sel.setSellerAddress(request.getParameter("sellerAddress"));
            int i=sellerService.selUpdate(sel);
            if(i>0){
                out.print("修改成功");
            }else {
                out.print("修改失败");
            }
        }
        if(action!=null && action.equals("selUpdateLogin")){
            Seller sel=new Seller();
            sel.setId(Integer.valueOf(request.getParameter("id")));
            sel.setSellerName(request.getParameter("sellerName"));
            sel.setSellerUser(request.getParameter("sellerUser"));
            sel.setSellerBirthday(Date.valueOf(request.getParameter("sellerBirthday")));
            sel.setSellerSex(request.getParameter("sellerSex"));
            sel.setSellerIdCard(request.getParameter("sellerIdCard"));
            sel.setSellerEmail(request.getParameter("sellerEmail"));
            sel.setSellerTel(request.getParameter("sellerTel"));
            sel.setSellerAddress(request.getParameter("sellerAddress"));
            int i=sellerService.selUpdate(sel);
            if(i>0){
                session.setAttribute("Sel",sel);
                response.sendRedirect(path+"Seller/new.jsp");
            }else {
                out.print("修改失败");
            }
        }
        if(action!=null && action.equals("sellerUpdatePwd")){
            Seller sel=new Seller();
            sel.setId(Integer.valueOf(request.getParameter("id")));
            sel.setSellerPassword(MD5Util.toMD5(request.getParameter("sellerPassword")));
            int i=sellerService.selUpdatePwd(sel);
            if(i>0){
                out.print("修改成功");
            }else {
                out.print("修改失败");
            }
        }
        if(action!=null && action.equals("selDelById")){
            String id=request.getParameter("id");
            int i=sellerService.selDel(id);
            if(i>0){
                out.print("删除成功");
            }else {
                out.print("删除失败");
            }
        }
        if(action!=null && action.equals("selAdd")){
            Seller sel=new Seller();
            sel.setSellerName(request.getParameter("sellerName"));
            sel.setSellerUser(request.getParameter("sellerUser"));
            sel.setSellerPassword(MD5Util.toMD5(request.getParameter("sellerPassword")));
            sel.setSellerBirthday(Date.valueOf(request.getParameter("sellerBirthday")));
            sel.setSellerSex(request.getParameter("sellerSex"));
            sel.setSellerIdCard(request.getParameter("sellerIdCard"));
            sel.setSellerEmail(request.getParameter("sellerEmail"));
            sel.setSellerTel(request.getParameter("sellerTel"));
            sel.setSellerAddress(request.getParameter("sellerAddress"));
            int i=sellerService.selAdd(sel);
            if(i>0){
                out.print("添加成功");
            }else {
                out.print("添加失败");
            }
        }
        if(action!=null && action.equals("selNameAll")){
            List<Seller> page=sellerService.selQuery();
            out.print(JSONArray.toJSONString(page));
        }
        if(action!=null && action.equals("out")){
            session.invalidate();
            response.sendRedirect(path+"Login.jsp");
        }
    }
}
