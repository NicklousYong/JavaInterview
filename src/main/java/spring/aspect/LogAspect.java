package spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author lixaing
 *
 */
// 标记此类为切面
@Aspect
@Component
// 纳入 Spring 容器管理
public class LogAspect {

    /* 1. 定义切入点：匹配 com.example.service 包下所有类的所有方法*/
    @Pointcut("execution(* spring.aspect..*(..))")
    public void servicePointcut() {}

    // 2. 定义通知：环绕通知，记录方法执行时间
    // 将通知与切入点关联
    @Around("servicePointcut()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // 目标方法执行前：记录开始时间
        long startTime = System.currentTimeMillis();

        // 执行目标方法（连接点）
        Object result = joinPoint.proceed();

        // 目标方法执行后：计算并打印耗时
        long endTime = System.currentTimeMillis();
        System.out.println(
                joinPoint.getSignature().getDeclaringTypeName() + "." +
                        // 类名
                        joinPoint.getSignature().getName() + "() " +
                        // 方法名
                        "执行耗时：" + (endTime - startTime) + "ms"
        );

        return result;
    }
}
    