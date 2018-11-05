package com.es.aop;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Deprecated
public class ExceptionAdvice {
	Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

	@AfterThrowing(pointcut = "execution(* com.es.db.controller.*.*(..))", throwing = "e")
	public void doAfterThrowing(Exception e) {
		logger.info("Å×³öÒì³£ : "+e.getMessage());
	}

}