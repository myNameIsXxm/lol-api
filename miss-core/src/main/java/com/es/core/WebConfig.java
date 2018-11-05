package com.es.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.SimpleServletHandlerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
@ComponentScan(basePackages = {
	"com.es.controller",
	"com.xixi.report",
	"com.es.test",
	"com.es.core",
	"com.es.api",
	"com.mysi.service.impl",
	"com.mysi.service.face",
	"com.es.entity",
	"com.es.dao.face",
	"com.es.dao.impl"
})
public class WebConfig extends WebMvcConfigurationSupport {
	private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);
	
	/**
	 * ¬∑æ∂∆•≈‰≤Œ ˝≈‰÷√
	 */
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		logger.info("configurePathMatch");
		configurer.setUseSuffixPatternMatch(false);
	}
	
	@Bean
	public HandlerAdapter servletHandlerAdapter() {
		logger.info("HandlerAdapter");
		return new SimpleServletHandlerAdapter();
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		logger.info("LocaleChangeInterceptor");
		return new LocaleChangeInterceptor();
	}

	@Bean(name = "localeResolver")
	public CookieLocaleResolver cookieLocaleResolver() {
		logger.info("CookieLocaleResolver");
		return new CookieLocaleResolver();
	}

	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		logger.info("RequestMappingHandlerMapping");

		return super.requestMappingHandlerMapping();
	}

	@Bean
	public MyInterceptor initializingInterceptor() {
		logger.info("MyInterceptor");
		return new MyInterceptor();
	}

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		logger.info("addInterceptors start");
		registry.addInterceptor(localeChangeInterceptor());
		registry.addInterceptor(initializingInterceptor());
		logger.info("addInterceptors end");
	}
	
	@Bean  
    public HandlerMapping resourceHandlerMapping() {  
        logger.info("HandlerMapping");  
        return super.resourceHandlerMapping();  
    } 
	
	@Override  
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {  
        logger.info("addResourceHandlers");  
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");  
        registry.addResourceHandler("/plugins/**").addResourceLocations("/plugins/");
        registry.addResourceHandler("/dist/**").addResourceLocations("/dist/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/themes/**").addResourceLocations("/themes/");
        registry.addResourceHandler("/uploads/**").addResourceLocations("/uploads/");
        registry.addResourceHandler("/upload/**").addResourceLocations("/upload/"); 
        registry.addResourceHandler("/dtd/**").addResourceLocations("/dtd/");
        registry.addResourceHandler("/attached/**").addResourceLocations("/attached/");
        registry.addResourceHandler("/assets/**").addResourceLocations("/attached/");
        registry.addResourceHandler("/taglib/**").addResourceLocations("/taglib/");
        registry.addResourceHandler("/WEB-INF/taglib/**").addResourceLocations("/WEB-INF/taglib/");
    }  
	
	@Bean  
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {  
        logger.info("RequestMappingHandlerAdapter");  
        return super.requestMappingHandlerAdapter();  
    }
	
}