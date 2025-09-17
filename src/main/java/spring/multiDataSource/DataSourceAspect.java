package spring.multiDataSource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DataSourceAspect {

    // 切入点：扫描所有带 @DataSource 注解的方法或类
    @Pointcut("@annotation( spring.multiDataSource.DataSource) || @within( spring.multiDataSource.DataSource)")
    public void dataSourcePointCut() {}

    // 环绕通知：切换数据源
    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            // 获取注解指定的数据源
            DataSourceType dataSourceType = getDataSourceType(joinPoint);
            // 设置当前线程的数据源
            DataSourceContextHolder.setDataSourceType(dataSourceType);

            // 执行目标方法
            return joinPoint.proceed();
        } finally {
            // 清除数据源标识（避免线程池复用导致的问题）
            DataSourceContextHolder.clearDataSourceType();
        }
    }

    // 获取方法或类上的 @DataSource 注解
    private DataSourceType getDataSourceType(ProceedingJoinPoint joinPoint) {
        // 先找方法上的注解
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.isAnnotationPresent(DataSource.class)) {
            return method.getAnnotation(DataSource.class).value();
        }

        // 再找类上的注解
        Class<?> targetClass = joinPoint.getTarget().getClass();
        if (targetClass.isAnnotationPresent(DataSource.class)) {
            return targetClass.getAnnotation(DataSource.class).value();
        }

        // 默认使用主数据源
        return DataSourceType.PRIMARY;
    }
}
