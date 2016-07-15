package cn.org.quark.admin.manager;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.org.quark.admin.entity.CoreResource;
import cn.org.quark.admin.entity.CoreRole;
import cn.org.quark.core.dao.hibernate5.HibernateEntityDao;
import cn.org.quark.core.security.manager.RefreshAuthManager;
import cn.org.quark.core.utils.UtilString;

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

	@Override
	public void removeById(Serializable id) {
		CoreResource res = super.get(id);
		if(res.getRoles().size()>0){
			for(Iterator<CoreRole> it = res.getRoles().iterator();it.hasNext();){
				CoreRole role = it.next();
				role.getResources().remove(res);
			}
			RefreshAuthManager.refresh();
		}
		super.removeById(id);
	}

	@Override
	public void save(Object o) {
		super.save(o);
		//有关联角色的资源变更，进行刷新权限
		CoreResource res = (CoreResource)o;
		if(!UtilString.isEmpty(res.getOid())){
			if(!res.getRoles().isEmpty()){
				RefreshAuthManager.refresh();
			}
		}
	}
	
}
