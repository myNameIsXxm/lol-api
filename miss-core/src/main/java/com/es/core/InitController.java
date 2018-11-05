package com.es.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InitController {
	@RequestMapping("/")
	public ModelAndView init(HttpServletResponse response, HttpServletRequest result) {
		 return new ModelAndView("redirect:/main");
	}
}
