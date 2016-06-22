package cn.org.quark.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.org.quark.admin.entity.CoreDept;
import cn.org.quark.admin.manager.DeptManager;
import cn.org.quark.core.web.springmvc.BaseEntityAction;

@Controller("mvcDeptAction")
@RequestMapping("/dept")
public class DeptAction extends BaseEntityAction<CoreDept,DeptManager>{

	@RequestMapping("/index")
	public String index(){
		return "admin/user_index";
	}
	@RequestMapping("/tree")
	public ModelAndView tree(){
		String treeHtml = deptManager.buildTree();
		return new ModelAndView("admin/modu_tree","treeHtml",treeHtml);
	}
	@Autowired
	private DeptManager deptManager;
}
