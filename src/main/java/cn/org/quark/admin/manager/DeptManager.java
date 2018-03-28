package cn.org.quark.admin.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
	
	
	/**
	 * 通过ID获取所有子部门
	 * @param id
	 * @return
	 */
	public List<CoreDept> queryLayerChildrenDept(String id){
		List<CoreDept> list = queryChildrenDept(id);
		List<CoreDept> _layerChildrenDept = new ArrayList<CoreDept>();
		_buildLayerDept(_layerChildrenDept,list);
		return _layerChildrenDept;
	}
	/**
	 * 所有子部门放至LIST中
	 * @param appendList
	 * @param list
	 */
	private void _buildLayerDept(List<CoreDept> appendList ,Collection<CoreDept> list){
		for(CoreDept dept : list){
			appendList.add(dept);
			Set<CoreDept> subDept = dept.getSubDept();
			if(!subDept.isEmpty()){
				_buildLayerDept(appendList,subDept);
			} 
		}
		return ;
	}
	
	/**
	 * 获取当前部门及所有子部门
	 * @return
	 */
	public List<CoreDept> queryChildrenDept(String id){
		//treeHtml = new StringBuilder();
		list1.clear();
		List<CoreDept> list = null;
		if(UtilString.isEmpty(id)){
			list = this.createCriteria(Restrictions.isNull("parentDept")).list();
		} else {
			list = this.createCriteria(Restrictions.or(Restrictions.eq("parentDept.oid", id),Restrictions.eq("oid", id))).list();
		}

		for(CoreDept dept : list){
			CoreDept d = new CoreDept();
			d.setOid(dept.getOid());
			d.setDeptid(dept.getDeptid());
			d.setDeptname(dept.getDeptname());
			list1.add(d);
			_buildTree(d,dept);
		}
		System.out.println("list1:"+list1);
		return list1;
	}
	/**
	 * 构建生成菜单树
	 * @return
	 */
	public List<CoreDept> buildTree(){
		return queryChildrenDept(null);
	}
	private List<CoreDept> list1 = new ArrayList<CoreDept>();
	
	//private StringBuilder treeHtml ;
	
	private void _buildTree(CoreDept r ,CoreDept dept){
		Set<CoreDept> set = dept.getSubDepts();
		if(set.isEmpty()){
			//treeHtml.append("<li id='").append(dept.getOid()).append("'>").append(dept.getDeptname()).append("</li>");
			return ;
		}
		//treeHtml.append("<li><span>").append(dept.getDeptname()).append("</span>");
		for(Iterator<CoreDept> it = set.iterator();it.hasNext();){
			CoreDept _dept = it.next();
			
			CoreDept d = new CoreDept();
			d.setOid(_dept.getOid());
			d.setDeptid(_dept.getDeptid());
			d.setDeptname(_dept.getDeptname());
			r.getSubDept().add(d);
			//treeHtml.append("<ul>");
			
			_buildTree(d,_dept);
			//treeHtml.append("</ul>");
		}
		//treeHtml.append("</li>");
		
		return ;
	}
}
