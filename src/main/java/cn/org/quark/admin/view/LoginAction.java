package cn.org.quark.admin.view;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.org.quark.core.web.struts2.BaseAction;
/**
 * 
 * @author Leo
 *
 */
@Service
@Scope("prototype")
public class LoginAction extends BaseAction{

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	private String login_error;
	public String getLogin_error() {
		return login_error;
	}
	public void setLogin_error(String loginError) {
		login_error = loginError;
	}
	
}
