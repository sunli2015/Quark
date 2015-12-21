package cn.org.quark.admin.manager;

import java.util.List;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.org.quark.admin.entity.CoreModule;
import cn.org.quark.core.dao.hibernate5.HibernateEntityDao;



/**
 * 业务模块管理manager
 *
 */
@Service
@RemoteProxy
public class ModuManager extends HibernateEntityDao<CoreModule>{

	/**
	 * 只获得顶级节点
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CoreModule> getToplayer(){
		return this.createCriteria(Restrictions.isNull("parentModule")).list();
	}
	/**
	 * 得到某节点下的一层
	 * @param parentId
	 * @return
	 */
	public List<CoreModule> getOneLayer(String parentId){
		return this.createCriteria().addOrder(Order.asc("orderid")).createCriteria("parentModule").add(Restrictions.eq("oid", parentId)).list();
	}
	/**
	 * ajax调用
	 * 删除一个节点，包括子节点
	 * @param deptId
	 */
	@RemoteMethod
	public String deleteNode(String nodeId){
		if(!CollectionUtils.isEmpty(this.get(nodeId).getSubModules())){
			return "请先删除子模块！";
		}
		if(!CollectionUtils.isEmpty(this.get(nodeId).getResources())){
			return "请先删除下面的资源！";
		}
		this.removeById(nodeId);
		return "删除成功！";
	}
	
	/**
	 * 根据实体对象的类的名称得到module对象
	 * @param entityClassName
	 * @return
	 */
	public CoreModule findByClass(String entityClassName){
		String hql = " from CoreModule modu where modu.entityClassName = ?";
		 List<CoreModule> modules = this.find(hql,new Object[]{entityClassName});
		 return modules.isEmpty() ? null : modules.get(0);
	}
}
