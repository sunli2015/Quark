package cn.org.quark.core.dao.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * 查询回调类,组织查询条件
 *
 */
public class CriteriaSetup {
    
    /**
     * 由业务传入多个查询条件
     * 由Restrictions类实现查询条件封装到List对象中
     */    
    private List<Criterion> criterions = new ArrayList<Criterion>();
    
    /** A List<Order> variable :排序条件 */
    private Map sortMap = new HashMap();
    
    /**  简单的过虑条件 */
    Map<String,Object> filters = new LinkedHashMap<String,Object>();
    
    public void setup(Criteria criteria) {
    	
        //得到简单条件
        if (filters != null && !filters.isEmpty()) {
            Set<String> keys = filters.keySet();
            for (String key : keys) {
                Object value = filters.get(key);
                if (StringUtils.isNotBlank(value.toString()))
                    criteria.add(Restrictions.eq(key, value));
            }
        }
        
        //多个查询条件
        List<Criterion> lsCris = getCriterions();
        if(lsCris != null && lsCris.size() > 0){
            for(Criterion criter : lsCris){
                criteria.add(criter);
            }
        }
        
        //多个排序条件
        Map sorts = getSortMap();
        if (!sortMap.isEmpty()) {
			for (Object o : sortMap.keySet()) {
				String fieldName = o.toString();
				String orderType = sortMap.get(fieldName).toString();
				
				// 处理嵌套属性如category.name,modify_user.id,暂时只处理一级嵌套
				if (fieldName.indexOf('.') != -1) {
					String alias = StringUtils.substringBefore(fieldName, ".");
					criteria.createAlias(alias, alias);
				}

				if ("asc".equalsIgnoreCase(orderType)) {
					criteria.addOrder(Order.asc(fieldName));
				} else {
					criteria.addOrder(Order.desc(fieldName));
				}
			}
		}
    }
	public List<Criterion> getCriterions() {
		return criterions;
	}

	public void setCriterions(List<Criterion> criterions) {
		this.criterions = criterions;
	}

	public Map<String, Object> getFilters() {
		return filters;
	}

	public void setFilters(Map<String, Object> filters) {
		this.filters = filters;
	}

	public Map getSortMap() {
		return sortMap;
	}

	public void setSorts(Map sortMap) {
		this.sortMap = sortMap;
	}
	
	public void addCriterion(Criterion criterion){
		this.getCriterions().add(criterion);
	}
	public void addSort(String field,String orderType){
		this.getSortMap().put(field, orderType);
	}
	public void addFilter(String key,Object value){
		this.getFilters().put(key, value);
	}
}

