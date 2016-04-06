package cn.org.quark.admin.controller;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.org.quark.admin.entity.CoreRole;
import cn.org.quark.admin.manager.RoleManager;
import cn.org.quark.core.dao.support.Page;
import cn.org.quark.core.utils.UtilString;
import cn.org.quark.core.web.springmvc.BaseEntityAction;
import cn.org.quark.core.web.support.ResultData;

@Controller("mvcRoleAction")
@RequestMapping("/role")
public class RoleAction extends BaseEntityAction<CoreRole,RoleManager>{

	@RequestMapping("/list")
	@ResponseBody
	public ResultData<List<CoreRole>> list(Page page) throws Exception{

		return super.list(page);
		
	}
	
	@RequestMapping("/index")
	public String index() {
		return "admin/role_list";
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public ResultData delete(String id) throws Exception{
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
	public ResultData<CoreRole> edit(String id) throws Exception{
		return super.edit(id);
	}


	/**
	 * 保存
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	@ResponseBody
	public ResultData<CoreRole> save(CoreRole entity) throws Exception{
		if(UtilString.isEmpty(entity.getOid())) entity.setOid(null);
		return super.save(entity);
	}

	@Autowired
	private RoleManager roleManager;
}
