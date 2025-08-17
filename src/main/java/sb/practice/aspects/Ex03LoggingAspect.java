package sb.practice.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
//@Order(3)
public class Ex03LoggingAspect { // In your project, name it ProjectLoggingAspect

    private static final Logger log = LoggerFactory.getLogger(Ex03LoggingAspect.class);

    // Pointcut for all methods in your project package
    @Pointcut("execution(* sb.practice..*(..))")
    public void allProjectMethods() {}

    // Log method entry
    @Before("allProjectMethods()")
    public void logMethodEntry(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        log.debug("➡ Entering method: {} | args: {}", methodName, Arrays.toString(args));
    }

    // Log successful exit
    @AfterReturning(pointcut = "allProjectMethods()", returning = "result")
    public void logMethodExit(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().toLongString();
        log.debug("⬅ Method {} returned: {}", methodName, result);
    }

    // Log exceptions
    @AfterThrowing(pointcut = "allProjectMethods()", throwing = "ex")
    public void logMethodException(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().toLongString();
        log.error("✖ Method {} threw exception: {}", methodName, ex.toString());
    }
}

