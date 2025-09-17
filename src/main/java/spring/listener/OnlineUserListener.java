package spring.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class OnlineUserListener implements HttpSessionListener {

    // 在线用户数（需考虑并发安全）
    private int onlineUserCount = 0;

    // Session 创建时触发（用户登录）
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        onlineUserCount++;
        System.out.println("用户上线，当前在线人数：" + onlineUserCount);
        // 可将在线人数存入 ServletContext（全局共享）
        se.getSession().getServletContext().setAttribute("onlineUserCount", onlineUserCount);
    }

    // Session 销毁时触发（用户退出或超时）
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        onlineUserCount--;
        System.out.println("用户下线，当前在线人数：" + onlineUserCount);
        se.getSession().getServletContext().setAttribute("onlineUserCount", onlineUserCount);
    }
}
