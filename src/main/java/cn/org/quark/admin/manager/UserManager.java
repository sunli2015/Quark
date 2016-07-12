package cn.org.quark.admin.manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.BasePasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Service;

import cn.org.quark.admin.entity.CoreRole;
import cn.org.quark.admin.entity.CoreUser;
import cn.org.quark.core.Constants;
import cn.org.quark.core.dao.hibernate5.HibernateEntityDao;
import cn.org.quark.core.security.manager.RefreshAuthManager;
import cn.org.quark.core.utils.UtilString;
/**
 * 用户管理器
 * @author Leo
 *
 */
@SuppressWarnings("unchecked")
@Service
@RemoteProxy(name="userManager")
public class UserManager extends HibernateEntityDao<CoreUser>{
	
	@Override
	public void save(Object o) {
		CoreUser coreUser = (CoreUser)o;
		if(UtilString.isEmpty(coreUser.getPassword())){
			coreUser.setPassword(passwordEncoder.encodePassword(Constants.DEFAULT_PASSWD, null));
			
		}
		super.save(o);
	}
	/**
	 * 密码是否正确
	 * @param oid
	 * @param pwd
	 * @return
	 */
	public boolean isRightPwd(String oid,String pwd){
		CoreUser user = this.get(oid);
		if(user!=null && user.getPassword().equals(passwordEncoder.encodePassword(pwd,null))){
			return true;
		}
		return false;
	}
	/**
	 * 修改密码
	 * @param oid
	 * @param pwd
	 */
	@RemoteMethod
	public void changePwd(String oid,String pwd){
		CoreUser user = this.get(oid);
		if(UtilString.isEmpty(pwd)) pwd = Constants.DEFAULT_PASSWD;
		user.setPassword(passwordEncoder.encodePassword(pwd, null));
		this.save(user);
	}
	/**
	 * 修改状态
	 * @param oid
	 * @param status
	 */
	@RemoteMethod
	public void changeStatus(String oid , String status){
		CoreUser user = this.get(oid);
		user.setStatus(Long.valueOf(status));
	}
	/**
	 * 通过用户ID获取权限列表
	 * @param oid
	 * @return
	 */
	@RemoteMethod
	public List<String[]> getRoles(String oid){
		List<String[]> rt = new ArrayList<String[]>();
		List<CoreRole> allRoles = this.getAll(CoreRole.class);
		Set<CoreRole> myRoles = this.get(oid).getRoles();
		Iterator<CoreRole> it = allRoles.iterator();
		while(it.hasNext()){
			CoreRole inr = it.next();
			String[] ins = new String[]{inr.getOid(),inr.getRolename(),myRoles.contains(inr)?"checked":""};
			rt.add(ins);
		}
		return rt;
	}
	/**
	 * 通过权限ID修改权限，重新加载权限
	 * @param oid
	 * @param roleids
	 */
	@RemoteMethod
	public void saveRoles(String oid,String[] roleids){	
//		Collection<GrantedAuthorityImpl> auths = new HashSet();
		Set<CoreRole> set = new HashSet<CoreRole>();
		for(int i=0;i<roleids.length;i++){
			CoreRole r = new CoreRole();
			r.setOid(roleids[i]);
//			GrantedAuthorityImpl authority = new GrantedAuthorityImpl("ROLE_"+roleids[i]);
//			auths.add(authority);
			set.add(r);
		}
		CoreUser u = this.get(oid);
		u.setRoles(set);
		super.save(u);
		RefreshAuthManager.refresh();
	}
	@Autowired
	private BasePasswordEncoder passwordEncoder;
}
