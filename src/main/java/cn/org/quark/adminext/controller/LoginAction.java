package cn.org.quark.adminext.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("mvcLoginActionExt")
@RequestMapping("/managerext")
public class LoginAction {

	@RequestMapping("/login")
	public String login() throws Exception {
		return "commonext/login";
	}
	@RequestMapping("/main")
	public String main() throws Exception{
		return "commonext/main";
	}
	
}
