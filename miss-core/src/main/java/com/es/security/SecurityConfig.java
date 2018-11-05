package com.es.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.event.LoggerListener;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

@Configuration  
@EnableWebSecurity  
public class SecurityConfig extends WebSecurityConfigurerAdapter {  
  
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);  
  
    @Override  
    public void configure(WebSecurity web) throws Exception {  
        web.ignoring().antMatchers(
    		"/images/**", "/plugins/**","/dist/**",
    		"/js/**","/css/**","/themes/**","/uploads/**",
    		"/dtd/**","/attached/**","/assets/**");  
    }  
  
    @Override  
    protected void configure(HttpSecurity http) throws Exception {  
        // 设置拦截规则  
        http.authorizeRequests()  
	        .expressionHandler(webSecurityExpressionHandler())  
	        .and()  
	        .exceptionHandling().accessDeniedPage("/login");  
  
        // 自定义登录页面  
        http.csrf().disable().formLogin().loginPage("/login")  
	        .failureUrl("/login")  
	        .permitAll();  
  
        // 自定义注销  
        http.logout().logoutUrl("/out")
        	.logoutSuccessUrl("/login")  
            .invalidateHttpSession(true);  
  
        // session管理  
        http.sessionManagement().sessionFixation().changeSessionId()  
        	.maximumSessions(1).expiredUrl("/");  
  
        // RemeberMe  
        http.rememberMe().key("webmvc#FD637E6D9C0F1A5A67082AF56CE32485");  
  
    }  
  
    @Bean  
    public LoggerListener loggerListener() {  
        logger.info("org.springframework.security.authentication.event.LoggerListener");  
        LoggerListener loggerListener = new LoggerListener();  
        return loggerListener;  
    }  
  
    @Bean  
    public org.springframework.security.access.event.LoggerListener eventLoggerListener() {  
        logger.info("org.springframework.security.access.event.LoggerListener");  
        org.springframework.security.access.event.LoggerListener eventLoggerListener = new org.springframework.security.access.event.LoggerListener();  
        return eventLoggerListener;  
    } 
  
     
    @Bean(name = "expressionHandler")  
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {  
        logger.info("DefaultWebSecurityExpressionHandler");  
        DefaultWebSecurityExpressionHandler webSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();  
        return webSecurityExpressionHandler;  
    } 
  
     
    @Bean(name = "expressionVoter")  
    public WebExpressionVoter webExpressionVoter() {  
        logger.info("WebExpressionVoter");  
        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();  
        webExpressionVoter.setExpressionHandler(webSecurityExpressionHandler());  
        return webExpressionVoter;  
    } 
  
}
