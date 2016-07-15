package cn.org.quark.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.org.quark.admin.entity.CoreDept;
import cn.org.quark.admin.entity.CoreUser;
import cn.org.quark.admin.manager.DeptManager;
import cn.org.quark.core.dao.support.Page;
import cn.org.quark.core.utils.UtilString;
import cn.org.quark.core.web.springmvc.BaseEntityAction;
import cn.org.quark.core.web.support.ResultData;
import cn.org.quark.core.web.support.RtnData;

@Controller("mvcDeptAction")
@RequestMapping("/dept")
public class DeptAction extends BaseEntityAction<CoreDept,DeptManager>{

	@RequestMapping("/index")
	public String index(){
		return "admin/user_index";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public ResultData<List<CoreDept>> list(Page page) throws Exception{
		return super.list(page);
	}
	
	@RequestMapping("/indexTree")
	@ResponseBody
	public ResultData<CoreDept> tree(){
		List<CoreDept> tree = deptManager.buildTree();
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
	public ResultData<CoreDept> save(CoreDept entity) throws Exception{
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
	public ResultData<CoreDept> delete(String id) throws Exception{
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
	public ResultData<CoreDept> edit(String id) throws Exception{
		return super.edit(id);
	}
	@Override
	protected void resetEditEntity(CoreDept entity) {
		entity.setParentDeptOid(entity.getParentDept()==null?null:entity.getParentDept().getOid());
	}
	@Autowired
	private DeptManager deptManager;
}
