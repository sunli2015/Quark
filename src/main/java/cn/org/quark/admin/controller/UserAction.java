package cn.org.quark.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.org.quark.admin.manager.UserManager;
import cn.org.quark.core.common.RtnCode;
import cn.org.quark.core.exception.BizException;
import cn.org.quark.core.login.LoginUtil;
import cn.org.quark.core.login.Loginer;
import cn.org.quark.core.web.support.ResultData;

@Controller("mvcUserAction")
@RequestMapping("/user")
public class UserAction {
	private static Logger logger = LoggerFactory.getLogger(UserAction.class);
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/editpwd")
	public String editPwd(){
		return "admin/pwd_edit";
	}
	/**
	 * 修改密码
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	@RequestMapping("/modifypwd")
	@ResponseBody
	public ResultData modifyPwd(String oldPwd , String newPwd,HttpServletRequest request){
		Loginer loginer = LoginUtil.getLoginer(request);
		logger.debug("==>loginid:"+loginer.getLoginid()+",oldPwd:"+oldPwd+",newPwd:"+newPwd);
		String id = loginer.getUserid();
		if(!userManager.isRightPwd(id,oldPwd)){
			throw new BizException(RtnCode.PWD_OLDPWD_INCORRECT);
		}
		userManager.changePwd(id, newPwd);
		ResultData resultData = new ResultData();
		return resultData;
	}
	@Autowired
	private UserManager userManager;
}
