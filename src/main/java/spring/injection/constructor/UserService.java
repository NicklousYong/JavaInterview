package spring.injection.constructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDao userDao; // 建议用 final 修饰，确保依赖不可变

    // 构造器注入：Spring 自动将 UserDao 实例传入
    @Autowired // Spring 4.3+ 后，若类只有一个构造器，@Autowired 可省略
    public UserService(UserDao userDao) {
        this.userDao = userDao; // 初始化依赖
    }

    public void saveUser() {
        userDao.save(); // 使用注入的依赖
    }



}