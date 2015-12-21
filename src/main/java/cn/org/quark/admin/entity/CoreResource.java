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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "CORE_RESOURCES")
public class CoreResource implements java.io.Serializable {

	private static final long serialVersionUID = 2613435851698286320L;
	@Id
	@Column(length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")	
	private String oid;
	
	@ManyToOne(targetEntity = CoreModule.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "MODULE_ID")
	private CoreModule module;
	
	@Column(length = 50)
	private String rcode;//编码标识
	@Column(length = 100)
	private String rname;
	//@Column(length = 10,name="RES_TYPE")
	//private String resType;
	@Column(length = 200,name="RES_STRING")
	private String resString;
	@Column(length = 200)
	private String descn;
	
	@ManyToMany(targetEntity=CoreRole.class,mappedBy="resources",
			cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.LAZY)
	private Set<CoreRole> roles = new HashSet<CoreRole>(0);
	
	@Transient
	private Boolean selected;
	
	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public CoreModule getModule() {
		return module;
	}

	public void setModule(CoreModule module) {
		this.module = module;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getResString() {
		return resString;
	}

	public void setResString(String resString) {
		this.resString = resString;
	}

	public String getDescn() {
		return descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}

	public Set<CoreRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<CoreRole> roles) {
		this.roles = roles;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public String getRcode() {
		return rcode;
	}

	public void setRcode(String rcode) {
		this.rcode = rcode;
	}


}