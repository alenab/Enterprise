package ua.goit.calculate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

public class LogAspect {

    private final Logger logger = LogManager.getLogger(this);

    public Object login(ProceedingJoinPoint pjp) throws Throwable {
        Object result;
        try {
            result = pjp.proceed();
            logger.info("[{}] {}({}): {}", pjp.getTarget().getClass().getSimpleName(),
                    pjp.getSignature().getName(), pjp.getArgs(), result);
            return result;
        } catch (IllegalStateException e) {
            logger.error("It was called: {} ({}) {}", pjp.getSignature().getName(), pjp.getArgs(), e.getMessage());
            throw e;
        }
    }

}
