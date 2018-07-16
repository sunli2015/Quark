package cn.org.quark.admin.entity;

import java.util.HashSet;
import java.util.LinkedHashSet;
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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "CORE_RESOURCES")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","roles","module","subs","parent"})
public class CoreResource implements java.io.Serializable {

	private static final long serialVersionUID = 2613435851698286320L;
	@Id
	@Column(length = 64)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")	
	private String oid;
	
	@ManyToOne(targetEntity = CoreModule.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "MODULE_ID")
	private CoreModule module;
	
	/**
	 * 
	 */
	@ManyToOne(targetEntity = CoreResource.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	private CoreResource parent;
	
	@Column(length = 50)
	private String rcode;//编码标识
	@Column(length = 100)
	private String rname;//资源名称
	@Column(length = 10,name="RES_TYPE")
	private String resType;//类型1：菜单2：按钮
	@Column(length = 200,name="RES_STRING")
	private String resString;//链接
	@Column(length = 200)
	private String descn;//资源描述
	
	@Column(length = 20,name="RES_ICON")
	private String resIcon;//资源图标
	
	@Column(length = 10,name="TREE_LEVEL")
	private Integer treeLevel;
	@Column(length = 10,name="TREE_LEAF")
	private String treeLeaf;
	/**
	 * 
	 */
	@OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
	@OrderBy("rname asc")
	private Set<CoreResource> subs = new LinkedHashSet<CoreResource>(0);
	
	
	
	@ManyToMany(targetEntity=CoreRole.class,mappedBy="resources",
			cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.LAZY)
	private Set<CoreRole> roles = new HashSet<CoreRole>(0);
	
	@Transient
	private Boolean selected;
	
	@Transient
	private String moduleOid;
	
	@Transient
	private String parentCode;
	@Transient
	private boolean isRoot;
	@Transient
	private boolean isTreeLeaf;
	
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

	public String getModuleOid() {
		return moduleOid;
	}

	public void setModuleOid(String moduleOid) {
		this.moduleOid = moduleOid;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public String getResIcon() {
		return resIcon;
	}

	public void setResIcon(String resIcon) {
		this.resIcon = resIcon;
	}

	public CoreResource getParent() {
		return parent;
	}

	public void setParent(CoreResource parent) {
		this.parent = parent;
	}

	public Set<CoreResource> getSubs() {
		return subs;
	}

	public void setSubs(Set<CoreResource> subs) {
		this.subs = subs;
	}

	public Integer getTreeLevel() {
		return treeLevel;
	}

	public void setTreeLevel(Integer treeLevel) {
		this.treeLevel = treeLevel;
	}

	public String getTreeLeaf() {
		return treeLeaf;
	}

	public void setTreeLeaf(String treeLeaf) {
		this.treeLeaf = treeLeaf;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public boolean getIsRoot() {
		if(this.treeLevel == 0){
			isRoot = true;
		} else {
			isRoot = false;
		}
		return isRoot;
	}

	public void setIsRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public boolean getIsTreeLeaf() {
		if("0".equals(this.treeLeaf)){
			isTreeLeaf = false;
		} else {
			isTreeLeaf = true;
		}
		return isTreeLeaf;
	}

	public void setIsTreeLeaf(boolean isTreeLeaf) {
		this.isTreeLeaf = isTreeLeaf;
	}
	

}