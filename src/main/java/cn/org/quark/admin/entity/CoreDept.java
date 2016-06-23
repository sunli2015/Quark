package cn.org.quark.admin.entity;

import java.util.HashSet;
import java.util.Set;





import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "CORE_DEPT")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","users","subDepts"})
public class CoreDept implements java.io.Serializable {

	private static final long serialVersionUID = -3803800474454329950L;
	@Id
	@Column(length = 64)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String oid;
	
	@Column(length = 100)
	private String deptid; //部门编号
	
	@Column(length = 100)
	private String deptname;//部门名称
	
	@Column(length = 50)
	private String abbreviation;   //部门简称
	
	@ManyToOne
	@JoinColumn(name = "PARENT_OID")
	private CoreDept parentDept;

	@OneToMany(mappedBy = "parentDept", cascade = CascadeType.REMOVE)
	private Set<CoreDept> subDepts = new HashSet<CoreDept>(0);

	@OneToMany(mappedBy = "dept", cascade = CascadeType.REMOVE)
	private Set<CoreUser> users = new HashSet<CoreUser>(0);

	@Transient
	private String parentDeptOid;
	@Transient
	private Set<CoreDept> subDept = new HashSet<CoreDept>(0);
	
	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
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

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public CoreDept getParentDept() {
		return parentDept;
	}

	public void setParentDept(CoreDept parentDept) {
		this.parentDept = parentDept;
	}

	public Set<CoreDept> getSubDepts() {
		return subDepts;
	}

	public void setSubDepts(Set<CoreDept> subDepts) {
		this.subDepts = subDepts;
	}

	public Set<CoreUser> getUsers() {
		return users;
	}

	public void setUsers(Set<CoreUser> users) {
		this.users = users;
	}

	public String getParentDeptOid() {
		return parentDeptOid;
	}

	public void setParentDeptOid(String parentDeptOid) {
		this.parentDeptOid = parentDeptOid;
	}

	public Set<CoreDept> getSubDept() {
		return subDept;
	}

	public void setSubDept(Set<CoreDept> subDept) {
		this.subDept = subDept;
	}

	

}