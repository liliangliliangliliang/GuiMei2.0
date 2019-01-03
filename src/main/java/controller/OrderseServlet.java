package controller;

import pojo.*;
import service.impl.GoodsServiceImplDao;
import service.impl.OrderseServiceImplDao;
import service.impl.ShoppingcarServiceImplDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "OrderseServlet",urlPatterns = "/doOrd")
public class OrderseServlet extends HttpServlet {
    Orderse orderse=new Orderse();
    String CusId;
    String shopId;
    String selId;
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
        OrderseServiceImplDao ordService=OrderseServiceImplDao.getInstance();
        ShoppingcarServiceImplDao shopService=ShoppingcarServiceImplDao.getInstance();
        GoodsServiceImplDao goodsService=GoodsServiceImplDao.getInstance();



        if(action!=null && action.equals("ordQuery")){
            if(request.getParameter("scCusId")!=null){
                CusId=request.getParameter("scCusId");
            }
            int pageSize=5;
            int pageNumber=Integer.valueOf(request.getParameter("pageNumber"));
            Page<All> page=ordService.ordQuery(pageSize,pageNumber,CusId);
            session.setAttribute("Page",page);
            request.setAttribute("servletURL",path+"doOrd?action=ordQuery");
            request.getRequestDispatcher(path+"Customer/ordQuery.jsp").forward(request,response);
        }
        if(action!=null && action.equals("ordQueryBySel")){
            if(request.getParameter("id")!=null){
                selId=request.getParameter("id");
            }
            int pageSize=5;
            int pageNumber=Integer.valueOf(request.getParameter("pageNumber"));
            Page<All> page=ordService.ordQuerySel(pageSize,pageNumber,selId);
            session.setAttribute("Page",page);
            request.setAttribute("servletURL",path+"doOrd?action=ordQueryBySel");
            request.getRequestDispatcher(path+"Seller/ordQuery.jsp").forward(request,response);
        }
        if(action!=null && action.equals("UpdateById")){
            String id=request.getParameter("id");
            All all=ordService.ordQueryById(id);
            session.setAttribute("Ord",all);
            response.sendRedirect(path+"Seller/ordUpdate.jsp");
        }
        if(action!=null && action.equals("ordUpdate")){
            Orderse ord=new Orderse();
            ord.setId(Long.valueOf(request.getParameter("id")));
            ord.setOrderseAddress(request.getParameter("orderseAddress"));
            ord.setOrderseMoney(Double.valueOf(request.getParameter("orderseMoney")));
            ord.setOrderseStatus(Long.valueOf(request.getParameter("orderseStatus")));
            int i=ordService.ordUpdate(ord);
            if(i>0){
                out.print("订单修改成功");
            }else {
                out.print("订单修改失败");
            }
        }
        if(action!=null && action.equals("toOrdAdd")){
            shopId=request.getParameter("id");
            int id=Integer.valueOf(shopId);
            Shoppingcar shop=shopService.shopQuery(id);
            long orderseGoodsId=shop.getScGoodsId();
            long orderseCusId=shop.getScCusId();
            long number=shop.getScNumber();
            All all=goodsService.goodsQuery(new String(String.valueOf(orderseGoodsId)));
            Goods goods=all.getGoods();
            double goodsMoney=goods.getGoodsMoney();
            double goodsCarriage=goods.getGoodsCarriage();
            Discount dis=all.getDiscount();
            double discRate=dis.getDiscRate();
            double orderseMoney=goodsMoney*discRate*number+goodsCarriage;
            orderse.setOrderseGoodsId(orderseGoodsId);
            orderse.setOrderseCusId(orderseCusId);
            orderse.setOrderseMoney(orderseMoney);
            request.setAttribute("Ord",orderse);
            request.getRequestDispatcher(path+"Customer/ordAddress.jsp").forward(request,response);
        }

        if(action!=null && action.equals("ordAdd")){
            String orderseAddress=request.getParameter("orderseAddress");
            orderse.setOrderseAddress(orderseAddress);
            Date date2=new Date();
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            String date3=format.format(date2);
            orderse.setOrderseDate(java.sql.Date.valueOf(date3));
            int i=ordService.ordAdd(orderse);
            if(i>0){
                shopService.shopDel(shopId);
                out.print("已成功加入订单 瞬间快递瞬间为你服务到家");
            }else {
                out.print("不能加入订单");
            }
        }



    }
}
