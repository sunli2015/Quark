package cn.org.quark.adminext.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.org.quark.admin.entity.CoreDept;
import cn.org.quark.admin.entity.CoreRole;
import cn.org.quark.admin.entity.CoreUser;
import cn.org.quark.admin.manager.UserManager;
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
@Controller("mvcUserActionExt")
@RequestMapping("/user/ext")
public class UserAction extends BaseEntityAction<CoreUser,UserManager>{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 
	 * @param example
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public JqGridPage<CoreUser> list(CoreUser coreUser,JqGridPage<CoreUser> page ){
		JqGridPage<CoreUser> resultData = new JqGridPage<CoreUser>();
		try {
			CriteriaSetup criteriaSetup = new CriteriaSetup();
			if(null != coreUser && null != coreUser.getDept()&&coreUser.getDept().getOid()!=null&&!coreUser.getDept().getOid().equals("")){
				 criteriaSetup.addCriterion(Restrictions.eq( "dept", coreUser.getDept() ));
			}
			if(null != coreUser &&!coreUser.getLogid().equals("") && null != coreUser.getLogid()){
				criteriaSetup.addCriterion(Restrictions.like("logid", coreUser.getLogid(),MatchMode.ANYWHERE));
			}
			if(null != coreUser &&!coreUser.getCname().equals("") && null != coreUser.getCname()){
				criteriaSetup.addCriterion(Restrictions.like("cname", coreUser.getCname(),MatchMode.ANYWHERE));
			}
			if(null != coreUser &&!coreUser.getEmail().equals("") && null != coreUser.getEmail()){
				criteriaSetup.addCriterion(Restrictions.like("email", coreUser.getEmail(),MatchMode.ANYWHERE));
			}
			if(null != coreUser &&!coreUser.getMobile().equals("") && null != coreUser.getMobile()){
				criteriaSetup.addCriterion(Restrictions.like("mobile", coreUser.getMobile(),MatchMode.ANYWHERE));
			}
			
			Page page1 = new Page();
			page1.setCurPage(page.getPageNo());
			page1.setPageSize(page.getPageSize());
			
			ResultData<List<CoreUser>> _resultData = super.doListEntity(criteriaSetup, page1);
			
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
	public RtnStatusResult save1(CoreUser enity){
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
	public RtnResult<CoreUser> edit1(String id){
		RtnResult<CoreUser> resultData = new RtnResult<CoreUser>();
		try {

			ResultData<CoreUser> result = super.edit(id);
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
	
	@RequestMapping("/grant")
	@ResponseBody
	public ResultData<CoreRole> grant(String userid , String roleids) throws Exception{
		Assert.hasLength(userid,"userid is empty");
		Assert.hasLength(roleids,"roleids is empty");
		userManager.saveRoles(userid, roleids.split(","));;
		return new ResultData();
	}
	@RequestMapping("/modifystatus")
	@ResponseBody
	public RtnStatusResult modifystatus(String oid , String status) throws Exception{
		RtnStatusResult result = new RtnStatusResult();
		try{
			super.getEntityManager().changeStatus(oid, status);
			result.setMessage("操作成功");
		}catch (Exception e) {
			result.setCode(RtnCode.OTHER_ERROR);
			result.setErrMsg(""+e.getMessage());
		} 
		
		//ResultData resultData = new ResultData();
		return result;
	}
	@RequestMapping("/disable")
	@ResponseBody
	public RtnStatusResult disable(String oid , String status) throws Exception{
		RtnStatusResult result = new RtnStatusResult();
		status = "0";
		try{
			super.getEntityManager().changeStatus(oid, status);
			result.setMessage("操作成功");
		}catch (Exception e) {
			result.setCode(RtnCode.OTHER_ERROR);
			result.setErrMsg(""+e.getMessage());
		} 
		
		//ResultData resultData = new ResultData();
		return result;
	}
	@RequestMapping("/enable")
	@ResponseBody
	public RtnStatusResult enable(String oid , String status) throws Exception{
		RtnStatusResult result = new RtnStatusResult();
		status = "1";
		try{
			super.getEntityManager().changeStatus(oid, status);
			result.setMessage("操作成功");
		}catch (Exception e) {
			result.setCode(RtnCode.OTHER_ERROR);
			result.setErrMsg(""+e.getMessage());
		} 
		
		//ResultData resultData = new ResultData();
		return result;
	}
	
	@Autowired
	private UserManager userManager;
}
