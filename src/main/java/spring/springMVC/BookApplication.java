package spring.springMVC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

// @SpringBootApplication 注解包含了@ComponentScan，会自动扫描当前包及子包
@SpringBootApplication
public class BookApplication {
    public static void main(String[] args) {
        // 启动Spring Boot应用
        ConfigurableApplicationContext run = SpringApplication.run(BookApplication.class, args);


    }
}
