package cn.org.quark.core.security.manager;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import cn.org.quark.core.login.Loginer;

/**
 * 
 * @author Leo
 *
 */
public class LoginerManager extends JdbcDaoSupport {
	
	public Loginer getLoginerByLoginidAndPasswd(String loginid)
			throws RuntimeException {

		SqlRowSet rowSet = this.getJdbcTemplate().queryForRowSet(loginerQuery,new Object[] { loginid });
		Loginer loginer = new Loginer();
		if (rowSet.next()) {
			loginer.setUserid(rowSet.getString(1));
			loginer.setLoginid(rowSet.getString(2));
			loginer.setCname(rowSet.getString(3));
			loginer.setPassword(rowSet.getString(4));
			loginer.setDeptid(rowSet.getString(5));
			loginer.setDeptname(rowSet.getString(6));
		}
		return loginer;
	}

	private String loginerQuery;
	public void setLoginerQuery(String loginerQuery) {
		this.loginerQuery = loginerQuery;
	}
}
