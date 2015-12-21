package cn.org.quark.admin.manager;

import java.util.List;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import cn.org.quark.admin.entity.CoreDept;
import cn.org.quark.core.Constants;
import cn.org.quark.core.dao.hibernate5.HibernateEntityDao;
import cn.org.quark.core.utils.UtilString;

@SuppressWarnings("unchecked")
@Service
@RemoteProxy(name="deptManager")
public class DeptManager extends HibernateEntityDao<CoreDept>{
	/**
	 * 得到部门树的第一层
	 * @return
	 */
	public List<CoreDept> getToplayer(){
		return this.createCriteria(Restrictions.isNull("parentDept")).list();
	}
	/**
	 * 得到某部门下的一层部门
	 * @param parentId
	 * @return
	 */
	public List<CoreDept> getOneLayer(String parentId){
		return this.createCriteria().addOrder(Order.asc("deptid")).createCriteria("parentDept").add(Restrictions.eq("oid", parentId)).list();
	}
	/**
	 * ajax调用
	 * 删除一个部门，包括子部门和人员
	 * @param deptId
	 */
	@RemoteMethod
	public void deleteDept(String deptId){
		this.removeById(deptId);
	}
	
	/**
	 * ajax 调用 检查部门下是否有子部门或人员
	 */
	@RemoteMethod
	public boolean canDel(String deptId){
		if(Constants.ROOT_DEPT_ID.equals(deptId)) return false;
		CoreDept d = this.get(deptId);
		if(d.getSubDepts().size()>0 || d.getUsers().size()>0){
			return false;
		}
		return true;
	}
	@RemoteMethod
	public boolean validDept(String oid ,String deptCode){
		CoreDept dept = null;
		if(UtilString.isEmpty(oid)){
			dept = (CoreDept) super.createCriteria(Restrictions.eq("deptid", deptCode)).uniqueResult();
		} else {
			dept = (CoreDept)super.createCriteria(Restrictions.ne("oid", oid)).add(Restrictions.eq("deptid", deptCode)).uniqueResult();
		}
		
		if(dept == null) return true;
		return false;
	}
}
