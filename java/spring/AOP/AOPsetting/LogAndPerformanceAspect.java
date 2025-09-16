package spring.AOP.AOPsetting;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

// 声明这是一个切面类
@Aspect
// 让Spring管理这个Bean
@Component
public class LogAndPerformanceAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAndPerformanceAspect.class);

    // 定义切入点：匹配com.example.aopdemo.service包下所有类的所有方法
    @Pointcut("execution(* com.example.aopdemo.service..*(..))")
    public void serviceMethods() {}

    // 前置通知：方法执行前记录日志
    @Before("serviceMethods()")
    public void logBeforeMethod(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        logger.info("调用 {}.{}() 方法，参数: {}",
                className, methodName, Arrays.toString(args));
    }

    // 后置通知：方法执行后记录日志（无论是否抛出异常）
    @After("serviceMethods()")
    public void logAfterMethod(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        logger.info("{}.{}() 方法执行结束", className, methodName);
    }

    // 返回通知：方法正常返回后记录返回值
    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        logger.info("{}.{}() 方法返回值: {}", className, methodName, result);
    }

    // 异常通知：方法抛出异常时记录异常信息
    @AfterThrowing(pointcut = "serviceMethods()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Exception ex) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        logger.error("{}.{}() 方法抛出异常: {}",
                className, methodName, ex.getMessage(), ex);
    }

    // 环绕通知：用于性能监控，计算方法执行时间
    @Around("serviceMethods()")
    public Object monitorPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        try {
            // 执行目标方法
            return joinPoint.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            String className = joinPoint.getTarget().getClass().getSimpleName();
            String methodName = joinPoint.getSignature().getName();

            logger.info("{}.{}() 方法执行时间: {}ms",
                    className, methodName, (endTime - startTime));
        }
    }
}
