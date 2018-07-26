package cn.org.quark.adminext.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.org.quark.admin.entity.CoreResource;
import cn.org.quark.admin.entity.CoreRole;
import cn.org.quark.admin.manager.ResourceManager;
import cn.org.quark.admin.manager.RoleManager;
import cn.org.quark.core.common.RtnCode;
import cn.org.quark.core.common.ZtreeData;
import cn.org.quark.core.dao.support.CriteriaSetup;
import cn.org.quark.core.dao.support.Page;
import cn.org.quark.core.utils.UtilString;
import cn.org.quark.core.web.springmvc.BaseEntityAction;
import cn.org.quark.core.web.support.JqGridPage;
import cn.org.quark.core.web.support.ResultData;
import cn.org.quark.core.web.support.RtnResult;
import cn.org.quark.core.web.support.RtnStatusResult;

@Controller("mvcRoleActionActionExt")
@RequestMapping("/role/ext")
public class RoleAction extends BaseEntityAction<CoreRole,RoleManager>{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RoleManager roleManager;
	/**
	 * 
	 * @param example
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public JqGridPage<CoreRole> list(CoreRole coreRole,JqGridPage<CoreRole> page ){
		JqGridPage<CoreRole> resultData = new JqGridPage<CoreRole>();
		try {
			CriteriaSetup criteriaSetup = new CriteriaSetup();
			if(null != coreRole && null != coreRole.getRolecode()){
				criteriaSetup.addCriterion(Restrictions.like("rolecode", coreRole.getRolecode().substring(5), MatchMode.ANYWHERE));
			}
			if(null != coreRole && null != coreRole.getRolename()){
				criteriaSetup.addCriterion(Restrictions.like("rolename", coreRole.getRolename(), MatchMode.ANYWHERE));
			}
			Page page1 = new Page();
			page1.setCurPage(page.getPageNo());
			page1.setPageSize(page.getPageSize());
			
			ResultData<List<CoreRole>> _resultData = super.doListEntity(criteriaSetup, page1);
			
			resultData.setPageNo(page.getPageNo());
			resultData.setCount(_resultData.getData().getTotal());
			resultData.setPageSize(page.getPageSize());
			resultData.setList(_resultData.getData().getData());
			
		} catch (Exception e) {
			e.printStackTrace();
			resultData.setCode(RtnCode.OTHER_ERROR);
			resultData.setErrMsg(""+e.getMessage());
		}
		return resultData;
	}
	@RequestMapping("/listByUserId")
	@ResponseBody
	public List<CoreRole> list(String userid) throws Exception{
		JqGridPage<CoreRole> resultData = new JqGridPage<CoreRole>();
		Page<CoreRole> data = super.getEntityManager().queryByUserId(userid, 1, Page.MAX_PAGE_SIZE);
		//resultData.setPageNo(page.getPageNo());
		//resultData.setCount(data.getData().getTotal());
		//resultData.setPageSize(page.getPageSize());
		resultData.setList(data.getResult());
		return data.getResult();
	}
	/**
	 * 
	 * @param example
	 * @return
	 */
	@RequestMapping("/saveit")
	@ResponseBody
	public RtnStatusResult save1(CoreRole enity){
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
	public RtnResult<CoreRole> edit1(String id){
		RtnResult<CoreRole> resultData = new RtnResult<CoreRole>();
		try {

			ResultData<CoreRole> result = super.edit(id);
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
			result.setMessage("删除成功");
		} catch (Exception e) {
			result.setCode(RtnCode.OTHER_ERROR);
			result.setErrMsg(""+e.getMessage());
		} 
		return result;
	}
	
	/**
	 * 
	 * @param roleid
	 * @param resourceIds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/grant")
	@ResponseBody
	public RtnResult<CoreRole> grant(String roleid , String resourceIds) throws Exception{
		RtnResult<CoreRole> resultData = new RtnResult<CoreRole>();
		
		try {
			Assert.hasLength(roleid,"roleid is empty");
			Assert.hasLength(resourceIds,"resourceIds is empty");
			roleManager.changeRes(roleid, resourceIds.split(","));
			//ResultData<CoreRole> result = super.edit(id);
			resultData.setCode(RtnCode.SUCC);
			resultData.setMessage("操作成功！");
		} catch (Exception e) {
			resultData.setCode(RtnCode.OTHER_ERROR);
			resultData.setErrMsg(""+e.getMessage());
		} 
		return resultData;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/tree")
	@ResponseBody
	public List<ZtreeData> tree(String roleid){
		List<ZtreeData> treeData = new ArrayList<ZtreeData>();
		try {
			List<CoreResource> resources = resourceManager.createCriteria(Restrictions.isNull("parent")).list();
			List<CoreResource> resourcesSelected = new ArrayList<CoreResource>();
			//resourcesSelected = resourceManager.listByRole(roleid, resourcesSelected);
			rebuildTree(treeData,resources,roleid);
		} catch (Exception e) {
			
		} 
		return treeData;
	}
	
	
	private void rebuildTree(List<ZtreeData> treeData ,List<CoreResource> resources,String roleid){
		for(CoreResource res : resources){
			ZtreeData d = new ZtreeData();
			d.setId(res.getOid());
			d.setName(res.getRname());
			if(res.getParent() == null){
				d.setpId("0");
			} else {
				d.setpId(res.getParent().getOid());
			}
			
			/*if(resourcesSelected!=null&&resourcesSelected.size()>0){
				for(CoreResource resSelected : resourcesSelected){
					if(resSelected.getOid().equals(res.getOid())){
						d.setSelected(true);
						break;
					}
				}
			}*/
			if(res.getRoles()!=null){
				for (CoreRole cr : res.getRoles()) {  
					if(cr.getOid().equals(roleid)){
						d.setSelected(true);
						break;
					}
				}  
			}
			if(d.getSelected()==null){
				d.setSelected(false);
			}
			treeData.add(d);
			if(!res.getSubs().isEmpty()){
				
				rebuildTree(treeData,new ArrayList<CoreResource>(res.getSubs()),roleid);
			}
		}
	}
	@Autowired
	private ResourceManager resourceManager;


}
