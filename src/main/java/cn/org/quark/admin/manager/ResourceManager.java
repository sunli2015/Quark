package cn.org.quark.admin.manager;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.org.quark.admin.entity.CoreResource;
import cn.org.quark.admin.entity.CoreRole;
import cn.org.quark.core.dao.hibernate5.HibernateEntityDao;

@Service
public class ResourceManager extends HibernateEntityDao<CoreResource>{
	/**
	 * 
	 * @param roleId
	 * @param res
	 * @return
	 */
	public List<CoreResource> listByRole(String roleId,List<CoreResource> res){
		CoreRole role = this.get(CoreRole.class, roleId);
		for(CoreResource r:res){
			r.setSelected(r.getRoles().contains(role));
		}
		return res;
	}
}
