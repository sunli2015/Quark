package cn.org.quark.core.security;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * 通过JDBC管理资源
 * @author Leo
 *
 */
public class JdbcFilterInvocationDefinitionSourceFactoryBean extends JdbcDaoSupport implements FactoryBean<FilterInvocationSecurityMetadataSource>{
	
	
	public FilterInvocationSecurityMetadataSource getObject()
			throws Exception {
		return new DefaultFilterInvocationSecurityMetadataSource(this
	            .buildRequestMap());
	}

	public Class<?> getObjectType() {
		return FilterInvocationSecurityMetadataSource.class;
	}

	public boolean isSingleton() {
		return true;
	}
	protected LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> buildRequestMap() {
        LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = null;
        requestMap = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();

        Map<String, String> resourceMap = this.findResources();

        for (Map.Entry<String, String> entry : resourceMap.entrySet()) {
            String key = entry.getKey();
            requestMap.put(new AntPathRequestMatcher(key),
                (Collection<ConfigAttribute>) SecurityConfig.createListFromCommaDelimitedString(entry.getValue()) );
        }

        return requestMap;
    }
	protected Map<String, String> findResources() {
        Map<String, String> resourceMap = new LinkedHashMap<String, String>();
        ResourceMapping resourceMapping = new ResourceMapping(getDataSource(),resourceQuery);
        List<Resource> resources = resourceMapping.execute();
        for (Resource resource : resources) {
            String url = resource.getUrl();
            String role = resource.getRole();

            if (resourceMap.containsKey(url)) {
                String value = resourceMap.get(url);
                resourceMap.put(url, value + "," + role);
            } else {
                resourceMap.put(url, role);
            }
        }
        return resourceMap;
    }
	private String resourceQuery;
	/**
	 * query all resource 's sql
	 * @param resourceQuery
	 */
	public void setResourceQuery(String resourceQuery) {
        this.resourceQuery = resourceQuery;
    }
	/**
	 * resource pojo
	 * @author Leo
	 *
	 */
    private class Resource {
        private String url;
        private String role;

        public Resource(String url, String role) {
            this.url = url;
            this.role = role;
        }

        public String getUrl() {
            return url;
        }

        public String getRole() {
            return role;
        }
    }
    /**
     * resource jdbc operate dao
     * @author Leo
     *
     */
    private class ResourceMapping extends MappingSqlQuery<Resource> {
    	protected ResourceMapping(DataSource dataSource,String resourceQuery) {
	            super(dataSource, resourceQuery);
	            compile();
	    }
		@Override
		protected Resource mapRow(ResultSet rs, int rownum) throws SQLException {
			String url = rs.getString(1);
            String role = rs.getString(2);
            Resource resource = new Resource(url, role);
            return resource;
		}
    }

}
