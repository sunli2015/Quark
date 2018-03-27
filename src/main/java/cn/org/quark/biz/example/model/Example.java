package cn.org.quark.biz.example.model;

import cn.org.quark.core.BaseVo;


public class Example extends BaseVo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String oid ; 
	private String name ; 
	private String insertDate;
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	
	
	
}
