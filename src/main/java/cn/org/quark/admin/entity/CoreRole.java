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
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "CORE_ROLES")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","resources","users"})
public class CoreRole implements java.io.Serializable {

	private static final long serialVersionUID = -8116465701943141377L;
	@Id
	@Column(length = 64)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String oid;
	@Column(length = 50)
	private String rolecode;
	@Column(length = 100)
	private String rolename;
	@Column(length = 200)
	private String descn;
	
	@ManyToMany(targetEntity=CoreResource.class,
			cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.LAZY)
	@JoinTable(name="CORE_ROLE_RESOURCE",
			joinColumns={@JoinColumn(name="ROLEID")},
			inverseJoinColumns={@JoinColumn(name="RESID")})
	private Set<CoreResource> resources = new HashSet<CoreResource>(0);
	
	@ManyToMany(targetEntity=CoreUser.class,mappedBy="roles",
			cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private Set<CoreUser> users = new HashSet<CoreUser>(0);

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = "ROLE_"+rolecode;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getDescn() {
		return descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}

	public Set<CoreResource> getResources() {
		return resources;
	}

	public void setResources(Set<CoreResource> resources) {
		this.resources = resources;
	}

	public Set<CoreUser> getUsers() {
		return users;
	}

	public void setUsers(Set<CoreUser> users) {
		this.users = users;
	}
	
	
	
}