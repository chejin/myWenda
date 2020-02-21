package com.newcoder.myWenda.aspect;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Component
@Aspect
public class LogAspect {
	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
	//返回值+包+类+方法+参数
	@Before("execution(* com.newcoder.myWenda.controller.*Controller.*(..))")
	public void beforeMethod(JoinPoint joinPoint) {
	    StringBuilder sb = new StringBuilder();
	    for (Object arg : joinPoint.getArgs()) {
	    	if(arg != null) {
	    		sb.append("arg:" + arg.toString() + "|");
	    	}
	    }
	    logger.info("before method:" + sb.toString());
	}
	
	@After("execution(* com.newcoder.myWenda.controller.IndexController.*(..))")
    public void afterMethod() {
        logger.info("after method" + new Date());
    }
}
