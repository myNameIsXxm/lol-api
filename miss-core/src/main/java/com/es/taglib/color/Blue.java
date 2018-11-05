package com.es.taglib.color;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class Blue extends SimpleTagSupport {

	StringWriter sw = new StringWriter();
	
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		getJspBody().invoke(sw);
		out.println("<font color='blue'>"+sw.toString()+"</font>");
	}

	public StringWriter getSw() {
		return sw;
	}

	public void setSw(StringWriter sw) {
		this.sw = sw;
	}
}