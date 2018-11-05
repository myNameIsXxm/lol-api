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
     * ҵ����������֮ǰ������
     * ����:ǰ�ó�ʼ������,Ȩ���ж�
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
			excludedUrls.add("/login"); // ���˵�¼ҳ�͵ǳ�ҳ,����ҳ�����֮ǰ����Ҫ��֤��¼
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
     * ��ҵ��������������DispatcherServlet��ͻ��˷�����Ӧǰ������
     * ����:��Controller ����֮���ModelAndView ������в���
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
     * ��DispatcherServlet��ȫ����������󱻵���
     * ����:��Դ����
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
