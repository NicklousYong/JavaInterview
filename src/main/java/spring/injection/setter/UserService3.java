package spring.injection.setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.injection.constructor.UserDao;

@Service
public class UserService3 {
    private UserDao userDao; // 非 final（需后续通过 Setter 修改）

    // Setter 方法注入：Spring 调用该方法注入 UserDao
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void saveUser() {
        if (userDao != null) {
            userDao.save();
        }
    }
}