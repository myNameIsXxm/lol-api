package com.es.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.es.io.StringPrintWriter;
@Deprecated
public class DefaultExceptionResolver implements HandlerExceptionResolver {
	private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionResolver.class);
	@Override
	public ModelAndView resolveException(
		HttpServletRequest request, 
		HttpServletResponse response, 
		Object handler,
		Exception ex) {
		logger.error("Catch Exception: ",ex);
        Map<String,Object> map = new HashMap<String,Object>();  
        /*StringPrintWriter strintPrintWriter = new StringPrintWriter();  
        ex.printStackTrace(strintPrintWriter);  
        map.put("errorMsg", strintPrintWriter.getString());*/ 
        map.put("errorMsg",ex.getMessage());
        return new ModelAndView("error",map); 
	}
}
