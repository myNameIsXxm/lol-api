package com.es.exception;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

@Configuration
@EnableWebMvc
public class ExceptionConfig {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionConfig.class);

	@Bean
	public AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor(){
		logger.info("AutowiredAnnotationBeanPostProcessor");
		return new AutowiredAnnotationBeanPostProcessor();
	}
	
	@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
		logger.info("SimpleMappingExceptionResolver");
		SimpleMappingExceptionResolver bean = new SimpleMappingExceptionResolver();
		bean.setDefaultErrorView("error/error");
		bean.setExceptionAttribute("exception");
		Properties mappings = new Properties();
		mappings.put("com.es.exception.AuthorizationException", "/login");
		bean.setExceptionMappings(mappings);
		return bean;
	}
}
