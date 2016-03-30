package cn.org.quark.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("mvcLoginAction")
@RequestMapping("/manager")
public class LoginAction {

	@RequestMapping("/login")
	public String login() throws Exception {
		return "common/login";
	}
	@RequestMapping("/main")
	public String main() throws Exception{
		return "common/main";
	}
	
}
