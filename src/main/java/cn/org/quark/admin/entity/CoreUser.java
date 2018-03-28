package cn.org.quark.admin.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "CORE_USERS")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","roles","dept"})
public class CoreUser implements java.io.Serializable {

	private static final long serialVersionUID = -8865934112915062913L;
	@Id
	@Column(length = 64)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String oid;//OID
	
	@ManyToOne(targetEntity = CoreDept.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPT_OID")
	private CoreDept dept;//部门
	@Column(length = 50)
	private String logid;//登陆名
	@Column(length = 50)
	private String cname;//姓名
	@Column(length = 100)
	private String password;//密码
	@Column(length = 50)
	private String idno;//工号
	@Column(length = 20)
	private String mobile;//手机号码
	@Column(length = 50)
	private String email;//邮件
	@Column(length = 1)
	private Long status;//状态

	@ManyToMany(targetEntity=CoreRole.class,
			cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(name="CORE_USER_ROLE",
			joinColumns={@JoinColumn(name="USERID")},
			inverseJoinColumns={@JoinColumn(name="ROLEID")})
	private Set<CoreRole> roles = new HashSet<CoreRole>(0);

	
	@Transient
	private String deptOid;
	@Transient
	private String deptName;
	
	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public CoreDept getDept() {
		return dept;
	}

	public void setDept(CoreDept dept) {
		this.dept = dept;
	}

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdno() {
		return idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Set<CoreRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<CoreRole> roles) {
		this.roles = roles;
	}

	public String getDeptOid() {
		return deptOid;
	}

	public void setDeptOid(String deptOid) {
		this.deptOid = deptOid;
	}

	public String getDeptName() {
		return dept.getDeptname();
	}
}