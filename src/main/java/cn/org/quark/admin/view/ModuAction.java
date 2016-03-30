package cn.org.quark.admin.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.org.quark.admin.entity.CoreModule;
import cn.org.quark.admin.manager.ModuManager;
import cn.org.quark.core.web.struts2.BaseEntityAction;

public class ModuAction extends BaseEntityAction<CoreModule, ModuManager> {

	private static final long serialVersionUID = -3122220426821374096L;

	// 注入manager
	@Autowired
	private ModuManager moduManager;

	private String poid;
	
	public String getPoid() {
		return poid;
	}

	public void setPoid(String poid) {
		this.poid = poid;
	}
	public void setModuManager(ModuManager moduManager) {
		this.moduManager = moduManager;
	}

	public ModuManager getModuManager() {
		return moduManager;
	}
	
}
