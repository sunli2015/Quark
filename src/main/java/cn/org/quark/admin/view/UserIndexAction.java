package cn.org.quark.admin.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.org.quark.admin.entity.CoreDept;
import cn.org.quark.admin.manager.DeptManager;
import cn.org.quark.core.web.struts2.BaseAction;
/**
 * 用户索引树
 * @author Leo
 *
 */
@Service
@Scope("prototype")
public class UserIndexAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String execute(){
		return SUCCESS;
	}
	/**
	 * 部门树
	 * @return
	 */
	public String treeXml(){
		
		if(id.equals("0"))
			depts = deptManager.getToplayer();
		else
			depts = deptManager.getOneLayer(id);
		return "treexml";
	}
	private String id = "0";
	private List<CoreDept> depts ;
	@Autowired
	private DeptManager deptManager;
	public DeptManager getDeptManager() {
		return deptManager;
	}
	public void setDeptManager(DeptManager deptManager) {
		this.deptManager = deptManager;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<CoreDept> getDepts() {
		return depts;
	}
	public void setDepts(List<CoreDept> depts) {
		this.depts = depts;
	}
	
}
