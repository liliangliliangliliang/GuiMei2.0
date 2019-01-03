package core.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName CheckUserFilter
 * @Author LILIANG
 * @Date 2018/12/20 13:54
 * @Version 1.0
 **/
public class CheckUserFilterSel implements Filter {
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
        Object selObj=session.getAttribute("Sel");
        String path=request.getContextPath();
        if(selObj!=null ){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            response.sendRedirect(path+"/Login.jsp");
        }




    }

    public void destroy() {

    }
}
