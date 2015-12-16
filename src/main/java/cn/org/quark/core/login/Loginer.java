package cn.org.quark.core.login;

import java.io.Serializable;

/**
 * 登录者信息
 * 
 */
public class Loginer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7085659714080583151L;

	/**
	 * 用户ID
	 */
	private String userid;

	/**
	 * 中文名
	 */
	private String cname;

	/**
	 * 登录ID
	 */
	private String loginid;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 登录者所在部门ID
	 */
	private String deptid;

	/**
	 * 登录者所在部门名称
	 */
	private String deptname;

	
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}
