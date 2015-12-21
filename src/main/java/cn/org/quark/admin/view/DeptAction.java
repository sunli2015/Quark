package cn.org.quark.admin.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.org.quark.admin.entity.CoreDept;
import cn.org.quark.admin.manager.DeptManager;
import cn.org.quark.core.web.struts2.BaseEntityAction;
@Service
@Scope("prototype")
public class DeptAction extends BaseEntityAction<CoreDept, DeptManager>{
	@Autowired
	private DeptManager deptManager;
	private String poid;
	public DeptManager getDeptManager() {
		return deptManager;
	}

	public void setDeptManager(DeptManager deptManager) {
		this.deptManager = deptManager;
	}

	public String getPoid() {
		return poid;
	}

	public void setPoid(String poid) {
		this.poid = poid;
	}
	
}
