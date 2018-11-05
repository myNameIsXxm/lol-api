package com.es.view;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.es.core.CommConfig;


@Configuration
public class ViewConfig extends WebMvcConfigurerAdapter{
	private static final Logger logger = LoggerFactory.getLogger(ViewConfig.class);
	
	/**
	 * ViewResolver ��ͼ������:��һ���߼��ϵ���ͼ���ƽ���Ϊһ����������ͼ(View)
	 */
	@Bean
	public ViewResolver getViewResolver(ContentNegotiationManager manager){
		logger.info("ViewResolver");
		ContentNegotiatingViewResolver bean = new ContentNegotiatingViewResolver();
		bean.setContentNegotiationManager(manager);
		List<ViewResolver> viewResolvers = new ArrayList<ViewResolver>();
		viewResolvers.add(new BeanNameViewResolver());
		//viewResolvers.add(new XmlViewResolver());
		viewResolvers.add(new JsonViewResolver());
		/*
		 * InternalResourceViewResolver(�ڲ���Դ��ͼ������)��URLBasedViewResolver������,
		 * ����Controller�������������ص�ģ�����Զ���ŵ���Ӧ��request������,
		 * Ȼ��ͨ��RequestDispatcher�ڷ������˰�����forword�ض���Ŀ��URL
		 */
		InternalResourceViewResolver jspViewResolver = new InternalResourceViewResolver();
		jspViewResolver.setPrefix("/jsp/");
		jspViewResolver.setSuffix(".jsp");
		viewResolvers.add(jspViewResolver);
		bean.setViewResolvers(viewResolvers);
		return bean;
	}
	
	@Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
        	.favorParameter(false) // �رղ���֧��
    		.favorPathExtension(false) // �ر���չ��֧��
    		.ignoreAcceptHeader(true) // ���Զ�Accept Header��֧��
    		.defaultContentType(MediaType.TEXT_HTML); // Ĭ��չ����ʽ
    }
	
}
