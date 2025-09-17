package spring.multiDataSource;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    // 配置主数据源
    @Bean
    @ConfigurationProperties("spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    // 配置从数据源
    @Bean
    @ConfigurationProperties("spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    // 配置动态数据源（将多个数据源注入）
    @Primary  // 优先使用该数据源
    @Bean(name = "customDynamicDataSource")//在 @Configuration 注解的配置类中，@Bean 注解标注的方法所返回的 Bean，其默认名称与方法名一致。由于已经存在了DynamicDataSource会报错
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();

        // 配置默认数据源（当未指定时使用）
        dynamicDataSource.setDefaultTargetDataSource(primaryDataSource());

        // 配置所有数据源（key 为数据源标识，value 为数据源对象）
        Map<Object, Object> dataSources = new HashMap<>();
        dataSources.put(DataSourceType.PRIMARY, primaryDataSource());
        dataSources.put(DataSourceType.SECONDARY, secondaryDataSource());
        dynamicDataSource.setTargetDataSources(dataSources);

        return dynamicDataSource;
    }
}
