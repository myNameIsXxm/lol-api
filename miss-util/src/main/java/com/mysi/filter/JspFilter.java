package com.mysi.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JspFilter implements Filter {
	@Override
	public void doFilter(ServletRequest rq, ServletResponse rs, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) rq;
		HttpServletResponse response = (HttpServletResponse) rs;
		String uri = request.getRequestURI();
		if (checkEnd(uri,AllowableJsp.getAuthorised())) {
			filterChain.doFilter(request, response);
		} else {
			Object o = request.getSession().getAttribute("username");
			if (o == null || o.toString().equals("")) {
				response.sendRedirect("/error/noAccessPermission");
			} else {
				filterChain.doFilter(request, response);
			}
		}
	}

	private boolean checkEnd(String uri, List<String> authorised) {
		for(String html:authorised){
			if(uri.endsWith(html)){
				return true;
			}
		}
		return false;
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
