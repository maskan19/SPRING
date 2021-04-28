package kr.or.ddit.example.advice;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAdvice {

	private static final Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);
	
	@Pointcut("execution(* kr.or.ddit.example..service.*.*(..))")
	public void dummy() {
		
	}
	
//	@Before("execution(* kr.or.ddit.example..service.*.*(..))")
	@Before("dummy()")
	public void beforeAdvice(JoinPoint joinPoint) {
		logger.info("이게 어느 시점에 출력되는지 확인");
//		메소드 호출 조인포인트만 지원한다.?
		
		Object target = joinPoint.getTarget(); //특정 오브젝트를 지칭하면 안된다.
		String className = target.getClass().getName();				
		
		Signature signature = joinPoint.getSignature();
		String methodName   =  signature.getName();
		Object[] args = joinPoint.getArgs();
		
		logger.info("로직({}.{})을 대상으로 전달된 파라미터 : {}", className,methodName,  args);
		
	}
	
	@AfterReturning(pointcut="dummy()", returning="retValue" )
	public void afterAdvice(JoinPoint joinPoint, Object retValue) {
		Object target = joinPoint.getTarget(); //특정 오브젝트를 지칭하면 안된다.
		String className = target.getClass().getName();				
		
		Signature signature = joinPoint.getSignature();
		String methodName   =  signature.getName();
		Object[] args = joinPoint.getArgs();
		
		logger.info("{}.{}({})의 실행 결과 : {}", className, methodName, args, retValue);
		
	}
	
	@AfterThrowing(pointcut = "dummy()", throwing = "e")
	public void afterThrowingAdvice(JoinPoint joinPoint, Throwable e) {
		Object target = joinPoint.getTarget(); //특정 오브젝트를 지칭하면 안된다.
		String className = target.getClass().getName();				
		
		Signature signature = joinPoint.getSignature();
		String methodName   =  signature.getName();
		Object[] args = joinPoint.getArgs();
		logger.error(String.format("%s.%s(%s)", className, methodName, args), e);
	}
	
	@Around("dummy()")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
//		 throws Throwable 컨슈머가 가져감
		
		Object target = joinPoint.getTarget(); //특정 오브젝트를 지칭하면 안된다.
		String className = target.getClass().getName();				
		
		Signature signature = joinPoint.getSignature();
		String methodName   =  signature.getName();
		Object[] args = joinPoint.getArgs();
		
		long start =System.currentTimeMillis();
		
		Object retValue;
		try {
			retValue = joinPoint.proceed(args);
			long end = System.currentTimeMillis();
			logger.info("{}.{}({})의 실행 결과 : {}\n 소요시간 : {}ms", className, methodName, args, retValue, (end-start));
			return retValue;
			
		} catch (Throwable e) {
			logger.error(String.format("%s.%s(%s)", className, methodName, args), e);
			throw e;
		} 
	}
	
}
