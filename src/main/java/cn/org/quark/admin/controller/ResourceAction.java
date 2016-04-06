package cn.org.quark.admin.controller;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.org.quark.admin.entity.CoreResource;
import cn.org.quark.admin.manager.ResourceManager;
import cn.org.quark.core.dao.support.CriteriaSetup;
import cn.org.quark.core.dao.support.Page;
import cn.org.quark.core.utils.UtilString;
import cn.org.quark.core.web.springmvc.BaseEntityAction;
import cn.org.quark.core.web.support.ResultData;

@Controller("mvcResourceAction")
@RequestMapping("/resource")
public class ResourceAction extends BaseEntityAction<CoreResource,ResourceManager>{

	@RequestMapping("/list")
	@ResponseBody
	public ResultData<List<CoreResource>> list(String moduleId,Page page) throws Exception{
		CriteriaSetup criteriaSetup = new CriteriaSetup();
		criteriaSetup.addCriterion(Restrictions.eq("module.oid", moduleId));
		return super.doListEntity(criteriaSetup, page);
		
	}
	
	@RequestMapping("/index")
	public String index() {
		return "admin/res_list";
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
	public ResultData<CoreResource> edit(String id) throws Exception{
		return super.edit(id);
	}
	
	@Override
	protected void resetEditEntity(CoreResource entity) {
		entity.setModuleOid(entity.getModule().getOid());
	}

	/**
	 * 保存
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	@ResponseBody
	public ResultData<CoreResource> save(CoreResource entity) throws Exception{
		if(UtilString.isEmpty(entity.getOid())) entity.setOid(null);
		return super.save(entity);
	}

	@Autowired
	private ResourceManager resourceManager;
}
