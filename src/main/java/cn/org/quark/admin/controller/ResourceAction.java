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
import cn.org.quark.core.web.support.ResultData;
import cn.org.quark.core.web.support.RtnData;

@Controller("mvcResourceAction")
@RequestMapping("/resource")
public class ResourceAction {

	@RequestMapping("/list")
	@ResponseBody
	public ResultData list(String moduleId,Page page){
		if(page == null ) page = new Page();
		CriteriaSetup criteriaSetup = new CriteriaSetup();
		criteriaSetup.addCriterion(Restrictions.eq("module.oid", moduleId));
		Page data = resourceManager.pagedQuery(criteriaSetup, page.getCurPage(), page.getPageSize());
		
		ResultData resultData = new ResultData();
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
	@Autowired
	private ResourceManager resourceManager;
}
