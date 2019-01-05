package core.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName core.filter
 * @AUTHOR mew
 * @Date 2019/1/5 11:31
 * @VERSION 1.0
 */
public class CheckFilterCus implements Filter{
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /**
         * 拦截资源，并进行拦截处理
         * 要验证当前请求有么有进行登录操作
         * 如何标识是否登录
         * 通过session.setAttribute("Student",stu);
         */
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        HttpSession session=request.getSession();
        Object cusObj=session.getAttribute("customer");
        String path=request.getContextPath();
        if(cusObj!=null ){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            response.sendRedirect(path+"/cusLog.jsp");
        }
    }

    public void destroy() {

    }
}