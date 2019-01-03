package controller;

import com.alibaba.fastjson.JSONArray;
import org.apache.tomcat.util.digester.SetTopRule;
import pojo.All;
import pojo.Page;
import pojo.Smallclass;
import service.impl.SmallclassServiceImplDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "SmallclassServlet",urlPatterns = "/doSmall")
public class SmallclassServlet extends HttpServlet {
    String smallName;
    String bigName;
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
        SmallclassServiceImplDao smallService=SmallclassServiceImplDao.getInstance();
        int pageSize=5;


        if(action!=null && action.equals("smallQuery")){
            int pageNumber=Integer.valueOf(request.getParameter("pageNumber"));
            Page<All> page=smallService.smallQuery(pageSize,pageNumber);
            session.setAttribute("Page",page);
            request.setAttribute("servletURL",path+"doSmall?action=smallQuery");
            request.getRequestDispatcher(path+"page/smallclass/SmallQuery.jsp").forward(request,response);
        }
        if(action!=null && action.equals("smallQueryLike")){
            int pageNumber=Integer.valueOf(request.getParameter("pageNumber"));
            if(request.getParameter("smallName")!=null){
                smallName=request.getParameter("smallName");
            }
            if(request.getParameter("bigName")!=null){
                bigName=request.getParameter("bigName");
            }
            Page<All> page=smallService.smallQuery(pageSize,pageNumber,smallName,bigName);
            session.setAttribute("Page",page);
            request.setAttribute("servletURL",path+"doSmall?action=smallQueryLike");
            request.getRequestDispatcher(path+"page/smallclass/SmallQuery.jsp").forward(request,response);
        }

        if(action!=null && action.equals("smallUpdateById")){
            String id=request.getParameter("id");
            Smallclass small=smallService.smallQueryById(id);
            session.setAttribute("Small",small);
            response.sendRedirect(path+"page/smallclass/SmallUpdate.jsp");
        }
        if(action!=null && action.equals("queryName")){
            String smallName=request.getParameter("smallName");
            boolean i=smallService.smallQueryName(smallName);
            if(i){
                out.print(1);
            }else {
                out.print(0);
            }
        }
        if(action!=null && action.equals("smallUpdate")){
            Smallclass small=new Smallclass();
            small.setId(Integer.valueOf(request.getParameter("id")));
            small.setSmallName(request.getParameter("smallName"));
            small.setSmallBigId(Integer.valueOf(request.getParameter("smallBigId")));
            small.setSmallText(request.getParameter("smallText"));
            int i=smallService.smallUpdate(small);
            if(i>0){
                out.print("修改成功");
            }else {
                out.print("修改失败");
            }
        }
        if(action!=null && action.equals("smallDel")){
            String id=request.getParameter("id");
            int i=smallService.smallDel(id);
            if(i>0){
                out.print("删除成功");
            }else {
                out.print("删除失败");
            }
        }
        if(action!=null && action.equals("smallAdd")){
            Smallclass small=new Smallclass();
            small.setSmallName(request.getParameter("smallName"));
            small.setSmallBigId(Integer.valueOf(request.getParameter("smallBigId")));
            small.setSmallText(request.getParameter("smallText"));
            int i=smallService.smallAdd(small);
            if(i>0){
                response.sendRedirect(path+"doSmall?action=smallQuery&pageNumber=1");
            }else {
                out.print("添加失败");
            }
        }
        if(action!=null && action.equals("smallNameAll")){
            List<All> page=smallService.smallQuery();
            out.print(JSONArray.toJSONString(page));
        }
    }
}
