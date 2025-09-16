package spring.injection.constructor;


import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    public void save() {
        System.out.println("保存用户到数据库");
    }
}