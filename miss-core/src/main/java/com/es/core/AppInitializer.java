package com.es.core;


import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import com.es.aop.AopConfig;
import com.es.convert.ConvertConfig;
import com.es.exception.ExceptionConfig;
import com.es.view.ViewConfig;

public class AppInitializer implements WebApplicationInitializer {

	private static final Logger logger = LoggerFactory.getLogger(AppInitializer.class);
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		WebApplicationContext webContext = getWebContext();
		
		logger.info("contextLoaderListener");
		ContextLoaderListener listener = new ContextLoaderListener(webContext);
		servletContext.addListener(listener);
		
		logger.info("dispatcherServlet");
		ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher", new DispatcherServlet(webContext));
		registration.setLoadOnStartup(1);
		registration.addMapping("/");
		
		/*logger.info("springSecurityFilterChain");
		DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy("springSecurityFilterChain");
		servletContext
			.addFilter("springSecurityFilterChain", delegatingFilterProxy)
			.addMappingForUrlPatterns(null, false, "/*");*/
		// 与 SpringSecurityInitializer.java的作用一致
		
		
		logger.info("characterEncodingFilter");
		FilterRegistration.Dynamic characterEncodingFilter = servletContext
			.addFilter("characterEncodingFilter", new CharacterEncodingFilter());
		characterEncodingFilter.setInitParameter("encoding", "UTF-8");
		characterEncodingFilter.setInitParameter("ForceEncoding", "true");
		characterEncodingFilter.addMappingForUrlPatterns(null, false, "/*");
		
		/*logger.info("cacheFilter");
		FilterRegistration.Dynamic cacheFilter = servletContext
			.addFilter("CacheFilter", new CacheFilter());
		cacheFilter.setInitParameter("time", "7200");
		cacheFilter.setInitParameter("scope", "session");
		cacheFilter.addMappingForUrlPatterns(null, false, "*.jsp");*/
		
	}
	
	public static WebApplicationContext getWebContext(){
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.register(
			SqlConfig.class,
			WebConfig.class,
			ExceptionConfig.class,
			AopConfig.class,
			CommConfig.class,
			ViewConfig.class,
			//SecurityConfig.class,
			ConvertConfig.class
		);
		return webContext;
	}
	
	/*public static WebApplicationContext getSqlContext(){
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.register(
			SqlConfig.class
		);
		return webContext;
	}
	
	public static WebApplicationContext getElseContext(){
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.register(
			WebConfig.class,
			ExceptionConfig.class,
			AopConfig.class,
			CommConfig.class,
			ViewConfig.class,
			//SecurityConfig.class,
			ConvertConfig.class
		);
		return webContext;
	}*/
	
}