package cn.org.quark.admin.controller;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.org.quark.admin.entity.CoreModule;
import cn.org.quark.admin.entity.CoreResource;
import cn.org.quark.admin.manager.ResourceManager;
import cn.org.quark.core.dao.support.CriteriaSetup;
import cn.org.quark.core.dao.support.Page;
import cn.org.quark.core.utils.UtilString;
import cn.org.quark.core.web.support.ResultData;
import cn.org.quark.core.web.support.RtnData;

@Controller("mvcResourceAction")
@RequestMapping("/resource")
public class ResourceAction {

	@RequestMapping("/list")
	@ResponseBody
	public ResultData<List<CoreResource>> list(String moduleId,Page page){
		if(page == null ) page = new Page();
		CriteriaSetup criteriaSetup = new CriteriaSetup();
		criteriaSetup.addCriterion(Restrictions.eq("module.oid", moduleId));
		Page<CoreResource> data = resourceManager.pagedQuery(criteriaSetup, page.getCurPage(), page.getPageSize());
		
		ResultData<List<CoreResource>> resultData = new ResultData<List<CoreResource>>();
		RtnData<List<CoreResource>> rtnData= new RtnData<List<CoreResource>>();
		rtnData.setData(data.getResult());
		rtnData.setTotal(data.getTotalCount());
		rtnData.setPageSize(data.getPageSize());
		resultData.setData(rtnData);
		return resultData;
	}
	@RequestMapping("/index")
	public String index(){
		return "admin/res_list";
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ResultData delete(String id){
		Assert.hasLength(id);
		resourceManager.removeById(id);;
		ResultData resultData = new ResultData();
		return resultData;
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public ResultData edit(String id){
		Assert.hasLength(id);
		CoreResource resource = resourceManager.findUniqueBy("oid", id);
		resource.setModuleOid(resource.getModule().getOid());
		
		ResultData<CoreResource> resultData = new ResultData<CoreResource>();
		RtnData<CoreResource> rtnData= new RtnData<CoreResource>();
		rtnData.setData(resource);
		resultData.setData(rtnData);
		return resultData;
	}
	@RequestMapping("/save")
	@ResponseBody
	public ResultData save(CoreResource resource){
		if(UtilString.isEmpty(resource.getOid())) resource.setOid(null);
		resourceManager.save(resource);;
		ResultData<CoreResource> resultData = new ResultData<CoreResource>();
		return resultData;
	}
	@Autowired
	private ResourceManager resourceManager;
}
