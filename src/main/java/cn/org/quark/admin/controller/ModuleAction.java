package cn.org.quark.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.org.quark.admin.manager.ModuManager;

@Controller("mvcModuleAction")
@RequestMapping("/module")
public class ModuleAction {

	/*@RequestMapping("/index")
	@ResponseBody
	public ResultData<List<CoreModule>> index(){
		
		List<CoreModule> list = moduManager.createCriteria(Restrictions.isNull("parentModule")).list();
		ResultData<List<CoreModule>> resultData = new ResultData<List<CoreModule>>();
		RtnData<List<CoreModule>> rtnData= new RtnData<List<CoreModule>>();
		rtnData.setData(list);
		resultData.setData(rtnData);
		return resultData;
	}*/
	@RequestMapping("/index")
	public String index(){
		return "admin/res_index";
	}
	
	@RequestMapping("/tree")
	public ModelAndView tree(){
		String treeHtml = moduManager.buildTree();
		return new ModelAndView("admin/modu_tree","treeHtml",treeHtml);
	}
	@Autowired
	private ModuManager moduManager;
}
