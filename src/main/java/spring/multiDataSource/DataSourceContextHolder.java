package spring.multiDataSource;


public class DataSourceContextHolder {
    // 用 ThreadLocal 存储当前线程使用的数据源标识
    private static final ThreadLocal<DataSourceType> CONTEXT_HOLDER = new ThreadLocal<>();

    // 设置当前数据源
    public static void setDataSourceType(DataSourceType type) {
        CONTEXT_HOLDER.set(type);
    }

    // 获取当前数据源
    public static DataSourceType getDataSourceType() {
        return CONTEXT_HOLDER.get();
    }

    // 清除当前数据源（避免线程复用导致的问题）
    public static void clearDataSourceType() {
        CONTEXT_HOLDER.remove();
    }
}