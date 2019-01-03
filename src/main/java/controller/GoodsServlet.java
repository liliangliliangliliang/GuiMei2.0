package controller;

import core.util.UploadFile;
import pojo.All;
import pojo.Goods;
import pojo.Page;
import service.impl.GoodsServiceImplDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "GoodsServlet",urlPatterns = "/doGoods")
public class GoodsServlet extends HttpServlet {
    String goodsName;
    String sellerName;
    String smallName;
    String id;

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
        GoodsServiceImplDao goodsService=GoodsServiceImplDao.getInstance();
        int pageSize=5;

        if(action!=null && action.equals("goodsQuery")){
            int pageNumber=Integer.valueOf(request.getParameter("pageNumber"));
            Page<All> page=goodsService.goodsQuery(pageSize,pageNumber);
            session.setAttribute("Page",page);
            request.setAttribute("servletURL",path+"doGoods?action=goodsQuery");
            request.getRequestDispatcher(path+"page/goods/goodsQuery.jsp").forward(request,response);
        }
        if(action!=null && action.equals("goodsQueryLike")){
            int pageNumber=Integer.valueOf(request.getParameter("pageNumber"));
            if(request.getParameter("smallName")!=null){
                smallName=request.getParameter("smallName");
            }
            if(request.getParameter("goodsName")!=null){
                goodsName=request.getParameter("goodsName");
            }
            if(request.getParameter("sellerName")!=null){
                sellerName=request.getParameter("sellerName");
            }
            Page<All> page=goodsService.goodsQuery(pageSize,pageNumber,goodsName,sellerName,smallName);
            session.setAttribute("Page",page);
            request.setAttribute("servletURL",path+"doGoods?action=goodsQueryLike");
            request.getRequestDispatcher(path+"page/goods/goodsQuery.jsp").forward(request,response);
        }
        //商家查询
        if(action!=null && action.equals("goodsQueryById")){
            if(request.getParameter("id")!=null){
                id=request.getParameter("id");
            }
            int pageNumber=Integer.valueOf(request.getParameter("pageNumber"));
            Page<All> page=goodsService.goodsQuery(pageSize,pageNumber,id);
            session.setAttribute("Page",page);
            request.setAttribute("servletURL",path+"doGoods?action=goodsQueryById");
            request.getRequestDispatcher(path+"Seller/goodsQuery.jsp").forward(request,response);
        }
        if(action!=null && action.equals("UpdateById")){
            String id=request.getParameter("id");
            All all=goodsService.goodsQuery(id);
            session.setAttribute("All",all);
            response.sendRedirect(path+"Seller/goodsUpdate.jsp");
        }
        if(action!=null && action.equals("QueryLike")){
            if(request.getParameter("id")!=null){
                id=request.getParameter("id");
            }
            int pageNumber=Integer.valueOf(request.getParameter("pageNumber"));
            if(request.getParameter("smallName")!=null){
                smallName=request.getParameter("smallName");
            }
            if(request.getParameter("goodsName")!=null){
                goodsName=request.getParameter("goodsName");
            }
            if(request.getParameter("sellerName")!=null){
                sellerName=request.getParameter("sellerName");
                System.out.println(sellerName);
            }
            Page<All> page=goodsService.goodsQuery(pageSize,pageNumber,goodsName,sellerName,smallName,id);
            session.setAttribute("Page",page);
            request.setAttribute("servletURL",path+"doGoods?action=QueryLike");
            request.getRequestDispatcher(path+"Seller/goodsQuery.jsp").forward(request,response);
        }

        //顾客的查询
        if(action!=null && action.equals("goodsCusQuery")){
            int pageNumber=Integer.valueOf(request.getParameter("pageNumber"));
            Page<All> page=goodsService.goodsQuery(pageSize,pageNumber);
            session.setAttribute("Page",page);
            request.setAttribute("servletURL",path+"doGoods?action=goodsCusQuery");
            request.getRequestDispatcher(path+"Customer/goodsQuery.jsp").forward(request,response);
        }
        if(action!=null && action.equals("goodsCusQueryLike")){
            int pageNumber=Integer.valueOf(request.getParameter("pageNumber"));
            if(request.getParameter("smallName")!=null){
                smallName=request.getParameter("smallName");
            }
            if(request.getParameter("goodsName")!=null){
                goodsName=request.getParameter("goodsName");
            }
            if(request.getParameter("sellerName")!=null){
                sellerName=request.getParameter("sellerName");
            }
            Page<All> page=goodsService.goodsQuery(pageSize,pageNumber,goodsName,sellerName,smallName);
            session.setAttribute("Page",page);
            request.setAttribute("servletURL",path+"doGoods?action=goodsCusQueryLike");
            request.getRequestDispatcher(path+"Customer/goodsQuery.jsp").forward(request,response);
        }
        if(action!=null && action.equals("goodsLookByIg")){
            String id=request.getParameter("id");
            All all=goodsService.goodsQuery(id);
            session.setAttribute("All",all);
            response.sendRedirect(path+"Customer/goodsQueryOne.jsp");
        }
        //顾客查询结束

        if(action!=null && action.equals("goodsUpdateById")){
            String id=request.getParameter("id");
            All all=goodsService.goodsQuery(id);
            session.setAttribute("All",all);
            response.sendRedirect(path+"page/goods/goodsUpdate.jsp");
        }
        if(action!=null && action.equals("queryName")){
            String goodsName=request.getParameter("goodsName");
            String goodsSeId=request.getParameter("goodsSeId");
            boolean i=goodsService.goodsName(goodsName,goodsSeId);
            if(i){
                out.print(1);
            }else {
                out.print(0);
            }
        }
        if(action!=null && action.equals("goodsUpdate")){
            //定义要上传的文件的保存的位置
            String filePatu="GoodsImage";
            String paths=getServletContext().getRealPath(filePatu);
            Map<String,String> map= UploadFile.uploadUtil(paths,request,1024*1024*10,"userHobby");
            Goods goods=new Goods();
            goods.setId(Integer.valueOf(map.get("id")));
            goods.setGoodsName(map.get("goodsName").trim());
            goods.setGoodsMoney(Double.valueOf(map.get("goodsMoney")));
            goods.setGoodsNumber(Integer.valueOf(map.get("goodsNumber")));
           /* goods.setGoodsImage(map.get("goodsImage"));*/
            goods.setGoodsCarriage(Double.valueOf(map.get("goodsCarriage")));
            goods.setGoodsType(Integer.valueOf(map.get("goodsType")));
            goods.setGoodsSmalId(Integer.valueOf(map.get("goodsSmalId")));
            goods.setGoodsSeId(Integer.valueOf(map.get("goodsSeId")));
            goods.setGoodsDiscId(Integer.valueOf(map.get("goodsDiscId")));
            int i=goodsService.goodsUpdate(goods);
            if(i>0){
                out.print("修改成功");
            }else {
                out.print("修改失败");
            }
        }
        if(action!=null && action.equals("goodsDel")){
            String id=request.getParameter("id");
            int i=goodsService.goodsDel(id);
            if(i>0){
                out.print("删除成功");
            }else {
                out.print("删除失败");
            }
        }
        if(action!=null && action.equals("goodsAdd")){
            //定义要上传的文件的保存的位置
            String filePatu="GoodsImage";
            String paths=getServletContext().getRealPath(filePatu);
            Map<String,String> map= UploadFile.uploadUtil(paths,request,1024*1024*10,"userHobby");
            Goods goods=new Goods();
            goods.setGoodsName(map.get("goodsName").trim());
            goods.setGoodsMoney(Double.valueOf(map.get("goodsMoney")));
            goods.setGoodsNumber(Integer.valueOf(map.get("goodsNumber")));
            goods.setGoodsImage(map.get("goodsImage"));
            goods.setGoodsCarriage(Double.valueOf(map.get("goodsCarriage")));
            goods.setGoodsType(Integer.valueOf(map.get("goodsType")));
            goods.setGoodsSmalId(Integer.valueOf(map.get("goodsSmalId")));
            goods.setGoodsSeId(Integer.valueOf(map.get("goodsSeId")));
            goods.setGoodsDiscId(Integer.valueOf(map.get("goodsDiscId")));
            int i=goodsService.goodsAdd(goods);
            if(i>0){
                response.sendRedirect(path+"doGoods?action=goodsQuery&pageNumber=1");
            }else {
                out.print("添加失败");
            }
        }
        if(action!=null && action.equals("goodsSelAdd")){
            //定义要上传的文件的保存的位置
            String filePatu="GoodsImage";
            String paths=getServletContext().getRealPath(filePatu);
            Map<String,String> map= UploadFile.uploadUtil(paths,request,1024*1024*10,"userHobby");
            Goods goods=new Goods();
            goods.setGoodsName(map.get("goodsName").trim());
            goods.setGoodsMoney(Double.valueOf(map.get("goodsMoney")));
            goods.setGoodsNumber(Integer.valueOf(map.get("goodsNumber")));
            goods.setGoodsImage(map.get("goodsImage"));
            goods.setGoodsCarriage(Double.valueOf(map.get("goodsCarriage")));
            goods.setGoodsType(Integer.valueOf(map.get("goodsType")));
            goods.setGoodsSmalId(Integer.valueOf(map.get("goodsSmalId")));
            goods.setGoodsSeId(Integer.valueOf(id));
            goods.setGoodsDiscId(Integer.valueOf(map.get("goodsDiscId")));
            int i=goodsService.goodsAdd(goods);
            if(i>0){
                response.sendRedirect(path+"doGoods?action=goodsQueryById&pageNumber=1");
            }else {
                out.print("添加失败");
            }
        }





    }
}
