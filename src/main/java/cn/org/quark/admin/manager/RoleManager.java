package cn.org.quark.admin.manager;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Service;

import cn.org.quark.admin.entity.CoreResource;
import cn.org.quark.admin.entity.CoreRole;
import cn.org.quark.core.dao.hibernate5.HibernateEntityDao;

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
	public void changeRes(String roleid,String[] checked,String[] notchecked){
		System.out.println(roleid);
		CoreRole role = this.get(roleid);
		for(String id:notchecked){
			CoreResource res = this.get(CoreResource.class, id);
			if(role.getResources().contains(res)){
				role.getResources().remove(res);
//				authorityManager.renovateResource(res.getResString(), res.getResType(), role.getRoleid(),false);
			}
		}
		for(String id:checked){
			CoreResource res = this.get(CoreResource.class, id);
			if(!role.getResources().contains(res)){
				role.getResources().add(res);
//				authorityManager.renovateResource(res.getResString(), res.getResType(), role.getRoleid(),true);
			}
		}
	}
}
