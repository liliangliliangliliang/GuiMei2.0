package controller;

import com.alibaba.fastjson.JSONArray;
import pojo.Discount;
import service.impl.DiscountServiceImplDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "DiscountServlet",urlPatterns = "/doDis")
public class DiscountServlet extends HttpServlet {
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
        DiscountServiceImplDao disService=DiscountServiceImplDao.getInstance();


        if(action!=null && action.equals("disNameAll")){
            List<Discount> list=disService.disQuery();
            out.print(JSONArray.toJSONString(list));
        }
    }
}
