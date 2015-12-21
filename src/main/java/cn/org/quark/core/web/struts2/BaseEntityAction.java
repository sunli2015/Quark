package cn.org.quark.core.web.struts2;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import cn.org.quark.core.dao.EntityDao;
import cn.org.quark.core.dao.support.CriteriaSetup;
import cn.org.quark.core.dao.support.Page;
import cn.org.quark.core.exception.BizException;
import cn.org.quark.core.utils.BeanUtils;
import cn.org.quark.core.utils.GenericsUtils;
import cn.org.quark.core.web.support.PageRequestUtils;

import com.opensymphony.xwork2.Preparable;

/**
 * 无流程的单对象相关的基类，供继承
 * 
 */
@SuppressWarnings("unchecked")
abstract public class BaseEntityAction<T, M extends EntityDao<T>> extends
		BaseAction implements Preparable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static final String DELETED = "deleted";
	
	/**
	 * 操作状态:已新增
	 */
	protected static final String STATE_SAVEED_NEW = "saved.new";
	
	/**
	 * 操作状态:已修改
	 */
	protected static final String STATE_SAVEED_UPDATE = "saved.update";
	
	/**
	 * 操作状态:已删除
	 */
	protected static final String STATE_DELETED = "deleted";
	
	private M entityManager; // Action管理Entity所用的manager.

	protected Class<T> entityClass; // Action所管理的Entity类型.

	protected Class idClass; // Action所管理的Entity的主键类型.

	protected String idName; // Action所管理的Entity的主键名.
	/**
	 * 操作后的状态
	 */
	protected String laststate;
	

	/**
	 * 取得entityClass的函数. JDK1.4不支持泛型的子类可以抛开Class<T> entityClass,重载此函数达到相同效果。
	 */
	protected Class<T> getEntityClass() {
		return entityClass;
	}

	/**
	 * 获得EntityManager类进行CRUD操作,可以在子类重载.
	 */
	protected M getEntityManager() {
		Assert.notNull(entityManager, "Manager未能成功初始化");
		return entityManager;
	}

	/**
	 * 业务对象
	 */
	protected T entity;

	protected List<T> entitys;

	public List<T> getEntitys() {
		return entitys;
	}

	public void setEntitys(List<T> entitys) {
		this.entitys = entitys;
	}

	public T getEntity() {
		return this.entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	/**
	 * 从request中获得Entity的id，并判断其有效性.
	 */
	protected Serializable getEntityId() {
		String idString = getHttpServletRequest().getParameter(idName);
		if (StringUtils.isEmpty(idString)) {
			return null;
		}
		try {
			return (Serializable) ConvertUtils.convert(idString, idClass);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Wrong when get id from request");
		}
	}

	/**
	 * 设置request中Entity的id.
	 */
	protected void setEntityId(Serializable id) {
		getHttpServletRequest().setAttribute(idName, id);
	}

	public void prepare() throws Exception{
		// 根据T,反射获得entityClass
		entityClass = GenericsUtils.getSuperClassGenricType(getClass());

		// 根据M,反射获得符合M类型的manager
		Class managerClass = GenericsUtils.getSuperClassGenricType(getClass(), 1);
		List<Field> fields = BeanUtils.getFieldsByType(this,managerClass);
		Assert.isTrue(fields.size() == 1,
				"subclass's has not only one entity manager property.");
		try {
			entityManager = (M) BeanUtils.getProperty(this, fields.get(0).getName());
			Assert.notNull(entityManager,
					"subclass not inject manager to action sucessful.");
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}

		// 反射获得entity的主键类型
		try {
			idName = entityManager.getIdName(entityClass);
			idClass = BeanUtils.getPropertyType(entityClass,idName);
			entity = entityClass.newInstance();
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}
	}

	/**
	 * 删除用的action
	 */
	public String delete() {
		try {
			doDeleteEntity();
			this.setEntityId(null);
			this.setLaststate(STATE_DELETED);
			this.addActionMessage(getText("entity.deleted"));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			this.addActionError(getText("entity.deleted.failed"));
		}
		return DELETED;
	}

	/**
	 * 编辑业务对象的Action函数.
	 */
	public String edit() {
		T object = null;
		if (this.getEntityId() != null) {// 修改
			object = doGetEntity();
			if (object == null) {
				this.addActionError(getText("entity.missing"));
				return list();
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
		return INPUT;
	}

	/**
	 * 保存使用的action
	 */
	public String save() {
		boolean isNew = false;
		T object;
		try {
			// 如果是修改操作，id is not blank
			if (getEntityId() != null) {
				object = doGetEntity();
				if (object == null) {
					this.addActionError(getText("entity.missing"));
					return list();
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
				this.addActionMessage(getText("entity.saved.new"));
			} else {
				this.setLaststate(STATE_SAVEED_UPDATE);
				this.addActionMessage(getText("entity.saved.update"));
			}

		} catch (BizException e) {
			logger.error(e.getMessage(), e);
			String messageKey = e.getMessageKey();
			if(StringUtils.isBlank(messageKey))
				messageKey = "entity.saved.failed";
			this.addActionError(getText(messageKey));
		} catch (Exception e) {
			
		} 
		return INPUT;
	}

	/**
	 * 默认首页，列表页面
	 * 
	 * @return
	 */
	public String execute() {
		return list();
	}

	/**
	 * 列表使用的action
	 */
	public String list() {
		doListEntity(new CriteriaSetup(), Page.DEFAULT_PAGE_SIZE);
		return SUCCESS;
	}

	/**
	 * 获取业务对象列表的函数.
	 */
	protected void doListEntity(CriteriaSetup criteriaSetup, int pageSize) {
		Page _page = PageRequestUtils.getPage(getHttpServletRequest());
		Page page = getEntityManager().pagedQuery(criteriaSetup,_page.getCurPage(),_page.getPageSize());
		this.setEntitys(page.getResult());
		
	}
/*	*//**
	 * 获取业务对象列表的函数.
	 *//*
	protected void doListEntityByRatio(CriteriaSetup criteriaSetup, String tableId,
			int pageSize,int ratio) {
		int totalRows = RequestUtils.getTotalRowsFromRequest(
				getHttpServletRequest(), tableId);
		float ratio_f;
		int TotalCount=0;
		if(ratio<=0){
			totalRows=0;
		}
		else{
			ratio_f=ratio/100;
			totalRows=totalRows<=0?0:(int)(totalRows*ratio_f);
			
		}
		Limit limit = RequestUtils.getLimit(getHttpServletRequest(), tableId,
				totalRows, Page.DEFAULT_PAGE_SIZE);
		Page page = getEntityManager().pagedQueryEC(limit, criteriaSetup);
		
		this.setEntitys(page.getResult());
		if(ratio<=0){
			TotalCount=0;
		}
		else{
			ratio_f=ratio/100;
			TotalCount=(int)(page.getTotalCount()*ratio_f)<=0?0:(int)(page.getTotalCount()*ratio_f);
			
		}
		RequestUtils.setTotalRows(getHttpServletRequest(),tableId, TotalCount);
	}*/

	/**
	 * 新建业务对象的函数.
	 */
	protected T doNewEntity() {
		T object = null;
		try {
			object = getEntityClass().newInstance();
		} catch (Exception e) {
			logger.error("Can't new Instance of entity.", e);
		}
		return object;
	}

	/**
	 * 从数据库获取业务对象的函数.
	 */
	protected T doGetEntity() {
		Serializable id = getEntityId();
		return getEntityManager().get(id);
	}

	/**
	 * 保存业务对象的函数.
	 */
	protected void doSaveEntity(T object) {
		getEntityManager().save(object);
	}

	/**
	 * 删除业务对象的函数.
	 */
	protected void doDeleteEntity() {
		Serializable id = getEntityId();
		getEntityManager().removeById(id);
	}

	public String getIdName() {
		return idName;
	}

	public void setIdName(String idName) {
		this.idName = idName;
	}

	/**
	 * 将request中的entity内容通过BeanUtils的copyProperties()绑定到Object中.
	 * 因为BeanUtils中两个参数的顺序很容易搞错，因此封装此函数.
	 */
	protected void bindEntity(T entity, Object object) {
		if (entity != null) {
			try {
				BeanUtils.updateObject(object,entity);
			} catch (Exception e) {
				ReflectionUtils.handleReflectionException(e);
			}
		}
	}

	public String getLaststate() {
		return laststate;
	}

	public void setLaststate(String laststate) {
		this.laststate = laststate;
	}

}
