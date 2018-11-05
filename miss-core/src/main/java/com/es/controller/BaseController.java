package com.es.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class BaseController {
	
	public void doHead(HttpServletResponse response){
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		response.addHeader("Access-Control-Allow-Headers",
				"X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		response.addHeader("Access-Control-Max-Age", "1728000");
	}
	
	public void write(HttpServletResponse response, Object object) {
		try {
			response.getWriter().write(new Gson().toJson(object));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
