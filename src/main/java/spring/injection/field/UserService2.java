package spring.injection.field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.injection.constructor.UserDao;

@Service
public class UserService2 {
    // 字段注入：Spring 通过反射直接给 private 字段赋值
    @Autowired
    private UserDao userDao; // 非 final，且无显式初始化

    public void saveUser() {
        userDao.save();
    }

}