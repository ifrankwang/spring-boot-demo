package com.github.ifrankwang.spring.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * @author Frank Wang
 */
public class SpelExpressionUtil {

    public static <T> T parseJoinPoint(ProceedingJoinPoint joinPoint, String expel, Class<T> clazz) {
        final ExpressionParser parser = new SpelExpressionParser();
        final EvaluationContext context = new StandardEvaluationContext();
        final Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        final String[] paramNames = new LocalVariableTableParameterNameDiscoverer().getParameterNames(method);
        final Object[] paramValues = joinPoint.getArgs();

        if (null == paramNames) {
            return null;
        }

        for (int i = 0; i < paramNames.length; i++) {
            context.setVariable(paramNames[i], paramValues[i]);
        }

        return parser.parseExpression(expel).getValue(context, clazz);
    }
}
