package cn.org.quark.core.security.manager;

import cn.org.quark.core.security.JdbcSecurityMetadataSource;
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
		JdbcSecurityMetadataSource jdbcSecurityMetadataSource = (JdbcSecurityMetadataSource) ServiceLocator.getBean("filterInvocationSecurityMetadataSource");
		jdbcSecurityMetadataSource.resetRequestMap();
		
	}
}
