package com.es.convert;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.CharSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.es.view.ViewConfig;
@Configuration
public class ConvertConfig extends WebMvcConfigurationSupport{
	private static final Logger logger = LoggerFactory.getLogger(ConvertConfig.class);
	/**
	 * HttpMessageConverter 消息转换器 , 就是@RequestBody和@ResponseBody2个注解的的实现
	 */
	@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		logger.info("configureMessageConverters start");
		converters.add(fastJsonHttpMessageConverter());
		//converters.add(mappingJackson2HttpMessageConverter());
		logger.info("configureMessageConverters end");
        super.configureMessageConverters(converters);
    }
	
	/*@Bean(name="mappingJackson2HttpMessageConverter")
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
		logger.info("MappingJackson2HttpMessageConverter");
		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
		supportedMediaTypes.add(MediaType.TEXT_HTML);
		
		MappingJackson2HttpMessageConverter bean = new MappingJackson2HttpMessageConverter();
		bean.setSupportedMediaTypes(supportedMediaTypes);
		return bean;
	}*/
	
	@Bean(name="fastJsonHttpMessageConverter")
	public FastJsonHttpMessageConverter fastJsonHttpMessageConverter(){
		logger.info("FastJsonHttpMessageConverter");
		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
		supportedMediaTypes.add(MediaType.APPLICATION_JSON);
		supportedMediaTypes.add(MediaType.TEXT_HTML);
		
		FastJsonHttpMessageConverter bean = new FastJsonHttpMessageConverter();
		bean.setSupportedMediaTypes(supportedMediaTypes);
		bean.setFeatures(SerializerFeature.QuoteFieldNames,SerializerFeature.WriteDateUseDateFormat);
		bean.setCharset(Charset.forName("UTF-8"));
		return bean;
	}
}
