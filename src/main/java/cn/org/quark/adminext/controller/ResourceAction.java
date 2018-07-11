package cn.org.quark.adminext.controller;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.org.quark.admin.entity.CoreResource;
import cn.org.quark.admin.manager.ResourceManager;
import cn.org.quark.biz.example.model.Example;
import cn.org.quark.core.common.RtnCode;
import cn.org.quark.core.dao.support.CriteriaSetup;
import cn.org.quark.core.dao.support.Page;
import cn.org.quark.core.utils.UtilString;
import cn.org.quark.core.web.springmvc.BaseEntityAction;
import cn.org.quark.core.web.support.JqGridPage;
import cn.org.quark.core.web.support.ResultData;
import cn.org.quark.core.web.support.RtnResult;
import cn.org.quark.core.web.support.RtnStatusResult;

/**
 * 
 * @author Leo
 *
 */
@Controller("mvcResourceActionExt")
@RequestMapping("/resource/ext")
public class ResourceAction extends BaseEntityAction<CoreResource,ResourceManager>{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 
	 * @param example
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public JqGridPage<CoreResource> list(CoreResource coreResource,JqGridPage<CoreResource> page ){
		JqGridPage<CoreResource> resultData = new JqGridPage<CoreResource>();
		try {
			CriteriaSetup criteriaSetup = new CriteriaSetup();
			if(null != coreResource && null != coreResource.getRcode()){
				criteriaSetup.addCriterion(Restrictions.like("rcode", coreResource.getRcode(), MatchMode.ANYWHERE));
			}
			if(null != coreResource && null != coreResource.getRname()){
				criteriaSetup.addCriterion(Restrictions.like("rname", coreResource.getRname(), MatchMode.ANYWHERE));
			}
			Page page1 = new Page();
			page1.setCurPage(page.getPageNo());
			page1.setPageSize(page.getPageSize());
			
			ResultData<List<CoreResource>> _resultData = super.doListEntity(criteriaSetup, page1);
			
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
	/**
	 * 
	 * @param example
	 * @return
	 */
	@RequestMapping("/saveit")
	@ResponseBody
	public RtnStatusResult save1(CoreResource enity){
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
	public RtnResult<CoreResource> edit1(String id){
		RtnResult<CoreResource> resultData = new RtnResult<CoreResource>();
		try {

			ResultData<CoreResource> result = super.edit(id);
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
	private ResourceManager resourceManager;
}
