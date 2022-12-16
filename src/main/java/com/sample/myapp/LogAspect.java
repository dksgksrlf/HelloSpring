package com.sample.myapp;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
	@Pointcut(value="execution(* com.sample..*.sayHello(..))")
	private void helloPointcut() {}
	
	@Pointcut(value="execution(* com.sample..*.sayGoodbye(..))")
	private void goodbyePointcut() {}
	
	@Before("helloPointcut()")
	public void beforeLog(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		System.out.println("[Log: Before]메소드 이름: "+signature.getName());
	}
	@After("helloPointcut()")
	public void afterLog(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		System.out.println("[Log: After]메소드 이름: "+signature.getName());
	}
	@AfterReturning(pointcut="helloPointcut()", returning="message")
	public Object afterReturningLog(JoinPoint joinPoint, String message) {
		Signature signature = joinPoint.getSignature();
		System.out.println("[Log: AfterReturning]메소드 이름: "+signature.getName());
		System.out.println("[Log: AfterReturning]메소드 리턴 값: "+ message);
		return message;
	}
	@AfterThrowing(pointcut="helloPointcut()",throwing="exception")
	public void afterThrowingLog(JoinPoint joinPoint,Exception exception) {
		Signature signature = joinPoint.getSignature();
		System.out.println("[Log: AfterThrowing]메소드 이름: "+signature.getName());
		System.out.println("[Log: AfterThrowing]예외 발생: "+exception.getMessage());
	}
	@Around("execution(* com.sample..*.*(..))")
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable{
		Signature signature = joinPoint.getSignature();
		System.out.println("[Log: Around]before: "+signature.getName()+" time check start");
		long startTime = System.nanoTime();
		Object result =null;
		try {
			result=joinPoint.proceed();
		}catch(Exception e) {
			System.out.println("[Log: Around]Exception: "+signature.getName());
		}finally {
			System.out.println("[Log: Around]Finally: "+signature.getName());
		}
		
		long endTime=System.nanoTime();
		System.out.println("[Log: Around]After: "+signature.getName()+"time check end");
		System.out.println("[Log: Around] "+signature.getName()+"processing time is "+(endTime-startTime)+"ns");
		return result;
		
	}
}
