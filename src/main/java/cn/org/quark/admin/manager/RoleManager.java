package cn.org.quark.admin.manager;

import java.util.List;
import java.util.Set;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.hibernate.Criteria;
import org.springframework.stereotype.Service;

import cn.org.quark.admin.entity.CoreResource;
import cn.org.quark.admin.entity.CoreRole;
import cn.org.quark.admin.entity.CoreUser;
import cn.org.quark.core.dao.hibernate5.HibernateEntityDao;
import cn.org.quark.core.dao.support.Page;

/**
 * 角色业务逻辑
 * @author Leo
 *
 */
@SuppressWarnings("unchecked")
@Service
@RemoteProxy(name="roleManager")
public class RoleManager extends HibernateEntityDao<CoreRole>{
	
	@RemoteMethod
	public void changeRes(String roleid,String[] checked){
//		System.out.println(roleid);
		CoreRole role = this.get(roleid);
		Set<CoreResource> resList= role.getResources();
		if(!resList.isEmpty()) resList.clear();
		for(String id:checked){
			CoreResource res = this.get(CoreResource.class, id);
			role.getResources().add(res);
		}
	}
	/**
	 * 
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<CoreRole> queryByUserId(String userId , int pageNo, int pageSize){
		CoreUser user = super.get(CoreUser.class, userId);
		Criteria criteria = this.createCriteria();
		Page<CoreRole> page= pagedQuery(criteria, pageNo, pageSize);
		
		List<CoreRole> list = page.getResult();
		for(CoreRole role : list){
			if(role.getUsers().contains(user)) role.setChecked(true);
		}
		
		return page;
	}

	 
}
