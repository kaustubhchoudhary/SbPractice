package sb.practice.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//@Aspect
//@Component
//@Order(1)
public class Ex01SimpleLoggingAspect {

    // Pointcut for exactly one method in RoleController
    @Pointcut("execution(* sb.practice.controllers.RoleController.createRole(..))")
    public void logCreateRole() {}

    @Before("logCreateRole()")
    public void logBeforeCreateRole(JoinPoint joinPoint) {

        String methodName = joinPoint.getSignature().getName();
        String joinPtShortName = joinPoint.toShortString();
        String joinPtLongName = joinPoint.toLongString();
        Object[] args = joinPoint.getArgs();

        System.out.println("==> joinPtShortName: " + joinPtShortName);
        System.out.println("==> joinPtLongName: " + joinPtLongName);
        System.out.println("==> Method called: " + methodName);
        System.out.println("==> Arguments: " + Arrays.toString(args));
    }

    @AfterReturning(pointcut = "logCreateRole()", returning = "result")
    public void logAfterCreateRole(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().toShortString();
        System.out.println("==> Method completed: " + methodName);
        System.out.println("==> Returned: " + result);
    }
}