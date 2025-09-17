package spring.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// 拦截所有请求（/*）
@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 排除登录页和静态资源
        String path = httpRequest.getRequestURI();
        if (path.contains("/login") || path.contains("/static/")) {
            chain.doFilter(request, response); // 放行
            return;
        }

        // 验证登录状态
        if (httpRequest.getSession().getAttribute("user") == null) {
            httpResponse.sendRedirect("/login"); // 未登录则跳转登录页
            return;
        }

        chain.doFilter(request, response); // 已登录则放行
    }

    // 初始化和销毁方法（可选）
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    @Override
    public void destroy() {}
}
