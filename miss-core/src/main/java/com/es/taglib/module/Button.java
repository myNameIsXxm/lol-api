package com.es.taglib.module;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class Button extends SimpleTagSupport {

	StringWriter sw = new StringWriter();
	
	private String style="";
	private String click="";
	
	public void doTag() throws JspException, IOException {
		getJspBody().invoke(sw);
		StringBuffer sb = new StringBuffer("");
		sb.append("<input type='button' value='"+sw.toString()+"'");
		sb.append(" style='"+style+"'");
		sb.append(" onclick='"+click+"'");
		sb.append("></button>");
		getJspContext().getOut().println(sb.toString());
	}

	public StringWriter getSw() {
		return sw;
	}

	public void setSw(StringWriter sw) {
		this.sw = sw;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getClick() {
		return click;
	}

	public void setClick(String click) {
		this.click = click;
	}

}