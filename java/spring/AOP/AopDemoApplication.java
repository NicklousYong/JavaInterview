package spring.AOP;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import spring.AOP.AOPsetting.UserService;

@SpringBootApplication
@EnableAspectJAutoProxy // 启用AOP支持
public class AopDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopDemoApplication.class, args);
    }

    // 启动后执行测试代码
    @Bean
    public CommandLineRunner testAop(UserService userService) {
        return args -> {
            System.out.println("=== 测试正常方法调用 ===");
            String user = userService.getUserById(1001L);

            System.out.println("\n=== 测试带参数方法调用 ===");
            int result = userService.updateUserName(1001L, "李四");

            System.out.println("\n=== 测试异常方法调用 ===");
            try {
                userService.deleteUser(-1L); // 会抛出异常
            } catch (IllegalArgumentException e) {
                // 捕获异常，仅作演示
            }
        };
    }
}
