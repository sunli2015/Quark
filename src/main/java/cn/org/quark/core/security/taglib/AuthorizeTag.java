package cn.org.quark.core.security.taglib;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import cn.org.quark.core.security.manager.ResourceManager;
import cn.org.quark.core.security.manager.ResourceManager.Auth;
import cn.org.quark.core.web.support.ServiceLocator;

/**
 * 对用户授权进行认证的Tag, 如果用户没相应授权，则Tag中的Html代码全部不显示
 * @author leo
 */
public class AuthorizeTag extends TagSupport{

	private static final long serialVersionUID = 0L;

	private String res = "";

	// ~ Methods
	// ========================================================================================================

	public int doStartTag() throws JspException {
		ResourceManager resourceManager = (ResourceManager)ServiceLocator.getBean("authResourceManager");
		List<Auth> authes = resourceManager.queryAuthByResTag(res);
		if(authes == null || authes.isEmpty()){
			return Tag.SKIP_BODY;
		}
		Collection required = copy(authes);
		final Collection granted = getPrincipalAuthorities();
		//判断权限
		if ((null != res) && !"".equals(res)) {
			granted.retainAll(required);
			if (granted.isEmpty()) {
				return Tag.SKIP_BODY;
			}
		}
		return Tag.EVAL_BODY_INCLUDE;
	}

	/**
	 * 获取当前用户授权
	 * @return
	 */
	private Collection getPrincipalAuthorities() {
		Authentication currentUser = SecurityContextHolder.getContext()
				.getAuthentication();
		if (null == currentUser) {
			return Collections.EMPTY_LIST;
		}
		if ((null == currentUser.getAuthorities())
				|| (currentUser.getAuthorities().size() < 1)) {
			return Collections.EMPTY_LIST;
		}
		Set<String> grantedList = new HashSet<String>();
		Collection<GrantedAuthority> _list= (Collection<GrantedAuthority>) currentUser.getAuthorities();
		for(GrantedAuthority au : _list){
			grantedList.add(au.getAuthority());
		}
		return grantedList;
	}

	private Set copy(List<Auth> authes){
		Set target = new HashSet();
		for (Auth auth : authes) {
			target.add(auth.getRole());
		}
		return target;
	}
	public void setRes(String res) {
		this.res = res;
	}

}
