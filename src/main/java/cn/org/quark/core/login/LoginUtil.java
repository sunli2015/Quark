package cn.org.quark.core.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;

import cn.org.quark.core.Constants;
/**
 * 
 * @author Leo
 *
 */
public class LoginUtil {
	public static Loginer getLoginer(HttpServletRequest request){
		Loginer loginer = (Loginer) request.getSession().getAttribute(Constants.USER_IN_SESSION);
		Assert.notNull(loginer,"Object loginer not exist!");
		return loginer;
	}
}
