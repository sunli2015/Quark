package cn.org.quark.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.org.quark.admin.entity.CoreDept;
import cn.org.quark.admin.entity.CoreModule;
import cn.org.quark.admin.manager.ModuManager;
import cn.org.quark.core.dao.support.Page;
import cn.org.quark.core.utils.UtilString;
import cn.org.quark.core.web.springmvc.BaseEntityAction;
import cn.org.quark.core.web.support.ResultData;
import cn.org.quark.core.web.support.RtnData;

@Controller("mvcModuleAction")
@RequestMapping("/module")
public class ModuleAction  extends BaseEntityAction<CoreModule,ModuManager>{

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
	@RequestMapping("/list")
	@ResponseBody
	public ResultData<List<CoreModule>> list(Page page) throws Exception{
		return super.list(page);
	}
	@RequestMapping("/tree")
	@ResponseBody
	public ResultData<CoreModule> tree(){
		List<CoreModule> tree  = moduManager.buildTree();
		RtnData rtnData = new RtnData();
		rtnData.setData(tree);
		ResultData resultData = new ResultData();
		resultData.setData(rtnData);
		return resultData;
	}
	
	/**
	 * 保存
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	@ResponseBody
	public ResultData<CoreModule> save(CoreModule entity) throws Exception{
		if(UtilString.isEmpty(entity.getOid())) entity.setOid(null);
		return super.save(entity);
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public ResultData<CoreModule> delete(String id) throws Exception{
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
	public ResultData<CoreModule> edit(String id) throws Exception{
		return super.edit(id);
	}
	@Override
	protected void resetEditEntity(CoreModule entity) {
		entity.setParentModuleOid(entity.getParentModule()==null?null:entity.getParentModule().getOid());
	}
	@Autowired
	private ModuManager moduManager;
}
