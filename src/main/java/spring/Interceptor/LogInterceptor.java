package spring.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LogInterceptor implements HandlerInterceptor {

    // 方法执行前调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("请求路径：" + request.getRequestURI());
        request.setAttribute("startTime", System.currentTimeMillis()); // 记录开始时间
        return true; // true 表示放行，false 表示拦截
    }

    // 方法执行后、视图渲染前调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           org.springframework.web.servlet.ModelAndView modelAndView) throws Exception {
        long startTime = (long) request.getAttribute("startTime");
        System.out.println("方法执行时间：" + (System.currentTimeMillis() - startTime) + "ms");
    }

    // 整个请求完成后调用（包括视图渲染）
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        // 可用于资源清理
    }
}
