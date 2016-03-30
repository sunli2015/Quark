package cn.org.quark.admin.view;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.org.quark.admin.entity.CoreResource;
import cn.org.quark.admin.manager.ResourceManager;
import cn.org.quark.core.dao.support.CriteriaSetup;
import cn.org.quark.core.dao.support.Page;
import cn.org.quark.core.utils.UtilString;
import cn.org.quark.core.web.struts2.JsonEntityAction;
import cn.org.quark.core.web.support.PageRequestUtils;


public class ResourceAction extends JsonEntityAction<CoreResource, ResourceManager>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 通过角色选择资源
	 * @return
	 */
	public String select(){
		doSelectListEntity(new CriteriaSetup(), "", Page.DEFAULT_PAGE_SIZE);
		return "select";
	}
	public String selectIndex(){
		return "selectIndex";
	}
	
	protected void doSelectListEntity(CriteriaSetup criteriaSetup, String tableId,
			int pageSize) {
//		Page _page = PageRequestUtils.getPage(getHttpServletRequest());
//		Page page = getEntityManager().pagedQuery(criteriaSetup,_page.getCurPage(),_page.getPageSize());
//		this.setEntitys(this.getEntityManager().listByRole(this.getRoleid(), page.getResult()));
//		page.setData(this.getEntitys());
//		jsonData.setPage(page.getCurrentPageNo());
//		jsonData.setTotal(page.getTotalCount());
//		jsonData.getRows().addAll(page.getResult());
	}
	
//	@Override
//	protected void doListEntity(CriteriaSetup criteriaSetup, int pageSize) {
//		if(!UtilString.isEmpty(moduId)){
//			criteriaSetup.addCriterion(Restrictions.eq("module.oid", moduId));
//		}
//		super.doListEntity(criteriaSetup, pageSize);
//	}

	private String roleid;
	private String moduId ;
	@Autowired
	private ResourceManager resourceManager;

	public ResourceManager getResourceManager() {
		return resourceManager;
	}

	public void setResourceManager(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}
	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getModuId() {
		return moduId;
	}
	public void setModuId(String moduId) {
		this.moduId = moduId;
	}
	
}
