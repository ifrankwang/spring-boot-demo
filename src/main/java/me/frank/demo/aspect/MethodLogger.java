package me.frank.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 切面Logger类，在方法执行前后log方法名和参数
 * @author 王明哲
 */
@Aspect
@Component
public class MethodLogger {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 在Controller和Service方法执行前log方法名和参数
     *
     * @param joinPoint 切入点
     */
    @Before("execution(* me.frank.demo..*.*Controller.*(..)) || execution(* me.frank.demo.service..*.*(..))")
    public void logMethodBefore(JoinPoint joinPoint) {
        logger.info("\n开始执行方法: {}", getMethodStr(joinPoint));
    }

    /**
     * 在Controller和Service方法执行后log方法名和参数
     *
     * @param joinPoint 切入点
     */
    @After("execution(* me.frank.demo..*.*Controller.*(..)) || execution(* me.frank.demo.service..*.*(..))")
    public void logMethodAfter(JoinPoint joinPoint) {
        logger.info("\n结束执行方法: {}", getMethodStr(joinPoint));
    }

    /**
     * 获取方法名称和参数
     *
     * @param joinPoint 切入点
     * @return 方法名称和参数的字符串，格式为：包名.方法名
     */
    private String getMethodStr(JoinPoint joinPoint) {
        return String.format(
                "%s.%s",
                joinPoint.getTarget().getClass().getName(),
                joinPoint.getSignature().getName());
    }
}
