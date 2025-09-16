package spring.AOP.AOPsetting;

import org.springframework.stereotype.Service;

// 业务服务类
@Service
public class UserService {

    // 正常的业务方法
    public String getUserById(Long id) {
        // 模拟数据库查询
        try {
            Thread.sleep(150); // 模拟耗时操作
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "用户" + id + ": 张三";
    }

    // 可能抛出异常的方法
    public void deleteUser(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("用户ID无效: " + id);
        }
        // 模拟删除操作
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("用户" + id + "已删除");
    }

    // 带参数的方法
    public int updateUserName(Long id, String newName) {
        // 模拟更新操作
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return 1; // 表示更新成功的记录数
    }
}
