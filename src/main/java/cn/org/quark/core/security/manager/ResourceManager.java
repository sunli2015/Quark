package cn.org.quark.core.security.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
/**
 * 权限资源管理器
 * @author Leo
 *
 */
public class ResourceManager extends JdbcDaoSupport {
	/**
	 * 通过资源标识，获取权限列表
	 * @param resString
	 * @return
	 */
	public List<Auth> queryAuthByResTag(String resTag){
		return (List<Auth>)this.getJdbcTemplate().query(queryAuthByResTag, new Object[]{resTag}, new RowMapper(){
			
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Auth(rs.getString(1),rs.getString(2));
			}
			
		});
	}
	public class Auth {
        private String rcode;
        private String role;

        public Auth(String rcode, String role) {
            this.rcode = rcode;
            this.role = role;
        }

        public String getRcode() {
            return rcode;
        }

        public String getRole() {
            return role;
        }
        public String toString(){
        	return this.role;
        }
    }
	private String queryAuthByResTag;
	public void setQueryAuthByResTag(String queryAuthByResTag) {
		this.queryAuthByResTag = queryAuthByResTag;
	}
	
}
