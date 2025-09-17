package spring.multiDataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import spring.multiDataSource.service.UserService;


@RestController
public class UserController {

    @Autowired(required = false)
    private final UserService userService;

    @Autowired(required = false)
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 1. 默认使用主数据源（无需注解）
    @GetMapping("/user/{id}")
    public String getUserFromPrimary(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // 2. 通过注解指定使用从数据源
    @GetMapping("/user/secondary/{id}")
    @DataSource(DataSourceType.SECONDARY)
    public String getUserFromSecondary(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}

