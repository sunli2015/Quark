package cn.org.quark.admin.view;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.org.quark.admin.entity.CoreUser;
import cn.org.quark.admin.manager.UserManager;
import cn.org.quark.core.dao.support.CriteriaSetup;
import cn.org.quark.core.utils.UtilString;
import cn.org.quark.core.web.struts2.JsonEntityAction;

/**
 * 用户管理ACTION
 * @author Leo
 * 
 */

public class UserAction extends JsonEntityAction<CoreUser, UserManager>{
	@Autowired
	private UserManager userManager;
	public UserManager getUserManager() {
		return userManager;
	}
//	@Override
//	protected void doListEntity(CriteriaSetup criteriaSetup, int pageSize) {
//
//		if(!UtilString.isEmpty(deptId)){
//			criteriaSetup.addCriterion(Restrictions.eq("dept.oid", deptId));
//		}
//		if(!UtilString.isEmpty(entity.getLogid()))
//			criteriaSetup.addCriterion(Restrictions.like("logid", entity.getLogid(),MatchMode.ANYWHERE));
//		if(!UtilString.isEmpty(entity.getCname()))
//			criteriaSetup.addCriterion(Restrictions.like("cname", entity.getCname(),MatchMode.ANYWHERE));
//		if(!UtilString.isEmpty(entity.getIdno()))
//			criteriaSetup.addCriterion(Restrictions.like("idno", entity.getIdno(),MatchMode.ANYWHERE));
//		if(entity.getStatus() != null)
//			criteriaSetup.addCriterion(Restrictions.eq("status", entity.getStatus()));
//		super.doListEntity(criteriaSetup, pageSize);
//	}
	private String deptId;
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
}
