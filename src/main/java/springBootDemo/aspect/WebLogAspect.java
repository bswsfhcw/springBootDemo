package springBootDemo.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 使用 @Component，@Aspect 标记到切面类上：
 * 
 * @author bswsfhcw
 *
 */
/*影响websocket,暂未查出原因*/
//@Aspect
@Order(-99) // 控制多个Aspect的执行顺序，越小越先执行
@Component
public class WebLogAspect {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * springBootDemo..*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        LOGGER.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        LOGGER.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
    	LOGGER.info("doAfterReturning : " + ret);
    }
    @Around("execution(public * springBootDemo..*.*(..))")
    public Object method(ProceedingJoinPoint pjp) throws Throwable {

    	LOGGER.info("=====Aspect处理=======");
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("参数为:" + arg);
        }
        long start = System.currentTimeMillis();
        Object object = pjp.proceed();
        LOGGER.info("Aspect 耗时:" + (System.currentTimeMillis() - start));
        return object;
    }
}