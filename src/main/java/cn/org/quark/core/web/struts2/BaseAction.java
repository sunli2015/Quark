package cn.org.quark.core.web.struts2;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import cn.org.quark.core.login.LoginUtil;
import cn.org.quark.core.login.Loginer;


/**
 * 最基础的一个Controller，供继承
 * 
 */
abstract public class BaseAction {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 登录者信息
	 * @return
	 */
	protected Loginer getLoginer(HttpServletRequest request){
		Loginer loginer = LoginUtil.getLoginer(request);
		Assert.notNull(loginer,"Object loginer not exist!");
		return loginer;
	}
}
