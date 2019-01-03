package controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import pojo.Bigclass;
import pojo.Page;
import service.impl.BigclassServiceImplDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "BigclassServlet",urlPatterns = "/doBig")
public class BigclassServlet extends HttpServlet {
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
        BigclassServiceImplDao bigService=BigclassServiceImplDao.getInstance();

        //分页查大分类
        if(action!=null && action.equals("bigQuery")){
            int pageSize=5;
            int pageNumber=Integer.valueOf(request.getParameter("pageNumber"));
            Page<Bigclass> page=bigService.bigQuery(pageSize,pageNumber);
            session.setAttribute("Page",page);
            request.setAttribute("servletURL",path+"doBig?action=bigQuery");
            request.getRequestDispatcher(path+"page/bigclass/bigQuery.jsp").forward(request,response);
        }
        //按ID查一个分类
        if(action!=null && action.equals("bigUpdateById")){
            String id=request.getParameter("id");
            Bigclass big=bigService.bigQuery(id);
            session.setAttribute("Big",big);
            response.sendRedirect(path+"page/bigclass/bigUpdate.jsp");
        }
        //修改一个大分类
        if(action!=null && action.equals("bigUpdate")){
            Bigclass big=new Bigclass();
            big.setId(Integer.valueOf(request.getParameter("id")));
            big.setBigName(request.getParameter("bigName"));
            big.setBigText(request.getParameter("bigText"));
            int i=bigService.bigUpdate(big);
            if(i>0){
                out.print("修改成功");
            }else {
                out.print("修改失败");
            }
        }
        //按ID删除一个没有小分类的大分类
        if(action!=null && action.equals("bigDel")){
            String id=request.getParameter("id");
            int i=bigService.bigDel(id);
            if(i>0){
                response.sendRedirect(path+"doBig?action=bigQuery&pageNumber=1");
            }else {
                out.print("删除失败,不能删除有小分类的大分类");
            }
        }
        //按大分类名查看是否有重名分类
        if(action!=null && action.equals("bigName")){
            String bigName=request.getParameter("bigName");
            boolean i=bigService.bigQueryName(bigName);
            if(i){
                out.print(1);
            }else {
                out.print(0);
            }
        }
        //添加一个不重复名的大分类
        if(action!=null && action.equals("bigAdd")){
            Bigclass big=new Bigclass();
            big.setBigName(request.getParameter("bigName"));
            big.setBigText(request.getParameter("bigText"));
            int i=bigService.bigAdd(big);
            if(i>0){
                response.sendRedirect(path+"doBig?action=bigQuery&pageNumber=1");
            }else {
                out.print("添加失败");
            }
        }
        if(action!=null && action.equals("queryNameAll")){
            List<Bigclass> list=bigService.bigNameAll();
            out.print(JSONArray.toJSONString(list));
        }




    }
}
