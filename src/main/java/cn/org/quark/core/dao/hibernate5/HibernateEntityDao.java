package cn.org.quark.core.dao.hibernate5;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;

import cn.org.quark.core.dao.EntityDao;
import cn.org.quark.core.dao.support.CriteriaSetup;
import cn.org.quark.core.dao.support.Page;
import cn.org.quark.core.utils.GenericsUtils;

/**
 * 负责为单个Entity对象提供CRUD操作的Hibernate DAO基类. <p/> 子类只要在类定义时指定所管理Entity的Class,
 * 即拥有对单个Entity对象的CRUD操作.
 * 
 * <pre>
 * public class UserManager extends HibernateEntityDao&lt;User&gt; {
 * }
 * </pre>
 * 
 * @see HibernateGenericDao
 */
@SuppressWarnings("unchecked")
public abstract class HibernateEntityDao<T> extends HibernateGenericDao
		implements EntityDao<T> {

	private Class<T> entityClass;// DAO所管理的Entity类型.

	/**
	 * 在构造函数中将泛型T.class赋给entityClass.
	 */
	public HibernateEntityDao() {

	}

	/**
	 * 取得entityClass.JDK1.4不支持泛型的子类可以抛开Class<T> entityClass,重载此函数达到相同效果。
	 */
	protected Class<T> getEntityClass() {
		if (entityClass == null)
			entityClass = GenericsUtils.getSuperClassGenricType(getClass());
		return entityClass;
	}

	/**
	 * 得到记录总条数
	 * 
	 * @return
	 */
	public Long getCount(CriteriaSetup criteriaSetup) {
		Criteria c = this.createCriteria();
		criteriaSetup.setup(c);
		return this.getCount(c);
	}

	/**
	 * 根据ID获取对象.
	 * 
	 * @see HibernateGenericDao#getId(Class,Object)
	 */
	public T get(Serializable id) {
		return get(getEntityClass(), id);
	}

	/**
	 * 获取全部对象
	 * 
	 * @see HibernateGenericDao#getAll(Class)
	 */
	public List<T> getAll() {
		return getAll(getEntityClass());
	}

	/**
	 * 获取全部对象,带排序参数.
	 * 
	 * @see HibernateGenericDao#getAll(Class,String,boolean)
	 */
	public List<T> getAll(String orderBy, boolean isAsc) {
		return getAll(getEntityClass(), orderBy, isAsc);
	}

	/**
	 * 根据ID移除对象.
	 * 
	 * @see HibernateGenericDao#removeById(Class,Serializable)
	 */
	public void removeById(Serializable id) {
		removeById(getEntityClass(), id);
	}

	/**
	 * 取得Entity的Criteria.
	 * 
	 * @see HibernateGenericDao#createCriteria(Class,Criterion[])
	 */
	public Criteria createCriteria(Criterion... criterions) {
		return createCriteria(getEntityClass(), criterions);
	}

	/**
	 * 取得Entity的Criteria,带排序参数.
	 * 
	 * @see HibernateGenericDao#createCriteria(Class,String,boolean,Criterion[])
	 */
	public Criteria createCriteria(String orderBy, boolean isAsc,
			Criterion... criterions) {
		return createCriteria(getEntityClass(), orderBy, isAsc, criterions);
	}

	/**
	 * 根据属性名和属性值查询对象.
	 * 
	 * @return 符合条件的对象列表
	 * @see HibernateGenericDao#findBy(Class,String,Object)
	 */
	public List<T> findBy(String propertyName, Object value) {
		return findBy(getEntityClass(), propertyName, value);
	}

	/**
	 * 根据属性名和属性值查询对象,带排序参数.
	 * 
	 * @return 符合条件的对象列表
	 * @see HibernateGenericDao#findBy(Class,String,Object,String,boolean)
	 */
	public List<T> findBy(String propertyName, Object value, String orderBy,
			boolean isAsc) {
		return findBy(getEntityClass(), propertyName, value, orderBy, isAsc);
	}

	/**
	 * 根据属性名和属性值查询单个对象.
	 * 
	 * @return 符合条件的唯一对象 or null
	 * @see HibernateGenericDao#findUniqueBy(Class,String,Object)
	 */
	public T findUniqueBy(String propertyName, Object value) {
		return findUniqueBy(getEntityClass(), propertyName, value);
	}
/*
	*//**
	 * 判断对象某些属性的值在数据库中唯一.
	 * 
	 * @param uniquePropertyNames
	 *            在POJO里不能重复的属性列表,以逗号分割 如"name,loginid,password"
	 * @see HibernateGenericDao#isUnique(Class,Object,String)
	 *//*
	public boolean isUnique(Object entity, String uniquePropertyNames) {
		return isUnique(getEntityClass(), entity, uniquePropertyNames);
	}*/

	/**
	 * 消除与 Hibernate Session 的关联
	 * 
	 * @param entity
	 */
	public void evit(Object entity) {
		getSession().evict(entity);
	}
/*	*//**
	 * 得到数据库字段名
	 * @param propertyName
	 * @return
	 *//*
	public String getColumnByProperty(String propertyName){
		return this.getColumnName(entityClass, propertyName);
	}*/
	
	public Page pagedQuery(CriteriaSetup criteriaSetup, int pageNo, int pageSize) {
		Criteria criteria = this.createCriteria();
		criteriaSetup.setup(criteria);
		return pagedQuery(criteria, pageNo, pageSize);
	}

/*	*//**
	 * 支持EC控件的查询方法
	 * 
	 * @param filterMap
	 *            过滤条件
	 * @param limit
	 *            EC的limit对象
	 * @return
	 * @throws Exception
	 *             TODO:去除日期类型模糊查询带的Oracle物理特性
	 *//*
	public Page pagedQueryEC(Limit limit, CriteriaSetup criteriaSetup) {
		Criteria criteria = createCriteria();
		Map orderMap = ExtremeTablePage.getSort(limit);
		if(!CollectionUtils.isEmpty(orderMap)){
			criteriaSetup.getSortMap().clear();
		}
		criteriaSetup.setup(criteria);
		if (!CollectionUtils.isEmpty(orderMap)){
			sortCriteria(criteria, orderMap, getEntityClass());
		}
		if (limit.isFiltered()) {
			Filter[] filters = limit.getFilterSet().getFilters();
			for (Filter filter : filters) {
				try {
					Field propfield;
					Class claz = this.getEntityClass();
					// 处理多层关系
					String pname = filter.getProperty();
					String lastname = pname;
					if (pname.indexOf('.') != -1) {
						String alias = StringUtils.substringBefore(pname, ".");
						//避免和排序重复alias
						if(CollectionUtils.isEmpty(orderMap) || !orderMap.containsKey(pname))
							criteria.createAlias(alias, alias);
	
						String[] pnames = pname.split("\\.");
						for(int i=0;i<pnames.length-1;i++){
							claz = BeanUtils.getDeclaredField(claz, pnames[i]).getType();
						}
						lastname = pnames[pnames.length-1];
					}
					
					propfield = BeanUtils.getDeclaredField(claz, lastname);
					Class type = propfield.getType();
					if ((this.getDialect().indexOf("Oracle")>0)
							&&(type.equals(java.util.Date.class)||type.equals(java.sql.Date.class)||type.equals(java.sql.Timestamp.class)||type.equals(java.sql.Time.class))){
						criteria.add(Restrictions.sqlRestriction("to_char({alias}."+pname+",'"+getDateFormatByProperty(filter.getProperty())+"') like ? ","%"+filter.getValue()+"%",Hibernate.STRING));
					}else if(type.equals(java.lang.String.class)){
						criteria.add(Restrictions.like(pname, filter.getValue(), MatchMode.ANYWHERE));
					}else{
						criteria.add(Restrictions.sqlRestriction("{alias}."+pname+" like ?","%"+filter.getValue()+"%",Hibernate.STRING));
					}
				} catch (NoSuchFieldException e) {
					logger.error("can't find this field: "
							+ filter.getProperty(), e);
				}
			}
		}

		return pagedQuery(criteria, limit.getPage(), limit
				.getCurrentRowsDisplayed());
	}*/
	
/*	*//**
	 * 
	 * @version Jul 27, 2010 2:37:16 PM
	 *
	 *传入Sql,传入指定VO的Class类型 用于ec分页.
	 *注意装载数据的VO属性 与 SQL查询字段
	 * @param hql 一般SQL
	 * @param pageNo 分页参数
	 * @param pageSize 分页参数
	 * @param targe Class类型
	 * @return Page
	 *//*
	public Page pagedQuery(String hql, int pageNo, int pageSize, Class targe) {
		Assert.hasText(hql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		// Count查询
		Assert.hasText(hql);
		// 实际查询返回分页对象
		int totalCount = this.getSession().createSQLQuery(hql).list().size();
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		Query query = this.getSession().createSQLQuery(hql).setResultTransformer(Transformers.aliasToBean(targe));
		List list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
		//处理得到的集合
		list = processPageList(list);
		return new Page(startIndex, totalCount, pageSize, list);
	}*/
	
/*	
	*//**
	 * 通过pojo中的属性名得到时间格式 这是模糊查询时间字段的时候需要用到 如果与默认格式不同则请在子类中重构该方法
	 * 
	 * @param propertyName
	 * @return
	 *//*
	protected String getDateFormatByProperty(String propertyName) {
		return "yyyy-MM-dd hh24:mi";
	}*/

}
