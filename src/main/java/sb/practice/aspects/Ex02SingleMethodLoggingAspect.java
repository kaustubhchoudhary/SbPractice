package sb.practice.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//@Aspect
//@Component
//@Order(2)
public class Ex02SingleMethodLoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(Ex02SingleMethodLoggingAspect.class);

    // 🎯 Target only the createRole method inside RoleController
    @Pointcut("execution(* sb.practice.controllers.RoleController.createRole(..))")
    public void logCreateRole() {}

    // Log method entry + parameters
    @Before("logCreateRole()")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        log.debug("➡ Entering method: {} with arguments {}", methodName, Arrays.toString(args));
    }

    // Log method exit + return value
    @AfterReturning(pointcut = "logCreateRole()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().toShortString();
        log.debug("⬅ Exiting method: {} with return value {}", methodName, result);
    }

    // Log exceptions
    @AfterThrowing(pointcut = "logCreateRole()", throwing = "ex")
    public void logMethodException(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().toLongString();
        log.error("✖ Method {} threw exception: {}", methodName, ex.toString());
    }
}