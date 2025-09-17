package spring.multiDataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Map;

// 动态数据源路由
// 自定义动态数据源类继承 AbstractRoutingDataSource，通过 targetDataSources 中的键路由数据源
public class DynamicDataSource extends AbstractRoutingDataSource {


    // 决定使用哪个数据源（返回数据源标识）
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSourceType();
    }



}