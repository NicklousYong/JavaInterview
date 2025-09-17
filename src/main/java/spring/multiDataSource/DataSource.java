package spring.multiDataSource;

import java.lang.annotation.*;

// 自定义数据源切换注解
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    // 默认使用主数据源
    DataSourceType value() default DataSourceType.PRIMARY;
}
