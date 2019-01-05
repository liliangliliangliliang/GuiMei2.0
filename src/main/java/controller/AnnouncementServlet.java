package controller;

import pojo.Announcement;
import pojo.Page;
import service.impl.AnnouncementServiceImplDao;

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

@WebServlet(name = "AnnouncementServlet",urlPatterns = "/doAn")
public class AnnouncementServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out =response.getWriter();
        String action=request.getParameter("action");
        String path=request.getContextPath()+"/";
        HttpSession session=request.getSession();
        AnnouncementServiceImplDao anService=AnnouncementServiceImplDao.getInstance();



        if(action!=null && action.equals("anQuery")){
            System.out.println("jintu");
            int pageSize=5;
            int pageNumber=Integer.valueOf(request.getParameter("pageNumber"));
            Page<Announcement> page=anService.anQuery(pageSize,pageNumber);
            session.setAttribute("Page",page);
            request.setAttribute("servletURL",path+"doAn?action=anQuery&pageNumber=1");
            request.getRequestDispatcher(path+"page/announcement/anQuery.jsp").forward(request,response);
        }
        if(action!=null && action.equals("anUpdateById")){
            String id=request.getParameter("id");
            Announcement an=anService.anQuery(id);
            session.setAttribute("An",an);
            response.sendRedirect(path+"page/announcement/anUpdate.jsp");
        }
        if(action!=null && action.equals("queryName")){
            String aTitle=request.getParameter("aTitle");
            boolean i=anService.anName(aTitle);
            if(i){
                out.print(1);
            }else {
                out.print(0);
            }
        }
        if(action!=null && action.equals("anUpdate")){
            Announcement an=new Announcement();
            an.setId(Integer.valueOf(request.getParameter("id")));
            an.setATitle(request.getParameter("aTitle"));
            an.setAText(request.getParameter("aText"));
            an.setADate(Date.valueOf(request.getParameter("aDate")));
            int i=anService.anUpdate(an);
            if(i>0){
                out.print("修改成功");
            }else {
                out.print("修改失败");
            }
        }
        if(action!=null && action.equals("anDel")){
            String id=request.getParameter("id");
            int i=anService.anDel(id);
            if(i>0){
                out.print("删除成功");
            }else {
                out.print("删除失败");
            }
        }
        if(action!=null && action.equals("anAdd")){
            Announcement an=new Announcement();
            an.setATitle(request.getParameter("aTitle"));
            an.setAText(request.getParameter("aText"));
            an.setADate(Date.valueOf(request.getParameter("aDate")));
            int i=anService.anAdd(an);
            if(i>0){
                out.print("添加成功");
            }else {
                out.print("添加失败");
            }
        }

        if(action!=null && action.equals("anns")){
            session.setAttribute("anns",anService.annList());
            session.setAttribute("goodsList",anService.goodsList());
            request.getRequestDispatcher("Customer/GUIMEI/homepage.jsp").forward(request,response);
        }
    }
}
