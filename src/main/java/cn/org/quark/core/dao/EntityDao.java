package cn.org.quark.core.dao;

import java.io.Serializable;
import java.util.List;

import cn.org.quark.core.dao.support.CriteriaSetup;
import cn.org.quark.core.dao.support.Page;

/**
 * 针对单个Entity对象的操作定义.不依赖于具体ORM实现方案.
 *
 */
public interface EntityDao<T> {

	T get(Serializable id);

	List<T> getAll();

	void save(Object o);

	void remove(Object o);

	void removeById(Serializable id);

	/**
	 * 获取Entity对象的主键名.
	 */
	String getIdName(Class<T> clazz);
	
	Long getCount(CriteriaSetup criteriaSetup);
	
	Page<T> pagedQuery(CriteriaSetup criteriaSetup,int pageNo, int pageSize);
}
