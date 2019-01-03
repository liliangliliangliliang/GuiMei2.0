package controller;

import pojo.All;
import pojo.Customer;
import pojo.Page;
import pojo.Shoppingcar;
import service.impl.ShoppingcarServiceImplDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ShoppingcarServlet",urlPatterns = "/doShop")
public class ShoppingcarServlet extends HttpServlet {
    String scCusId;
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
        String path=request.getContextPath();
        ShoppingcarServiceImplDao shopService=ShoppingcarServiceImplDao.getInstance();


        if(action!=null && action.equals("shopQuery")){
            if(request.getParameter("scCusId")!=null){
                scCusId=request.getParameter("scCusId");
            }
            int pageSize=5;
            int pageNumber=Integer.valueOf(request.getParameter("pageNumber"));
            Page<All> page=shopService.shopQuery(pageSize,pageNumber,scCusId);
            session.setAttribute("Page",page);
            request.setAttribute("servletURL",path+"doShop?action=shopQuery");
            request.getRequestDispatcher(path+"Customer/shopQuery.jsp").forward(request,response);
        }
        if(action!=null && action.equals("shopAdd")){
            Shoppingcar shopAdd=new Shoppingcar();
            String goodsId=request.getParameter("id");
            Customer cus=(Customer)session.getAttribute("Cus");
            long cusId=cus.getId();
            shopAdd.setScGoodsId(Integer.valueOf(goodsId));
            shopAdd.setScCusId(cusId);
            Shoppingcar shop=shopService.shopQuery(cusId,goodsId);
            if(shop!=null){
                long number=shop.getScNumber()+1;
                long id=shop.getId();
                shopAdd.setScNumber(number);
                shopAdd.setId(id);
                int i=shopService.shopUpdate(shopAdd);
                if(i>0){
                    out.print("已加入购物车");
                }else {
                    out.print("添加失败");
                }
            }else {
                shopAdd.setScNumber(1);
                int i=shopService.shopAdd(shopAdd);
                if(i>0){
                    out.print("已加入购物车");
                }else {
                    out.print("添加失败");
                }
            }
        }
        if(action!=null && action.equals("shopUpdateById")){
          String id=request.getParameter("id");
            All all=shopService.shopQuery(id);
            session.setAttribute("All",all);
            response.sendRedirect(path+"Customer/shopUpdate.jsp");
        }
        if(action!=null && action.equals("shopUpdate")){
            String id=request.getParameter("id");
            int number=Integer.valueOf(request.getParameter("scNumber"));
            if(number>0){
                Shoppingcar shop=new Shoppingcar();
                shop.setId(Integer.valueOf(id));
                shop.setScNumber(number);
                int i=shopService.shopUpdate(shop);
                if(i>0){
                    out.print("修改完成");
                }else {
                    out.print("修改失败");
                }
            }else {
                int i=shopService.shopDel(id);
                if(i>0){
                    out.print("删除成功");
                }else {
                    out.print("删除失败");
                }
            }
        }
        if(action!=null && action.equals("shopDel")){
            String id=request.getParameter("id");
            int i=shopService.shopDel(id);
            if(i>0){
                out.print("删除成功");
            }else {
                out.print("删除失败");
            }
        }


    }
}
