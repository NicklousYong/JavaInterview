package spring.multiDataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.example.mapper")  // 扫描 Mapper 接口
public class MyBatisConfig {

    // 注入动态数据源（关键：使用动态数据源而非单一数据源）
    private final DataSource dynamicDataSource;

    public MyBatisConfig(DataSource dynamicDataSource) {
        this.dynamicDataSource = dynamicDataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dynamicDataSource);  // 设置动态数据源

        // 配置 Mapper 映射文件路径
        sessionFactory.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath:mapper/**/*.xml")
        );
        return sessionFactory;
    }
}
