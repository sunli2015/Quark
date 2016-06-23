package cn.org.quark.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.org.quark.admin.entity.CoreResource;
import cn.org.quark.admin.entity.CoreRole;
import cn.org.quark.admin.entity.CoreUser;
import cn.org.quark.admin.manager.RoleManager;
import cn.org.quark.admin.manager.UserManager;
import cn.org.quark.core.common.RtnCode;
import cn.org.quark.core.dao.support.CriteriaSetup;
import cn.org.quark.core.dao.support.Page;
import cn.org.quark.core.exception.BizException;
import cn.org.quark.core.login.LoginUtil;
import cn.org.quark.core.login.Loginer;
import cn.org.quark.core.utils.UtilString;
import cn.org.quark.core.web.springmvc.BaseEntityAction;
import cn.org.quark.core.web.support.ResultData;

@Controller("mvcUserAction")
@RequestMapping("/user")
public class UserAction extends BaseEntityAction<CoreUser,UserManager>{
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
	@RequestMapping("/index")
	public String index() {
		return "admin/user_list";
	}
	@RequestMapping("/list")
	@ResponseBody
	public ResultData<List<CoreUser>> list(String deptId , Page page) throws Exception{
		CriteriaSetup criteriaSetup = new CriteriaSetup();
		criteriaSetup.addCriterion(Restrictions.eq("dept.oid", deptId));
		return super.doListEntity(criteriaSetup, page);
	}

	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public ResultData<CoreUser> delete(String id) throws Exception{
		return super.delete(id);
	}
	
	/**
	 * 编辑
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public ResultData<CoreUser> edit(String id) throws Exception{
		return super.edit(id);
	}
	@Override
	protected void resetEditEntity(CoreUser entity) {
		entity.setDeptOid(entity.getDept().getOid());
	}

	/**
	 * 保存
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	@ResponseBody
	public ResultData<CoreUser> save(CoreUser entity) throws Exception{
		if(UtilString.isEmpty(entity.getOid())) entity.setOid(null);
		return super.save(entity);
	}
	
	@RequestMapping("/grant")
	@ResponseBody
	public ResultData<CoreRole> grant(String userid , String roleids) throws Exception{
		Assert.hasLength(userid,"userid is empty");
		Assert.hasLength(roleids,"roleids is empty");
		userManager.saveRoles(userid, roleids.split(","));;
		return new ResultData();
	}
	
	
	@Autowired
	private UserManager userManager;
}
