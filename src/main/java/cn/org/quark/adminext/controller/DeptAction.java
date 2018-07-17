package cn.org.quark.adminext.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.org.quark.admin.entity.CoreDept;
import cn.org.quark.admin.manager.DeptManager;
import cn.org.quark.admin.manager.UserManager;
import cn.org.quark.core.common.RtnCode;
import cn.org.quark.core.dao.support.CriteriaSetup;
import cn.org.quark.core.dao.support.Page;
import cn.org.quark.core.utils.UtilString;
import cn.org.quark.core.web.springmvc.BaseEntityAction;
import cn.org.quark.core.web.support.JqGridPage;
import cn.org.quark.core.web.support.ResultData;
import cn.org.quark.core.web.support.RtnData;
import cn.org.quark.core.web.support.RtnResult;
import cn.org.quark.core.web.support.RtnStatusResult;

/**
 * 
 * @author Leo
 *
 */
@Controller("mvcDeptActionExt")
@RequestMapping("/dept/ext")
public class DeptAction extends BaseEntityAction<CoreDept,DeptManager>{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 
	 * @param example
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public JqGridPage<CoreDept> list(CoreDept coreDept,JqGridPage<CoreDept> page ){
		JqGridPage<CoreDept> resultData = new JqGridPage<CoreDept>();
		try {
			CriteriaSetup criteriaSetup = new CriteriaSetup();
			/*if(null != CoreDept && null != CoreDept.getRcode()){
				criteriaSetup.addCriterion(Restrictions.like("rcode", CoreDept.getRcode(), MatchMode.ANYWHERE));
			}
			if(null != CoreDept && null != CoreDept.getRname()){
				criteriaSetup.addCriterion(Restrictions.like("rname", CoreDept.getRname(), MatchMode.ANYWHERE));
			}*/
			Page page1 = new Page();
			page1.setCurPage(page.getPageNo());
			page1.setPageSize(page.getPageSize());
			
			ResultData<List<CoreDept>> _resultData = super.doListEntity(criteriaSetup, page1);
			
			resultData.setPageNo(page.getPageNo());
			resultData.setCount(_resultData.getData().getTotal());
			resultData.setPageSize(page.getPageSize());
			resultData.setList(_resultData.getData().getData());
			
		} catch (Exception e) {
			resultData.setCode(RtnCode.OTHER_ERROR);
			resultData.setErrMsg(""+e.getMessage());
		}
		return resultData;
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
	
	@RequestMapping(value = "treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData(String excludeCode, String parentCode, Boolean isAll,
			String officeTypes, String companyCode, String isShowCode, String isShowFullName,
			Boolean isLoadUser, String postCode, String roleCode, String ctrlPermi) {
		try{
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			//List<CoreDept> tree = deptManager.buildTree();
			CriteriaSetup criteriaSetup = new CriteriaSetup();
			Page page1 = new Page();
			ResultData<List<CoreDept>> data = super.doListEntity(criteriaSetup, page1);
			
			List<CoreDept> list =  data.getData().getData();
			for (CoreDept coreDept:list) {
				
				Map<String, Object> map = new HashMap<String,Object>();
				map.put("id", coreDept.getOid());
				if(coreDept.getParentDept()!=null){
					map.put("pId", coreDept.getParentDept().getOid());
				}else{
					map.put("pId", null);
				}
				
				map.put("name", coreDept.getDeptname());
				map.put("title", coreDept.getDeptname());
				
				mapList.add(map);
			}
			return mapList;
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("error---"+ex.getMessage());
		}
		return null;
	}
	/**
	 * 
	 * @param example
	 * @return
	 */
	@RequestMapping("/saveit")
	@ResponseBody
	public RtnStatusResult save1(CoreDept enity){
		RtnStatusResult resultData = new RtnStatusResult();
		try {

			if(UtilString.isEmpty(enity.getOid())) enity.setOid(null);
			super.save(enity);
			
			
			resultData.setResult("true");
			resultData.setMessage("保存成功");
		} catch (Exception e) {
			resultData.setCode(RtnCode.OTHER_ERROR);
			resultData.setErrMsg(""+e.getMessage());
			logger.error("save ex",e);
		} 
		return resultData;
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/editit")
	@ResponseBody
	public RtnResult<CoreDept> edit1(String id){
		RtnResult<CoreDept> resultData = new RtnResult<CoreDept>();
		try {

			ResultData<CoreDept> result = super.edit(id);
			resultData.setCode(result.getCode());
			resultData.setData(result.getData().getData());
		} catch (Exception e) {
			resultData.setCode(RtnCode.OTHER_ERROR);
			resultData.setErrMsg(""+e.getMessage());
		} 
		return resultData;
	}
	@RequestMapping("/deleteit")
	@ResponseBody
	public RtnStatusResult delete1(String id){
		RtnStatusResult result = new RtnStatusResult();
		try {
			super.delete(id);
			result.setErrMsg("删除成功");
		} catch (Exception e) {
			result.setCode(RtnCode.OTHER_ERROR);
			result.setErrMsg(""+e.getMessage());
		} 
		return result;
	}
	@Autowired
	private DeptManager deptManager;
}
