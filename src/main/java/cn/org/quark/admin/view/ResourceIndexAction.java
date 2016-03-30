package cn.org.quark.admin.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.org.quark.admin.entity.CoreModule;
import cn.org.quark.admin.manager.ModuManager;
import cn.org.quark.core.web.struts2.BaseAction;


public class ResourceIndexAction extends BaseAction {

	private static final long serialVersionUID = -1415788085132574622L;
	@Autowired
	private ModuManager moduManager;

	private List<CoreModule> modules;
	private String id="0";
	
	public List<CoreModule> getModules() {
		return modules;
	}
	public void setModules(List<CoreModule> modules) {
		this.modules = modules;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String execute(){
		return "SUCCESS";
	}
	public String treeXml(){
		if(id.equals("0"))
			modules = moduManager.getToplayer();
		else
			modules = moduManager.getOneLayer(id);
		return "treexml";
	}
	public String menuXml(){
		return "menuxml";
	}
	public void setModuManager(ModuManager moduManager) {
		this.moduManager = moduManager;
	}
}
