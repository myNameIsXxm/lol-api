package com.es.core;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.es.exception.AuthorizationException;
import com.es.field.Config;
import com.es.field.Fields;

public class MyInterceptor extends HandlerInterceptorAdapter implements Fields,Config{
	private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);
	/**
     * 业务处理器处理之前被调用
     * 作用:前置初始化操作,权限判断
     */
	@Override
	public boolean preHandle (
		HttpServletRequest request, 
		HttpServletResponse response, 
		Object handler)
		throws ServletException, AuthorizationException {
		if(CHECK_USER){
			List<String> excludedUrls = new ArrayList<String>();
			excludedUrls.add("/out");
			excludedUrls.add("/login"); // 除了登录页和登出页,其他页面进入之前都需要验证登录
			String requestUri = request.getRequestURI();
			for (String url : excludedUrls){
				if (requestUri.endsWith(url)){
					return true;
				}
			}
			User user = (User) request.getSession().getAttribute(F_USER);
			if (user == null){
				throw new AuthorizationException();
			}
		}
		return true;
	}
	/**
     * 在业务处理完成请求后，在DispatcherServlet向客户端返回响应前被调用
     * 作用:对Controller 处理之后的ModelAndView 对象进行操作
     */
	@Override
    public void postHandle(
    	HttpServletRequest request,
        HttpServletResponse response, 
        Object handler,
        ModelAndView modelAndView) 
        throws Exception {
		//logger.info("--postHandle--");
    }
	/**
     * 在DispatcherServlet完全处理完请求后被调用
     * 作用:资源清理
     */
    @Override
    public void afterCompletion(
    	HttpServletRequest request,
        HttpServletResponse response, 
        Object handler, 
        Exception ex)
        throws Exception {
    	//logger.info("--afterCompletion--");
    }
}
