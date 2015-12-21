package cn.org.quark.admin.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.org.quark.admin.entity.CoreRole;
import cn.org.quark.admin.manager.RoleManager;
import cn.org.quark.core.web.struts2.JsonEntityAction;
/**
 * 角色管理
 * @author Leo
 *
 */
@Service
@Scope("prototype")
public class RoleAction extends JsonEntityAction<CoreRole, RoleManager>{
	@Autowired
	private RoleManager roleManger;

	public RoleManager getRoleManger() {
		return roleManger;
	}

	public void setRoleManger(RoleManager roleManger) {
		this.roleManger = roleManger;
	}
	
}
