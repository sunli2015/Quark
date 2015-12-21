package cn.org.quark.core.web.struts2;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import cn.org.quark.core.dao.EntityDao;
import cn.org.quark.core.dao.support.CriteriaSetup;
import cn.org.quark.core.dao.support.Page;
import cn.org.quark.core.exception.BizException;
import cn.org.quark.core.web.support.JsonData;
import cn.org.quark.core.web.support.PageRequestUtils;
/**
 * 
 * @author Leo
 *
 * @param <T>
 */
public class JsonEntityAction<T, M extends EntityDao<T>> extends BaseEntityAction<T,M>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String INDEX = "index";
	protected JsonData<T> jsonData = new JsonData<T>();
	public JsonData<T> getJsonData() {
		return jsonData;
	}
	public void setJsonData(JsonData<T> jsonData) {
		this.jsonData = jsonData;
	}
	
	@Override
	protected void doListEntity(CriteriaSetup criteriaSetup, int pageSize) {
		Page _page = PageRequestUtils.getPage(getHttpServletRequest());
		Page page = getEntityManager().pagedQuery(criteriaSetup,_page.getCurPage(),_page.getPageSize());
		jsonData.setPage(page.getCurrentPageNo());
		jsonData.setTotal(page.getTotalCount());
		jsonData.getRows().addAll(page.getResult());
	}
	@Override
	public String delete() {
		try {
			doDeleteEntity();
			this.setEntityId(null);
			this.setLaststate(STATE_DELETED);
//			this.addActionMessage(getText("entity.deleted"));
			jsonData.setOperResult(JsonData.OPER_SUCC);
			jsonData.setOperMsg(getText("entity.deleted"));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
//			this.addActionError(getText("entity.deleted.failed"));
			jsonData.setOperResult(JsonData.OPER_FAIL);
			jsonData.setOperMsg(getText("entity.deleted.failed"));
		}
		return SUCCESS;
	}
	@Override
	public String edit() {
		T object = null;
		if (this.getEntityId() != null) {// 修改
			object = doGetEntity();
			if (object == null) {
//				this.addActionError(getText("entity.missing"));
				jsonData.setOperResult(JsonData.OPER_FAIL);
				jsonData.setOperMsg(getText("entity.missing"));
				return SUCCESS;
			}
		} else {// 新增
			try {
				object = entityClass.newInstance();
			} catch (InstantiationException e) {
				logger.error(e);
			} catch (IllegalAccessException e) {
				logger.error(e);
			}
		}
		this.setEntity(object);
		jsonData.setOperResult(JsonData.OPER_SUCC);
		jsonData.setEntity(object);
		return SUCCESS;
	}
	@Override
	public String save() {
		boolean isNew = false;
		T object;
		try {
			// 如果是修改操作，id is not blank
			if (getEntityId() != null) {
				object = doGetEntity();
				if (object == null) {
//					this.addActionError(getText("entity.missing"));
					jsonData.setOperResult(JsonData.OPER_FAIL);
					jsonData.setOperMsg(getText("entity.missing"));
					return SUCCESS;
				}
			} else { // 否则为新增操作
				object = doNewEntity();
				isNew = true;
				PropertyUtils.setProperty(entity, idName, null);
			}
			bindEntity(entity, object);
			doSaveEntity(object);
			if (isNew) {// 为了防止在新增界面点击保存时新增多条，第二次点击保存则为修改
				bindEntity(object, entity);
				this.setLaststate(STATE_SAVEED_NEW);
				this.setEntityId(this.getEntityId());
//				this.addActionMessage(getText("entity.saved.new"));
				jsonData.setOperResult(JsonData.OPER_SUCC);
				jsonData.setOperMsg(getText("entity.saved.new"));
			} else {
				this.setLaststate(STATE_SAVEED_UPDATE);
//				this.addActionMessage(getText("entity.saved.update"));
				jsonData.setOperResult(JsonData.OPER_SUCC);
				jsonData.setOperMsg(getText("entity.saved.update"));
			}

		} catch (BizException e) {
			logger.error(e.getMessage(), e);
			String messageKey = e.getMessageKey();
			if(StringUtils.isBlank(messageKey))
				messageKey = "entity.saved.failed";
//			this.addActionError(getText(messageKey));
			jsonData.setOperResult(JsonData.OPER_FAIL);
			jsonData.setOperMsg(getText(messageKey));
		} catch (Exception e) {
			jsonData.setOperResult(JsonData.OPER_FAIL);
			jsonData.setOperMsg(getText("entity.saved.failed"));
		} 
		return SUCCESS;
	}
	/**
	 * 主页界面
	 * @return
	 */
	public String index() {
		return INDEX;
	}
}
