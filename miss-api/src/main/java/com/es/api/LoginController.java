package com.es.api;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.es.controller.BaseController;
import com.es.entity.User;
import com.mysi.service.face.UserServiceFace;

@Controller
@RequestMapping(value = "/index")
public class LoginController extends BaseController {

	@Inject
	private UserServiceFace userService;
	
	@RequestMapping("/register/{name}/{pass}/$")
	public @ResponseBody String register(HttpServletResponse response, @PathVariable String name,
			@PathVariable String pass) {
		doHead(response);
		int num = userService.save(name,pass);
		if(num==1){
			return "success";
		}else{
			return "the user name already exists";
		}
	}
	
	@RequestMapping("/login/{name}/{pass}/$")
	public @ResponseBody String login(HttpServletResponse response, @PathVariable String name,
			@PathVariable String pass) {
		doHead(response);
		User user = userService.find(name,pass);
		if(user==null){
			return "ERROR:Incorrect username or password";
		}else{
			return "success";
		}
		
	}
}
