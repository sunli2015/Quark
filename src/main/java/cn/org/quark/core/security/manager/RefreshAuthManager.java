package cn.org.quark.core.security.manager;

import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import cn.org.quark.core.web.support.ServiceLocator;
/**
 * 权限组织结构变更，刷新权限
 * @author Leo
 *
 */
public class RefreshAuthManager {
	/**
	 * 
	 */
	public static void refresh(){
		
		FilterInvocationSecurityMetadataSource fids = (FilterInvocationSecurityMetadataSource)ServiceLocator.getBean("filterInvocationSecurityMetadataSource");
		FilterSecurityInterceptor filter = (FilterSecurityInterceptor) ServiceLocator.getBean("filterSecurityInterceptor");
		filter.destroy();
		filter.setSecurityMetadataSource(fids);
		
	}
}
